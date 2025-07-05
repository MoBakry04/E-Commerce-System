import java.util.List;


public class ShippingService
{
    List<Product> items;
    ShippingService(List<Product> items)
    {
        this.items = items;
    }

    void printItems()
    {
        for (Product item : items)
        {
            System.out.println( String.format("%s: %s", item.getName(), item.getWeight().get()));
        }
    }


}
