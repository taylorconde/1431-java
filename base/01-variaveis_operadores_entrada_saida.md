# Uma Breve Introdução ao Java

Este é um módulo direto ao ponto, para quem já conhece Java e está revendo os conceitos fundamentais ou buscando atualização, ou aqueles que já programam em outras linguagens e estão chegando por aqui. Dito isso, vamos mergulhar rápido e sem muitas voltas numa abordagem ao Java moderno. 

Java é uma linguagem de programação fortemente tipada, orientada a objetos e multiplataforma criada em 1995. Ao longo de seus quase 30 anos, sempre esteve entre as linguagens de programação mais populares do mundo e uma das queridinhas no meio financeiro. No início do projeto, os objetivos eram criar uma linguagem que trabalha em parceria com os engenheiros. "Escreva uma vez e rode em qualquer lugar" era o slogan. O código Java é compilado em bytecode, uma linguagem intermediária interpretada em um ambiente virtual, a JVM (Java Virtual Machine), e esta específica de arquitetura e sistema operacional. Quase 30 anos depois, também temos a GraalVM, que permite compilação em código nativo e executa bem mais rápido. 

Nesse primeiro encontro, ou re-encontro, vamos preparar o ambiente de programação e trabalhar as variáveis, entrada e saída do sistema e formatação de texto. 

## Preparando o ambiente

