# Controlando o Fluxo de Execução

Estamos fazendo uma introdução/revisão do Java e vimos como manipular variáveis e entrada e saída. Agora vamos ver como manipular datas e controlar o fluxo da aplicação com condicionais e laços. 

## Datas

Java possui duas API's para datas, sendo uma legada (`Date`) e a mais nova introduzida no JDK 8 (`java.time`). Vamos começar pela forma atual e depois a legada. 

A nova API segue a padronização da ISO 8601. Podemos dividir esse estudo em três partes: human-friendly, computacional e cálculos. A primeira é aquela que conseguimos ler e tem fácil manipulação. A segunda tem o foco na precisão. Por fim, os cálculos são relacionados às comparações, etc. 

- `LocalTime`: representa uma hora do dia com os valores para hora, minuto, segundo e milissegundos. 
- `LocalDate`: representa uma data com dia, mês e ano.
- `LocalDateTime`: representa uma data e hora.
- `Month`/`Year`/`DayOfWeek`/`YearMonth`: auxiliares de `LocalDate` para manipulações mais específicas.
- `Instant`: usado para cálculos computacionais com precisão de nanossegundos. 
- `Duration`: diferença de tempo e cálculos baseados em horas.
- `Period`: diferença de datas e cálculos baseados em datas.

Ainda existem muitas outras classes como a `OffsetDateTime` e `ZonedOffset` para representação de fusos-horários (timezones) e o pacote `java.time.temporal` para acessar unidades de tempo e cálculos, como a `ChronoUnit` que permite calcular a diferença de tempo de acordo com uma unidade específica, como a diferença entre duas datas em dias, anos, minutos, etc. 

Agora, vamos ver algumas operações com datas comuns no dia a dia.

### Instância

As classes do pacote `java.time` são imutáveis, então não é possível instanciar e modificar seus atributos. Assim, o que temos são características dos padrões de projeto Singleton e Builder, onde acessamos uma instância ou podemos definir como é o objeto que queremos manipular. Parece a mesma coisa, mas não temos nunca acesso ao construtor das classes. 

```java
import java.time.LocalDate;
void main() {
    LocalDate hoje = LocalDate.now();//Obtém a representação da data de hoje
    LocalDate independencia = LocalDate.of(1822, 9, 7);//Obtém uma data específica
}
```

O código acima apresenta as duas formas mais comuns para obter objetos `LocalDate`. Existem formas equivalentes e similares para `LocalDateTime` e os demais tipos. 

### Conversão "de" e "para" texto

Outra forma possível de obter um `LocalDate` é convertendo a partir de texto. Para isso é necessário definir o formato da data. No trecho abaixo, estão dois formatos.

```java
import java.time.LocalDate;
void main() {
    LocalDate olimpiadas = LocalDate.parse("26/07/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    LocalDate copaFutebol = LocalDate.parse("2026-06-11", DateTimeFormatter.ISO_DATE);
}
```

Veja que na segunda opção utilizamos uma constante, que representa o padrão ISO (yyyy-MM-dd) e até poderia ser omitida. Isso porque a API `java.time` é aderente à ISO. 

Da mesma forma, usamos os formatadores para converter de `LocalDate` para texto. 

```java
import java.time.format.DateTimeFormatter;
void main() {
    String sOlimpiadas = olimpiadas.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
}
```

Uma vez que o formato ISO é o padrão, sempre que quiser converter de texto para `LocalDate` e vice-versa, o padrão ISO é o default. Ou seja, um `print` em uma variável `LocalDate` será no formato "yyyy-MM-dd". 

### Marcadores de formatação

A seguir, as marcações mais comuns no uso do `DateTimeFormatter`.

- d: dia com 1 dígito (1, 2, 10, 30)
- dd: dia com 2 dígitos (01, 02, 10, 30)
- MM: mês com 2 dígitos (01, 02, 10, 12)
- MMM: abreviação do nome do mês (Jan, Feb, Dec)
- MMMM: nome completo do mês (January, February, December)
- yy: ano com 2 dígitos (99, 23)
- yyyy: ano com 4 dígitos (1999, 2023)
- H: hora do dia (0-23) com 1 dígito (0, 1, 14, 23)
- HH: hora do dia (0-23) com 2 dígitos (00, 01, 14, 23)
- h: hora em formato de 12 horas (1-12) com 1 dígito (1, 2, 10, 12)
- hh: hora em formato de 12 horas (1-12) com 2 dígitos (01, 02, 10, 12)
- m: minutos com 1 dígito (0, 5, 30)
- mm: minutos com 2 dígitos (00, 05, 30)
- s: segundos com 1 dígito (0, 5, 45)
- ss: segundos com 2 dígitos (00, 05, 45)
- S: fração de segundo com 1 dígito (3 para 0.3 segundos)
- SSS: fração de segundo com 3 dígitos (003 para 0.003 segundos)
- a: marca AM/PM (AM, PM)
- z: fuso horário abreviado (PST, CET)
- Z: fuso horário numérico com sinal (+0000, -0800)
- X: fuso horário numérico com sinal e dois dígitos de hora (Z, +08, -04)
- XX: fuso horário numérico com sinal, dois dígitos de hora e dois dígitos de minutos (Z, +0800, -0430)
- XXX: fuso horário numérico com sinal, dois dígitos de hora e dois dígitos de minutos separados por ":" (Z, +08:00, -04:30)
- E: abreviação do dia da semana (Mon, Tue, Fri)
- EEEE: nome completo do dia da semana (Monday, Tuesday, Friday)

