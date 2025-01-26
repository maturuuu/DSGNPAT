import java.util.ArrayList;

/**
 * This class is used for storing the player's <code>Creature</code> Creature objects.
 */
public class Inventory {
    private ArrayList<Creature> aCreatureList = new ArrayList<Creature>();      //generates an arraylist to store the creatures

    /**
     * This constructor initializes the player active creature
     * 
     * @param CActiveCreature  Creature object of player's active Creature
     */
    public Inventory(Creature CActiveCreature){     //constructor
        addCreature(CActiveCreature);
        aCreatureList.get(0).setIsActive(1);
    }

    /**
     * This method adds a new <code>Creature</code> Creature to the inventory.
     * 
     * @param CCreature  Creature object for Creature to be added.
     */
    public void addCreature(Creature CCreature){
        Creature newCreature = new Creature(CCreature.getName(), CCreature.getType(), CCreature.getFamily(), CCreature.getEvolutionLevel());
        aCreatureList.add(newCreature);
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
     * This method gets the number of Creature a player has
     * 
     * @return integer value for the number of Creature
     */
    public int getListSize(){
        return aCreatureList.size();
    }

    
}
