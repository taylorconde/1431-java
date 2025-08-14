class Solution{
    public static void main(String args[]){
        String senha = args.length > 0 ? args[0] : "";

        boolean deveTerNoMinimoOitoCaracteres = senha.length() >= 8;
        boolean deveConterPeloMenosUmNumero = senha.matches(".*\\d.*");
        boolean deveConterPeloMenosUmaLetraMaiuscula = senha.matches(".*[A-Z].*");
        boolean deveConterPeloMenosUmaLetraMinuscula = senha.matches(".*[a-z].*");
        boolean deveConterPeloMenosUmCaractereEspecial = senha.matches(".*[^a-zA-Z0-9].*");

        boolean senhaValida = deveTerNoMinimoOitoCaracteres && deveConterPeloMenosUmaLetraMaiuscula && deveConterPeloMenosUmNumero && deveConterPeloMenosUmaLetraMinuscula && deveConterPeloMenosUmCaractereEspecial;

        if(senhaValida){
            System.out.println("Senha válida");
        }else{
            System.out.println("Senha inválida");
        }
    

    } 
}