# Expressões Mais Ricas

Quando um projeto de software está mais próximo de um "código" que de uma "linguagem" (de programação) é hora de se preocupar. Isso quer dizer que um código, seja em qual linguagem ou tecnologia for, não deve ser algo incompreensível, mas antes deve prezar pela comunicação - entre as pessoas envolvidas na manutenção e evolução. 

Dessa forma, as linguagens devem evoluir e devemos nos apropriar dos recursos que nos permitem comunicar com a flexibilidade e propriedade que precisamos. Vamos explorar dois novos recursos do Java que nos capacitam a tratar `switch` como expressão e o `pattern matching` para checagem e conversão mais simples.

> A tradução desses termos implica em perda de significado e sentido e atrapalhariam o estudo

## Switch expressions

> Esse tópico é melhor compreendido com o conhecimento de expressões lambda

A sintaxe tradicional de um bloco `switch` é baseada na avaliação de uma variável e os blocos para cada caso, contendo o controle `break` em cada, e o caso `default`. Vamos retomar o caso que trabalhamos no estudo de estruturas de condicional, 

```java
void main() {
    String tipoDiaSemana = switchDiaSemana();
    System.out.println(tipoDiaSemana);
}
static String switchDiaSemana() {
    String tipoDiaSemana;
    DayOfWeek hoje = LocalDate.now().getDayOfWeek();
    switch (hoje) {
        case MONDAY, TUESDAY, WEDNESDAY, FRIDAY, THURSDAY:
            tipoDiaSemana = "Dia de Semana";
            break;
        case SATURDAY, SUNDAY:
            tipoDiaSemana = "Fim de Semana";
            break;
    }
    return tipoDiaSemana;
}
```

O método não é muito legível e a variável `tipoDiaSemana` parece um pouco perdida. Vamos mudar para a sintaxe usando `switch expression` e comparar a legibilidade, 

```java
void main() {
    String tipoDiaSemana = switchDiaSemana();
    System.out.println(tipoDiaSemana);
}
static String switchDiaSemana() {
    DayOfWeek hoje = LocalDate.now().getDayOfWeek();
    return switch (hoje) {
        case MONDAY, TUESDAY, WEDNESDAY, FRIDAY, THURSDAY -> "Dia de Semana";
        case SATURDAY, SUNDAY -> "Fim de Semana";
    };
}
```

Perceba que `switch` passou a ser uma expressão e retorna um valor. Agora não precisamos mais da variável `tipoDiaSemana` que, claramente, existia para contornar uma limitação de expressividade e prejudicava a leitura e compreensão do método. 

A sintaxe geral do `switch expression` é 

```java
int result = switch (expression) {
    case constant1 -> value1;
    case constant2 -> value2;
    default -> defaultValue;
};
```

Voltando ao nosso projeto de escola. Considere que queremos aplicar um adicional ao salário baseado na `senioridade` do `InstrutorFixo`. 

```java
class Financeiro {
    //código da classe sem mais alterações
    public BigDecimal aplicarAdicional(Senioridade senioridade) {
        return switch (senioridade) {
            case L1 -> BigDecimal.valueOf(1.1);
            case L2 -> BigDecimal.valueOf(1.12);
            case L3 -> BigDecimal.valueOf(1.175);
        };
    }
}
```

Algumas particularidades que precisamos prestar atenção

1. O caso `default` é opcional
2. A variável de controle (`senioridade` no último exemplo) não pode ser `null`
3. Em caso de precisar de um bloco, o retorno usa a palavra-chave `yield`. [Mais detalhes](https://docs.oracle.com/en/java/javase/17/language/switch-expressions.html#:~:text=%22case%20L%3A%22%20Statements%20and%20the%20yield%20Statement)

## Pattern matching

"Pattern matching" envolve testar o tipo específico de um objeto e, então, realizar o "casting" para o subtipo específico. 

Relembrando o método `realizarPagamento` em `Financeiro` e aplicando o "pattern matching"

```java
class Financeiro {
    public void realizarPagamento(Pagavel pagavel) {
        if (pagavel instanceof Instrutor instrutor) {
            System.out.println(instrutor.resumoContrato());
        }
        System.out.println(pagavel.gerarRecibo());
    }
}
```

Observe que a declaração da variável `instrutor` e o "casting" já são feitos junto ao teste com `instance of`, poupando de operações que apenas tornam a programação prolixa.

Além disso, também é possível utilizar "pattern matching" com `switch` e em qualquer situação que segue o padrão "testar com `instance of` e converter com casting".

Considere o exemplo hipotético, apenas para fins didáticos

```java
public static double getPerimeter(Shape shape) throws IllegalArgumentException {
    return switch (shape) {
        case Rectangle r -> 2 * r.length() + 2 * r.width();
        case Circle c    -> 2 * c.radius() * Math.PI;
        default          -> throw new IllegalArgumentException("Unrecognized shape");
    };
}
```

## Record pattern

Outra feature, ainda mais recente, e com semelhanças ao "pattern matching" é o "record pattern". Quando testar se um objeto é do subtipo de um `record` existente, você pode acessar os atributos do `record` como se fossem variáveis locais. 

```java
record Point(double x, double y) {}

static void printAngleFromXAxis(Object obj) {
    if (obj instanceof Point(double x, double y)) {
        System.out.println(Math.toDegrees(Math.atan2(y, x)));
    }
}     
```

Note que as variáveis `x` e `y` referenciadas no `println` são os atributos definidos em `Point`. 

> Existem outras opções de uso dos "record patterns". Consulte a [documentação](https://docs.oracle.com/en/java/javase/20/language/record-patterns.html) para mais detalhes.


## Estudo de caso

Finalização do projeto: Uso de `switch expressions` para categorizar o estado dos empréstimos e aplicar "pattern matching" ao processar diferentes tipos de itens (`Livro`, `Revista`) no sistema. Integração desses recursos ao sistema, mantendo a coesão e baixo acoplamento.

## Desafio

Implementar melhorias no sistema, usando `switch expressions` para lidar com diferentes estados de empréstimo e aplicar pattern matching para processar operações específicas de cada tipo de item.
