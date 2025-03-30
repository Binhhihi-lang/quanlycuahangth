package quanlycuahangth;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.time.*;

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

    // Kiểm tra tồn kho của sản phẩm
    public static boolean checkStock(String productID) {
        Inventory inv = inventoryMap.get(productID);
        if (inv != null && inv.quantity > 0) {
            System.out.println("Sản phẩm " + productID + " còn tồn: " + inv.quantity);
            return true;
        }
        System.out.println("Sản phẩm " + productID + " không còn hàng.");
        return false;
    }

    // Cập nhật tồn kho: tăng số lượng lên (với nhập hàng) hoặc giảm khi bán
    public static boolean updateInventory(String productID, int addQuantity) {
        Inventory inv = inventoryMap.get(productID);
        if (inv == null) {
            // Nếu chưa có, tạo mới với expirationDate mặc định (ví dụ 1 năm sau)
            inv = new Inventory(productID, addQuantity, LocalDate.now().plusYears(1));
            inventoryMap.put(productID, inv);
        } else {
            inv.quantity += addQuantity;
        }
        System.out.println("Cập nhật kho: sản phẩm " + productID + " có số lượng mới: " + inventoryMap.get(productID).quantity);
        return true;
    }

    // Xuất hàng (giảm số lượng khi bán)
    public static boolean removeStock(String productID, int removeQuantity) {
        Inventory inv = inventoryMap.get(productID);
        if (inv != null && inv.quantity >= removeQuantity) {
            inv.quantity -= removeQuantity;
            System.out.println("Xuất hàng thành công: " + productID + " còn lại " + inv.quantity);
            return true;
        }
        System.out.println("Xuất hàng thất bại: Không đủ số lượng tồn kho.");
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
        System.out.println("===== DANH SÁCH TỒN KHO =====");
        for (Inventory inv : inventoryMap.values()) {
            System.out.println("Sản phẩm: " + inv.productID + " | Số lượng: " + inv.quantity + " | Hạn sử dụng: " + inv.expirationDate);
        }
        System.out.println("=============================");
    }

    // Getters
    public String getProductID() {
        return productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}
