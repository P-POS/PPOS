package member;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class NewMemberDialog extends JDialog implements ActionListener {
    private JTextField tfName = new JTextField();;
    private JTextField tfNumber = new JTextField();;
    private JButton btnRegister = new JButton("등록");

    private MemberView parentView; // 부모 창에 대한 참조

    JPanel panel = new JPanel();
    JLabel lblName = new JLabel("이름:");
    JLabel lblNumber = new JLabel("번호:");
    JLabel lblError = new JLabel("이미 등록된 번호입니다");


    public NewMemberDialog(MemberView parent) {
        this.parentView = parent;

        setTitle("새 회원 등록");
        setSize(400, 250);
        setResizable(false);
        setLocationRelativeTo(parent);
        setModal(true); // 모달 다이얼로그 설정
        panel.setLayout(null);

        // 이름 라벨과 텍스트 필드
        lblName.setFont(new Font("Arial", Font.BOLD, 14));
        lblName.setBounds(50, 20, 60, 50); // 위치와 크기 지정
        panel.add(lblName);

        tfName.setBounds(120, 20, 150, 50); // 위치와 크기 지정
        panel.add(tfName);

        // 번호 라벨과 텍스트 필드
        lblNumber.setFont(new Font("Arial", Font.BOLD, 14));
        lblNumber.setBounds(50, 100, 60, 50); // 위치와 크기 지정
        panel.add(lblNumber);

        tfNumber.setBounds(120, 100, 150, 50); // 위치와 크기 지정
        panel.add(tfNumber);

        btnRegister.setBounds(250, 180, 100, 30); // 위치와 크기 지정
        panel.add(btnRegister);
        btnRegister.addActionListener(this);

        lblError.setBounds(20,180,130,30);
        lblError.setFont(new Font("Arial", Font.PLAIN, 12));
        lblError.setForeground(Color.RED);
        lblError.setVisible(false); // 처음엔 안보이게
        panel.add(lblError);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegister) {
            // 이름과 번호를 가져와서 회원 등록 메서드 호출
            String name = tfName.getText();
            Integer number = Integer.parseInt(tfNumber.getText());

            // 회원 등록 메서드 호출 (이 부분은 MemberController에 구현된 메서드를 호출하도록 변경해야 합니다)
            boolean check_create = parentView.memberController.createMember(number,name,null);
            if (check_create){
                // 다이얼로그 닫기
                lblError.setVisible(true);
                dispose();
                parentView.prepareList();
            }
            else{
                lblError.setVisible(true);
            }
        }
    }
}
