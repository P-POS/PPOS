package member;

import java.util.ArrayList;
import java.util.List;

public class MemberController {

    private MemberService memberService;
    private MemberView memberView;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        this.memberView = new MemberView(this);
    }

    public void createMember(int memberId, String memberName, Integer memberScore) {
        if (memberScore == null) {
            memberScore = 0;
        }
        MemberModel memberModel = new MemberModel(memberId, memberName, memberScore);
        memberService.createMember(memberModel);
        // memberView.displayMessage("Member created successfully.");
    }

    public void deleteMember(int memberId) {
        memberService.deleteMember(memberId);
        // memberView.displayMessage("Member deleted successfully.");
    }

    public void updateMember(int memberId, String memberName, int memberScore) {
        MemberModel memberModel = new MemberModel(memberId, memberName, memberScore);
        memberService.updateMember(memberModel);
        // memberView.displayMessage("Member updated successfully.");
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
}
