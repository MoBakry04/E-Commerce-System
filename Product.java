import java.util.Date;
import java.util.Optional;
import java.util.Objects;
import java.time.LocalDate;

interface shipping{
    String getName();
    Optional<Double> getWeight();
}

public class Product implements shipping{
    private String name;
    private double price;
    private int quantity;
    private Optional<Double> shipping_weight;
    private Optional<Date> expiration_date;

    public Product(String name, double price, int quantity, Optional<Double> shipping_weight, Optional<Date> expiration_date) {
        this.name = Objects.requireNonNull(name);
        if(price <= 0){
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        this.price = price;
        if(quantity <= 0){
            throw new IllegalArgumentException("Quantity should be greater than 0");
        }
        this.quantity = quantity;
        if(shipping_weight.isPresent() && shipping_weight.get() < 0){
            throw new IllegalArgumentException("Shipping weight should be greater than 0");
        }
        this.shipping_weight = shipping_weight.isPresent() ? shipping_weight : Optional.empty();
        expiration_date.ifPresent(date -> {
            if (!date.after(new Date())) {
                throw new IllegalArgumentException(
                        "Cannot add product that is already expired: " + date);
            }
        });
        this.expiration_date = expiration_date.isPresent() ? expiration_date : Optional.empty();
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    //if stock increases/decreases
    public void setQuantity(int quantity) {
        if(quantity <= 0){
            throw new IllegalArgumentException("Quantity should be greater than 0");
        }
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }

    public boolean isExpired() {
        return expiration_date
                .map(date -> !date.after(new Date()))
                .orElse(false);
    }

    public Optional<Double> getWeight() {
        return shipping_weight;
    }
}