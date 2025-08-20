package br.com.ada.t1431.desafios.pix.extra;


import br.com.ada.t1431.desafios.pix.extra.repository.ChavePixRepository;
import br.com.ada.t1431.desafios.pix.extra.repository.impl.ChavePixRepositoryEmArquivo;
import br.com.ada.t1431.desafios.pix.extra.service.GerenciadorChavesPix;
import br.com.ada.t1431.desafios.pix.extra.service.impl.GerenciadorChavesPixDefaultService;
import br.com.ada.t1431.desafios.pix.extra.view.View;
import br.com.ada.t1431.desafios.pix.extra.controller.Controller;

/**
 * Ponto de entrada principal para a aplicação de gerenciamento de chaves PIX via linha de comando (CLI).
 * <p>
 * Esta classe interpreta os argumentos da linha de comando, direciona para a operação correta (cadastrar, buscar, remover) e lida com as exceções, fornecendo feedback ao usuário.
 */
public class Main {
    private static final String DIRETORIO_DADOS = "chaves_pix_data";
    private final static ChavePixRepository repositorio = new ChavePixRepositoryEmArquivo(DIRETORIO_DADOS);
    private final static GerenciadorChavesPix gerenciador = new GerenciadorChavesPixDefaultService(repositorio);
    private final static View view = new View();
    private final static Controller controller = new Controller(gerenciador, view);

    /**
     * Método principal que inicia a aplicação.
     *
     * @param args os argumentos da linha de comando.
     */
    public static void main(String[] args) {
        controller.processar(args);
    }
}
