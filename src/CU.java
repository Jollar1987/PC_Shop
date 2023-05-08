import java.util.Scanner;

/**
 * Class wich contains custom utilities (CU)
 */
public class CU{
    /**
     * Scanner
     */
    public static Scanner sc = new Scanner(System.in);
    /**
     * method for pause
     * @param time - pause-time in milliseconds
     *
     */
    public static void pause(int time) throws InterruptedException {
        Thread.sleep(time);
    }

    /**
     * method to fill console with empty lines
     * @param emptyLines - number of empty Lines
     */
    public static void consoleEmptyLine(int emptyLines){
        for (int i = 0; i<emptyLines; i++){
            System.out.println();
        }
    }

    /**
     * method to fill console with 50 empty lines
     */
    public static void consoleEmptyLine(){
        for (int i = 0; i<50; i++){
            System.out.println();
        }
    }

    /**
     * prints a separatorline to the console
     * @param typ declares typ of line
     *            true - long line
     *            false - short line
     */
    public static void consoleSeparatorLine(boolean typ){
        if (typ) {
            System.out.println("[32m-----------------------------------------------[0m");
        } else {
            System.out.println("[36m----------------------[0m");
        }
    }


    public static boolean isNumeric(String non) {
        if (non == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(non);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
