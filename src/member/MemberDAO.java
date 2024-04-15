package member;

import java.util.ArrayList;

public interface MemberDAO {

    void createMember(MemberDTO member);

    void deleteMember(int memberId);

    void updateMember(MemberDTO member);

    ArrayList<MemberDTO> getMember(int memberId);

    ArrayList<MemberDTO> getMemberUseName(String memberName);

    ArrayList<MemberDTO> getAllMembers();

    String getLatestSaleDate(int memberId);
}
