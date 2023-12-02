import java.util.Scanner;

/**
 * A classe JogoDaVelha implementa um jogo da velha simples.
 */
public class JogoDaVelha {
    static String[][] pos = {{"00", "01", "02"}, {"10", "11", "12"}, {"20", "21", "22"}};
    static int player = 1;
    static int winner = 0;

    /**
     * O método principal que inicia e controla o jogo.
     */
    public static void main(String[] args) {
        do {
            drawBoard();
            requestMove();
            winner = checkWinner();
            player++;
        } while (winner == 0);
        drawBoard();
        displayResult();
    }

    /**
     * Desenha o tabuleiro do jogo na tela.
     */
    static void drawBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + pos[i][j]);
                if (j < 2) {
                    System.out.print(" |");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("--------------");
            }
        }
    }



    /**
     * Solicita ao jogador atual para fazer uma jogada.
     */
    static void requestMove() {
        if (player % 2 == 0) {
            System.out.println("Player 2, informe a row e na próxima row a column que deseja inserir o X: ");
        } else {
            System.out.println("Player 1, informe a row e na próxima row a column que deseja inserir o O: ");
        }
        try {
            Scanner input = new Scanner(System.in);
            int row = input.nextInt();
            int column = input.nextInt();
            markPosition(row, column);
        } catch (Exception e) {
            System.out.println("Digite um número válido corresponde na matriz.");
            drawBoard();
            requestMove();
        }
    }

    /**
     * Marca a posição escolhida pelo jogador no tabuleiro.
     * @param row A row da posição escolhida.
     * @param column A column da posição escolhida.
     */
    static void markPosition(int row, int column) {
        if (player % 2 == 0) {
            pos[row][column] = "X";
        } else {
            pos[row][column] = "O";
        }
    }

    /**
     * Verifica se algum jogador ganhou o jogo.
     * @return O número do jogador que ganhou, ou 0 se ninguém ganhou ainda, ou 3 se o jogo terminou em empate.
     */
    static int checkWinner() {
        for (int row = 0; row < 3; row++) {
            if (pos[row][0].equals(pos[row][1]) && pos[row][0].equals(pos[row][2])) {
                return pos[row][0].equals("X") ? 2 : 1;
            }
        }
        for (int column = 0; column < 3; column++) {
            if (pos[0][column].equals(pos[1][column]) && pos[0][column].equals(pos[2][column])) {
                return pos[0][column].equals("X") ? 2 : 1;
            }
        }
        if ((pos[0][0].equals(pos[1][1]) && pos[0][0].equals(pos[2][2])) || (pos[0][2].equals(pos[1][1]) && pos[0][2].equals(pos[2][0]))) {
            return pos[1][1].equals("X") ? 2 : 1;
        }
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (!pos[row][column].equals("X") && !pos[row][column].equals("O")) {
                    return 0;
                }
            }
        }
        return 3;
    }


    /**
     * Exibe o resultado do jogo.
     */
    static void displayResult() {
        if (winner == 1) {
            System.out.println("Player 1 VENCEU!");
        } else if (winner == 2) {
            System.out.println("Player 2 VENCEU!");
        } else {
            System.out.println("EMPATE!");
        }
    }
}
