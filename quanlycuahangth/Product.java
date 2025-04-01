package quanlycuahangth;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Product {

    private String productID;
    private String name;
    private double price;
    private String desciption;
    private String categoryID;

    private static List<Product> productList = new ArrayList<>();

    public Product(String productID, String name, double price, int stockQuantity, String description, String categoryID) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.categoryID = categoryID;
        this.desciption = description;
    }

    public String getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getDesciption() {
        return desciption;
    }

    public static boolean addProduct(Product product) {
        for (Product p : productList) {
            if (p.productID.equals(product.productID)) {
                return false;
            }
        }
        productList.add(product);
        return true;
    }

    public static boolean updateProduct(String productID, String name, double price, String desciption, String categoryID) {
        for (Product p : productList) {
            if (p.productID.equals(productID)) {
                p.name = name;
                p.price = price;
                p.desciption = desciption;
                p.categoryID = categoryID;
                return true;
            }
        }
        return false;
    }

    public static boolean deleteProduct(String productID) {
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.productID.equals(productID)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public static List<Product> searchProduct(String productID) {
        List<Product> result = new ArrayList<>();
        for (Product p : productList) {
            if (p.productID.equals(productID)) {
                result.add(p);
                return result;
            }
        }
        return null;
    }

    public static List<Product> listProducts() {
        return new ArrayList<>(productList);
    }
}