Baixe e instale o JDK de sua preferência (pode ser o mais atual ou a LTS) direto do [site oficial](https://www.oracle.com/java/technologies/downloads/). Escolha seu SO e arquitetura e siga o passo-a-passo. Além disso, recomendamos usar uma IDE para escrever seus códigos e executar de maneira fácil e prática. As sugestões são [IntelliJ IDEA](https://www.jetbrains.com/idea/download/), [VS Code](https://code.visualstudio.com/docs/java/java-tutorial) e [Eclipse IDE](https://www.eclipse.org/downloads/).

> Os exemplos aqui estão com o IntelliJ IDEA e o JDK 21 (LTS).

Para usar o JDK no terminal ou para melhor integração das ferramentas, você deve fazer as seguintes manipulações das variáveis de ambiente
- Criar a variável `$JAVA_HOME` apontando para o diretório raiz de instalação do JDK; 
- Adicionar na `$PATH` o `$JAVA_HOME\bin` para reconhecer as ferramentas da JDK. 

Teste abrindo um terminal e digitando o comando 

```sh
java -version
```

A saída deve ser algo similar a 

> openjdk version "21" 2023-09-19<br>
> OpenJDK Runtime Environment (build 21+35-2513)<br>
> OpenJDK 64-Bit Server VM (build 21+35-2513, mixed mode, sharing)

## O tradicional "Hello World"

A forma mais tradicional da primeira linha de código, em Java, é 

```java
public class PrimeiroPrograma {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```

A primeira linha é a definição da classe - voltaremos nisso em POO. A segunda linha é a definição do método de entrada - também explicaremos mais sobre os métodos. A linha mais importante é a linha 3, a que executa a ação do primeiro programa.

Você pode copiar esse código num arquivo bloco de notas (ou qualquer ferramenta de texto sem formatação) e salvar com o nome "PrimeiroPrograma.java". Aqui o nome deve, obrigatoriamente, ser igual ao que está definido como nome da classe. Se quiser executar pelo terminal, você precisa navegar até o arquivo, compilar e executar. 

```sh
> javac PrimeiroPrograma.java
> java PrimeiroPrograma
```

> No JDK 21 já é possível usar direto o comando `java PrimeiroPrograma` e a ferramenta `java` chama internamente o compilador. 

Se você estiver no IntelliJ, tudo será mais simples. Crie um projeto Java marque a opção "Add sample code". 

![01-create-project](https://ada-lms-prd.s3.sa-east-1.amazonaws.com/module-templates/49df56c9-6aad-43a8-a79d-0c01647c6663/media/8d501da4e9c141329f560ac99062c176/86930bee-2053-4ccd-967c-47cb88c84ad7.png)

Para executar, use o botão verde ao lado da linha 3, como mostra a figura.

![02-run](https://ada-lms-prd.s3.sa-east-1.amazonaws.com/module-templates/49df56c9-6aad-43a8-a79d-0c01647c6663/media/d4f54c16911c4425b6ff0447cb72f8a9/d5599f88-19f1-4997-9f8f-e6bc854bcc7e.png)

### Formas alternativas

#### 1. JShell

A forma mais simples de fazer o seu primeiro código é usando a ferramenta JShell. Ela já vem no JDK e você pode acessá-la abrindo um terminal e digitando `jshell`. A saída deve ser similar a 

> |  Welcome to JShell -- Version 21<br>
> |  For an introduction type: /help intro

Aqui você já pode escrever seus comandos Java. Qualquer coisa que você queira testar. Vamos seguir com o código do "Hello World" com 

```sh
jshell> System.out.println("Hello World");
Hello World
```

Sim. É apenas isso. Não precisa definir nenhum bloco de código. O JShell é direto ao ponto. 

#### 2. JEP 445: Unnamed Classes and Instance Main Methods (Preview)

Ainda está em preview uma funcionalidade que vai ajudar muito esse primeiro contato. No JDK está como "preview feature" e esperamos vê-la integrada totalmente em breve. Todo o código da abordagem tradicional passa a se resumir a apenas

```java
void main() {
    System.out.println("Hello World");
}
```

## Variáveis

Java é fortemente tipada, então é necessário definir o tipo de cada variável no momento de sua declaração. Além disso, toda instrução Java termina com ponto-e-vírgula.

```
tipo nomeVariavel = valor;
```

> Como você vê em `nomeVariavel`, seguimos uma convenção "camel case" para nomes de variáveis e métodos e "pascal case" para nomes de classes. 

Os tipos primitivos são oito: quatro para inteiros, dois para ponto flutuante, um para booleanos e um para caracteres.

<table>
  <thead>
    <tr>
      <th>Palavra-chave</th>
      <th>Tamanho</th>
      <th>Intervalo</th>
      <th>Descrição</th>
      <th>Exemplo</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>byte</td>
      <td>1 byte</td>
      <td>-2<sup>7</sup> a 2<sup>7</sup>-1 (−128 a 127)</td>
      <td>Números inteiros de pequeno porte</td>
      <td>100</td>
    </tr>
    <tr>
      <td>short</td>
      <td>2 bytes</td>
      <td>-2<sup>15</sup> a 2<sup>15</sup>-1 (−32,768 a 32,767)</td>
      <td>Números inteiros de porte médio</td>
      <td>32000</td>
    </tr>
    <tr>
      <td>int</td>
      <td>4 bytes</td>
      <td>-2<sup>31</sup> a 2<sup>31</sup>-1</td>
      <td>Números inteiros</td>
      <td>42</td>
    </tr>
    <tr>
      <td>long</td>
      <td>8 bytes</td>
      <td>-2<sup>63</sup> a 2<sup>63</sup>-1</td>
      <td>Números inteiros grandes</td>
      <td>10000000000L</td>
    </tr>
    <tr>
      <td>float</td>
      <td>4 bytes</td>
      <td>±1.4e-45 a ±3.4e+38</td>
      <td>Números com ponto decimal (precisão simples)</td>
      <td>3.14f</td>
    </tr>
    <tr>
      <td>double</td>
      <td>8 bytes</td>
      <td>±4.9e-324 a ±1.7e+308</td>
      <td>Números com ponto decimal (precisão dupla)</td>
      <td>3.14159265359</td>
    </tr>
    <tr>
      <td>char</td>
      <td>2 bytes</td>
      <td>0 a 65,535</td>
      <td>Um único caractere Unicode</td>
      <td>'A'</td>
    </tr>
    <tr>
      <td>boolean</td>
      <td>1 bit</td>
      <td>true ou false</td>
      <td>Valor lógico</td>
      <td>true</td>
    </tr>
  </tbody>
</table>

### Casting

A sintaxe de casting em Java é simples 

```java
int numero = (int) 3.0;
```

Cuidado para respeitar os limites de cada tipo ou resultará em erro. 

### Valores precisos

Lembre-se que `double` e `float` são números de ponto flutuante e, portanto, não são precisos. Quando for necessário garantir precisão, seja em cálculo científico, financeiro ou outro, opte por usar `java.math.BigDecimal`. 

> Java carrega apenas o pacote `java.lang` por padrão. Os demais precisam ser importados. Para usar `BigDecimal` importe antes da sua declaração de classe

```java
import java.math.BigDecimal;
```

### String

Para a representação de textos usando `String`. Veja que todos os tipos primitivos iniciam com letra minúscula, mas `String` inicia com maiúscula. Isso porque `String` segue a convenção "pascal case", ou seja, é uma classe assim como `System`. 

## Entrada e saída do sistema

A entrada e saída de dados padrão do sistema permite a aplicação ser interativa com o usuário através do terminal. No primeiro código, o "Hello World", já fizemos uma saída usando `System.out.println`. Esse comando imprime uma linha inteira no terminal. Ou seja, ao final do conteúdo (parâmetro) adiciona uma quebra de linha. 

```java
void main() {
    System.out.println(1);
    System.out.println(2);
    System.out.println(3);
}
```

A saída do código acima serão os números 1, 2 e 3 em linhas separadas. Ainda existem outros dois métodos para saída a partir de `System.out`. Para escrever uma saída sem quebra de linha, use `print` e para formatar a saída, use `printf`.

```java
void main() {
    System.out.print(1);
    System.out.print(2);
    System.out.print(3);
}
```

Aqui a saída será "123", onde os números impressos estão em sequência um após o outro. Se quiser separá-los por vírgula pode formatar a saída

```java
void main() {
    System.out.printf("%d, %d, %d", 1, 2, 3);
}
```

As marcações `%d` são usadas para números inteiros. O primeiro parâmetro é a string de formatação, os demais são os parâmetros para substituir os marcadores - obedecendo a mesma quantidade.

Para fazer leitura de dados do sistema, precisamos ler de `System.in`. Ambos `System.in` e `System.out` representam os "streams" de entrada e saída padrão do sistema e são usados em manipulações de terminal. Para facilitar a manipulação de leitura, usamos a classe `java.util.Scanner` e é necessário importá-la. 

```java
import java.util.Scanner;

void main() {
    Scanner input = new Scanner(System.in);
    System.out.println("Digite um número inteiro");
    int numero = input.nextInt();
    System.out.println("numero= "+ numero);
    input.close();
}
```

Primeiro é necessário criar uma instância de `Scanner` (Scanner é uma classe e usamos suas instâncias - vamos falar de POO mais à frente). Existem métodos específicos para realizar a leitura de acordo com o dado pretendido

- nextInt: lê um `int`
- nextDouble: lê um `double`
- nextLine: lê uma linha inteira como `String`
- next: lê uma palavra (até o primeiro caractere em branco - seja espaço ou quebra de linha) como `String`.

Existem outros métodos e variações. Explore e divirta-se!

> Scanner manipula streams. Sempre que abrir um stream é necessário fechar. A princípio, use o método "close()". Mais à frente vamos ver outra forma mais prática.

## Formatação de texto

Abordamos rapidamente a formação de texto com o `System.out.printf` e marcadores para `int`. Agora vamos explorar um pouco mais. 

O `printf` usa a mesma sintaxe de formatação disponível em `String.format`. Internamente, quem faz o serviço é a classe `java.util.Formatter` ([documentação](https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html)) e o método `format`. Mas esse é um detalhe que não vamos entrar e você pode explorar a vontade. Aqui, vamos focar nos marcadores de formatação. 

As marcações sempre acontecem precedidas de `%`, logo `%d` é um marcador para um parâmetro inteiro decimal, que pode ser um `int`. Algumas outras opções

- b: para valores booleanos (`boolean`)
- s: para textos (`String`)
- f: para números decimais (`double`)
- %: o caractere `%`, literalmente.
- n: quebra de linha, específica de sistema. 

A quebra de linha com `%n` é especialmente útil porque nem todo sistema usa o mesmo símbolo para quebra de linha, alguns `\n` enquanto outros `\rn`. 

Também é possível definir outros detalhes de formatação, como a quantidade de casas decimais a serem escritas. Se quiser escrever `PI` como `3,14` use 

```java
void main() {
    System.out.printf("%.2f", Math.PI);
}
```

E se quiser `3.14`, como é usado em inglês, basta usar as configurações de `java.util.Locale`

```java
void main() {
    String s = String.format(Locale.of("en", "us"), "%.2f", Math.PI);
    System.out.println(s);
}
```

## Operadores

Java segue a sintaxe da maioria das linguagens quanto aos operadores

- Aritméticos: soma (`+`), subtração (`-`), multiplicação (`*`), divisão (`/`), resto da divisão (`%`)
- Lógicos: negação (`!`), E (`&&`), OU (`||`)
- Relacionais: maior que (`>`), menor que (`<`), igual (`==`), diferente (`!=`)
- Ternário: `? :`
- Comentário: linha inteira (`//`), bloco (`/* */`), documentação (`/** */`)

### Precedência

Assim como na Matemática, costumamos usar vários operadores em uma mesma expressão. Para que o resultado não nos surpreenda, precisamos conhecer bem a precedência dos operadores. Ou seja, em ordem de prioridade dos operadores e como serão executados.

| Operadores     | Precedência                           |
|----------------|---------------------------------------|
| postfix        | `exp++` `exp--`                       |
| unary          | `++exp` `--exp` `+exp` `-exp` `~` `!` |
| multiplicative | `*` `/` `&`                           |
| additive       | `+` `-`                               |
| relational     | `<` `>` `<=` `>=`                     |
| equality       | `==` `!=`                             |
| logical AND    | `&&`                                  |
| logical OR     | <code>&#124;&#124;</code>             |
| ternary        | `? :`                                 |
| assignment     | `=` `+=` `-=` `*=` `/=` `%=`          |

## Estudo de caso

Criar uma aplicação que lê dois números do terminal e informa o resultado da soma, subtração, divisão e multiplicação.

## Desafio

Ler a operação como terceiro parâmetro, por exemplo: 3 5 +
