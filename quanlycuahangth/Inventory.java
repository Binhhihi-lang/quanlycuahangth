package quanlycuahangth;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.time.LocalDate;

public class Inventory {
    // Giả lập kho hàng bằng Map: key = productID, value = Inventory object
    private static HashMap<String, Inventory> inventoryMap = new HashMap<>();

    private String productID;
    private int quantity;
    private LocalDate expirationDate;

    public Inventory(String productID, int quantity, LocalDate expirationDate) {
        this.productID = productID;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    public String getProductID() {
        return productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    // Kiểm tra tồn kho của sản phẩm
    public static boolean checkStock(String productID) {
        Inventory inv = inventoryMap.get(productID);
        if (inv != null && inv.quantity > 0) {
            System.out.println("Sản phẩm " + productID + " còn tồn: " + inv.quantity);
            return true;
        }
//        System.out.println("Sản phẩm " + productID + " không còn hàng.");
        return false;
    }

    // Nhập hàng về thêm sản phẩm vào kho
    public static boolean addProductToInventory(String productID, int addQuantity, LocalDate expirationDate) {
        Inventory inv = inventoryMap.get(productID);
        if (inv == null) {
            inv = new Inventory(productID, addQuantity, expirationDate);
            inventoryMap.put(productID, inv);
        } else {
            inv.quantity += addQuantity;
        }
        return true;
    }

    // Xuất hàng (giảm số lượng khi bán)
    public static boolean reduceQuantityOfProductFromInventory(String productID, int reduceQuantity) {
        Inventory inv = inventoryMap.get(productID);
        if (inv != null && inv.quantity >= reduceQuantity) {
            inv.quantity -= reduceQuantity;
            return true;
        }
//        System.out.println("Xuất hàng thất bại: Không đủ số lượng tồn kho.");
        return false;
    }

    // Xóa mặt hàng trong kho
    public static boolean removeProductInInventory(String productID) {
        if (inventoryMap.containsKey(productID)) {
            inventoryMap.remove(productID);
            return true;
        }
        return false;
    }

    public static boolean updateProductInInventory(String productID, int quantity, LocalDate expirationDate) {
        Inventory inv = inventoryMap.get(productID);
        if (inv != null) {
            inv.productID = productID;
            inv.quantity = quantity;
            inv.expirationDate = expirationDate;
            return true;
        }
        return false;
    }

    // Cảnh báo sản phẩm sắp hết
    public static boolean warnLowStock(String productID, int threshold) {
        Inventory inv = inventoryMap.get(productID);
        if (inv != null && inv.quantity <= threshold) {
            System.out.println("Cảnh báo: Sản phẩm " + productID + " chỉ còn " + inv.quantity + " sản phẩm.");
            return true;
        }
        return false;
    }

    // Lấy danh sách sản phẩm tồn kho lâu ngày (giả sử dựa theo expirationDate)
    public static List<Inventory> getOldInventoryItems(LocalDate referenceDate) {
        List<Inventory> oldItems = new ArrayList<>();
        for (Inventory inv : inventoryMap.values()) {
            if (inv.expirationDate.isBefore(referenceDate)) {
                oldItems.add(inv);
            }
        }
        return oldItems;
    }

    // Hiển thị toàn bộ sản phẩm trong kho
    public static void displayInventory() {
        for (Inventory inv : inventoryMap.values()) {
            System.out.println("Sản phẩm: " + inv.productID + " | Số lượng: " + inv.quantity + " | Hạn sử dụng: " + inv.expirationDate);
        }
    }

}