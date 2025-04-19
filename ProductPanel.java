package ui.product;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableModel;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import ui.effect.TableHeader;
import ui.effect.ContentManager;
import ui.effect.TableButtonEditor;
import ui.effect.TableButtonRenderer;

import db.ProductDAO;

public class ProductPanel extends javax.swing.JPanel {

    public ProductPanel() {
        initComponents();
        
        // Style cho header
        TableHeader.styleHeader(productTable, new Color(56, 66, 137), new Font("Segoe UI", Font.BOLD, 15));
        
        ProductInfoPanel productInfoPanel = new ProductInfoPanel();
        ProductEditPanel productEditPanel = new ProductEditPanel();
        ProductAddPanel productAddPanel = new ProductAddPanel();
        ProductBlankPanel productBlankPanel = new ProductBlankPanel();
        
        // Danh sách các panel cần hiển thị
        Map<JPanel, String> panelMap = Map.of(
                productInfoPanel, "Info",
                productEditPanel, "Edit",
                productAddPanel, "Add",
                productBlankPanel, "Blank"
        );
        
        // Thêm các panel trên vào contentPanel
        ContentManager conMan = new ContentManager(contentPanel);
        conMan.addPanel(panelMap);
        conMan.showPanel("Blank");
        
        // Tạo các nút ở cột cuối cùng
        TableColumn finalColumn = productTable.getColumnModel().getColumn(productTable.getColumnCount() - 1);
        finalColumn.setPreferredWidth(85);
        
        TableButtonEditor tableBtnEditor = new TableButtonEditor(List.of("Xem", "Sửa", "Xóa"));
        
        finalColumn.setCellRenderer(new TableButtonRenderer(List.of("Xem", "Sửa", "Xóa")));
        finalColumn.setCellEditor(tableBtnEditor);
        
        // Lấy model của bảng product
        DefaultTableModel productModel = (DefaultTableModel) productTable.getModel();
        
        // Tải dữ liệu của bảng từ database
        loadProductTable(productModel);
        
        // Tạo SimpleDateFormat để gán giá trị kiểu date sao cho giống với format của MySQL
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Tạo các xử lý hành động cho 3 nút cuối bảng
        tableBtnEditor.addTableButtonActionListener(conMan);
        
        // Tạo các xử lý hành động cho các nút khác ngoài bảng
        // Nút thêm mặt hàng mới
        btnAdd.addActionListener(e -> conMan.showPanel("Add"));
        
        // Nút Tìm kiếm
        btnSearch.addActionListener(e -> {
            String textId = txtSearch.getText().trim();
            if (!textId.isEmpty()) {
                try {
                    int id = Integer.parseInt(textId);
                    Vector<Vector<Object>> result = ProductDAO.searchProductById(id);
                    productModel.setRowCount(0);  // Xóa bảng cũ

                    if (result.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm nào!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        for (Vector<Object> row : result) {
                            productModel.addRow(row);
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Mã mặt hàng phải là một số nguyên không âm!", "Lỗi cú pháp", JOptionPane.ERROR_MESSAGE);
                    btnClearAll.doClick();
                }
            }
            else {
                loadProductTable(productModel); // Load lại bảng nếu searchbar trống
            }
        });
        
        // Nút Xóa tất cả
        btnClearAll.addActionListener(e -> {
            txtSearch.setText("");
            loadProductTable(productModel);
        });
        
        // Tạo các xử lý hành động cho 3 nút ở bảng
        // Nút Xem
        tableBtnEditor.getBtnView().addActionListener(e -> {
            int row = productTable.getEditingRow();
            if (row != -1) {
                row = productTable.convertRowIndexToModel(row);

                int productId = (int) productTable.getModel().getValueAt(row, 0);
                Vector<Object> productInfo = ProductDAO.getProductById(productId);

                if (productInfo != null) {
                    productInfoPanel.getTxtId().setText(productInfo.get(0).toString());
                    productInfoPanel.getTxtName().setText(productInfo.get(1).toString());
                    productInfoPanel.getTxtPurchasePrice().setText(productInfo.get(2).toString());
                    productInfoPanel.getTxtSalePrice().setText(productInfo.get(3).toString());
                    productInfoPanel.getTxtQuantity().setText(productInfo.get(4).toString());
                    productInfoPanel.getTxtUnit().setText(productInfo.get(5).toString());
                    productInfoPanel.getTxtProdDate().setText(productInfo.get(6).toString());
                    productInfoPanel.getTxtExpDate().setText(productInfo.get(7).toString());
                    productInfoPanel.getTxtCategory().setText(productInfo.get(8).toString());
                    productInfoPanel.getTxtSupplierId().setText(productInfo.get(9).toString());
                }
            }
        });
        
        // Nút Sửa
        tableBtnEditor.getBtnEdit().addActionListener(e -> {
            int row = productTable.getEditingRow();
            if (row != -1) {
                row = productTable.convertRowIndexToModel(row); // để đảm bảo đúng dữ liệu gốc

                int productId = (int) productTable.getModel().getValueAt(row, 0);
                Vector<Object> productInfo = ProductDAO.getProductById(productId);

                if (productInfo != null) {
                    productEditPanel.getTxtId().setText(productInfo.get(0).toString());
                    productEditPanel.getTxtName().setText(productInfo.get(1).toString());
                    productEditPanel.getTxtPurchasePrice().setText(productInfo.get(2).toString());
                    productEditPanel.getTxtSalePrice().setText(productInfo.get(3).toString());
                    productEditPanel.getTxtQuantity().setText(productInfo.get(4).toString());
                    productEditPanel.getTxtUnit().setText(productInfo.get(5).toString());
                    try {
                        productEditPanel.getTxtProdDate().setDate(sdf.parse(productInfo.get(6).toString()));
                    } catch (ParseException ex) {
                        Logger.getLogger(ProductPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        productEditPanel.getTxtExpDate().setDate(sdf.parse(productInfo.get(7).toString()));
                    } catch (ParseException ex) {
                        Logger.getLogger(ProductPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String categoryFromDB = productInfo.get(8).toString(); // Lấy từ DB
                    JComboBox<String> cbCategory = productEditPanel.getTxtCategory(); // Lấy combobox

                    int itemCount = cbCategory.getItemCount();
                    for (int i = 0; i < itemCount; i++) {
                        String item = cbCategory.getItemAt(i);
                        if (item.equalsIgnoreCase(categoryFromDB)) {
                            cbCategory.setSelectedIndex(i); // ✅ chọn dòng phù hợp
                            break;
                        }
                    }
                    productEditPanel.getTxtSupplierId().setText(productInfo.get(9).toString());

                    conMan.showPanel("Edit");
                }
            }
        });
        
        // Nút Xóa
        tableBtnEditor.getBtnDelete().addActionListener(e -> {
            // Lấy sản phẩm cần xóa từ bảng (lấy product_id từ cột 0)
            int row = productTable.getEditingRow();
            if (row != -1) {
                row = productTable.convertRowIndexToModel(row);  // Đảm bảo row đúng với model
                int productId = (int) productTable.getModel().getValueAt(row, 0);

                // Xác nhận xóa sản phẩm
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa sản phẩm này?", "Xóa sản phẩm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Xóa sản phẩm trong cơ sở dữ liệu
                    boolean success = ProductDAO.deleteProduct(productId);

                    if (success) {
                        JOptionPane.showMessageDialog(null, "Sản phẩm đã được xóa thành công.");
                        conMan.showPanel("Blank");
                        loadProductTable(productModel); // Load lại dữ liệu của bảng
                    } else {
                        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi xóa sản phẩm.");
                    }
                }
            }
        });

        // Nút Lưu các thay đổi ở form Chỉnh sửa mặt hàng
        productEditPanel.getBtnSaveChanges().addActionListener(e ->{
            // Kiểm tra dữ liệu ở các textfield có đúng định dạng hay không
            if (!validateProductInputs(
                    productEditPanel.getTxtName(),
                    productEditPanel.getTxtPurchasePrice(),
                    productEditPanel.getTxtSalePrice(),
                    productEditPanel.getTxtQuantity(),
                    productEditPanel.getTxtUnit(),
                    productEditPanel.getTxtProdDate(),
                    productEditPanel.getTxtExpDate(),
                    productEditPanel.getTxtSupplierId())) {
                return;
            }
            
            // Lấy dữ liệu từ các textfield
            int productId = Integer.parseInt(productEditPanel.getTxtId().getText());
            String productName = productEditPanel.getTxtName().getText();
            double purchasePrice = Double.parseDouble(productEditPanel.getTxtPurchasePrice().getText());
            double salePrice = Double.parseDouble(productEditPanel.getTxtSalePrice().getText());
            int productQuantity = Integer.parseInt(productEditPanel.getTxtQuantity().getText());
            String unit = productEditPanel.getTxtUnit().getText();
            String prodDate = sdf.format(productEditPanel.getTxtProdDate().getDate());
            String expDate = sdf.format(productEditPanel.getTxtExpDate().getDate());
            String category = (String) productEditPanel.getTxtCategory().getSelectedItem();
            int supplierId = Integer.parseInt(productEditPanel.getTxtSupplierId().getText());
            
            // Gọi ProductDAO để cập nhật thông tin sản phẩm trong database
            boolean success = ProductDAO.updateProduct(productId, productName, purchasePrice, salePrice, productQuantity, unit, prodDate, expDate, category, supplierId);

            // Kiểm tra kết quả và thông báo cho người dùng
            if (success) {
                JOptionPane.showMessageDialog(null, "Cập nhật sản phẩm thành công!");
                loadProductTable(productModel); // Load lại bảng
            } else {
                JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi cập nhật sản phẩm.");
            }
        });
        
        // Nút Lưu các thay đổi ở form Tạo mặt hàng mới
        productAddPanel.getBtnSaveChanges().addActionListener(e -> {
            // Kiểm tra dữ liệu ở các textfield có đúng định dạng hay không
            if (!validateProductInputs(
                    productAddPanel.getTxtName(),
                    productAddPanel.getTxtPurchasePrice(),
                    productAddPanel.getTxtSalePrice(),
                    productAddPanel.getTxtQuantity(),
                    productAddPanel.getTxtUnit(),
                    productAddPanel.getTxtProdDate(),
                    productAddPanel.getTxtExpDate(),
                    productAddPanel.getTxtSupplierId())) {
                return;
            }
            
            // Lấy các dữ liệu từ textfield
            String name = productAddPanel.getTxtName().getText();
            double purchasePrice = Double.parseDouble(productAddPanel.getTxtPurchasePrice().getText());
            double salePrice = Double.parseDouble(productAddPanel.getTxtSalePrice().getText());
            int quantity = Integer.parseInt(productAddPanel.getTxtQuantity().getText());
            String unit = productAddPanel.getTxtUnit().getText();
            String prodDate = sdf.format(productAddPanel.getTxtProdDate().getDate());
            String expDate = sdf.format(productAddPanel.getTxtExpDate().getDate());
            String category = (String) productAddPanel.getTxtCategory().getSelectedItem();
            String supplierId = productAddPanel.getTxtSupplierId().getText();
            
            // Gọi DAO thêm sản phẩm
            boolean success = ProductDAO.addProduct(name, purchasePrice, salePrice, quantity, unit, prodDate, expDate, category, supplierId);

            if (success) {
                JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!");
                loadProductTable(productModel);
                conMan.showPanel("Blank");
            } else {
                JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại!");
            }
        });

    }

    private void loadProductTable(DefaultTableModel productModel) {
        productModel.setRowCount(0); // Xoá tất cả hàng cũ (nếu có)

        for (Vector<Object> row : ProductDAO.getProductList()) {
            row.add(null); // Tạo một ô trống ở cuối cho 3 nút xem/sửa/xóa
            productModel.addRow(row);
        }
    }
    
    // Hàm kiểm tra dữ liệu nhập vào
    public static boolean validateProductInputs(JTextField txtName, JTextField txtPurchasePrice,
                                            JTextField txtSalePrice, JTextField txtQuantity,
                                            JTextField txtUnit, JDateChooser txtProdDate,
                                            JDateChooser txtExpDate, JTextField txtSupplierId) {
        StringBuilder errorMsg = new StringBuilder();

        // 1. Tên mặt hàng
        if (txtName.getText().trim().isEmpty()) {
            errorMsg.append("- Tên mặt hàng không được để trống.\n");
        }

        // 2. Đơn giá nhập
        try {
            float purchasePrice = Float.parseFloat(txtPurchasePrice.getText().trim());
            if (purchasePrice < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            errorMsg.append("- Đơn giá nhập phải là số thực không âm.\n");
        }

        // 3. Đơn giá bán
        try {
            float salePrice = Float.parseFloat(txtSalePrice.getText().trim());
            if (salePrice < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            errorMsg.append("- Đơn giá bán phải là số thực không âm.\n");
        }

        // 4. Số lượng
        try {
            int quantity = Integer.parseInt(txtQuantity.getText().trim());
            if (quantity < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            errorMsg.append("- Số lượng phải là số nguyên không âm.\n");
        }

        // 5. Đơn vị tính
        if (txtUnit.getText().trim().isEmpty()) {
            errorMsg.append("- Đơn vị tính không được để trống.\n");
        }

        // 6. Ngày sản xuất và hạn sử dụng
        Date prodDate = txtProdDate.getDate();
        Date expDate = txtExpDate.getDate();

        if (prodDate == null || expDate == null) {
            errorMsg.append("- Ngày sản xuất và hạn sử dụng phải theo định dạng năm-tháng-ngày.\n");
        } else {
            if (!expDate.after(prodDate)) {
                errorMsg.append("- Hạn sử dụng phải sau ngày sản xuất.\n");
            }
        }
        // 7. Mã nhà cung cấp
        try {
            int supplierId = Integer.parseInt(txtSupplierId.getText().trim());
            if (supplierId < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            errorMsg.append("- Mã nhà cung cấp phải là số nguyên không âm.\n");
        }

        // ⚠️ Nếu có lỗi → hiển thị 1 lần
        if (!errorMsg.isEmpty()) {
            JOptionPane.showMessageDialog(null, errorMsg.toString(), "Lỗi kiểu dữ liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true; // ✅ Dữ liệu hợp lệ
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        btnClearAll = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        contentPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(231, 236, 240));
        setMaximumSize(new java.awt.Dimension(1071, 734));
        setMinimumSize(new java.awt.Dimension(1071, 734));
        setPreferredSize(new java.awt.Dimension(1071, 734));

        main.setBackground(new java.awt.Color(255, 255, 255));
        main.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        productTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null, null, null, null},
                {"2", null, null, null, null, null},
                {"3", null, null, null, null, null},
                {"5", null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {"4", null, null, null, null, null},
                {"6", null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Giá bán", "Số lượng có", "Đơn vị tính", "Hành động"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        productTable.setRowHeight(30);
        productTable.setSelectionBackground(new java.awt.Color(56, 66, 137));
        productTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        productTable.setShowGrid(false);
        jScrollPane1.setViewportView(productTable);

        btnAdd.setBackground(new java.awt.Color(0, 153, 0));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Thêm sản phẩm mới");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Mã sản phẩm");

        btnSearch.setBackground(new java.awt.Color(0, 102, 255));
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Tìm kiếm");

        btnClearAll.setBackground(new java.awt.Color(255, 51, 0));
        btnClearAll.setForeground(new java.awt.Color(255, 255, 255));
        btnClearAll.setText("Xóa tất cả");

        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(main);
        main.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
            .addGroup(mainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainLayout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClearAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdd))
                    .addGroup(mainLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearAll, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1))
        );

        contentPanel.setBackground(new java.awt.Color(231, 236, 240));
        contentPanel.setMaximumSize(new java.awt.Dimension(283, 734));
        contentPanel.setMinimumSize(new java.awt.Dimension(283, 734));
        contentPanel.setPreferredSize(new java.awt.Dimension(283, 734));
        contentPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClearAll;
    private javax.swing.JButton btnSearch;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel main;
    private javax.swing.JTable productTable;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
