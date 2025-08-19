import java.util.Map;

class Main{
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Uso: java Main <nome_do_servico>");
            System.out.println("Por favor, forneça o nome do serviço como argumento.");
            return;
        }

        String serviceName = args[0];
        
        PasswordGenerator passwordGenerator = createPasswordGeneratorFromEnv();

        PasswordManager passwordManager = new LocalFileStorePasswordManager(".passwords", passwordGenerator);

        Map<String, String> searchResults = passwordManager.search(serviceName);
        String password = searchResults.get(serviceName);

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
            generatorType = "default";
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