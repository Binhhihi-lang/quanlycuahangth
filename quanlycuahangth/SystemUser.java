package quanlycuahangth;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SystemUser {
    protected String userID;
    protected String username;
    protected String password;

    // Giả lập danh sách người dùng (database)
    private static List<SystemUser> userList = new ArrayList<>();

    public SystemUser(String userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    // Kiểm tra đăng nhập
    public static SystemUser login(String username, String password) {
        for (SystemUser user : userList) {
            if (user.username.equals(username) && user.password.equals(password)) {
                System.out.println("Đăng nhập thành công: " + username);
                return user;
            }
        }
        System.out.println("Đăng nhập thất bại cho: " + username);
        return null;
    }

    // Đăng xuất (đơn giản chỉ in thông báo)
    public void logout() {
        System.out.println(username + " đã đăng xuất.");
    }

    // Tạo tài khoản mới
    public static boolean createUser(String userID, String username, String password) {
        // Kiểm tra trùng username
        for (SystemUser user : userList) {
            if (user.username.equals(username)) {
                System.out.println("Username đã tồn tại.");
                return false;
            }
        }
        SystemUser newUser = new SystemUser(userID, username, password);
        userList.add(newUser);
        System.out.println("Tạo tài khoản thành công: " + username);
        return true;
    }

    // Cập nhật thông tin tài khoản
    public static boolean updateUser(String userID, String newUsername, String newPassword) {
        for (SystemUser user : userList) {
            if (user.userID.equals(userID)) {
                user.username = newUsername;
                user.password = newPassword;
                System.out.println("Cập nhật tài khoản thành công cho: " + userID);
                return true;
            }
        }
        System.out.println("Không tìm thấy userID: " + userID);
        return false;
    }

    // Xóa tài khoản
    public static boolean deleteUser(String userID) {
        Iterator<SystemUser> iterator = userList.iterator();
        while (iterator.hasNext()) {
            SystemUser user = iterator.next();
            if (user.userID.equals(userID)) {
                iterator.remove();
                System.out.println("Xóa tài khoản thành công: " + userID);
                return true;
            }
        }
        System.out.println("Không tìm thấy userID: " + userID);
        return false;
    }
}
