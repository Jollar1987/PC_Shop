import java.util.ArrayList;
import java.util.HashMap;

public class ProductKeyboard extends Product{
    private String layout;

    public ProductKeyboard(String modell, String brand, double price, String layout) {
        super(modell, brand, price);
        setLayout(layout);
    }
    public ProductKeyboard(){

    }
    public String getTyp(){
        return "Tastatur";
    }
    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        if (!layout.equals("")){
            this.layout = layout;
        }
        else{
            this.layout = null;
        }
    }

    public HashMap<String, String> pullProperties(){
        HashMap<String, String> properties = super.pullProperties();
        properties.put(CCS.getLayout(), "");
        return properties;
    }
    public void pushProperties(HashMap<String, String> properties){
        super.pushProperties(properties);
        setLayout(properties.get(CCS.getLayout()));
    }

    @Override
    public String toString() {
        return "ProductKeyboard{" +
                "layout='" + layout + '\'' +
                "} " + super.toString();
    }
}
