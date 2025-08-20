package br.com.ada.t1431.desafios.pix.validador.impl;

import br.com.ada.t1431.desafios.pix.model.exception.ChavePixFormatoInvalidoException;
import br.com.ada.t1431.desafios.pix.validador.ValidadorChavePix;

/**
 * Implementação de ValidadorChavePix para chaves do tipo CPF.
 */
public class ValidadorCPF implements ValidadorChavePix {
    @Override
    public void validar(String valor) throws ChavePixFormatoInvalidoException {
        final String REGEX_FORMATO_CPF = "\\d{11}";
        final String REGEX_DIGITOS_IGUAIS = "(\\d)\\1{10}";
        final String MENSAGEM_FORMATO_INVALIDO = "Formato de CPF inválido. Deve conter 11 dígitos numéricos e não podem ser todos iguais.";
        final String MENSAGEM_DIGITOS_VERIFICADORES_INVALIDOS = "CPF inválido. Os dígitos verificadores não conferem.";

        if (valor == null || !valor.matches(REGEX_FORMATO_CPF) || valor.matches(REGEX_DIGITOS_IGUAIS)) {
            throw new ChavePixFormatoInvalidoException(MENSAGEM_FORMATO_INVALIDO);
        }

        int digito1 = calcularDigitoVerificador(valor, 9);
        int digito2 = calcularDigitoVerificador(valor, 10);

        if (digito1 != (valor.charAt(9) - '0') || digito2 != (valor.charAt(10) - '0')) {
            throw new ChavePixFormatoInvalidoException(MENSAGEM_DIGITOS_VERIFICADORES_INVALIDOS);
        }
    }

    private int calcularDigitoVerificador(String cpf, int tamanho) {
        int soma = 0;
        int pesoInicial = tamanho + 1;
        for (int i = 0; i < tamanho; i++) {
            soma += (cpf.charAt(i) - '0') * (pesoInicial - i);
        }
        int resto = 11 - (soma % 11);
        return (resto >= 10) ? 0 : resto;
    }
}