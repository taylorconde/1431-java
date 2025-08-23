// Crie um programa java que represente uma calculadora simples
// que possa realizar as quatro operações básicas: adição, subtração, multiplicação e
// divisão. O programa deve solicitar ao usuário dois números e a operação desejada,
// e exibir o resultado da operação.

package exemplos.aula5.calculadora;

public class CalculadoraBasica{    

    // Estado
    // sintaxe => tipoDoDado nomeDoCampo;
    private double numero1;
    private double numero2;
    


    // modificador de acesso, qual usar aqui?   
    // public, private, protected
    // public: acessível de qualquer lugar
    // private: acessível apenas dentro da classe
    // protected: acessível dentro da classe e subclasses
    public CalculadoraBasica(double n1, double n2){
        numero1 = n1;
        numero2 = n2;
    }

    CalculadoraBasica(String n1, String n2){
        converterNumerosParaDouble(n1, n2);
    }

    private void converterNumerosParaDouble(String n1, String n2){
        numero1 = Double.valueOf(n1);
        numero2 = Double.valueOf(n2);
    }

    CalculadoraBasica(){

    }

    // Comportamento => Metodos
    // tipoDeRetorno nomeDoMetodo parametros(opcional)
    public double soma(){
        return numero1 + numero2;
    }

    public double subtrai(){
        return numero1 - numero2;
    }

    public double multiplica(){
        return numero1 * numero2;
    }

    public double divide(){
        return numero1 / numero2;
    }
}