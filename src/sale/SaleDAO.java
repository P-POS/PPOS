package sale;

public interface SaleDAO {

    void sellSale(SaleDTO saleDTO);

    String getLatestSaleDate(int memberId);
}
