import java.util.HashSet;
import java.util.Set;

/**
 * Política que valida se a senha contém pelo menos dois caracteres especiais distintos.
 */
class SpecialCharacterPolicy implements PasswordPolicy {

    private static final int QUANTIDADE_CHARACTERS_ESPECIAIS_DISTINTOS = 1;

    /**
     * Valida se a senha contém pelo menos dois caracteres especiais distintos.
     * Um caractere especial é qualquer caractere que não seja letra ou número.
     *QUANTIDADE_CHARACTERS_ESPECIAIS_DISTINTOS
     * @param password A senha a ser validada.
     * @throws PasswordPolicyException se a senha não atender ao critério.
     */
    @Override
    public void validate(String password) throws PasswordPolicyException {
        Set<Character> specialChars = new HashSet<>();

        for (char c : password.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                specialChars.add(c);
            }
        }

        if (specialChars.size() < QUANTIDADE_CHARACTERS_ESPECIAIS_DISTINTOS) {
            throw new PasswordPolicyException("A senha deve conter pelo menos " + QUANTIDADE_CHARACTERS_ESPECIAIS_DISTINTOS + " caracteres especiais distintos.");
        }
    }
}
