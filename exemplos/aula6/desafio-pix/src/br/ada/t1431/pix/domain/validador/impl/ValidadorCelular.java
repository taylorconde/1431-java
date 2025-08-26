package br.ada.t1431.pix.domain.validador.impl;
import br.ada.t1431.pix.domain.DDD;
import br.ada.t1431.pix.domain.exception.ChavePixInvalidaException;
import br.ada.t1431.pix.domain.validador.Validador;

public class ValidadorCelular implements Validador {
    @Override
    public void validar(String valor) throws ChavePixInvalidaException {
        String telefone = valor.replaceAll("\\D", "");

        if (!telefone.matches("\\d{11}")) {
            throw new ChavePixInvalidaException("Telefone deve ter 11 dígitos");
        }

        if (telefone.matches("(\\d)\\1{10}")) {
            throw new ChavePixInvalidaException("Telefone não pode ter todos os dígitos iguais");
        }

        if (!(telefone.substring(2,3).equals("9"))) {
            throw new ChavePixInvalidaException("Celular deve começar com 9");
        }

        if (!DDD.isValido(Integer.parseInt(telefone.substring(0, 2)))) {
            throw new ChavePixInvalidaException("DDD inválido");
        }

    }

}
