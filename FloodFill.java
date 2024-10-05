import java.awt.image.BufferedImage;

public class FloodFill {

    // Função de preenchimento com pilha e fila
    public static void floodFill(BufferedImage image, int startX, int startY, int targetColor, int newColor) {
        if (targetColor == newColor) {
            return;
        }

        int width = image.getWidth();
        int height = image.getHeight();

        // Usando as estruturas de dados customizadas
        DinamicStack<Main.Pixel> stack = new DinamicStack<>(10); // Tamanho inicial da pilha
        StaticQueue<Main.Pixel> queue = new StaticQueue<>(10); // Tamanho inicial da fila

        // Empilha o pixel inicial na pilha
        stack.push(new Main.Pixel(startX, startY));

        // Mudanças nas coordenadas para os pixels vizinhos
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // Enquanto houver pixels na pilha ou na fila
        while (!stack.isEmpty() || !queue.isEmpty()) {
            if (!stack.isEmpty()) {
                // Processa um pixel da pilha
                Main.Pixel p = stack.pop();
                int x = p.x;
                int y = p.y;

                // Se o pixel estiver fora da imagem, ignoramos
                if (x < 0 || x >= width || y < 0 || y >= height) {
                    continue;
                }

                // Se o pixel atual não tiver a cor do alvo, ignoramos
                if (image.getRGB(x, y) != targetColor) {
                    continue;
                }

                // Pinta o pixel com a nova cor
                image.setRGB(x, y, newColor);

                // Adiciona os pixels vizinhos na fila
                for (int i = 0; i < 4; i++) {
                    int newX = x + dx[i];
                    int newY = y + dy[i];
                    queue.add(new Main.Pixel(newX, newY));
                }

            } else if (!queue.isEmpty()) {
                // Processa um pixel da fila
                Main.Pixel p = queue.remove();
                int x = p.x;
                int y = p.y;

                // Se o pixel estiver fora da imagem, ignoramos
                if (x < 0 || x >= width || y < 0 || y >= height) {
                    continue;
                }

                // Se o pixel atual não tiver a cor do alvo, ignoramos
                if (image.getRGB(x, y) != targetColor) {
                    continue;
                }

                // Pinta o pixel com a nova cor
                image.setRGB(x, y, newColor);

                // Adiciona os pixels vizinhos na pilha
                for (int i = 0; i < 4; i++) {
                    int newX = x + dx[i];
                    int newY = y + dy[i];
                    stack.push(new Main.Pixel(newX, newY));
                }
            }
        }
    }
}