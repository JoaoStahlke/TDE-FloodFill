public class SimpleStack<T> {
    private T[] data;
    private int top;

    public SimpleStack(int initialSize) {
        data = (T[]) new Object[initialSize];
        top = -1;
    }
    public void push(T value) {
        if (top == data.length - 1) {
            resize();
        }
        data[++top] = value;
    }
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        return data[top--];
    }
    public boolean isEmpty() {
        return top == -1;
    }

    private void resize() {
        T[] newData = (T[]) new Object[data.length * 2];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }
}
