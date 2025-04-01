package quanlycuahangth;

import java.time.LocalDate;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Statistics {

    // Thống kê doanh thu theo khoảng thời gian
    public static double calculateRevenue(List<Invoice> invoices, LocalDate startDate, LocalDate endDate) {
        double totalRevenue = 0.0;
        for (Invoice invoice : invoices) {
            if (!invoice.getDate().isBefore(startDate) && !invoice.getDate().isAfter(endDate)) {
                totalRevenue += invoice.getTotalAmount();
            }
        }
        return totalRevenue;
    }

    // Số lượng sản phẩm đã bán
    public static int calculateTotalProductsSold(List<Invoice> invoices) {
        int totalProductsSold = 0;
        for (Invoice invoice : invoices) {
            for (InvoiceItem item : invoice.getItems()) {
                totalProductsSold += item.getQuantity();
            }
        }
        return totalProductsSold;
    }

    // Sản phẩm bán chạy nhất
    public static Product getBestSellingProduct(List<Invoice> invoices, List<Product> products) {
        Map<String, Integer> productSales = new HashMap<>();
        for (Invoice invoice : invoices) {
            for (InvoiceItem item : invoice.getItems()) {
                productSales.put(item.getProductID(), productSales.getOrDefault(item.getProductID(), 0) + item.getQuantity());
            }
        }
        String bestSellingProductID = null;
        int maxSales = 0;
        for (Map.Entry<String, Integer> entry : productSales.entrySet()) {
            if (entry.getValue() > maxSales) {
                maxSales = entry.getValue();
                bestSellingProductID = entry.getKey();
            }
        }
        for (Product product : products) {
            if (product.getProductID().equals(bestSellingProductID)) {
                return product;
            }
        }
        return null;
    }

    // Số lượng khách hàng
    public static int calculateTotalCustomers(List<Customer> customers) {
        return customers.size();
    }

    // Tính tiền lương nhân viên
    public static double calculateTotalEmployeeSalaries(List<Employee> employees) {
        double totalSalaries = 0.0;
        for (Employee employee : employees) {
            totalSalaries += employee.getSalary();
        }
        return totalSalaries;
    }

    // Tổng số lần nhập hàng theo nhà cung cấp
    public static Map<String, Integer> calculateTotalOrdersBySupplier(List<PurchaseOrder> purchaseOrders) {
        Map<String, Integer> supplierOrders = new HashMap<>();
        for (PurchaseOrder order : purchaseOrders) {
            supplierOrders.put(order.getSupplierID(), supplierOrders.getOrDefault(order.getSupplierID(), 0) + 1);
        }
        return supplierOrders;
    }

    // Tổng số nhà cung cấp
    public static int calculateTotalSuppliers(List<Supplier> suppliers) {
        return suppliers.size();
    }

    // Tính lợi nhuận (Doanh thu - Chi phí nhập hàng)
    public static double calculateProfit(List<Invoice> invoices, List<PurchaseOrder> purchaseOrders) {
        double totalRevenue = calculateRevenue(invoices, LocalDate.MIN, LocalDate.MAX);
        double totalCost = 0.0;
        for (PurchaseOrder order : purchaseOrders) {
            totalCost += order.getTotalAmount();
        }
        return totalRevenue - totalCost;
    }
}