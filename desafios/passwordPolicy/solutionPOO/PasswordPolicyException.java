/**
 * Exceção personalizada lançada quando uma senha viola uma ou mais políticas de segurança.
 */
class PasswordPolicyException extends Exception {
    /**
     * Constrói uma nova exceção com a mensagem de detalhe especificada.
     * @param message A mensagem de detalhe.
     */
    public PasswordPolicyException(String message) {
        super(message);
    }
}