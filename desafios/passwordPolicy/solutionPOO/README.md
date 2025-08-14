# Solução Orientada a Objetos - Validador de Senha

Esta é uma implementação orientada a objetos do desafio de validação de senha. O projeto utiliza o padrão de projeto Factory e múltiplas classes de política para verificar a segurança de uma senha fornecida via linha de comando.

## Como Compilar

Para compilar o projeto, navegue até o diretório `solutionPOO` e execute o seguinte comando. Ele irá compilar todos os arquivos `.java` necessários.

```bash
javac *.java
```

## Como Executar

Após a compilação, você pode executar o programa passando a senha que deseja validar como um argumento de linha de comando.

```bash
java Main <sua_senha_aqui>
```

### Critérios de Validação
A senha deve atender a todos os seguintes critérios para ser considerada válida:
- No mínimo 8 caracteres.
- Pelo menos 1 letra maiúscula.
- Pelo menos 1 letra minúscula.
- Pelo menos 1 número.
- Pelo menos 1 caractere especial (ex: `!@#$`).
- Não pode ser uma senha comum (ex: "123456", "password").

### Exemplo de Senha Válida

```bash
java Main SenhaForte123!
```
**Saída esperada:**
```
Senha válida
```

### Exemplo de Senha Inválida

```bash
java Main senhafraca
```
**Saída esperada:**
```
Senha inválida
```