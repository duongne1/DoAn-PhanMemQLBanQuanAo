/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import dao.KhachHangDAO;
import entity.KhachHang;
import entity.NhanVien;
import helper.MessageDialogHelper;
import java.awt.Color;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class TimKiemKhacHang_Form extends javax.swing.JPanel {

    private DefaultTableModel tblModel;
    private MainForm parentForm;
    private KhachHangDAO kh_dao = new KhachHangDAO();

    /**
     * Creates new form TimKiemKhacHang_Form
     */
    public TimKiemKhacHang_Form() {
        initComponents();
        initTable();
        loadDataToTable();
    }

    private void initTable() {
        tblModel = new DefaultTableModel();
        tblModel.setColumnIdentifiers(new String[]{"Mã khách hàng", "Tên khách hàng", "Giới tính", "Số điện thoại", "Địa chỉ"});
        tblKH.setModel(tblModel);
    }

    public void loadDataToTable() {
        try {
            List<KhachHang> list = kh_dao.finAll();

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        btnTimNV = new javax.swing.JButton();
        btnXemTC = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKH = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 16))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel1.setText("Tên khách hàng");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel3.setText("Số điện thoại:");

        txtTenKH.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTenKH.setText("Nhập vào họ hoặc tên để tìm");
        txtTenKH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTenKHFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTenKHFocusLost(evt);
            }
        });
        txtTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKHActionPerformed(evt);
            }
        });

        txtSDT.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtSDT.setText("Nhập số điện thoại để tìm");
        txtSDT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSDTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSDTFocusLost(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(0, 153, 153));
        btnLamMoi.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lam_moi.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnTimNV.setBackground(new java.awt.Color(0, 153, 153));
        btnTimNV.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnTimNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search-icon-16.png"))); // NOI18N
        btnTimNV.setText("Tìm kiếm");
        btnTimNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimNVActionPerformed(evt);
            }
        });

        btnXemTC.setBackground(new java.awt.Color(0, 153, 153));
        btnXemTC.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnXemTC.setText("Xem tất cả");
        btnXemTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemTCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                            .addComponent(txtSDT)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(btnTimNV)
                        .addGap(32, 32, 32)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXemTC)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTimNV, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(btnXemTC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 16))); // NOI18N

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("Tìm kiếm khách hàng");
        jPanel2.getAccessibleContext().setAccessibleName("Danh sách khách hàng");
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenKHFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenKHFocusGained
        if (txtTenKH.getText().trim().equals("Nhập vào họ hoặc tên để tìm")) {
            txtTenKH.setText("");
        }
        txtTenKH.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_txtTenKHFocusGained

    private void txtTenKHFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenKHFocusLost
        if (txtTenKH.getText().trim().equals("")) {
            txtTenKH.setText("Nhập vào họ hoặc tên để tìm");
        }
        txtTenKH.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_txtTenKHFocusLost

    private void txtTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHActionPerformed

    private void txtSDTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSDTFocusGained
        if (txtSDT.getText().trim().equals("Nhập số điện thoại để tìm")) {
            txtSDT.setText("");
        }
        txtSDT.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_txtSDTFocusGained

    private void txtSDTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSDTFocusLost
        if (txtSDT.getText().trim().equals("")) {
            txtSDT.setText("Nhập số điện thoại để tìm");
        }
        txtSDT.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_txtSDTFocusLost

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        txtTenKH.setText("");
        txtSDT.setText("");
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnTimNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimNVActionPerformed
        try {
            String ten = txtTenKH.getText();
            String sdt = txtSDT.getText();
            if (ten.equals("")) {
                KhachHang a = kh_dao.getKhachHangTheoSDT(sdt);
                tblModel.setRowCount(0);
                String gt = "";
                if (a.isGioiTinh() == true) {
                    gt = "Nam";
                } else {
                    gt = "Nữ";
                }
                {
                    tblModel.addRow(new Object[]{
                        a.getMaKH(), a.getHoTen(), gt,
                        a.getSoDT(), a.getDiaChi()
                    });
                }
            } else {
                List<KhachHang> list = kh_dao.getKhachHangTheoTen(ten);

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
            }

            tblModel.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialogHelper.showErrorDialog(parentForm, e.getMessage(), "Lỗi");
        }
    }//GEN-LAST:event_btnTimNVActionPerformed

    private void tblKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKHMouseClicked
        try {
            int row = tblKH.getSelectedRow();
            if (row >= 0) {
                String id = (String) tblKH.getValueAt(row, 0);
                KhachHang kh = kh_dao.getKhachHangByID(id);
                if (kh != null) {
                    txtTenKH.setText(kh.getHoTen());
                    txtSDT.setText(kh.getSoDT());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialogHelper.showErrorDialog(parentForm,
                    e.getMessage(), "Lỗi");
        }
    }//GEN-LAST:event_tblKHMouseClicked

    public void xoaDLTable() {
        for (int i = tblModel.getRowCount() - 1; i >= 0; i--) {
            tblModel.removeRow(i);
        }
    }
    private void btnXemTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemTCActionPerformed
        xoaDLTable();
        loadDataToTable();
    }//GEN-LAST:event_btnXemTCActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnTimNV;
    private javax.swing.JButton btnXemTC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblKH;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
