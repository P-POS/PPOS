package sale;

import java.util.ArrayList;
import java.util.Date;

public class SalesService {

    MemberRepository memberRepository;
    ProductRepository productRepository;
    SaleRepository saleRepository;
    ArrayList<ProductOrderNumDTO> productOrderNumDTOS;
    MemberDTO memberDTO;

    SalesService() {
        this.memberRepository = new MemberRepository();
        this.saleRepository = new SaleRepository();
        this.productRepository = new ProductRepository();
    }

    public String sellSale() {
        int totalSale = 0;
        for (ProductOrderNumDTO tempProduct : productOrderNumDTOS) {
            int tempProductPrice = tempProduct.productDTO.getProductPrice();
            int tempProductStock = tempProduct.productDTO.getProductStock();
            int tempProductNum = tempProduct.productOrderNum;
            totalSale += tempProductNum * tempProductPrice;
            if (tempProductStock < tempProductNum) {
                return String.format("%s의 재고가 부족합니다.", tempProduct.productDTO.getProductName());
            }
        }

        memberRepository.stackPoint(memberDTO.getClientId(), (int) Math.floor(totalSale * 0.01));

        saleRepository.sellSale(new SaleDTO(new Date(), totalSale, memberDTO.getClientId()));

        productRepository.sellProduct(productOrderNumDTOS);
        productOrderNumDTOS.clear();
        this.memberDTO = getMemberInfo(memberDTO.getClientId());
        return "success";
    }

    public ArrayList<ProductOrderNumDTO> getProductInfo(int productId) {
        ProductDTO productDTO = productRepository.getProductInfo(productId);
        ProductOrderNumDTO productOrderNumDTO = new ProductOrderNumDTO(productDTO, 1);
        productOrderNumDTOS.add(productOrderNumDTO);
        return productOrderNumDTOS;
    }

    public MemberDTO getMemberInfo(int memberId) {
        this.memberDTO = memberRepository.getMemberInfo(memberId);
        return memberDTO;
    }

    public String sellSaleUsePoint(int score) {
        if (memberDTO.getPointScore() < score) {
            return "fail";
        } else {
            memberRepository.usePoint(memberDTO, score);

            return "success";
        }
    }

    public ArrayList<ProductOrderNumDTO> cancleProduct(int sequence) {
        productOrderNumDTOS.remove(sequence);
        return productOrderNumDTOS;
    }

    public ArrayList<ProductOrderNumDTO> cancleProducts() {
        productOrderNumDTOS.clear();
        return productOrderNumDTOS;
    }
}
