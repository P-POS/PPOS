package sale;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import member.MemberDTO;
import product.ProductOrderNumDTO;

class TableSelectionListener implements ListSelectionListener {

    private final JTable table;
    private final SaleView saleView;

    public TableSelectionListener(JTable table, SaleView saleView) {

        this.table = table;
        this.saleView = saleView;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        if (!e.getValueIsAdjusting()) { // 클릭이 완료된 경우에만 실행
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) { // 선택된 행이 있는지 확인
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                saleView.setRow(model, selectedRow); // SaleView의 setRow 메서드 호출
            }
        }
    }
}

public class SaleView extends JFrame implements ActionListener {

    JPanel panel = new JPanel();

    String headers[] = {
        "상품이름", "가격", "수량", "총 가격"
    };

    String array[][] = {
    };
    DefaultTableModel model;
    int selectedRow;
    JTextField inputBox = new JTextField();
    JTable table = new JTable(array, headers);
    JScrollPane scrollPane = new JScrollPane(table);
    int totalPriceSum;
    JLabel sumPrice = new JLabel("총 가격 : ");
    JButton btn_addProduct = new JButton("상품 등록");
    JButton btn_getUser = new JButton("유저 조회");
    JButton btn_payment = new JButton("결제");
    JButton btn_usePoint = new JButton("포인트 사용");
    JButton btn_fixAmount = new JButton("수량 수정");
    JButton btn_refund = new JButton("반품");
    JButton btn_cancle = new JButton("취소");
    JButton btn_allCancle = new JButton("전체 취소");
    JButton btn_home = new JButton("홈");
    JPanel userInfoPanel = new JPanel();
    JLabel userTitle = new JLabel("정보");
    JLabel userName = new JLabel("회원 이름 : ");
    JLabel userPoint = new JLabel("포인트 점수 : 0 점");

    SaleController saleController;
    private int pre_point;

    public void setRow(DefaultTableModel model, int selectedRow) {

        this.model = model;
        this.selectedRow = selectedRow;
    }

