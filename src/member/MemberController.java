package member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

public class MemberController {

    private MemberService memberService;
    private MemberView memberView;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        this.memberView = new MemberView(this);
    }

    public boolean createMember(int memberId, String memberName, Integer memberScore) {
        if (memberScore == null) {
            memberScore = 0;
        }
        try {
            MemberModel memberModel = new MemberModel(memberId, memberName, memberScore);
            memberService.createMember(memberModel);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public void deleteMember(int memberId) {
        memberService.deleteMember(memberId);
    }

    public void updateMember(int memberId, String memberName, int memberScore) {
        MemberModel memberModel = new MemberModel(memberId, memberName, memberScore);
        memberService.updateMember(memberModel);
    }

    public MemberModel getMember(int memberId) {
        MemberModel memberModel = memberService.getMember(memberId);
        if (memberModel != null) {
            return memberModel;
        } else {
            return null;
        }
    }

    public ArrayList getAllMembers() {
        ArrayList<MemberModel> members = memberService.getAllMembers();
        if (!members.isEmpty()) {
            return members;
        } else {
            return null;
        }
    }

    String getLatestSaleDate(int memberId) {
        String latestSaleDate = memberService.getLatestSaleDate(memberId);
        if (latestSaleDate != null) {
            return latestSaleDate;
        } else {
            return null;
        }
    }
}
