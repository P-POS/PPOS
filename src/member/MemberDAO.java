package member;

public interface MemberDAO {

    void createMember(String memberName);

    void deleteMember(int memberId);

    void updateMember(int memberId, String memberName, int memberScore);

    String getMemberName(int memberId);

    void setMemberName(int memberId, String memberName);

    int getMemberScore(int memberId);

    void setMemberScore(int memberId, int memberScore);
}
