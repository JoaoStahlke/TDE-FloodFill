public class SimpleStack<T> {
    private T[] data;
    private int top;

    @SuppressWarnings("unchecked")
    public SimpleStack(int initialSize) {
        data = (T[]) new Object[initialSize];
        top = -1;
    }

    // Adiciona um elemento no topo da pilha
    public void push(T value) {
        if (top == data.length - 1) {
            resize();
        }
        data[++top] = value;
    }

    // Remove e retorna o elemento do topo da pilha
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        return data[top--];
    }

    // Verifica se a pilha está vazia
    public boolean isEmpty() {
        return top == -1;
    }

    // Redimensiona a pilha quando cheia
    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newData = (T[]) new Object[data.length * 2];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }
}
