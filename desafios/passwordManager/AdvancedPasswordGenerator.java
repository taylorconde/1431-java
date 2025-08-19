import java.security.SecureRandom;

class AdvancedPasswordGenerator implements PasswordGenerator{
    
    private static int PASSWORD_LENGTH = 32;
    private static String CHARS = "}{|ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
    
    @Override
    public String generateRandomPassword() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(CHARS.length());
            password.append(CHARS.charAt(index));
        }
        return password.toString();
    }
}
