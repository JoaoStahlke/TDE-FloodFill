import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class FloodFillTestes {

    // Classe para representar um pixel em x e y
    static class Pixel {
        int x, y;

        public Pixel(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        try {
            // Carregar imagem
            BufferedImage image = ImageIO.read(new File("testinho.png"));

            // Coordenadas iniciais
            int startX = 1;
            int startY = 1;
            int targetColor = image.getRGB(startX, startY); // Cor do pixel inicial

            int newColor = 0xFF0000FF; // Nova cor em ARGB (0xFF0000FF = Azul)

            // Chamar a função de preenchimento
            floodFill(image, startX, startY, targetColor, newColor);

            // Salvar a imagem modificada
            ImageIO.write(image, "png", new File("testinho3.png"));
            System.out.println("Tudo Inundado!");

        } catch (IOException e) {
            System.out.println("Erro ao carregar ou salvar a imagem: " + e.getMessage());
        }
    }

    public static void floodFill(BufferedImage image, int startX, int startY, int targetColor, int newColor) {
        int width = image.getWidth();
        int height = image.getHeight();

        DinamicStack<Pixel> stack = new DinamicStack<>(100000); // Pilha
        StaticQueue<Pixel> queue = new StaticQueue<>(100000);   // Fila

        // Matriz para marcar os pixels processados
        boolean[][] processed = new boolean[width][height];

        // Empurrar pixel inicial na pilha e fila
        stack.push(new Pixel(startX, startY));
        queue.add(new Pixel(startX, startY));
        processed[startX][startY] = true;

        // Vetores para controlar os vizinhos (esquerda, direita, cima, baixo)
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // Enquanto houver elementos na pilha ou fila
        while (!stack.isEmpty() || !queue.isEmpty()) {
            Pixel p = !stack.isEmpty() ? stack.pop() : queue.remove();

            if (p == null) continue; // Evitar erros de null

            int x = p.x;
            int y = p.y;

            // Verifica se está dentro da imagem e se a cor é igual ao targetColor
            if (x < 0 || x >= width || y < 0 || y >= height || image.getRGB(x, y) != targetColor) {
                continue;
            }

            // Pinta o pixel com a nova cor
            image.setRGB(x, y, newColor);

            // Adiciona os vizinhos que ainda não foram processados
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (newX >= 0 && newX < width && newY >= 0 && newY < height && !processed[newX][newY]) {
                    stack.push(new Pixel(newX, newY));
                    queue.add(new Pixel(newX, newY));
                    processed[newX][newY] = true;
                }
            }
        }
    }
}
