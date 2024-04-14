package sale;

public interface MemberDAO {
    MemberDTO getMemberInfo(int memberId);
    void stackPoint(int memberId,int score);
}
