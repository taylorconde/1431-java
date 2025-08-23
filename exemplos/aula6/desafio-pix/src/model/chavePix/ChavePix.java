package model.chavePix;

import model.dadosBancarios.DadosBancarios;
import model.exception.ChavePixInvalidaException;
import model.validador.Validador;

// 4 principios da POO
// Abstração, Encapsulamento, Herança, Polimorfismo
public class ChavePix {

    TipoDeChavePix tipo;
    String valor;
    DadosBancarios dadosBancarios;
    Validador validador;

    ChavePix(TipoDeChavePix tipo, String valor, DadosBancarios dadosBancarios, Validador validador) {
        this.tipo = tipo;
        this.valor = valor;
        this.dadosBancarios = dadosBancarios;
        this.validador = validador;
    }

    void validar(){
        validador.validar(this.valor);
    }

}
