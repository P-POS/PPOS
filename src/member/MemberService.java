package member;

import java.util.ArrayList;
import java.util.List;

public class MemberService {

    private MemberDAO memberDAO;

    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public void createMember(MemberModel member) {
        int memberId = member.getMemberId();
        MemberDTO existingMember = memberDAO.getMember(memberId).get(0);

        if (existingMember != null) {
            throw new IllegalArgumentException("Member with ID " + memberId + " already exists.");
        }

        memberDAO.createMember(
            new MemberDTO(member.getMemberId(), member.getMemberName(), member.getMemberScore()));
    }

    public void deleteMember(int memberId) {
        MemberDTO existingMember = memberDAO.getMember(memberId).get(0);
        if (existingMember != null) {
            memberDAO.deleteMember(memberId);
        } else {
            throw new IllegalArgumentException("Member with ID " + memberId + " does not exist.");
        }
    }

    public void updateMember(MemberModel member) {
        int memberId = member.getMemberId();
        MemberDTO existingMember = memberDAO.getMember(memberId).get(0);
        if (existingMember != null) {
            memberDAO.updateMember(new MemberDTO(member.getMemberId(), member.getMemberName(),
                member.getMemberScore()));
        } else {
            throw new IllegalArgumentException("Member with ID " + memberId + " does not exist.");
        }
    }

    public MemberModel getMember(int memberId) {
        MemberDTO memberDTO = memberDAO.getMember(memberId).get(0);
        if (memberDTO != null) {
            return new MemberModel(memberDTO.getMemberId(), memberDTO.getMemberName(),
                memberDTO.getMemberScore());
        } else {
            return null;
        }
    }

    public ArrayList<MemberModel> getAllMembers() {
        ArrayList<MemberDTO> memberDTOs = memberDAO.getAllMembers();
        ArrayList<MemberModel> members = new ArrayList<>();
        for (MemberDTO memberDTO : memberDTOs) {
            members.add(new MemberModel(memberDTO.getMemberId(), memberDTO.getMemberName(),
                memberDTO.getMemberScore()));
        }
        return members;
    }

    public String getLatestSaleDate(int memberId) {
        String latestSaleDate = memberDAO.getLatestSaleDate(memberId);
        if (latestSaleDate != null) {
            return latestSaleDate;
        } else {
            return null;
        }
    }
}
