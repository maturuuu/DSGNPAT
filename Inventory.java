import java.util.ArrayList;

/**
 * This class is used for storing the player's <code>Creature</code> Creature objects.
 */
public class Inventory {
    private ArrayList<Creature> aCreatureList = new ArrayList<Creature>();      //generates an arraylist to store the creatures
    private Creature[] aAllCreatures;                                           //a copy of all creatures

    /**
     * This constructor initializes the player active creature
     * 
     * @param CActiveCreature  Creature object of player's active Creature
     */
    public Inventory(Creature CActiveCreature, Creature[] aAllCreatures){     //constructor
        addCreature(CActiveCreature);
        aCreatureList.get(0).setIsActive(1);
        this.aAllCreatures = aAllCreatures;
    }

    /**
     * This method adds a new <code>Creature</code> Creature to the inventory.
     * 
     * @param CCreature  Creature object for Creature to be added.
     */
    public void addCreature(Creature CCreature){
        Creature newCreature = new Creature(CCreature.getName(), CCreature.getType(), CCreature.getFamily(), CCreature.getEvolutionLevel(), CCreature.getFilename());
        aCreatureList.add(newCreature);
    }

    /**
     * This method removes a <code>Creature</code> from the inventory.
     * 
     * @param CCreature  Creature object to be removed.
     */
    public void removeCreature(Creature CCreature){
        aCreatureList.remove(CCreature);
    }

    /**
     * This method removes two <code>Creatures</code> from the inventory and adds the evolved Creature to the inventory.
     * 
     * @param CCreature1  1st Creature to be evolved.
     * @param CCreature2  2nd Creature to be evolved.
     */
    public void evolveCreature(Creature CCreature1, Creature CCreature2){
        Creature CNewCreature = null;
        for(Creature creature : aAllCreatures){
            if(creature.getEvolutionLevel() == CCreature1.getEvolutionLevel()+1 && creature.getFamily() == CCreature1.getFamily()){
                CNewCreature = creature;
                removeCreature(CCreature1);
                removeCreature(CCreature2);
                aCreatureList.add(CNewCreature);
                if(CCreature1.getIsActive() == 1 || CCreature2.getIsActive() == 1){
                    CNewCreature.setIsActive(1);
                }

                System.out.println("\nEvolution SUCCESS!! " + CNewCreature.getName() + " has been added to your inventory.");
                System.out.println("--------------------------");
                System.out.println("Name: " + CNewCreature.getName());
                System.out.println("Type: " + CNewCreature.getType());
                System.out.println("Family: " + CNewCreature.getFamily());
                System.out.println("Evolution Level: " + CNewCreature.getEvolutionLevel());
                System.out.println("--------------------------\n");
            }
        }
    }


    /**
     * This method displays the player's Creatures.
     * 
     * @param strPlayerName  String value for player name
     * @param CActiveCreature  Creature object for player's active Creature
     */
    public void printInventory(String strPlayerName, Creature CActiveCreature){     //prints all creatures in the inventory
        System.out.println(strPlayerName + "'s Creatures:");
        int y = getActiveIndex()+1;
        System.out.println("Active: " + CActiveCreature.getName() + " [" + y + "]\n"); //prints active creature
        for(int i=0; i<aCreatureList.size(); i++){
            Creature thisCreature = aCreatureList.get(i);
            int x = i+1;
            System.out.println("[" + x + "] --------------");
            System.out.println("Name: " + thisCreature.getName());
            System.out.println("Type: " + thisCreature.getType());
            System.out.println("Family: " + thisCreature.getFamily());
            System.out.println("Evolution Level: " + thisCreature.getEvolutionLevel() + "\n");
        }
    }

    /**
     * This method assigns a new Creature active.
     * 
     * @param CNewActiveCreature  Creature object for new Creature to be assigned active
     */
    public void setActiveCreature(Creature CNewActiveCreature){        //sets current active creature to 0 and new active creature to 1
        aCreatureList.get(getActiveIndex()).setIsActive(0);
        CNewActiveCreature.setIsActive(1);
    }

    /**
     * This method gets the index of the active creature
     * 
     * @return  index of the currently active Creature
     */
    public int getActiveIndex(){        //returns the index of the active creature
        for(int i=0; i<aCreatureList.size(); i++){
            Creature thisCreature = aCreatureList.get(i);
            if(thisCreature.getIsActive() == 1){
                return i;
            }
        }
        return 0;
    }

    /**
     * This method gets a creature from inventory.
     * 
     * @param nIndex  integer value for index of a Creature from array
     * @return  Creature object for Creature chosen
     */
    public Creature getCreature(int nIndex){
        return aCreatureList.get(nIndex);
    }

    /**
     * This method gets the number of Creatures a player has
     * 
     * @return integer value for inventory size
     */
    public int getListSize(){
        return aCreatureList.size();
    }

    
}
