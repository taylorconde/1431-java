# 🛡️ Desafio Password Policy

## 📜 Descrição
Este desafio consiste em criar um programa Java que receba uma senha como parâmetro de linha de comando e valide se ela atende a uma política de segurança pré-definida.  
O objetivo é praticar a escrita, compilação e execução de programas Java pela linha de comando, além de reforçar o uso de parâmetros e expressões condicionais.

## 📚 Recapitulação
- Um programa Java básico pode ser escrito em qualquer editor de texto.
- Para compilar:
  ```bash
  javac MeuPrograma.java
  ```
- Isso gera o arquivo `.class`, desde que não haja erros de sintaxe.
- Para executar:
  ```bash
  java MeuPrograma
  ```

## 🎯 Objetivo do Desafio
Criar um programa Java que:
1. **Receba um parâmetro pela linha de comando** (a senha).
2. **Valide a senha** de acordo com os critérios:
   - No mínimo **8 caracteres**.
   - Contenha **pelo menos 1 número**.
   - Contenha **pelo menos 1 letra maiúscula**.
   - Contenha **pelo menos 1 letra minúscula**.
   - Contenha **pelo menos 1 caractere especial** (por exemplo: `!@#$%^&*()`).
3. **Exiba mensagem de sucesso** se a senha for válida.
4. **Exiba mensagem de erro** se a senha não atender aos critérios.

## 📌 Exemplo de uso
```bash
javac PasswordPolicy.java
java PasswordPolicy MinhaSenha123!
```
**Saída esperada (se válida):**
```
Senha cadastrada com sucesso!
```

**Saída esperada (se inválida):**
```
Erro: A senha não atende aos critérios de segurança.
```

## 💡 Dicas
- Use `args[0]` para capturar o parâmetro da linha de comando.
- Utilize **expressões regulares** (`String.matches`) ou verificações com métodos como `Character.isDigit`, `Character.isUpperCase`, etc.
- Lembre-se de validar se o usuário informou o parâmetro antes de processar.

## 🚀 Objetivo de Aprendizagem
- Revisar sintaxe básica do Java.
- Trabalhar com parâmetros de linha de comando.
- Aplicar validações lógicas e condicionais.
- Entender a importância de regras de segurança para senhas.

---
💬 *Em caso de dúvidas, procure o professor para suporte durante o desafio.*
