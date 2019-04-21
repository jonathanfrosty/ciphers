import org.junit.Test;

public class SubstitutionCipherTest {
    final private static String alphabet = Master.ALPHABET;

    @Test
    public void encrypt() {
        SubstitutionCipher cipher = new SubstitutionCipher(alphabet);

        String plainText = "This tests encryption!";
        String mapping = "zVhiZtRBS,ENlpHGIrbaT4qjD eQWY9JLK5OAo8-X.2kcgfUx?m'M3nF6w1uyCd!sv\"7P0";
        String encryptedText = cipher.encrypt(plainText, mapping);

        assert encryptedText.equals("gBSb0aZbab0ZphrDGaSHp\"");
    }

    @Test
    public void decrypt() {
        SubstitutionCipher cipher = new SubstitutionCipher(alphabet);

        String cipherText = "VdoN-yDNyN-xDcaCqyonFR";
        String mapping = ".6cxDf7doWEL0FnqIaNy'QlMCTmZ98?,gkAv\"1!jHJYS4VK32rsu5hiepwObPtBG URXz-";
        String decryptedText = cipher.decrypt(cipherText, mapping);

        assert decryptedText.equals("This tests decryption!");
    }

    @Test
    public void encryptDecrypt() {
        SubstitutionCipher cipher = new SubstitutionCipher(alphabet);

        String plainText = "Hello, my name is Jonathan.";
        String mapping = "kXm7xnrONspT1oK'hau8iP5\"DGMEw9-eWd?0HZfyV 2UCLb4!q6BlR,FvISgAQzJcj.t3Y";
        String encryptedText = cipher.encrypt(plainText, mapping);
        String decryptedText = cipher.decrypt(encryptedText, mapping);

        assert decryptedText.equals(plainText);
    }
}