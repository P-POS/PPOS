package product;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

public class ProductView extends JFrame implements ActionListener {
    private final ProductController productController;

    DefaultTableModel model;
    JTable table;
    JScrollPane scrollPane;

    DefaultTableCellRenderer item_renderer = new DefaultTableCellRenderer();
    DefaultTableCellRenderer header_renderer;

    // 검색어 입력창
    JTextField searchInput = new JTextField("상품번호, 상품명");

    // 버튼
    JButton btn_home = new JButton("홈");
    JButton btn_search = new JButton("검색");
    JButton btn_all = new JButton("전체 조회");
    JButton btn_register = new JButton("등록");
    JButton btn_update = new JButton("수정");
    JButton btn_delete = new JButton("삭제");

    JLabel label = new JLabel("상품관리");
    Font mainFont = new Font("맑은 고딕", Font.BOLD, 25);
    Font subFont = new Font("맑은 고딕", Font.PLAIN, 16);
    Font btnFont = new Font("맑은 고딕", Font.PLAIN, 14);

    public ProductView(ProductController productController) {
        this.productController = productController;

        // 프레임 크기 설정 및 화면에 표시
        setSize(1280, 960);
        setLayout(null);

        String[] columnNames = {"No", "상품번호", "상품명", "가격", "수량"};

        // JTable 생성
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        JTableHeader header = table.getTableHeader();

        header_renderer = (DefaultTableCellRenderer) table.getTableHeader()
                .getDefaultRenderer();
        header_renderer.setHorizontalAlignment(SwingConstants.CENTER);
        item_renderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 5; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(item_renderer);
        }

        // 상품 불러오기
        loadProductData();

        searchInput.setForeground(Color.GRAY); // placeholder 색상 설정

        btn_home.addActionListener(this);
        btn_search.addActionListener(this);
        btn_all.addActionListener(this);
        btn_register.addActionListener(this);
        btn_update.addActionListener(this);
        btn_delete.addActionListener(this);

        table.getColumnModel().getColumn(2).setPreferredWidth(400);

        label.setBounds(20, 20, 1280, 50);
        scrollPane.setBounds(20, 150, 1220, 740);
        searchInput.setBounds(20, 80, 710, 50);
        btn_home.setBounds(1175, 20, 60, 50);
        btn_search.setBounds(735, 80, 120, 50);
        btn_all.setBounds(860, 80, 120, 50);
        btn_register.setBounds(985, 80, 80, 50);
        btn_update.setBounds(1070, 80, 80, 50);
        btn_delete.setBounds(1155, 80, 80, 50);

        label.setFont(mainFont);
        table.setFont(subFont);
        header.setFont(subFont);
        header.setBackground(Color.lightGray);
        table.setRowHeight(24);
        searchInput.setFont(btnFont);
        btn_home.setFont(btnFont);
        btn_search.setFont(btnFont);
        btn_all.setFont(btnFont);
        btn_register.setFont(btnFont);
        btn_update.setFont(btnFont);
        btn_delete.setFont(btnFont);

