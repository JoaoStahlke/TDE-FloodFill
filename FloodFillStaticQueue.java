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
            BufferedImage image = ImageIO.read(new File("testinho.png"));

            int startX = 1, startY = 1;
            int targetColor = image.getRGB(startX, startY);
            int newColor = 0xFFFF0000; // Vermei

            floodFill(image, startX, startY, targetColor, newColor);

            ImageIO.write(image, "png", new File("testinho3.png"));
            System.out.println("Flood Fill completed!");

        } catch (IOException e) {
            System.out.println("Error loading or saving the image: " + e.getMessage());
        }
    }

    public static void floodFill(BufferedImage image, int startX, int startY, int targetColor, int newColor) {
        int width = image.getWidth();
        int height = image.getHeight();

        StaticQueue<Pixel> queue = new StaticQueue<>(width * height);

        if (targetColor == newColor) {
            return;
        }

        queue.add(new Pixel(startX, startY));

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Pixel p = queue.remove();
            if (p == null) {
                // If remove() returns null, exit the loop
                break;
            }

            if (p.x < 0 || p.x >= width || p.y < 0 || p.y >= height) {
                continue;
            }

            if (image.getRGB(p.x, p.y) != targetColor) {
                continue;
            }

            image.setRGB(p.x, p.y, newColor);

            for (int i = 0; i < 4; i++) {
                int newX = p.x + dx[i];
                int newY = p.y + dy[i];
                if (!queue.isFull()) {
                    queue.add(new Pixel(newX, newY));
                }
            }
        }
    }
}