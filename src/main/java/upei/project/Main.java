package upei.project;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    private int exampleVar = 0;

    public void setExampleVar(int inVal)
    {
        exampleVar = 0;
    }

    public int getExampleVar()
    {
        return exampleVar;
    }

    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        print("Fox and Dalaal Game");
    }


    // generic to make debugging easy
    public static <T> void print(T arg1) {
        System.out.print(arg1);
    }
}