import java.util.Set;

/**
 * Política que valida se a senha não está em uma lista de senhas comuns e fáceis de adivinhar.
 */
class CommonPasswordPolicy implements PasswordPolicy {

    private static final Set<String> COMMON_PASSWORDS = Set.of(
        "123456",
        "12345678",
        "password",
        "qwerty",
        "admin",
        "12345",
        "123456789",
        "senha123",
        "password123!"
    );

    /**
     * Valida se a senha não é uma das senhas comuns. A verificação não diferencia maiúsculas de minúsculas.
     *
     * @param password A senha a ser validada.
     * @throws PasswordPolicyException se a senha for considerada muito comum.
     */
    @Override
    public void validate(String password) throws PasswordPolicyException {
        if (COMMON_PASSWORDS.contains(password.toLowerCase())) {
            throw new PasswordPolicyException("A senha é muito comum. Por favor, escolha uma senha mais forte.");
        }
    }
}