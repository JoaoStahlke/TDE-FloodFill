import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class FloodFillStaticQueue {

    public static void main(String[] args) {
        try {
            File resultDir = new File("Resultado_Fila");
            if (!resultDir.exists()) {
                resultDir.mkdir();
            }

            BufferedImage image = ImageIO.read(new File("testinhoinho.png"));
            int startX = 1, startY = 1;
            int targetColor = image.getRGB(startX, startY);
            int newColor = 0xFFFF0000;

            floodFill(image, startX, startY, targetColor, newColor);

            System.out.println("Flood Fill completo!");

        } catch (IOException e) {
            System.out.println("Erro ao carregar ou salvar a imagem: " + e.getMessage());
        }
    }

    public static void floodFill(BufferedImage image, int startX, int startY, int targetColor, int newColor) {
        int width = image.getWidth();
        int height = image.getHeight();
        int pixelsPintados = 0;
        int numeroImagem = 0;

        StaticQueue<Pixel> queue = new StaticQueue<>(width * height);

        if (targetColor == newColor) {
            return;
        }

        queue.add(new Pixel(startX, startY));

        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};

        while (!queue.isEmpty()) {
            Pixel p = queue.remove();
            if (p == null) break;

            if (p.x < 0 || p.x >= width || p.y < 0 || p.y >= height) {
                continue;
            }

            if (image.getRGB(p.x, p.y) != targetColor) {
                continue;
            }

            image.setRGB(p.x, p.y, newColor);
            pixelsPintados++;

            if (pixelsPintados % 20 == 0) {
                try {
                    File outputfile = new File("Resultado_Fila/resultado_" + numeroImagem + ".png");
                    ImageIO.write(image, "png", outputfile);
                    numeroImagem++;
                } catch (IOException e) {
                    System.out.println("Erro ao salvar imagem intermediária: " + e.getMessage());
                }
            }
            for (int i = 0; i < 4; i++) {
                int newX = p.x + dx[i];
                int newY = p.y + dy[i];
                if (!queue.isFull()) {
                    queue.add(new Pixel(newX, newY));
                }
            }
        }

        try {
            File outputfile = new File("Resultado_Fila/resultado_final.png");
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            System.out.println("Erro ao salvar imagem final: " + e.getMessage());
        }
    }
}