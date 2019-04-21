public class VigenereCipher {
    final private String ALPHABET;

    public VigenereCipher(String alphabet) {
        ALPHABET = alphabet;
    }

    public String decrypt(String cipherText, String key) {
        StringBuilder plainText = new StringBuilder();
        String keyText = getKeyText(cipherText, key);

        // perform decryption by subtracting the alphabetical index of each character in the keyText
        // from the alphabetical index of the corresponding character in the ciphertext
        for(int i = 0; i < cipherText.length(); i++) {
            char cipherChar = cipherText.charAt(i);
            int alphabetIndex = ALPHABET.indexOf(cipherChar);

            char keyChar = keyText.charAt(i);
            int shift = ALPHABET.indexOf(keyChar);

            int newIndex = Master.mod(alphabetIndex - shift, ALPHABET.length());
            char plainChar = ALPHABET.charAt(newIndex);

            plainText.append(plainChar);
        }

        return plainText.toString();
    }

    public String encrypt(String plainText, String key) {
        StringBuilder cipherText = new StringBuilder();
        String keyText = getKeyText(plainText, key);

        // perform encryption by adding the alphabetical index of each character in the keyText
        // to the alphabetical index of the corresponding character in the plaintext
        for(int i = 0; i < plainText.length(); i++) {
            char plainChar = plainText.charAt(i);
            int alphabetIndex = ALPHABET.indexOf(plainChar);

            char keyChar = keyText.charAt(i);
            int shift = ALPHABET.indexOf(keyChar);

            int newIndex = Master.mod(alphabetIndex + shift, ALPHABET.length());
            char cipherChar = ALPHABET.charAt(newIndex);

            cipherText.append(cipherChar);
        }

        return cipherText.toString();
    }

    // append the key onto itself repeatedly until the length of the ciphertext is equaled
    private String getKeyText(String cipherText, String key) {
        StringBuilder keyText = new StringBuilder();

        while(keyText.length() < cipherText.length()) keyText.append(key);

        keyText.setLength(cipherText.length());

        return keyText.toString();
    }
}