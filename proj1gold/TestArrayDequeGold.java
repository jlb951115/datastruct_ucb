import org.junit.Test;
import static org.junit.Assert.*;
public class TestArrayDequeGold {
    @Test
    public void testArrayDeque() {
        ArrayDequeSolution<Integer> ref = new ArrayDequeSolution<Integer>();
        StudentArrayDeque<Integer> my = new StudentArrayDeque<Integer>();
        String s = "";
        for (int j = 0; j < 100; j++) {
             ref.addLast(j);
             my.addLast(j);
             s += "addLast(" + j + ")" + "\n";
            }

        for (int i = 0; i < 80; i++) {
            double prob = StdRandom.uniform();
            if (prob > 0.5) {
               int x = ref.removeFirst();
               int y = my.removeFirst();
               s += "removeFirst()" + "\n";
               assertEquals(s, x, y);
            } else {
                int x = ref.removeLast();
                int y = my.removeLast();
                s += "removeLast()" + "\n";
                assertEquals(s, x, y);
            }
        }
    }
}
