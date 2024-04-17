package member;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class MemberView extends JFrame implements ActionListener {

    MemberController memberController;

    String classRow[] = {"고객번호", "고객이름", "포인트점수", "최근거래일자", "삭제"};
    String classThings[][] = {};
    DefaultTableCellRenderer item_renderer = new DefaultTableCellRenderer();
    DefaultTableCellRenderer header_renderer;
    DefaultTableModel model_member = new DefaultTableModel(classThings, classRow) {
        public boolean isCellEditable(int i, int c) { // 최근 거래일자 까지는 수정안되게
            if (c == 4) {
                return true;
            } else {
                return false;
            }
        }
    };

    JLabel lb_title = new JLabel("고객정보");
    JTable tb_member = new JTable(model_member);
    JScrollPane sp_member = new JScrollPane(tb_member);
    JPanel p_search = new JPanel();
    JPanel p_all = new JPanel();

    JTextField tf_member = new JTextField("고객번호, 고객이름");
    JButton btn_search = new JButton("검색");
    JButton btn_register = new JButton("등록");
    JButton btn_searchAll = new JButton("전체조회");
    JButton btn_home = new JButton("홈");

    JTableHeader header;
    Dimension headerSize;

    Font mainFont = new Font("맑은 고딕", Font.BOLD, 25);
    Font headerFont = new Font("맑은 고딕", Font.BOLD, 16);
    Font subFont = new Font("맑은 고딕", Font.PLAIN, 14);

    public MemberView(MemberController memberController) {
        this.memberController = memberController;

        prepareList();

        // 전체 레이아웃 설정
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 960);
        this.setFocusable(true);
        this.setResizable(false);

        // 큰 JPanel 레이아웃 설정
        p_all.setLayout(null); // BorderLayout 설정

        // JPanel 내부의 레이아웃을 설정
        p_search.setLayout(null);
        p_search.setBounds(0, 0, 1280, 150);

        // 검색 패널에 들어갈 컴포넌트 사이즈 설정
        lb_title.setFont(mainFont);
        lb_title.setBounds(45, 15, 500, 50);
        tf_member.setBounds(40, 75, 810, 50);
        tf_member.setForeground(Color.GRAY);
        tf_member.setFont(subFont);
        btn_search.setBounds(870, 75, 120, 50);
        btn_searchAll.setBounds(995, 75, 120, 50);
        btn_register.setBounds(1120, 75, 120, 50);
        btn_home.setBounds(1180, 15, 60, 50); // 좌표 및 크기 설정

        // 버튼 액션 리스너
        btn_home.addActionListener(this); // 액션 리스너 추가
        btn_search.addActionListener(this);
        btn_register.addActionListener(this);
        btn_searchAll.addActionListener(this);

        // panel에 텍스트 필드, 조회 버튼, 검색 버튼 추가
        p_search.add(lb_title);
        p_search.add(tf_member);
        p_search.add(btn_search);
        p_search.add(btn_register);
        p_search.add(btn_searchAll);
        p_search.add(btn_home); // 패널에 버튼 추가

        // 테이블 구성
        header_renderer = (DefaultTableCellRenderer) tb_member.getTableHeader()
            .getDefaultRenderer();
        header_renderer.setHorizontalAlignment(SwingConstants.CENTER);
        item_renderer.setHorizontalAlignment(SwingConstants.CENTER);
        tb_member.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tb_member.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb_member.getColumnModel().getColumn(0).setPreferredWidth(270);
        tb_member.getColumnModel().getColumn(1).setPreferredWidth(270);
        tb_member.getColumnModel().getColumn(2).setPreferredWidth(270);
        tb_member.getColumnModel().getColumn(3).setPreferredWidth(270);
        for (int i = 0; i < 4; i++) {
            tb_member.getColumnModel().getColumn(i).setCellRenderer(item_renderer);
        }
        tb_member.getColumnModel().getColumn(4).setPreferredWidth(100);
        tb_member.getColumn("삭제").setCellRenderer(new ButtonRendererDelete());
        tb_member.getColumn("삭제").setCellEditor(new ButtonEditorDelete(new JCheckBox()));

        // tb_member.getColumn("삭제").setCellEditor(new ButtonEditor1(new JCheckBox()));
        header = tb_member.getTableHeader();
        headerSize = header.getPreferredSize();
        headerSize.height = 30; // 헤더의 높이를 30으로 설정
        header.setPreferredSize(headerSize);
        header.setFont(headerFont); // 원하는 폰트로 변경
        tb_member.getTableHeader().setReorderingAllowed(false);
        tb_member.setFont(subFont);
        tb_member.setRowHeight(30);
        tb_member.setShowGrid(true);
        tb_member.setSelectionBackground(Color.LIGHT_GRAY);
        tb_member.setFocusable(true);

        // 리스트를 스크롤 가능하게 만듭니다.
        sp_member.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp_member.setBounds(40, 150, 1200, 740);

        // 큰 패널에 작은 패널들 넣기
        p_all.add(p_search); // 검색 패널을 BorderLayout의 NORTH에 추가
        p_all.add(sp_member); // 스크롤 패널을 BorderLayout의 CENTER에 추가

        this.getContentPane().add(p_all);

        setVisible(true);

        tf_member.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // 포커스를 얻었을 때, placeholder 효과 주지 않기
                if (tf_member.getText().equals("고객번호, 고객이름")) {
                    tf_member.setText("");
                    tf_member.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // 포커스를 잃었을 때, 텍스트 필드가 비어있다면 placeholder 효과 주기
                if (tf_member.getText().isEmpty()) {
                    tf_member.setForeground(Color.GRAY);
                    tf_member.setText("고객번호, 고객이름");
                }
            }
        });


    }

    class ButtonRendererDelete extends JButton implements TableCellRenderer {

        protected JButton button;

        public ButtonRendererDelete() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
            button = new JButton("삭제");
            button.setContentAreaFilled(false);
            button.setFont(subFont);
            button.setBounds(0, 0, 60, 30);
            return button;
        }
    }

    class ButtonEditorDelete extends DefaultCellEditor {

        protected JButton button;
        private boolean isPushed;
        public String buttonSID;
        public int row, column;

        public ButtonEditorDelete(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == button) {
                        buttonSID = (String) tb_member.getValueAt(row, 0);
                        memberController.deleteMember(Integer.parseInt(buttonSID));
                        prepareList();
                    }
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
            this.row = row;
            this.column = column;
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            return isPushed;
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_home) { // 이벤트 발생한게 뒤로가기
            this.dispose(); // 현재 창 닫기
            memberController.openMainPage();
        } else if (e.getSource() == btn_search) { // 이벤트 발생한게 검색버튼
            String searchInput = tf_member.getText(); // 텍스트 필드 값
            if (searchInput.equals("고객번호, 고객이름")) { // 아무것도 검색 안하면 전체 리스트
                prepareList();
            } else {
                prepareList(searchInput); // 검색창에 있는 내용으로 검색
            }
        } else if (e.getSource() == btn_register) { // 이벤트 발생한게 등록버튼
            // 새 회원 등록 다이얼로그 띄우기
            new NewMemberDialog(this);
        } else if (e.getSource() == btn_searchAll) {
            tf_member.setForeground(Color.GRAY);
            tf_member.setText("고객번호, 고객이름");
            prepareList();
        }
    }

    public void prepareList() {
        model_member.getDataVector().removeAllElements();
        String[] list = new String[4];
        ArrayList<MemberModel> memberModels = memberController.getAllMembers();

        for (int i = 0; i < memberModels.size(); i++) {
            list[0] = String.valueOf(memberModels.get(i).getMemberId());
            list[1] = memberModels.get(i).getMemberName();
            list[2] = String.valueOf(memberModels.get(i).getMemberScore());
            list[3] = memberController.getLatestSaleDate(memberModels.get(i).getMemberId());
            model_member.addRow(list);
        }
    }

    public void prepareList(String value) {
        boolean isNumeric = value.matches("-?\\d+(\\.\\d+)?"); // 숫자인지 확인
        model_member.getDataVector().removeAllElements();
        ArrayList<MemberModel> memberModels;
        String[] list = new String[4];
        if (isNumeric) {
            memberModels = memberController.getMemberUseId(Integer.parseInt(value));
        } else {
            memberModels = memberController.getMemberUseName(value);
        }

        if (memberModels.size() == 0) {
            this.repaint();
        } else {
            for (int i = 0; i < memberModels.size(); i++) {
                list[0] = String.valueOf(memberModels.get(i).getMemberId());
                list[1] = memberModels.get(i).getMemberName();
                list[2] = String.valueOf(memberModels.get(i).getMemberScore());
                list[3] = memberController.getLatestSaleDate(memberModels.get(i).getMemberId());
                model_member.addRow(list);
            }
        }
    }
}

