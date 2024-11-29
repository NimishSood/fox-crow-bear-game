package upei.project;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        print("Fox and Dalaal Game");
        System.out.println("-------New Game---");
        System.out.println("Making a board for you...");
        Board newBoard = new Board();



        // Initializing Players...
        Player player1 = new Player("Adi");
        Player player2 = new Player("Nimish");
        Player player3 = new Player("Harsh");


        // TEST... add player 1 to board[0][0]
        newBoard.board[0][0].AddPlayerToBlock(player1);
        newBoard.board[0][0].AddPlayerToBlock(player2);
        newBoard.board[0][0].AddPlayerToBlock(player3);
        newBoard.printBoard();

    }


    // generic to make debugging easy
    public static <T> void print(T arg1)
    {
        System.out.print(arg1);
    }


}