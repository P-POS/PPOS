package member;

import java.util.ArrayList;
import java.util.List;

public class MemberService {

    private MemberDAO memberDAO;

    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public void createMember(MemberModel member) {
        memberDAO.createMember(new MemberDTO(
            member.getMemberId(),
            member.getMemberName(),
            member.getMemberScore()));
    }

    public void deleteMember(int memberId) {
        memberDAO.deleteMember(memberId);
    }

    public void updateMember(MemberModel member) {
        memberDAO.updateMember(new MemberDTO(
            member.getMemberId(),
            member.getMemberName(),
            member.getMemberScore()));
    }

    public MemberModel getMember(int memberId) {
        MemberDTO memberDTO = memberDAO.getMember(memberId);
        return new MemberModel(
            memberDTO.getMemberId(),
            memberDTO.getMemberName(),
            memberDTO.getMemberScore());
    }

    public List<MemberModel> getAllMembers() {
        List<MemberDTO> memberDTOs = memberDAO.getAllMembers();
        List<MemberModel> members = new ArrayList<>();
        for (MemberDTO memberDTO : memberDTOs) {
            members.add(new MemberModel(
                memberDTO.getMemberId(),
                memberDTO.getMemberName(),
                memberDTO.getMemberScore()));
        }
        return members;
    }
}
