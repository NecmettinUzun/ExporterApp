package springIntro.exporter.stack;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueCollection implements ICollection {

    private  Queue<String> stack = new ArrayBlockingQueue<String>(1000);
    
    @Override
    public String get() {
        return this.stack.poll();
    }

    @Override
    public void put(String item) {
        this.stack.add(item);
    }

    @Override
    public int size() {
        return this.stack.size();
    }

}
