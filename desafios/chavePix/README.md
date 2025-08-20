# 💳 Gerenciador de Chaves PIX

Este projeto é um programa de linha de comando (CLI) em Java para validar, cadastrar, buscar e remover chaves PIX. O sistema utiliza um arquivo local para persistir os dados.

## Funcionalidades Principais

- **Cadastro de Chaves:** Permite associar uma chave PIX a dados bancários (instituição, agência, conta).
- **Validação Completa:** Valida o formato e os dígitos verificadores para chaves CPF e CNPJ.
- **Busca e Remoção:** Permite consultar e remover chaves existentes.
- **Tipos Suportados:** `CPF`, `CNPJ`, `EMAIL`, `CELULAR`, `ALEATORIA`.
- **Persistência em Arquivo:** Salva os dados em um diretório local (`chaves_pix_data`).

## Como Compilar

Para compilar o projeto, navegue até o diretório raiz e execute o comando correspondente ao seu sistema operacional.

### Windows

No `cmd` ou `PowerShell`, execute os seguintes comandos:

```batch
dir /s /b src\\*.java > sources.txt
javac -d out @sources.txt
del sources.txt
```
*Estes comandos criam uma lista de todos os arquivos `.java`, a utilizam para a compilação e, em seguida, removem a lista temporária.*

### Linux / macOS

Em um terminal `bash` ou `zsh`, execute o seguinte comando:

```bash
javac -d out $(find src -name "*.java")
```
*Este comando encontra todos os arquivos `.java` no diretório `src` e os passa para o compilador `javac`.*

## Documentação das Classes `Main`

Esta seção detalha o funcionamento de cada uma das classes de entrada (`Main`) do projeto.

### 1. `br.com.ada.t1431.desafios.pix.Main`

Esta é a implementação mais básica, focada exclusivamente na **validação** de uma chave PIX.

- **Propósito:** Verificar se uma chave PIX (CPF, CNPJ, Email ou Celular) possui um formato válido.
- **Como Executar:** Requer dois argumentos na linha de comando: o tipo da chave e o seu valor.
- **Comando:**
  ```bash
  java -cp ./out br.com.ada.t1431.desafios.pix.Main <tipo_da_chave> <valor_da_chave>
  ```
- **Exemplo:**
  ```bash
  # Validando um CPF com formato correto
  java -cp ./out br.com.ada.t1431.desafios.pix.Main CPF 12345678901
  ```
  **Saída:** `Chave Pix válida.`

### 2. `br.com.ada.t1431.desafios.pix.extra.Main`

Esta classe expande a funcionalidade básica, introduzindo um sistema completo de **gerenciamento de chaves PIX com persistência em arquivo**.

- **Propósito:** Atuar como uma ferramenta CLI para cadastrar, buscar e remover chaves PIX, associando-as a dados bancários. Os dados são salvos em arquivos locais no diretório `chaves_pix_data`.
- **Operações Suportadas:** `cadastrar`, `buscar`, `remover`, `help`.
- **Como Executar:** A primeira palavra do comando define a operação a ser realizada.

- **Operação `cadastrar`:**
  - **Descrição:** Cadastra uma nova chave PIX associada a dados bancários.
  - **Comando:**
    ```bash
    java -cp ./out br.com.ada.t1431.desafios.pix.extra.Main cadastrar -i <instituicao> -a <agencia> -c <conta> -t <tipo> -v <valor>
    ```
  - **Exemplo:**
    ```bash
    java -cp ./out br.com.ada.t1431.desafios.pix.extra.Main cadastrar -i 001 -a 1234 -c 56789-0 -t EMAIL -v fulano@email.com
    ```
    **Saída:** `Chave PIX 'fulano@email.com' cadastrada com sucesso.`

- **Operação `buscar`:**
  - **Descrição:** Procura por uma chave PIX já cadastrada.
  - **Comando:**
    ```bash
    java -cp ./out br.com.ada.t1431.desafios.pix.extra.Main buscar <valor_da_chave>
    ```
  - **Exemplo:**
    ```bash
    java -cp ./out br.com.ada.t1431.desafios.pix.extra.Main buscar fulano@email.com
    ```
    **Saída:** `Chave encontrada: fulano@email.com | Instituição: 001 | Agência: 1234 | Conta: 56789-0`

- **Operação `remover`:**
  - **Descrição:** Apaga uma chave PIX do sistema.
  - **Comando:**
    ```bash
    java -cp ./out br.com.ada.t1431.desafios.pix.extra.Main remover <valor_da_chave>
    ```
  - **Exemplo:**
    ```bash
    java -cp ./out br.com.ada.t1431.desafios.pix.extra.Main remover fulano@email.com
    ```
    **Saída:** `Chave PIX 'fulano@email.com' removida com sucesso.`

### 3. `br.com.ada.t1431.desafios.pix.extra2.Main`

Esta é a versão mais avançada, que adiciona a capacidade de **gerar chaves aleatórias automaticamente**.

- **Propósito:** Oferecer todas as funcionalidades da classe `extra.Main`, com o bônus de poder gerar uma chave do tipo `ALEATORIA` sem que o usuário precise fornecer um valor.
- **Como Executar:** A sintaxe é idêntica à da classe `extra.Main`. A diferença está no comportamento ao cadastrar uma chave aleatória.

- **Operação `cadastrar` (com chave aleatória):**
  - **Descrição:** Para cadastrar uma chave aleatória, basta omitir o parâmetro `-v` (valor) e definir o tipo (`-t`) como `ALEATORIA`. O sistema irá gerar um UUID como valor da chave.
  - **Comando:**
    ```bash
    java -cp ./out br.com.ada.t1431.desafios.pix.extra2.Main cadastrar -i <instituicao> -a <agencia> -c <conta> -t ALEATORIA
    ```
  - **Exemplo:**
    ```bash
    java -cp ./out br.com.ada.t1431.desafios.pix.extra2.Main cadastrar -i 260 -a 0001 -c 12345-6 -t ALEATORIA
    ```
    **Saída:** `Chave PIX 'xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx' cadastrada com sucesso.` (Onde 'x' representa o UUID gerado).

As demais operações (`buscar`, `remover`) funcionam da mesma forma que na classe `extra.Main`.
