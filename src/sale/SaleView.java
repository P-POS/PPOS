package sale;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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

    String name = "김신한";
    int point = 3013;
    String data = "2024-01-01";

    JLabel userName = new JLabel("회원 이름 : " + name);
    JLabel userPoint = new JLabel("포인트 점수 : " + Integer.toString(point) + " 점");
    JLabel userData = new JLabel("최근 거래일 : " + data);


    SaleController saleController;

    public SaleView() {

        setSize(1280, 960);
        setLayout(null);

        // 상품 테이블 설정.
        scrollPane.setBounds(20, 20, 600, 400);
        table.setRowHeight(30); // 원하는 높이로 조절
        table.setFont(new Font("Arial", Font.PLAIN, 18)); // 글꼴, 스타일, 크기 설정
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 18)); // 헤더의 글꼴, 굵은 스타일, 크기 설정
        // 총가격 설정
        sumPrice.setBounds(20, 440, 200, 40);
        sumPrice.setFont(new Font("Arial", Font.PLAIN, 18)); // 글꼴, 스타일, 크기 설정
        // 입력창
        inputBox.setBounds(20, 500, 300, 50);
        inputBox.setFont(new Font("Arial", Font.PLAIN, 22));
        // 상품등록
        btn_addProduct.setBounds(325, 500, 150, 50);
        btn_addProduct.setFont(new Font("Arial", Font.PLAIN, 18));
        // 고객 조회
        btn_getUser.setBounds(480, 500, 150, 50);
        btn_getUser.setFont(new Font("Arial", Font.PLAIN, 18));
        // 유저 정보
        userName.setBounds(20, 600, 200, 40);
        userPoint.setBounds(20, 650, 200, 40);
        userData.setBounds(20, 700, 200, 40);

        userName.setFont(new Font("Arial", Font.PLAIN, 18));
        userPoint.setFont(new Font("Arial", Font.PLAIN, 18));
        userData.setFont(new Font("Arial", Font.PLAIN, 18));

        add(scrollPane);
        add(sumPrice);
        add(inputBox);
        add(btn_addProduct);
        add(btn_getUser);
        add(userName);
        add(userPoint);
        add(userData);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int number = Integer.parseInt(inputBox.getText());

    }
}
