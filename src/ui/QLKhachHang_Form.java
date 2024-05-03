/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import dao.KhachHangDAO;
import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import entity.KhachHang;
import entity.NhanVien;
import entity.TaiKhoan;
import helper.DataVaildator;
import helper.MessageDialogHelper;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class QLKhachHang_Form extends javax.swing.JPanel {

    private DefaultTableModel tblModel;
    private MainForm parentForm;
    private TaiKhoanDAO tk_dao;
    private KhachHangDAO kh_dao;

    /**
     * Creates new form KhachHang
     */
    public QLKhachHang_Form() throws Exception {
        tk_dao = new TaiKhoanDAO();
        kh_dao = new KhachHangDAO();
        initComponents();
        initTable();
        loadDataToTable1();

        List<TaiKhoan> ls = tk_dao.finAll();
    }

    public void loadDataToTable1() {
        try {
            KhachHangDAO nv = new KhachHangDAO();
            List<KhachHang> list = nv.finAll();

            tblModel.setRowCount(0);
            for (KhachHang khachHang : list) {
                String gt = "";
                if (khachHang.isGioiTinh() == true) {
                    gt = "Nam";
                } else {
                    gt = "Nữ";
                }
                {

                    tblModel.addRow(new Object[]{
                        khachHang.getMaKH(), khachHang.getHoTen(), gt,
                        khachHang.getSoDT(), khachHang.getDiaChi()
                    });
                }
            }
            tblModel.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialogHelper.showErrorDialog(parentForm, e.getMessage(), "Lỗi");
        }
    }

    private void initTable() {
        tblModel = new DefaultTableModel();
        tblModel.setColumnIdentifiers(new String[]{"Mã khách hàng", "Tên khách hàng", "Giới tính", "Số điện thoại", "Địa chỉ"});
        tblKH.setModel(tblModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        txtSoDT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKH = new javax.swing.JTable();

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel2.setText("Mã khách hàng:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel3.setText("Giới tính:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel4.setText("Địa chỉ:");

        txtMaKH.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtMaKH.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtMaKH.setEnabled(false);

        txtDiaChi.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        rdoNu.setText("Nữ");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel5.setText("Tên khách hàng:");

        txtTenKH.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        txtSoDT.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel6.setText("Số điện thoại:");

        btnThem.setBackground(new java.awt.Color(0, 153, 153));
        btnThem.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/them.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(0, 153, 153));
        btnLamMoi.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lam_moi.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");

        btnCapNhat.setBackground(new java.awt.Color(0, 153, 153));
        btnCapNhat.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Actions-document-edit-icon-16.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(0, 153, 153));
        btnXoa.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 16))); // NOI18N

        tblKH.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        tblKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKH);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoi)
                        .addGap(26, 26, 26)
                        .addComponent(btnCapNhat)
                        .addGap(30, 30, 30)
                        .addComponent(btnXoa))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rdoNam)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdoNu))
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(61, 61, 61)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)
                                .addComponent(jLabel5)))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                            .addComponent(txtSoDT))))
                .addGap(0, 95, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
            if (checkData() == true) {
                String ten = txtTenKH.getText();
                boolean gt;
                if (rdoNam.isSelected()) {
                    gt = true;
                } else {
                    gt = false;
                }
                String sdt = txtSoDT.getText();
                String diaChi = txtDiaChi.getText();

                KhachHang kh = new KhachHang(ten, gt, sdt, diaChi);
                if (kh_dao.insert(kh)) {
                    MessageDialogHelper.showMessageDialog(parentForm, "Thêm khách hàng thành công", "Thông báo");
                    loadDataToTable1();
                } else {
                    MessageDialogHelper.showMessageDialog(parentForm, "Thêm khách hàng thất bại", "Cảnh báo");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            MessageDialogHelper.showErrorDialog(parentForm,
                    e.getMessage(), "Lỗi");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKHMouseClicked
        try {
            int row = tblKH.getSelectedRow();
            if (row >= 0) {
                String id = (String) tblKH.getValueAt(row, 0);
                KhachHangDAO dao = new KhachHangDAO();
                KhachHang kh = kh_dao.getKhachHangByID(id);
                if (kh != null) {
                    txtMaKH.setText(kh.getMaKH());
                    txtTenKH.setText(kh.getHoTen());
                    if (String.valueOf(kh.isGioiTinh()).equals("Nam")) {
                        rdoNam.isSelected();
                    } else {
                        rdoNu.isSelected();
                    }
                    txtSoDT.setText(kh.getSoDT());
                    txtDiaChi.setText(kh.getDiaChi());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialogHelper.showErrorDialog(parentForm,
                    e.getMessage(), "Lỗi");
        }
    }//GEN-LAST:event_tblKHMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed

        int row = tblKH.getSelectedRow();
        if (row != 1) {
            if (MessageDialogHelper.showConfirmDialog(parentForm, "Bạn có muốn xoá khách hàng này không?", "Hỏi")
                    == JOptionPane.NO_OPTION) {
                return;
            }
        }
        try {
            KhachHangDAO dao = new KhachHangDAO();
            String id = tblModel.getValueAt(row, 0).toString();
            if (dao.delete(id)) {
                MessageDialogHelper.showMessageDialog(parentForm, "Khách hàng đã được xoá", "Thông báo");
                loadDataToTable1();
            } else {
                MessageDialogHelper.showMessageDialog(parentForm, "Khách hàng không xoá được được do lỗi", "Cảnh báo");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(parentForm, "Không thể xoá khách hàng này");
        }


    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        try {
            if (checkData() == true) {
                String ma = txtMaKH.getText();
                String ten = txtTenKH.getText();
                boolean gt;
                if (rdoNam.isSelected()) {
                    gt = true;
                } else {
                    gt = false;
                }
                String sdt = txtSoDT.getText();
                String diaChi = txtDiaChi.getText();

                KhachHang kh = new KhachHang(ma, ten, gt, sdt, diaChi);
                if (kh_dao.update(kh)) {
                    MessageDialogHelper.showMessageDialog(parentForm, "Cập nhật khách hàng thành công", "Thông báo");
                    loadDataToTable1();
                } else {
                    MessageDialogHelper.showMessageDialog(parentForm, "Cập nhật khách hàng thất bại", "Cảnh báo");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialogHelper.showErrorDialog(parentForm,
                    e.getMessage(), "Lỗi");
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private boolean checkData() throws Exception {
        String tenKH = txtTenKH.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String soDT = txtSoDT.getText().trim();
        String dt = soDT + "          ";

        if (!(tenKH.length() > 0)) {
            JOptionPane.showMessageDialog(parentForm, "Tên không được để trống");
            txtTenKH.requestFocus();
            return false;
        }
        if (!tenKH.matches("^[^0-9]+$")) {
            JOptionPane.showMessageDialog(parentForm, "Tên không được có số");
            txtTenKH.requestFocus();
            return false;
        }

        if (!(diaChi.length() > 0)) {
            JOptionPane.showMessageDialog(parentForm, "Địa chỉ không được để trống");
            txtDiaChi.requestFocus();
            return false;
        }
        if (!(soDT.length() > 0 && soDT.matches("^0\\d{9}"))) {
            JOptionPane.showMessageDialog(this, "Số điện thoại gồm dãy 10 số bắt đầu từ số 0");
            txtSoDT.requestFocus();
            return false;
        } else {
            List<KhachHang> dsKH = kh_dao.finAll();
            for (KhachHang khachHang : dsKH) {
                if (khachHang.getSoDT().equalsIgnoreCase(dt)) {
                    JOptionPane.showMessageDialog(parentForm, "Số điện thoại đã tồn tại");
                    txtSoDT.requestFocus();
                    return false;
                }
            }
        }
        return true;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblKH;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
