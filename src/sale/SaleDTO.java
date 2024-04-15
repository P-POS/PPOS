package sale;

import java.util.Date;

public class SaleDTO {
    Date date;
    int saleId;
    int totalSale;
    int memberId;

    public SaleDTO(Date date, int saleId, int totalSale, int memberId) {
        this.date = date;
        this.saleId = saleId;
        this.totalSale = totalSale;
        this.memberId = memberId;
    }

    public SaleDTO(Date date, int totalSale, int memberId) {
        this.date = date;
        this.totalSale = totalSale;
        this.memberId = memberId;
    }

    public Date getDate() {
        return date;
    }

    public int getSaleId() {
        return saleId;
    }

    public int getTotalSale() {
        return totalSale;
    }

    public int getMemberId() {
        return memberId;
    }
}
