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

    public static void main(String[] args) {
        try {
            // Cria o diretório para os resultados
            File resultDir = new File("Resultado_Fila");
            if (!resultDir.exists()) {
                resultDir.mkdir();
            }

            BufferedImage image = ImageIO.read(new File("teste2.png"));
            int startX = 163, startY = 37;
            int targetColor = image.getRGB(startX, startY);
            int newColor = 0xFFFF0000; // Vermelho

            floodFill(image, startX, startY, targetColor, newColor);

            System.out.println("Flood Fill completed!");

        } catch (IOException e) {
            System.out.println("Error loading or saving the image: " + e.getMessage());
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

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

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

            // Salva uma imagem a cada 20 pixels pintados
            if (pixelsPintados % 1000 == 0) {
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

        // Salva a imagem final
        try {
            File outputfile = new File("Resultado_Fila/resultado_final.png");
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            System.out.println("Erro ao salvar imagem final: " + e.getMessage());
        }
    }
}