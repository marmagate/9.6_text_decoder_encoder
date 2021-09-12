public class Main {
    public static void main(String[] args) {

        EncryptionMachine test1 = new EncryptionMachine(new StringBuilder("TOP SECRET INFORMATION"));

        test1.encrypt("123");
        System.out.println(test1.encryptedText);

        test1.decrypt("123");
        System.out.println(test1.decryptedText);
    }
}