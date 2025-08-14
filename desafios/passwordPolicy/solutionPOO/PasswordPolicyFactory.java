import java.util.List;

/**
 * Fábrica responsável por criar conjuntos de políticas de senha.
 * Esta classe centraliza a criação da lista de políticas, facilitando a manutenção e a extensibilidade.
 */
class PasswordPolicyFactory {

    /**
     * Cria e retorna a lista padrão de políticas de senha.
     * @return Uma lista imutável contendo as instâncias das políticas de senha padrão.
     */
    public static List<PasswordPolicy> createDefaultPolicies() {
        return List.of(
            new MinimumLengthPolicy(), new UpperCasePolicy(), new LowerCasePolicy(),
            new DigitPolicy(), new SpecialCharacterPolicy(), new CommonPasswordPolicy()
        );
    }
}