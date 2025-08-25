package br.ada.t1431.pix.app.repository;

import br.ada.t1431.pix.domain.ChavePix;
import br.ada.t1431.pix.domain.ChavePixRepository;
import br.ada.t1431.pix.domain.TipoDeChavePix;

public class ArquivoLocalRepository implements ChavePixRepository {

    private String diretorio;

    public ArquivoLocalRepository(String diretorio) {
        this.diretorio = diretorio;
    }

    @Override
    public ChavePix save(ChavePix chavePix) {
        return null;
    }

    @Override
    public void delete(TipoDeChavePix tipo, String valor) {

    }

    @Override
    public ChavePix find(TipoDeChavePix tipo, String valor) {
        return null;
    }
}
