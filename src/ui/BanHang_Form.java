/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import com.sun.jdi.connect.spi.Connection;
import java.sql.DriverManager;

import connect.DatabaseHelper;
import dao.ChatLieuDAO;
import dao.ChiTietDonHangDAO;
import dao.ChiTietHDDAO;
import dao.DonDatHangDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.KichThuocDAO;
import dao.LoaiSPDAO;
import dao.MauSacDAO;
import dao.NhanVienDAO;
import dao.SanPhamDAO;
import entity.ChatLieu;
import entity.ChiTietDonHang;
import entity.ChiTietHD;
import entity.DonDatHang;
import entity.HoaDon;
import entity.KhachHang;
import entity.KichThuoc;
import entity.LoaiSP;
import entity.NhanVien;
import entity.SanPham;
import helper.ImageHelper;
import helper.MessageDialogHelper;
import helper.SharedData;
import java.awt.Color;
import java.awt.Image;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import static ui.QLHoaDon_Form.parentForm;

/**
 *
 * @author ADMIN
 */
public class BanHang_Form extends javax.swing.JPanel {

    private static KhachHang khachHang = null;
    private static SanPham sanPham = null;
    private static HoaDon hoaDon = new HoaDon();
    private static ChiTietHD CTHD = new ChiTietHD();
    private static ChiTietDonHang CTDH = new ChiTietDonHang();
    private static ChiTietHDDAO cthd_dao = new ChiTietHDDAO();
    private static ChiTietDonHangDAO ctdh_dao = new ChiTietDonHangDAO();
    private XuatHoaDonBH_Form xuatHD = new XuatHoaDonBH_Form();

    public static DefaultTableModel tblModel1, tblModel2;
    private MainForm parentForm;
    private byte[] personalImage;

    private KichThuocDAO kt_dao;
    private ChatLieuDAO cl_dao;
    private LoaiSPDAO loai_dao;
    private MauSacDAO ms_dao;
    private SanPhamDAO sp_dao;
    private KhachHangDAO kh_dao;
    private DonDatHangDAO dh_dao;
    private NhanVienDAO nv_dao = new NhanVienDAO();
    private static HoaDonDAO hd_dao = new HoaDonDAO();

//    private NhanVien nhanVien = nv_dao.getNhanVienByID("NV1");
//    private HoaDon hoadon = hd_dao.getHoaDonByID("HD11");
    /**
     * Creates new form DatHangForm
     */
    public BanHang_Form() throws Exception {
        dh_dao = new DonDatHangDAO();
        kh_dao = new KhachHangDAO();
        kt_dao = new KichThuocDAO();
        cl_dao = new ChatLieuDAO();
        loai_dao = new LoaiSPDAO();
        ms_dao = new MauSacDAO();
        sp_dao = new SanPhamDAO();
        initComponents();
        initTable();
        loadDataToTable();
        initTable1();
//        loadDataToTable1();

        jPanel2.setFocusable(true);

        List<KichThuoc> ls = kt_dao.finAll();
        for (KichThuoc item : ls) {

            cboKichThuoc.addItem(item.getTenKichThuoc());
        }

        List<ChatLieu> cl = cl_dao.finAll();
        for (ChatLieu item : cl) {
            cboChatLieu.addItem(item.getTenChatLieu());
        }

        List<LoaiSP> loai = loai_dao.finAll();
        for (LoaiSP item : loai) {
            cboLoaiSP.addItem(item.getTenLoai());
        }
    }

    private void initTable() {
        tblModel1 = new DefaultTableModel();
        tblModel1.setColumnIdentifiers(new String[]{"Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Nhãn hiệu", "Giá bán", "Số lượng", "Chất liệu", "Màu sắc", "Kích thước"});
        tblSP.setModel(tblModel1);
    }

    public void loadDataToTable() {
        try {
            SanPhamDAO sp = new SanPhamDAO();
            List<SanPham> list = sp.finAll1();
            tblModel1.setRowCount(0);
            for (SanPham sanPham : list) {
                tblModel1.addRow(new Object[]{
                    sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getLoaiSP().getTenLoai(),
                    sanPham.getNhanHieu(), sanPham.getGia(), sanPham.getSoLuong(),
                    sanPham.getChatLieu().getTenChatLieu(), sanPham.getMauSac().getTenMau(),
                    sanPham.getKichThuoc().getTenKichThuoc()
                });
            }
            tblModel1.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialogHelper.showErrorDialog(parentForm, e.getMessage(), "Lỗi");
        }
    }

