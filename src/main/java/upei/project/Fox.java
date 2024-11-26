package upei.project;

public class Fox extends Enemy {

    public Fox(String name) {
        super(name);
    }

    @Override
    public void Shout() {
            Debug.print("Woof Woof🦊🦊🦊🦊!");
    }
}
