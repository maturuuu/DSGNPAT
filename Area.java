import java.util.Scanner;
import java.util.SplittableRandom;

/**
 * This class is used for displaying map area, performing exploration of map area, and starting of battle phase based on chance.
 */
public class Area {

    Scanner scanner = new Scanner(System.in);
    private char[][] cArray;
    private int nXPos;
    private int nYPos;
    private BattlePhase CBattle;

    //for battle phase
    private String strPlayerName;
    private CreatureGenerator CCreatureGenerator;
    private Inventory CPlayerInventory;

    /**
     * This constructor accepts map dimensions X and Y, creature generator, player inventory and name, and initializes player starting position.
     * 
     * @param nX  integer value for map x dimension (width).
     * @param nY  integer value for map y dimension (heigth).
     * @param CCreatureGenerator CreatureGenerator object for random Creature.
     * @param CPlayerInventory Inventory object for player Creatures.
     * @param strPlayerName String value for player name.
     */
    public Area(int nX, int nY, CreatureGenerator CCreatureGenerator, Inventory CPlayerInventory, String strPlayerName) {
        this.cArray = new char[nY][nX];
        this.nXPos = 0;
        this.nYPos = 0;
        this.CCreatureGenerator = CCreatureGenerator;
        this.CPlayerInventory = CPlayerInventory;
        this.strPlayerName = strPlayerName;
    }

    /**
     * This method generates the map diplay based on x and y dimensions.
     */
    public void displayMap(){

        for(int i = 0; i < cArray.length; i++){
            System.out.println("");
            for(int p = 0; p < cArray[i].length; p++){
                System.out.printf("==========");
            }
            System.out.printf("=\n");
            for(int k = 0; k < 5; k++){
                for(int j = 0; j < cArray[i].length; j++ ){
                    if(k != 2){
                        System.out.printf("=         ");
                    } else {
                        if (nXPos == j && nYPos ==i){
                            System.out.printf("=    %c    ", 'X');
                        } else {
                            System.out.printf("=    %c    ", ' ');
                        }
                        
                    }
                }
                System.out.printf("=\n");
            }
        }
        for(int p = 0; p < cArray[0].length; p++){
            System.out.printf("==========");
        }
        System.out.printf("=\n");

    }
    
    /**
     * This method displays player choice to move and exit. Gets player choice.
     * 
     * @return  integer value of player choice.
     */
    public int getChoice(){

        System.out.println("What do you want to do?");
        System.out.println("[1] Move Left");
        System.out.println("[2] Move Right");
        System.out.println("[3] Exit");
        System.out.print("\nchoice: ");

        int choice = 0;
        boolean validInput = false;

        do {
            try {
                String lineChoice = scanner.nextLine();
                choice = Integer.parseInt(lineChoice);
                
                if (choice >= 1 && choice <= 3) {
                    validInput = true;
                } else {
                    System.out.print("Enter a valid number: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input, please enter a valid number: ");
            }
        } while (!validInput);

        System.out.println("");

        return choice;
    } 

    /**
     * This method changes display based on player choice and performs chance event of <code>BattlePhase<\code>.
     */
    public void movePosition(){
        displayMap();
        int choice = getChoice();
        if (choice == 1 || choice == 2) {
            if (choice == 1) {
                this.nXPos -= 1;
                if (this.nXPos == -1){
                   System.out.println("You are not allowed to explore this area of the map.\n");
                    this.nXPos = 0;
                    movePosition();
                }
            }
            else if (choice == 2) {
                this.nXPos += 1;
                if (this.nXPos == 5){
                    System.out.println("You are not allowed to explore this area of the map.\n");
                    this.nXPos -= 1;
                   movePosition();                   
                }
            }

            boolean isFight = false;

            boolean encounterChance[] = {true, true, false, false, false};

            SplittableRandom random = new SplittableRandom();

            isFight = encounterChance[random.nextInt(0,5)];

            if (isFight){
                System.out.println("///////////////////////////////////////");
                System.out.println("\n              BATTLE!              \n");
                System.out.println("///////////////////////////////////////");
                this.CBattle = new BattlePhase(CCreatureGenerator, CPlayerInventory, strPlayerName);

                CBattle.fight();
            }

            movePosition();
        } else if (choice == 3) {
        System.out.println("Returning to main menu...");   

        } else {
            System.out.println("Invalid choice\n");
            movePosition();
        }
    }
}

