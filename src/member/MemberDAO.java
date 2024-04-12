package member;

public interface MemberDAO {

    void createMember(String memberName);

    void deleteMember(int memberId);

    void updateMember(int memberId, String memberName, int memberPoint);

    String getMemberName(int memberId);

    void setMemberName(int memberId, String memberName);

    int getMemberPoint(int memberId);

    void setMemberPoint(int memberId, int memberPoint);
}
