---
marp: true
title: Controle de Datas e Fluxo no Java
description: Introdução às APIs de datas e estruturas de controle em Java
class: invert
---

# Aula 2 - Controle de Datas e Fluxo no Java

---

# Sumário

1. Introdução
2. Datas
3. Instância de Datas
4. Conversão de e para Texto
5. Marcadores de Formatação
6. Internacionalização
7. API Legada (`Date`)
---

# Sumário

8. Controle de Fluxo - Condicionais
9. Controle de Fluxo - Laços
10. Estudo de Caso
11. Desafio

---

# Introdução

Estamos fazendo uma introdução/revisão do Java e vimos como manipular variáveis e entrada e saída. Agora vamos ver como manipular datas e controlar o fluxo da aplicação com condicionais e laços.

---

# Datas

Java possui duas API's para datas, sendo uma legada (`Date`) e a mais nova introduzida no JDK 8 (`java.time`). Vamos começar pela forma atual e depois a legada.

---

# Datas (continuação)

A nova API segue a padronização da ISO 8601. Podemos dividir esse estudo em três partes: human-friendly, computacional e cálculos.

- `LocalTime`: representa uma hora do dia com os valores para hora, minuto, segundo e milissegundos. 
- `LocalDate`: representa uma data com dia, mês e ano.
- `LocalDateTime`: representa uma data e hora.
- `Month`/`Year`/`DayOfWeek`/`YearMonth`: auxiliares de `LocalDate`.

---

# Datas (continuação)

- `Instant`: usado para cálculos computacionais com precisão de nanossegundos.
- `Duration`: diferença de tempo e cálculos baseados em horas.
- `Period`: diferença de datas e cálculos baseados em datas.
- `OffsetDateTime` e `ZonedOffset` para fusos-horários.
- `java.time.temporal` com unidades de tempo e cálculos como `ChronoUnit`.

---

# Instância de Datas

As classes do pacote `java.time` são imutáveis.

```java
import java.time.LocalDate;
void main() {
    LocalDate hoje = LocalDate.now();
    LocalDate independencia = LocalDate.of(1822, 9, 7);
}
```

---

# Conversão de e para Texto

```java
LocalDate olimpiadas = LocalDate.parse("26/07/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
LocalDate copaFutebol = LocalDate.parse("2026-06-11", DateTimeFormatter.ISO_DATE);
```

```java
String sOlimpiadas = olimpiadas.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
```

---

# Marcadores de Formatação

- d, dd, MM, MMM, MMMM, yy, yyyy
- H, HH, h, hh, m, mm, s, ss
- S, SSS, a, z, Z, X, XX, XXX
- E, EEEE

[Documentação do DateTimeFormatter](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html)

---

# Internacionalização

```java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
void main() {
    LocalDate hoje = LocalDate.now();
    String diaDaSemana = hoje.format(DateTimeFormatter.ofPattern("EEEE", Locale.of("pt", "BR")));
    System.out.println(diaDaSemana);
}
```

---

# API Legada (Date)

- Problemas: mês inicia em 0, ano desde 1900, mutável
- Conversão entre `Date` e `LocalDate`/`LocalDateTime`

```java
public static LocalDate dateToLocalDate(Date date) { ... }
public static Date localDateToDate(LocalDate localDate) { ... }
```

---

# Controle de Fluxo - Condicionais

```java
if (nota >= 70) { ... } 
else if (nota >= 60) { ... } 
else { ... }
```

```java
switch (hoje) {
    case MONDAY, TUESDAY, ...:
        System.out.println("Dia de Semana");
        break;
}
```

---

# Controle de Fluxo - Laços

```java
for (int i = 0; i < numeros.length; i++) { ... }
for (int numero : numeros) { ... }
```

```java
while (num % 2 != 0) { ... }
do {
    num = random.nextInt(10);
} while (num % 2 != 0);
```

---

# Estudo de Caso

Dado um feriado, se tiver emenda (terça ou quinta-feira), vamos planejar uma viagem.

---

# Desafio

Calcular quantos dias, dias úteis (seg a sex) e semanas faltam de hoje até a data da viagem.