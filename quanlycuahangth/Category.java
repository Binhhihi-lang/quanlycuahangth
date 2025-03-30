package quanlycuahangth;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Category {
    private String categoryID;
    private String name;

    private static List<Category> categoryList = new ArrayList<>();

    public Category(String categoryID, String name) {
        this.categoryID = categoryID;
        this.name = name;
    }

    public static boolean addCategory(Category category) {
        for (Category c : categoryList) {
            if (c.categoryID.equals(category.categoryID)) {
                System.out.println("Danh mục sản phẩm đã tồn tại:" + category.categoryID);
                return false;
            }
        }
        categoryList.add(category);
        System.out.println("Đã thêm Danh mục sản phẩm: " + category.categoryID);
        return true;
    }

    public static boolean updateCategory(String categoryID, String name) {
        for (Category c : categoryList) {
            if (c.categoryID.equals(categoryID)) {
                c.categoryID = categoryID;
                c.name = name;
            }
            System.out.println("Đã cập nhật danh mục sản phẩm thành công" + categoryID);
            return true;
        }
        System.out.println("Không tìm thấy danh mục sản phẩm" + categoryID);
        return false;
    }

    public static boolean deleteCategory(String categoryID) {
        Iterator<Category> categoryIterator = categoryList.iterator();
        while (categoryIterator.hasNext()) {
            Category c = categoryIterator.next();
            if (c.categoryID.equals(categoryID)) {
                categoryIterator.remove();
                System.out.println("Xóa danh mục sản phẩm thành công" + categoryID);
                return true;
            }
        }
        System.out.println("Không tìm thấy danh mục cần xóa" + categoryID);
        return false;
    }

    public static List<Category> searchCategory(String categoryID) {
        List<Category> result = new ArrayList<>();
        for (Category s : categoryList) {
            if (s.categoryID.equals(categoryID)) {
                System.out.println("Đã tìm thấy danh mục sản phẩm");
                result.add(s);
            } else {
                System.out.println("Không tìm thấy danh mục sản phẩm" + categoryID);

            }
        }
        return result;
    }

    public static List<Category> listCategories() {
        return new ArrayList<>(categoryList);
    }
}
