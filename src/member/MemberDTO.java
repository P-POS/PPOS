package member;

public class MemberDTO {
    private int memberId;
    private String memberName;
    private int memberScore;

    public MemberDTO() {
    }

    public MemberDTO(int memberId, String memberName, int memberScore) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberScore = memberScore;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getMemberScore() {
        return memberScore;
    }

    public void setMemberScore(int memberScore) {
        this.memberScore = memberScore;
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
