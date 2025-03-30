package quanlycuahangth;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Product {

    private String productID;
    private String name;
    private double price;
    private int stockQuantity;
    private String categoryID;

    private static List<Product> productList = new ArrayList<>();

    public Product(String productID, String name, double price, int stockQuantity, String categoryID) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryID = categoryID;
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

    public int getStockQuantity() {
        return stockQuantity;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public static boolean addProduct(Product product) {
        for (Product p : productList) {
            if (p.productID.equals(product.productID)) {
                System.out.println("Sản phẩm đã tồn tại" + p.productID);
                return false;
            }
        }
        productList.add(product);
        System.out.println("Đã thêm sản phẩm thành công");
        return true;
    }

    public static boolean updateProduct(String productID, String name, double price, int stockQuantity) {
        for (Product p : productList) {
            if (p.productID.equals(productID)) {
                p.name = name;
                p.price = price;
                p.stockQuantity = stockQuantity;
                System.out.println("Cập nhật sản phẩm thành công");
                return true;
            }
        }
        System.out.println("Không tìm thấy sản phẩm");
        return false;
    }

    public static boolean deleteProduct(String productID) {
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.productID.equals(productID)) {
                iterator.remove();
                System.out.println("Đã xóa thành công sản phẩm");
                return true;
            }
        }
        System.out.println("Không tìm thấy sản phẩm");
        return false;
    }

    public static List<Product> searchProduct(String productID) {
        List<Product> result = new ArrayList<>();
        for (Product p : productList) {
            if (p.productID.equals(productID)) {
                result.add(p);
                System.out.println("Đã tìm thấy sản phẩm");
            } else {
                System.out.println("không tìm thấy sản phẩm" + productID);
            }
        }
        return result;
    }

    public static List<Product> listProducts() {
        return new ArrayList<>(productList);
    }
}