    private void initTable1() {
        tblModel2 = new DefaultTableModel();
        tblModel2.setColumnIdentifiers(new String[]{"Mã sản phẩm", "Tên sản phẩm", "Nhãn hiệu", "Chất liệu", "Kích thước", "Số lượng", "Giá bán", "Tổng tiền"});
        tblDonHang.setModel(tblModel2);
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
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboLoaiSP = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cboChatLieu = new javax.swing.JComboBox<>();
        cboKichThuoc = new javax.swing.JComboBox<>();
        btnTim = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtTimSDT = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnDatHang = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        btnTimKH = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        txtTenKH = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtSDTKH = new javax.swing.JTextField();
        txtTienKhach = new javax.swing.JTextField();
        ckoXuat = new javax.swing.JCheckBox();
        lblTongTien = new javax.swing.JLabel();
        lblTienTra = new javax.swing.JLabel();
        btnLamMoi = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        spnSoLuong = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDonHang = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        lblAnh = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm Quần Áo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 16))); // NOI18N

        jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel2.setText("Tên sản phẩm:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel3.setText("Loại sản phẩm:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel5.setText("Chất liệu: ");

        cboLoaiSP.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cboLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cboLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiSPActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel6.setText("Kích thước:");

        cboChatLieu.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cboChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cboChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChatLieuActionPerformed(evt);
            }
        });

        cboKichThuoc.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cboKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cboKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKichThuocActionPerformed(evt);
            }
        });

        btnTim.setBackground(new java.awt.Color(0, 153, 153));
        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search-icon-32.png"))); // NOI18N
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cboChatLieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm khách hàng:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 16))); // NOI18N

        txtTimSDT.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTimSDT.setForeground(new java.awt.Color(153, 153, 153));
        txtTimSDT.setText("Nhập số điện thoại để tìm");
        txtTimSDT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimSDTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimSDTFocusLost(evt);
            }
        });
        txtTimSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimSDTActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel13.setText("Tổng tiền:");

        btnDatHang.setBackground(new java.awt.Color(0, 153, 153));
        btnDatHang.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnDatHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cargo.png"))); // NOI18N
        btnDatHang.setText("Đặt hàng");
        btnDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatHangActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel16.setText("Tiền trả lại:");

        jButton4.setBackground(new java.awt.Color(0, 153, 153));
        jButton4.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/xoa.png"))); // NOI18N
        jButton4.setText("Xoá");

        btnTimKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search-icon-32.png"))); // NOI18N
        btnTimKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKHActionPerformed(evt);
            }
        });

        btnThanhToan.setBackground(new java.awt.Color(0, 153, 153));
        btnThanhToan.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/payment-method.png"))); // NOI18N
        btnThanhToan.setText("Mua hàng");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        txtTenKH.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTenKH.setText("Tên khách hàng");
        txtTenKH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTenKHFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTenKHFocusLost(evt);
            }
        });

        txtDiaChi.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtDiaChi.setText("Địa chỉ");
        txtDiaChi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDiaChiFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiaChiFocusLost(evt);
            }
        });

        txtSDTKH.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtSDTKH.setText("Số điện thoại");
        txtSDTKH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSDTKHFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSDTKHFocusLost(evt);
            }
        });
        txtSDTKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTKHActionPerformed(evt);
            }
        });

        txtTienKhach.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTienKhach.setForeground(new java.awt.Color(204, 204, 204));
        txtTienKhach.setText("Tiền khách đưa");
        txtTienKhach.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTienKhachFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTienKhachFocusLost(evt);
            }
        });
        txtTienKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachActionPerformed(evt);
            }
        });

        ckoXuat.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        ckoXuat.setText("Xuất hoá đơn");

        lblTongTien.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(255, 0, 51));
        lblTongTien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTongTien.setText("0 VND");

        lblTienTra.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTienTra.setForeground(new java.awt.Color(255, 0, 51));
        lblTienTra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTienTra.setText("0 VND");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSDTKH)
                                    .addComponent(txtTimSDT))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                    .addComponent(txtDiaChi))
                                .addGap(26, 26, 26))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(ckoXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDatHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(82, 82, 82))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtTienKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTienTra, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTenKH)
                        .addComponent(btnDatHang))
                    .addComponent(txtTimSDT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4)
                    .addComponent(txtSDTKH, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtDiaChi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThanhToan)
                        .addComponent(ckoXuat)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTienTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnLamMoi.setBackground(new java.awt.Color(0, 153, 153));
        btnLamMoi.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lam_moi.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(0, 153, 153));
        btnThem.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/them.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        spnSoLuong.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        spnSoLuong.setModel(new javax.swing.SpinnerNumberModel(1, 1, 50, 1));
        spnSoLuong.setRequestFocusEnabled(false);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel8.setText("Số lượng:");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn đặt hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 16))); // NOI18N

        tblDonHang.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        tblDonHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Chất liệu", "Màu sắc", "Kích thước", "Số lượng", "Giá", "Tổng tiền", ""
            }
        ));
        jScrollPane2.setViewportView(tblDonHang);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tblSP.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        tblSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Nhãn hiệu", "Giá bán", "Số lượng", "Chất liệu", "Màu sắc", "Kích thước"
            }
        ));
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSP);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 2));

        lblAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(184, 184, 184)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnThem)
                                .addGap(18, 18, 18)
                                .addComponent(btnLamMoi)))
                        .addGap(0, 31, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        spnSoLuong.getAccessibleContext().setAccessibleName("");
        spnSoLuong.getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimSDTActionPerformed

    private void txtTimSDTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimSDTFocusGained
        if (txtTimSDT.getText().trim().equals("Nhập số điện thoại để tìm")) {
            txtTimSDT.setText("");
        }
        txtTimSDT.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_txtTimSDTFocusGained

    private void txtTimSDTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimSDTFocusLost
        if (txtTimSDT.getText().trim().equals("")) {
            txtTimSDT.setText("Nhập số điện thoại để tìm");
        }
        txtTimSDT.setForeground(new Color(204, 204, 204));
    }//GEN-LAST:event_txtTimSDTFocusLost

    private void cboChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChatLieuActionPerformed
        try {
            String chatLieu = cboChatLieu.getSelectedItem().toString();
            String ma;
            if (chatLieu.equals("Vải Cotton")) {
                ma = "CL1";
            } else if (chatLieu.equals("Vải Kaki")) {
                ma = "CL2";
            } else if (chatLieu.equals("Vải Jean")) {
                ma = "CL3";
            } else if (chatLieu.equals("Vải Kate")) {
                ma = "CL4";
            } else if (chatLieu.equals("Vải Nỉ")) {
                ma = "CL10";
            } else {
                ma = "CL0";
            }

            if (ma.equals("CL0")) {
                xoaDLTable();
                SanPhamDAO sp = new SanPhamDAO();
                List<SanPham> list = sp.finAll1();
                tblModel1.setRowCount(0);
                for (SanPham sanPham : list) {
                    tblModel1.addRow(new Object[]{
                        sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getLoaiSP().getTenLoai(),
                        sanPham.getNhanHieu(), sanPham.getGia(), sanPham.getSoLuong(),
                        sanPham.getChatLieu().getTenChatLieu(), sanPham.getMauSac().getTenMau(),
                        sanPham.getKichThuoc().getTenKichThuoc()
                    });
                }
                tblModel1.fireTableDataChanged();
            } else if (ma.equals("CL1")) {
                xoaDLTable();
                SanPhamDAO sp = new SanPhamDAO();
                List<SanPham> list = sp.getDSSPTheoChatLieu(ma);
                tblModel1.setRowCount(0);
                for (SanPham sanPham : list) {
                    tblModel1.addRow(new Object[]{
                        sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getLoaiSP().getTenLoai(),
                        sanPham.getNhanHieu(), sanPham.getGia(), sanPham.getSoLuong(),
                        sanPham.getChatLieu().getTenChatLieu(), sanPham.getMauSac().getTenMau(),
                        sanPham.getKichThuoc().getTenKichThuoc()
                    });
                }
                tblModel1.fireTableDataChanged();
            } else if (ma.equals("CL2")) {
                xoaDLTable();
                SanPhamDAO sp = new SanPhamDAO();
                List<SanPham> list = sp.getDSSPTheoChatLieu(ma);
                tblModel1.setRowCount(0);
                for (SanPham sanPham : list) {
                    tblModel1.addRow(new Object[]{
                        sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getLoaiSP().getTenLoai(),
                        sanPham.getNhanHieu(), sanPham.getGia(), sanPham.getSoLuong(),
                        sanPham.getChatLieu().getTenChatLieu(), sanPham.getMauSac().getTenMau(),
                        sanPham.getKichThuoc().getTenKichThuoc()
                    });
                }
                tblModel1.fireTableDataChanged();
            } else if (ma.equals("CL3")) {
                xoaDLTable();
                SanPhamDAO sp = new SanPhamDAO();
                List<SanPham> list = sp.getDSSPTheoChatLieu(ma);
                tblModel1.setRowCount(0);
                for (SanPham sanPham : list) {
                    tblModel1.addRow(new Object[]{
                        sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getLoaiSP().getTenLoai(),
                        sanPham.getNhanHieu(), sanPham.getGia(), sanPham.getSoLuong(),
                        sanPham.getChatLieu().getTenChatLieu(), sanPham.getMauSac().getTenMau(),
                        sanPham.getKichThuoc().getTenKichThuoc()
                    });
                }
                tblModel1.fireTableDataChanged();
            } else if (ma.equals("CL4")) {
                xoaDLTable();
                SanPhamDAO sp = new SanPhamDAO();
                List<SanPham> list = sp.getDSSPTheoChatLieu(ma);
                tblModel1.setRowCount(0);
                for (SanPham sanPham : list) {
                    tblModel1.addRow(new Object[]{
                        sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getLoaiSP().getTenLoai(),
                        sanPham.getNhanHieu(), sanPham.getGia(), sanPham.getSoLuong(),
                        sanPham.getChatLieu().getTenChatLieu(), sanPham.getMauSac().getTenMau(),
                        sanPham.getKichThuoc().getTenKichThuoc()
                    });
                }
                tblModel1.fireTableDataChanged();
            } else if (ma.equals("CL10")) {
                xoaDLTable();
                SanPhamDAO sp = new SanPhamDAO();
                List<SanPham> list = sp.getDSSPTheoChatLieu(ma);
                tblModel1.setRowCount(0);
                for (SanPham sanPham : list) {
                    tblModel1.addRow(new Object[]{
                        sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getLoaiSP().getTenLoai(),
                        sanPham.getNhanHieu(), sanPham.getGia(), sanPham.getSoLuong(),
                        sanPham.getChatLieu().getTenChatLieu(), sanPham.getMauSac().getTenMau(),
                        sanPham.getKichThuoc().getTenKichThuoc()
                    });
                }
                tblModel1.fireTableDataChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialogHelper.showErrorDialog(parentForm,
                    e.getMessage(), "Lỗi");
        }

    }//GEN-LAST:event_cboChatLieuActionPerformed

    private void txtTenKHFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenKHFocusGained
        if (txtTenKH.getText().trim().equals("Tên khách hàng")) {
            txtTenKH.setText("");
        }
        txtTenKH.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_txtTenKHFocusGained

    private void txtTenKHFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenKHFocusLost
        if (txtTenKH.getText().trim().equals("")) {
            txtTenKH.setText("Tên khách hàng");
        }
        txtTenKH.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_txtTenKHFocusLost

    private void txtSDTKHFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSDTKHFocusGained
        if (txtSDTKH.getText().trim().equals("Số điện thoại")) {
            txtSDTKH.setText("");
        }
        txtSDTKH.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_txtSDTKHFocusGained

    private void txtSDTKHFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSDTKHFocusLost
        if (txtSDTKH.getText().trim().equals("")) {
            txtSDTKH.setText("Số điện thoại");
        }
        txtSDTKH.setForeground(new Color(0, 0, 0));
        String sdt = txtSDTKH.getText();
        try {
            KhachHang kh = kh_dao.getKhachHangTheoSDT(sdt);
            if (kh != null) {
                txtTenKH.setText(kh.getHoTen());
                txtDiaChi.setText(kh.getDiaChi());
            }
        } catch (Exception ex) {
            Logger.getLogger(BanHang_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtSDTKHFocusLost

    private void txtDiaChiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiaChiFocusGained
        if (txtDiaChi.getText().trim().equals("Địa chỉ")) {
            txtDiaChi.setText("");
        }
        txtDiaChi.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_txtDiaChiFocusGained

    private void txtDiaChiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiaChiFocusLost
        if (txtDiaChi.getText().trim().equals("")) {
            txtDiaChi.setText("Địa chỉ");
        }
        txtDiaChi.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_txtDiaChiFocusLost

    private void txtTienKhachFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTienKhachFocusGained
        if (txtTienKhach.getText().trim().equals("Tiền khách đưa")) {
            txtTienKhach.setText("");
        }
        txtTienKhach.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_txtTienKhachFocusGained

    private void txtTienKhachFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTienKhachFocusLost
        if (txtTienKhach.getText().trim().equals("")) {
            txtTienKhach.setText("Tiền khách đưa");
        }
        txtTienKhach.setForeground(new Color(0, 0, 0));

    }//GEN-LAST:event_txtTienKhachFocusLost

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked
        try {
            int row = tblSP.getSelectedRow();
            if (row >= 0) {
                String id = (String) tblSP.getValueAt(row, 0);
                SanPhamDAO dao = new SanPhamDAO();
                SanPham sp = sp_dao.getSanPhamByID(id);
                if (sp != null) {
                    if (sp.getAnh() != null) {

                        Image img1 = ImageHelper.createImageFromByteArray(sp.getAnh(), "jpg");
                        lblAnh.setIcon(new ImageIcon(img1));
                        ImageHelper.resize(img1, 163, 101);
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

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
            int row = tblSP.getSelectedRow();
            if (row != -1) {
                String maSP = tblModel1.getValueAt(row, 0).toString();

                String tenSP = tblModel1.getValueAt(row, 1).toString();
                String nhanHieu = tblModel1.getValueAt(row, 3).toString();
                String chatLieu = tblModel1.getValueAt(row, 6).toString();
                String kichThuoc = tblModel1.getValueAt(row, 8).toString();
                Double giaBan = Double.valueOf(tblModel1.getValueAt(row, 4).toString());
                int soLuongMua = (int) spnSoLuong.getValue();
                double thanhTien = giaBan * soLuongMua;
                int soLuong = Integer.valueOf(tblModel1.getValueAt(row, 5).toString());
                if (soLuong - soLuongMua >= 0) {
                    if (tblDonHang.getRowCount() <= 0) {
                        tblModel2.addRow(new Object[]{
                            maSP, tenSP, nhanHieu, chatLieu, kichThuoc, soLuongMua, giaBan, thanhTien
                        });
                    } else {
                        int hang = tblDonHang.getRowCount();
                        for (int i = 0; i < hang; i++) {
                            String ma = tblModel2.getValueAt(i, 0).toString();
                            int sl = Integer.valueOf(tblModel2.getValueAt(i, 5).toString());
                            if (ma.contains(maSP)) {
                                sl += soLuongMua;
                                if (soLuong - sl >= 0) {
                                    tblModel2.setValueAt(sl, i, 5);
                                    double tongTien = sl * giaBan;
                                    tblModel2.setValueAt(tongTien, i, 7);
                                    loadTongTien();
                                    return;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Cửa hàng không đủ số lượng");
                                    return;
                                }
                            }
                        }
                        tblModel2.addRow(new Object[]{
                            maSP, tenSP, nhanHieu, chatLieu, kichThuoc, soLuongMua, giaBan, thanhTien
                        });
                    }
                    loadTongTien();
                } else {
                    JOptionPane.showMessageDialog(null, "Cửa hàng không đủ số lượng");
                    return;
                }
            } else {
                MessageDialogHelper.showMessageDialog(parentForm, "Bạn phải chọn sản phẩm để mua ", "Thông báo");
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialogHelper.showErrorDialog(parentForm,
                    e.getMessage(), "Lỗi");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    public void loadTongTien() {
        DecimalFormat dft = new DecimalFormat("###,###,### VND");
        int tong = 0;
        int colum = tblDonHang.getRowCount();
        for (int i = 0; i < colum; i++) {
            Double tongTien = Double.valueOf(tblModel2.getValueAt(i, 7).toString());
            tong += tongTien;
        }
        lblTongTien.setText(dft.format(tong));
    }

    public int getTongTien() {
        int tong = 0;
        int colum = tblDonHang.getRowCount();
        for (int i = 0; i < colum; i++) {
            Double tongTien = Double.valueOf(tblModel2.getValueAt(i, 7).toString());
            tong += tongTien;
        }
        return tong;
    }
    private void cboLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiSPActionPerformed
        try {
            String loai = cboLoaiSP.getSelectedItem().toString();
            String ma;

            if (loai.equals("Áo")) {
                ma = "LH1";
            } else if (loai.equals("Quần")) {
                ma = "LH2";
            } else {
                ma = "LH0";
            }

            if (ma.equals("LH0")) {
                xoaDLTable();
                SanPhamDAO sp = new SanPhamDAO();
                List<SanPham> list = sp.finAll1();
                tblModel1.setRowCount(0);
                for (SanPham sanPham : list) {
                    tblModel1.addRow(new Object[]{
                        sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getLoaiSP().getTenLoai(),
                        sanPham.getNhanHieu(), sanPham.getGia(), sanPham.getSoLuong(),
                        sanPham.getChatLieu().getTenChatLieu(), sanPham.getMauSac().getTenMau(),
                        sanPham.getKichThuoc().getTenKichThuoc()
                    });
                }
                tblModel1.fireTableDataChanged();
            }

            if (ma.equals("LH1")) {
                xoaDLTable();
                SanPhamDAO sp = new SanPhamDAO();
                List<SanPham> list = sp.getDSSPTheoMaLoai(ma);
                tblModel1.setRowCount(0);
                for (SanPham sanPham : list) {
                    tblModel1.addRow(new Object[]{
                        sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getLoaiSP().getTenLoai(),
                        sanPham.getNhanHieu(), sanPham.getGia(), sanPham.getSoLuong(),
                        sanPham.getChatLieu().getTenChatLieu(), sanPham.getMauSac().getTenMau(),
                        sanPham.getKichThuoc().getTenKichThuoc()
                    });
                }
                tblModel1.fireTableDataChanged();
            }

            if (ma.equals("LH2")) {
                xoaDLTable();
                SanPhamDAO sp = new SanPhamDAO();
                List<SanPham> list = sp.getDSSPTheoMaLoai(ma);
                tblModel1.setRowCount(0);
                for (SanPham sanPham : list) {
                    tblModel1.addRow(new Object[]{
                        sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getLoaiSP().getTenLoai(),
                        sanPham.getNhanHieu(), sanPham.getGia(), sanPham.getSoLuong(),
                        sanPham.getChatLieu().getTenChatLieu(), sanPham.getMauSac().getTenMau(),
                        sanPham.getKichThuoc().getTenKichThuoc()
                    });
                }
                tblModel1.fireTableDataChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialogHelper.showErrorDialog(parentForm,
                    e.getMessage(), "Lỗi");
        }
    }//GEN-LAST:event_cboLoaiSPActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed

    }//GEN-LAST:event_btnTimActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        for (int i = tblModel2.getRowCount() - 1; i >= 0; i--) {
            tblModel2.removeRow(i);
        }
        lblTongTien.setText("0 VND");
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void cboKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKichThuocActionPerformed
        try {
            String kichThuoc = cboKichThuoc.getSelectedItem().toString();
            String kt;

            if (kichThuoc.equals("S         ")) {
                kt = "KT1";
            } else if (kichThuoc.equals("M         ")) {
                kt = "KT2";
            } else if (kichThuoc.equals("L         ")) {
                kt = "KT3";
            } else if (kichThuoc.equals("XL        ")) {
                kt = "KT4";
            } else if (kichThuoc.equals("XXL       ")) {
                kt = "KT5";
            } else {
                kt = "KT0";
            }

            if (kt.equals("KT1")) {
                xoaDLTable();
                SanPhamDAO sp = new SanPhamDAO();
                List<SanPham> list = sp.getDSSPTheoKichThuoc(kt);
                tblModel1.setRowCount(0);
                for (SanPham sanPham : list) {
                    tblModel1.addRow(new Object[]{
                        sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getLoaiSP().getTenLoai(),
                        sanPham.getNhanHieu(), sanPham.getGia(), sanPham.getSoLuong(),
                        sanPham.getChatLieu().getTenChatLieu(), sanPham.getMauSac().getTenMau(),
                        sanPham.getKichThuoc().getTenKichThuoc()
                    });
                }
                tblModel1.fireTableDataChanged();
            } else if (kt.equals("KT2")) {
                xoaDLTable();
                SanPhamDAO sp = new SanPhamDAO();
                List<SanPham> list = sp.getDSSPTheoKichThuoc(kt);
                tblModel1.setRowCount(0);
                for (SanPham sanPham : list) {
                    tblModel1.addRow(new Object[]{
                        sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getLoaiSP().getTenLoai(),
                        sanPham.getNhanHieu(), sanPham.getGia(), sanPham.getSoLuong(),
                        sanPham.getChatLieu().getTenChatLieu(), sanPham.getMauSac().getTenMau(),
                        sanPham.getKichThuoc().getTenKichThuoc()
                    });
                }
                tblModel1.fireTableDataChanged();
            } else if (kt.equals("KT3")) {
                xoaDLTable();
                SanPhamDAO sp = new SanPhamDAO();
                List<SanPham> list = sp.getDSSPTheoKichThuoc(kt);
                tblModel1.setRowCount(0);
                for (SanPham sanPham : list) {
                    tblModel1.addRow(new Object[]{
                        sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getLoaiSP().getTenLoai(),
                        sanPham.getNhanHieu(), sanPham.getGia(), sanPham.getSoLuong(),
                        sanPham.getChatLieu().getTenChatLieu(), sanPham.getMauSac().getTenMau(),
                        sanPham.getKichThuoc().getTenKichThuoc()
                    });
                }
                tblModel1.fireTableDataChanged();
            } else if (kt.equals("KT4")) {
                xoaDLTable();
                SanPhamDAO sp = new SanPhamDAO();
                List<SanPham> list = sp.getDSSPTheoKichThuoc(kt);
                tblModel1.setRowCount(0);
                for (SanPham sanPham : list) {
                    tblModel1.addRow(new Object[]{
                        sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getLoaiSP().getTenLoai(),
                        sanPham.getNhanHieu(), sanPham.getGia(), sanPham.getSoLuong(),
                        sanPham.getChatLieu().getTenChatLieu(), sanPham.getMauSac().getTenMau(),
                        sanPham.getKichThuoc().getTenKichThuoc()
                    });
                }
                tblModel1.fireTableDataChanged();
            } else if (kt.equals("KT5")) {
                xoaDLTable();
                SanPhamDAO sp = new SanPhamDAO();
                List<SanPham> list = sp.getDSSPTheoKichThuoc(kt);
                tblModel1.setRowCount(0);
                for (SanPham sanPham : list) {
                    tblModel1.addRow(new Object[]{
                        sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getLoaiSP().getTenLoai(),
                        sanPham.getNhanHieu(), sanPham.getGia(), sanPham.getSoLuong(),
                        sanPham.getChatLieu().getTenChatLieu(), sanPham.getMauSac().getTenMau(),
                        sanPham.getKichThuoc().getTenKichThuoc()
                    });
                }
                tblModel1.fireTableDataChanged();
            } else {
                xoaDLTable();
                SanPhamDAO sp = new SanPhamDAO();
                List<SanPham> list = sp.finAll1();
                tblModel1.setRowCount(0);
                for (SanPham sanPham : list) {
                    tblModel1.addRow(new Object[]{
                        sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getLoaiSP().getTenLoai(),
                        sanPham.getNhanHieu(), sanPham.getGia(), sanPham.getSoLuong(),
                        sanPham.getChatLieu().getTenChatLieu(), sanPham.getMauSac().getTenMau(),
                        sanPham.getKichThuoc().getTenKichThuoc()
                    });
                }
                tblModel1.fireTableDataChanged();
            }

        } catch (Exception e) {
            e.printStackTrace();
            MessageDialogHelper.showErrorDialog(parentForm,
                    e.getMessage(), "Lỗi");
        }
    }//GEN-LAST:event_cboKichThuocActionPerformed

    private void btnTimKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKHActionPerformed
        try {
            String sdt = txtTimSDT.getText();
            KhachHang kh = kh_dao.getKhachHangTheoSDT(sdt);
            if (kh != null) {
                txtSDTKH.setText(kh.getSoDT());
                txtDiaChi.setText(kh.getDiaChi());
                txtTenKH.setText(kh.getHoTen());
                txtTimSDT.setText("");
            } else {
                JOptionPane.showMessageDialog(parentForm, "Không tìm thấy");
            }

        } catch (Exception e) {
            e.printStackTrace();
            MessageDialogHelper.showErrorDialog(parentForm,
                    e.getMessage(), "Lỗi");
        }
    }//GEN-LAST:event_btnTimKHActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        try {
            String sdt = txtSDTKH.getText();
            khachHang = kh_dao.getKhachHangTheoSDT(sdt);

            if (khachHang == null) {
                if (checkData() == true) {
                    String ten = txtTenKH.getText();
                    String sdtKH = txtSDTKH.getText();
                    String diaChi = txtDiaChi.getText();
                    boolean gt = true;
                    KhachHang a = new KhachHang(ten, gt, sdt, diaChi);
                    kh_dao.insert(a);

                    HoaDon hd = new HoaDon();
                    Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
                    hd.setNgayLapHD(currentDate);

                    String maTK = SharedData.nguoiDangNhap.getMaTK();
                    NhanVien nv = nv_dao.getNhanVienTheoMaTK(maTK);
                    hd.setNhanVien(nv);
                    float tongTien = getTongTien();
                    hd.setTongTien(tongTien);
                    KhachHang kh1 = kh_dao.getTop1KH();
                    hd.setKhachHang(kh1);
                    hd_dao.insert(hd);
                    int hang = tblDonHang.getRowCount();
                    for (int i = 0; i < hang; i++) {
                        String ma = tblModel2.getValueAt(i, 0).toString();
                        SanPham sp = sp_dao.getSanPhamByID(ma);
                        HoaDon hd1 = hd_dao.getTop1HD();
                        CTHD.setHoaDon(hd1);
                        CTHD.setSanPham(sp);
                        int soLuong = Integer.valueOf(tblModel2.getValueAt(i, 5).toString());
                        CTHD.setSoLuong(soLuong);
                        cthd_dao.insert(CTHD);
                    }
                    JOptionPane.showMessageDialog(null, "Thanh toán thành công");
                    if (ckoXuat.isSelected()) {
//                        XuatHoaDonBH_Form b;
//                        b = new XuatHoaDonBH_Form();
//                        b.setLocationRelativeTo(parentForm);
//                        InHD();
//                        b.setVisible(true);
//                        b.printingHoaDon();

                        HoaDon hd3 = hd_dao.getTop1HD();
                        String maHD = hd3.getMaHD();
                        Hashtable map = new Hashtable();
                        JasperReport report = JasperCompileManager.compileReport("src/ui/rpt.jrxml");
                        map.put("maHD", maHD);
                        java.sql.Connection con = DatabaseHelper.opConnection();
                        JasperPrint p = JasperFillManager.fillReport(report, map, con);
                        JasperViewer.viewReport(p, false);
                        JasperExportManager.exportReportToPdfFile(p, "test.pdf");

                    }

                    int hang1 = tblDonHang.getRowCount();
                    for (int i = 0; i < hang1; i++) {
                        String ma1 = tblModel2.getValueAt(i, 0).toString();

                        SanPham sp = sp_dao.getSanPhamByID(ma1);
                        int soLuong = Integer.valueOf(tblModel2.getValueAt(i, 5).toString());
                        int ctsl = sp.getSoLuong() - soLuong;
                        sp.setSoLuong(ctsl);
                        sp_dao.update(sp);
                    }
                    loadDataToTable();
                    xoaDLTable1();
                }

            } else {
                HoaDon hd = new HoaDon();
                Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
                hd.setNgayLapHD(currentDate);
                String maTK = SharedData.nguoiDangNhap.getMaTK();
                NhanVien nv = nv_dao.getNhanVienTheoMaTK(maTK);
                hd.setNhanVien(nv);
                float tongTien = getTongTien();
                hd.setTongTien(tongTien);
                hd.setKhachHang(khachHang);
                hd_dao.insert(hd);

                int hang = tblDonHang.getRowCount();
                for (int i = 0; i < hang; i++) {
                    String ma = tblModel2.getValueAt(i, 0).toString();
                    SanPham sp = sp_dao.getSanPhamByID(ma);
                    HoaDon hd1 = hd_dao.getTop1HD();
                    CTHD.setHoaDon(hd1);
                    CTHD.setSanPham(sp);
                    int soLuong = Integer.valueOf(tblModel2.getValueAt(i, 5).toString());
                    CTHD.setSoLuong(soLuong);
                    cthd_dao.insert(CTHD);
                }
                JOptionPane.showMessageDialog(null, "Thanh toán thành công");
                int hang1 = tblDonHang.getRowCount();
                for (int i = 0; i < hang1; i++) {
                    String ma1 = tblModel2.getValueAt(i, 0).toString();

                    SanPham sp = sp_dao.getSanPhamByID(ma1);
                    int soLuong = Integer.valueOf(tblModel2.getValueAt(i, 5).toString());
                    int ctsl = sp.getSoLuong() - soLuong;
                    sp.setSoLuong(ctsl);
                    sp_dao.update(sp);
                }
                loadDataToTable();
                xoaDLTable1();

                if (ckoXuat.isSelected()) {
//                    XuatHoaDonBH_Form a;
//                    a = new XuatHoaDonBH_Form();
//                    a.setLocationRelativeTo(parentForm);
//                    InHD();
//                    a.setVisible(true);
//                    a.printingHoaDon();

                    HoaDon hd3 = hd_dao.getTop1HD();
                    String maHD = hd3.getMaHD();
                    Hashtable map = new Hashtable();
                    JasperReport report = JasperCompileManager.compileReport("src/ui/rpt.jrxml");
                    map.put("maHD", maHD);
                    java.sql.Connection con = DatabaseHelper.opConnection();
                    JasperPrint p = JasperFillManager.fillReport(report, map, con);
                    JasperViewer.viewReport(p, false);
                    JasperExportManager.exportReportToPdfFile(p, "test.pdf");

                }
            }

        } catch (Exception ex) {
            Logger.getLogger(BanHang_Form.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    public void InHD() throws Exception {
        HoaDon hd5 = hd_dao.getTop1HD();
        String maTK = SharedData.nguoiDangNhap.getMaTK();
        NhanVien nv = nv_dao.getNhanVienTheoMaTK(maTK);
        String tenNV = nv.getHoTen();
        String diaChi = txtDiaChi.getText();
        String sdt = txtSDTKH.getText();
        String tenKH = txtTenKH.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
        String ngay = dateFormat.format(currentDate);
        String maHD = hd5.getMaHD();
        DecimalFormat dft = new DecimalFormat("###,###,### VND");
        xuatHD.lblDiaChi.setText(diaChi);
        xuatHD.lblNV.setText(tenNV);
        xuatHD.lblKH.setText(tenKH);
        xuatHD.lblNgayLap.setText(ngay);
        xuatHD.lblSDT.setText(sdt);
        xuatHD.lblMaHD.setText(maHD);
        double tienNhan = Double.valueOf(txtTienKhach.getText());
        String tienTra = lblTienTra.getText();
        xuatHD.lblTienNhan.setText(dft.format((tienNhan)));
        xuatHD.lblTienTra.setText(tienTra);
        List<ChiTietHD> listCTHD = cthd_dao.getCTHDTheoMaDH(hd5.getMaHD());
        int i = 0;
        float thanhTien = 0;
        for (ChiTietHD chiTietHD : listCTHD) {
            i++;

            xuatHD.tblModel1.addRow(new Object[]{
                i, chiTietHD.getSanPham().getTenSP(), chiTietHD.getSanPham().getKichThuoc().getTenKichThuoc(),
                dft.format(chiTietHD.getSanPham().getGia()), chiTietHD.getSoLuong(), dft.format(chiTietHD.getSanPham().getGia() * chiTietHD.getSoLuong())

            });
            thanhTien += chiTietHD.getSanPham().getGia() * chiTietHD.getSoLuong();
            xuatHD.lblTongTien.setText(dft.format(thanhTien));
        }

    }

//    public void XuatHoaDon(String idhd) {
//        try {
//            Hashtable map = new Hashtable();
//            JasperReport report = JasperCompileManager.compileReport("C:\\Users\\ADMIN\\Documents\\NetBeansProjects\\QuanLyBanQuanAo\\src\\ui\\rpt.jrxml");
//
//            map.put("MaHD", idhd);
//            JasperPrint p = JasperFillManager.fillReport(report, map, DatabaseHelper.con);
//            JasperViewer.viewReport(p, false);
//            JasperExportManager.exportReportToPdfFile(p, "test.pdf");
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
    private boolean checkData() throws Exception {
        String tenKH = txtTenKH.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String soDT = txtSDTKH.getText().trim();
        String dt = soDT + "          ";

        if (!(tenKH.length() > 0) || tenKH.equals("Tên khách hàng")) {
            JOptionPane.showMessageDialog(parentForm, "Tên không được để trống");
            txtTenKH.requestFocus();
            return false;
        }
        if (!tenKH.matches("^[^0-9]+$")) {
            JOptionPane.showMessageDialog(parentForm, "Tên không được có số");
            txtTenKH.requestFocus();
            return false;
        }

        if (!(diaChi.length() > 0) || diaChi.equals("Địa chỉ")) {
            JOptionPane.showMessageDialog(parentForm, "Địa chỉ không được để trống");
            txtDiaChi.requestFocus();
            return false;
        }
        if (!(soDT.length() > 0 && soDT.matches("^0\\d{9}"))) {
            JOptionPane.showMessageDialog(this, "Số điện thoại gồm dãy 10 số bắt đầu từ số 0");
            txtSDTKH.requestFocus();
            return false;
        }
        return true;

    }

    private void btnDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatHangActionPerformed
        try {
            String sdt = txtSDTKH.getText();
            khachHang = kh_dao.getKhachHangTheoSDT(sdt);

            if (khachHang == null) {
                if (checkData() == true) {
                    String ten = txtTenKH.getText();
                    String sdtKH = txtSDTKH.getText();
                    String diaChi = txtDiaChi.getText();
                    boolean gt = true;
                    KhachHang a = new KhachHang(ten, gt, sdt, diaChi);
                    kh_dao.insert(a);

                    DonDatHang dh = new DonDatHang();
                    Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
                    dh.setNgayDatHang(currentDate);

                    String maTK = SharedData.nguoiDangNhap.getMaTK();
                    NhanVien nv = nv_dao.getNhanVienTheoMaTK(maTK);
                    dh.setNhanVien(nv);

                    KhachHang kh1 = kh_dao.getTop1KH();
                    float tongTien = getTongTien();
                    dh.setTongTien(tongTien);
                    dh.setKhachHang(kh1);
                    dh_dao.insert(dh);
                    int hang = tblDonHang.getRowCount();
                    for (int i = 0; i < hang; i++) {
                        String ma = tblModel2.getValueAt(i, 0).toString();
                        SanPham sp = sp_dao.getSanPhamByID(ma);
                        DonDatHang dh1 = dh_dao.getTop1DonHang();
                        CTDH.setDonHang(dh1);
                        CTDH.setSanPham(sp);
                        int soLuong = Integer.valueOf(tblModel2.getValueAt(i, 5).toString());
                        CTDH.setSoLuong(soLuong);
                        ctdh_dao.insert(CTDH);
                    }
                    JOptionPane.showMessageDialog(null, "Đặt hàng thành công");
                    int hang1 = tblDonHang.getRowCount();
                    for (int i = 0; i < hang1; i++) {
                        String ma1 = tblModel2.getValueAt(i, 0).toString();

                        SanPham sp = sp_dao.getSanPhamByID(ma1);
                        int soLuong = Integer.valueOf(tblModel2.getValueAt(i, 5).toString());
                        int ctsl = sp.getSoLuong() - soLuong;
                        sp.setSoLuong(ctsl);
                        sp_dao.update(sp);
                    }
                    loadDataToTable();
                    xoaDLTable1();
                }
            } else {
                String soDT = txtDiaChi.getText();
                DonDatHang dh2 = dh_dao.getDSDHTheoSDTKH(sdt);
                if (dh2 == null) {
                    DonDatHang dh = new DonDatHang();
                    Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
                    dh.setNgayDatHang(currentDate);

                    String maTK = SharedData.nguoiDangNhap.getMaTK();
                    NhanVien nv = nv_dao.getNhanVienTheoMaTK(maTK);
                    dh.setNhanVien(nv);

                    dh.setKhachHang(khachHang);
                    float tongTien = getTongTien();
                    dh.setTongTien(tongTien);
                    dh_dao.insert(dh);

                    int hang = tblDonHang.getRowCount();
                    for (int i = 0; i < hang; i++) {
                        String ma = tblModel2.getValueAt(i, 0).toString();
                        SanPham sp = sp_dao.getSanPhamByID(ma);
                        DonDatHang dh1 = dh_dao.getTop1DonHang();
                        CTDH.setDonHang(dh1);
                        CTDH.setSanPham(sp);
                        int soLuong = Integer.valueOf(tblModel2.getValueAt(i, 5).toString());
                        CTDH.setSoLuong(soLuong);
                        ctdh_dao.insert(CTDH);
                    }
                    JOptionPane.showMessageDialog(null, "Đặt hàng thành công");
                    int hang1 = tblDonHang.getRowCount();
                    for (int i = 0; i < hang1; i++) {
                        String ma1 = tblModel2.getValueAt(i, 0).toString();

                        SanPham sp = sp_dao.getSanPhamByID(ma1);
                        int soLuong = Integer.valueOf(tblModel2.getValueAt(i, 5).toString());
                        int ctsl = sp.getSoLuong() - soLuong;
                        sp.setSoLuong(ctsl);
                        sp_dao.update(sp);
                    }
                    loadDataToTable();
                    xoaDLTable1();
                } else {
                    int hang = tblDonHang.getRowCount();
                    float tongTien = dh2.getTongTien();
                    List<ChiTietDonHang> list = ctdh_dao.getDSCTDHTheoDH(dh2.getMaDH());
                    for (int i = 0; i < hang; i++) {
                        String ma = tblModel2.getValueAt(i, 0).toString();
                        SanPham sp = sp_dao.getSanPhamByID(ma);
                        CTDH.setDonHang(dh2);
                        CTDH.setSanPham(sp);
                        int soLuong = Integer.valueOf(tblModel2.getValueAt(i, 5).toString());
                        CTDH.setSoLuong(soLuong);
                        if (ctdh_dao.insert(CTDH) == false) {
                            JOptionPane.showMessageDialog(parentForm, "Sản phẩm đã có trong đơn hàng");
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Đặt hàng thành công");
                    float tongTien1 = getTongTien();
                    float tien = tongTien + tongTien1;
                    dh2.setTongTien(tien);
                    dh_dao.update(dh2);
                    int hang1 = tblDonHang.getRowCount();
                    for (int i = 0; i < hang1; i++) {
                        String ma1 = tblModel2.getValueAt(i, 0).toString();

                        SanPham sp = sp_dao.getSanPhamByID(ma1);
                        int soLuong = Integer.valueOf(tblModel2.getValueAt(i, 5).toString());
                        int ctsl = sp.getSoLuong() - soLuong;
                        sp.setSoLuong(ctsl);
                        sp_dao.update(sp);
                    }
                    loadDataToTable();
                    xoaDLTable1();
                }

            }

        } catch (Exception ex) {
            Logger.getLogger(BanHang_Form.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDatHangActionPerformed

    private void txtTienKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachActionPerformed
        int a = getTongTien();
        String tienThuKhach = txtTienKhach.getText();
        if (!tienThuKhach.matches("^[0-9]{1,}$")) {
            JOptionPane.showMessageDialog(parentForm, "Tiền khách đưa phải là số >0");
        } else {
            double tienKhach = Double.valueOf(txtTienKhach.getText());
            double tienTra = tienKhach - a;
            DecimalFormat dft = new DecimalFormat("###,###,### VND");
            if (tienKhach >= a) {
                lblTienTra.setText(dft.format(tienTra));

            } else {
                lblTienTra.setText("0VND");
            }
        }


    }//GEN-LAST:event_txtTienKhachActionPerformed

    private void txtSDTKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTKHActionPerformed
        String sdt = txtSDTKH.getText();
        try {
            KhachHang kh = kh_dao.getKhachHangTheoSDT(sdt);
            if (kh != null) {
                txtTenKH.setText(kh.getHoTen());
                txtDiaChi.setText(kh.getDiaChi());
            }
        } catch (Exception ex) {
            Logger.getLogger(BanHang_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtSDTKHActionPerformed

    public void xoaDLTable() {
        for (int i = tblModel1.getRowCount() - 1; i >= 0; i--) {
            tblModel1.removeRow(i);
        }
    }

    public void xoaDLTable1() {
        for (int i = tblModel2.getRowCount() - 1; i >= 0; i--) {
            tblModel2.removeRow(i);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDatHang;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnTimKH;
    private javax.swing.JComboBox<String> cboChatLieu;
    private javax.swing.JComboBox<String> cboKichThuoc;
    private javax.swing.JComboBox<String> cboLoaiSP;
    private javax.swing.JCheckBox ckoXuat;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblTienTra;
    public static javax.swing.JLabel lblTongTien;
    private javax.swing.JSpinner spnSoLuong;
    public static javax.swing.JTable tblDonHang;
    private javax.swing.JTable tblSP;
    public static javax.swing.JTextField txtDiaChi;
    public static javax.swing.JTextField txtSDTKH;
    public static javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTienKhach;
    private javax.swing.JTextField txtTimSDT;
    // End of variables declaration//GEN-END:variables

}
