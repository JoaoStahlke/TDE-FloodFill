import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Main {
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
            BufferedImage image = ImageIO.read(new File("ultrateste.png"));

            // Coordenadas iniciais
            int startX = 1;
            int startY = 1;

            int targetColor = image.getRGB(startX, startY); // Cor do pixel inicial
            int newColor = 0xFFFF0000; // Nova cor: Vermelho

            // Chama a função de preenchimento
            FloodFill.floodFill(image, startX, startY, targetColor, newColor);

            // Salva a imagem modificada
            ImageIO.write(image, "png", new File("ultrateste_modificada.png"));
            System.out.println("Preenchimento concluído!");

        } catch (IOException e) {
            System.out.println("Erro ao carregar ou salvar a imagem: " + e.getMessage());
        }
    }
}
