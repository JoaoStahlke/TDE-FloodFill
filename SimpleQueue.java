public class SimpleQueue<T> {
    private T[] data;
    private int front, rear, size;

    @SuppressWarnings("unchecked")
    public SimpleQueue(int initialSize) {
        data = (T[]) new Object[initialSize];
        front = 0;
        rear = 0;
        size = 0;
    }

    // Adiciona um elemento no final da fila
    public void add(T value) {
        if (size == data.length) {
            resize();
        }
        data[rear] = value;
        rear = (rear + 1) % data.length;
        size++;
    }

    // Remove e retorna o elemento da frente da fila
    public T remove() {
        if (isEmpty()) {
            return null;
        }
        T value = data[front];
        front = (front + 1) % data.length;
        size--;
        return value;
    }

    // Verifica se a fila está vazia
    public boolean isEmpty() {
        return size == 0;
    }

    // Redimensiona a fila quando cheia
    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newData = (T[]) new Object[data.length * 2];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }
        front = 0;
        rear = size;
        data = newData;
    }
}
