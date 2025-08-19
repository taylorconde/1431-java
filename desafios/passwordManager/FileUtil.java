import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Utility class for file operations.
 * Provides static methods for reading from and writing to files.
 */
class FileUtil{

    /**
     * Reads the content of a file.
     * If the file does not exist, it assumes it will be created later and returns an empty string.
     *
     * @param filePath The path of the file to read.
     * @return The content of the file as a string. Returns an empty string if the file doesn't exist.
     */
    static String readFile(String filePath) {

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {

            System.out.println("Arquivo ainda não existe, será criando um novo");
        }
        return content.toString();
    }

    /**
     * Writes content to a file, overwriting any existing content.
     *
     * @param filePath The path of the file to write to.
     * @param content The content to be written to the file.
     */
    static void writeFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}