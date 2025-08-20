package br.com.ada.t1431.desafios.pix.extra2.model;

import br.com.ada.t1431.desafios.pix.extra2.service.GeradorChaveAleatoria;
import br.com.ada.t1431.desafios.pix.model.TipoChave;
import br.com.ada.t1431.desafios.pix.extra.model.ChavePixComDadosBancarios;

/**
 * Fábrica responsável por criar instâncias de {@link ChavePixComDadosBancarios}.
 * <p>
 * Esta classe abstrai a lógica de construção de uma {@code ChavePixComDadosBancarios},
 * especialmente para lidar com a criação de chaves do tipo {@link TipoChave#ALEATORIA}.
 * Ela utiliza o padrão Singleton para garantir que apenas uma instância da fábrica seja usada.
 */
public class ChavePixComDadosBancariosFactory {

    private final GeradorChaveAleatoria geradorChaveAleatoria;
    private static ChavePixComDadosBancariosFactory instance;

    private ChavePixComDadosBancariosFactory(GeradorChaveAleatoria geradorChaveAleatoria) {
        this.geradorChaveAleatoria = geradorChaveAleatoria;
    }

    /**
     * Obtém a instância única da fábrica (Singleton).
     *
     * @param geradorChaveAleatoria o gerador a ser usado para chaves aleatórias.
     * @return uma instância de {@code ChavePixComDadosBancariosFactory}.
     */
    public static ChavePixComDadosBancariosFactory getInstance(GeradorChaveAleatoria geradorChaveAleatoria) {
        if (instance == null) {
            instance = new ChavePixComDadosBancariosFactory(geradorChaveAleatoria);
        }
        return instance;
    }

    /**
     * Cria uma instância de {@link ChavePixComDadosBancarios} com base nos parâmetros fornecidos.
     * <p>
     * Se o tipo for {@link TipoChave#ALEATORIA} e o valor da chave for nulo ou vazio,
     * um novo valor de chave aleatória será gerado automaticamente.
     *
     * @param codigoInstituicao Código da instituição financeira.
     * @param agencia           Número da agência.
     * @param conta             Número da conta.
     * @param tipoChaveStr      O tipo da chave como String (ex: "CPF", "ALEATORIA").
     * @param valorChave        O valor da chave (pode ser nulo para chaves aleatórias).
     * @return uma nova instância de {@link ChavePixComDadosBancarios}.
     */
    public ChavePixComDadosBancarios criarChavePixComDadosBancarios(String codigoInstituicao, String agencia, String conta, String tipoChaveStr, String valorChave) {
        ChavePixComDadosBancarios.Builder builder = ChavePixComDadosBancarios.builder()
                .codigoInstituicao(codigoInstituicao)
                .agencia(agencia)
                .conta(conta)
                .tipoChave(tipoChaveStr);

        if (TipoChave.ALEATORIA.name().equalsIgnoreCase(tipoChaveStr) && (valorChave == null || valorChave.isBlank())) {
            builder.valorChave(geradorChaveAleatoria.gerarChaveAleatoria());
        } else {
            builder.valorChave(valorChave);
        }

        return builder.build();
    }
}