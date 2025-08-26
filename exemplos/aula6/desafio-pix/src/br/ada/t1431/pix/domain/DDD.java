package br.ada.t1431.pix.domain;

public enum DDD {
    AC(68), AL(82), AP(96), AM(92), BA(71), CE(85), DF(61),
    ES(27), GO(62), MA(98), MT(65), MS(67), MG(31), PA(91),
    PB(83), PR(41), PE(81), PI(86), RJ(21), RN(84), RS(51),
    RO(69), RR(95), SC(48), SP(11), SE(79), TO(63);

    private final int codigo;

    DDD(int codigo) {
        this.codigo = codigo;
    };

    public int getCodigo() {
        return codigo;
    };

    // Método para verificar se um código é válido
    public static boolean isValido(int codigo) {
        for (DDD d : DDD.values()) {
            if (d.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }
}