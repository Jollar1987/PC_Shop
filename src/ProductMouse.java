import java.util.ArrayList;
import java.util.HashMap;

public class ProductMouse extends Product{
    private String sensors;

    public ProductMouse(String modell, String brand, double price, String sensors) {
        super(modell, brand, price);
        setSensors(sensors);
    }
    public ProductMouse(){

    }
    public String getTyp(){
        return "Maus";
    }
    public String getSensors() {
        return sensors;
    }

    public void setSensors(String sensors) {
        if (!sensors.equals("")){
            this.sensors = sensors;
        }
        else{
            this.sensors = null;
        }
    }
    @Override
    public String toString() {
        return "ProductMouse{" +
                "sensors='" + sensors + '\'' +
                "} " + super.toString();
    }
}
