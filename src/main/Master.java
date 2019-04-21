public class Master {
    final public static String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.'\"!?- ";

    // Java's modulo operator doesn't function correct with negative numbers, so this method accounts for this.
    public static int mod(int x, int y) {
        int result = x % y;
        if (result < 0) result += y;
        return result;
    }
}
