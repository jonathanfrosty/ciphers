public class CaesarCipher {
    final private String ALPHABET;

    public CaesarCipher(String alphabet) {
        ALPHABET = alphabet;
    }

    public String decrypt(String cipherText, int shift) {
        StringBuilder plainText = new StringBuilder();

        // for each character of the ciphertext, subtract a constant shift value from that character's alphabetical index, to determine the plaintext character
        for(int i = 0; i < cipherText.length(); i++) {
            char cipherChar = cipherText.charAt(i);
            int alphabetIndex = ALPHABET.indexOf(cipherChar);

            int newIndex = Master.mod(alphabetIndex - shift, ALPHABET.length());
            char plainChar = ALPHABET.charAt(newIndex);

            plainText.append(plainChar);
        }

        return plainText.toString();
    }

    public String encrypt(String plainText, int shift) {
        StringBuilder cipherText = new StringBuilder();

        // for each character of the plaintext, add a constant shift value to that character's alphabetical index, to determine the ciphertext character
        for(int i = 0; i < plainText.length(); i++) {
            char plainChar = plainText.charAt(i);
            int alphabetIndex = ALPHABET.indexOf(plainChar);

            int newIndex = Master.mod(alphabetIndex + shift, ALPHABET.length());
            char cipherChar = ALPHABET.charAt(newIndex);

            cipherText.append(cipherChar);
        }

        return cipherText.toString();
    }
}
