import java.util.Map;

interface PasswordManager{
    String createNew(String key);
    Map<String, String> search(String key);
    String reset(String key);
    void remove(String key);
}