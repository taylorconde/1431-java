import java.util.List;

class Main{
    public static void main(String[] args){
        if (args.length == 0) {
            System.out.println("Uso: java Main <senha>");
            System.out.println("Erro: A senha não foi fornecida.");
            return;
        }

        String password = args[0];

        List<PasswordPolicy> policies = PasswordPolicyFactory.createDefaultPolicies();

        try {
            for (PasswordPolicy policy : policies) {
                policy.validate(password);
            }
            System.out.println("Senha válida");
        } catch (PasswordPolicyException e) {
            System.out.println("Senha inválida");
            System.out.println("Motivo: " + e.getMessage());
        }
    } 
}