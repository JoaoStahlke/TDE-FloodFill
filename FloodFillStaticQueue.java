import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class FloodFillStaticQueue {
    static class Pixel {
        int x, y;

        public Pixel(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Implementação da fila estática
    public static class StaticQueue<T> {
        private int top = -1;
        private int base = 0;
        private T[] data;

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
                return null; // Modificado para retornar null se a fila estiver vazia
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

    public static void main(String[] args) {
        try {
            // Carregar a imagem
            BufferedImage image = ImageIO.read(new File("testinho.png")); // Insira o caminho correto da imagem aqui

            // Coordenadas iniciais
            int startX = 1; // x inicial
            int startY = 1; // y inicial
            int targetColor = image.getRGB(startX, startY); // Cor do pixel inicial
            int newColor = 0xFFFF0000; // Nova cor (vermelho)

            // Verifica se a nova cor é a mesma que a cor do pixel inicial
            if (targetColor != newColor) {
                // Chama a função de preenchimento
                floodFill(image, startX, startY, targetColor, newColor);

                // Salva a imagem modificada
                ImageIO.write(image, "png", new File("testinho_floodfill.png")); // Salva a nova imagem
                System.out.println("Preenchimento completo!");
            } else {
                System.out.println("A nova cor é a mesma que a cor do pixel inicial.");
            }

        } catch (IOException e) {
            System.out.println("Erro ao carregar ou salvar a imagem: " + e.getMessage());
        }
    }

    public static void floodFill(BufferedImage image, int startX, int startY, int targetColor, int newColor) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Inicializa a fila estática com tamanho suficiente
        StaticQueue<Pixel> queue = new StaticQueue<>(10000);
        queue.add(new Pixel(startX, startY));

        // Movimentos nas direções dos pixels vizinhos
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // Enquanto a fila não estiver vazia
        while (!queue.isEmpty()) {
            Pixel p = queue.remove();

            // Verifica se o pixel foi corretamente removido da fila
            if (p == null) {
                continue; // Se p é null, ignora essa iteração
            }

            int x = p.x;
            int y = p.y;

            // Verifica se o pixel está dentro dos limites da imagem
            if (x < 0 || x >= width || y < 0 || y >= height) {
                continue;
            }

            // Verifica se o pixel atual tem a cor alvo
            if (image.getRGB(x, y) != targetColor) {
                continue;
            }

            // Pinta o pixel com a nova cor
            image.setRGB(x, y, newColor);

            // Adiciona os pixels vizinhos na fila
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                queue.add(new Pixel(newX, newY));
            }
        }
    }
}
