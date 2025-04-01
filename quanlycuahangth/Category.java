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
                return false;
            }
        }
        categoryList.add(category);
        return true;
    }

    public static boolean updateCategory(String categoryID, String name) {
        for (Category c : categoryList) {
            if (c.categoryID.equals(categoryID)) {
                c.categoryID = categoryID;
                c.name = name;
            }
            return true;
        }
        return false;
    }

    public static boolean deleteCategory(String categoryID) {
        Iterator<Category> categoryIterator = categoryList.iterator();
        while (categoryIterator.hasNext()) {
            Category c = categoryIterator.next();
            if (c.categoryID.equals(categoryID)) {
                categoryIterator.remove();
                return true;
            }
        }
        return false;
    }

    public static List<Category> searchCategory(String categoryID) {
        List<Category> result = new ArrayList<>();
        for (Category s : categoryList) {
            if (s.categoryID.equals(categoryID)) {
                result.add(s);
                return result;
            }
        }
        return null;
    }

    public static List<Category> listCategories() {
        return new ArrayList<>(categoryList);
    }
}
