import java.awt.image.BufferedImage; // Trabalhar com imagens
import javax.imageio.ImageIO; // Ler e salvar imagens
import java.io.File; // Usar arquivos
import java.io.IOException;

public class FloodFillWithSimpleStack {

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
            BufferedImage image = ImageIO.read(new File("testinho.png")); // Nome da imagem original

            // Coordenadas iniciais e cores
            int startX = 1, startY = 1; // Coordenadas iniciais
            int targetColor = image.getRGB(startX, startY); // Cor do pixel inicial
            int newColor = 0xFF0000FF; // Nova cor (Azul)

            // Chama o algoritmo Flood Fill
            floodFill(image, startX, startY, targetColor, newColor);

            // Salva a imagem modificada
            ImageIO.write(image, "png", new File("testinho2.png")); // Nome da imagem resultante
            System.out.println("Flood Fill completado!");

        } catch (IOException e) {
            System.out.println("Erro ao carregar ou salvar a imagem: " + e.getMessage());
        }
    }

    // Função Flood Fill
    public static void floodFill(BufferedImage image, int startX, int startY, int targetColor, int newColor) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Usamos a pilha simples fornecida
        SimpleStack<Pixel> stack = new SimpleStack<>(width * height);

        // Verifica se a cor alvo é a mesma da nova cor
        if (targetColor == newColor) {
            return; // Não faz nada se as cores forem as mesmas
        }

        // Adiciona o pixel inicial na pilha
        stack.push(new Pixel(startX, startY));

        // Direções para os vizinhos (cima, baixo, esquerda, direita)
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // Enquanto a pilha não estiver vazia
        while (!stack.isEmpty()) {
            Pixel p = stack.pop(); // Remove o próximo pixel

            // Verifica se o pixel está dentro dos limites da imagem
            if (p == null || p.x < 0 || p.x >= width || p.y < 0 || p.y >= height) {
                continue;
            }

            int x = p.x;
            int y = p.y;

            // Verifica se o pixel atual tem a cor alvo
            if (image.getRGB(x, y) != targetColor) {
                continue;
            }

            // Pinta o pixel com a nova cor
            image.setRGB(x, y, newColor);

            // Adiciona os vizinhos à pilha
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                stack.push(new Pixel(newX, newY)); // Adiciona pixel vizinho à pilha
            }
        }
    }
}
