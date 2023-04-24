public abstract class Characters {
    //Variables
    public String name;
    public int maxHP, hp, xp;

    //Constructor
    public Characters(String name, int maxHP, int xp){
        this.name = name;
        this.maxHP = maxHP;
        this.xp = xp;
        this.hp = maxHP;
    }
    //methods that are overriden in enemy and player classes

    public abstract int attack();
    public abstract int defense();

}
