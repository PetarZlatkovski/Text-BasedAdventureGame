public class Player extends Characters{

    //player specific variables
    public int numAtkUpgrades;
    public int numDefUpgrades;
    int gold, restsLeft, pots;
    //arrays for the upgrades
    public String[] atkUpgrades = {"Adventurer", "Hero", "Legend", "God"};
    public String[] defUpgrades = {"OakSkin", "StoneFlesh", "IronLike", "DragonScaled"};


    //player constructor
    public Player (String name){
        super(name, 100, 0);

        this.numAtkUpgrades = 0;
        this.numDefUpgrades = 0;


        this.gold = 15;
        this.restsLeft = 1;
        this.pots = 0;
        chooseTrait();
    }
    //overrides for the methods in character class
    @Override
    public int attack() {
        return (int) (Math.random()*(xp/4 + numAtkUpgrades*3 + 3) + numAtkUpgrades*2 + numDefUpgrades +1);
    }

    @Override
    public int defense() {
        return (int) (Math.random()*(xp/4 + numDefUpgrades*3 + 3) + numDefUpgrades*2 + numAtkUpgrades +1);
    }
    //method for choosing upgrades at beginning of game and end of levels
    public void chooseTrait(){
        Logic.clearConsole();
        Logic.printHeading("Choose an ATK or DEF trait:");
        System.out.println("(1) ATK " + atkUpgrades[numAtkUpgrades]);
        System.out.println("(2) DEF " + defUpgrades[numAtkUpgrades]);

        int input = Logic.readInput("->", 2);
        Logic.clearConsole();

        if(input == 1) {
            Logic.printHeading("You chose " + atkUpgrades[numAtkUpgrades] + "!");
            numAtkUpgrades++;
        }else if (input == 2){
            Logic.printHeading("You chose " + defUpgrades[numDefUpgrades] + "!");
            numDefUpgrades++;
        }
        Logic.anythingToContinue();
    }
}
