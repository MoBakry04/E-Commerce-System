import java.util.*;

public class Cart {
    private final Map<Product, Integer> items = new HashMap<>();

    //assuming u cant add already added products through this function
    public void addProduct(Product product, int qty) {

        if (qty <= 0) throw new IllegalArgumentException("qty must be > 0");
        items.put( Objects.requireNonNull(product), qty);
    }

    public void removeProduct(Product product) {
        items.remove(product);
    }

    public void updateQuantity(Product product, int qty) {
        if (qty == 0) {
            items.remove(product);
        }
        else if(qty < 0){
            throw new IllegalArgumentException("qty must be greater than 0");
        }
        else {
            items.put(product, qty);
        }
    }
    public boolean isEmpty() {
        return items.isEmpty();
    }

    public Map<Product, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public double getTotal() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity    = entry.getValue();
            double lineTotal = product.getPrice() * quantity;
            total += lineTotal;
        }
        return total;
    }
}