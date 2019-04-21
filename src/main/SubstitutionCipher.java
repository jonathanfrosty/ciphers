import java.util.ArrayList;

public class SubstitutionCipher {
    final private String ALPHABET;

    public SubstitutionCipher(String alphabet) {
        ALPHABET = alphabet;
    }

    // perform decryption by setting the position of each given ciphertext character to its mapped plaintext character in a new string
    public String decrypt(String cipherText, String mapping) {
        return substituteCharacters(cipherText, mapping, false);
    }

    // perform encryption by setting the position of each given plaintext character to its mapped ciphertext character in a new string
    public String encrypt(String plainText, String mapping) {
        return substituteCharacters(plainText, mapping, true);
    }

    // substitute characters in the text for their corresponding characters in the mapping string
    private String substituteCharacters(String text, String mapping, boolean encrypt) {
        StringBuilder newText = new StringBuilder();
        newText.setLength(text.length());

        for(int i = 0; i < mapping.length(); i++) {
            char c = ALPHABET.charAt(i);
            char mapChar = mapping.charAt(i);

            ArrayList<Integer> occurrences = getAllOccurrences(text, (encrypt) ? c : mapChar);

            // for every index a given character appears at in the text, put its mapped character at those indexes in a new string
            for(int index : occurrences) {
                newText.setCharAt(index, (encrypt) ? mapChar : c);
            }
        }

        return newText.toString();
    }

    // get every index that a particular character appears in a given text
    private ArrayList<Integer> getAllOccurrences(String text, char c) {
        ArrayList<Integer> occurrences = new ArrayList<>();

        int index = text.indexOf(c);

        // while the 'indexOf' function continues to successfully find the given character in the text...
        while (index >= 0) {
            occurrences.add(index);
            index = text.indexOf(c, index + 1);
        }

        return occurrences;
    }
}