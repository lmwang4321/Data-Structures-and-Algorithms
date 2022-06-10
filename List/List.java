import java.util.Iterator;

public interface List<Item> extends Iterable<Item> {
    void addLast(Item x);
    Item getLast();
    int size();
    Item get(int index);
}
