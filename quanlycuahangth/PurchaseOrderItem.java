package quanlycuahangth;

import java.util.*;

public class PurchaseOrderItem {
    private String purchaseOrderItemID;
    private String purchaseOrderID;
    private String productID;
    private double price;
    private int quantity;

    private static List<PurchaseOrderItem> purchaseOrderItemsList = new ArrayList<>();

    public PurchaseOrderItem(String purchaseOrderItemID, String purchaseOrderID, String productID, double price, int quantity) {
        this.purchaseOrderItemID = purchaseOrderItemID;
        this.purchaseOrderID = purchaseOrderID;
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
    }

    public String getPurchaseOrderItemID() {
        return purchaseOrderItemID;
    }

    public String getPurchaseOrderID() {
        return purchaseOrderID;
    }

    public String getProductID() {
        return productID;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public static boolean addProductToPurchaseOrder(String purchaseOrderItemID, String purchaseOrderID, String productID, double price, int quantity) {
        for (PurchaseOrderItem item : purchaseOrderItemsList) {
            if (item.purchaseOrderItemID.equals(purchaseOrderItemID)) {
                return false;
            }
        }
        PurchaseOrderItem newItem = new PurchaseOrderItem(purchaseOrderItemID, purchaseOrderID, productID, price, quantity);
        purchaseOrderItemsList.add(newItem);
        return true;
    }

    public static boolean removeProductFromPurchaseOrder(String purchaseOrderItemID) {
        Iterator<PurchaseOrderItem> it = purchaseOrderItemsList.iterator();
        while (it.hasNext()) {
            PurchaseOrderItem item = it.next();
            if (item.purchaseOrderItemID.equals(purchaseOrderItemID)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public static boolean updatePurchaseOrderItem(String purchaseOrderItemID, String purchaseOrderID, String productID, double price, int quantity) {
        for (PurchaseOrderItem item : purchaseOrderItemsList) {
            if (item.purchaseOrderItemID.equals(purchaseOrderItemID)) {
                item.purchaseOrderID = purchaseOrderID;
                item.productID = productID;
                item.price = price;
                item.quantity = quantity;
                return true;
            }
        }
        return false;
    }
}