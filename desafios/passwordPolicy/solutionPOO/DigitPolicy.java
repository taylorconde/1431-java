/**
 * Política que valida se a senha contém pelo menos um dígito numérico.
 */
class DigitPolicy implements PasswordPolicy {
    /**
     * Valida se a senha contém pelo menos um número (0-9).
     *
     * @param password A senha a ser validada.
     * @throws PasswordPolicyException se a senha não contiver números.
     */
    @Override
    public void validate(String password) throws PasswordPolicyException {
        if (!password.matches(".*\\d.*")) {
            throw new PasswordPolicyException("A senha deve conter pelo menos um número.");
        }
    }
}