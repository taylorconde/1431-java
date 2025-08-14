/**
 * Define o contrato para uma política de validação de senha.
 * Cada implementação desta interface representa uma regra específica que a senha deve seguir.
 */
interface PasswordPolicy {

    /**
     * Valida uma senha de acordo com a política específica.
     * @param password A senha a ser validada.
     * @throws PasswordPolicyException se a senha não atender aos critérios da política.
     */
    void validate(String password) throws PasswordPolicyException;
}