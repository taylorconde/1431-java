class MeuPrimeiroPrograma{
    public static void main(String[] args){

        int quantidadeDeParametros = args.length;

        if(quantidadeDeParametros < 1){
            System.out.println("Informe pelo menos um nome");
            return; // encerra o programa
        }

        String nome = "";
        
        // enhanced for (for aprimorado)
        for(String s : args){
            nome = nome + s + " ";
        }

        nome = nome.trim(); // remove espaços ao fim do texto

        System.out.println("Olá " + nome + "!");
        System.out.println("Seu nome tem " + nome.length() + " letras");

    }
}