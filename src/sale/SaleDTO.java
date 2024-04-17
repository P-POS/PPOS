package sale;

import java.util.Date;

public class SaleDTO {

    private Date date;
    private int saleId;
    private int totalSale;
    private int memberId;

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

    public SaleDTO(Date date, int totalSale) {

        this.date = date;
        this.totalSale = totalSale;
        this.memberId = -1;
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
