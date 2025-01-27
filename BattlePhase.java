import java.util.Scanner;
import java.util.SplittableRandom;

/**
 * This class is used to do battle where player is asked to choose an action. A random enemy Creature is chosen in this class.
 */
public class BattlePhase {

    SplittableRandom random = new SplittableRandom();
    Scanner CScanner = new Scanner(System.in);
    PressEnter CPressEnter = new PressEnter(CScanner);
    private float nEnemyHealth;
    private String strPlayerName;
    private Creature CPlayerCreature;
    private Creature CEnemyCreature;
    private Inventory CPlayerInventory;

    /**
     * This constructor accepts the creature generator object, the player's Creature inventory, player name, and initializes randomly chosen enemy and its health.
     * 
     * @param CEnemyGenerator  Creature object randomly chosen from generator
     * @param CPlayerInventory  Inventory object for player's Creature list
     * @param strPlayerName  String value for player name
     * @param nAreaLevel    Level of the area
     */
    public BattlePhase(CreatureGenerator CEnemyGenerator, Inventory CPlayerInventory, String strPlayerName, int nAreaLevel){
        nEnemyHealth = 50;
        CEnemyCreature = CEnemyGenerator.randomCreature(CEnemyGenerator.getCreatures(nAreaLevel));
        this.CPlayerCreature = CPlayerInventory.getCreature(CPlayerInventory.getActiveIndex());
        this.CPlayerInventory = CPlayerInventory;
        this.strPlayerName = strPlayerName;
    }

    /**
     * This method displays player and enemy Creature stats. 
     * 
     * @param nWhatToPrint  Integer value signaling which Creature stats to print
     */
    public void printStats(int nWhatToPrint){
        switch(nWhatToPrint){
            default: 

            case 1: System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                    System.out.print(CEnemyCreature.getName());
                    if(nEnemyHealth > 0){
                        System.out.printf(" [%.2fHP]\n", nEnemyHealth);
                    } else {
                        System.out.printf("0");
                    }
                    System.out.println(CEnemyCreature.getType() + " type");
                    System.out.println("Evolution Level " + CEnemyCreature.getEvolutionLevel());
                    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                    System.out.println(CPlayerCreature.getName());
                    System.out.println(CPlayerCreature.getType() + " type");
                    System.out.println("Evolution Level " + CPlayerCreature.getEvolutionLevel());
                    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                    System.out.println("\n");      
                    break;

            case 2: System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                    System.out.print(CEnemyCreature.getName());
                    if(nEnemyHealth > 0){
                        System.out.printf(" [%.2fHP]\n", nEnemyHealth);
                    } else {
                        System.out.printf("0");
                    }
                    System.out.println(CEnemyCreature.getType() + " type");
                    System.out.println("Evolution Level " + CEnemyCreature.getEvolutionLevel());
                    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                    System.out.println("\n");
                    break;  
        }
    }

    /**
     * This method displays player options and gets player choice.
     * @return  integer value for player choice
     */
    public int getChoice(){
        System.out.println("\n=====================================");
        System.out.println("What will " + strPlayerName + " do?");
        System.out.println("[1] " + "(" + CPlayerCreature.getName() + ") Attack!");
        System.out.println("[2] Swap Creature");
        System.out.println("[3] Catch");
        System.out.println("[4] Run Away");
        System.out.print("choice: ");

        int choice = -1;
        if (CScanner.hasNextInt()) {
            choice = CScanner.nextInt();
            if (choice < 1 || choice > 4) {
                System.out.println("Please enter a valid choice (1-4).");
            }
        } 
        else {
            System.out.println("Please enter a valid integer choice (1-4).");
            CScanner.next();
        }
        System.out.println("");

        return choice;
    }

