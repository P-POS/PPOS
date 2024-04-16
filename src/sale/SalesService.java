package sale;

import java.util.ArrayList;
import java.util.Date;

public class SalesService {
    MemberRepository memberRepository;
    ProductRepository productRepository;
    SaleRepository saleRepository;
    ArrayList<ProductOrderNumDTO> productOrderNumDTOS;
    MemberDTO memberDTO;
    int totalCal;
    int usePoint;
    public SalesService(){
        this.memberRepository = new MemberRepository();
        this.saleRepository = new SaleRepository();
        this.productRepository = new ProductRepository();
        productOrderNumDTOS = new ArrayList<>();
        this.usePoint = 0;
        this.totalCal = 0;
        memberDTO = null;
    }
    public int getTotal(){
        this.totalCal = 0;
        for(ProductOrderNumDTO productOrderNumDTO :productOrderNumDTOS){
            totalCal+=productOrderNumDTO.getProductOrderNum()*productOrderNumDTO.getProductDTO().getProductPrice();

        }
        return totalCal-usePoint;
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

        if(memberDTO != null) {
            memberRepository.usePoint(memberDTO, this.usePoint);
            memberRepository.stackPoint(memberDTO.getClientId(),
                (int) Math.floor(totalSale * 0.01));
            saleRepository.sellSale(new SaleDTO(new Date(),totalSale, memberDTO.getClientId()));
        }
        else{
            saleRepository.sellSale(new SaleDTO(new Date(),totalSale));
        }
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

    public int usePoint(int score){
        if(memberDTO.getPointScore()>score){
            this.usePoint = score;
            return score;
        }
        else{
            return -1;
        }
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
        totalCal = 0;
        usePoint = 0;
        this.memberDTO = null;
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

    public void setMemberDTONull() {
        this.memberDTO = null;
    }

}
