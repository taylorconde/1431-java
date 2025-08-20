package br.com.ada.t1431.desafios.pix.extra.service;

import br.com.ada.t1431.desafios.pix.extra.model.exception.ChavePixExistenteException;
import br.com.ada.t1431.desafios.pix.extra.model.exception.ChavePixNaoExistenteException;
import br.com.ada.t1431.desafios.pix.extra.model.ChavePixComDadosBancarios;
import br.com.ada.t1431.desafios.pix.model.exception.ChavePixFormatoInvalidoException;

import java.util.Optional;

/**
 * Interface que define o contrato para serviços de gerenciamento de chaves PIX.
 * <p>
 * As implementações desta interface são responsáveis pela lógica de negócio
 * para salvar, remover e buscar chaves PIX em um meio de persistência.
 */
public interface GerenciadorChavesPix {

    /**
     * Salva uma nova chave PIX no sistema.
     *
     * @param chavePixExtension o objeto contendo a chave PIX e os dados bancários.
     * @throws ChavePixExistenteException se a chave PIX já estiver cadastrada.
     * @throws ChavePixFormatoInvalidoException se o formato da chave PIX for inválido.
     */
    void save(ChavePixComDadosBancarios chavePixExtension) throws ChavePixExistenteException, ChavePixFormatoInvalidoException;
    /**
     * Remove uma chave PIX do sistema com base no seu valor.
     *
     * @param valorChave o valor da chave a ser removida.
     * @throws ChavePixNaoExistenteException se a chave PIX não for encontrada.
     */
    void remove(String valorChave) throws ChavePixNaoExistenteException;
    /**
     * Busca uma chave PIX no sistema pelo seu valor.
     *
     * @param valorChave o valor da chave a ser buscada.
     * @return um {@link Optional} contendo a {@link ChavePixComDadosBancarios} se encontrada, ou vazio caso contrário.
     */
    Optional<ChavePixComDadosBancarios> findBy(String valorChave);
}
