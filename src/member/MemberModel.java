package member;

public class MemberModel {

    private int memberId;
    private String memberName;
    private int memberScore;

    public MemberModel(int memberId, String memberName, int memberScore) {
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

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setMemberScore(int memberScore) {
        this.memberScore = memberScore;
    }
}
