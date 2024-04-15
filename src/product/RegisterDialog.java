package product;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

class RegisterDialog extends JDialog implements ActionListener {
    ProductController productController = new ProductController();
    ProductView productView;
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

    public RegisterDialog(JFrame owner, String title, boolean modal, ProductView productView) {
        super(owner, title, modal);
        this.productView = productView;
        initializeUI();
        setLocationRelativeTo(owner);
        btn_save.setText("등록");
    }

    public RegisterDialog(JFrame owner, String title, boolean modal, int productNum, String productName, int productPrice, int productQuantity, ProductView productView) {
        super(owner, title, modal);
        this.productView = productView;
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
            productController.addProduct(product);
        } else {
            productController.updateProduct(product);
        }

         productView.loadProductData();

        dispose(); // 다이얼로그 닫기
    }
}
