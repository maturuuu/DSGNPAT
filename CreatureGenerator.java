import java.util.ArrayList;
import java.util.Random;

/**
 * This class is used for generating the list of all Creatures in the game and generating a random Creature.
 */
public class CreatureGenerator {
    private Creature aAllCreatures[];

    /**
     * This constructor initializes all the Creatures in the game.
     */
    public CreatureGenerator(){
        Creature Strawander = new Creature ("Strawander", "Fire", 'A', 1, "samplefilename");
        Creature Chocowool = new Creature ("Chocowool", "Fire", 'B', 1, "samplefilename");
        Creature Parfwit = new Creature ("Parfwit", "Fire", 'C', 1, "samplefilename");
        Creature Brownisaur = new Creature ("Brownisaur", "Grass", 'D', 1, "samplefilename");
        Creature Frubat = new Creature ("Frubat", "Grass", 'E', 1, "samplefilename");
        Creature Malts = new Creature ("Malts", "Grass", 'F', 1, "samplefilename");
        Creature Squirpie = new Creature ("Squirpie", "Water", 'G', 1, "samplefilename");
        Creature Chocolite = new Creature ("Chocolite", "Water", 'H', 1, "samplefilename");
        Creature Oshacone = new Creature ("Oshacone", "Water", 'I', 1, "samplefilename");

        Creature Strawleon = new Creature ("Strawleon", "Fire", 'A', 2, "samplefilename");
        Creature Chocofluff = new Creature ("Chocofluff", "Fire", 'B', 2, "samplefilename");
        Creature Parfure = new Creature ("Parfure", "Fire", 'C', 2, "samplefilename");
        Creature Chocosaur = new Creature ("Chocosaur", "Grass", 'D', 2, "samplefilename");
        Creature Golberry = new Creature ("Golberry", "Grass", 'E', 2, "samplefilename");
        Creature Kirlicake = new Creature ("Kirlicake", "Grass", 'F', 2, "samplefilename");
        Creature Tartortle = new Creature ("Tartortle", "Water", 'G', 2, "samplefilename");
        Creature Chocolish = new Creature ("Chocolish", "Water", 'H', 2, "samplefilename");
        Creature Dewice = new Creature ("Dewice", "Water", 'I', 2, "samplefilename");

        Creature Strawizard = new Creature ("Strawizard", "Fire", 'A', 3, "samplefilename");
        Creature Candaros = new Creature ("Candaros", "Fire", 'B', 3, "samplefilename");
        Creature Parfelure = new Creature ("Parfelure", "Fire", 'C', 3, "samplefilename");
        Creature Fudgasaur = new Creature ("Fudgasaur", "Grass", 'D', 3, "samplefilename");
        Creature Croberry = new Creature ("Croberry", "Grass", 'E', 3, "samplefilename");
        Creature Velvevoir = new Creature ("Velvevoir", "Grass", 'F', 3, "samplefilename");
        Creature Piestoise = new Creature ("Piestoise", "Water", 'G', 3, "samplefilename");
        Creature Icesundae = new Creature ("Icesundae", "Water", 'H', 3, "samplefilename");
        Creature Samurcone = new Creature ("Samurcone", "Water", 'I', 3, "samplefilename");

        aAllCreatures = new Creature[]{Strawander, Chocowool, Parfwit, Brownisaur, Frubat, Malts, Squirpie, Chocolite, Oshacone, 
                                       Strawleon, Chocofluff, Parfure, Chocosaur, Golberry, Kirlicake, Tartortle, Chocolish, Dewice,
                                       Strawizard, Candaros, Parfelure, Fudgasaur, Croberry, Velvevoir, Piestoise, Icesundae, Samurcone};
        }
    

    /**
     * This method gets all creatures of a certain evolution level threshhold.
     * 
     * @param   nEL evolution level threshold
     * @return  array of creatures within the given threshold
     */
    public Creature[] getCreatures(int nEL){
        ArrayList<Creature> aCreatureTemp = new ArrayList<Creature>();
        for(Creature CCreature : aAllCreatures){
            if(CCreature.getEvolutionLevel() <= nEL){
                aCreatureTemp.add(CCreature);
            }
        }
        Creature[] aCreatureReturn = aCreatureTemp.toArray(new Creature[aCreatureTemp.size()]);
        return aCreatureReturn;
    }

    /**
     * This method gets a random Creature.
     * 
     * @return  creature object for random Creature.
     */
    public Creature randomCreature(Creature[] aCreaturesList){
        Random CRandom = new Random();
        return aCreaturesList[CRandom.nextInt(aCreaturesList.length)];
    }
}