Não deixe de consultar a [documentação](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html) para um guia completo sobre `DateTimeFormatter`.

### Internacionalização

Na sessão anterior você pode ter percebido que os dias da semana e meses estão em inglês. Esse é o padrão da linguagem, mas não significa que você precisa traduzir ou fazer alguma código de conversão. A API `java.time` trabalha muito bem com internacionalização usando `java.util.Locale`. 

```java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
void main() {
    LocalDate hoje = LocalDate.now();
    String diaDaSemana = hoje.format(DateTimeFormatter.ofPattern("EEEE", Locale.of("pt", "BR")));
    System.out.println(diaDaSemana);
}
```

### Date (legado)

As primeiras versões do Java contavam com a class `java.util.Date` para manipulação de datas. Seu funcionamento era baseado na interface `Calendar` e não possuía uma representação uniforme, além de não trabalhar bem com padrões de internacionalização. Alguns problemas conhecidos são o mês de janeiro representado como mês 0 (zero) e os anos são representados a partir de 1900, ou seja, 2024 seria 124. Outro ponto é que a classe `Date` não é imutável, podendo causar problemas na manipulação e no comportamento. 

Ainda assim, pode ser possível precisar manter códigos que usam essa classe. Se a aplicação estiver numa versão superior ao Java 8, você pode converter das seguintes formas 

```java
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Converter {
    
    // Converte java.util.Date para LocalDate
    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
    }

    // Converte java.util.Date para LocalDateTime
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
    }

    // Converte LocalDate para java.util.Date
    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toInstant());
    }

    // Converte LocalDateTime para java.util.Date
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault())
            .toInstant());
    }
}
```

## Controle de fluxo - Condicionais

As estruturas de controle condicional em Java são `if`, `else` e `switch`. 

```java
void main() {
    int nota = 80;
    if (nota >= 70) {
        System.out.println("Aprovado");
    } else if (nota >= 60) {
        System.out.println("Recuperação");
    } else {
        System.out.println("Reprovado");
    }
}
```

> Apesar das chaves serem opcionais para blocos com uma linha, são muito recomendáveis para manter a facilidade de leitura.

A estrutura `switch` é interessante para valores exatos. Podemos usar `switch` para agrupar os dias de semana e de fim de semana.

```java
import java.time.LocalDate;
import java.time.DayOfWeek;
void main() {
    DayOfWeek hoje = LocalDate.now().getDayOfWeek();
    switch (hoje) {
        case MONDAY, TUESDAY, WEDNESDAY, FRIDAY, THURSDAY:
            System.out.println("Dia de Semana");
            break;
        case SATURDAY, SUNDAY:
            System.out.println("Fim de Semana");
            break;
    }
}
```

Veja que é possível agrupar opções num mesmo `case`, porém, no caso anterior, das notas, trabalhamos com intervalos, então o `if-else` é mais interessante. Essa é a sintaxe clássica do `switch` em Java. Existem outras formas mais modernas usando lambdas que vamos ver ao final deste módulo, junto às outras novidades das versões mais novas. Nessa sintaxe, é fundamental o uso do `break` para sair do bloco de código após realizar a ação do `case`. 

> Por curiosidade, experimente remover o `break` e observe o comportamento do código. É um possível bug que você pode enfrentar no dia a dia.

**Obs.:** o `switch` só funciona para números inteiros (`byte`, `short`, `char` e `int`), `enums` e `String`.

## Controle de fluxo - Laços

As estruturas de laço em Java são `for` e `while`. Porém, existem algumas variações de `for` e a forma `do-while` para o laço condicional. 

```java
void main() {
    int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    for (int i = 0; i < numeros.length; i++) {
        System.out.println(numeros[i]);
    }
}
```

É a forma mais clássica do laço `for`, muito útil quando é necessário ter controle sobre a contagem da interação. Se, porém, não for necessário, existe a forma "enhanced" que simplifica

```java
void main() {
    int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    for (int numero : numeros) {
        System.out.println(numero);
    }
}
```

> Apenas como curiosidade, existe ainda o `forEach` que pode ser usado em coleções e streams. Mas esse tema foge ao escopo deste módulo. 

Por fim, os laços condicionais com `while` e `do-while`

```java
void main() {
    int num = new Random().nextInt(10);
    int cont = 0;
    while (num % 2 != 0) {
        num = new Random().nextInt(10);
        cont++;
    }
    System.out.printf("%d é um número par. Foram necessárias %d interações.%n", num, cont);
}
```

Esse código obtém inteiros aleatórios entre 1 e 10 até que seja par. Outra opção seria 

```java
void main() {
    Random random = new Random();
    int num = 0;
    int cont = 0;
    do {
        num = random.nextInt(10);
        cont++;
    } while (num % 2 != 0);
    System.out.printf("%d é um número par. Foram necessárias %d interações.%n", num, cont);
}
```

## Estudo de caso

Dado um feriado, se tiver emenda (terça ou quinta-feira), vamos planejar uma viagem.

## Desafio

Calcular quantos dias, dias úteis (seg a sex) e semanas faltam de hoje até a data da viagem.
