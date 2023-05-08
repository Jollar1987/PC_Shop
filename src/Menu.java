import java.util.ArrayList;

public class Menu {
    /**
     * Method to handel MainMenu
     */
    public static void main() throws Exception {
        CU.consoleEmptyLine();
        System.out.print("""
                \u001B[32m-----------------------------------------------
                \u001B[34mPC-Shop\u001B[31m Hauptmenü\u001B[0m von: [Ihr Vor - und Nachname]
                \u001B[32m-----------------------------------------------\u001B[0m
                1) Produkt anlegen
                2) Produkt bearbeiten
                3) Produkt suchen
                4) Produkt löschen
                0) Shop beenden
                \u001B[32m-----------------------------------------------\u001B[0m
                Bitte wählen:""");
        String input = CU.sc.nextLine().toLowerCase();
        switch (input) {
            case "1", "produkt anlegen","anlegen": Main.produktAnlegen();break;
            case "2", "produkt bearbeiten","bearbeiten": break;
            case "3", "produkt suchen","suchen": Main.produktSuchen(); break;
            case "4", "produkt löschen","löschen": break;
            case "0", "shop beenden","beenden": Main.main(null); break;
            default:
                CU.consoleEmptyLine(1);
                System.out.println("""
                \u001B[31m--------------------
                Fehlerhafte Eingabe!
                --------------------""");
                CU.pause(700);
                main();
                break;
        }
    }

    /**
     * Method to handle Create Menu
     * @throws InterruptedException
     */
    public static void create() throws Exception {
        Product product;
        CU.consoleEmptyLine();
        System.out.print("""
                \u001B[32m-----------------------------------------------
                \u001B[34mPC-Shop\u001B[31m\t\tProdukt anlegen
                \u001B[32m-----------------------------------------------\u001B[0m
                1) Monitor
                2) Mainboard
                3) Tastatur
                4) Maus
                0) zurück zum Hauptmenü
                \u001B[32m-----------------------------------------------\u001B[0m
                Bitte wählen:""");
        String input = CU.sc.nextLine().toLowerCase();
        switch (input){
            case"1", "monitor":
                product = new ProductMonitor();
                CU.pause(1000);
                Main.products.add(Function.create(product));
                Menu.main();
                break;
            case"2", "mainboard":
                product = new ProductMotherboard();
                CU.pause(1000);
                Function.create(product);
                break;
            case"3", "tastatur":
                product = new ProductKeyboard();
                CU.pause(1000);
                Function.create(product);
                break;
            case"4", "maus":
                product = new ProductMouse();
                CU.pause(1000);
                Function.create(product);
                break;
            case"0", "zurück", "hauptmenü": break;
            default:
                CU.consoleEmptyLine(2);
                CU.pause(50);
                System.err.println("""
                --------------------
                Fehlerhafte Eingabe!
                --------------------""");
                CU.pause(700);
                create();
                break;
        }

    }

    /**
     * Method to handle Search Menu
     */
    public static void search(){
        System.out.println( """
                \u001B[32m-----------------------------------------------
                \u001B[34mPC-Shop\u001B[0m\t\tProdukt(e) suchen
                \u001B[32m-----------------------------------------------\u001B[0m
                1) Monitore durchsuchen
                2) Mainboards durchsuchen
                3) Tastaturen durchsuchen
                4) Mäuse durchsuchen
                5) Alles durchsuchen
                0) zurück zum Hauptmenü
                \u001B[32m-----------------------------------------------\u001B[0m
                Bitte wählen:""");
    }
}
