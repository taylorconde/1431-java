package br.ada.t1431.pix.domain.dadosBancarios;

public record DadosBancarios(
        String agencia,
        String conta,
        String codigoBanco,
        TipoDeContaBancaria tipo
){

}
