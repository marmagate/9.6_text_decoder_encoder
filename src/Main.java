import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        EncryptionMachine secretInformation = new EncryptionMachine(new StringBuilder("TOP SECRET INFORMATION"));

        FileReader keyReader = new FileReader("keyPhrase.txt");
        FileOutputStream textDecryptor = new FileOutputStream("decryption.txt");

        char[] buffer = new char[255];
        int length = keyReader.read(buffer);
        String keyReaderString = new String(buffer);
        keyReader.close();

        secretInformation.decrypt(keyReaderString);
        textDecryptor.write(secretInformation.decryptedText.toString().getBytes());
        textDecryptor.close();
    }
}