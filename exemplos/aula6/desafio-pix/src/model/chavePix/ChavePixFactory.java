package model.chavePix;

import model.dadosBancarios.DadosBancarios;
import model.validador.*;
import model.validador.impl.*;

public class ChavePixFactory {

    public static ChavePix create(TipoDeChavePix tipo, String valor, DadosBancarios dadosBancarios) {

        Validador validador =  switch (tipo) {
            case CPF -> new ValidadorCPF();
            case CNPJ -> new ValidadorCNPJ();
            case EMAIL -> new ValidadorEmail();
            case CELULAR -> new ValidadorCelular();
            case ALEATORIA -> new ValidadorAleatoria();
        };

        return new ChavePix(tipo, valor, dadosBancarios, validador);

    }
}
