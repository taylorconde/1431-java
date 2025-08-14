/**
 * Política que valida se a senha contém pelo menos uma letra maiúscula.
 */
class UpperCasePolicy implements PasswordPolicy {
    /**
     * Valida se a senha contém pelo menos uma letra maiúscula (A-Z).
     *
     * @param password A senha a ser validada.
     * @throws PasswordPolicyException se a senha não contiver letras maiúsculas.
     */
    @Override
    public void validate(String password) throws PasswordPolicyException {
        if (!password.matches(".*[A-Z].*")) {
            throw new PasswordPolicyException("A senha deve conter pelo menos uma letra maiúscula.");
        }
    }
}
