public class LinkedListDeque<T> {
    private class DoubleNode {
        public DoubleNode prev;
        public T value;
        public DoubleNode next;
        public DoubleNode(DoubleNode prev, T value, DoubleNode next) {
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

    public void addFirst(T item) {
        this.size += 1;
        this.first.next = new DoubleNode(this.first, item, this.first.next);
        this.first.next.next.prev = this.first.next;
    }

    public void addLast(T item) {
        this.size += 1;
        this.last.prev = new DoubleNode(this.last.prev, item, this.last);
        this.last.prev.prev.next = this.last.prev;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

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

    public T removeFirst() {
        if (this.size == 0) {
            return null;
        } else {
            this.size -= 1;
            T x = this.first.next.value;
            this.first.next = this.first.next.next;
            this.first.next.prev = this.first;
            return x;
        }
    }

    public T removeLast() {
        if (this.size == 0) {
            return null;
        } else {
            this.size -= 1;
            T x = this.last.prev.value;
            this.last.prev = this.last.prev.prev;
            this.last.prev.next = this.last;
            return x;
        }
    }

    public T get(int index) {
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
    public T getRecursive(int index) {
        if (index >= this.size) {
            return null;
        } else if (index == 0) {
            return this.first.next.value;
        } else {
            DoubleNode first = new DoubleNode(null, null, this.first.next.next);
            int x = this.size - 1;
            LinkedListDeque<T> L = new LinkedListDeque<>(first, x, null);
            return L.getRecursive(index - 1);
        }
    }
}
