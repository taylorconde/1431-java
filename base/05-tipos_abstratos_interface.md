# Tipos Abstratos e Interfaces

Anteriormente, definimos que os pilares da orientação a objetos são quatro, e já falamos sobre três deles: abstração, encapsulamento e herança. Nessa aula, vamos abordar o polimorfismo. 

A abstração, às vezes, pode ser confundida com os tipos abstratos. Podemos definir uma classe como abstrata, no sentido que não é possível representar instâncias diretas daquela classe. Em Java, isso implica que até pode haver um construtor, mas apenas para ser herdado por seus subtipos e não para ser invocado diretamente usando `new`. 

## Classes abstratas

Considerando o exemplo anterior, da escola, podemos definir que os instrutores serão sempre prestadores de serviço ou do quadro de funcionários. Vamos chamar de `InstrutorTemporario` e `InstrutorFixo`, respectivamente. 

```java
abstract class Instrutor extends Pessoa {
    private final String area;
    protected Instrutor(String nome, String area) {
        super(nome);
        this.area = area;
    }
}
class InstrutorTemporario extends Instrutor {
    private final LocalDate dataInicio;
    private final LocalDate dataFim;
    public InstrutorTemporario(String nome, String area, LocalDate dataInicio, LocalDate dataFim) {
        super(nome, area);
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
}
class InstrutorFixo extends Instrutor {
    private final LocalDate dataInicio;
    private final String senioridade;
    public InstrutorFixo(String nome, String area, LocalDate dataInicio, String senioridade) {
        super(nome, area);
        this.dataInicio = dataInicio;
        this.senioridade = senioridade;
    }
}
```

Acrescentamos a palavra-chave `abstract` na classe `Instrutor` e modificamos a visibilidade do construtor para `protected`. Também criamos a classe `InstrutorFixo` com os atributos `dataInicio` e `senioridade`, indicando que existe uma progressão de carreira. Dessa forma, não podemos criar instâncias diretas de `Instrutor`, mas apenas dos seus subtipos. 

Usar classes abstratas permite definir comportamentos comuns no tipo, no entanto, restringindo que os objetos devem ser sempre específicos. Ou seja, é uma abordagem fortemente centrada em evitar repetição de código.

### Métodos abstratos

É possível definir apenas a assinatura de um método sem especificar sua implementação ou os detalhes de seu comportamento. Chamamos esse método de "abstrato" e sua sintaxe é 

```java
abstract class Instrutor extends Pessoa {
    abstract String resumoInstrutor();
    //resto do código da classe
}
```

> Se há pelo menos um método abstrato, então a classe DEVE ser abstrata.

Métodos abstratos são partes fundamentais na definição de contratos de API. Logo, uma classe abstrata define que realiza um comportamento, porém esse comportamento (sua implementação) pode variar de acordo com cada subtipo. Veja que os subtipos `InstrutorTemporario` e `TemporarioFixo` definem atributos próprios, então o `resumoInstrutor` do primeiro poderia focar no período do contrato enquanto o outro poderia ser a sua senioridade e tempo de vínculo.

**Atenção!** Quando um tipo define um método abstrato todos os seus subtipos são **OBRIGADOS** a implementá-los.

## Interfaces

Uma interface define um contrato de API, onde existe um comportamento esperado, mas sem conhecer os detalhes do seu funcionamento. Essa é a definição estabelecida na POO e que acabou causando certo conflito e estranheza nas últimas versões do Java. 

Antigamente, as interfaces em Java definiam apenas assinaturas de métodos. Em outras palavras, uma interface era uma classe abstrata onde todos os seus métodos são abstratos. No entanto, no Java 8 surgiu a funcionalidade "métodos default", que são métodos não-abstratos e possuem implementação. O objetivo dessa funcionalidade era permitir um comportamento padrão para uma funcionalidade do contrato para não quebrar a compatibilidade com os subtipos já existentes. 

```java
interface Pagavel {
    double calcularSalario();
    default String gerarRecibo() {
        return "Recibo gerado para o valor de: " + calcularSalario();
    }
}
abstract class Instrutor extends Pessoa implements Pagavel {
    private final BigDecimal salario;
    public BigDecimal getSalario() { return salario; }
    //TODO resto da classe
}
class InstrutorTemporario extends Instrutor {
    //TODO resto da classe
    @Override
    public double calcularSalario() {
        return this.getSalario().multiply(BigDecimal.valueOf(1.3)).doubleValue();
    }
}
```

Definimos uma interface `Pagavel` com os métodos `calcularSalario` e `gerarRecibo`, onde este último possui uma definição padrão de implementação.

> Métodos `default` são um recurso da linguagem Java e não têm relação com o fundamento "interface" da POO.

