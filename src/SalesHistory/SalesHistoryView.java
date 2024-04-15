package SalesHistory;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class SalesHistoryView extends JFrame implements ActionListener, MouseListener {
    SalesHistoryController salesHistoryController = new SalesHistoryController();

    DefaultTableModel model;
    JTable table;
    JScrollPane scrollPane;

    // 검색어 입력창
    JTextField searchInput = new JTextField("고객번호, 고객이름");

    // 버튼
    JButton btn_search = new JButton("검색");
    JButton btn_all = new JButton("전체 조회");
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

        // 컬럼명 설정
        String[] columnNames = {"거래번호", "고객이름", "고객번호", "금액", "날짜"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        JTableHeader header = table.getTableHeader();

        loadSalesHistories();

        searchInput.setForeground(Color.GRAY); // placeholder 색상 설정

        btn_search.addActionListener(this);
        btn_receipt.addActionListener(this);
        btn_return.addActionListener(this);
        table.addMouseListener(this);

        label.setBounds(20, 20, 1280, 50);
        scrollPane.setBounds(20, 150, 1220, 740);
        searchInput.setBounds(20, 80, 690, 50);
        btn_search.setBounds(725, 80, 120, 50);
        btn_all.setBounds(855, 80, 120, 50);
        btn_receipt.setBounds(985, 80, 120, 50);
        btn_return.setBounds(1115, 80, 120, 50);

        label.setFont(mainFont);
        table.setFont(subFont);
        header.setFont(subFont);
        header.setBackground(Color.lightGray);
        table.setRowHeight(24);
        searchInput.setFont(btnFont);
        btn_search.setFont(btnFont);
        btn_all.setFont(btnFont);
        btn_receipt.setFont(btnFont);
        btn_return.setFont(btnFont);

        add(label);
        add(scrollPane);
        add(searchInput);
        add(btn_search);
        add(btn_all);
        add(btn_receipt);
        add(btn_return);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        btn_search.requestFocusInWindow();
        
        // placeholder 효과를 주기 위한 코드
        searchInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // 포커스를 얻었을 때, placeholder 효과 주지 않기
                if (searchInput.getText().equals("고객번호, 고객이름")) {
                    searchInput.setText("");
                    searchInput.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                // 포커스를 잃었을 때, 텍스트 필드가 비어있다면 placeholder 효과 주기
                if (searchInput.getText().isEmpty()) {
                    searchInput.setForeground(Color.GRAY);
                    searchInput.setText("고객번호, 고객이름");
                }
            }
        });
    }

    private void loadSalesHistories() {
        // 상품 목록 가져오기
        ArrayList<SalesHistory> salesHistories = salesHistoryController.getSalesHistories();

        System.out.println("salesHistories: " + salesHistories);

        // 모델 데이터 초기화
        model.setRowCount(0);

        // 거래 목록을 JTable 모델에 추가
        for (SalesHistory salesHistory : salesHistories) {
            model.addRow(new Object[]{salesHistory.transactionID, salesHistory.memberName, salesHistory.memberNum, salesHistory.totalAmount, salesHistory.date});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_search) {
            // 검색 버튼을 클릭했을 때
            String searchText = searchInput.getText();
            model.setRowCount(0); // 테이블 데이터 초기화

            // 거래 목록에서 검색어에 해당하는 거래 목록만 필터링하여 테이블에 추가
            for (SalesHistory salesHistory : salesHistoryController.getSalesHistories()) {
                if (salesHistory.getMemberName().contains(searchText)) {
                    model.addRow(new Object[]{salesHistory.transactionID, salesHistory.memberName, salesHistory.memberNum, salesHistory.totalAmount, salesHistory.date});
                }
            }
        } else if (e.getSource() == btn_all) {
            // 전체 거래 내역 조회 버튼을 클릭했을 때
            loadSalesHistories();
        } else if (e.getSource() == btn_receipt) {
            // 영수증 출력 버튼을 클릭했을 때
            System.out.println("영수증 버튼 클릭");
        } else if (e.getSource() == btn_return) {
            // 반품 버튼을 클릭했을 때
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) { // 행이 하나라도 선택되었는지 확인
                // 선택된 행의 데이터 가져오기
                int transactionID = (int) table.getValueAt(selectedRow, 1);

                salesHistoryController.refundSalesHistory(transactionID);
            } else {
                // 행이 선택되지 않았을 경우 경고 메시지 표시
                JOptionPane.showMessageDialog(this, "반품할 내역을 선택해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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
