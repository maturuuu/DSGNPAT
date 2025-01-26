import java.util.Scanner;

/** 
 * This class is used for game player initialization of name, starter creature, and runs the game menu.
*/
public class Application {
    private String strPlayerName;
    private Creature CFirstCreature;
    private Creature aAllCreatures[];
    private CreatureGenerator CCreatureGenerator;
    
    /**
     * This constructor initializes the starting pokemon of the player.
     */
    public Application(){
        this.CCreatureGenerator = new CreatureGenerator();
    }

    /**
     * This method runs the game menu.
     * 
     * @param CPlayer  Player object for initialization of name and starting Creature.
     */
    public void runPlayer(Player CPlayer){
        CPlayer.run();
    }

    /**
     * This method displays the game starting screen where their name and starting pokemon are displayed. Leads to the game menu after.
     */
    public void run(){
        Scanner CNameScanner = new Scanner(System.in);      //enter player name
        System.out.print("Enter your name: ");
        strPlayerName = CNameScanner.nextLine();
        System.out.println("");
        System.out.println("Your name is " + strPlayerName + "!");

        CCreatureGenerator.makeCreatures();     //creates Creature instances for all creatures
        aAllCreatures = CCreatureGenerator.getAllCreatures();

        for(int i=0; i<aAllCreatures.length; i++){      //choose starter creature
            int x = i+1;
            System.out.println("[" + x + "] " + aAllCreatures[i].getName());
        }
        System.out.print("Choose your starter creature: ");
        Scanner CStarterScanner = new Scanner(System.in);
        
        int nIndex;
        do{
            try{
                nIndex = CStarterScanner.nextInt()-1;
                if(nIndex < 0 || nIndex >= aAllCreatures.length){
                    System.out.print("Enter a valid Creature number : ");
                }
            }

            catch(java.util.InputMismatchException e){
                System.out.print("Invalid input, please enter a valid Creature number : ");
                CStarterScanner.next();
                nIndex = -1;
            }
        } while(nIndex < 0 || nIndex >= aAllCreatures.length);

        CFirstCreature = aAllCreatures[nIndex];
        System.out.println("\nYou selected " + CFirstCreature.getName() + "!");
        System.out.println("");

        Player CPlayer = new Player(strPlayerName, CFirstCreature, CCreatureGenerator); //create player instance
        runPlayer(CPlayer);     //start the game

        CNameScanner.close();
        CStarterScanner.close();
    }
}
