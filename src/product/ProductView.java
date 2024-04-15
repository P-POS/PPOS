package product;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

public class ProductView extends JFrame implements ActionListener, MouseListener {
    ProductController productController = new ProductController();

    // 테이블 임시데이터 설정
    String[][] data = {
            {"1", "000000", "아몬드빼빼로", "1,500", "12"},
            {"2", "000001", "참이슬", "1,600", "5"},
            {"3", "000002", "오뚜기카레", "7,800", "22"}
    };

    // 컬럼명 설정
    String[] columnNames = {"No", "상품번호", "상품명", "금액", "잔여수량"};

    DefaultTableModel model;
    JTable table;
    JScrollPane scrollPane;

    // 검색어 입력창
    JTextField searchInput = new JTextField();

    // 버튼
    JButton btn_search = new JButton("검색");
    JButton btn_all = new JButton("전체 조회");
    JButton btn_register = new JButton("등록");
    JButton btn_update = new JButton("수정");

    JLabel label = new JLabel("상품관리");
    Font mainFont = new Font("맑은 고딕", Font.BOLD, 25);
    Font subFont = new Font("맑은 고딕", Font.PLAIN, 16);
    Font btnFont = new Font("맑은 고딕", Font.PLAIN, 14);

    public ProductView() {
        // 프레임 크기 설정 및 화면에 표시
        setSize(1280, 960);
        setLayout(null);

        String[] columnNames = {"No", "상품번호", "상품명", "가격", "수량"};

        // JTable 생성
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        JTableHeader header = table.getTableHeader();

        // 상품 불러오기
        loadProductData();

        btn_search.addActionListener(this);
        btn_all.addActionListener(this);
        btn_register.addActionListener(this);
        btn_update.addActionListener(this);
        table.addMouseListener(this);

        label.setBounds(20, 20, 1280, 50);
        scrollPane.setBounds(20, 150, 1220, 740);
        searchInput.setBounds(20, 80, 690, 50);
        btn_search.setBounds(725, 80, 120, 50);
        btn_all.setBounds(855, 80, 120, 50);
        btn_register.setBounds(985, 80, 120, 50);
        btn_update.setBounds(1115, 80, 120, 50);

        label.setFont(mainFont);
        table.setFont(subFont);
        header.setFont(subFont);
        header.setBackground(Color.lightGray);
        table.setRowHeight(24);
        searchInput.setFont(btnFont);
        btn_search.setFont(btnFont);
        btn_all.setFont(btnFont);
        btn_register.setFont(btnFont);
        btn_update.setFont(btnFont);

        add(label);
        add(scrollPane);
        add(searchInput);
        add(btn_search);
        add(btn_all);
        add(btn_register);
        add(btn_update);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loadProductData() {
        // 상품 목록 가져오기
        ArrayList<Product> products = productController.getProducts();

        System.out.println("products: " + products);

        // 모델 데이터 초기화
        model.setRowCount(0);

        int no = 1;
        // 상품 목록을 JTable 모델에 추가
        for (Product product : products) {
            model.addRow(new Object[]{no++, product.getProductNum(), product.getProductName(), product.getProductPrice(), product.getProductQuantity()});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_search) {
            // 검색 버튼을 클릭했을 때
            String searchText = searchInput.getText();
            model.setRowCount(0); // 테이블 데이터 초기화

            int no = 1;
            // 상품 목록에서 검색어에 해당하는 상품만 필터링하여 테이블에 추가
            for (Product product : productController.getProducts()) {
                if (product.getProductName().contains(searchText)) {
                    model.addRow(new Object[]{no++, product.getProductNum(), product.getProductName(), product.getProductPrice(), product.getProductQuantity()});
                }
            }
        } else if (e.getSource() == btn_all) {
            // 전체 상품 조회 버튼을 클릭했을 때
            loadProductData();
        } else if (e.getSource() == btn_register) {
            // 등록 버튼 클릭 시 모달 다이얼로그 띄우기
            RegisterDialog registerDialog = new RegisterDialog(this, "상품 등록", true);
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
                RegisterDialog registerDialog = new RegisterDialog(this, "상품 수정", true, productNum, productName, productPrice, productQuantity);
                registerDialog.setVisible(true);
            } else {
                // 행이 선택되지 않았을 경우 경고 메시지 표시
                JOptionPane.showMessageDialog(this, "수정할 행을 선택해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // table의 특정 행을 클릭했을 때 발생하는 이벤트가 들어가는 자리
        if (e.getSource() == table) {
            int row = table.rowAtPoint(e.getPoint());
            int col = table.columnAtPoint(e.getPoint());

            // 특정 행을 클릭했을 때, 거래번호 출력
            System.out.println("데이터: " + table.getValueAt(row, 0));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
