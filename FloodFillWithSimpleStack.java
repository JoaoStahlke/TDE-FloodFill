import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class FloodFillWithSimpleStack {

    public static void main(String[] args) {
        try {
            // Cria o diretório para os resultados
            File resultDir = new File("Resultado_Pilha");
            if (!resultDir.exists()) {
                resultDir.mkdir();
            }

            BufferedImage image = ImageIO.read(new File("testinho.png"));
            int startX = 300, startY = 204;
            int targetColor = image.getRGB(startX, startY);
            int newColor = 0xFF0000FF; // Azul

            floodFill(image, startX, startY, targetColor, newColor);

            System.out.println("Flood Fill completado!");

        } catch (IOException e) {
            System.out.println("Erro ao carregar ou salvar a imagem: " + e.getMessage());
        }
    }

    public static void floodFill(BufferedImage image, int startX, int startY, int targetColor, int newColor) {
        int width = image.getWidth();
        int height = image.getHeight();
        int pixelsPintados = 0;
        int numeroImagem = 0;

        SimpleStack<Pixel> stack = new SimpleStack<>(width * height);

        if (targetColor == newColor) {
            return;
        }

        stack.push(new Pixel(startX, startY));

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!stack.isEmpty()) {
            Pixel p = stack.pop();

            if (p == null || p.x < 0 || p.x >= width || p.y < 0 || p.y >= height) {
                continue;
            }

            if (image.getRGB(p.x, p.y) != targetColor) {
                continue;
            }

            image.setRGB(p.x, p.y, newColor);
            pixelsPintados++;

            // Salva uma imagem a cada 1000 pixels pintados
            if (pixelsPintados % 1000 == 0) {
                try {
                    File outputfile = new File("Resultado_Pilha/resultado_" + numeroImagem + ".png");
                    ImageIO.write(image, "png", outputfile);
                    numeroImagem++;
                } catch (IOException e) {
                    System.out.println("Erro ao salvar imagem intermediária: " + e.getMessage());
                }
            }

            for (int i = 0; i < 4; i++) {
                int newX = p.x + dx[i];
                int newY = p.y + dy[i];
                stack.push(new Pixel(newX, newY));
            }
        }

        // Salva a imagem final
        try {
            File outputfile = new File("Resultado_Pilha/resultado_final.png");
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            System.out.println("Erro ao salvar imagem final: " + e.getMessage());
        }
    }
}