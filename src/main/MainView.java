package main;

import SalesHistory.SalesHistoryController;
import SalesHistory.SalesHistoryDBRepository;
import SalesHistory.SalesHistoryView;
import member.MemberController;
import member.MemberRepository;
import member.MemberService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import product.ProductController;
import product.ProductView;


public class MainView extends JFrame implements ActionListener {

    //필요한 버튼
    JLabel l_title = new JLabel("PPOS");
    JButton btn_member = new JButton("회원관리");
    JButton btn_sale = new JButton("판매서비스");
    JButton btn_product = new JButton("상품관리");
    JButton btn_salesHistory = new JButton("거래내역");

    MainController mainController;

    public MainView(MainController mainController) {
        this.mainController = mainController;

        setSize(1280, 960);
        setLayout(null); // 디폴트로 지정되있는거 해제

        // 컴포넌트 위치
        l_title.setBounds(250, 250 ,800,200);
        l_title.setFont(new Font("Serif",Font.BOLD,80));
        l_title.setHorizontalAlignment(JLabel.CENTER); // 수평 가운데 정렬

        btn_member.setBounds(150, 600, 200, 100);
        btn_sale.setBounds(400, 600, 200, 100);
        btn_product.setBounds(650, 600, 200, 100);
        btn_salesHistory.setBounds(900, 600, 200, 100);

        // 버튼 리스너 추가
        btn_member.addActionListener(this);
        btn_sale.addActionListener(this);
        btn_product.addActionListener(this);
        btn_salesHistory.addActionListener(this);

        // 화면에 추가
        add(l_title);
        add(btn_member);
        add(btn_sale);
        add(btn_product);
        add(btn_salesHistory);

        // 창 크면, 프로그램 종료
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_member) { // 이벤트 발생한게 회원버튼
            this.setVisible(false);
            mainController.openMemberPage();
        } else if (e.getSource() == btn_sale) { // 이벤트 발생한게 판매버튼
            this.setVisible(false);
            mainController.openSalePage();
        } else if (e.getSource() == btn_product) { // 이벤트 발생한게 상품버튼
            this.setVisible(false);
            mainController.openProductPage();
        } else if (e.getSource() == btn_salesHistory) { // 이벤트 발생한게 판매내역버튼
            this.setVisible(false);
            mainController.openSalesHistoryPage();
        }
    }
}

