import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
                Bitte wählen: """);
        String input = CU.sc.nextLine().toLowerCase();
        switch (input) {
            case "1", "produkt anlegen","anlegen": Main.produktAnlegen();break;
            case "2", "produkt bearbeiten","bearbeiten": Main.produktBearbeiten();break;
            case "3", "produkt suchen","suchen": Main.produktSuchen(); break;
            case "4", "produkt löschen","löschen": break;
            case "0", "shop beenden","beenden": System.exit(0); break;
            default:
                errorInput();
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
                Bitte wählen: """);
        String input = CU.sc.nextLine().toLowerCase();
        switch (input){
            case"1", "monitor":
                product = new ProductMonitor();
                CU.pause(1000);
                Products.add(Function.create(product));
                repeatCreateorMainMenu();
                break;
            case"2", "mainboard":
                product = new ProductMotherboard();
                CU.pause(1000);
                Products.add(Function.create(product));
                repeatCreateorMainMenu();
                break;
            case"3", "tastatur":
                product = new ProductKeyboard();
                CU.pause(1000);
                Products.add(Function.create(product));
                repeatCreateorMainMenu();
                break;
            case"4", "maus":
                product = new ProductMouse();
                CU.pause(1000);
                Products.add(Function.create(product));
                repeatCreateorMainMenu();
                break;
            case"0", "zurück", "zurück zum", "zum hauptmenü", "zurück zum hauptmenü" , "zurück hauptmenü": break;
            default:
                errorInput();
                create();
                break;
        }

    }

    /**
     * Method for handling edit product menu
     * @throws Exception
     */
    public static void edit() throws Exception {
        CU.consoleEmptyLine();
        int x = 0;
        String inputString;
        int inputInt = -1;
        System.out.println("""
                \u001B[32m-----------------------------------------------
                \u001B[34mPC-Shop\u001B[31m\t\tProdukt bearbeiten
                \u001B[32m-----------------------------------------------\u001B[0m""");
        for (Product p: Products.get()){
            x++;
            System.out.println(x + ") " + p.getIdentifier(true));
        }
        CU.consoleSeparatorLine(true);
        System.out.print("Bitte wählen(Zahl): ");
        inputString = CU.sc.nextLine();
        try{
            inputInt = Integer.parseInt(inputString);
        }catch (Exception e){
            //nothing to do if fail
        }
        if(inputInt >= 1 && inputInt <= Products.get().size()){
            editProduct(Products.get().get(inputInt), Products.get().get(inputInt).getClass());
        }else{
            errorInput();
            edit();
        }
    }
    public static void editProduct(Product product, Class<? extends Product> aClass) throws Exception {
        CU.consoleEmptyLine();
        CU.consoleSeparatorLine(true);
        System.out.println("Bearbeiten:\n" + product.getIdentifier(false));
        CU.consoleSeparatorLine(true);
        Class<? extends Product> iterator = product.getClass();
        int counter = 0;
        while (iterator != null){
            for (Field field : iterator.getDeclaredFields()) {
                counter++;
                String getterMethodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                Method getterMethodLanguageString = CCS.class.getMethod(getterMethodName);
                Method getterMethodValue = aClass.getMethod(getterMethodName);
                System.out.println(counter + ") " + getterMethodLanguageString.invoke(null) + " - " + getterMethodValue.invoke(product));
            }
            iterator = (Class<? extends Product>) iterator.getSuperclass();
        }
    }
    /**
     * Method to handle Search Menu
     */
    public static void search(){
        System.out.print( """
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
                Bitte wählen: """);
    }

    /**
     * Menu for choice for correction
     * @throws Exception
     */

    public static void correction() throws Exception {
        System.out.print("""
                1) zurück zum Hauptmenü
                2) Fehlerwerte korrigieren
                \u001B[32m-----------------------------------------------\u001B[0m
                Bitte wählen: """);
        String input = CU.sc.nextLine().toLowerCase();
        switch (input){
            case "1", "zurück", "zurück zum", "zum hauptmenü", "zurück zum hauptmenü" , "zurück hauptmenü":main(); break;
            case "2", "korrigieren", "fehlerwerte", "fehlerwerte korrigieren": break;
            default:
                errorInput();
                correction();
        }
    }

    /**
     * Method for throwing failureinfo if input for menuchoice is not available (incorrect)
     * @throws Exception
     */
    public static void  errorInput() throws Exception {
        CU.consoleEmptyLine(1);
        System.out.println("""
                \u001B[31m--------------------
                Fehlerhafte Eingabe!
                --------------------\u001B[0m""");
        CU.pause(700);
    }

    /**
     * Menu for repeating create after successful creation of product
     * @return
     * @throws Exception
     */
    public static void repeatCreateorMainMenu() throws Exception{
        CU.consoleSeparatorLine(true);
        System.out.print("""
                1) Weiteres Produkt hinzufügen
                2) Zurück zum Hauptmenü
                \u001B[32m-----------------------------------------------\u001B[0m
                Bitte wählen: """);
        String input = CU.sc.nextLine().toLowerCase();
        switch (input) {
            case "1", "weiteres", "produkt", "hinzufügen", "weiteres produkt", "produkt hinzufügen", "weiteres hinzufügen":
                create();
            case "2", "zurück", "zurück zum", "zum hauptmenü", "zurück zum hauptmenü" , "zurück hauptmenü":
                Menu.main();
            default:
                errorInput();
                repeatCreateorMainMenu();
        }
    }
}
