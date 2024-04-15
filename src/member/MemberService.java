package member;

import java.util.ArrayList;

public class MemberService {

    private MemberDAO memberDAO;

    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public void createMember(MemberModel member) {
        int memberId = member.getMemberId();
        ArrayList<MemberDTO> existingMember = memberDAO.getMember(memberId);

        if (existingMember.size() != 0) {
            throw new IllegalArgumentException("Member with ID " + memberId + " already exists.");
        }

        memberDAO.createMember(
            new MemberDTO(member.getMemberId(), member.getMemberName(), member.getMemberScore()));
    }

    public void deleteMember(int memberId) {
        ArrayList<MemberDTO> existingMember = memberDAO.getMember(memberId);
        if (existingMember.size() != 0) {
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

    public ArrayList<MemberModel> getMember(int memberId) {
        ArrayList<MemberDTO> memberDTOs = memberDAO.getMember(memberId);
        ArrayList<MemberModel> members = new ArrayList<>();
        for (MemberDTO memberDTO : memberDTOs) {
            members.add(new MemberModel(memberDTO.getMemberId(), memberDTO.getMemberName(),
                memberDTO.getMemberScore()));
        }
        return members;
    }

    public ArrayList<MemberModel> getMemberUseName(String memberName) {
        ArrayList<MemberDTO> memberDTOs = memberDAO.getMemberUseName(memberName);
        ArrayList<MemberModel> members = new ArrayList<>();
        for (MemberDTO memberDTO : memberDTOs) {
            members.add(new MemberModel(memberDTO.getMemberId(), memberDTO.getMemberName(),
                memberDTO.getMemberScore()));
        }
        return members;
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
