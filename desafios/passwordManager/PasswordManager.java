import java.util.Map;

interface PasswordManager{
    String createNew(String key);

    /**
     * Searches for passwords associated with a given key.
     *
     * @param key The key to search for.
     * @return A map where keys are service names and values are their corresponding passwords.
     */
    Map<String, String> search(String key);

    /**
     * Resets the password for a given key.
     *
     * @param key The key of the password to reset.
     * @return The newly generated password.
     */
    String reset(String key);

    /**
     * Removes the password associated with a given key.
     *
     * @param key The key of the password to remove.
     */
    void remove(String key);
}