import java.util.Map;

class LocalFileStorePasswordManager implements PasswordManager{
 
    private final String filePath;
    private final PasswordGenerator passwordGenerator;

    public LocalFileStorePasswordManager(String filePath, PasswordGenerator passwordGenerator) {
        this.filePath = filePath;
        this.passwordGenerator = passwordGenerator;
    }


    @Override
    public Map<String, String> search(String key) {
        String fileContent = FileUtil.readFile(filePath);
        return KeyValueUtil.search(key, fileContent);
    }

    @Override
    public String createNew(String key) {
        String fileContent = FileUtil.readFile(filePath);
        String randomPassword = passwordGenerator.generateRandomPassword();
        String newFileContent = KeyValueUtil.setValue(key, randomPassword, fileContent);
        FileUtil.writeFile(filePath, newFileContent);
        return randomPassword;
    }

    @Override
    public String reset(String key) {
        // TODO: Implementar a lógica de reset de senha
        return null;
    }

    @Override
    public void remove(String key) {
        // TODO: Implementar a lógica de remoção
    }

}