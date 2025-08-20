package br.com.ada.t1431.desafios.pix.validador;

import br.com.ada.t1431.desafios.pix.model.TipoChave;
import br.com.ada.t1431.desafios.pix.validador.impl.ValidadorCNPJ;
import br.com.ada.t1431.desafios.pix.validador.impl.ValidadorCPF;
import br.com.ada.t1431.desafios.pix.validador.impl.ValidadorCelular;
import br.com.ada.t1431.desafios.pix.validador.impl.ValidadorEmail;
import br.com.ada.t1431.desafios.pix.extra2.validador.ValidadorChaveAleatoria;

/**
 * Fábrica responsável por fornecer a instância correta do validador
 * com base no tipo de chave Pix.
 */
public final class ValidadorChavePixFactory {

    private ValidadorChavePixFactory() {}
    
    /**
     * Retorna a instância do validador apropriado para o tipo de chave.
     *
     * @param tipoChave O tipo da chave Pix.
     * @return Uma instância de ValidadorChavePix.
     */
    public static ValidadorChavePix getValidador(TipoChave tipoChave) {
        return switch (tipoChave) {
            case CPF -> new ValidadorCPF();
            case CNPJ -> new ValidadorCNPJ();
            case EMAIL -> new ValidadorEmail();
            case CELULAR -> new ValidadorCelular();
            case ALEATORIA -> new ValidadorChaveAleatoria();
        };
    }
}