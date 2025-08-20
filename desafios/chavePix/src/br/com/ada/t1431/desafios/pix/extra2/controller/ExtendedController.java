package br.com.ada.t1431.desafios.pix.extra2.controller;

import br.com.ada.t1431.desafios.pix.extra.model.ChavePixComDadosBancarios;
import br.com.ada.t1431.desafios.pix.extra.model.exception.ChavePixExistenteException;
import br.com.ada.t1431.desafios.pix.extra.model.exception.ChavePixNaoExistenteException;
import br.com.ada.t1431.desafios.pix.extra.service.GerenciadorChavesPix;
import br.com.ada.t1431.desafios.pix.model.exception.ChavePixFormatoInvalidoException;
import br.com.ada.t1431.desafios.pix.extra.view.View;
import br.com.ada.t1431.desafios.pix.extra.controller.Controller;
import br.com.ada.t1431.desafios.pix.extra2.model.GeradorChaveAleatoria; 
import br.com.ada.t1431.desafios.pix.extra2.model.ChavePixComDadosBancariosFactory; 

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ExtendedController extends Controller {

    private final GeradorChaveAleatoria geradorChaveAleatoria;

    public ExtendedController(GerenciadorChavesPix gerenciador, View view, GeradorChaveAleatoria geradorChaveAleatoria) {
        super(gerenciador, view);
        this.geradorChaveAleatoria = geradorChaveAleatoria;
    }

    @Override
    protected void cadastrarChave(String[] args) throws ChavePixExistenteException, ChavePixFormatoInvalidoException, ChavePixNaoExistenteException {
        Map<String, String> params = parseArgs(args); 

        ChavePixComDadosBancarios chave = ChavePixComDadosBancariosFactory.getInstance(this.geradorChaveAleatoria).criarChavePixComDadosBancarios(
                params.get("-i"), 
                params.get("-a"), 
                params.get("-c"), 
                params.get("-t"), 
                params.get("-v")
        );

        gerenciador.save(chave);
        view.showMessage("Chave PIX '" + chave.getChavePix().getValor() + "' cadastrada com sucesso.");
}
}
