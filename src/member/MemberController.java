package member;

public class MemberController {

    MemberModel memberModel;
    MemberView memberView;

    MemberController(MemberModel memberModel) {
        this.memberModel = memberModel;
        this.memberView = new MemberView(this);
    }
}
