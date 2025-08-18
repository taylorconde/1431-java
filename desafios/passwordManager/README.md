# 🔐 Gerenciador de Senhas de Linha de Comando

Este projeto é um gerenciador de senhas simples, executado via linha de comando. Ele permite que o usuário armazene e recupere senhas fortes, geradas automaticamente, para diferentes serviços e aplicativos. As senhas são salvas em um arquivo local chamado `.passwords`.

## Funcionalidades Principais

- **Geração Automática:** Se o serviço informado não existir no arquivo `.passwords`, o programa gera uma nova senha forte e a salva.
- **Recuperação de Senha:** Se o serviço já existir, o programa exibe a senha armazenada.
- **Armazenamento Local:** Todas as senhas são guardadas no arquivo `.passwords`, criado na mesma pasta do programa.
- **Uso Simples:** Interação direta pela linha de comando, recebendo o nome do serviço como único argumento.

## Como Compilar

Para compilar o projeto, navegue até o diretório e execute o seguinte comando (assumindo que o arquivo principal se chama `PasswordManager.java`):

```bash
javac PasswordManager.java
```

## Como Executar

Após a compilação, você pode executar o programa passando o nome do serviço como um argumento de linha de comando.

```bash
java PasswordManager <nome_do_servico>
```

Substitua `<nome_do_servico>` pelo nome do serviço para o qual você deseja gerar ou recuperar uma senha (ex: `gmail`, `facebook`, `github`).

### Exemplo de Uso

**1. Gerando uma nova senha para o `gmail`:**
```bash
java PasswordManager gmail
```
**Saída esperada (a senha gerada será aleatória):**
```
Nova senha para gmail gerada: Kx!8sP@z#tGv
```

**2. Recuperando a senha do `gmail`:**
```bash
java PasswordManager gmail
```
**Saída esperada:**
```
Senha para gmail: Kx!8sP@z#tGv
```

## Estrutura do Arquivo `.passwords`

O arquivo `.passwords` armazena as credenciais no formato `serviço:senha`, com um serviço por linha. Por exemplo:

```
gmail:Kx!8sP@z#tGv
facebook:!n9Yp#sWqZ@r
```