package br.com.ada.t1431.desafios.pix.extra.model;

import br.com.ada.t1431.desafios.pix.model.ChavePix;
import br.com.ada.t1431.desafios.pix.model.TipoChave;

import java.util.stream.Stream;

/**
 * Representa uma chave PIX com dados bancários associados.
 * Esta classe é imutável e encapsula um objeto {@link ChavePix} e um {@link DadosBancarios}.
 * A construção é feita através do padrão Builder.
 */
public final class ChavePixComDadosBancarios {

    private final ChavePix chavePix;
    private final DadosBancarios dadosBancarios;

    private ChavePixComDadosBancarios(ChavePix chavePix, DadosBancarios dadosBancarios) {
        this.chavePix = chavePix;
        this.dadosBancarios = dadosBancarios;
    }

    /**
     * @return o objeto {@link ChavePix} encapsulado.
     */
    public ChavePix getChavePix() {
        return chavePix;
    }

    /**
     * @return o objeto {@link DadosBancarios} associado.
     */
    public DadosBancarios getDadosBancarios() {
        return dadosBancarios;
    }

    /**
     * Retorna uma nova instância do Builder para construir um objeto {@link ChavePixComDadosBancarios}.
     *
     * @return uma nova instância de {@link Builder}.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Classe Builder para a construção de objetos {@link ChavePixComDadosBancarios}.
     * Garante que o objeto seja criado em um estado válido.
     */
    public static final class Builder {
        private String codigoInstituicao;
        private String agencia;
        private String conta;
        private String tipoChave;
        private String valorChave;

        /**
         * Define o código da instituição financeira.
         * @param codigoInstituicao o código da instituição.
         * @return a própria instância do Builder para encadeamento.
         */
        public Builder codigoInstituicao(String codigoInstituicao) {
            this.codigoInstituicao = codigoInstituicao;
            return this;
        }

        /**
         * Define o número da agência.
         * @param agencia o número da agência.
         * @return a própria instância do Builder para encadeamento.
         */
        public Builder agencia(String agencia) {
            this.agencia = agencia;
            return this;
        }

        /**
         * Define o número da conta.
         * @param conta o número da conta.
         * @return a própria instância do Builder para encadeamento.
         */
        public Builder conta(String conta) {
            this.conta = conta;
            return this;
        }

        /**
         * Define o tipo da chave PIX.
         * @param tipoChave o tipo da chave (ex: "CPF", "EMAIL").
         * @return a própria instância do Builder para encadeamento.
         */
        public Builder tipoChave(String tipoChave) {
            this.tipoChave = tipoChave;
            return this;
        }

        /**
         * Define o valor da chave PIX.
         * @param valorChave o valor da chave.
         * @return a própria instância do Builder para encadeamento.
         */
        public Builder valorChave(String valorChave) {
            this.valorChave = valorChave;
            return this;
        }

        /**
         * Constrói e retorna um objeto {@link ChavePixComDadosBancarios} imutável.
         * Lança uma {@link IllegalStateException} se algum campo obrigatório não for preenchido.
         *
         * @return uma nova instância de {@link ChavePixComDadosBancarios}.
         */
        public ChavePixComDadosBancarios build() {
            validarCampos();

            ChavePix chavePix = ChavePix.of(this.tipoChave, this.valorChave);
            DadosBancarios dadosBancarios = new DadosBancarios(this.codigoInstituicao, this.agencia, this.conta);
            return new ChavePixComDadosBancarios(chavePix, dadosBancarios);
        }

        private void validarCampos() {
            validarCampoObrigatorio(codigoInstituicao, "código da instituição");
            validarCampoObrigatorio(agencia, "agência");
            validarCampoObrigatorio(conta, "conta");
            validarCampoObrigatorio(tipoChave, "tipo da chave");
            validarCampoObrigatorio(valorChave, "valor da chave");
        }

        private void validarCampoObrigatorio(String valor, String nomeCampo) {
            if (valor == null || valor.isBlank()) {
                throw new IllegalStateException("O campo '" + nomeCampo + "' é obrigatório.");
            }
        }
    }
}