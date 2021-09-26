import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EncryptionMachine {

    private String SECRET_KEY = "";
    private String encryptionText = "";
    private String decryptionText = "";

    public void metaMethod() throws ConvertException {
        getValuesFromFiles();
        encrypt();
        decrypt();
        setValuesToFiles();
    }

    private void getValuesFromFiles() throws ConvertException {
        // getting decode word
        Reader keyReader;
        StringBuilder sbDWord = new StringBuilder(SECRET_KEY);
        int dwLength;
        try {
            keyReader = new FileReader("keyPhrase.txt", StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            throw new ConvertException("Something with file opening: " + e.getMessage());
        } catch (IOException e) {
            throw new ConvertException("Something went wrong: " + e.getMessage());
        }
        while (true) {
            try {
                if ((dwLength = keyReader.read()) == -1) break;
            } catch (IOException e) {
                throw new ConvertException("Something went wrong: " + e.getMessage());
            }
            sbDWord.append((char) dwLength);
        }
        SECRET_KEY = sbDWord.toString();
        try {
            keyReader.close();
        } catch (IOException e) {
            throw new ConvertException("Error closing stream: " + e.getMessage());
        }

        // working with enc file
        FileReader encReader;
        StringBuilder sbEnc = new StringBuilder(encryptionText);
        int encLength;
        try {
            encReader = new FileReader("encryption.txt", StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            throw new ConvertException("File not found: " + e.getMessage());
        } catch (IOException e) {
            throw new ConvertException("Something went wrong: " + e.getMessage());
        }
        while (true) {
            try {
                if ((encLength = encReader.read()) == -1) break;
            } catch (IOException e) {
                throw new ConvertException("Something went wrong: " + e.getMessage());
            }
            sbEnc.append((char) encLength);
        }
        encryptionText = sbEnc.toString();
        try {
            encReader.close();
        } catch (IOException e) {
            throw new ConvertException("Error closing stream: " + e.getMessage());
        }

        // working with dec file
        FileReader decReader;
        StringBuilder sbDec = new StringBuilder(decryptionText);
        int decLength;
        try {
            decReader = new FileReader("decryption.txt", StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            throw new ConvertException("File not found: " + e.getMessage());
        } catch (IOException e) {
            throw new ConvertException("Something went wrong: " + e.getMessage());
        }
        while (true) {
            try {
                if ((decLength = decReader.read()) == -1) break;
            } catch (IOException e) {
                throw new ConvertException("Something went wrong: " + e.getMessage());
            }
            sbDec.append((char) decLength);
        }
        decryptionText = sbDec.toString();
        try {
            decReader.close();
        } catch (IOException e) {
            throw new ConvertException("Error closing stream: " + e.getMessage());
        }
    }

    private void encrypt() throws ConvertException {
        try (FileReader fileReader = new FileReader("encryption.txt", StandardCharsets.UTF_8)) {
            if (fileReader.read() != -1) {
                // getting encryption time
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
                String date = dateFormat.format(calendar.getTime());
                // adding date to StringBuilder
                StringBuilder sbEnc = new StringBuilder(encryptionText);
                sbEnc.append("\n").append(date);
                // encrypting the message via loop (+++)
                for (int i = 0; i < sbEnc.length(); i++) {
                    sbEnc.setCharAt(i, (char) ((int) sbEnc.charAt(i) + getKeyCode(SECRET_KEY)));
                }
                System.out.println("Message encrypted");
                encryptionText = sbEnc.toString();
            }
        } catch (IOException e) {
            throw new ConvertException("Smth wrong with file reading: " + e.getMessage());
        }
    }

    private void decrypt() throws ConvertException {
        try (FileReader fileReader = new FileReader("decryption.txt", StandardCharsets.UTF_8)) {
            if ((fileReader.read()) != -1) {
                StringBuilder sbDec = new StringBuilder(decryptionText);
                for (int i = 0; i < sbDec.length(); i++) {
                    sbDec.setCharAt(i, (char) ((int) sbDec.charAt(i) - getKeyCode(SECRET_KEY)));
                }
                System.out.println("Message decrypted via inputted key");
                decryptionText = sbDec.toString();
            }
        } catch (IOException e) {
            throw new ConvertException("Smth wrong with file reading: " + e.getMessage());
        }
    }

    private void setValuesToFiles() throws ConvertException {
        try (FileWriter encWriter = new FileWriter("encryption.txt", StandardCharsets.UTF_8);
             FileWriter decWriter = new FileWriter("decryption.txt", StandardCharsets.UTF_8)) {
            encWriter.write(encryptionText);
            decWriter.write(decryptionText);
        } catch (IOException e) {
            throw new ConvertException("Smth wrong with file writing: " + e.getMessage());
        }
    }

    private int getKeyCode(String key) {
        // getting the key-code
        int keyCode = 0;
        for (int i = 0; i < key.length(); i++) {
            keyCode += key.charAt(i);
        }
        return keyCode;
    }
}

class ConvertException extends IOException {
    public ConvertException(String message) {
        super(message);
    }
}