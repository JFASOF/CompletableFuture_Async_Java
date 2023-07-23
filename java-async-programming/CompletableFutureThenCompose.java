import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/*
 * Retrieve the user’s information from the database
 * Validate the user’s information
 * Retrieve the product information from the database
 * Validate the product information
 * Calculate the total cost of the order
 * Place the order in the database
 */

public class CompletableFutureThenCompose {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        User user1 = new User("Jack", "Johnson", "12ef-dsd7-98re-rr88");
        User user2 = new User("John", "Johnson", "12ef-dsd7-98re-rr88");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        retrieveUser(users);
        user1.products.add(0, new Product("Egg", 2, "123-eef", 12.0));
        user1.products.add(0, new Product("Milk", 5, "123-eef", 13.45));
        user1.products.add(0, new Product("Beer", 3, "123-eef", 12.45));
        user2.products.add(0, new Product("Milk", 5, "123-eef", 11.47));
        user2.products.add(0, new Product("Beer", 3, "123-eef", 10.28));
        System.out.println(user1.products.size());

        CompletableFuture<User> userFuture = CompletableFuture.supplyAsync(() -> retrieveUser(users));
        CompletableFuture<Product> productFuture = userFuture
                .thenCompose(user -> CompletableFuture.supplyAsync(() -> retrieveProduct(user1)));
        CompletableFuture<Double> costFuture = productFuture
                .thenCompose(product -> CompletableFuture.supplyAsync(() -> calculateCost(user1.getProducts().get(0))));
        CompletableFuture<String> orderFuture = costFuture
                .thenCompose(cost -> CompletableFuture.supplyAsync(() -> placeOrder(cost)));
        System.out.println(userFuture.get().toString());
        System.out.println(productFuture.get().toString());
        System.out.println(costFuture.get());
        System.out.println(Objects.nonNull(orderFuture.get()) ? orderFuture.get() : null);

    }

    private static Double calculateCost(Product product) {
        return product.getQuantity() * product.getPrice();
    }

    private static Product retrieveProduct(User user) {
        return user.getProducts().get(0);
    }

    private static String placeOrder(Double cost) {
        return "Place Ordered. Total Cost : %9f".formatted(cost);
    }

    private static User retrieveUser(List<User> users) {
        return users.get(0);
    }
}

class User {
    private String name;
    private String surname;
    private String uuid;
    public List<Product> products = new ArrayList<>();

    public User(String name, String surname, String uuid) {
        this.name = name;
        this.surname = surname;
        this.uuid = uuid;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<Product> getProducts() {
        return products;
    }

}

class Product {
    private String name;
    private int quantity;
    private String uuid;
    private double price;

    public Product(String name, int quantity, String uuid, double price) {
        this.name = name;
        this.quantity = quantity;
        this.uuid = uuid;
        this.price = price;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
}