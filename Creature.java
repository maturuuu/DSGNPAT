/**
 * This class is used for containing a Creature <code>creature</code> name, type, family, evolution level, and active status.
 */
public class Creature {
    private String strName;
    private String strType;
    private char chFamily;
    private int nEvolutionLevel;
    private int nIsActive;

    private String strFilename;

    /**
     * This constructor accepts data to initialize creature fields and active status.
     * 
     * @param strName  string value for Creature name
     * @param strType  string value for Creature type
     * @param chFamily  character value for Creature family
     * @param nEvolutionLevel  integer value for Creature evolution level
     * @param strFilename   filename for GUI
     */
    public Creature(String strName, String strType, char chFamily, int nEvolutionLevel, String strFilename){
        this.strName = strName;
        this.strType = strType;
        this.chFamily = chFamily;
        this.nEvolutionLevel = nEvolutionLevel;
        nIsActive = 0;
        this.strFilename = strFilename;
    }

    /**
     * This method provides Creature's name.
     * @return  string value for Creature name
     */
    public String getName(){
        return strName;
    }

    /**
     * This method provides Creature's type.
     * @return  string value for Creature type
     */
    public String getType(){
        return strType;
    }

    /**
     * This method provides Creature's family.
     * @return  char value for Creature family
     */
    public char getFamily(){
        return chFamily;
    }

    /**
     * This method provides Creature's evolution level.
     * @return  integer value for Creature evolution level
     */
    public int getEvolutionLevel(){
        return nEvolutionLevel;
    }

    /**
     * This method provides Creature's active status.
     * @return  integer value for Creature name
     */
    public int getIsActive(){
        return nIsActive;
    }

    /**
     * This method provides Creature's filename.
     * @return  String value for Creature filename
     */
    public String getFilename(){
        return strFilename;
    }

    /**
     * This method changes a Creature's active status
     */
    public void setIsActive(int nIsActive){
        this.nIsActive = nIsActive;
    }
}
