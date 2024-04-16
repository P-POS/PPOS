package sale;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class SaleView extends JFrame implements ActionListener {

    JPanel panel = new JPanel();

    String headers[] = {
        "상품이름", "가격", "수량", "총 가격"
    };

    String array[][] = {
        {"아몬드빼빼로", "1300", "4", "6400"},
        {"오뚜기 카레", "7800", "1", "7800"},
        {"아몬드빼빼로", "1300", "4", "6400"},
        {"오뚜기 카레", "7800", "1", "7800"},
        {"아몬드빼빼로", "1300", "4", "6400"},
        {"오뚜기 카레", "7800", "1", "7800"},
        {"아몬드빼빼로", "1300", "4", "6400"},
        {"오뚜기 카레", "7800", "1", "7800"},
        {"아몬드빼빼로", "1300", "4", "6400"},
        {"오뚜기 카레", "7800", "1", "7800"},
        {"아몬드빼빼로", "1300", "4", "6400"},
        {"오뚜기 카레", "7800", "1", "7800"},
        {"아몬드빼빼로", "1300", "4", "6400"},
        {"오뚜기 카레", "7800", "1", "7800"},
        {"아몬드빼빼로", "1300", "4", "6400"},
        {"오뚜기 카레", "7800", "1", "7800"},
        {"아몬드빼빼로", "1300", "4", "6400"},
        {"오뚜기 카레", "7800", "1", "7800"},
        {"아몬드빼빼로", "1300", "4", "6400"},
        {"오뚜기 카레", "7800", "1", "7800"},
        {"아몬드빼빼로", "1300", "4", "6400"},
        {"오뚜기 카레", "7800", "1", "7800"},
        {"아몬드빼빼로", "1300", "4", "6400"},
        {"오뚜기 카레", "7800", "1", "7800"},
    };
    JTextField inputBox = new JTextField();
    JTable table = new JTable(array, headers);
    JScrollPane scrollPane = new JScrollPane(table);
    JLabel sumPrice = new JLabel("총 가격 : 1000000000");
    JButton btn_addProduct = new JButton("상품 등록");
    JButton btn_getUser = new JButton("유저 조회");
    JButton btn_payment = new JButton("결제");
    JButton btn_usePoint = new JButton("포인트 사용");
    JButton btn_trash = new JButton("쓰레기 봉투");
    JButton btn_soju = new JButton("소주 공병");
    JButton btn_cancle = new JButton("취소");
    JButton btn_allCancle = new JButton("전체 취소");


    String name;
    int point;
    String data = "2024-01-01";
    JPanel userInfoPanel = new JPanel();
    JLabel userTitle = new JLabel("정보");
    JLabel userName = new JLabel("회원 이름 : ");
    JLabel userPoint = new JLabel("포인트 점수 : 0 점");


    SaleController saleController;

    public SaleView(SaleController saleController) {
        this.saleController = saleController;

        setSize(1280, 960);
        setLayout(null);

        // 상품 테이블 설정.
        scrollPane.setBounds(20, 30, 600, 650);
        table.setRowHeight(30); // 원하는 높이로 조절
        table.setFont(new Font("Arial", Font.PLAIN, 18)); // 글꼴, 스타일, 크기 설정
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 18)); // 헤더의 글꼴, 굵은 스타일, 크기 설정
        // 총가격 설정
        sumPrice.setBounds(200, 700, 200, 40);
        sumPrice.setFont(new Font("Arial", Font.PLAIN, 20)); // 글꼴, 스타일, 크기 설정

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

        // 결제
        btn_payment.setBounds(700, 210, 250, 100);
        btn_payment.setFont(new Font("Arial", Font.PLAIN, 18));
        btn_payment.addActionListener(this);
        // 포인트 사용
        btn_usePoint.setBounds(970, 210, 250, 100);
        btn_usePoint.setFont(new Font("Arial", Font.PLAIN, 18));
        btn_usePoint.addActionListener(this);
        // 쓰레기 봉투
        btn_trash.setBounds(700, 550, 250, 100);
        btn_trash.setFont(new Font("Arial", Font.PLAIN, 18));
        btn_trash.addActionListener(this);
        //  소주 공병
        btn_soju.setBounds(970, 550, 250, 100);
        btn_soju.setFont(new Font("Arial", Font.PLAIN, 18));
        btn_soju.addActionListener(this);
        // 취소
        btn_cancle.setBounds(700, 660, 250, 100);
        btn_cancle.setFont(new Font("Arial", Font.PLAIN, 18));
        btn_cancle.addActionListener(this);
        // 전체 취소
        btn_allCancle.setBounds(970, 660, 250, 100);
        btn_allCancle.setFont(new Font("Arial", Font.PLAIN, 18));
        btn_allCancle.addActionListener(this);

        add(scrollPane);
        add(sumPrice);
        add(inputBox);
        add(btn_addProduct);
        add(btn_getUser);
        add(btn_payment);
        add(btn_usePoint);

        add(btn_trash);
        add(btn_soju);
        add(btn_cancle);
        add(btn_allCancle);

        add(userName);
        add(userPoint);
        add(userTitle);
        add(userInfoPanel);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int key = Integer.parseInt(inputBox.getText());

        if (e.getSource() == btn_addProduct) {
            ArrayList<ProductOrderNumDTO> result = saleController.getProductInfo(key);

        }else if (e.getSource() == btn_getUser){
            MemberDTO result = saleController.getMemberInfo(key);

            userName.setText("회원 이름 : " + result.getClientName());
            userPoint.setText("포인트 점수 : " + result.getPointScore());

        } else if (e.getSource() == btn_payment) {

        } else if (e.getSource() == btn_cancle) {
            ArrayList<ProductOrderNumDTO> result = saleController.cancleProduct(key);
            DefaultTableModel model = (DefaultTableModel) table.getModel();

        } else if (e.getSource() == btn_allCancle) {
            ArrayList<ProductOrderNumDTO> result = saleController.cancleProducts();
        }

    }
}
