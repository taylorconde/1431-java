package br.ada.t1431.pix.domain;

public interface ChavePixRepository {
    ChavePix save(ChavePix chavePix);
    void delete(TipoDeChavePix tipo, String valor);
    ChavePix find(TipoDeChavePix tipo, String valor);
}