package member;

public interface MemberDAO {

    void createMember(MemberDTO member);

    void deleteMember(int memberId);

    void updateMember(MemberDTO member);

    MemberDTO getMember(int memberId);
}
