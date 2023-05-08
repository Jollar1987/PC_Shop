import java.util.ArrayList;
import java.util.HashMap;

public class ProductMotherboard extends Product{
    private String formFactor;

    public ProductMotherboard(String modell, String brand, double price, String formFactor) {
        super(modell, brand, price);
        setFormFactor(formFactor);
    }
    public ProductMotherboard(){

    }
    public String getTyp(){
        return "Mainboard";
    }
    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        if (!formFactor.equals("")){
            this.formFactor = formFactor;
        }
        else{
            this.formFactor = null;
        }
    }

    public HashMap<String, String> pullProperties(){
        HashMap<String, String> properties = super.pullProperties();
        properties.put(CCS.getFormFactor(), "");
        return properties;
    }
    public void pushProperties(HashMap<String, String> properties){
        super.pushProperties(properties);
        setFormFactor(properties.get(CCS.getFormFactor()));
    }

    @Override
    public String toString() {
        return "ProductMotherboard{" +
                "formFactor='" + formFactor + '\'' +
                "} " + super.toString();
    }
}
