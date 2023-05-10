import java.util.HashMap;

/**
 * Superclass
 */
public abstract class Product {
    private String model,brand;
    private double price;

    public Product(String model, String brand, double price) {
        setModel(model);
        setBrand(brand);
        setPrice(String.valueOf(price));
    }

    public Product() {

    }
    public abstract String getTyp();

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (!model.equals("")){
            this.model = model;
        }
        else{
            this.model = null;
        }
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (!brand.equals("")){
            this.brand = brand;
        }
        else{
            this.brand = null;
        }

    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price){
        if (price > 0.01){
            this.price = price;
        }
        else{
            this.price = -1;
        }
    }
    public void setPrice(String price) {
        if(CU.isNumeric(price)){
            if (Double.parseDouble(price)>0.01){
                this.price = Double.parseDouble(price);
            }
            else{
                this.price = -1;
            }
        }
        else{
            this.price = -1;
        }

    }
    @Override
    public String toString() {
        return "Product{" +
                "modell='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }

    /**
     * String for identification purposes
     * @param bool
     * @return true - return in one line; false - return in two lines
     */
    public String getIdentifier(boolean bool){
        if (bool){
            return "Marke: " + brand + " - Model: " + model;
        } else {
            return "Marke: " + brand + "\nModel: " + model;
        }

    }
}
