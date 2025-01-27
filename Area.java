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

    // for battle phase
    private String strPlayerName;
    private CreatureGenerator CCreatureGenerator;
    private Inventory CPlayerInventory;
    private int nAreaLevel;

    /**
     * This constructor accepts map dimensions X and Y, creature generator, player inventory and name, and initializes player starting position.
     *
     * @param nX                integer value for map x dimension (width).
     * @param nY                integer value for map y dimension (height).
     * @param CCreatureGenerator CreatureGenerator object for random Creature.
     * @param CPlayerInventory  Inventory object for player Creatures.
     * @param strPlayerName     String value for player name.
     * @param nAreaLevel        integer value for area level
     */
    public Area(int nX, int nY, CreatureGenerator CCreatureGenerator, Inventory CPlayerInventory, String strPlayerName, int nAreaLevel) {
        this.cArray = new char[nY][nX];
        this.nXPos = 0;
        this.nYPos = 0;
        this.CCreatureGenerator = CCreatureGenerator;
        this.CPlayerInventory = CPlayerInventory;
        this.strPlayerName = strPlayerName;
        this.nAreaLevel = nAreaLevel;
    }

    /**
     * This method generates the map display based on x and y dimensions.
     */
    public void displayMap() {
        for (int i = 0; i < cArray.length; i++) {
            System.out.println("");
            for (int p = 0; p < cArray[i].length; p++) {
                System.out.printf("==========");
            }
            System.out.printf("=\n");
            for (int k = 0; k < 5; k++) {
                for (int j = 0; j < cArray[i].length; j++) {
                    if (k != 2) {
                        System.out.printf("=         ");
                    } else {
                        if (nXPos == j && nYPos == i) {
                            System.out.printf("=    %c    ", 'X');
                        } else {
                            System.out.printf("=    %c    ", ' ');
                        }

                    }
                }
                System.out.printf("=\n");
            }
        }
        for (int p = 0; p < cArray[0].length; p++) {
            System.out.printf("==========");
        }
        System.out.printf("=\n");

    }

    /**
     * This method displays player choice to move and exit. Gets player choice.
     *
     * @return integer value of player choice.
     */
    public int getChoice() {
        System.out.println("What do you want to do?");
        System.out.println("[1] Move Left");
        System.out.println("[2] Move Right");
        System.out.println("[3] Move Up");
        System.out.println("[4] Move Down");
        System.out.println("[5] Exit");
        System.out.print("\nchoice: ");

        int choice = 0;
        boolean validInput = false;

        do {
            try {
                String lineChoice = scanner.nextLine();
                choice = Integer.parseInt(lineChoice);

                if (choice >= 1 && choice <= 5) {
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
    public void movePosition() {
    displayMap();
    int choice = getChoice();
    if (choice >= 1 && choice <= 4) {
        int newXPos = nXPos;
        int newYPos = nYPos;

        switch (choice) {
            case 1:
                newXPos -= 1;
                break;
            case 2:
                newXPos += 1;
                break;
            case 3:
                newYPos -= 1;
                break;
            case 4:
                newYPos += 1;
                break;
        }

        if (newXPos >= 0 && newXPos < cArray[0].length && newYPos >= 0 && newYPos < cArray.length) {
            this.nXPos = newXPos;
            this.nYPos = newYPos;

            boolean isFight = false;

            boolean encounterChance[] = {true, true, false, false, false};

            SplittableRandom random = new SplittableRandom();

            isFight = encounterChance[random.nextInt(0, 5)];

            if (isFight) {
                System.out.println("///////////////////////////////////////");
                System.out.println("\n              BATTLE!              \n");
                System.out.println("///////////////////////////////////////");
                this.CBattle = new BattlePhase(CCreatureGenerator, CPlayerInventory, strPlayerName, nAreaLevel);

                CBattle.fight();
            }
        } else {
            System.out.println("You are not allowed to explore this area of the map.\n");
        }

        movePosition();
    } else if (choice == 5) {
        System.out.println("Returning to the main menu...");

    } else {
        System.out.println("Invalid choice\n");
        movePosition();
    }
}
}

