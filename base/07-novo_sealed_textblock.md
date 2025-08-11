# Melhor Controle

Neste módulo já estudamos que a orientação a objetos e o Java permitem o reaproveitamento de código através dos tipos e subtipos definidos por herança. Também abordamos que a palavra-chave `final`, quando usada em classes, não permitem subtipos. Em cenários que precisam controlar os possíveis subtipos, Java possui a funcionalidade de "classes seladas" (tradução livre de "sealed classes").

Outro novo recurso das versões mais recentes é o "bloco de texto" (tradução livre para "text blocks"), que permite definir uma `String` de várias linhas. Essa funcionalidade é útil em situações de textos longos com muitas concatenações. 

Esses novos recursos são fortes motivos para usar as versões mais recentes do Java nos seus projetos. Vamos detalhá-los a seguir.  

## Classes seladas

As classes seladas permitem controlar quais subtipos são permitidos. 

> Também é possível definir `sealed interfaces`.

```java
abstract sealed class Instrutor extends Pessoa implements Pagavel permits InstrutorFixo, InstrutorTemporario {
    //código da classe sem mais alterações
}
final class InstrutorTemporario extends Instrutor {
    //código da classe sem mais alterações
}
final class InstrutorFixo extends Instrutor {
    //código da classe sem mais alterações
}
```

As definições dos subtipos é marcada pela palavra-chave `permits` seguindo a lista dos subtipos. Observe que essa parte deve vir após a herança (`extends`) e implementações de interface (`implements`).

Todo subtipo de classe selada deve ser um subtipo direto e seguir uma das opções

- `final`: não pode ser herdada;
- `sealed`: deve definir os subtipos permitidos;
- `non-sealed`: aberta para qualquer subtipo.

> Uma vez que uma classe `final` pode ser subtipo de uma classe selada, então é possível definir um `record` como subtipo permitido.

Aqui estamos restringindo apenas as capacidades de herança. O controle de acesso ou visibilidade ainda continua sendo definido pelas palavras-chave como `public`.


## Blocos de texto

Blocos de texto são uma nova funcionalidade que permite `Strings` de múltiplas linhas. 

```java
String boasVindas = """
                    Olá! Te damos as boas vindas ao Java.
                    Este módulo é para quem busca se atualizar na linguagem
                     ou já programa em outra tecnologia.
                    """;
String jsonPessoa = """
    {
       "nome": "Pessoa"
    }
    """;
```

Esse recurso é comumente usado para mensagens, representações de JSON e para instruções SQL - mas sem nenhuma restrição para qualquer outro cenário.

> Todos os métodos e manipulações de `String` ainda se aplicam 

### String templates 

> Esta funcionalidade está em "preview" e deve ser usada com cautela, uma vez que pode ser removida nas próximas versões.

São uma nova sintaxe (ainda em "preview") para `Strings` que permite a interpolação entre texto estático e variáveis. 

```java
final class InstrutorFixo extends Instrutor {
    //código da classe sem mais alterações
    @Override
    String resumoContrato() {
        return STR."Nome: \{this.getNome()}; Início: \{this.dataInicio.format(DateTimeFormatter.ISO_DATE)}; Senioridade: \{this.senioridade.getDescricao()}";
    }
}
```

Perceba como esse recurso nos permite implementar o método `resumoContrato` interpolando o texto estático com o dinamismo do código Java. E também é possível combinar o bloco de texto com o "String template". 

```java
final class InstrutorFixo extends Instrutor {
    //código da classe sem mais alterações
    String converterParaJson() {
        return STR."""
        {
            "nome": "\{this.getNome()}",
            "dataInicio": "\{this.dataInicio.format(DateTimeFormatter.ISO_DATE)}",
            "senioridade": "\{this.senioridade.getDescricao()}"
        }
        """;
    }
}
```

## Estudo de caso

Evolução do projeto: Implementação de subtipos controlados usando `sealed classes` para diferentes perfis de usuário (`Bibliotecario`, `UsuarioComum`), restringindo as classes permitidas. Uso de `text blocks` para criar mensagens aos usuários e relatórios de forma clara e legível.

## Desafio

Expandir o sistema de perfis de usuário para incluir diferentes tipos de relatórios gerados dinamicamente, aplicando tanto `sealed classes` quanto `text blocks`.

