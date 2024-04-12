import product.ProductDBRepository;

public class MartAPP {

    public static void main(String[] args) {
        ProductDBRepository productDBRepository = new ProductDBRepository();

        productDBRepository.DBConnectTest();
    }
}