Java permite que uma classe implemente mais de uma interface por vez, separadas por vírgulas. Alguns engenheiros chamam esse comportamento de **"herança múltipla"**, mas isso pode causar confusão com o conceito de herança e o uso da palavra-chave `extends`, então, sendo controverso, vale o conhecimento, mas é prudente não repetir esse discurso. 

## Polimorfismo

O último fundamento da POO é o polimorfismo, que define que uma classe pode ser representada de várias formas, explorando os tipos dos quais ela for subtipo. Exceto `Object`, todos os tipos em Java possuem duas ou mais representações. 

Poderíamos definir uma coleção ou array com todos os subtipos de instrutores representados pelo seu tipo comum `Instrutor`, ou ainda todos os instrutores e alunos representados como `Pessoa`. Quando aplicamos o polimorfismo ficam visíveis apenas os comportamentos e atributos definidos no tipo da variável.

```java
void main() {
    Pessoa aluno = new Aluno();
    aluno.getMatricula();// não está acessível e causa erro de compilação
    Instrutor instrutor = new InstrutorTemporario();
    instrutor.getSalario();//funciona porque está definido em Instrutor
    instrutor.getDataInicio();// não está acessível e causa erro de compilação
}
```

### Baixo acoplamento

O polimorfismo é sempre recomendável quando lidando com interfaces, classes abstratas e herança para promover o **baixo acoplamento** no projeto - também chamado de "desacoplamento" e "dependência fraca". 

Considere o conceito de interface, no sentido amplo. Pensando numa interface de conexão de vídeo no padrão HDMI, temos muitos dispositivos de saída de imagem e muitos outros de entrada de vídeo. Todos os que são compatíveis com o padrão HDMI são compatíveis entre si, formando um par entrada e saída. Dessa forma, não é necessário se restringir a fabricante ou modelo específico, pois qualquer "implementação" do padrão deve funcionar bem.

Da mesma forma, devemos sempre buscar nos apoiar nas interfaces definidas, permitindo liberdade e generalização na construção do projeto de software.

### Checagem de tipo

Em cenários de polimorfismo pode ser necessário checar o tipo específico de uma variável. Neste caso, em Java, usamos a palavra-chave `instance of`.

```java
class Financeiro {
    public void realizarPagamento(Pagavel pagavel) {
        if (pagavel instanceof Instrutor) {
            Instrutor instrutor = (Instrutor) pagavel;
            System.out.println(instrutor.resumoContrato());
        }
        System.out.println(pagavel.gerarRecibo());
    }
}
```

Fazendo uma analogia, quando usamos polimorfismo estamos envelopando uma variável com um tipo diferente, que não a descreve totalmente. Nessa forma, apenas os comportamentos e características do tipo (do envelope) estão visíveis. Para desenvelopar e acessar as especificidades, precisamos realizar o "cast" para o subtipo específico. A única forma de fazer isso com segurança é antes checando com `instance of`. 

## Enumerações

Existem situações onde queremos restringir as possíveis instâncias ou valores para uma determinada classe. 

Considere o atributo `senioridade` que definimos em `InstrutorFixo`. Deixar como campo aberto de texto pode permitir ocorrência de inconsistências ou valores que não condizem com a realidade da escola. Vamos considerar três níveis definidos como L1, L2 e L3. 

Podemos resolver esse problema usando enumerações ("enumerations" ou "enums"). A sintaxe mais simples é 

```java
enum Senioridade {
    L1, L2, L3
}
```

Isso pode parecer muito estranho! Mas se prestarmos mais atenção, vamos perceber que esses valores são todas as instâncias possíveis de uma classe chamada `Senioridade`. Isso fica mais evidente se definirmos atributos para a enum. 

```java
enum Senioridade {
    L1(""), L2(""), L3("");
    private final String descricao;
    Senioridade(String descricao) { this.descricao = descricao; }
    public String getDescricao() { return descricao; }
}
```

Observe que agora temos algo mais parecido com uma classe, que contém o atributo imutável `descricao` e o seu método de acesso. O construtor é privado, portanto só está acessível internamente. Logo, é como se `L1`, `L2` e `L3` fossem constantes públicas e estáticas que contém as únicas instâncias possíveis de `Senioridade`.

> Agora que você sabe disso, use as enums com muito mais domínio para resolver diversos problemas. 

## Estudo de caso

Evolução do projeto: Implementação de uma interface `Emprestavel` para definir comportamentos comuns em diferentes itens da biblioteca, como livros e revistas. Criação de classes concretas (`Livro`, `Revista`) que implementam essa interface, demonstrando o uso de abstrações para reduzir o acoplamento.

## Desafio

Adicionar uma interface `Catalogavel` para itens que podem ser catalogados na biblioteca, e implementar métodos que permitam a busca e filtragem de itens usando polimorfismo e baixo acoplamento.
