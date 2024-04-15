package member;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberView extends JFrame implements ActionListener {

    MemberController memberController;

    String classRow[] = {"고객번호","고객이름","포인트점수","최근거래일자","조회","삭제"};
    //    String classThings[][];
    String classThings[][] = {
            {"1", "홍길동", "100","2024-03-30"},
            {"2", "이순신", "200","2024-04-03"},
            {"3", "강감찬", "150","2024-02-08"}
            // 이하 추가 데이터 필요
    };
    DefaultTableCellRenderer item_renderer = new DefaultTableCellRenderer();
    DefaultTableCellRenderer header_renderer;
    DefaultTableModel model_member = new DefaultTableModel(classThings, classRow){
        public boolean isCellEditable(int i, int c) {
            if(c==3)
                return true;
            else return false;
        }
    };

    JTable tb_member = new JTable(model_member);
    JScrollPane sp_member = new JScrollPane(tb_member);
    JPanel p_search= new JPanel();
    JPanel p_all = new JPanel();

    JTextField tf_member = new JTextField();
    JButton btn_search = new JButton("검색");
    JButton btn_register = new JButton("등록");

    JTableHeader header;
    Dimension headerSize;

    public MemberView(MemberController memberController){
        this.memberController = memberController;

        // 전체 레이아웃 설정
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 960);
        this.setFocusable(true);
        this.setResizable(false);

        // 큰 JPanel 레이아웃 설정
        p_all.setLayout(null); // BorderLayout 설정

        // JPanel 내부의 레이아웃을 설정
        p_search.setLayout(null);
        p_search.setBounds(0,0,1280,150);

        // 검색 패널에 들어갈 컴포넌트 사이즈 설정
        tf_member.setBounds(110,30,890,60);
        btn_search.setBounds(1025,30,60,60);
        btn_register.setBounds(1090,30,60,60);

        // panel에 텍스트 필드, 조회 버튼, 검색 버튼 추가
        p_search.add(tf_member);
        p_search.add(btn_search);
        p_search.add(btn_register);

        // 테이블 구성
        header_renderer = (DefaultTableCellRenderer) tb_member.getTableHeader().getDefaultRenderer();
        header_renderer.setHorizontalAlignment(SwingConstants.CENTER);
        item_renderer.setHorizontalAlignment(SwingConstants.CENTER);
        tb_member.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tb_member.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb_member.getColumnModel().getColumn(0).setPreferredWidth(260);
        tb_member.getColumnModel().getColumn(1).setPreferredWidth(260);
        tb_member.getColumnModel().getColumn(2).setPreferredWidth(260);
        tb_member.getColumnModel().getColumn(3).setPreferredWidth(260);
        for(int i=0;i<4;i++) {
            tb_member.getColumnModel().getColumn(i).setCellRenderer(item_renderer);
        }
        tb_member.getColumnModel().getColumn(4).setPreferredWidth(67);
        tb_member.getColumnModel().getColumn(5).setPreferredWidth(67);
        tb_member.getColumn("조회").setCellRenderer(new ButtonRendererSearch());
        tb_member.getColumn("삭제").setCellRenderer(new ButtonRendererDelete());
        // tb_member.getColumn("삭제").setCellEditor(new ButtonEditor1(new JCheckBox()));
        header = tb_member.getTableHeader();
        headerSize = header.getPreferredSize();
        headerSize.height = 30; // 헤더의 높이를 30으로 설정
        header.setPreferredSize(headerSize);
        header.setFont(new Font("Arial", Font.BOLD, 15)); // 원하는 폰트로 변경
        tb_member.getTableHeader().setReorderingAllowed(false);
        tb_member.setFont(new Font("Arial",Font.PLAIN,16));
        tb_member.setRowHeight(30);
        tb_member.setShowGrid(true);
        tb_member.setSelectionBackground(Color.LIGHT_GRAY);
        tb_member.setFocusable(true);

        // 리스트를 스크롤 가능하게 만듭니다.
        sp_member.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp_member.setBounds(40,150,1200,750);
        //sp_member.setPreferredSize(new Dimension(1280, 700)); // 스크롤 패널 크기 설정

        // 큰 패널에 작은 패널들 넣기
        p_all.add(p_search); // 검색 패널을 BorderLayout의 NORTH에 추가
        p_all.add(sp_member); // 스크롤 패널을 BorderLayout의 CENTER에 추가

        this.getContentPane().add(p_all);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    class ButtonRendererSearch extends JButton implements TableCellRenderer {
        protected JButton button;
        public ButtonRendererSearch() {
            setOpaque(true);
        }
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            button = new JButton("조회");
            button.setContentAreaFilled(false);
            button.setFont(new Font("Arial",Font.BOLD,14));
            button.setBounds(0,0,60,30);
            //setText((value == null) ? "" : value.toString());
            return button;
        }
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
            button.setFont(new Font("Arial",Font.BOLD,14));
            button.setBounds(0,0,60,30);
            //setText((value == null) ? "" : value.toString());
            return button;
        }
    }
}
