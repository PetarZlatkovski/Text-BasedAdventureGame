public class Enemy extends Characters{
    //variable to store player xp
    int playerXP;

    //enemy specific constructor
    public Enemy(String name, int playerXP) {
        super(name, (int) (Math.random()*playerXP + playerXP/3 + 5), (int)(Math.random()*(playerXP/4 +2) +1));

        this.playerXP = playerXP;
    }
    //enemy specific attack and defence math
    @Override
    public int attack() {
        return (int) (Math.random()*(playerXP/4 + 1) + xp/4 +3);
    }

    @Override
    public int defense() {
        return (int) (Math.random()*(playerXP/4 + 1) + xp/4 +3);
    }
}
