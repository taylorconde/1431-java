package br.com.ada.t1431.desafios.pix.extra.repository.impl;

import br.com.ada.t1431.desafios.pix.extra.model.ChavePixComDadosBancarios;
import br.com.ada.t1431.desafios.pix.extra.repository.ChavePixRepository;
import br.com.ada.t1431.desafios.pix.extra.util.FileOperations;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementação de {@link ChavePixRepository} que persiste as chaves Pix em arquivos.
 * Cada chave Pix é armazenada como uma linha em um arquivo, com os campos separados por um delimitador.
 */
public class ChavePixRepositoryEmArquivo implements ChavePixRepository {

    /**
     * O separador usado para delimitar os campos de uma chave Pix na linha do arquivo.
     */
    private static final String SEPARADOR = ";";
    private final FileOperations fileOperations;

    /**
     * Construtor que inicializa o repositório com o diretório onde os arquivos de chaves serão armazenados.
     *
     * @param diretorio O caminho do diretório para armazenar os dados das chaves Pix.
     */
    public ChavePixRepositoryEmArquivo(String diretorio) {
        this.fileOperations = new FileOperations(diretorio, ".chaves");
    }

    /**
     * Salva ou atualiza uma chave Pix no repositório.
     * Se uma chave com o mesmo valor já existir, ela é substituída (comportamento de "upsert").
     * Isso garante que não haverá chaves duplicadas.
     *
     * @param chavePixComDadosBancarios A chave Pix a ser salva ou atualizada.
     * @return A chave Pix que foi salva.
     */
    @Override
    public ChavePixComDadosBancarios save(ChavePixComDadosBancarios chavePixComDadosBancarios) {
        List<ChavePixComDadosBancarios> chaves = lerTodasAsChaves();
        chaves.removeIf(chave -> chave.getChavePix().getValor().equals(chavePixComDadosBancarios.getChavePix().getValor()));
        chaves.add(chavePixComDadosBancarios);
        escreverChaves(chaves);
        return chavePixComDadosBancarios;
    }

    /**
     * Busca uma chave Pix pelo seu valor.
     *
     * @param valorChave O valor da chave Pix a ser procurada.
     * @return Um {@link Optional} contendo a chave se encontrada, ou um {@link Optional} vazio caso contrário.
     */
    @Override
    public Optional<ChavePixComDadosBancarios> findBy(String valorChave) {
        return lerTodasAsChaves().stream()
                .filter(chave -> chave.getChavePix().getValor().equals(valorChave))
                .findFirst();
    }

    /**
     * Remove uma chave Pix do repositório com base no seu valor.
     *
     * @param valorChave O valor da chave Pix a ser removida.
     * @return {@code true} se a chave foi encontrada e removida com sucesso, {@code false} caso contrário.
     */
    @Override
    public boolean remove(String valorChave) {
        List<ChavePixComDadosBancarios> chaves = lerTodasAsChaves();
        boolean foiRemovido = chaves.removeIf(chave -> chave.getChavePix().getValor().equals(valorChave));

        if (!foiRemovido) {
            return false;
        }

        escreverChaves(chaves);
        return true;
    }

    /**
     * Lê todas as linhas do arquivo de chaves e as converte em uma lista de objetos {@link ChavePixComDadosBancarios}.
     * Ignora linhas mal formatadas.
     *
     * @return Uma lista de {@link ChavePixComDadosBancarios} presentes no arquivo.
     */
    private List<ChavePixComDadosBancarios> lerTodasAsChaves() {
        return fileOperations.lerLinhas().stream()
                .map(this::parseLinha)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Escreve a lista de {@link ChavePixComDadosBancarios} de volta para o arquivo, sobrescrevendo o conteúdo existente.
     * Cada objeto é formatado como uma linha de texto antes de ser escrito.
     *
     * @param chaves A lista de chaves Pix a ser escrita no arquivo.
     */
    private void escreverChaves(List<ChavePixComDadosBancarios> chaves) {
        List<String> linhas = chaves.stream()
                .map(this::formatarLinha)
                .collect(Collectors.toList());
        fileOperations.escreverLinhas(linhas);
    }

    /**
     * Converte uma linha de texto do arquivo em um objeto {@link ChavePixComDadosBancarios}.
     * Espera que a linha esteja formatada com campos separados por {@code SEPARADOR}.
     *
     * @param linha A linha de texto a ser parseada.
     * @return Um objeto {@link ChavePixComDadosBancarios} se a linha estiver bem formatada, ou {@code null} caso contrário.
     */
    private ChavePixComDadosBancarios parseLinha(String linha) {
        final int NUMERO_DE_PARTES_ESPERADO = 5;
        String[] partes = linha.split(SEPARADOR, NUMERO_DE_PARTES_ESPERADO);
        if (partes.length < NUMERO_DE_PARTES_ESPERADO) {
            System.err.println("Linha mal formatada no arquivo de chaves, será ignorada: " + linha);
            return null;
        }
        return ChavePixComDadosBancarios.builder()
                .codigoInstituicao(partes[0])
                .agencia(partes[1])
                .conta(partes[2])
                .tipoChave(partes[3])
                .valorChave(partes[4])
                .build();
    }

    /**
     * Formata um objeto {@link ChavePixComDadosBancarios} em uma linha de texto para ser escrita no arquivo.
     * Os campos são unidos usando o {@code SEPARADOR}.
     *
     * @param chave O objeto {@link ChavePixComDadosBancarios} a ser formatado.
     * @return Uma string representando a chave Pix formatada para o arquivo.
     */
    private String formatarLinha(ChavePixComDadosBancarios chave) {
        return String.join(SEPARADOR,
                chave.getDadosBancarios().codigoInstituicao(),
                chave.getDadosBancarios().agencia(),
                chave.getDadosBancarios().conta(),
                chave.getChavePix().getTipoChave().name(),
                chave.getChavePix().getValor());
    }
}
