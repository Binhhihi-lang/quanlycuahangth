package quanlycuahangth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Statistics {

    // Thống kê doanh thu theo khoảng thời gian
    public static double revenueStatistics(String startDate, String endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date cannot be null");
        }
        double totalRevenue = 0;
        List<Invoice> invoices = InvoiceManager.getInvoicesByDateRange(startDate, endDate);
        for (Invoice invoice : invoices) {
            totalRevenue += invoice.getTotalAmount();
        }
        return totalRevenue;
    }

    // Thống kê số lượng sản phẩm đã bán
    public static Map<String, Integer> productStatistics() {
        Map<String, Integer> productSales = new HashMap<>();
        List<Invoice> invoices = InvoiceManager.getAllInvoices();
        for (Invoice invoice : invoices) {
            for (InvoiceItem item : invoice.getItems()) {
                productSales.compute(item.getProductID(), (k, v) -> (v == null) ? item.getQuantity() : v + item.getQuantity());
            }
        }
        return productSales;
    }

    // Thống kê số lượng sản phẩm tồn kho
    public static Map<String, Integer> inventoryStatistics() {
        Map<String, Integer> inventoryData = new HashMap<>();
        List<Product> products = ProductManager.getAllProducts();
        for (Product product : products) {
            inventoryData.put(product.getProductID(), product.getStockQuantity());
        }
        return inventoryData;
    }

    // Thống kê số lượng khách hàng
    public static int customerStatistics() {
        return CustomerManager.getAllCustomers().size();
    }

    // Thống kê tổng số nhân viên
    public static int employeeStatistics() {
        return EmployeeManager.getAllEmployees().size();
    }

    // Thống kê tổng số lần nhập hàng theo nhà cung cấp
    public static Map<String, Integer> supplierStatistics() {
        Map<String, Integer> supplierOrders = new HashMap<>();
        List<PurchaseOrder> orders = PurchaseOrderManager.getAllOrders();
        for (PurchaseOrder order : orders) {
            supplierOrders.compute(order.getSupplierID(), (k, v) -> (v == null) ? 1 : v + 1);
        }
        return supplierOrders;
    }

    // Thống kê lợi nhuận (Doanh thu - Chi phí nhập hàng)
    public static double profitStatistics(String startDate, String endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date cannot be null");
        }
        double revenue = revenueStatistics(startDate, endDate);
        double totalCost = 0;
        List<PurchaseOrder> orders = PurchaseOrderManager.getOrdersByDateRange(startDate, endDate);
        for (PurchaseOrder order : orders) {
            totalCost += order.getTotalCost();
        }
        return revenue - totalCost;
    }
}
