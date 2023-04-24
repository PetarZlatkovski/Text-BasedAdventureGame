public class Story {

    public static void printIntro(Player player){
        Logic.clearConsole();
        Logic.printSeperator(30);
        System.out.println("Story");
        Logic.printSeperator(30);
        System.out.println("You are " + player.name + ", and you have come to Hel to kill The Last King and save your brother.");
        Logic.anythingToContinue();
    }
    public static void printLevelOne(){
        Logic.clearConsole();
        Logic.printSeperator(30);
        System.out.println("Level 1");
        Logic.printSeperator(30);
        System.out.println("You enter Hel and are on the first level, a dark tomb... Who knows what's in here..." +
                "\nYou hear some strange noises making you flinch.\nHowever you still go on regardless of your fear of the unknown...");
        Logic.anythingToContinue();
    }
    public static void printLevelTwo(){
        Logic.clearConsole();
        Logic.printSeperator(30);
        System.out.println("Level 2");
        Logic.printSeperator(30);
        System.out.println("After reaching what appears to be a dead end, you fall down a hole to the second level of the dungeon, The Sewers.\nIt smells horrible, making you gag...");
        Logic.anythingToContinue();
    }
    public static void printLevelThree()
    {
        Logic.clearConsole();
        Logic.printSeperator(30);
        System.out.println("Level 3");
        Logic.printSeperator(30);
        System.out.println("You finally find a ladder leading to the next level, The Maze of Darkness." +
                "\nThe maze is ever changing, never being able to trace your steps back,\nand a powerful darkness dwells here, making your lamp light dimmer...");
        Logic.anythingToContinue();

    }
    public static void printFinalLevel(){
        Logic.clearConsole();
        Logic.printSeperator(30);
        System.out.println("Final Level");
        Logic.printSeperator(30);
        System.out.println("You have made your way through the maze and into the Throne Room.\nNow all that remains is slay The Last King and save your brother...");
        Logic.anythingToContinue();
    }
    public static void printEnding(){
        Logic.clearConsole();
        Logic.printSeperator(30);
        System.out.println("Epilogue");
        Logic.printSeperator(30);
        System.out.println("You have slain the beast and have saved your brother, well done!\nAll that remains now is to go home...");
        Logic.anythingToContinue();
        Logic.clearConsole();
        Logic.printHeading("Thank you for playing!");
    }
}
