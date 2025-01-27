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
        this.CPlayerInventory = new Inventory(CFirstCreature, CCreatureGenerator.getCreatures(3));
        this.CCreatureGenerator = CCreatureGenerator;
        this.CScanner = new Scanner(System.in);
        this.CPressEnter = new PressEnter(CScanner);
    }

    /**
     * This method runs the game menu where player can choose to:
     * <p><ul>
     * <li>[1] Check inventory
     * <li>[2] Explore an Area
     * <li>[3] Evolve a creature
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
                                System.out.println("\nYour new active Creature is " + CPlayerInventory.getCreature(CPlayerInventory.getActiveIndex()).getName());
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
                                case 1: CActiveArea = new Area(5, 1, CCreatureGenerator, CPlayerInventory, strPlayerName, 1);
                                        break;
                                
                                case 2: CActiveArea = new Area(3, 3, CCreatureGenerator, CPlayerInventory, strPlayerName, 2);
                                        break;
                                
                                case 3: CActiveArea = new Area(4, 4, CCreatureGenerator, CPlayerInventory, strPlayerName, 3);
                                        break;                    
                            }
                            CActiveArea.movePosition();
                        }
                        break;

                case 3: if(CPlayerInventory.getListSize()<2){   //Evolve Creature
                            System.out.println("You need at least two creatures to evolve!\n");
                        }

                        else{
                            System.out.println("EVOLUTION: Select two Creatures with the same Evolution Level and Family");
                            CPlayerInventory.printInventory(strPlayerName, CPlayerInventory.getCreature(CPlayerInventory.getActiveIndex()));
                            int nIndex1, nIndex2;

                            System.out.print("Enter the inventory number of the first Creature : ");
                            do{
                                try{
                                    nIndex1 = CScanner.nextInt() - 1;
                                    if(nIndex1 < 0 || nIndex1 >= CPlayerInventory.getListSize()){
                                        System.out.print("Enter a valid inventory number : ");
                                    }                                   
                                }
                                catch(java.util.InputMismatchException e){
                                    System.out.print("Invalid input, please enter a valid number : ");
                                    CScanner.next();
                                    nIndex1 = -1;
                                }
                            } while(nIndex1 < 0 || nIndex1 >= CPlayerInventory.getListSize());
                            
                            System.out.print("Enter the inventory number of the second Creature : ");
                            do{
                                try{
                                    nIndex2 = CScanner.nextInt() - 1;
                                    if(nIndex2 < 0 || nIndex2 >= CPlayerInventory.getListSize()){
                                        System.out.print("Enter a valid inventory number : ");
                                    }                                   
                                }
                                catch(java.util.InputMismatchException e){
                                    System.out.print("Invalid input, please enter a valid number : ");
                                    CScanner.next();
                                    nIndex2 = -1;
                                }
                            } while(nIndex2 < 0 || nIndex2 >= CPlayerInventory.getListSize());

                            if(!CPlayerInventory.getCreature(nIndex1).getName().equals(CPlayerInventory.getCreature(nIndex2).getName())){
                                System.out.println("Evolution FAILED : Creatures were not of the same Evolution Level and Family.\n");
                            }

                            else if (nIndex1 == nIndex2){
                                System.out.println("Evolution FAILED : You selected the same creature twice!\n");
                            }

                            else if(CPlayerInventory.getCreature(nIndex1).getEvolutionLevel() == 3){
                                System.out.println("Evolution FAILED : Max level reached, creatures cannot be evolved further.\n");
                            }

                            else{
                                CPlayerInventory.evolveCreature(CPlayerInventory.getCreature(nIndex1), CPlayerInventory.getCreature(nIndex2));
                                CPressEnter.goUno();
                            }
                        }

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
