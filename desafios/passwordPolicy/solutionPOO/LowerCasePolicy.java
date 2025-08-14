/**
 * Política que valida se a senha contém pelo menos uma letra minúscula.
 */
class LowerCasePolicy implements PasswordPolicy {
    /**
     * Valida se a senha contém pelo menos uma letra minúscula (a-z).
     *
     * @param password A senha a ser validada.
     * @throws PasswordPolicyException se a senha não contiver letras minúsculas.
     */
    @Override
    public void validate(String password) throws PasswordPolicyException {
        if (!password.matches(".*[a-z].*")) {
            throw new PasswordPolicyException("A senha deve conter pelo menos uma letra minúscula.");
        }
    }
}
