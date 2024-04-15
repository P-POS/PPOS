package sale;

import java.util.ArrayList;
import java.util.Date;

public class SalesService {
    MemberRepository memberRepository;
    ProductRepository productRepository;
    SaleRepository saleRepository;
    ArrayList<ProductOrderNumDTO> productOrderNumDTOS;
    MemberDTO memberDTO;
    public SalesService(){
        this.memberRepository = new MemberRepository();
        this.saleRepository = new SaleRepository();
        this.productRepository = new ProductRepository();
        productOrderNumDTOS = new ArrayList<ProductOrderNumDTO>();
    }

    public String sellSale(){
        int totalSale = 0;
        for(ProductOrderNumDTO tempProduct : productOrderNumDTOS){
            int tempProductPrice = tempProduct.productDTO.getProductPrice();
            int tempProductStock = tempProduct.productDTO.getProductStock();
            int tempProductNum = tempProduct.productOrderNum;
            totalSale += tempProductNum*tempProductPrice;
            if(tempProductStock<tempProductNum){
                return String.format("%s의 재고가 부족합니다.",tempProduct.productDTO.getProductName());
            }
        }

        memberRepository.stackPoint(memberDTO.getClientId() ,(int)Math.floor(totalSale*0.01));

        saleRepository.sellSale(new SaleDTO(new Date(),totalSale, memberDTO.getClientId()));

        productRepository.sellProduct(productOrderNumDTOS);
        productOrderNumDTOS.clear();
        this.memberDTO = getMemberInfo(memberDTO.getClientId());
        return "success";
    }

    public ArrayList<ProductOrderNumDTO> getProductInfo(int productId){
        ProductDTO productDTO = productRepository.getProductInfo(productId);
        ProductOrderNumDTO productOrderNumDTO = new ProductOrderNumDTO(productDTO,1);
        productOrderNumDTOS.add(productOrderNumDTO);
        return productOrderNumDTOS;
    }

    public MemberDTO getMemberInfo(int memberId){
        this.memberDTO = memberRepository.getMemberInfo(memberId);
        return memberDTO;
    }

    public String sellSaleUsePoint(int score){
        if(memberDTO.getPointScore()<score){
            return "pointFail";
        }
        else{
            memberRepository.usePoint(memberDTO,score);
            String key = sellSale();
            if(key!="success"){
                memberRepository.usePoint(memberDTO,-score);
                return key;
            }
            else
                return "success";
        }
    }

    public ArrayList<ProductOrderNumDTO> cancleProduct(int sequence){
        System.out.println(productOrderNumDTOS.size());
        productOrderNumDTOS.remove(sequence);

        return productOrderNumDTOS;
    }

    public ArrayList<ProductOrderNumDTO> cancleProducts(){
        productOrderNumDTOS.clear();
        return productOrderNumDTOS;
    }
    public ArrayList<ProductOrderNumDTO> returnProduct(int sequence){
        ProductOrderNumDTO productOrderNumDTO = productOrderNumDTOS.get(sequence);
        productOrderNumDTO.setRefundProduct();
        productOrderNumDTOS.set(sequence,productOrderNumDTO);
        return productOrderNumDTOS;
    }

    public ArrayList<ProductOrderNumDTO> updateOrderNum(int sequence, int orderNum){
        ProductOrderNumDTO productOrderNumDTO = productOrderNumDTOS.get(sequence);
        productOrderNumDTO.setProductOrderNum(orderNum);
        productOrderNumDTOS.set(sequence,productOrderNumDTO);
        return productOrderNumDTOS;
    }
}
