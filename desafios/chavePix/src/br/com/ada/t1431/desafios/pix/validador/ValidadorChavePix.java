package br.com.ada.t1431.desafios.pix.validador;

import br.com.ada.t1431.desafios.pix.model.exception.ChavePixFormatoInvalidoException;

/**
 * Interface que define o contrato para validadores de chaves PIX.
 * Cada implementação será responsável por validar um tipo específico de chave.
 */
public interface ValidadorChavePix {
    /**
     * Valida se o valor fornecido corresponde ao formato esperado para o tipo de chave.
     * @throws ChavePixFormatoInvalidoException se o valor for inválido.
     */
    void validar(String valor) throws ChavePixFormatoInvalidoException;
}