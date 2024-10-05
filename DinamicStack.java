public class DinamicStack<T> {
    private T[] data;
    private int top = -1;

    public DinamicStack(int capacidadeInicial) {
        data = (T[]) new Object[capacidadeInicial];
    }

    // Adicionar novos elementos
    public void push(T elemento) {
        if (isFull()) {
            aumentar();
        }
        data[++top] = elemento;
    }
    
    // Remove e retorna o elemento do topo da pilha
    public T pop() {
        if (isEmpty()) {
            return null;
        } else {
            T elemento = data[top];
            data[top--] = null;
            return elemento;
        }
    }

    public boolean isFull() {
        return top == data.length - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    // Expande a pilha quando cheia
    private void aumentar() {
        int novaCapacidade = data.length * 2;
        T[] newData = (T[]) new Object[novaCapacidade];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }
}
