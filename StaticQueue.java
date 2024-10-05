public class StaticQueue<T> {
    private int top = -1;
    private int base = 0;
    private T[] data;

    @SuppressWarnings("unchecked")
    public StaticQueue(int size) {
        data = (T[]) new Object[size];
    }

    public void add(T item) {
        if (isFull()) {
            throw new IllegalStateException("A fila está cheia.");
        }
        top = move(top);
        data[top] = item;
    }

    public T remove() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia.");
        }
        T item = data[base];
        data[base] = null;
        base = move(base);
        return item;
    }

    public boolean isFull() {
        return move(top) == base && data[base] != null;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    private int move(int position) {
        return (position + 1) % data.length;
    }
}
