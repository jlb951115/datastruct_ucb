public interface Deque<Item> {
    public void addFirst(Item item);
    public void addLast(Item item);
    public int size();
    public Item removeFirst();
    public Item removeLast();
    public Item get(int index);
    public void printDeque();
    public boolean isEmpty();
}
