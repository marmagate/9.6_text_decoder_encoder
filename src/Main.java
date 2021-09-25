public class Main {
    public static void main(String[] args) {

        try {
            EncryptionMachine encryptionMachine = new EncryptionMachine();
            encryptionMachine.metaMethod();
        } catch (ConvertException e) {
            System.out.println(e.getMessage());
        }
    }
}