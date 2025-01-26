import java.util.Scanner;

/**
 * This class is used for player input formatting.
 */
public class PressEnter {
    Scanner CThisScanner;

    /**
     * This constructor initializes scanner object
     * @param CThisScanner  Scanner object for player input.
     */
    public PressEnter(Scanner CThisScanner){
        this.CThisScanner = CThisScanner;
    }

    /**
     * This method prints a single line after press enter prompt.
     */
    public void goUno(){
        System.out.println("Press Enter to continue...");
        CThisScanner.nextLine();
    }

    /**
     * This method prints two lines after press enter prompt.
     */
    public void goTwo(){
        System.out.println("Press Enter to continue...");
        CThisScanner.nextLine();
        CThisScanner.nextLine();
    }
}
