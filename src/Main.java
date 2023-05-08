
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static ArrayList<Product> products = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        CC.setDummyData();
        CC.setConstants();
        products = CC.getDummyData();

        /*
        String test1 = "Hallo,";
        String test2 = null;
        String test3 = "Welt";
        String test4 = test1+ test2 + ","+test3;
        String[] elements = test4.split(",");
        ArrayList<String> testlist = new ArrayList<>(Arrays.asList(elements));
        for (String element: testlist) {
            System.out.println(element);
        }
         */
        //FieldTest.fieldTest();
        //System.out.println(CC.getDummyData().get(0).toString());
        hauptMenue();


        /*
        System.out.println(System.getProperty("user.dir"));
        CC.setConstants();
        System.out.println("EEK");
        for (String x: CC.getEnergyEffClass()){
            System.out.println(x);
        }
        CU.consoleEmptyLine(1);
        System.out.println("MC");
        for (String x: CC.getMiscConnectors()){
            System.out.println(x);
        }
        CU.consoleEmptyLine(1);
        System.out.println("MCI");
        for (String x: CC.getMonitorConnectorInput()){
            System.out.println(x);
        }
        CU.consoleEmptyLine(1);
        System.out.println("MCO");
        for (String x: CC.getMonitorConnectorOutput()){
            System.out.println(x);
        }

         */

    }
    public static void hauptMenue() throws Exception {
        Menu.main();
    }

    public static void produktAnlegen() throws Exception {
        Menu.create();
    }

    public static void produktSuchen() {
        Menu.search();
    }
}