public class OffByN  implements CharacterComparator {
    private int N;
    public OffByN(int x) {
        this.N = x;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == this.N;
    }

}
