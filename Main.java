import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Date d = new Date(125, 7, 0);
        Product product1 = new Product("apple", 5, 10, Optional.empty(), Optional.empty());
        Product product2 = new Product("mango", 4, 20, Optional.empty(), Optional.of(d));
        Product product3 = new Product("mushroom", 4, 20, Optional.of(30.0), Optional.of(d));
        Customer customer = new Customer("mohamed", "bakry", "mowaelbk@gmail.com",
                "+201159796464", "sdad", 100.0);
        customer.cart.addProduct(product1, 5);
        customer.cart.addProduct(product2, 4);
        customer.cart.updateQuantity(product1, 7);
        customer.cart.removeProduct(product2);
        customer.cart.addProduct(product3, 3);
        ShippingService shippingService;
        List<Product> products = customer.checkout();
        shippingService = new ShippingService(products);
        shippingService.printItems();

    }
}