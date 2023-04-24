import java.util.Scanner;
public class Logic {
    //public variables that are used throughout the code
    static Scanner scanner = new Scanner(System.in);
    public static Player player;
    public static boolean isGameRunning;
    public static String[] encounters = {"Battle", "Battle", "Battle", "Battle", "Rest", "Shop"};
    public static String[] enemies = {"Goblin", "Slime", "Ghost", "Golem", "Assassin"};
    public static int enemiesKilled = 0;
    public static int dungLevel = 0;
    public static String[] levels = {"","Level 1: The steps to Hel", "Level 2: The Sewers", "Level 3: Maze of Darkness", "Final Level: The Throne Room"};


    //method for player input
    public static int readInput(String prompt, int userInput)   //this int basically says how many options we are allowed i.e. if it is 2 we have 2 options, if it is 3 we have 3 options
    {
        int input;

        do{
            System.out.println(prompt);
            try{
                input = Integer.parseInt((scanner.next()));
            }catch (Exception e){
                input = -1;
                System.out.println("Please enter your choice");
            }

        }while(input < 1 || input > userInput);
        return input;
    }
    //method for clearing the console
    public static void clearConsole() {
        for (int i = 0; i<100; i++)
            System.out.println();
    }
    //method for printing "-" for a certain amount of times
    public static void printSeperator(int n){
        for (int i = 0; i< n; i++)
            System.out.print("-");
        System.out.println();
    }
    //method for printing text with the printSeperator method between
    public static void printHeading(String title){
        printSeperator(30);
        System.out.println(title);
        printSeperator(30);
    }
    //method where any input continues the game
    public static void anythingToContinue(){
        System.out.println("\nEnter anything to continue...");
        scanner.next();
    }
    //method for beginning of game
    public static void startOfGame(){

        String playerName;
        boolean nameSet = false;

        clearConsole();
        printTitle();
        System.out.println("\nText-Based RPG made by Petar Zlatkovski");

        anythingToContinue();
        do{
            clearConsole();
            printHeading("Please Enter your character's name...\n");
            playerName = scanner.next();

            //we ask the player if this is the name they want
            clearConsole();
            printHeading(playerName + " is your name, correct?");
            System.out.println("(1) Yes!\n(2) No, I would like to change my name to...");
            int input = readInput("->", 2);
            if (input == 1)
                nameSet = true;
        }while(!nameSet);

        //creation of new player object
        player = new Player(playerName);

        Story.printIntro(player);

        isGameRunning = true;

        Story.printLevelOne();

        gameLoop();
    }
    //method to check in which level we are
    public static void checkDungeonLevel() {
        if (player.xp <10 && dungLevel == 0){   //if-statement for first level containing enemies in it

            dungLevel = 1;
            enemies[0] = "Ghost";
            enemies[1] = "Socialist";
            enemies[2] = "Sand Monster";
            enemies[3] = "Gremlin";
            enemies[4] = "Assassin";

            encounters[0] = "Battle";
            encounters[1] = "Battle";
            encounters[2] = "Battle";
            encounters[3] = "Rest";
            encounters[4] = "Shop";
        }
        else if (player.xp >= 10 && dungLevel == 1) {   //if-statement for second level
            dungLevel = 2;
            Story.printLevelTwo();
            player.chooseTrait();
            enemies[0] = "Monster";
            enemies[1] = "Wolf";
            enemies[2] = "Football Hooligan";
            enemies[3] = "Goblin";
            enemies[4] = "Unfriendly Giant";

            encounters[0] = "Battle";
            encounters[1] = "Battle";
            encounters[2] = "Battle";
            encounters[3] = "Rest";
            encounters[4] = "Shop";
        } else if (player.xp >= 20 && dungLevel == 2) { //if-statement for third level
            dungLevel = 3;
            Story.printLevelThree();
            player.chooseTrait();
            enemies[0] = "Bulgarian";
            enemies[1] = "Bear";
            enemies[2] = "Stone Golem";
            enemies[3] = "Goblin King";
            enemies[4] = "Georgian";

            encounters[0] = "Battle";
            encounters[1] = "Battle";
            encounters[2] = "Battle";
            encounters[3] = "Rest";
            encounters[4] = "Shop";
        } else if (player.xp >= 40 && dungLevel == 3) { //if-statement for final level
            dungLevel = 4;

            player.chooseTrait();
            player.chooseTrait();

            Story.printFinalLevel();

            //code to fully heal player before final battle
            player.hp = player.maxHP;
            System.out.println("A strange magical force healed all your wounds...");
            printHeading(player.name + "\nHP: " + player.hp + "/" + player.maxHP);

            finalBattle();
        }
    }
    //method for choosing random encounters between fight, rest and shop
    public static void randomEncounter(){
        int encounter = (int) (Math.random()*encounters.length);

        if (encounters[encounter].equals("Battle")) {
            randomBattle();
        }
        else if (encounters[encounter].equals("Rest")) {
            takeRest();
        }else if (encounters[encounter].equals("Shop")){
            shop();
        }
    }
    //method to continue the game loop and when we reach the final level it goes to the final battle
    public static void continueJourney(){
        checkDungeonLevel();

        if (dungLevel != 4)
            randomEncounter();
    }
    //method that prints players info including health, upgrades, xp, name and others
    public static void characterInfo(){
        clearConsole();
        printHeading("CHARACTER INFO");
        System.out.println(player.name + "\tHP: " + player.hp + "/" + player.maxHP);
        printSeperator(30);
        System.out.println("XP: "+ player.xp + "\tGold: " + player.gold);
        printSeperator(30);
        System.out.println("# of Potions: " + player.pots);
        printSeperator(30);
        if(player.numAtkUpgrades > 0)
        {
            System.out.println("Offensive trait: " + player.atkUpgrades[player.numAtkUpgrades - 1]);
            printSeperator(20);
        }
        if (player.numDefUpgrades > 0)
        {
            System.out.println("Defensive traits: " + player.defUpgrades[player.numDefUpgrades - 1]);
            printSeperator(20);


        }
        anythingToContinue();
    }
    //method to print menu
    public static void printMenu(){
        clearConsole();
        printHeading(levels[dungLevel]);
        System.out.println("Choose an action:");
        printSeperator(30);
        System.out.println("(1) Continue");
        System.out.println("(2) Character info");
        System.out.println("(3) Exit Game");
    }
    //method that checks what we input, if it is 1 we continue, if it is 2 we check player info and 3 closes game
    public static void gameLoop()
    {
        while(isGameRunning){
            printMenu();
            int input = readInput("->", 3);
            if (input == 1)
                continueJourney();
            else if (input == 2)
                characterInfo();
            else if (input == 3)
                isGameRunning = false;

        }
    }

