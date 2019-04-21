import org.junit.Test;

public class VigenereCipherTest {
    final private static String alphabet = Master.ALPHABET;

    @Test
    public void encrypt() {
        VigenereCipher cipher = new VigenereCipher(alphabet);

        String plainText = "This tests encryption!";
        String key = "cipherkey";
        String encryptedText = cipher.encrypt(plainText, key);

        assert encryptedText.equals("VpxzdKowRuhtugIItRkwCd");
    }

    @Test
    public void decrypt() {
        VigenereCipher cipher = new VigenereCipher(alphabet);

        String cipherText = "VpxzdKowRuhslgIItRkwCd";
        String key = "cipherkey";
        String decryptedText = cipher.decrypt(cipherText, key);

        assert decryptedText.equals("This tests decryption!");
    }

    @Test
    public void encryptDecrypt() {
        VigenereCipher cipher = new VigenereCipher(alphabet);

        String plainText = "Hello, my name is Jonathan.";
        String key = "cipherkey";
        String encryptedText = cipher.encrypt(plainText, key);
        String decryptedText = cipher.decrypt(encryptedText, key);

        assert decryptedText.equals(plainText);
    }

    // This test uses frequency analysis of the English language to determine the key, and in turn decrypt the ciphertext.
    // To perform this technique, character frequency data must be referenced, however I only managed to find case-insensitive letter frequency data.
    // As such, this test restricts the alphabet, ciphertext and plaintext to simply uppercase letters with no spaces or punctuation.
    // Here, an extract from the book "Tess of the d'Urbervilles" is used.
    @Test
    public void decryptFrequencyAnalysis() {
        FrequencyAnalysis fa = new FrequencyAnalysis("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        VigenereCipher cipher = new VigenereCipher("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        String cipherText = "EPFQPXUWWOBEUHODDPREVQHNJDTSKTBJCMKAXXSGRXBDVWTGBHSJXWFTHZTCJOSVZTREKSEDFTAWBYHZIOBEUTRGUXJCWFFBWOHZTMHZIUTFNECETKAJAWUXOZFWRHDVBWPHVJGLTMNASJATYDDWKAJAGQHNJCSSEBCEZWUXCESJTGMVWFWXAEVSGBQLJWUXNYHZBGTTBYRHDHSJXPQLHWOXATGEXTWEOKDBWOBWLLRLAYKTCPTMEYXCKZTMNGSJBLVPOFMBWLBQHMQPFOTRRLASGZNCSVTMRNOFGHCDSFLXHZIJFXJYWFZLXXSLBFNDWXBVJYBGMENRWLBFRKSGNKOZFEXKAPZSMBXYGSMENLGLBVJYOKLBBEMGNTWOWOBEUOCAMPREVENVQXCJXKNROJWYXCMGNKOPSDBGPDHZTGRQCJFXAWMKAHFPRERKNWWYBHDDASGBJZFOATCPJWKBCHOKBLXGSJUNCTFWMTRYODBMCWSYHHMYOLNKNTVGIXROCFHPCPGKURJWZLATCDHWGWNCOFWLCCCFZUNEKWXGVLBSGWFZASGMAFGLFXRSONXXWZIYATWOAGKXCSOFXGXFUZMHYFHQHNXFHGYTWIWWMRKZHZYHAJCMKLNWTSGWHZIJITAPBLLTWOGALMNCGAVTWXOCXMQPASEELZAXHKCLPDXBOJCMPBUWCFERBSCOVHWQWVXGLPWFFXQLJWRHDDSWGXVWOLXEHDVWJNRNYDRBWBIAKXMJSKMANJRAWGCVBGPPQPFWRHDHSJXBCHOKHGUJPQVAJYQWMAJEWXHNWOMGNANCSLAXLZZVFHXYZGHDNOOKETWEIHHG";

        // use frequency analysis to determine the key, given its length
        int keyLength = 6;
        double[][] frequencies = fa.getFrequencies(cipherText, keyLength);
        String key = fa.calculateKey(frequencies);
        String decryptedText = cipher.decrypt(cipherText, key);

        assert decryptedText.equals("VERYWELLIWILLWALKWITHYOUASFARASYOURHOMEOYESSHEANSWEREDWITHAJADEDGAITWALKWIMEIFYOUWILLIDOBEARINMINDTHATYOUCAMETOMARRYMEBEFOREYOUKNEWOMYSTATEPERHAPSPERHAPSYOUAREALITTLEBETTERANDKINDERTHANIHAVEBEENTHINKINGYOUWEREWHATEVERISMEANTASKINDNESSIAMGRATEFULFORWHATEVERISMEANTINANYOTHERWAYIAMANGEREDATICANNOTSENSEYOURMEANINGSOMETIMESIFICANNOTLEGITIMIZEOURFORMERRELATIONSATLEASTICANASSISTYOUANDIWILLDOITWITHMUCHMOREREGARDFORYOURFEELINGSTHANIFORMERLYSHOWEDMYRELIGIOUSMANIAORWHATEVERITWASISOVERBUTIRETAINALITTLEGOODNATUREIHOPEIDONOWTESSBYALLTHATSTENDERANDSTRONGBETWEENMANANDWOMANTRUSTMEIHAVEENOUGHANDMORETHANENOUGHTOPUTYOUOUTOFANXIETYBOTHFORYOURSELFANDYOURPARENTSANDSISTERSICANMAKETHEMALLCOMFORTABLEIFYOUWILLONLYSHOWCONFIDENCEINMEHAVEYOUSEENEMLATELYSHEQUICKLYINQUIREDYESTHEYDIDNTKNOWWHEREYOUWEREITWASONLYBYCHANCETHATIFOUNDYOUHERETHECOLDMOONLOOKEDASLANTUPON");
    }
}