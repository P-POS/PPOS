package member;

import java.util.List;

public interface MemberDAO {

    void createMember(MemberDTO member);

    void deleteMember(int memberId);

    void updateMember(MemberDTO member);

    MemberDTO getMember(int memberId);

    List<MemberDTO> getAllMembers();
}