    public SaleView(SaleController saleController) {

        this.saleController = saleController;
        setSize(1280, 960);
        setLayout(null);

        // 홈 버튼
        btn_home.addActionListener(this);
        btn_home.setBounds(30, 695, 60, 50);
        btn_home.setFont(new Font("Arial", Font.PLAIN, 18));

        // 상품 테이블 설정.
        scrollPane.setBounds(20, 30, 600, 650);
        table.setRowHeight(30); // 원하는 높이로 조절
        table.setFont(new Font("Arial", Font.PLAIN, 18)); // 글꼴, 스타일, 크기 설정
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 18)); // 헤더의 글꼴, 굵은 스타일, 크기 설정
        // 총가격 설정
        sumPrice.setBounds(200, 700, 200, 40);
        sumPrice.setFont(new Font("Arial", Font.PLAIN, 20)); // 글꼴, 스타일, 크기 설정
        table.getSelectionModel().addListSelectionListener(new TableSelectionListener(table, this));

        // 유저 정보==================
        userTitle.setBounds(710, 360, 200, 40); // 유저 정보 제목
        userName.setBounds(710, 400, 200, 40);
        userPoint.setBounds(710, 440, 200, 40);

        userTitle.setFont(new Font("Arial", Font.PLAIN, 28));
        userName.setFont(new Font("Arial", Font.PLAIN, 16));
        userPoint.setFont(new Font("Arial", Font.PLAIN, 16));

        userInfoPanel.setLayout(null);
        userInfoPanel.setBounds(700, 350, 515, 170);
        userInfoPanel.setBackground(Color.WHITE);

        userInfoPanel.add(userTitle);
        userInfoPanel.add(userName);
        userInfoPanel.add(userPoint);
        // 유저정보 ===================

        // 오른쪽
        // 입력창
        inputBox.setBounds(700, 30, 520, 50);
        inputBox.setFont(new Font("Arial", Font.PLAIN, 22));
        // 상품등록
        btn_addProduct.setBounds(700, 100, 250, 100);
        btn_addProduct.setFont(new Font("Arial", Font.PLAIN, 18));
        btn_addProduct.addActionListener(this);
        // 고객 조회
        btn_getUser.setBounds(970, 100, 250, 100);
        btn_getUser.setFont(new Font("Arial", Font.PLAIN, 18));
        btn_getUser.addActionListener(this);
        // 포인트 사용
        btn_usePoint.setBounds(700, 210, 250, 100);
        btn_usePoint.setFont(new Font("Arial", Font.PLAIN, 18));
        btn_usePoint.addActionListener(this);
        // 수량 수정
        btn_fixAmount.setBounds(970, 210, 250, 100);
        btn_fixAmount.setFont(new Font("Arial", Font.PLAIN, 18));
        btn_fixAmount.addActionListener(this);
        //  환불
        btn_refund.setBounds(700, 550, 250, 100);
        btn_refund.setFont(new Font("Arial", Font.PLAIN, 18));
        btn_refund.addActionListener(this);
        // 결제
        btn_payment.setBounds(970, 550, 250, 100);
        btn_payment.setFont(new Font("Arial", Font.PLAIN, 18));
        btn_payment.addActionListener(this);
        // 취소
        btn_cancle.setBounds(700, 660, 250, 100);
        btn_cancle.setFont(new Font("Arial", Font.PLAIN, 18));
        btn_cancle.addActionListener(this);
        // 전체 취소
        btn_allCancle.setBounds(970, 660, 250, 100);
        btn_allCancle.setFont(new Font("Arial", Font.PLAIN, 18));
        btn_allCancle.addActionListener(this);

        add(btn_home);
        add(scrollPane);
        add(sumPrice);
        add(inputBox);
        add(btn_addProduct);
        add(btn_getUser);
        add(btn_payment);
        add(btn_usePoint);

        add(btn_fixAmount);
        add(btn_refund);
        add(btn_cancle);
        add(btn_allCancle);

        add(userName);
        add(userPoint);
        add(userTitle);
        add(userInfoPanel);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void updateTable(ArrayList<ProductOrderNumDTO> result) {
        // DefaultTableModel 객체 생성
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(headers); // 테이블의 헤더 설정
        totalPriceSum = 0;

        // result의 내용을 모델에 추가
        for (ProductOrderNumDTO productOrder : result) {
            Object[] row = {
                productOrder.getGetProductDTO().getProductName(),
                productOrder.getGetProductDTO().getProductPrice(),
                productOrder.getProductOrderNum(),
                (productOrder.getGetProductDTO().getProductPrice()
                    * productOrder.getProductOrderNum())
            };
            model.addRow(row);
        }
        // 테이블에 모델 설정
        table.setModel(model);
        totalPriceSum = saleController.getTotal();
        sumPrice.setText("총 가격 : " + totalPriceSum);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btn_home) {
            this.setVisible(false);
            saleController.openMainPage();
        } else {
            int key = Integer.parseInt(inputBox.getText());

            if (e.getSource() == btn_addProduct) {  //완료
                try {
                    ArrayList<ProductOrderNumDTO> result = saleController.getProductInfo(key);
                    // DefaultTableModel 객체 생성
                    DefaultTableModel model = new DefaultTableModel();
                    model.setColumnIdentifiers(headers); // 테이블의 헤더 설정
                    updateTable(result);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, "상품 번호를 확인해주세요.", "경고",
                        JOptionPane.ERROR_MESSAGE);
                }

            } else if (e.getSource() == btn_getUser) {    // 완료
                try {
                    MemberDTO result = saleController.getMemberInfo(key);
                    userName.setText("회원 이름 : " + result.getMemberName());
                    userPoint.setText("포인트 점수 : " + result.getMemberScore());
                    this.pre_point = result.getMemberScore();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, "회원 등록 되지 않은 유저입니다.", "경고",
                        JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == btn_payment) {
                try {
                    String result = saleController.sellSale();
                    if (result.equals("success")) {
                        DefaultTableModel emptyTableModel = new DefaultTableModel();
                        emptyTableModel.setColumnIdentifiers(headers);
                        table.setModel(emptyTableModel); // 테이블을 초기화합니다.
                        sumPrice.setText("총 가격 : 0"); // 총 가격을 초기화합니다.
                        userName.setText("회원 이름 : ");
                        userPoint.setText("포인트 점수 : ");
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, "결제 정보를 확인해보세요.", "경고",
                        JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == btn_cancle) {
                try {
                    ArrayList<ProductOrderNumDTO> result = saleController.cancleProduct(
                        selectedRow);
                    updateTable(result);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, "취소 항목을 선택하세요.", "경고",
                        JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == btn_allCancle) {
                try {
                    ArrayList<ProductOrderNumDTO> result = saleController.cancleProducts();
                    totalPriceSum = 0;
                    userName.setText("회원 이름 : ");
                    userPoint.setText("포인트 점수 : ");
                    inputBox.setText("");
                    updateTable(result);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, "서버 오류. 개발자에게 문의하세요.", "경고",
                        JOptionPane.ERROR_MESSAGE);
                }

            } else if (e.getSource() == btn_fixAmount) {  // 완료
                try {
                    ArrayList<ProductOrderNumDTO> result = saleController.updateOrderNum(
                        selectedRow, key);
                    // DefaultTableModel 객체 생성
                    DefaultTableModel model = new DefaultTableModel();
                    model.setColumnIdentifiers(headers); // 테이블의 헤더 설정
                    totalPriceSum = 0;
                    for (ProductOrderNumDTO productOrder : result) {
                        Object[] row = {
                            productOrder.getGetProductDTO().getProductName(),
                            productOrder.getGetProductDTO().getProductPrice(),
                            productOrder.getProductOrderNum(),
                            (productOrder.getGetProductDTO().getProductPrice()
                                * productOrder.getProductOrderNum())
                        };
                        model.addRow(row);
                    }
                    // 테이블에 모델 설정
                    table.setModel(model);
                    totalPriceSum = saleController.getTotal();
                    sumPrice.setText("총 가격 : " + totalPriceSum);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, "상품의 재고가 부족합니다.", "경고",
                        JOptionPane.ERROR_MESSAGE);
                }

            } else if (e.getSource() == btn_refund) {
                try {
                    ArrayList<ProductOrderNumDTO> result = saleController.returnProducts(
                        selectedRow);
                    updateTable(result);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, "환불할 항목을 선택해주세요.", "경고",
                        JOptionPane.ERROR_MESSAGE);
                }

            } else if (e.getSource() == btn_usePoint) {
                try {
                    // 이따 추가
                    int result = saleController.usePoint(key);
                    if (result == -1) {
                        JOptionPane.showMessageDialog(this, "포인트가 부족합니다!", "경고",
                            JOptionPane.ERROR_MESSAGE);
                    } else {
                        totalPriceSum = saleController.getTotal();
                        sumPrice.setText("총 가격 : " + totalPriceSum);
                        int point = pre_point - result;
                        userPoint.setText("포인트 점수 : " + (point));
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, "회원 조회후에 이용해주세요.", "경고",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