        add(label);
        add(scrollPane);
        add(searchInput);
        add(btn_home);
        add(btn_search);
        add(btn_all);
        add(btn_register);
        add(btn_update);
        add(btn_delete);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // placeholder 효과를 주기 위한 코드
        searchInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // 포커스를 얻었을 때, placeholder 효과 주지 않기
                if (searchInput.getText().equals("상품번호, 상품명")) {
                    searchInput.setText("");
                    searchInput.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // 포커스를 잃었을 때, 텍스트 필드가 비어있다면 placeholder 효과 주기
                if (searchInput.getText().isEmpty()) {
                    searchInput.setForeground(Color.GRAY);
                    searchInput.setText("상품번호, 상품명");
                }
            }
        });
    }

    public void loadProductData() {
        // 상품 목록 가져오기
        ArrayList<Product> products = productController.getProducts();

        // 모델 데이터 초기화
        model.setRowCount(0);

        int no = 1;
        // 상품 목록을 JTable 모델에 추가
        for (Product product : products) {
            model.addRow(new Object[]{no++, product.getProductNum(), product.getProductName(),
                    product.getProductPrice(), product.getProductQuantity()});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_home) {
            this.setVisible(false);
            productController.openMainPage();
        } else if (e.getSource() == btn_search) {
            boolean isNumeric = searchInput.getText().matches("-?\\d+(\\.\\d+)?"); // 숫자인지 확인
            // 검색 버튼을 클릭했을 때
            String searchText = searchInput.getText();
            model.setRowCount(0); // 테이블 데이터 초기화

            int no = 1;
            // 상품 목록에서 검색어에 해당하는 상품만 필터링하여 테이블에 추가
            for (Product product : productController.getProducts()) {
                if (isNumeric) {
                    // searchText가 숫자일 경우, productNum과 비교
                    int searchNum = Integer.parseInt(searchText);
                    if (product.getProductNum() == searchNum) {
                        model.addRow(new Object[]{no++, product.getProductNum(),
                                product.getProductName(), product.getProductPrice(),
                                product.getProductQuantity()});
                    }
                } else {
                    // searchText가 문자열일 경우, productName과 비교
                    if (product.getProductName().contains(searchText)) {
                        model.addRow(new Object[]{no++, product.getProductNum(),
                                product.getProductName(), product.getProductPrice(),
                                product.getProductQuantity()});
                    }
                }
            }
        } else if (e.getSource() == btn_all) {
            // 전체 상품 조회 버튼을 클릭했을 때
            loadProductData();
            searchInput.setForeground(Color.GRAY);
            searchInput.setText("상품번호, 상품명");
        } else if (e.getSource() == btn_register) {
            // 등록 버튼 클릭 시 모달 다이얼로그 띄우기
            RegisterDialog registerDialog = new RegisterDialog(this, "상품 등록", true, this, productController, searchInput);
            registerDialog.setVisible(true);
        } else if (e.getSource() == btn_update) {
            // 수정 버튼을 클릭했을 때
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) { // 행이 하나라도 선택되었는지 확인
                // 선택된 행의 데이터 가져오기
                int productNum = (int) table.getValueAt(selectedRow, 1);
                String productName = (String) table.getValueAt(selectedRow, 2);
                int productPrice = (int) table.getValueAt(selectedRow, 3);
                int productQuantity = (int) table.getValueAt(selectedRow, 4);

                // 선택된 행의 데이터를 RegisterDialog에 전달
                RegisterDialog registerDialog = new RegisterDialog(this, "상품 수정", true, productNum,
                        productName, productPrice, productQuantity, this, productController, searchInput);
                registerDialog.setVisible(true);
            } else {
                // 행이 선택되지 않았을 경우 경고 메시지 표시
                JLabel label = new JLabel("수정할 상품을 선택해주세요.");
                label.setFont(btnFont);
                JOptionPane.showMessageDialog(this, label, "경고",
                        JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == btn_delete) {
            // 삭제 버튼을 클릭했을 때
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) { // 행이 하나라도 선택되었는지 확인
                int productNum = (int) table.getValueAt(selectedRow, 1);

                productController.deleteProduct(productNum);
                loadProductData();
            } else {
                // 행이 선택되지 않았을 경우 경고 메시지 표시
                JLabel label = new JLabel("삭제할 상품을 선택해주세요.");
                label.setFont(btnFont);
                JOptionPane.showMessageDialog(this, label, "경고",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}

class RegisterDialog extends JDialog implements ActionListener {
    ProductView productView;
    ProductController productController;
    JTextField searchInput;
    JLabel nameLabel = new JLabel("상품명:");
    JTextField tfName = new JTextField(20);
    JLabel numberLabel = new JLabel("상품번호:");
    JTextField tfProductNumber = new JTextField(20);
    JLabel priceLabel = new JLabel("가격:");
    JTextField tfPrice = new JTextField(20);
    JLabel quantityLabel = new JLabel("수량:");
    JTextField tfQuantity = new JTextField(20);
    JButton btn_save = new JButton("등록");

    Font labelFont = new Font("맑은 고딕", Font.PLAIN, 14);

    public RegisterDialog(JFrame owner, String title, boolean modal, ProductView productView, ProductController productController, JTextField searchInput) {
        super(owner, title, modal);
        this.productView = productView;
        this.productController = productController;
        this.searchInput = searchInput;
        initializeUI();
        setLocationRelativeTo(owner);
        btn_save.setText("등록");
    }

    public RegisterDialog(JFrame owner, String title, boolean modal, int productNum, String productName, int productPrice, int productQuantity, ProductView productView, ProductController productController, JTextField searchInput) {
        super(owner, title, modal);
        this.productView = productView;
        this.productController = productController;
        this.searchInput = searchInput;
        initializeUI();
        setLocationRelativeTo(owner);
        btn_save.setText("수정");

        // 상품 정보를 텍스트 필드에 초기화
        tfName.setText(productName);
        tfProductNumber.setText(Integer.toString(productNum));
        tfPrice.setText(Integer.toString(productPrice));
        tfQuantity.setText(Integer.toString(productQuantity));
    }

    // 공통 UI (등록/수정)
    private void initializeUI() {
        setSize(500, 420);
        setLayout(null);

        // 레이블과 텍스트 필드 추가
        nameLabel.setBounds(50, 40, 100, 50);
        tfName.setBounds(150, 40, 300, 50);
        numberLabel.setBounds(50, 100, 100, 50);
        tfProductNumber.setBounds(150, 100, 300, 50);
        quantityLabel.setBounds(50, 160, 100, 50);
        tfQuantity.setBounds(150, 160, 300, 50);
        priceLabel.setBounds(50, 220, 100, 50);
        tfPrice.setBounds(150, 220, 300, 50);
        btn_save.setBounds(200, 310, 100, 40);

        nameLabel.setFont(labelFont);
        tfName.setFont(labelFont);
        numberLabel.setFont(labelFont);
        tfProductNumber.setFont(labelFont);
        quantityLabel.setFont(labelFont);
        tfQuantity.setFont(labelFont);
        priceLabel.setFont(labelFont);
        tfPrice.setFont(labelFont);
        btn_save.setFont(labelFont);

        add(nameLabel);
        add(tfName);
        add(numberLabel);
        add(tfProductNumber);
        add(quantityLabel);
        add(tfQuantity);
        add(priceLabel);
        add(tfPrice);
        add(btn_save);

        // 저장 버튼에 대한 이벤트 처리
        btn_save.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String productName = tfName.getText();
        int productNum = Integer.parseInt(tfProductNumber.getText());
        int productQuantity = Integer.parseInt(tfQuantity.getText());
        int productPrice = Integer.parseInt(tfPrice.getText());

        // Product 객체 생성
        Product product = new Product(productNum, productName, productPrice, productQuantity);

        // 버튼의 텍스트에 따라 등록 또는 수정 로직 분리
        if (btn_save.getText().equals("등록")) {
            if (productController.addProduct(product)) {
                JLabel label = new JLabel("상품 등록이 완료되었습니다.");
                label.setFont(labelFont);
                JOptionPane.showMessageDialog(this, label, "등록 완료", JOptionPane.INFORMATION_MESSAGE);
                searchInput.setForeground(Color.GRAY);
                searchInput.setText("상품번호, 상품명");
            } else {
                JLabel label = new JLabel("정확한 정보를 입력해주세요.");
                label.setFont(labelFont);
                JOptionPane.showMessageDialog(this, label, "등록 실패", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            if (productController.updateProduct(product)) {
                JLabel label = new JLabel("상품 수정이 완료되었습니다.");
                label.setFont(labelFont);
                JOptionPane.showMessageDialog(this, label, "수정 완료", JOptionPane.INFORMATION_MESSAGE);
                searchInput.setForeground(Color.GRAY);
                searchInput.setText("상품번호, 상품명");
            } else {
                JLabel label = new JLabel("정확한 정보를 입력해주세요.");
                label.setFont(labelFont);
                JOptionPane.showMessageDialog(this, label, "수정 실패", JOptionPane.WARNING_MESSAGE);
            }
        }

        productView.loadProductData();

        dispose(); // 다이얼로그 닫기
    }
}