    //creating random battle
    public static void randomBattle(){
        clearConsole();
        printHeading("You have encountered an enemy!");
        anythingToContinue();
        //creating random enemy
        battle(new Enemy(enemies[(int)(Math.random()* enemies.length)], player.xp));
    }
    //the battle method
    public static void battle(Enemy enemy){
        while(true){
            clearConsole();
            printHeading(enemy.name + "\nHP: " + enemy.hp + "/" + enemy.maxHP);
            printHeading(player.name + "\nHP: " + player.hp + "/" + player.maxHP);
            printSeperator(20);
            System.out.println("(1) Fight\n(2) Use Potion\n(3) Run Away");
            int input = readInput("->", 1);
            if(input == 1) {
                //Fight

                //calculate dmg and dmgTook
                int dmg = player.attack() - enemy.defense();
                int dmgTook = enemy.attack() - player.defense();
                //in case damage is negative it multiplies by zero for attacking enemies and turns to zero if attacking the player
                if(dmgTook < 0){
                    dmgTook = 0;
                }
                if (dmg < 0){
                    dmg *= -1;
                }
                //deals damage to both player and enemy
                player.hp -= dmgTook;
                enemy.hp -= dmg;
                //overview of battle
                clearConsole();
                printHeading("Battle");
                System.out.println("You dealt " + dmg + " damage to the " + enemy.name + ".");
                printSeperator(30);
                System.out.println(("The " + enemy.name + " dealt " + dmgTook + " damage to you."));
                anythingToContinue();
                //check if battle over

                if(player.hp <= 0){
                    playerDied(); //method to end game
                    break;
                }else if (enemy.hp <= 0){
                    //tell player they won
                    clearConsole();
                    printHeading("You defeated the " + enemy.name + "!");
                    //increases player xp
                    player.xp += enemy.xp;
                    System.out.println("You earned " + enemy.xp + "XP!");
                    enemiesKilled++;
                    boolean addRest = (Math.random()*5 + 1 <= 3.0); //30% chance to earn an extra rest
                    int goldEarned = (int)(Math.random()*enemy.xp); //random chance to earn gold after defeating enemy
                    if(addRest) {
                        player.restsLeft++;
                        printHeading("You earned the chance to rest one more time.");
                    }
                    if(goldEarned > 0){
                        player.gold += goldEarned;
                        System.out.println("You collected " + goldEarned + " gold from the enemy's corpes.");
                    }
                    anythingToContinue();
                    break;
                }
            }else if (input == 2){
                //Potion

                clearConsole();
                if(player.pots > 0 && player.hp < player.maxHP) {
                    //player can heal
                    printHeading("Are you sure you want to drink a potion? (" + player.pots +" left)");
                    System.out.println("(1) Yes\n No");
                    input = readInput("->", 2);
                    if (input == 1){
                        player.hp = player.maxHP;
                        clearConsole();
                        printHeading("You drank a magic potion.\nIt restored your health to full");
                        anythingToContinue();
                    }
                }else{
                    //player can't heal
                    printHeading("you don't have any potions or are fully healed.");
                    anythingToContinue();
                }
            }else if(input == 3) {
                //Run Away
                clearConsole();
                if (dungLevel != 4) {
                    //chance to escape = 50%
                    if (Math.random() * 10 + 1 <= 5.0) {
                        printHeading("You successfully ran away from the enemy");
                        anythingToContinue();
                        break;
                    } else {
                        printHeading("You couldn't run away");
                        int dmgTook = enemy.attack();
                        System.out.println("In your attempt to flee you took " + dmgTook + "damage!");
                        //chekc if player still alive
                        if (player.hp <= 0) {
                            playerDied();
                        }
                    }
                }else{
                    printHeading("'YOU CANNOT ESCAPE ME FOOL!'");
                    anythingToContinue();
                }
            }
        }
    }
    //method that ends game when player hp reaches 0, also tells how much xp they gained and how many monsters they killed
    public static void playerDied(){
        clearConsole();
        printHeading("You died...");
        printHeading("In you attempt to save your brother you earned " + player.xp +"XP and killed " + enemiesKilled +" foes!");
        printHeading("Thank you for playing!\nBetter luck next time");
        isGameRunning = false;
    }
    //method for shop event
    public static void shop(){
        clearConsole();
        int price = 10 + dungLevel*5;   //price depends on level
        printHeading("You encounter a mysterious, but friendly man...\nHe offers to sell you a potion for " + price +" gold.");
        System.out.println("Do you want to buy a potion?\n(1) Yes\n(2) No");
        int input = readInput("->", 2);
        if(input == 1){
            clearConsole();
            if(player.gold >= price){
                printHeading("You bought a magic potion!");
                player.pots++;
                player.gold -= price;
            }else printHeading("You don't have enough to purchase a potion");
            anythingToContinue();
        }
    }
    //method for rest event where player heals a random amount
    public static void takeRest(){
        clearConsole();
        if(player.restsLeft >= 1){
            printHeading("Would you like to take a rest? (" + player.restsLeft + " rest(s) left.");
            System.out.println("(1) Yes\n(2) No");
            int input = readInput("->", 2);
            if (input == 1){
                clearConsole();
                if (player.hp < player.maxHP){
                    int hpRestored = (int) (Math.random() * (player.xp/4 +1) + 10);
                    player.hp += hpRestored;
                    if (player.hp > player.maxHP)
                        player.hp = player.maxHP;
                }else
                    System.out.println("You're at full health. You don't need to rest right now.");
                anythingToContinue();
            }

        }
    }
    //method for final battle
    public static void finalBattle(){
        battle(new Enemy("The Last King", 50));
        Story.printEnding();
        isGameRunning = false;
    }

