package SalesHistory;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import main.MainController;
import main.MainView;

public class SalesHistoryView extends JFrame implements ActionListener {
    private final SalesHistoryController salesHistoryController;

    DefaultTableModel model;
    JTable table;
    JScrollPane scrollPane;

    DefaultTableCellRenderer item_renderer = new DefaultTableCellRenderer();
    DefaultTableCellRenderer header_renderer;

    // 검색어 입력창
    JTextField searchInput = new JTextField("고객번호, 고객이름");

    // 버튼
    JButton btn_home = new JButton("홈");
    JButton btn_search = new JButton("검색");
    JButton btn_all = new JButton("전체 조회");
    JButton btn_return = new JButton("반품");
    
    JLabel label = new JLabel("거래내역");
    Font mainFont = new Font("맑은 고딕", Font.BOLD, 25);
    Font subFont = new Font("맑은 고딕", Font.PLAIN, 16);
    Font btnFont = new Font("맑은 고딕", Font.PLAIN, 14);

    public SalesHistoryView(SalesHistoryController salesHistoryController) {
        this.salesHistoryController = salesHistoryController;
        // 프레임 크기 설정 및 화면에 표시
        setSize(1280, 960);
        setLayout(null);

        // 컬럼명 설정
        String[] columnNames = {"거래번호", "고객이름", "고객번호", "금액", "날짜"};
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

        loadSalesHistories();

        searchInput.setForeground(Color.GRAY); // placeholder 색상 설정

        btn_home.addActionListener(this);
        btn_search.addActionListener(this);
        btn_all.addActionListener(this);
        btn_return.addActionListener(this);

        label.setBounds(20, 20, 1280, 50);
        scrollPane.setBounds(20, 150, 1220, 740);
        searchInput.setBounds(20, 80, 820, 50);
        btn_home.setBounds(1175, 20, 60, 50);
        btn_search.setBounds(855, 80, 120, 50);
        btn_all.setBounds(985, 80, 120, 50);
        btn_return.setBounds(1115, 80, 120, 50);

        label.setFont(mainFont);
        table.setFont(subFont);
        header.setFont(subFont);
        header.setBackground(Color.lightGray);
        table.setRowHeight(24);
        searchInput.setFont(btnFont);
        btn_home.setFont(btnFont);
        btn_search.setFont(btnFont);
        btn_all.setFont(btnFont);
        btn_return.setFont(btnFont);

        add(label);
        add(scrollPane);
        add(searchInput);
        add(btn_home);
        add(btn_search);
        add(btn_all);
        add(btn_return);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
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

        // 모델 데이터 초기화
        model.setRowCount(0);

        // 거래 목록을 JTable 모델에 추가
        for (SalesHistory salesHistory : salesHistories) {
            model.addRow(new Object[]{salesHistory.getTransactionID(), salesHistory.getMemberName(), salesHistory.getMemberNum(), salesHistory.getTotalAmount(), salesHistory.getDate()});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_home) {
            this.setVisible(false);
            salesHistoryController.openMainPage();
        } else if (e.getSource() == btn_search) {
            boolean isNumeric = searchInput.getText().matches("-?\\d+(\\.\\d+)?"); // 숫자인지 확인
            // 검색 버튼을 클릭했을 때
            String searchText = searchInput.getText();
            model.setRowCount(0); // 테이블 데이터 초기화

            // 거래 목록에서 검색어에 해당하는 거래 목록만 필터링하여 테이블에 추가
            for (SalesHistory salesHistory : salesHistoryController.getSalesHistories()) {
                if (isNumeric) {
                    // searchText가 숫자일 경우, getMemberNum과 비교
                    int searchNum = Integer.parseInt(searchText);
                    if (salesHistory.getMemberNum() == searchNum) {
                        model.addRow(new Object[]{salesHistory.getTransactionID(), salesHistory.getMemberName(), salesHistory.getMemberNum(), salesHistory.getTotalAmount(), salesHistory.getDate()});
                    }
                } else {
                    // searchText가 문자열일 경우, getMemberName 비교
                    if (salesHistory.getMemberName().contains(searchText)) {
                        model.addRow(new Object[]{salesHistory.getTransactionID(), salesHistory.getMemberName(), salesHistory.getMemberNum(), salesHistory.getTotalAmount(), salesHistory.getDate()});
                    }
                }
            }
        } else if (e.getSource() == btn_all) {
            // 전체 거래 내역 조회 버튼을 클릭했을 때
            loadSalesHistories();
            searchInput.setForeground(Color.GRAY);
            searchInput.setText("고객번호, 고객이름");
        } else if (e.getSource() == btn_return) {
            // 반품 버튼을 클릭했을 때
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) { // 행이 하나라도 선택되었는지 확인
                // 선택된 행의 데이터 가져오기
                int transactionID = (int) table.getValueAt(selectedRow, 0);

                if (salesHistoryController.refundSalesHistory(transactionID)) {
                    JLabel label = new JLabel("반품되었습니다.");
                    label.setFont(btnFont);
                    JOptionPane.showMessageDialog(this, label, "반품 완료", JOptionPane.INFORMATION_MESSAGE);
                    loadSalesHistories();
                }
            } else {
                // 행이 선택되지 않았을 경우 경고 메시지 표시
                JLabel label = new JLabel("반품할 내역을 선택해주세요.");
                label.setFont(btnFont);
                JOptionPane.showMessageDialog(this, label, "경고", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
