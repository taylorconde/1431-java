# Captura e Tratamento de Erros

Tudo seria tão bom se todo código que escrevemos funcionasse sem erros, certo? Porém, na programação, nunca podemos garantir a ausência de erros. Estamos sujeitos aos cenários inesperados e, assim, todo bom programa deve ser capaz de lidar com os erros. É a robustez ou a resiliência em tolerar falhas. 

Java define `Throwable` como a classe raiz para todos os erros (`Error`) ou exceções (`Exception`). Vamos nos ater apenas a esta última, que ainda pode ser divida em exceções checadas ou não checadas.

## Exceções checadas e não checadas

As exceções checadas são subtipos de `Exception` e devem, obrigatoriamente, definir um tratamento caso ocorram ou que sejam repassadas na pilha de execução. Essa obrigação é garantida em tempo de compilação. Essas exceções são comuns em cenários onde é esperado e possível o erro, como na leitura de um arquivo ou numa operação em bancos de dados, ou na conversão de um texto em URL (ex.: IOException, SQLException).

Já as exceções não checadas são filhas de `RuntimeException` e não precisam definir tratamento explícito. Normalmente, representam situações inesperadas como operações ilegais e mal uso da API (ex.: NullPointerException, IllegalArgumentException).

### Exceções personalizadas

Você pode criar suas próprias classes de exceção representando regras e condições específicas. Para isso, é necessário definir uma classe como subtipo de `Exception` ou `RuntimeException`, como em 

```java
public class MinhaExcecao extends RuntimeException {
    //Implemente o código da exceção aqui
}
```

### Exceções de negócio

O mais comum é usar exceções não checadas quando estiver definindo uma exceção personalizada. Exceções checadas geram efeitos colaterais no código porque exigem que sejam capturadas ou repassadas. Em se tratando de exceções não checadas, podemos definir apenas a captura "no ponto mais alto" da pilha de execução. 

## Captura e tratamento de exceções

Java usa a estrutura de bloco `try-catch` para definir um trecho de código em que pode ocorrer uma exceção e capturá-la. 

```java
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
void main() {
    try {
        File file = new File("nonexistentfile.txt");
        FileReader reader = new FileReader(file);
    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + e.getMessage());
    }
}
```

É possível definir múltiplos blocos `catch` para o mesmo `try` ou ainda condensa-los. 

> As boas práticas recomendam que o bloco `try` seja o mais conciso possível, isolando o ponto onde pode ocorrer a exceção. 

```java
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
void main() {
    try {
        File file = new File("nonexistentfile.txt");
        FileReader reader = new FileReader(file);
    } catch (FileNotFoundException e) {
        System.err.println("File not found: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Erro inesperado: " + e.getMessage());
    }
}
```

> Observe o uso de `System.err` a o invés de `System.out`. Essa abordagem é comum para diferenciar as saídas de sucesso e de erro. Execute na sua IDE e observe o comportamento.

Se os dois blocos `catch` foram iguais, ou seja, caso aconteça uma ou outra exceção o tratamento é o mesmo, então é possível reescrever como 

```java
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
void main() {
    try {
        File file = new File("nonexistentfile.txt");
        FileReader reader = new FileReader(file);
    } catch (FileNotFoundException | Exception e) {
        System.err.println("Erro inesperado: " + e.getMessage());
    }
}
```

Além do `try-catch` também existe o `finally` que sempre é executado, tanto em sucesso do `try` como em erro e captura no `catch`. Esse bloco serve para fechar o stream de leitura do arquivo, tanto em sucesso como em erro. 

```java
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
void main() {
    FileReader reader = null;
    try {
        File file = new File("nonexistentfile.txt");
        reader = new FileReader(file);
    } catch (FileNotFoundException e) {
        System.err.println("File not found: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Erro inesperado: " + e.getMessage());
    } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                System.err.println("Erro ao fechar o arquivo: " + e.getMessage() + ".");
            }
        }
    }
}
```

Esse é um código realmente longo e esquisito. Era assim nas versões muito antigas do Java e hoje já existe uma forma muito mais elegante e prática. 

## Try-with-resources

Esse recurso surgiu no Java 7 e se beneficia da interface `AutoCloseable`. Esta é uma interface que contém o método `close` e que deve ser chamado ao final do seu uso. Vamos ver como fica o código anterior reescrito com o `try-with-resources`.

```java
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
void main() {
    File file = new File("nonexistentfile.txt");
    try (FileReader reader = new FileReader(file)) {
        //leitura do arquivo
    } catch (IOException e) {
        System.err.println("Erro inesperado: " + e.getMessage());
    }
}
```

Dessa forma é até mais fácil de ler: "tente ler o arquivo e capture a exceção em caso de erro". 

## Estudo de caso

Agenda de compromissos de trabalho. Só deve permitir agendamentos em dias e horários de trabalho.

## Desafio

Pesquisar e usar o try-with-resources para manipulação do Scanner.
