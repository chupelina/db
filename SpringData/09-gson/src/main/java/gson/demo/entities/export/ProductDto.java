package gson.demo.entities.export;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class ProductDto {
    @Expose
    @SerializedName(value = "name")
    private String name;
    @Expose
    @SerializedName(value = "price")
    private BigDecimal price;
    @Expose
    @SerializedName(value = "buyerFirstName")
    private String bayerFirstName;
    @Expose
    @SerializedName(value = "buyerLastName")
    private String bayerLastName;

    public ProductDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBayerFirstName() {
        return bayerFirstName;
    }

    public void setBayerFirstName(String bayerFirstName) {
        this.bayerFirstName = bayerFirstName;
    }

    public String getBayerLastName() {
        return bayerLastName;
    }

    public void setBayerLastName(String bayerLastName) {
        this.bayerLastName = bayerLastName;
    }
}