    //method to print ascii art of title
    public static void printTitle(){
        System.out.println("                                                                                                                                                                                                                                                                                                            ");
        System.out.println("                     .!?7:                       ~??!^        ..                                      ~?~                                                                :                 !YY~                               ::                                                                            ");
        System.out.println("                     5#7J&!                  .^?BB?!G@7   :!JG5.                                     5&J5.                                                            :7G&P~.             ?@J!~                .^^:.        ^5&&Y^                                                                          ");
        System.out.println("                     .:  J@5?!~^^:::^^~!7J5GB&@@G   :^ :Y#@@@@^                                      5@?:       .:.                                                  ^B@@@@@7             ^GBJ~^:::.::~!:     J&@&5!!.     :G@@@@P.                                                                         ");
        System.out.println("                         ?@@@@@&BB&@@@@@@@@@@@@@?       ~@@@@#.                                       ^5&#BGGGGB#5.                                                   ~@@@@J                :Y@@&&&&&@@?      J&@@G Y?       !GP~                                                                           ");
        System.out.println("                        .#&P?!^:. !@@@@@J::^!JG@Y       :&@@@#.                   :                     !@@@@@@@G                      .                  ~!.        :?@@@@Y~!!:              ?@@@@@@@J        .^: ~&7                    :^                  ~~                                            ");
        System.out.println("                        ^!.        B@@@J       :!.      :&@@@&.  ~J?~.         .!P@B!.                   J@@@@@@~                   .7G#?.   .^        ^JB@@#?^.:~. ~YG@@@@B55J:               5@@@@@@^         :7G&5.     .^?GB?:   :~?PB&7 ^YG5!.        :?B@@G! !G7.                                     ");
        System.out.println("                                  .#@@@~                :&@@@&7JB&@@@&5^     ^J#@##@@#J^                 .B@@@@G                  ~5&@#@@#PJY&?     .7G@@&YP@@@#5!    ^@@@@7                   .#@@@@@^    .^75GG57:       7&@@@@G: .Y@@@@@P#@@@@@#7     ~5&@G5#@@B&@@G                                     ");
        System.out.println("                                  :&@@@^                :&@@@#!~^:~#@@@&7  !G@@@B..?B@@@G?.               7@@@@Y               .?B@@@! !P@@@@@!    Y&@@@&:  7BY^      ~@@@@?                    J@@@@@Y7?JYG&@@&P?^         5@@@&^   ^@@@@&?!^:Y@@@@Y  ~B@@@#  .^Y@@@@P                                     ");
        System.out.println("                                  ^@@@@~                :&@@@B     !@@@@@^ G@@@@G  :7#BY!.                :&@@@J              .!@@@@&:  :@@@@@~    G@@@@&7~YPYY5~     ~@@@@?                    ~@@@@@G^:. .:7G@@@@B!       Y@@@&.   ~@@@@B     B@@@G  Y@@@@B    ^@@@@Y                                     ");
        System.out.println("                                  !@@@@?                :&@@@B.     #@@@@? P@@@@#YP5?^                    .#@@@P            :57~@@@@&:  ^@@@@@^     ~Y#@@#GB&@@@@G7.  ~@@@@7                    !@@@@@#        ?@@@@B       Y@@@&.   ~@@@@B     B@@@G  J@@@@B    ~@@@@7                                     ");
        System.out.println("                                  ?@@@@G                ^@@@@#.     G@@@@~ 5@@@@&!.    ..                 ^@@@@&.       :~?P&&.!@@@@&:  ^@@@@@~      .5G^   .P@@@@B^  ^@@@@?                    P@@@@@@~       .#@@@Y       Y@@@&.   ^@@@@B     B@@@G  Y@@@@B    ~@@@@^                                     ");
        System.out.println("                                 .B@@@@@~              :G@@@@&.     G@@@5  ?@@@@@7.^!JYJ!                 P@@@@@Y~~!7YPB&@@@@5 ~@@@@@? .7@@@@@J.:: ^5&@B7^^^7#@&P!    !@@@@#!:.                !@@@@@@@Y       !@@@#: :G#G^ 5@@@&^  .P@@@@B    :#@@@B: !@@@@@7.^!5@@@@!                                     ");
        System.out.println("                              .:^G@@@@@@G              :7JP#@@Y    .&@&?    ~B@@@@G5?~:               :!75@&&###&&@@@@@@@@@@@?  ~B@@@@P5?!P@@@@GYJ5#@@@@@@@@@B?:      ?#@@@@&5!            .^!7#@&@@@@@#.      Y@@@B.  J@@~:P@@@@#~ ^YP#@@@!   ^P&@@@G: ~B@@@@PY7:5@@@P                                     ");
        System.out.println("                            7P5J??7??J5PGYY?~.             .^7Y~   ~G?:       ?57^.                  5&J!^::.....::^~!7?YPB&@7    ?5?~.    7PJ~..~:..^!J5BBY^          .7PY!.             !&P7~^:::^~7?YJ!^.   .YB&@BJ77?:   :JP7:     .:!J5:    :JJ^.   :?J!:     G@@G                                     ");
        System.out.println("                            5@JJ.       ^?7#@5                     .                                 ?GYJ                  .PG:?B.                                                        ^G5Y~           .       ::^:.                                ^P&B57^.   ^#@@!                                     ");
        System.out.println("                             :~^        ^??J7:                                                         .                    .5GP?                                                           ..                                                        ~B&&@@@@&#GG&#5^                                      ");
    }
}
