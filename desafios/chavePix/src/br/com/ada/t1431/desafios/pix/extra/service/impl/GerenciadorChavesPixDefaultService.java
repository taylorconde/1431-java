package br.com.ada.t1431.desafios.pix.extra.service.impl;

import br.com.ada.t1431.desafios.pix.extra.model.ChavePixComDadosBancarios;
import br.com.ada.t1431.desafios.pix.extra.model.exception.ChavePixExistenteException;
import br.com.ada.t1431.desafios.pix.extra.model.exception.ChavePixNaoExistenteException;
import br.com.ada.t1431.desafios.pix.extra.repository.ChavePixRepository;
import br.com.ada.t1431.desafios.pix.extra.service.GerenciadorChavesPix;
import br.com.ada.t1431.desafios.pix.model.exception.ChavePixFormatoInvalidoException;

import java.util.Optional;

/**
 * Implementação padrão da interface {@link GerenciadorChavesPix}.
 * Esta classe orquestra as operações de negócio para o gerenciamento de chaves Pix,
 * utilizando um {@link ChavePixRepository} para a camada de persistência.
 */
public class GerenciadorChavesPixDefaultService implements GerenciadorChavesPix {

    private final ChavePixRepository repositorio;

    /**
     * Constrói uma nova instância do serviço de gerenciamento de chaves Pix.
     *
     * @param repositorio A implementação do repositório que será usada para persistir os dados.
     */
    public GerenciadorChavesPixDefaultService(ChavePixRepository repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * Valida e salva uma nova chave Pix.
     * A chave é primeiro validada quanto ao formato. Em seguida, o repositório é consultado
     * para garantir que a chave ainda não existe antes de ser salva.
     *
     * @param chavePixComDadosBancarios O objeto contendo a chave Pix e os dados bancários associados.
     * @throws ChavePixExistenteException     Se uma chave com o mesmo valor já estiver cadastrada.
     * @throws ChavePixFormatoInvalidoException Se o formato da chave Pix for inválido.
     */
    @Override
    public void save(ChavePixComDadosBancarios chavePixComDadosBancarios) throws ChavePixExistenteException, ChavePixFormatoInvalidoException {
        chavePixComDadosBancarios.getChavePix().validar();

        if (repositorio.findBy(chavePixComDadosBancarios.getChavePix().getValor()).isPresent()) {
            throw new ChavePixExistenteException(
                    "Chave PIX já cadastrada: " + chavePixComDadosBancarios.getChavePix().getValor());
        }

        this.repositorio.save(chavePixComDadosBancarios);
    }

    /**
     * Busca por uma chave Pix com base no seu valor.
     *
     * @param valorChave O valor da chave Pix a ser procurada.
     * @return Um {@link Optional} contendo a chave Pix se encontrada, ou um {@link Optional} vazio caso contrário.
     */
    @Override
    public Optional<ChavePixComDadosBancarios> findBy(String valorChave) {
        return this.repositorio.findBy(valorChave);
    }

    /**
     * Remove uma chave Pix do sistema com base no seu valor.
     *
     * @param valorChave O valor da chave Pix a ser removida.
     * @throws ChavePixNaoExistenteException Se nenhuma chave Pix com o valor especificado for encontrada.
     */
    @Override
    public void remove(String valorChave) throws ChavePixNaoExistenteException {
        boolean removed = this.repositorio.remove(valorChave);
        if (!removed) {
            throw new ChavePixNaoExistenteException("Chave PIX não encontrada: " + valorChave);
        }
    }
}
