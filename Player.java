import java.util.Scanner;

/**
 * This class is used for storing player name, player inventory, game Creatures, and player game menu.
 */
public class Player {
    private String strPlayerName;
    private Inventory CPlayerInventory;
    private CreatureGenerator CCreatureGenerator; 
    private Area CActiveArea;
    Scanner CScanner;
    PressEnter CPressEnter;

    /**
     * This constructor accepts data for the player name, adds starter Creature to inventory, and adds all Creatures to the game.
     * 
     * @param strPlayerName  String value for player name
     * @param CFirstCreature  Creature object for first Creature
     * @param CCreatureGenerator  Generator object for all Creatures
     */
    public Player(String strPlayerName, Creature CFirstCreature, CreatureGenerator CCreatureGenerator){
        this.strPlayerName = strPlayerName;
        this.CPlayerInventory = new Inventory(CFirstCreature);
        this.CCreatureGenerator = CCreatureGenerator;
        this.CScanner = new Scanner(System.in);
        this.CPressEnter = new PressEnter(CScanner);
    }

    /**
     * This method runs the game menu where player can choose to:
     * <p><ul>
     * <li>[1] Check inventory
     * <li>[2] Explore an Area
     * <li>[3] Evolve a creature (this feature is still in development)
     * <li>[4] Exit the game.
     * </ul><p>
     */
    public void run(){
        int nMenuSelect = 0;
        int nAreaSelect = 0;
        
        while(nMenuSelect != 4){
            System.out.println("[1] : View Inventory\n[2] : Explore an Area\n[3] : Evolve Creature\n[4] : Exit");
            System.out.print("What will " + strPlayerName + " do? : ");
            
            do{
                try{
                    nMenuSelect = CScanner.nextInt();
                    if(nMenuSelect < 1 || nMenuSelect > 4){
                        System.out.print("Enter a valid number : ");
                    }
                }

                catch(java.util.InputMismatchException e){
                    System.out.print("Invalid input, please enter a valid number : ");
                    CScanner.next();
                    nMenuSelect = 0;
                }
                
            } while(nMenuSelect < 1 || nMenuSelect > 4);
            System.out.println("");

            switch(nMenuSelect){
                case 1: CPlayerInventory.printInventory(strPlayerName, CPlayerInventory.getCreature(CPlayerInventory.getActiveIndex())); //View inventory
                        System.out.print("Do you want to change your active Creature? [1: YES | 2: NO] : ");

                        int nSelect;
                        do{
                            try{
                                nSelect = CScanner.nextInt();
                                if(nSelect != 1 && nSelect != 2){
                                    System.out.print("Enter a valid number : ");
                                }
                            }

                            catch(java.util.InputMismatchException e){
                                System.out.print("Invalid input, please enter a valid number : ");
                                CScanner.next();
                                nSelect = -1;
                            }
                        } while(nSelect != 1 && nSelect != 2);

                        if(nSelect == 1){
                            if(CPlayerInventory.getListSize() == 1){
                                System.out.println("");
                                System.out.println("You only have one Creature!\n");
                                CPressEnter.goTwo();
                            }
                            else{
                                System.out.print("Enter the inventory number of your desired active Creature : ");
                            
                                int nIndex;
                                do{
                                    nIndex = CScanner.nextInt() - 1;
                                    if(nIndex < 0 || nIndex >= CPlayerInventory.getListSize()){
                                        System.out.print("Enter a valid inventory number : ");
                                    }
                                } while(nIndex < 0 || nIndex >= CPlayerInventory.getListSize());

                                CPlayerInventory.setActiveCreature(CPlayerInventory.getCreature(nIndex));
                                System.out.println("\nYour new active creature is " + CPlayerInventory.getCreature(CPlayerInventory.getActiveIndex()).getName());
                                System.out.println("");
                                CPressEnter.goTwo();
                            }
                        }
                        else{
                            System.out.println("");
                        }
                        break;

                case 2: System.out.println("[1] : Area 1\n[2] : Area 2\n[3] : Area 3\n[4] : Return to menu"); // Explore an area + battle phase
                        System.out.print("Which area would you like to explore? : ");
                        
                        do{
                            try{
                                nAreaSelect = CScanner.nextInt();
                                if(nAreaSelect < 1 || nAreaSelect > 4){
                                    System.out.print("Enter a valid number : ");
                                }
                            }

                            catch(java.util.InputMismatchException e){
                                System.out.print("Invalid input, please enter a valid number : ");
                                CScanner.next();
                                nAreaSelect = 0;
                            }
                        } while(nAreaSelect < 1 || nAreaSelect > 4);
                        
                        System.out.println("");
                        
                        if(nAreaSelect != 4){
                            switch(nAreaSelect){
                                case 1: CActiveArea = new Area(5, 1, CCreatureGenerator, CPlayerInventory, strPlayerName);
                                        break;
                                
                                case 2: System.out.println("This area is [UNDER CONSTRUCTION], bringing you to Area 1...\n");
                                        CActiveArea = new Area(5, 1, CCreatureGenerator, CPlayerInventory, strPlayerName);
                                        break;
                                
                                case 3: System.out.println("This area is [UNDER CONSTRUCTION], bringing you to Area 1...\n");
                                        CActiveArea = new Area(5, 1, CCreatureGenerator, CPlayerInventory, strPlayerName);
                                        break;                    
                            }
                            CActiveArea.movePosition();
                        }
                        break;

                case 3: System.out.println("This feature is [UNDER CONSTRUCTION], please return later!\n");     //Evolve Creature
                        break;

                case 4: System.out.print("Are you sure you want to exit? [1: YES | 2: CANCEL] : "); //Exit
                        if(CScanner.nextInt() == 2){
                            nMenuSelect = 0;
                        }
                        break;
            }
        }

        CScanner.close();
    }
}
