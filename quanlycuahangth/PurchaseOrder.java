package quanlycuahangth;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrder {
    private String PurchaseOrderID;
    private String supplierID;
    private String OrderDate;
    private String managerID;

    private static List<PurchaseOrder> purchaseOrders = new ArrayList<>();

    public PurchaseOrder(String PurchaseOrderID, String supplierID, String OrderDate, String managerID) {
        this.PurchaseOrderID = PurchaseOrderID;
        this.supplierID = supplierID;
        this.OrderDate = OrderDate;
        this.managerID = managerID;
    }

    // Tạo phiếu nhập kho mới
    public static boolean createPurchaseOrder(String PurchaseOrderID, String supplierID, String OrderDate, String managerID) {
        purchaseOrders.add(new PurchaseOrder(PurchaseOrderID, supplierID, OrderDate, managerID));
        System.out.println("Tạo phiếu nhập thành công!");
        return true;
    }

    // Liệt kê danh sách phiếu nhập kho
    public static List<PurchaseOrder> listPurchaseOrder() {
        return purchaseOrders;
    }

    // In thông tin phiếu nhập kho
    public static void printPurchaseOrder(String PurchaseOrderID) {
        for (PurchaseOrder po : purchaseOrders) {
            if (po.PurchaseOrderID.equals(PurchaseOrderID)) {
                System.out.println("Mã phiếu nhập: " + po.PurchaseOrderID);
                System.out.println("Nhà cung cấp: " + po.supplierID);
                System.out.println("Ngày nhập: " + po.OrderDate);
                System.out.println("Người quản lý: " + po.managerID);
                System.out.println("Danh sách sản phẩm:");
                for (PurchaseOrderItem item : PurchaseOrderItem.listPurchaseOrderItem()) {
                    if (item.PurchaseOrderID.equals(PurchaseOrderID)) {
                        System.out.println("- Mã sản phẩm: " + item.productID + ", Số lượng: " + item.quantity + ", Giá: " + item.price);
                    }
                }
                return;
            }
        }
        System.out.println("Không tìm thấy phiếu nhập có mã: " + PurchaseOrderID);
    }
}
