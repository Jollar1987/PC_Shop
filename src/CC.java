import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Custom Constants
 */
public class CC {
    private static ArrayList<String> energyEffClass = new ArrayList<>();
    private static ArrayList<String> monitorConnectorsInput = new ArrayList<>();
    private static ArrayList<String> monitorConnectorsOutput = new ArrayList<>();
    private static ArrayList<String> miscConnectors = new ArrayList<>();
    private static ArrayList<Product> dummyData = new ArrayList<>();


    public static void setConstants() throws FileNotFoundException {
        setEnergyEffClass();
        setMonitorConnectorOutput();
        setMonitorConnectorInput();

    }
    private static void setEnergyEffClass() throws FileNotFoundException {
        Scanner read = new Scanner(new File((System.getProperty("user.dir") + "\\Constants\\EnergieEffizenzklassen.txt")));
        while (read.hasNext()){
            energyEffClass.add(read.nextLine());
        }
    }

    public static ArrayList<String> getEnergyEffClass() {
        return energyEffClass;
    }

    public static ArrayList<String> getMonitorConnectorsInput() {
        return monitorConnectorsInput;
    }

    private static void setMonitorConnectorInput() throws FileNotFoundException {
        Scanner read = new Scanner(new File((System.getProperty("user.dir") + "\\Constants\\MonitorConnectorInput.txt")));
        while (read.hasNext()){
            monitorConnectorsInput.add(read.nextLine());
        }
        for (int i = 0; i < monitorConnectorsInput.size(); i++){
            monitorConnectorsInput.set(i, monitorConnectorsInput.get(i).trim());
        }
    }

    public static ArrayList<String> getMonitorConnectorsOutput() {
        return monitorConnectorsOutput;
    }

    private static void setMonitorConnectorOutput() throws FileNotFoundException {
        Scanner read = new Scanner(new File((System.getProperty("user.dir") + "\\Constants\\MonitorConnectorOutput.txt")));
        while (read.hasNext()){
            monitorConnectorsOutput.add(read.nextLine());
        }
        for (int i = 0; i < monitorConnectorsOutput.size(); i++){
            monitorConnectorsOutput.set(i, monitorConnectorsOutput.get(i).trim());
        }
    }

    public static ArrayList<String> getMiscConnectors() {
        return miscConnectors;
    }

    private static void setMiscConnectors() throws FileNotFoundException {
        Scanner read = new Scanner(new File((System.getProperty("user.dir") + "\\Constants\\MiscConnectors.txt")));
        while (read.hasNext()){
            miscConnectors.add(read.nextLine());
        }
    }

    public static ArrayList<Product> getDummyData() {
        return dummyData;
    }

    public static void setDummyData() {
        dummyData.add(new ProductKeyboard("Model Tastatur", "Tastaturfirma", 19.99, "Deutsch"));
        dummyData.add(new ProductKeyboard("Model Keyboard", "Keyboardcompany", 16.99, "English"));
        dummyData.add(new ProductMonitor("Model Bidschirm", "Bildschirmfirma", 99.99,27));
        dummyData.add(new ProductMonitor("Model Monitor", "Monitorcompany", 129.99,35));
        dummyData.add(new ProductMouse("Model Maus","Mausfirma",15.99,"Optomechanisch"));
        dummyData.add(new ProductMouse("Model Mouse","Mousecompany",5.99,"Darkfield"));
        dummyData.add(new ProductMotherboard("Model Mainboard", "Mainboardfirma",49.99,"ATX"));
        dummyData.add(new ProductMotherboard("Model Motherboard", "Motherboardcompany",69.99,"mATX"));
    }
}
