package SalesHistory;

public class SalesHistoryResDTO {
    private int transactionID;
    private int memberNum;
    private String memberName;
    private int totalAmount;
    private String date;

    public SalesHistoryResDTO(int transactionNum, int memberNum, String memberName, int totalAmount,
        String date) {
        this.transactionID = transactionNum;
        this.memberNum = memberNum;
        this.memberName = memberName;
        this.totalAmount = totalAmount;
        this.date = date;
    }

    public SalesHistoryResDTO(SalesHistory salesHistory) {
        this.transactionID = salesHistory.getTransactionID();
        this.memberNum = salesHistory.getMemberNum();
        this.memberName = salesHistory.getMemberName();
        this.totalAmount = salesHistory.getTotalAmount();
        this.date = salesHistory.getDate();
    }

    public int getTransactionID() {
        return transactionID;
    }

    public int getMemberNum() {
        return memberNum;
    }

    public String getMemberName() {
        return memberName;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public String getDate() {
        return date;
    }
}