class NewMemberDialog extends JDialog implements ActionListener {

    private JTextField tfName = new JTextField();
    ;
    private JTextField tfNumber = new JTextField();
    ;
    private JButton btnRegister = new JButton("등록");

    private MemberView parentView; // 부모 창에 대한 참조

    JPanel panel = new JPanel();
    JLabel lblName = new JLabel("이름:");
    JLabel lblNumber = new JLabel("번호:");
    JLabel lblError = new JLabel("이미 등록된 번호입니다");
    JLabel lblNoValue = new JLabel("입력값을 입력해주세요");


    public NewMemberDialog(MemberView parent) {
        this.parentView = parent;

        setTitle("새 회원 등록");
        setSize(400, 250);
        setResizable(false);
        setLocationRelativeTo(parent);
        setModal(true); // 모달 다이얼로그 설정
        panel.setLayout(null);

        // 이름 라벨과 텍스트 필드
        lblName.setFont(parentView.headerFont);
        lblName.setBounds(50, 20, 60, 50); // 위치와 크기 지정
        panel.add(lblName);

        tfName.setBounds(120, 20, 150, 50); // 위치와 크기 지정
        panel.add(tfName);

        // 번호 라벨과 텍스트 필드
        lblNumber.setFont(parentView.headerFont);
        lblNumber.setBounds(50, 100, 60, 50); // 위치와 크기 지정
        panel.add(lblNumber);

        tfNumber.setBounds(120, 100, 150, 50); // 위치와 크기 지정
        panel.add(tfNumber);

        btnRegister.setBounds(265, 165, 100, 40); // 위치와 크기 지정
        panel.add(btnRegister);
        btnRegister.addActionListener(this);

        lblError.setBounds(40, 170, 140, 30);
        lblError.setFont(parentView.subFont);
        lblError.setForeground(Color.RED);
        lblError.setVisible(false); // 처음엔 안보이게
        lblNoValue.setBounds(40, 170, 140, 30);
        lblNoValue.setFont(parentView.subFont);
        lblNoValue.setForeground(Color.RED);
        lblNoValue.setVisible(false); // 처음엔 안보이게
        panel.add(lblError);
        panel.add(lblNoValue);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegister) {
            // 이름과 번호를 가져와서 회원 등록 메서드 호출
            if (tfName.getText().isEmpty() || tfNumber.getText().isEmpty()) { // 둘 중 하나라도 빈칸이면
                lblError.setVisible(false);
                lblNoValue.setVisible(true);
            } else {
                String name = tfName.getText();
                Integer number = Integer.parseInt(tfNumber.getText());
                // 회원 등록 메서드 호출 (이 부분은 MemberController에 구현된 메서드를 호출하도록 변경해야 합니다)
                boolean check_create = parentView.memberController.createMember(number, name, null);
                if (check_create) {
                    // 다이얼로그 닫기
                    dispose();
                    lblError.setVisible(false);
                    lblNoValue.setVisible(false);
                    parentView.tf_member.setForeground(Color.GRAY);
                    parentView.tf_member.setText("고객번호, 고객이름");
                    parentView.prepareList();
                } else {
                    lblNoValue.setVisible(false);
                    lblError.setVisible(true);
                }
            }
        }
    }
}