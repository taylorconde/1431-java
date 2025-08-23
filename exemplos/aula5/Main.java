package exemplos.aula5;

import exemplos.aula5.calculadora.CalculadoraBasica;

public class Main{

    public static void main(String[] args){

        String parametro1 = args[0];
        String operacao = args[1];
        String parametro2 = args[2];

        double parametro1ConvertidoDeStringParaDouble = Double.valueOf(parametro1);
        double parametro2ConvertidoDeStringParaDouble = Double.valueOf(parametro2);

        CalculadoraBasica calculadora = new CalculadoraBasica(parametro1ConvertidoDeStringParaDouble, parametro2ConvertidoDeStringParaDouble);

        if(operacao.equalsIgnoreCase("soma")){
            System.out.println(calculadora.soma());
        }
        
        else if(operacao.equalsIgnoreCase("subtrai")){
            System.out.println(calculadora.subtrai());
        }

        else if(operacao.equalsIgnoreCase("multiplica")){
            System.out.println(calculadora.multiplica());
        }

        else if(operacao.equalsIgnoreCase("divide")){
            System.out.println(calculadora.divide());
        }

        else{
            System.out.println("Operação inválida");
        }

    }

}