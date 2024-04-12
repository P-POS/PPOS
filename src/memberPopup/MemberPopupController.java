package memberPopup;

import member.MemberView;

public class MemberPopupController {

    MemberPopupModel memberPopupModel;
    MemberPopupView memberPopupView;

    MemberPopupController(MemberPopupModel memberPopupModel) {
        this.memberPopupModel = memberPopupModel;
        this.memberPopupView = new MemberPopupView(this);
    }
}
