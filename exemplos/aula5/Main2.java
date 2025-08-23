class Main2{

    public static void main(String[] args) {
        
        double parametro1 = Double.valueOf(args[0]);
        String operacao = args[1];
        double parametro2 = Double.valueOf(args[2]);

        if(operacao.equalsIgnoreCase("soma")){
            System.out.println(parametro1 + parametro2);
        }
        
        else if(operacao.equalsIgnoreCase("subtrai")){
            System.out.println(parametro1 - parametro2);
        }

        else if(operacao.equalsIgnoreCase("multiplica")){
            System.out.println(parametro1 * parametro2);
        }

        else if(operacao.equalsIgnoreCase("divide")){
            if (parametro2 != 0) {
                System.out.println(parametro1 / parametro2);
            } 
            
            else {
                System.out.println("Divisão por zero não é permitida.");
            }
        }

        else{
            System.out.println("Operação inválida");
        }


    }

}