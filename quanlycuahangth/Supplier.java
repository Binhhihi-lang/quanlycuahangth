package quanlycuahangth;

import java.util.ArrayList;
import java.util.Iterator;
//Iterator là một interface trong Java (thuộc gói java.util)
// giúp duyệt qua các phần tử
// của một Collection (List, Set, Map, ...)
// mà không cần quan tâm đến cách dữ liệu được lưu trữ bên trong.
import java.util.List;

public class Supplier {
    private String supplierID;
    private String name;
    private String phone;
    private String email;
    private String address;

    // Giả lập danh sách nhà cung cấp
//    static có nghĩa là nó thuộc về class, chứ không phải riêng từng instance (đối tượng) của class đó
    private static List<Supplier> supplierList = new ArrayList<>();

    public Supplier(String supplierID, String name, String phone, String email, String address) {
        this.supplierID = supplierID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    // Thêm nhà cung cấp
    public static boolean addSupplier(Supplier supplier) {
        for (Supplier s : supplierList) {
            if (s.supplierID.equals(supplier.supplierID)) {
                System.out.println("Nhà cung cấp đã tồn tại: " + supplier.supplierID);
                return false;
            }
        }
        supplierList.add(supplier);
        System.out.println("Thêm nhà cung cấp thành công: " + supplier.name);
        return true;
    }

    // Cập nhật thông tin nhà cung cấp
    public static boolean updateSupplier(String supplierID, String name, String phone, String email, String address) {
        for (Supplier s : supplierList) {
            if (s.supplierID.equals(supplierID)) {
                s.name = name;
                s.phone = phone;
                s.email = email;
                s.address = address;
                System.out.println("Cập nhật nhà cung cấp thành công: " + supplierID);
                return true;
            }
        }
        System.out.println("Không tìm thấy nhà cung cấp: " + supplierID);
        return false;
    }

    // Xóa nhà cung cấp
    public static boolean deleteSupplier(String supplierID) {
        Iterator<Supplier> iterator = supplierList.iterator();
        while (iterator.hasNext()) {
            Supplier s = iterator.next();
            if (s.supplierID.equals(supplierID)) {
                iterator.remove();
                System.out.println("Xóa nhà cung cấp thành công: " + supplierID);
                return true;
            }
        }
        System.out.println("Không tìm thấy nhà cung cấp: " + supplierID);
        return false;
    }

    public static List<Supplier> searchSupplier(String supplierID) {
        List<Supplier> result = new ArrayList<>();
        for (Supplier s : supplierList) {
            if (s.supplierID.equals(supplierID)) {
                System.out.println("Đã tìm thấy nhà cung cấp");
                result.add(s);
            } else {
                System.out.println("Không tìm thấy nhà cung cấp" + supplierID);
            }
        }
        return result;
    }

    // Hiển thị danh sách nhà cung cấp
    public static List<Supplier> listSuppliers() {
        return new ArrayList<>(supplierList);
    }

//    public String getSupplierID() {
//        return supplierID;
//    }
//
//    public String getName() {
//        return name;
//    }
}
