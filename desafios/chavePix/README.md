# 💳 Gerenciador de Chaves PIX

Este projeto é um programa de linha de comando em Java para validar, cadastrar, buscar e remover chaves PIX. O sistema utiliza um arquivo local para persistir os dados.

## Funcionalidades Principais

- **Cadastro de Chaves:** Permite associar uma chave PIX a dados bancários (instituição, agência, conta).
- **Validação Completa:** Valida o formato e os dígitos verificadores para chaves CPF e CNPJ.
- **Busca e Remoção:** Permite consultar e remover chaves existentes.
- **Tipos Suportados:** CPF, CNPJ, E-mail e Telefone Celular.
- **Persistência em Arquivo:** Salva os dados em um diretório local (`chaves_pix_data`).

## Como Compilar

Para compilar o projeto, navegue até o diretório e execute o seguinte comando:

```bash
javac -d out $(find src -name "*.java") -Xlint:unchecked
```

## Como Executar

Após a compilação, você pode executar o programa passando os argumentos necessários pela linha de comando.

**Para apenas validar uma chave:**
```bash
java -cp ./out br.com.ada.t1431.desafios.pix.Main <tipo_da_chave> <valor_da_chave>
```

**Para validar e salvar uma chave (funcionalidade extra):**
```bash
java -cp ./out br.com.ada.t1431.desafios.pix.extra.Main cadastrar -i <codigo_instituicao> -a <agencia> -c <conta> -t <tipo_da_chave> -v <valor_da_chave>
```

Substitua os parâmetros pelos valores desejados. Os tipos de chave podem ser: `CPF`, `CNPJ`, `EMAIL`, `CELULAR`.

## Exemplo de Uso

**1. Validando um CPF (válido):**
```bash
java -cp ./out br.com.ada.t1431.desafios.pix.Main CPF 12345678901
```
**Saída esperada:**
```
Chave PIX válida!
```

**2. Cadastrando uma nova chave (funcionalidade extra):**
```bash
java -cp ./out br.com.ada.t1431.desafios.pix.Main -i 001 -t EMAIL -v usuario@exemplo.com -i 001 -a 1234 -c 56789
```
**Saída esperada:**
```
Chave PIX 'usuario@exemplo.com' cadastrada com sucesso.
```

## Estrutura do Arquivo `.chaves` (Funcionalidade Extra)

O arquivo `.chaves` armazena as chaves no formato `codigo_instituicao:tipo_chave:valor_chave`, com uma chave por linha. Por exemplo:

```
001:EMAIL:usuario@exemplo.com
260:CPF:11122233344
```