import java.awt.image.BufferedImage;

public class FloodFill {

    public static void floodFill(BufferedImage image, int startX, int startY, int targetColor, int newColor) {
        if (targetColor == newColor) {
            return;
        }

        int width = image.getWidth();
        int height = image.getHeight();

        // Usando as estruturas de dados personalizadas
        DinamicStack<Main.Pixel> stack = new DinamicStack<>(10000); // Tamanho inicial da pilha
        StaticQueue<Main.Pixel> queue = new StaticQueue<>(10000); // Tamanho inicial da fila

        // Matriz para verificar se um pixel já foi processado
        boolean[][] processed = new boolean[width][height];

        // Empilha o pixel inicial na pilha
        stack.push(new Main.Pixel(startX, startY));
        processed[startX][startY] = true;  // Marca o pixel inicial como processado

        // Mudanças nas coordenadas para os pixels vizinhos
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // Enquanto houver pixels na pilha ou na fila
        while (!stack.isEmpty() || !queue.isEmpty()) {
            Main.Pixel p = null;

            // Tenta pegar um pixel da pilha
            if (!stack.isEmpty()) {
                p = stack.pop();
            }
            // Se a pilha estiver vazia, tenta pegar da fila
            else if (!queue.isEmpty()) {
                p = queue.remove();
            }

            if (p == null) {
                continue;
            }

            int x = p.x;
            int y = p.y;

            // Se o pixel estiver fora da imagem, ignoramos
            if (x < 0 || x >= width || y < 0 || y >= height) {
                continue;
            }

            // Se o pixel atual não tiver a cor do alvo ou já foi processado, ignoramos
            if (!colorMatch(image.getRGB(x, y), targetColor) || processed[x][y]) {
                continue;
            }

            // Pinta o pixel com a nova cor
            image.setRGB(x, y, newColor);
            processed[x][y] = true; // Marca o pixel como processado

            // Adiciona os pixels vizinhos na fila/pilha se não forem processados
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (newX >= 0 && newX < width && newY >= 0 && newY < height && !processed[newX][newY]) {
                    // Adiciona à pilha ou fila dependendo da sua lógica
                    stack.push(new Main.Pixel(newX, newY));  // Empilha os vizinhos
                    queue.add(new Main.Pixel(newX, newY));   // Adiciona também na fila
                }
            }
        }
    }

    // Função para comparar cores com tolerância
    public static boolean colorMatch(int color1, int color2) {
        return color1 == color2;
    }
}
