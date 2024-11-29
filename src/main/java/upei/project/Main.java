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
        newBoard.printBoard();

    }


    // generic to make debugging easy
    public static <T> void print(T arg1)
    {
        System.out.print(arg1);
    }


}