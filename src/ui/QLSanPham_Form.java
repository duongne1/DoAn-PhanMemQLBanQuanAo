/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import dao.ChatLieuDAO;
import dao.KichThuocDAO;
import dao.LoaiSPDAO;
import dao.MauSacDAO;
import dao.SanPhamDAO;
import entity.ChatLieu;
import entity.KichThuoc;
import entity.LoaiSP;
import entity.MauSac;
import entity.SanPham;
import helper.DataVaildator;
import helper.ImageHelper;
import helper.MessageDialogHelper;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class QLSanPham_Form extends javax.swing.JPanel {

    private DefaultTableModel tblModel;
    private MainForm parentForm;
    private byte[] personalImage;

    private KichThuocDAO kt_dao;
    private ChatLieuDAO cl_dao;
    private LoaiSPDAO loai_dao;
    private MauSacDAO ms_dao;
    private SanPhamDAO sp_dao;

    /**
     * Creates new form ThemSanPham
     */
    public QLSanPham_Form() throws Exception {
        kt_dao = new KichThuocDAO();
        cl_dao = new ChatLieuDAO();
        loai_dao = new LoaiSPDAO();
        ms_dao = new MauSacDAO();
        sp_dao = new SanPhamDAO();
        initComponents();

        initTable();
        loadDataToTable1();

        List<KichThuoc> ls = kt_dao.finAll();
        cbKichThuoc.removeAllItems();
        for (KichThuoc item : ls) {

            cbKichThuoc.addItem(item.getTenKichThuoc());
        }

        List<ChatLieu> cl = cl_dao.finAll();
        cbChatLieu.removeAllItems();
        for (ChatLieu item : cl) {
            cbChatLieu.addItem(item.getTenChatLieu());
        }

        List<LoaiSP> loai = loai_dao.finAll();
        cbLoaiSP.removeAllItems();
        for (LoaiSP item : loai) {
            cbLoaiSP.addItem(item.getTenLoai());
        }

        List<MauSac> ms = ms_dao.finAll();
        cbMauSac.removeAllItems();
        for (MauSac item : ms) {
            cbMauSac.addItem(item.getTenMau());
        }

    }

    private void initTable() {
        tblModel = new DefaultTableModel();
        tblModel.setColumnIdentifiers(new String[]{"Mã sản phẩm", "Tên sản phẩm", "Loại", "Nhãn hiệu", "Kích thước", "Chất liệu", "Trạng thái kinh doanh", "Giá"});
        tblSP.setModel(tblModel);
    }

//    private void loadDataToTable() {
//        tblModel.setRowCount(0);
//        try {
//            sp_dao.loadData("select * from SanPham", tblModel);
//        } catch (Exception e) {
//        }
//
//    }
    public void loadDataToTable1() {
        try {
            SanPhamDAO sp = new SanPhamDAO();
            List<SanPham> list = sp.finAll();
            tblModel.setRowCount(0);
            for (SanPham sanPham : list) {
                tblModel.addRow(new Object[]{
                    sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getLoaiSP().getTenLoai(),
                    sanPham.getNhanHieu(), sanPham.getKichThuoc().getTenKichThuoc(),
                    sanPham.getChatLieu().getTenChatLieu(), sanPham.getTrangThai(), sanPham.getGia()
                });
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

        jLabel2 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNhanHieu = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbChatLieu = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbMauSac = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbKichThuoc = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbLoaiSP = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblAnh = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnAnh = new javax.swing.JButton();
        txtSoLuong = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        btnCapNhat = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        cbTinhTrang = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel2.setText("Mã sản phẩm:");

        txtMa.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtMa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtMa.setEnabled(false);
        txtMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel3.setText("Tên sản phẩm: ");

        txtTenSP.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTenSP.setText("Áo");
        txtTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSPActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel4.setText("Nhãn hiệu:");

        txtNhanHieu.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel5.setText("Chất liệu: ");

        cbChatLieu.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel6.setText("Loại sản phẩm:");

        cbMauSac.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel7.setText("Kích thước:");

        cbKichThuoc.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cbKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKichThuocActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel8.setText("Số lượng:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel10.setText("Giá: ");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel12.setText("Màu sắc: ");

        cbLoaiSP.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cbLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLoaiSPActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 16))); // NOI18N

        tblSP.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        tblSP.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1339, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 2));

        lblAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

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
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnAnh.setBackground(new java.awt.Color(0, 153, 153));
        btnAnh.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnAnh.setText("Ảnh");
        btnAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnhActionPerformed(evt);
            }
        });

        txtSoLuong.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        txtGia.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtGia.setText("100000");
        txtGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaActionPerformed(evt);
            }
        });

        btnCapNhat.setBackground(new java.awt.Color(0, 153, 153));
        btnCapNhat.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Actions-document-edit-icon-16.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel11.setText("Trạng thái");

        cbTinhTrang.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cbTinhTrang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn hàng", "Hết hàng", " " }));
        cbTinhTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTinhTrangActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton1.setText("Xoá");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(382, 382, 382)
                                .addComponent(btnThem)
                                .addGap(18, 18, 18)
                                .addComponent(btnLamMoi)
                                .addGap(18, 18, 18)
                                .addComponent(btnCapNhat)
                                .addGap(26, 26, 26)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(btnAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(51, 51, 51)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtNhanHieu, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtMa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(57, 57, 57)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(36, 36, 36)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cbChatLieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtTenSP)
                                            .addComponent(cbKichThuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtGia)
                                            .addComponent(cbTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNhanHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(cbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(245, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSPActionPerformed

    private void txtMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaActionPerformed

    private void cbKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKichThuocActionPerformed

    }//GEN-LAST:event_cbKichThuocActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        txtMa.setText("");
        txtTenSP.setText("");
        txtNhanHieu.setText("");
        txtSoLuong.setText("");
        txtGia.setText("");
        cbChatLieu.setSelectedIndex(0);
        cbKichThuoc.setSelectedIndex(0);
        cbLoaiSP.setSelectedIndex(0);
        cbMauSac.setSelectedIndex(0);
        lblAnh.setText("");

    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnhActionPerformed
        JFileChooser chose = new JFileChooser();
        chose.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    return f.getName().toLowerCase().endsWith(".jpg");
                }
            }

            @Override
            public String getDescription() {
                return "Image File (*.jpg)";
            }
        });
        if (chose.showOpenDialog(parentForm) == JFileChooser.CANCEL_OPTION) {
            return;
        }

        File file = chose.getSelectedFile();
        try {
            ImageIcon icon = new ImageIcon(file.getPath());
            Image img = ImageHelper.resize(icon.getImage(), 172, 153);
            ImageIcon resizedIcon = new ImageIcon(img);
            lblAnh.setIcon(resizedIcon);
            personalImage = ImageHelper.toByteArray(img, "jpg");
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialogHelper.showMessageDialog(parentForm,
                    e.getMessage(), "Lỗi");
        }
    }//GEN-LAST:event_btnAnhActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        StringBuilder sb = new StringBuilder();
        DataVaildator.vaildateEmpty(txtTenSP, sb, "Tên sản phẩm không được để trống");
        if (sb.length() > 0) {
            MessageDialogHelper.showErrorDialog(parentForm, sb.toString(), "Lỗi");
            return;
        }
        try {
            String ma = txtMa.getText();
            String ten = txtTenSP.getText();
            String nhanHieu = txtNhanHieu.getText();

            String kichThuoc = (String) cbKichThuoc.getSelectedItem();
            KichThuoc kt = kt_dao.getKichThuocByName(kichThuoc);

            String chatLieu = (String) cbChatLieu.getSelectedItem();
            ChatLieu cl = cl_dao.getChatLieuByName(chatLieu);

            String loai = (String) cbLoaiSP.getSelectedItem();
            LoaiSP lsp = loai_dao.getLoaiSPByName(loai);

            String mau = (String) cbMauSac.getSelectedItem();
            MauSac ms = ms_dao.getMauSacByName(mau);
            int soLuong = Integer.valueOf(txtSoLuong.getText());
            String trangThai = cbTinhTrang.getSelectedItem().toString();
            float gia = Float.valueOf(txtGia.getText());

            SanPham sp = new SanPham(ma, ten, nhanHieu, kt, cl, lsp, ms, soLuong, trangThai, gia, personalImage);
            if (sp_dao.insert(sp)) {
                MessageDialogHelper.showMessageDialog(parentForm, "Thêm sản phẩm thành công", "Thông báo");
                loadDataToTable1();
            } else {
                MessageDialogHelper.showMessageDialog(parentForm, "Thêm sản phẩm thất bại", "Cảnh báo");
            }

        } catch (Exception e) {
            e.printStackTrace();
            MessageDialogHelper.showErrorDialog(parentForm,
                    e.getMessage(), "Lỗi");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaActionPerformed

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked
        try {
            int row = tblSP.getSelectedRow();
            if (row >= 0) {
                String id = (String) tblSP.getValueAt(row, 0);
                SanPhamDAO dao = new SanPhamDAO();
                SanPham sp = sp_dao.getSanPhamByID(id);
                if (sp != null) {
                    txtMa.setText(sp.getMaSP());
                    txtTenSP.setText(sp.getTenSP());
                    txtNhanHieu.setText(sp.getNhanHieu());
                    cbKichThuoc.setSelectedItem(sp.getKichThuoc().getTenKichThuoc());
                    cbChatLieu.setSelectedItem(sp.getChatLieu().getTenChatLieu());
                    cbLoaiSP.setSelectedItem(sp.getLoaiSP().getTenLoai());
                    cbMauSac.setSelectedItem(sp.getMauSac().getTenMau());
                    txtSoLuong.setText(String.valueOf(sp.getSoLuong()));
                    txtGia.setText(String.valueOf(sp.getGia()));
                    if (sp.getAnh() != null) {
                        Image img = ImageHelper.createImageFromByteArray(sp.getAnh(), "jpg");
                        lblAnh.setIcon(new ImageIcon(img));
                        personalImage = sp.getAnh();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialogHelper.showErrorDialog(parentForm,
                    e.getMessage(), "Lỗi");
        }
    }//GEN-LAST:event_tblSPMouseClicked

    private void cbLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLoaiSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbLoaiSPActionPerformed

    private void cbTinhTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTinhTrangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTinhTrangActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        StringBuilder sb = new StringBuilder();
        DataVaildator.vaildateEmpty(txtMa, sb, "Mã sinh viên không được để trống");
        DataVaildator.vaildateEmpty(txtTenSP, sb, "Tên sản phẩm không được để trống");
        if (sb.length() > 0) {
            MessageDialogHelper.showErrorDialog(parentForm, sb.toString(), "Lỗi");
            return;
        }
        if (MessageDialogHelper.showConfirmDialog(parentForm, "Bạn có muốn cập nhật sản phẩm không?", "Hỏi")
                == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            
           String ma = txtMa.getText();
            String ten = txtTenSP.getText();
            String nhanHieu = txtNhanHieu.getText();

            String kichThuoc = (String) cbKichThuoc.getSelectedItem();
            KichThuoc kt = kt_dao.getKichThuocByName(kichThuoc);

            String chatLieu = (String) cbChatLieu.getSelectedItem();
            ChatLieu cl = cl_dao.getChatLieuByName(chatLieu);

            String loai = (String) cbLoaiSP.getSelectedItem();
            LoaiSP lsp = loai_dao.getLoaiSPByName(loai);

            String mau = (String) cbMauSac.getSelectedItem();
            MauSac ms = ms_dao.getMauSacByName(mau);
            
            String tinhTrang = cbTinhTrang.getSelectedItem().toString();
            int soLuong = Integer.valueOf(txtSoLuong.getText());
            String trangThai = cbTinhTrang.getSelectedItem().toString();
            float gia = Float.valueOf(txtGia.getText());

            SanPham sp = new SanPham(ma, ten, nhanHieu, kt, cl, lsp, ms, soLuong, trangThai, gia, personalImage);
            if (sp_dao.update(sp)) {
                MessageDialogHelper.showMessageDialog(parentForm, "Cập nhật sản phẩm thành công", "Thông báo");
                loadDataToTable1();
            } else {
                MessageDialogHelper.showMessageDialog(parentForm, "Cập nhật sản phẩm thất bại", "Cảnh báo");
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialogHelper.showErrorDialog(parentForm,
                    e.getMessage(), "Lỗi");
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnh;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cbChatLieu;
    private javax.swing.JComboBox<String> cbKichThuoc;
    private javax.swing.JComboBox<String> cbLoaiSP;
    private javax.swing.JComboBox<String> cbMauSac;
    private javax.swing.JComboBox<String> cbTinhTrang;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JTable tblSP;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtNhanHieu;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables


}