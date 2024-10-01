public class StaticQueue<T> {
    private int top = -1;
    private int base = 0;
    private T[] data;

    public StaticQueue(int size) {
        data = (T[]) new Object[size];
    }

    // Adiciona um elemento na fila
    public void add(T item) {
        if (isFull()) {
            throw new IllegalStateException("A fila está cheia. Não é possível adicionar mais elementos.");
        }
        top = move(top);
        data[top] = item;
    }

    // Remove um elemento da fila
    public T remove() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia. Não é possível remover elementos.");
        }
        T item = data[base];
        data[base] = null; // Limpa o valor na posição base
        base = move(base);
        return item;
    }

    public boolean isFull() {
        return move(top) == base && data[base] != null;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    // Move a posição de forma circular
    private int move(int position) {
        return (position + 1) % data.length;
    }
}
