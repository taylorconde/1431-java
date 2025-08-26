package br.ada.t1431.pix.app.service;

import br.ada.t1431.pix.domain.ChavePix;
import br.ada.t1431.pix.domain.ChavePixFactory;
import br.ada.t1431.pix.domain.ChavePixRepository;
import br.ada.t1431.pix.domain.TipoDeChavePix;
import br.ada.t1431.pix.domain.dadosBancarios.DadosBancarios;
import br.ada.t1431.pix.domain.dadosBancarios.TipoDeContaBancaria;

public class GerenciarChavePix {

    ChavePixRepository repository;

    public GerenciarChavePix(ChavePixRepository repository){
        this.repository = repository;
    }

    public ChavePix salvar(String valor, String tipoDaChave, String instituicao, String agencia, String conta, String tipoDeConta){

        DadosBancarios dadosBancarios = new DadosBancarios(instituicao, agencia, conta, TipoDeContaBancaria.valueOf(tipoDeConta.toUpperCase()));

        ChavePix chavePix = ChavePixFactory.create(TipoDeChavePix.valueOf(tipoDaChave.toUpperCase()), valor, dadosBancarios);

        return repository.save(chavePix);
    }


}
