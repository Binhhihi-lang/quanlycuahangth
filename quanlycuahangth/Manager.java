package quanlycuahangth;

public class Manager {
    private String managerID;
    private String name;

    public Manager(String managerID, String name) {
        this.managerID = managerID;
        this.name = name;
    }

    // Giả lập các thao tác quản lý sản phẩm (chỉ in thông báo, bạn có thể tích hợp ProductService thực tế)
    public boolean addProduct(String productID, String productName, double price, String categoryID) {
        System.out.println("Manager " + name + " thêm sản phẩm: " + productName);
        // Logic thêm sản phẩm (gọi ProductService nếu có)
        return true;
    }

    public boolean removeProduct(String productID) {
        System.out.println("Manager " + name + " xóa sản phẩm: " + productID);
        // Logic xóa sản phẩm
        return true;
    }

    public boolean updateProduct(String productID, String newName, double newPrice, String newCategoryID) {
        System.out.println("Manager " + name + " cập nhật sản phẩm " + productID + " thành " + newName + ", giá: " + newPrice);
        // Logic cập nhật sản phẩm
        return true;
    }

    // Bạn có thể bổ sung thêm các phương thức quản lý nhân viên, khách hàng,... tùy theo yêu cầu.

    // Getters
    public String getManagerID() {
        return managerID;
    }

    public String getName() {
        return name;
    }
}
