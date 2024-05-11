public class ArrayDeque<Item> implements Deque<Item> {
    private int nextfirst;
    private int nextlast;
    private int size;
    private Item[] item;
    public ArrayDeque() {
        this.size = 0;
        this.nextfirst = 0;
        this.nextlast = 1;
        this.item = (Item[]) new Object[8];
    }

    private int plusone(int x) {
        int index = x + 1;
        if (index == this.item.length) {
            index = 0;
        }
        return index;
    }

    private int minusone(int x) {
        int index = x - 1;
        if (index < 0) {
            index = this.item.length - 1;
        }
        return index;
    }
    private void resize(int capticy) {
        Item[] a = (Item[]) new Object[capticy];
        int i = 0;
        int index = this.nextfirst;
        while (i < this.size) {
            a[i] = this.item[plusone(index)];
            i += 1;
            index = plusone(index);
        }
        this.item = a;
        this.nextfirst = this.item.length - 1;
        this.nextlast = this.size;
    }

    @Override
    public void addFirst(Item item) {
        if (this.size == this.item.length) {
            resize(2 * this.size);
        }
        this.size += 1;
        this.item[this.nextfirst] = item;
        this.nextfirst = minusone(this.nextfirst);
    }

    @Override
    public void addLast(Item item) {
        if (this.size == this.item.length) {
            resize(2 * this.size);
        }
        this.size += 1;
        this.item[this.nextlast] = item;
        this.nextlast = plusone(this.nextlast);
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
    public Item get(int index) {
        return this.item[(this.nextfirst + 1 + index) % this.item.length];
    }

    @Override
    public void printDeque() {
        int i = 0;
        while (i < this.size) {
            System.out.print(this.get(i));
            System.out.print(' ');
            i += 1;
        }
    }

    @Override
    public Item removeFirst() {
        if (this.size == 0) {
            return null;
        }
        this.size -= 1;
        int index = plusone(this.nextfirst);
        Item x = this.item[index];
        this.nextfirst = index;
        this.item[this.nextfirst] = null;
        if (this.item.length >= 16 && this.size <= this.item.length * 0.25) {
            resize(this.size * 2);
        }
        return x;
    }

    @Override
    public Item removeLast() {
        if (this.size == 0) {
            return null;
        }
        this.size -= 1;
        int index = minusone(this.nextlast);
        Item x = this.item[index];
        this.nextlast = index;
        this.item[this.nextlast] = null;
        if (this.item.length >= 16 && this.size <= this.item.length * 0.25) {
            resize(this.size * 2);
        }
        return x;
    }
}
