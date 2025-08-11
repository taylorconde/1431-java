---
marp: true
title: Introdução ao Java
description: Conceitos iniciais sobre ambiente, variáveis, entrada e saída, operadores e formatação em Java.
class: invert
---

# Aula 1 - Introdução ao Java

---

# Sumário

- Introdução
- Preparando o ambiente
- Hello World
- Formas alternativas
- Variáveis
- Casting e precisão
- String
- Entrada e saída

---

# Sumário

- Formatação de texto
- Operadores
- Estudo de caso
- Desafio

---

# Introdução

Java é uma linguagem fortemente tipada, orientada a objetos e multiplataforma, criada em 1995.  
Muito popular, especialmente no meio financeiro.  
Slogan: _"Escreva uma vez e rode em qualquer lugar."_  
Executa em JVM e, com GraalVM, pode compilar em código nativo.

---

# Preparando o ambiente

1. Instale o JDK mais atual ou LTS do [site oficial](https://www.oracle.com/java/technologies/downloads/)
2. Recomendação de IDEs:
   - IntelliJ IDEA
   - VS Code
   - Eclipse
3. Configure variáveis:
   - `$JAVA_HOME`
   - Inclua `$JAVA_HOMEin` no `$PATH`

---

# Verificação da instalação

```sh
java -version
```

Exemplo de saída:

```
openjdk version "21" 2023-09-19
OpenJDK Runtime Environment (build 21+35-2513)
OpenJDK 64-Bit Server VM (build 21+35-2513, mixed mode, sharing)
```

---

# Hello World

```java
public class PrimeiroPrograma {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```

Compile e execute:

```sh
javac PrimeiroPrograma.java
java PrimeiroPrograma
```

---

# Executando no IntelliJ

1. Crie um novo projeto Java
2. Marque "Add sample code"
3. Clique no botão verde ao lado do código para executar

![01-create-project](https://ada-lms-prd.s3.sa-east-1.amazonaws.com/module-templates/49df56c9-6aad-43a8-a79d-0c01647c6663/media/8d501da4e9c141329f560ac99062c176/86930bee-2053-4ccd-967c-47cb88c84ad7.png)

---

# Formas alternativas - JShell

```sh
jshell
```

Exemplo:

```sh
jshell> System.out.println("Hello World");
Hello World
```

Sem necessidade de declarar classe ou método.

---

# Formas alternativas - JEP 445

Nova funcionalidade em _preview_:

```java
void main() {
    System.out.println("Hello World");
}
```

Código ainda mais simples para iniciantes.

---

# Variáveis

Sintaxe:

```java
tipo nomeVariavel = valor;
```

- CamelCase: variáveis e métodos
- PascalCase: classes

Tipos primitivos: byte, short, int, long, float, double, char, boolean

---

# Tabela de tipos (1/2)

| Palavra-chave | Tamanho  | Intervalo                | Descrição                  | Exemplo       |
|---------------|----------|--------------------------|----------------------------|---------------|
| byte          | 1 byte   | -128 a 127               | Inteiros pequenos          | 100           |
| short         | 2 bytes  | -32.768 a 32.767         | Inteiros médios            | 32000         |
| int           | 4 bytes  | -2³¹ a 2³¹-1             | Inteiros padrão            | 42            |
| long          | 8 bytes  | -2⁶³ a 2⁶³-1             | Inteiros grandes           | 10000000000L  |

---

# Tabela de tipos (2/2)

| Palavra-chave | Tamanho  | Intervalo                | Descrição                        | Exemplo         |
|---------------|----------|--------------------------|----------------------------------|-----------------|
| float         | 4 bytes  | ±1.4e-45 a ±3.4e+38       | Decimais simples                 | 3.14f           |
| double        | 8 bytes  | ±4.9e-324 a ±1.7e+308     | Decimais duplos                 | 3.14159265359   |
| char          | 2 bytes  | 0 a 65.535                | Caractere Unicode                | 'A'             |
| boolean       | 1 bit    | true ou false             | Valor lógico                     | true            |

---

# Casting e precisão

Casting:

```java
int numero = (int) 3.0;
```

Para precisão, use:

```java
import java.math.BigDecimal;
```

```java
BigDecimal valor = new BigDecimal("10.99");
```

---

# String

```java
String nome = "João";
```

- `String` inicia com maiúscula porque é uma classe.
- Primitivos usam letra minúscula.

---

# Saída de dados

```java
System.out.println("Texto"); // com quebra de linha
System.out.print("Texto");   // sem quebra de linha
System.out.printf("Valor: %d", 10); // formatação
```

---

# Entrada de dados

```java
import java.util.Scanner;

Scanner input = new Scanner(System.in);
int numero = input.nextInt();
input.close();
```

Métodos: `nextInt`, `nextDouble`, `nextLine`, `next`

---

# Formatação de texto

Marcadores do `printf`:

- `%d` inteiro
- `%s` texto
- `%f` decimal
- `%b` booleano
- `%n` nova linha
- `%%` símbolo %

---

# Formatação decimal

```java
System.out.printf("%.2f", Math.PI); // 3,14
```

Para inglês:

```java
String s = String.format(Locale.of("en", "us"), "%.2f", Math.PI);
```

---

# Operadores

- Aritméticos: `+`, `-`, `*`, `/`, `%`
- Lógicos: `!`, `&&`, `||`
- Relacionais: `>`, `<`, `==`, `!=`
- Ternário: `? :`
- Comentários: `//`, `/* */`, `/** */`

---

# Precedência de operadores

| Tipo            | Operadores                         |
|-----------------|------------------------------------|
| Postfix         | `exp++`, `exp--`                  |
| Unário          | `++exp`, `--exp`, `!`, `+`, `-`   |
| Multiplicativo  | `*`, `/`, `%`                     |
| Aditivo         | `+`, `-`                          |
| Relacional      | `<`, `>`, `<=`, `>=`              |
| Igualdade       | `==`, `!=`                        |


---

# Precedência de operadores

| Tipo            | Operadores                         |
|-----------------|------------------------------------|
| Lógico E        | `&&`                              |
| Lógico OU       | `\|\|`                            |
| Ternário        | `? :`                             |
| Atribuição      | `=`, `+=`, `-=`, `*=`, `/=`, `%=` |

---

# Estudo de caso

Criar uma aplicação que:

- Lê dois números
- Exibe:
  - Soma
  - Subtração
  - Multiplicação
  - Divisão

---

# Desafio

Permitir que o usuário informe:

```text
3 5 +
```

E exibir o resultado da operação informada.

---
