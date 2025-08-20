package br.com.ada.t1431.desafios.pix.model;

import br.com.ada.t1431.desafios.pix.model.exception.ChavePixFormatoInvalidoException;
import br.com.ada.t1431.desafios.pix.model.validador.ValidadorChavePix;
import br.com.ada.t1431.desafios.pix.model.validador.ValidadorChavePixFactory;

import java.util.Objects;

/**
 * Representa uma chave pix, contendo seu tipo e valor.
 * Esta classe é imutável e pode validar a si mesma.
 * A lógica de validação é determinada no momento da construção com base no tipo da chave.
 */
public final class ChavePix {
    private final TipoChave tipoChave;
    private final String valor;
    private ValidadorChavePix validador;

    private ChavePix(TipoChave tipoChave, String valor) {
        this.tipoChave = tipoChave;
        this.valor = valor;
        if(tipoChave!=null){
            this.validador = ValidadorChavePixFactory.getValidador(tipoChave);
        }
    }

    public TipoChave getTipoChave() {
        return tipoChave;
    }

    public String getValor() {
        return valor;
    }

    /**
     * Cria uma instância de ChavePix a partir de um tipo e valor.
     * Este é o método de fábrica preferencial para criar objetos ChavePix.
     *
     * @param tipoChave O tipo da chave.
     * @param valor O valor da chave.
     * @return uma nova instância de ChavePix.
     */
    public static ChavePix of(TipoChave tipoChave, String valor) {
        return new ChavePix(tipoChave, valor);
    }

    /**
     * Cria uma instância de ChavePix a partir de uma string de tipo e um valor.
     * Converte a string do tipo para o enum {@link TipoChave} correspondente.
     *
     * @param tipoChaveStr A representação em string do tipo da chave (ex: "cpf").
     * @param valor O valor da chave.
     * @return uma nova instância de ChavePix.
     */
    public static ChavePix of(String tipoChaveStr, String valor) {
        TipoChave tipoChave = TipoChave.fromString(tipoChaveStr);
        return new ChavePix(tipoChave, valor);
    }

    /**
     * Valida o valor da chave PIX de acordo com seu tipo, lançando uma exceção em caso de falha.
     * Utiliza o validador injetado durante a construção do objeto.
     *
     * @throws ChavePixFormatoInvalidoException se a chave for inválida.
     */
    public void validar() throws ChavePixFormatoInvalidoException {
        this.validador.validar(this.valor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChavePix chavePix = (ChavePix) o;
        return tipoChave == chavePix.tipoChave && Objects.equals(valor, chavePix.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoChave, valor);
    }
}
