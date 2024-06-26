/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.BUS_Member;
import BUS.BUS_Processing;
import Entity._Member;
import Entity._Processing;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Admin
 */
public class GUI_Processing extends javax.swing.JFrame {

    /**
     * Creates new form GUI_Member
     */
    private DefaultTableModel model;
    // Thêm biến để lưu tổng giá trị

    public GUI_Processing() {
        initComponents();
        setLocationRelativeTo(null);
        model = new DefaultTableModel();
        model.addColumn("Tên thành viên");
        model.addColumn("Hình thức xử lý");
        model.addColumn("Ngày xử lý");
        model.addColumn("Số tiền");
        model.addColumn("Trạng thái xử lý");
        model.addColumn("Maxl");
        jTable1.setModel(model);
        jTable1.getColumnModel().getColumn(5).setWidth(0);
        jTable1.getColumnModel().getColumn(5).setMinWidth(0);
        jTable1.getColumnModel().getColumn(5).setMaxWidth(0);
        enabledtext.setVisible(false);
        displayDataInTable();
        // Khởi tạo tổng giá trị ban đầu
        displayMemberIDs();
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
    }

    private void displayMemberIDs() {
        BUS_Member busMember = new BUS_Member();
        List<Object[]> members = busMember.getAllMembers("", "", "", "");

        // Xóa tất cả các mục đã tồn tại trong JComboBox8
        jComboBox8.removeAllItems();

        // Thêm ID thành viên vào JComboBox8
        for (Object[] memberData : members) {
            _Member member = (_Member) memberData[0];
            String memberID = member.getMaTV();
            jComboBox8.addItem(memberID);
        }
    }

