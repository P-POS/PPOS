package SalesHistory;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;

public class SalesHistoryView extends JFrame implements ActionListener, MouseListener {
    // 테이블 임시데이터 설정
    String[][] data = {
        {"1", "홍길동", "20,000", "2024-04-12"},
        {"2", "이순신", "45,000", "2024-04-12"},
        {"3", "강감찬", "35,000", "2024-04-12"}
    };

    // 컬럼명 설정
    String[] columnNames = {"거래번호", "고객번호", "금액", "날짜"};

    // JTable 생성
    JTable table = new JTable(data, columnNames);
    JTableHeader header = table.getTableHeader();

    // 스크롤 패인에 테이블 추가
    JScrollPane scrollPane = new JScrollPane(table);

    // 버튼
    JButton btn_receipt = new JButton("영수증 출력");
    JButton btn_return = new JButton("반품");
    
    JLabel label = new JLabel("거래내역");
    Font mainFont = new Font("맑은 고딕", Font.BOLD, 25);
    Font subFont = new Font("맑은 고딕", Font.PLAIN, 16);
    Font btnFont = new Font("맑은 고딕", Font.PLAIN, 14);

    public SalesHistoryView() {
        // 프레임 크기 설정 및 화면에 표시
        setSize(1280, 960);
        setLayout(null);

        btn_receipt.addActionListener(this);
        btn_return.addActionListener(this);
        table.addMouseListener(this);

        label.setBounds(20, 20, 1280, 50);
        scrollPane.setBounds(20, 150, 1220, 740);
        btn_receipt.setBounds(1010, 80, 120, 50);
        btn_return.setBounds(1140, 80, 100, 50);

        label.setFont(mainFont);
        table.setFont(subFont);
        header.setFont(subFont);
        header.setBackground(Color.lightGray);
        table.setRowHeight(24);
        btn_receipt.setFont(btnFont);
        btn_return.setFont(btnFont);

        add(label);
        add(scrollPane);
        add(btn_receipt);
        add(btn_return);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_receipt) {
            // 영수증 출력 버튼을 클릭했을 때 발생하는 이벤트
            System.out.println("영수증 버튼 클릭");
        } else if (e.getSource() == btn_return) {
            // 반품 버튼을 클릭했을 때 발생하는 이벤트
            System.out.println("반품 버튼 클릭");
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
