import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Subclass
 */
public class ProductMonitor extends Product{
    private double screenDiagonal;
    private ArrayList<String> monitorConnectorsOutput = new ArrayList<>();
    public ProductMonitor(String modell, String brand, double price, double sizeDiagonal) {
        super(modell, brand, price);
        setScreenDiagonal(sizeDiagonal);
    }
    public ProductMonitor(){

    }
    public ArrayList<String> getMonitorConnectorsOutput() {
        return monitorConnectorsOutput;
    }
    public void setMonitorConnectorsOutput(String monitorConnectorsOutput) {
        String[] elements = monitorConnectorsOutput.split(",");
        this.monitorConnectorsOutput = new ArrayList<>(Arrays.asList(elements));
    }
    public double getScreenDiagonal() {
        return screenDiagonal;
    }
    public String getTyp(){
        return "Monitor";
    }
    public void setScreenDiagonal(double sizeDiagonal) {
        this.screenDiagonal = sizeDiagonal;

    }
    public void setScreenDiagonal(String screenDiagonal) {
        if(CU.isNumeric(screenDiagonal)){
            if (Double.parseDouble(screenDiagonal)>0){
                this.screenDiagonal = Double.parseDouble(screenDiagonal);
            }
            else{
                this.screenDiagonal = -1;
            }
        }
        else{
            this.screenDiagonal = -1;
        }

    }
    @Override
    public String toString() {
        return "ProductMonitor{" +
                "sizeDiagonal=" + screenDiagonal +
                "} " + super.toString();
    }
    /*
    private double sizeDiagonal;
    private int[] aspectratio=new int[2], resolution = new int[2];
    private char energyEfficiencyClass;
    private ArrayList<String> videoScreenConnectors = new ArrayList<>();

    public ProductMonitor(String modell, String brand, double price, double sizeDiagonal, int[] aspectratio, int[] resolution, char energyEfficiencyClass, ArrayList<String> videoScreenConnectors) {
        super(modell, brand, price);
        setSizeDiagonal(sizeDiagonal);
        setAspectratio(aspectratio);
        setSizeDiagonal(sizeDiagonal);
        setResolution(resolution);
        setEnergyEfficiencyClass(energyEfficiencyClass);
        setVideoScreenConnectors(videoScreenConnectors);
    }

    public double getSizeDiagonal() {
        return sizeDiagonal;
    }

    public void setSizeDiagonal(double sizeDiagonal) {
        if (sizeDiagonal>0){
            this.sizeDiagonal = sizeDiagonal;
        }

    }

    public int[] getAspectratio() {
        return aspectratio;
    }

    public void setAspectratio(int[] aspectratio) {
        if (aspectratio[0]>0 && aspectratio[1]>0) {
            this.aspectratio = aspectratio;
        }
    }

    public int[] getResolution() {
        return resolution;
    }

    public void setResolution(int[] resolution) {
        if (resolution[0]>0 && resolution[1]>0) {
            this.resolution = resolution;
        }
    }

    public char getEnergyEfficiencyClass() {
        return energyEfficiencyClass;
    }

    public void setEnergyEfficiencyClass(char energyEfficiencyClass) {
        if (energyEfficiencyClass>='A' && energyEfficiencyClass<='G') {
            this.energyEfficiencyClass = energyEfficiencyClass;
        }
        else if(energyEfficiencyClass>='a' && energyEfficiencyClass<='g'){
            this.energyEfficiencyClass = energyEfficiencyClass;
            this.energyEfficiencyClass-=32;
        }
    }

    public ArrayList<String> getVideoScreenConnectors() {
        return videoScreenConnectors;
    }

    public void setVideoScreenConnectors(ArrayList<String> videoScreenConnectors) {
        this.videoScreenConnectors = videoScreenConnectors;
    }

     */
}
