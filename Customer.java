import java.util.*;
import java.util.regex.Pattern;

public class Customer {

    //email and phone number regex
    private static final Pattern EMAIL_REGEX = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
            Pattern.CASE_INSENSITIVE
    );
    private static final Pattern PHONE_REGEX = Pattern.compile(
            "^\\+?[1-9]\\d{1,14}$"
    );
    Cart cart;
    String first_name;
    String last_name;
    String email;
    String phone_number;
    String address;
    double balance;

    public Customer(String first_name, String last_name, String email, String phone_number, String address,  double balance) {
        this.first_name = Objects.requireNonNull(first_name);
        this.last_name = Objects.requireNonNull(last_name);
        this.address = Objects.requireNonNull(address);
        if (!EMAIL_REGEX.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email address: " + email);
        }
        this.email = Objects.requireNonNull(email);
        if (!PHONE_REGEX.matcher(phone_number).matches()) {
            throw new IllegalArgumentException("Invalid phone number: " + phone_number);
        }
        this.phone_number = Objects.requireNonNull(phone_number);
        this.cart = new Cart();
        this.balance = balance;
    }

    public List<Product> checkout() {
        List<Product> toShip = new ArrayList<>();
        if(cart.isEmpty()){
            throw new IllegalArgumentException("Cart is empty");
        }
        if(balance < cart.getTotal()){
            throw new IllegalArgumentException("Not enough balance");
        }
        Map<Product, Integer> items = cart.getItems();
        items.forEach((product,quantity)->{
            if(product.isExpired()){
                throw new IllegalArgumentException(product.getName() + " is expired");
            }
            if(quantity > product.getQuantity()){
                throw new IllegalArgumentException("not enough quantity of " +  product.getName() + " in stock");
            }
            if(product.getWeight().isPresent()){
                toShip.add(product);
            }
        });
        items.forEach((product,quantity)->{
            product.setQuantity(product.getQuantity() - quantity);
        });
        balance -= cart.getTotal();

        System.out.println("Subtotal: " + cart.getTotal());
        System.out.println("Shipping Fees: " + 50);
        System.out.println("Total: " + (cart.getTotal() + 50));
        System.out.println("New Balance: " + balance);
        return toShip;
    }
}
