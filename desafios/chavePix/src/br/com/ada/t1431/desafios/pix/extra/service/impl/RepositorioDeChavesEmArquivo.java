package br.com.ada.t1431.desafios.pix.extra.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class RepositorioDeChavesEmArquivo {
    private static final String NOME_ARQUIVO = ".chaves";
    private final Path arquivoChaves;

    RepositorioDeChavesEmArquivo(String diretorio) {
        Path pathDiretorio = Paths.get(diretorio);
        try {
            Files.createDirectories(pathDiretorio);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível criar o diretório para o arquivo de chaves.", e);
        }
        this.arquivoChaves = pathDiretorio.resolve(NOME_ARQUIVO);
    }

    List<String> lerLinhas() {
        if (!Files.exists(arquivoChaves)) {
            return new ArrayList<>();
        }
        try (Stream<String> lines = Files.lines(arquivoChaves)) {
            return lines.collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo de chaves.", e);
        }
    }

    void escreverLinhas(List<String> linhas) {
        try {
            Files.write(arquivoChaves, linhas);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar o arquivo de chaves.", e);
        }
    }
}