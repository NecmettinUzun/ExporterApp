package springIntro.exporter.stack;

import java.util.ArrayList;
import java.util.List;

public class ListCollection implements ICollection {

    List<String> list = new ArrayList<String>();

    @Override
    public String get() {
        if (list.isEmpty() || list.size() == 0) {
            return null;
        }
        return list.remove(0);
    }

    @Override
    public void put(String item) {
        list.add(item);
    }

    @Override
    public int size() {
        return list.size();
    }

}
