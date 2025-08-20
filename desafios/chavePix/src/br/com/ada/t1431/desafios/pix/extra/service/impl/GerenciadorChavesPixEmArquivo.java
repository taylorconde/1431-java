package br.com.ada.t1431.desafios.pix.extra.service.impl;

import br.com.ada.t1431.desafios.pix.extra.model.exception.ChavePixExistenteException;
import br.com.ada.t1431.desafios.pix.extra.model.exception.ChavePixNaoExistenteException;
import br.com.ada.t1431.desafios.pix.extra.model.ChavePixComDadosBancarios;
import br.com.ada.t1431.desafios.pix.extra.service.GerenciadorChavesPix;
import br.com.ada.t1431.desafios.pix.model.exception.ChavePixFormatoInvalidoException;
import br.com.ada.t1431.desafios.pix.extra.respository.RepositorioDeChavesEmArquivo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementação de {@link GerenciadorChavesPix} que persiste os dados em um arquivo local.
 * <p>
 * Esta classe utiliza um {@link RepositorioDeChavesEmArquivo} para lidar com as operações de I/O,
 * focando na lógica de negócio de manipulação das chaves.
 */
public class GerenciadorChavesPixEmArquivo implements GerenciadorChavesPix {

    private static final String SEPARADOR = ";";
    private final RepositorioDeChavesEmArquivo repositorio;

    /**
     * Constrói o gerenciador, especificando o diretório para armazenamento dos dados.
     * @param diretorio o caminho para o diretório onde o arquivo de chaves será salvo.
     */
    public GerenciadorChavesPixEmArquivo(String diretorio) {
        this.repositorio = new RepositorioDeChavesEmArquivo(diretorio);
    }

    @Override
    public void save(ChavePixComDadosBancarios chavePixComDadosBancarios) throws ChavePixExistenteException, ChavePixFormatoInvalidoException {
        
        chavePixComDadosBancarios.getChavePix().validar();
        
        if (findBy(chavePixComDadosBancarios.getChavePix().getValor()).isPresent()) {
            throw new ChavePixExistenteException(
                    "Chave PIX já cadastrada: " + chavePixComDadosBancarios.getChavePix().getValor());
        }

        List<ChavePixComDadosBancarios> chaves = lerTodasAsChaves();
        chaves.add(chavePixComDadosBancarios);
        escreverChaves(chaves);
    }

    @Override
    public Optional<ChavePixComDadosBancarios> findBy(String valorChave) {
        return lerTodasAsChaves().stream().filter(chave -> chave.getChavePix().getValor().equals(valorChave))
                .findFirst();
    }

    @Override
    public void remove(String valorChave) throws ChavePixNaoExistenteException {
        List<ChavePixComDadosBancarios> chaves = lerTodasAsChaves();
        boolean foiRemovido = chaves.removeIf(chave -> chave.getChavePix().getValor().equals(valorChave));

        if (!foiRemovido) {
            throw new ChavePixNaoExistenteException("Chave PIX não encontrada para remoção: " + valorChave);
        }

        escreverChaves(chaves);
    }

    private List<ChavePixComDadosBancarios> lerTodasAsChaves() {
        return repositorio.lerLinhas().stream().map(this::parseLinha).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private void escreverChaves(List<ChavePixComDadosBancarios> chaves) {
        List<String> linhas = chaves.stream().map(this::formatarLinha).collect(Collectors.toList());
        repositorio.escreverLinhas(linhas);
    }

    private ChavePixComDadosBancarios parseLinha(String linha) {
        final int NUMERO_DE_PARTES_ESPERADO = 5;
        final String MENSAGEM_ERRO_FORMATO = "Linha mal formatada no arquivo de chaves, será ignorada: ";
        String[] partes = linha.split(SEPARADOR, NUMERO_DE_PARTES_ESPERADO);
        if (partes.length < NUMERO_DE_PARTES_ESPERADO) {
            System.err.println(MENSAGEM_ERRO_FORMATO + linha);
            return null;
        }
        
        return ChavePixComDadosBancarios.builder().codigoInstituicao(partes[0]).agencia(partes[1]).conta(partes[2])
                .tipoChave(partes[3]).valorChave(partes[4]).build();
    }

    private String formatarLinha(ChavePixComDadosBancarios chave) {
        return String.join(SEPARADOR, chave.getDadosBancarios().codigoInstituicao(),
                chave.getDadosBancarios().agencia(), chave.getDadosBancarios().conta(),
                chave.getChavePix().getTipoChave().name(), chave.getChavePix().getValor());
    }
}