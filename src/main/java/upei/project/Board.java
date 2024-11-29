package upei.project;

public class Board {
    Block[][] board;

    public Board() {
        this.board = new Block[10][10];

        // populate board with 0s
        for (int i=0; i<this.board.length; i++) {
            for (int j= 0; j<this.board.length; j++) {
                this.board[i][j] = new Block();
            }
        }

        // initialize start and finish line
//        this.board[0][0] = new Block("Start");
//        this.board[this.board.length-1][this.board.length-1] = new Block("Finish");
    }

    public void printBoard() {
        for (int i=0; i<this.board.length; i++) {
            System.out.println();
            for (int j= 0; j<this.board.length; j++) {
                System.out.printf("%s\t",this.board[i][j].property);
            }
        }
    }

}
