package quanlycuahangth;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderItem {
    String PurchaseOrderID;
    protected String productID;
    protected int quantity;
    protected double price;

    private static List<PurchaseOrderItem> purchaseOrderItems = new ArrayList<>();

    public PurchaseOrderItem(String PurchaseOrderID, String productID, int quantity, double price) {
        this.PurchaseOrderID = PurchaseOrderID;
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
    }

    // Thêm sản phẩm vào phiếu nhập hàng
    public static boolean addProductToPurchaseOrderItem(String PurchaseOrderID, String productID, int quantity, double price) {
        purchaseOrderItems.add(new PurchaseOrderItem(PurchaseOrderID, productID, quantity, price));
        System.out.println("Thêm sản phẩm vào phiếu nhập thành công!");
//        // Gọi Inventory.updateInventory(productID, quantity) để cập nhật tồn kho
//        return Inventory.updateInventory(productID, quantity);
        return true;
    }

    // Xóa sản phẩm khỏi phiếu nhập hàng
    public static boolean removeProductFromPurchaseOrderItem(String PurchaseOrderID, String productID) {
        boolean removed = purchaseOrderItems.removeIf(item ->
                item.PurchaseOrderID.equals(PurchaseOrderID) && item.productID.equals(productID));
        if (removed) {
            System.out.println("Xóa sản phẩm khỏi phiếu nhập thành công!");
        } else {
            System.out.println("Không tìm thấy sản phẩm để xóa!");
        }
        return removed;
    }

    // Liệt kê danh sách sản phẩm trong phiếu nhập
    public static List<PurchaseOrderItem> listPurchaseOrderItem() {
        return new ArrayList<>(purchaseOrderItems);
    }
}
