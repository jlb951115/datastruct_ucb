public class LinkedListDeque<Item> implements Deque<Item> {
    private class DoubleNode {
        public DoubleNode prev;
        public Item value;
        public DoubleNode next;
        public DoubleNode(DoubleNode prev, Item value, DoubleNode next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    private int size;
    private DoubleNode first;
    private  DoubleNode last;

    public  LinkedListDeque() {
        this.size = 0;
        this.first = new DoubleNode(null, null, null);
        this.last = new DoubleNode(null, null, null);
        this.first.next = this.last;
        this.last.prev = this.first;
    }

    @Override
    public void addFirst(Item item) {
        this.size += 1;
        this.first.next = new DoubleNode(this.first, item, this.first.next);
        this.first.next.next.prev = this.first.next;
    }

    @Override
    public void addLast(Item item) {
        this.size += 1;
        this.last.prev = new DoubleNode(this.last.prev, item, this.last);
        this.last.prev.prev.next = this.last.prev;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }


    @Override
    public void printDeque() {
        int i = 0;
        DoubleNode p = this.first.next;
        while (i < this.size) {
            System.out.print(p.value);
            System.out.print(' ');
            p = p.next;
            i += 1;
        }
    }


    @Override
    public Item removeFirst() {
        if (this.size == 0) {
            return null;
        } else {
            this.size -= 1;
            Item x = this.first.next.value;
            this.first.next = this.first.next.next;
            this.first.next.prev = this.first;
            return x;
        }
    }

    @Override
    public Item removeLast() {
        if (this.size == 0) {
            return null;
        } else {
            this.size -= 1;
            Item x = this.last.prev.value;
            this.last.prev = this.last.prev.prev;
            this.last.prev.next = this.last;
            return x;
        }
    }

    @Override
    public Item get(int index) {
        if (index >= this.size) {
            return null;
        } else {
            int i = 0;
            DoubleNode p = this.first.next;
            while (i < index) {
                p = p.next;
                i += 1;
            }
            return p.value;
        }
    }

    private LinkedListDeque(DoubleNode firstnode, int number, DoubleNode lastnode) {
        first = firstnode;
        size = number;
        last = lastnode;
    }
    public Item getRecursive(int index) {
        if (index >= this.size) {
            return null;
        } else if (index == 0) {
            return this.first.next.value;
        } else {
            DoubleNode first = new DoubleNode(null, null, this.first.next.next);
            int x = this.size - 1;
            LinkedListDeque<Item> L = new LinkedListDeque<>(first, x, null);
            return L.getRecursive(index - 1);
        }
    }
}
