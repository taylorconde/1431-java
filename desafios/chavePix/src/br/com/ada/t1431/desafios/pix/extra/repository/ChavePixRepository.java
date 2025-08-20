package br.com.ada.t1431.desafios.pix.extra.repository;

import br.com.ada.t1431.desafios.pix.extra.model.ChavePixComDadosBancarios;
import java.util.Optional; 

/**
 * Interface para o repositório de Chaves Pix com Dados Bancários.
 * Define as operações básicas de persistência para as chaves Pix.
 */
public interface ChavePixRepository {
    /**
     * Salva uma nova chave Pix com dados bancários ou atualiza uma existente.
     *
     * @param chave A chave Pix com dados bancários a ser salva.
     * @return A chave Pix com dados bancários salva.
     */
    ChavePixComDadosBancarios save(ChavePixComDadosBancarios chave);

    /**
     * Busca uma chave Pix com dados bancários pelo seu valor.
     *
     * @param valorChave O valor da chave Pix a ser buscada.
     * @return Um {@link Optional} contendo a chave Pix se encontrada, ou vazio caso contrário.
     */
    Optional<ChavePixComDadosBancarios> findBy(String valorChave);

    /**
     * Remove uma chave Pix com dados bancários pelo seu valor.
     *
     * @param valorChave O valor da chave Pix a ser removida.
     * @return {@code true} se a chave foi removida com sucesso, {@code false} caso contrário.
     */
    boolean remove(String valorChave);
}