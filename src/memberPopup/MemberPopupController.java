package memberPopup;

import member.MemberService;

public class MemberPopupController {

    MemberService memberService;
    MemberPopupView memberPopupView;

    MemberPopupController(MemberService memberService) {
        this.memberService = memberService;
        this.memberPopupView = new MemberPopupView(this);
    }
}
