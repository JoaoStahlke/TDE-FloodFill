import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SimpleFloodFill {

    // Classe para representar um pixel com coordenadas x e y
    static class Pixel {
        int x, y;
        public Pixel(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        try {
            // Carrega a imagem
            BufferedImage image = ImageIO.read(new File("testinho.png"));

            // Coordenadas iniciais e cores
            int startX = 1, startY = 1;
            int targetColor = image.getRGB(startX, startY);
            int newColor = 0xFF0000FF; // Azul

            // Chama o algoritmo Flood Fill
            floodFill(image, startX, startY, targetColor, newColor);

            // Salva a imagem modificada
            ImageIO.write(image, "png", new File("testinho3.png"));
            System.out.println("Flood Fill completado!");

        } catch (IOException e) {
            System.out.println("Erro ao carregar ou salvar a imagem: " + e.getMessage());
        }
    }

    // Função Flood Fill
    public static void floodFill(BufferedImage image, int startX, int startY, int targetColor, int newColor) {
        int width = image.getWidth();
        int height = image.getHeight();

        SimpleStack<Pixel> stack = new SimpleStack<>(10); // Usamos a pilha simples
        SimpleQueue<Pixel> queue = new SimpleQueue<>(10); // Usamos a fila simples

        // Adiciona o pixel inicial na pilha
        stack.push(new Pixel(startX, startY));
        queue.add(new Pixel(startX, startY));

        // Direções para os vizinhos (cima, baixo, esquerda, direita)
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // Enquanto a pilha não estiver vazia
        while (!stack.isEmpty() || !queue.isEmpty()) {
            Pixel p = !stack.isEmpty() ? stack.pop() : queue.remove();

            int x = p.x;
            int y = p.y;

            // Verifica se o pixel está dentro dos limites da imagem
            if (x < 0 || x >= width || y < 0 || y >= height) {
                continue;
            }

            // Verifica se o pixel tem a cor alvo
            if (image.getRGB(x, y) != targetColor) {
                continue;
            }

            // Pinta o pixel com a nova cor
            image.setRGB(x, y, newColor);

            // Adiciona os vizinhos à pilha
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                stack.push(new Pixel(newX, newY));
                queue.add(new Pixel(newX, newY));
            }
        }
    }
}
