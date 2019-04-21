public class FrequencyAnalysis {
    final private String ALPHABET;

    // Percentage occurrence frequencies for each letter in the English language.
    final private double[] FREQUENCIES = {8.167, 1.492, 2.782, 4.253, 12.702, 2.228, 2.015, 6.094, 6.966,
                                          0.153, 0.772, 4.025, 2.406, 6.749,  7.507, 1.929, 0.095, 5.987,
                                          6.327, 9.056, 2.758, 0.978, 2.360,  0.150, 1.974, 0.074};

    public FrequencyAnalysis(String alphabet) {
        ALPHABET = alphabet;
    }

    // return an array which stores frequencies that every character in a specified alphabet occur at each position of the key throughout a given text
    // the first dimension of the array represents each position of the key, and the second dimension is the percentage frequency for each character in the alphabet
    public double[][] getFrequencies(String cipherText, int keyLength) {
        double[][] frequencies = new double[keyLength][ALPHABET.length()];

        for (int i = 0; i < frequencies.length; i++) {
            frequencies[i] = getPositionFrequencies(cipherText, i, keyLength);
        }

        return frequencies;
    }

    // returns an array which stores the percentage frequencies that each character in a given alphabet occurs at a given position of the key throughout a given text
    // for example, for a key of length 6: the first position (position = 0) of the key throughout the text will be at indexes:  0, 6, 12, 18, 24, etc.
    //                                     the second position (position = 1) of the key throughout the text will be at indexes: 1, 7, 13, 19, 25, etc.
    //                                     the third position (position = 2) of the key throughout the text will be at indexes:  2, 8, 14, 20, 26, etc.
    private double[] getPositionFrequencies(String cipherText, int position, int keyLength) {
        double[] positionFrequencies = new double[ALPHABET.length()];
        int[] counts = new int[ALPHABET.length()];
        double sum = 0;

        for(; position < cipherText.length(); position += keyLength) {
            char c = cipherText.charAt(position);
            int alphabetIndex = ALPHABET.indexOf(c);

            // set this index of the array to the percentage frequency of the given character
            positionFrequencies[alphabetIndex] = (++counts[alphabetIndex] / ++sum) * 100;
        }

        return positionFrequencies;
    }

    // determine the key using frequency analysis
    public String calculateKey(double[][] frequencies) {
        StringBuilder key = new StringBuilder();

        for(double[] positionFrequencies : frequencies) {
            // get the alphabetical index of the character at a given position of the key
            int shift = calculateShift(positionFrequencies);
            key.append(ALPHABET.charAt(shift));
        }

        return key.toString();
    }

    // calculate the shift value for a given position of the key - this is the number of times that the frequency array for each position of the key
    // needs to be left-shifted for it to best match the letter-frequency data array of the English language.
    private int calculateShift(double[] positionFrequencies) {
        int shift = 0;
        double bestSimilarity = Long.MAX_VALUE;

        for(int i = 0; i < positionFrequencies.length; i++) {
            // determine how similar the frequency array is to the English language letter-frequency array
            double similarity = compareArrays(positionFrequencies, FREQUENCIES);

            if(similarity < bestSimilarity) {
                bestSimilarity = similarity;
                shift = i;
            }

            // left-shift the frequency array by one position
            double firstItem = positionFrequencies[0];
            System.arraycopy(positionFrequencies, 1, positionFrequencies, 0, positionFrequencies.length - 1);
            positionFrequencies[positionFrequencies.length - 1] = firstItem;
        }

        return shift;
    }

    // returns a double value that represents how similar two arrays of doubles are - the smaller the value, the more similar they are.
    // it sums the absolute difference between each index of each array to get the similarity value.
    private double compareArrays(double[] array1, double[] array2) {
        double similarity = 0;

        for(int i = 0; i < array1.length && i < array2.length; i++) {
            double difference = array1[i] - array2[i];
            if(difference < 0) difference *= -1;

            similarity += difference;
        }

        return similarity;
    }

    // return an array which stores the percentage frequencies that every character in a specified alphabet occur in a given text
    public double[] getOverallFrequencies(String cipherText) {
        double[] frequencies = new double[ALPHABET.length()];

        for(int i = 0; i < ALPHABET.length(); i++) {
            frequencies[i] = getOverallFrequency(cipherText, ALPHABET.charAt(i));
        }

        return frequencies;
    }

    // find how many occurrences of a character in a given text there are, and return the percentage frequency using this value
    private double getOverallFrequency(String cipherText, char c) {
        int count = cipherText.length() - cipherText.replace(c + "", "").length();
        return ((double) count / cipherText.length()) * 100;
    }
}