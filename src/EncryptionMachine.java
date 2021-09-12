import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EncryptionMachine {

    private final StringBuilder text;
    private boolean isEncrypted = false;
    public StringBuilder encryptedText;
    public StringBuilder decryptedText;

    public EncryptionMachine(StringBuilder text) {
        this.text = text;
    }

    public void encrypt(String key) {
        encryptedText = text;
        // getting encryption time
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
        String date = dateFormat.format(calendar.getTime());
        // adding date to StringBuilder
        encryptedText.append("\n").append(date);
        // encrypting the message via loop (+++)
        for (int i = 0; i < encryptedText.length(); i++) {
            encryptedText.setCharAt(i, (char) ((int) encryptedText.charAt(i) + getKeyCode(key)));
        }
        isEncrypted = true;
        System.out.println("Message encrypted");
    }

    public void decrypt(String key) {
        if (!isEncrypted) {
            System.out.println("Message is not encrypted yet!");
        }
        decryptedText = encryptedText;
        for (int i = 0; i < decryptedText.length(); i++) {
            decryptedText.setCharAt(i, (char) ((int) decryptedText.charAt(i) - getKeyCode(key)));
        }
        System.out.println("Message decrypted via inputted key");
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