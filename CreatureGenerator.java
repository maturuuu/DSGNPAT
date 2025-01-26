import java.util.Random;

/**
 * This class is used for generating the list of all Creatures in the game and generating a random Creature.
 */
public class CreatureGenerator {
    private Creature aAllCreatures[];

    /**
     * This method initializes all the Creatures in the game.
     */
    public void makeCreatures(){
        Creature Strawander = new Creature ("Strawander", "Fire", 'A', 1);
        Creature Chocowool = new Creature ("Chocowool", "Fire", 'B', 1);
        Creature Parfwit = new Creature ("Parfwit", "Fire", 'C', 1);
        Creature Brownisaur = new Creature ("Brownisaur", "Grass", 'D', 1);
        Creature Frubat = new Creature ("Frubat", "Grass", 'E', 1);
        Creature Malts = new Creature ("Malts", "Grass", 'F', 1);
        Creature Squirpie = new Creature ("Squirpie", "Water", 'G', 1);
        Creature Chocolite = new Creature ("Chocolite", "Water", 'H', 1);
        Creature Oshacone = new Creature ("Oshacone", "Water", 'I', 1);
        aAllCreatures = new Creature[]{Strawander, Chocowool, Parfwit, Brownisaur, Frubat, Malts, Squirpie, Chocolite, Oshacone};
    }

    /**
     * This method retrieves all Creature.
     * 
     * @return  array value for all Creature.
     */
    public Creature[] getAllCreatures(){
        return aAllCreatures;
    }

    /**
     * This method gets a random Creature.
     * 
     * @return  creature object for random Creature.
     */
    public Creature randomCreature(){
        Random CRandom = new Random();
        return aAllCreatures[CRandom.nextInt(aAllCreatures.length)];
    }
}
