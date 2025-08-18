import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class KeyValueUtil{

    static String getValue(String key, String fileContent) {
        Pattern pattern = Pattern.compile("^" + Pattern.quote(key) + "=(.*)$", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(fileContent);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }

    static Map<String, String> search(String key, String fileContent) {
        Map<String, String> results = new LinkedHashMap<>();
        Pattern pattern = Pattern.compile("(?i)(^[^=]*" + Pattern.quote(key) + "[^=]*)=(.*)$", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(fileContent);
        while (matcher.find()) {
            String foundKey = matcher.group(1).trim();
            String foundValue = matcher.group(2).trim();
            results.put(foundKey, foundValue);
        }
        return results;
    }


    static String setValue(String key, String value, String fileContent) {
        Pattern pattern = Pattern.compile("^" + Pattern.quote(key) + "=.*$", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(fileContent);
        String newEntry = key + "=" + value;
        if (matcher.find()) {
            return matcher.replaceFirst(Matcher.quoteReplacement(newEntry));
        } else {
            if (fileContent.trim().isEmpty()) {
                return newEntry;
            }
            return fileContent.endsWith("\n") ? fileContent + newEntry : fileContent + "\n" + newEntry;
        }
    }


}