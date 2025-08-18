import java.util.Map;

class Main{
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: java Main <nome_do_servico>");
            System.out.println("Por favor, forneça o nome do serviço como argumento.");
            return;
        }

        String serviceName = args[0];
        
        // 1. Criar o gerador de senha com base na variável de ambiente
        PasswordGenerator passwordGenerator = createPasswordGeneratorFromEnv();

        // 2. Instanciar o PasswordManager com o arquivo e o gerador escolhido
        PasswordManager passwordManager = new LocalFileStorePasswordManager(".passwords", passwordGenerator);

        // 3. Buscar a senha exata no mapa retornado pelo search
        Map<String, String> searchResults = passwordManager.search(serviceName);
        String password = searchResults.get(serviceName);

        // 4. Decidir se mostra a senha existente ou cria uma nova
        if (password != null) {
            System.out.println("Senha para " + serviceName + ": " + password);
        } else {
            String newPassword = passwordManager.createNew(serviceName);
            System.out.println("Nova senha para " + serviceName + " gerada: " + newPassword);
        }
    }

    /**
     * Cria uma instância de PasswordGenerator com base na variável de ambiente PASSWORD_GENERATOR.
     * Os valores possíveis são "simple", "advanced". O padrão é "default".
     * @return Uma instância de PasswordGenerator.
     */
    private static PasswordGenerator createPasswordGeneratorFromEnv() {
        String generatorType = System.getenv("PASSWORD_GENERATOR");

        if (generatorType == null) {
            generatorType = "default"; // Valor padrão se a variável não estiver definida
        }

        switch (generatorType.toLowerCase()) {
            case "simple":
                return new SimplePasswordGenerator();
            case "advanced":
                return new AdvancedPasswordGenerator();
            default:
                return new DefaultPasswordGenerator();
        }
    }
}