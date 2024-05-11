public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> link = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            link.addLast(word.charAt(i));
        }
        return link;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> L = wordToDeque(word);
        while (L.size() > 1) {
            if (L.removeFirst() != L.removeLast())
                return false;
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> L = wordToDeque(word);
        while (L.size() > 1) {
            if (!cc.equalChars(L.removeFirst(), L.removeLast()))
                return false;
        }
        return true;
    }
}
