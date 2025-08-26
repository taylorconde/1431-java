package br.ada.t1431.pix.domain;


import br.ada.t1431.pix.domain.dadosBancarios.DadosBancarios;
import br.ada.t1431.pix.domain.exception.ChavePixInvalidaException;
import br.ada.t1431.pix.domain.validador.Validador;
import br.ada.t1431.pix.domain.validador.impl.*;

public class ChavePixFactory {

    public static ChavePix create(TipoDeChavePix tipo, String valor, DadosBancarios dadosBancarios) {
        Validador validador = switch (tipo) {
            case CPF -> new ValidadorCPF();
            case CNPJ -> new ValidadorCNPJ();
            case EMAIL -> new ValidadorEmail();
            case CELULAR -> new ValidadorCelular();
            case ALEATORIA -> new ValidadorAleatoria();
        };

        return new ChavePix(tipo, valor, dadosBancarios, validador);
    }

}
