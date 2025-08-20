package br.com.ada.t1431.desafios.pix.model;

import java.util.Arrays;

/**
 * Enum que representa os tipos de chave PIX suportados.
 */
public enum TipoChave {
    CPF,
    CNPJ,
    EMAIL,
    CELULAR,
    ALEATORIA;    

    /**
     * Converte uma string para o enum ChavePix correspondente, ignorando o caso.
     *
     * @param tipoChave A string que representa o tipo da chave (ex: "cpf", "EMAIL").
     * @return O enum ChavePix correspondente.
     * @throws IllegalArgumentException se a string não corresponder a nenhum tipo de chave válido.
     */
    public static TipoChave fromString(String tipoChave) {
        return Arrays.stream(values())
                .filter(key -> key.name().equalsIgnoreCase(tipoChave))
                .findFirst()
                .orElseThrow(() -> createIllegalArgumentException(tipoChave));
    }

    private static IllegalArgumentException createIllegalArgumentException(String tipoChave) {
        String tiposValidos = Arrays.toString(values());
        String mensagem = String.format("Tipo de chave PIX inválido: '%s'. Os tipos válidos são: %s", tipoChave, tiposValidos);
        return new IllegalArgumentException(mensagem);
    }
}
