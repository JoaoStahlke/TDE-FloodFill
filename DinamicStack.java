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
        System.out.println("Elemento " + elemento + " adicionado à pilha");
    }

    // Remove e retorna o elemento do topo da pilha
    public T pop() {
        if (isEmpty()) {
            System.out.println("A pilha está vazia :(");
            return null;
        } else {
            T elemento = data[top];
            data[top--] = null;
            System.out.println("Elemento " + elemento + " retirado da pilha");
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
        System.out.println("Capacidade da pilha expandida para " + novaCapacidade);
    }
}
