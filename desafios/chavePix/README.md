# 💳 Validador e Cadastrador de Chaves PIX

Este projeto é um programa de linha de comando em Java para validar chaves PIX. O sistema verifica se uma chave PIX (CPF, CNPJ, e-mail ou telefone celular) é válida de acordo com seu tipo e formatação.

## Funcionalidades Principais

- **Validação de Chaves:** Recebe o tipo e o valor de uma chave PIX como argumentos e valida sua formatação.
- **Tipos Suportados:** CPF, CNPJ, E-mail e Telefone Celular.
- **Feedback Imediato:** Informa ao usuário se a chave é válida ou não, especificando o motivo da falha.

## Como Compilar

Para compilar o projeto, navegue até o diretório e execute o seguinte comando:

```bash
javac *.java -d ./out
```

## Como Executar

Após a compilação, você pode executar o programa passando os argumentos necessários pela linha de comando.

**Para apenas validar uma chave:**
```bash
java -cp ./out Main <tipo_da_chave> <valor_da_chave>
```

**Para validar e salvar uma chave (funcionalidade extra):**
```bash
java -cp ./out Main <codigo_instituicao> <tipo_da_chave> <valor_da_chave>
```

Substitua os parâmetros pelos valores desejados. Os tipos de chave podem ser: `CPF`, `CNPJ`, `EMAIL`, `CELULAR`.

## Exemplo de Uso

**1. Validando um CPF (válido):**
```bash
java -cp ./out Main CPF 12345678901
```
**Saída esperada:**
```
Chave PIX válida!
```

**2. Cadastrando uma nova chave (funcionalidade extra):**
```bash
java -cp ./out Main 001 EMAIL usuario@exemplo.com
```
**Saída esperada:**
```
Chave PIX 'usuario@exemplo.com' cadastrada com sucesso para a instituição 001.
```

## Estrutura do Arquivo `.chaves` (Funcionalidade Extra)

O arquivo `.chaves` armazena as chaves no formato `codigo_instituicao:tipo_chave:valor_chave`, com uma chave por linha. Por exemplo:

```
001:EMAIL:usuario@exemplo.com
260:CPF:11122233344
```