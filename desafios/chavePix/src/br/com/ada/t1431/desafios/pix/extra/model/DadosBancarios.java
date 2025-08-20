package br.com.ada.t1431.desafios.pix.extra.model;

/**
 * Representa os dados bancários associados a uma chave PIX.
 * É um record imutável para carregar dados de forma simples e segura.
 */
public record DadosBancarios(String codigoInstituicao, String agencia, String conta) {

}