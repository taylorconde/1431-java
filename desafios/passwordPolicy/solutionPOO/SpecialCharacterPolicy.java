/**
 * Política que valida se a senha contém pelo menos um caractere especial.
 */
class SpecialCharacterPolicy implements PasswordPolicy {
    /**
     * Valida se a senha contém pelo menos um caractere que não seja letra ou número.
     *
     * @param password A senha a ser validada.
     * @throws PasswordPolicyException se a senha não contiver caracteres especiais.
     */
    @Override
    public void validate(String password) throws PasswordPolicyException {
        if (!password.matches(".*[^a-zA-Z0-9].*")) {
            throw new PasswordPolicyException("A senha deve conter pelo menos um caractere especial.");
        }
    }
}
