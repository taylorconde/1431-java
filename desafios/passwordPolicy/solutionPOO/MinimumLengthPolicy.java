/**
 * Política que valida se a senha possui um comprimento mínimo.
 */
class MinimumLengthPolicy implements PasswordPolicy {
    private static final int MIN_LENGTH = 8;

    /**
     * Valida se a senha tem pelo menos 8 caracteres.
     *
     * @param password A senha a ser validada.
     * @throws PasswordPolicyException se a senha for menor que 8 caracteres.
     */
    @Override
    public void validate(String password) throws PasswordPolicyException {
        if (password.length() < MIN_LENGTH) {
            throw new PasswordPolicyException("A senha deve ter no mínimo " + MIN_LENGTH + " caracteres.");
        }
    }
}