import org.junit.Test;

public class CaesarCipherTest {
    final private static String alphabet = Master.ALPHABET;

    @Test
    public void encrypt() {
        CaesarCipher cipher = new CaesarCipher(alphabet);

        String plainText = "This tests encryption!";
        String encryptedText = cipher.encrypt(plainText, 7);

        assert encryptedText.equals("0opzgAlzAzglujyFwApvud");
    }

    @Test
    public void decrypt() {
        CaesarCipher cipher = new CaesarCipher(alphabet);

        String cipherText = "0opzgAlzAzgkljyFwApvud";
        String decryptedText = cipher.decrypt(cipherText, 7);

        assert decryptedText.equals("This tests decryption!");
    }

    @Test
    public void encryptDecrypt() {
        CaesarCipher cipher = new CaesarCipher(alphabet);

        String plainText = "\"Jakub's dad, the biggest chungus!\" laughed the boy.";
        String encryptedText = cipher.encrypt(plainText, 7);
        String decryptedText = cipher.decrypt(encryptedText, 7);

        assert decryptedText.equals(plainText);
    }
}