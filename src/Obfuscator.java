import java.io.*;

public class Obfuscator {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java JavaObfuscator <inputFile.java>");
            return;
        }

        String inputFile = args[0];
        String outputFile = "Obfuscated_" + inputFile;

        try {
            obfuscateCode(inputFile, outputFile);
            System.out.println("Obfuscation completed successfully. Output: " + outputFile);
        } catch (IOException e) {
            System.err.println("Error during obfuscation: " + e.getMessage());
        }
    }

    private static void obfuscateCode(String inputFile, String outputFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                line = obfuscateLine(line);
                writer.write(line);
                writer.newLine();
            }
        }
    }

    private static String obfuscateLine(String line) {
        // Удаление комментариев
        line = line.replaceAll("//.*|/\\*(.|\\n)*?\\*/", "");

        // Замена имен полей и методов
        line = line.replaceAll("\\b[A-Za-z_]\\w*\\b", generateRandomString(8));

        return line;
    }

    private static String generateRandomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomString = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }
}