    /**
     * This method analyzes if player Creature has advantage over enemy Creature.
     * 
     * @return  boolean value for if player has advantage
     */
    public boolean isAdvantage(){
        if(CPlayerCreature.getType().equals("Fire") && CEnemyCreature.getType().equals("Grass")
        || CPlayerCreature.getType().equals("Grass") && CEnemyCreature.getType().equals("Water")
        || CPlayerCreature.getType().equals("Water") && CEnemyCreature.getType().equals("Fire")){
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method solves for damage dealt based on chance.
     * 
     * @return  float value of calculated damage
     */
    public float attack(){
        float fDamage;

        if(isAdvantage()){
            fDamage = random.nextInt(1,11) * CPlayerCreature.getEvolutionLevel() * 1.5f;
        } else {
            fDamage = random.nextInt(1,11) * CPlayerCreature.getEvolutionLevel();
        }

        return fDamage;
    }

    /**
     * This method analyzes if enemy Creature is caught based on chance.
     * 
     * @return  boolean value for if player caught the enemy Creature
     */
    public boolean catchEnemy(){

        float catchChance = (40 + 50 - nEnemyHealth)/100;
        double r = random.nextDouble();

        if(r < catchChance){
            CPlayerInventory.addCreature(CEnemyCreature);
            System.out.println("You caught the wild " + CEnemyCreature.getName() + "!\n");
            return true;
        } 
        else{
            System.out.println("You weren't able to catch the " + CEnemyCreature.getName() + "...");
            return false;
        }
    }

    /**
     * This method returns true if used to represent that player has ran away.
     * 
     * @return  boolean value true for when player chooses to run away.
     */
    public boolean runAway(){
        return true;
    }

    /**
     * This method swaps the current Creature with another Creature that is chosen by the player.
     *  
     * @param strPlayerName  String value for player name
     * @param CPlayerCreature  Creature object for storing the Creature the player swapped for
     */
    public void swapCreature(String strPlayerName, Creature CPlayerCreature){
        CPlayerInventory.printInventory(strPlayerName, CPlayerCreature);        //prints inventory w/ creature numbers
        System.out.print("[SWAP] Select a Creature from your inventory : ");

        int nIndex;
        do{
            try{
                nIndex = CScanner.nextInt() - 1;
                if(nIndex < 0 || nIndex >= CPlayerInventory.getListSize()){
                    System.out.print("Enter a valid inventory number : ");
                }
            }
            catch(java.util.InputMismatchException e){
                System.out.print("Invalid input, please enter a valid inventory number : ");
                CScanner.next();
                nIndex = -1;
            }
            
        } while(nIndex < 0 || nIndex >= CPlayerInventory.getListSize());
        
        CPlayerInventory.setActiveCreature(CPlayerInventory.getCreature(nIndex));
        this.CPlayerCreature = CPlayerInventory.getCreature(CPlayerInventory.getActiveIndex());
        System.out.println("\nYour new active Creature is " + CPlayerInventory.getCreature(CPlayerInventory.getActiveIndex()).getName());
        System.out.println("");
    }

    /**
     * This method runs the fight event of the game. Player is given 3 turns and consumes 1 turn per action made. Fight is over when enemy Creature
     * faints, player runs away, or player runs out of turns.
     * <p>
     * Player has 4 options:
     * <p><ul>
     * <li>[1] Attack enemy
     * <li>[2] Swap Creature
     * <li>[3] Catch enemy Creature
     * <li>[4] Run away
     * </ul><p>
     */
    public void fight(){
        int nTurn = 1;
        boolean bIsOver = false;
        System.out.println("\nA wild "+ CEnemyCreature.getName() + " appeared!");
        printStats(1);
        CPressEnter.goUno();

        while (nTurn <= 3 && bIsOver == false){
            System.out.print("TURN <" + nTurn + ">");
            int nChoice = getChoice();
            if (nChoice == 1){
                float fDamage = attack();
                nEnemyHealth -= fDamage;
                if (nEnemyHealth <= 0){
                    System.out.print("You dealt " + fDamage + " damage.");
                    if(isAdvantage()){
                        System.out.print(" It was SUPER EFFECTIVE!");
                    }
                    System.out.println("");
                    System.out.println("The wild " + CEnemyCreature.getName() + " fainted!\n");
                    bIsOver = true;
                }
                else{
                    System.out.printf("You dealt " + fDamage + " damage.");
                    if(isAdvantage()){
                        System.out.print(" It was SUPER EFFECTIVE!");
                    }
                    System.out.println("");
                    printStats(2);
                    nTurn++; 
                }
            } 
            else if (nChoice == 2){
                
                if(CPlayerInventory.getListSize() == 1){
                    System.out.println("You only have one Creature!\n");
                }
                else{
                    swapCreature(strPlayerName, CPlayerCreature);
                    nTurn++; 
                }
            } 
            else if (nChoice == 3){
                bIsOver = catchEnemy();
                nTurn++; 
            } 
            else if (nChoice == 4){
                bIsOver = runAway(); 
                System.out.println("You ran away.\n");
            }  
            else {
                System.out.println("Invalid choice, please pick again.");
            }
        }
        if(bIsOver == false){
            System.out.println("The wild " + CEnemyCreature.getName() + " ran away...\n");
            CPressEnter.goTwo();
        }
        else{
            CPressEnter.goTwo();
        }
    }
}
