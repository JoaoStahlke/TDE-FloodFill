import java.awt.image.BufferedImage; // Trabalhar c Imagens
import javax.imageio.ImageIO; // Ler e Salvar Imagens
import java.io.File; // Usar Files
import java.io.IOException;
import java.util.Stack;

public class FloodFillTestes {
    static class Pixel {     // Classe para representar pixel em x e y
        int x, y;

        public Pixel(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        try { // Try Obrigatório do Intellij pelo Jeito e Carregar Imagem Buffered
            BufferedImage image = ImageIO.read(new File("xd.png")); // Caminho para Imagem é passado ao objeto file

            // Coordenadas iniciais
            int startX = 1; // x=1
            int startY = 1; // y=1
            int targetColor = image.getRGB(startX, startY); // Cor do pixel inicial

            int newColor = 0xFF0000FF; // Nova cor em ARGB, sendo Alpha opacidade *
            // 0xFFFF0000 (Vermelho)
            // 0xFF00FF00 (Verde)
            // 0xFF0000FF (Azul)

            // Chamar função de preenchimento e seus vários parâmetros
            floodFill(image, startX, startY, targetColor, newColor);

            // Salva a imagem modificada em PNG
            ImageIO.write(image, "png", new File("xd2.png"));
            System.out.println("Tudo Inundado!");

        } catch (IOException e) {
            System.out.println("Erro ao carregar ou salvar a imagem: " + e.getMessage());
        }
    }

    public static void floodFill(BufferedImage image, int startX, int startY, int targetColor, int newColor) {

        // Obtém a largura e altura da imagem
        int width = image.getWidth();
        int height = image.getHeight();

        // Pilha p armazenar os pixels que precisam ser processados
        Stack<Pixel> stack = new Stack<>();
        stack.push(new Pixel(startX, startY));

        // Mudanças nas coordenadas para os pixels vizinhos
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // Enquanto pilha não vazia, pop
        while (!stack.isEmpty()) {
            Pixel p = stack.pop();
            int x = p.x;
            int y = p.y;

            // Se o pixel estiver fora da imagem, ignorar
            if (x < 0 || x >= width || y < 0 || y >= height) {
                continue;
            }

            // Se o pixel atual não tiver a cor do alvo, ignorar
            if (image.getRGB(x, y) != targetColor) {
                continue;
            }

            // Pinta o pixel com a nova cor
            image.setRGB(x, y, newColor);

            // Adiciona os pixels vizinhos na pilha
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                stack.push(new Pixel(newX, newY));
            }
        }
    }
}
