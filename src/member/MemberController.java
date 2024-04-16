package member;

import main.MainController;
import java.util.ArrayList;
import sale.SaleController;

public class MemberController {

    private MainController mainController;
    private MemberService memberService;
    private SaleController saleController;

    public MemberController(MainController mainController) {
        memberService = new MemberService();
        this.mainController = mainController;
        new MemberView(this);
//        this.saleController = new SaleController(mainController);
    }

    public void openMainPage() {
        mainController.openMainPage();
    }

    public MemberController(MemberService memberService) {

        this.memberService = memberService;
        new MemberView(this);
    }

    public boolean createMember(int memberId, String memberName, Integer memberScore) {

        if (memberScore == null) {
            memberScore = 0;
        }
        try {
            MemberModel memberModel = new MemberModel(memberId, memberName, memberScore);
            memberService.createMember(memberModel);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public boolean deleteMember(int memberId) {

        try {
            memberService.deleteMember(memberId);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public boolean updateMember(int memberId, String memberName, int memberScore) {

        try {
            MemberModel memberModel = new MemberModel(memberId, memberName, memberScore);
            memberService.updateMember(memberModel);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public ArrayList getMember(int memberId) {

        return memberService.getMember(memberId);
    }

    public ArrayList getMemberUseId(int memberId) {

        return memberService.getMemberUseId(memberId);
    }

    public ArrayList getMemberUseName(String memberName) {

        return memberService.getMemberUseName(memberName);
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

        return saleController.getLatestSaleDate(memberId);
    }
}
