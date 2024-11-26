package upei.project;

public abstract class Enemy {
    // name of the enemy
    String name;

    public Enemy(String name){
        this.name = name;
    }

    public abstract void Shout();
}
