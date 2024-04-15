package member;

public class MemberDTO {

    private int memberId;
    private String memberName;
    private int memberScore;

    public MemberDTO(int memberId, String memberName, int memberScore) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberScore = memberScore;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public int getMemberScore() {
        return memberScore;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
            "memberId=" + memberId +
            ", memberName='" + memberName + '\'' +
            ", memberScore=" + memberScore +
            '}';
    }
}