    private void displayDataInTable() {
        model.setRowCount(0);
        BUS_Processing busProcessing = new BUS_Processing();
        List<Object> processingList = busProcessing.getAllProcessing();

        // Đặt lại giá trị tổng số tiền về 0 mỗi khi gọi lại phương thức
        double totalAmount = 0;

        if (processingList != null) {
            for (Object processing : processingList) {
                if (processing instanceof _Processing) {
                    _Processing proc = (_Processing) processing;
                    String trangThai = proc.getTrangThaiXL() == 0 ? "chưa xử lý" : "Đã xử lý";
                    model.addRow(new Object[]{
                        proc.getMaTV().getHoTen(),
                        proc.getHinhThucXL(),
                        proc.getNgayXL(),
                        proc.getSoTien(),
                        trangThai,
                        proc.getMaXL()
                    });

                    // Kiểm tra và tính tổng giá trị chỉ nếu SoTien không null
                    if (proc.getSoTien() != null) {
                        totalAmount += proc.getSoTien();
                    }
                }
            }

            // Hiển thị tổng số tiền mới
            DecimalFormat df = new DecimalFormat("#,###.##");
            labelStatisticsFor5.setText("Tổng tiền: " + df.format(totalAmount));
        } else {
            System.out.println("Không có dữ liệu hoặc có lỗi xảy ra khi truy vấn.");
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

        jPanel2 = new javax.swing.JPanel();
        labelTitle = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        labelStatisticsFor = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        labelStatisticsFor1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        labelStatisticsFor5 = new javax.swing.JLabel();
        labelStatisticsFor6 = new javax.swing.JLabel();
        labelStatisticsFor7 = new javax.swing.JLabel();
        labelStatisticsFor8 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        labelStatisticsFor9 = new javax.swing.JLabel();
        enabledtext = new javax.swing.JTextField();
        labelStatisticsFor10 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox7 = new javax.swing.JComboBox<>();
        borrowDateChooser = new com.toedter.calendar.JDateChooser();
        jComboBox8 = new javax.swing.JComboBox<>();
        TextFieldSt1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        labelTitle.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        labelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle.setText("XỬ LÝ VI PHẠM");
        labelTitle.setPreferredSize(new java.awt.Dimension(300, 29));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(labelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setPreferredSize(new java.awt.Dimension(900, 54));

        labelStatisticsFor.setBackground(new java.awt.Color(255, 255, 255));
        labelStatisticsFor.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        labelStatisticsFor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelStatisticsFor.setText("Trạng thái xử lý");

        jComboBox1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "chọn", "chưa xử lý", "Đã xử lý" }));
        jComboBox1.setPreferredSize(new java.awt.Dimension(364, 42));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        labelStatisticsFor1.setBackground(new java.awt.Color(255, 255, 255));
        labelStatisticsFor1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        labelStatisticsFor1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelStatisticsFor1.setText("Hình thức xử lý");
        labelStatisticsFor1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jComboBox2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "chọn", "khóa thẻ 1 tháng", "Khóa thẻ 2 tháng", "Khóa thẻ vĩnh viễn", "Bồi thường mất tài sản", "Khóa thẻ 1 tháng và bồi thường" }));
        jComboBox2.setPreferredSize(new java.awt.Dimension(364, 20));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelStatisticsFor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(labelStatisticsFor1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(432, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelStatisticsFor, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(labelStatisticsFor1)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 200));

        labelStatisticsFor5.setBackground(new java.awt.Color(255, 255, 255));
        labelStatisticsFor5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        labelStatisticsFor5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelStatisticsFor5.setText("Tổng tiền : ");

        labelStatisticsFor6.setBackground(new java.awt.Color(255, 255, 255));
        labelStatisticsFor6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        labelStatisticsFor6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelStatisticsFor6.setText("Mã thành viên");

        labelStatisticsFor7.setBackground(new java.awt.Color(255, 255, 255));
        labelStatisticsFor7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        labelStatisticsFor7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelStatisticsFor7.setText("Hình thức xử lý");

        labelStatisticsFor8.setBackground(new java.awt.Color(255, 255, 255));
        labelStatisticsFor8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        labelStatisticsFor8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelStatisticsFor8.setText("Ngày xử lý");

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setText("Cập Nhật");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(204, 204, 204));
        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(39, 39, 39)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        labelStatisticsFor9.setBackground(new java.awt.Color(255, 255, 255));
        labelStatisticsFor9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        labelStatisticsFor9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelStatisticsFor9.setText("Số tiền");

        enabledtext.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        enabledtext.setForeground(new java.awt.Color(255, 255, 255));
        enabledtext.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        enabledtext.setToolTipText("");
        enabledtext.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        enabledtext.setCaretColor(new java.awt.Color(255, 255, 255));
        enabledtext.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        enabledtext.setEnabled(false);
        enabledtext.setSelectionColor(new java.awt.Color(255, 255, 255));

        labelStatisticsFor10.setBackground(new java.awt.Color(255, 255, 255));
        labelStatisticsFor10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        labelStatisticsFor10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelStatisticsFor10.setText("Trạng thái xử lý");

        jComboBox5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa xử lý", "Đã xử lý" }));
        jComboBox5.setPreferredSize(new java.awt.Dimension(364, 42));

        jComboBox7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "khóa thẻ 1 tháng", "Khóa thẻ 2 tháng", "Khóa thẻ vĩnh viễn", "Bồi thường mất tài sản", "Khóa thẻ 1 tháng và bồi thường" }));
        jComboBox7.setPreferredSize(new java.awt.Dimension(364, 42));

        jComboBox8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jComboBox8.setPreferredSize(new java.awt.Dimension(364, 42));

        TextFieldSt1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        TextFieldSt1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TextFieldSt1.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelStatisticsFor7)
                            .addComponent(labelStatisticsFor8))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox7, 0, 206, Short.MAX_VALUE)
                            .addComponent(borrowDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelStatisticsFor6)
                        .addGap(36, 36, 36)
                        .addComponent(jComboBox8, 0, 1, Short.MAX_VALUE))
                    .addComponent(labelStatisticsFor5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelStatisticsFor9)
                            .addComponent(labelStatisticsFor10))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox5, 0, 206, Short.MAX_VALUE)
                            .addComponent(TextFieldSt1)))
                    .addComponent(enabledtext, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(303, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(300, 300, 300))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelStatisticsFor5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelStatisticsFor6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelStatisticsFor9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TextFieldSt1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelStatisticsFor7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelStatisticsFor10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelStatisticsFor8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enabledtext, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(borrowDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Course ID", "Person ID"
            }
        ));
        jTable1.setRowHeight(40);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        // Lấy chỉ số của hàng được chọn
        int row = jTable1.getSelectedRow();

        // Kiểm tra nếu hàng được chọn hợp lệ
        if (row >= 0 && row < jTable1.getRowCount()) {
            // Lấy dữ liệu từ bảng và hiển thị vào các thành phần trên giao diện
            String memberName = (String) jTable1.getValueAt(row, 0);
            String processingMethod = (String) jTable1.getValueAt(row, 1);
            Date processingDate = (Date) jTable1.getValueAt(row, 2);
            Integer amountObj = (Integer) jTable1.getValueAt(row, 3);
            int amount = (amountObj != null) ? amountObj : 0; // Kiểm tra và gán giá trị mặc định nếu null
            String status = (String) jTable1.getValueAt(row, 4);
            // Xác định giá trị của statusValue dựa trên status
            String statusIndex;
            if (status != null && status.equals("chưa xử lý")) {
                statusIndex = "0"; // Nếu status là "đã xử lý", chọn "Đã xử lý"
            } else {
                statusIndex = "1"; // Mặc định là "Chưa xử lý"
            }
            BUS_Processing busProcessing = new BUS_Processing();
            List<Object> processingList = busProcessing.getAllProcessing();
            String memberID = null;

            for (Object processing : processingList) {
                if (processing instanceof _Processing) {
                    _Processing proc = (_Processing) processing;
                    if (proc.getMaTV().getHoTen().equals(memberName)) {
                        memberID = String.valueOf(proc.getMaTV().getMaTV());
                        break;
                    }
                }
            }
            Integer maxlObj = (Integer) jTable1.getValueAt(row, 5);
            String maxl = (maxlObj != null) ? maxlObj.toString() : "";
            // Hiển thị dữ liệu vào các thành phần trên giao diện
            jComboBox8.setSelectedItem(memberID); // Hiển thị mã thành viên
            jComboBox7.setSelectedItem(processingMethod);
            borrowDateChooser.setDate(processingDate);
            TextFieldSt1.setText(String.valueOf(amount));
            jComboBox5.setSelectedItem(statusIndex);
            enabledtext.setText(maxl);

        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String memberID = (String) jComboBox8.getSelectedItem();
        String processingMethod = (String) jComboBox7.getSelectedItem();
        Date processingDate = borrowDateChooser.getDate();
        String amountString = TextFieldSt1.getText();
        int amount = 0;
        try {
            if (!"".equals(amountString) && amountString != null) {
                amount = Integer.parseInt(amountString);
            }
        } catch (NumberFormatException e) {
            // Người dùng đã nhập một chuỗi không thể chuyển đổi thành số nguyên
            // Hiển thị một thông báo cảnh báo và không thực hiện thêm xử lý

            JOptionPane.showMessageDialog(this, "Vui lòng nhập một số nguyên cho số tiền!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            TextFieldSt1.setText("");

            // Di chuyển tiêu điểm (focus) vào TextFieldSt1
            TextFieldSt1.requestFocus();

            return; // Dừng thực hiện tiếp theo
        }

        int status = jComboBox5.getSelectedIndex();

        // Create a new _Processing object
        _Processing processing = new _Processing();

        // Set properties of the processing object
        processing.setMaTV(new _Member(memberID));
        processing.setHinhThucXL(processingMethod);
        processing.setNgayXL(processingDate);
        if (amount != 0) {
            processing.setSoTien(amount);
        }

        processing.setTrangThaiXL(status);

        // Call the createProcess method
        BUS_Processing busProcessing = new BUS_Processing();
        boolean success = busProcessing.createProcess(processing);

        if (success) {
            // Update the table
            displayDataInTable();
            // Display success message
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
        } else {
            // Display error message
            JOptionPane.showMessageDialog(this, "Thêm không thành công", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int amount = 0;
        try {
            amount = Integer.parseInt(TextFieldSt1.getText());
        } catch (Exception e) {
        }
        int status = jComboBox5.getSelectedIndex();
        int id = Integer.parseInt(enabledtext.getText());
        // Create a new _Processing object
        _Processing processing = new _Processing();
        // Set properties of the processing object
        if (!"".equals(TextFieldSt1.getText())) {
            processing.setSoTien(amount);
        }
        processing.setTrangThaiXL(status);
        processing.setMaXL(id);

        // Gọi phương thức updateProcess từ BUS để cập nhật dữ liệu
        BUS_Processing busProcessing = new BUS_Processing();
        boolean success = busProcessing.updateProcess(processing);

        // Kiểm tra xem cập nhật thành công hay không và thực hiện các hành động tương ứng
        if (success) {
            displayDataInTable();
            // Xử lý khi cập nhật thành công
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
        } else {
            // Xử lý khi cập nhật thất bại
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        int id = Integer.parseInt(enabledtext.getText());
        BUS_Processing daoProcessing = new BUS_Processing();

        // Tạo một đối tượng _Processing với mã là 11 (ví dụ)
        _Processing processing = new _Processing();
        processing.setMaXL(id); // Set mã XL tương ứng với mã cần xóa

        // Gọi hàm deleteProcess với đối tượng _Processing đã tạo
        boolean deletionResult = daoProcessing.deleteProcess(processing);

        if (deletionResult) {
            displayDataInTable();
            JOptionPane.showMessageDialog(this, "Xóa Mã xử lý " + processing.getMaXL() + " thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
//        

    }//GEN-LAST:event_jButton3ActionPerformed
    private void filterTableBySelectedValues() {
        DefaultTableModel obj_course = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> oj1_course = new TableRowSorter<>(obj_course);
        jTable1.setRowSorter(oj1_course);

        String selectedItem1 = (String) jComboBox1.getSelectedItem();
        String selectedItem2 = (String) jComboBox2.getSelectedItem();

        List<RowFilter<Object, Object>> filters = new ArrayList<>();
        if (!"chọn".equals(selectedItem1)) {
            filters.add(RowFilter.regexFilter(selectedItem1));
        }
        if (!"chọn".equals(selectedItem2)) {
            filters.add(RowFilter.regexFilter(selectedItem2));
        }

        oj1_course.setRowFilter(RowFilter.andFilter(filters));
    }

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        filterTableBySelectedValues();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
        filterTableBySelectedValues();
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_Processing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Processing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Processing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Processing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Processing().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField TextFieldSt1;
    public com.toedter.calendar.JDateChooser borrowDateChooser;
    public javax.swing.JTextField enabledtext;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTable1;
    public javax.swing.JLabel labelStatisticsFor;
    public javax.swing.JLabel labelStatisticsFor1;
    public javax.swing.JLabel labelStatisticsFor10;
    public javax.swing.JLabel labelStatisticsFor5;
    public javax.swing.JLabel labelStatisticsFor6;
    public javax.swing.JLabel labelStatisticsFor7;
    public javax.swing.JLabel labelStatisticsFor8;
    public javax.swing.JLabel labelStatisticsFor9;
    public javax.swing.JLabel labelTitle;
    // End of variables declaration//GEN-END:variables
}
