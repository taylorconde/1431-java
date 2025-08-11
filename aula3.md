---
marp: true
title: Captura e Tratamento de Erros
description: Introdução ao tratamento de exceções em Java, boas práticas e recursos modernos
class: invert
---

# Aula 3 - Captura e Tratamento de Erros

---

# Sumário

1. Introdução
2. Exceções Checadas e Não Checadas
3. Exceções Personalizadas e de Negócio
4. Captura e Tratamento com Try-Catch
5. Uso de Finally
6. Try-with-Resources
7. Estudo de Caso
8. Desafio

---

# Introdução

Tudo seria tão bom se todo código que escrevemos funcionasse sem erros, certo?

Porém, na programação, nunca podemos garantir a ausência de erros.

Java define `Throwable` como a raiz para erros (`Error`) e exceções (`Exception`). Vamos focar nas exceções.

---

# Exceções Checadas e Não Checadas

- Checadas: subtipos de `Exception`, exigem tratamento.
  - Exemplos: `IOException`, `SQLException`
- Não checadas: filhas de `RuntimeException`, tratamento opcional.
  - Exemplos: `NullPointerException`, `IllegalArgumentException`

---

# Exceções Personalizadas

Criação de exceções próprias:

```java
public class MinhaExcecao extends RuntimeException {
    // Implementação
}
```

---

# Exceções de Negócio

Uso de exceções não checadas em regras de negócio.

Evita tratamento repetitivo.

Captura apenas no ponto mais alto da pilha de execução.

---

# Captura com Try-Catch

```java
try {
    FileReader reader = new FileReader("nonexistentfile.txt");
} catch (FileNotFoundException e) {
    System.out.println("File not found: " + e.getMessage());
}
```

---

# Try com Múltiplos Catch

```java
try {
    FileReader reader = new FileReader("nonexistentfile.txt");
} catch (FileNotFoundException e) {
    System.err.println("File not found: " + e.getMessage());
} catch (Exception e) {
    System.err.println("Erro inesperado: " + e.getMessage());
}
```

---

# Catch Condensado

```java
try {
    FileReader reader = new FileReader("nonexistentfile.txt");
} catch (FileNotFoundException | Exception e) {
    System.err.println("Erro inesperado: " + e.getMessage());
}
```

---

# Uso do Finally

```java
try {
    reader = new FileReader(file);
} catch (...) { ... } finally {
    if (reader != null) {
        try {
            reader.close();
        } catch (IOException e) {
            ...
        }
    }
}
```

Executado sempre, com ou sem erro.

---

# Try-with-Resources

```java
try (FileReader reader = new FileReader(file)) {
    // leitura do arquivo
} catch (IOException e) {
    System.err.println("Erro: " + e.getMessage());
}
```

Recurso do Java 7, usa `AutoCloseable`.

---

# Estudo de Caso

Agenda de compromissos de trabalho.

Só deve permitir agendamentos em dias e horários úteis.

---

# Desafio

Pesquisar e usar o `try-with-resources` para manipulação do `Scanner`.