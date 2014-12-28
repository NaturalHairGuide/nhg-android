package mobile.lynn.com.naturalhairguide.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "HairProduct")
public class HairProduct {

    @Column(name = "name")
    private String name = "Product Name";

    @Column(name = "location")
    private String location = "Product Location";

    @Column(name = "price")
    private double price = 21.99;

    public HairProduct() {}

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
