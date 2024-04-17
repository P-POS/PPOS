package sale;

import salesHistory.SalesHistoryDTO;
import java.util.ArrayList;
import java.util.Date;
import member.MemberDTO;
import product.ProductOrderNumDTO;
import product.ProductDTO;
import product.ProductDBRepository;
public class SalesService {
    member.MemberRepository memberRepository;

    ProductDBRepository productRepository;
    SaleRepository saleRepository;
    ArrayList<ProductOrderNumDTO> productOrderNumDTOS;
    member.MemberDTO memberDTO;
    int totalCal;
    int usePoint;
    public SalesService(){
        this.memberRepository = new member.MemberRepository();

        this.saleRepository = new SaleRepository();
        this.productRepository = new product.ProductDBRepository();
        productOrderNumDTOS = new ArrayList<>();
        this.usePoint = 0;
        this.totalCal = 0;
        memberDTO = null;
    }

    public int getTotal() {

        this.totalCal = 0;
        for (ProductOrderNumDTO productOrderNumDTO : productOrderNumDTOS) {
            totalCal += productOrderNumDTO.getProductOrderNum() * productOrderNumDTO.getGetProductDTO()
                .getProductPrice();

        }
        return totalCal - usePoint;
    }

    public String sellSale() {

        for (ProductOrderNumDTO tempProduct : productOrderNumDTOS) {
            int tempProductStock = tempProduct.getGetProductDTO().getProductQuantity();
            int tempProductNum = tempProduct.getProductOrderNum();
            int tempProductPrice = tempProduct.getGetProductDTO().getProductPrice();
            if (tempProductStock < tempProductNum && tempProductPrice > 0) {
                return String.format("%s의 재고가 부족합니다.", tempProduct.getGetProductDTO().getProductName());
            }

        }

        if (memberDTO != null) {
            System.out.println((int) Math.floor((totalCal - usePoint) * 0.01));
            System.out.println(totalCal);
            memberRepository.stackPoint(memberDTO.getMemberId(),(int)Math.floor((totalCal-usePoint)*0.01));
            memberRepository.usePoint(memberDTO,this.usePoint);
            saleRepository.sellSale(new SaleDTO(new Date(),totalCal, memberDTO.getMemberId()));
        }
        else{
            saleRepository.sellSale(new SaleDTO(new Date(),totalCal));

        }
        productRepository.sellProduct(productOrderNumDTOS);
        productOrderNumDTOS.clear();
        this.memberDTO = null;
        return "success";
    }

    public ArrayList<ProductOrderNumDTO> getProductInfo(int productId) {

        ProductDTO productDTO = productRepository.selectProductByID(productId);
        ProductOrderNumDTO productOrderNumDTO = new ProductOrderNumDTO(productDTO, 1);
        productOrderNumDTOS.add(productOrderNumDTO);
        return productOrderNumDTOS;
    }

    public MemberDTO getMemberInfo(int memberId) {

        this.memberDTO = memberRepository.getMemberInfo(memberId);
        return memberDTO;
    }

    public int usePoint(int score) {

        if (memberDTO.getMemberScore() > score) {
            this.usePoint = score;
            return score;
        } else {
            return -1;
        }
    }

    public String sellSaleUsePoint(int score) {

        if (memberDTO.getMemberScore() < score) {
            return "pointFail";
        } else {
            memberRepository.usePoint(memberDTO, score);
            String key = sellSale();
            if (key.equals("success")) {
                memberRepository.usePoint(memberDTO, -score);
                return key;
            } else {
                return "success";
            }
        }
    }

    public ArrayList<ProductOrderNumDTO> cancleProduct(int sequence) {

        System.out.println(productOrderNumDTOS.size());
        productOrderNumDTOS.remove(sequence);

        return productOrderNumDTOS;
    }

    public ArrayList<ProductOrderNumDTO> cancleProducts() {

        productOrderNumDTOS.clear();
        totalCal = 0;
        usePoint = 0;
        this.memberDTO = null;
        return productOrderNumDTOS;
    }

    public ArrayList<ProductOrderNumDTO> returnProduct(int sequence) {

        ProductOrderNumDTO productOrderNumDTO = productOrderNumDTOS.get(sequence);
        productOrderNumDTO.setRefundProduct();
        productOrderNumDTOS.set(sequence, productOrderNumDTO);
        return productOrderNumDTOS;
    }

    public ArrayList<ProductOrderNumDTO> updateOrderNum(int sequence, int orderNum) {

        ProductOrderNumDTO productOrderNumDTO = productOrderNumDTOS.get(sequence);
        productOrderNumDTO.setProductOrderNum(orderNum);
        productOrderNumDTOS.set(sequence, productOrderNumDTO);
        return productOrderNumDTOS;
    }

    public void setMemberDTONull() {
        this.memberDTO = null;
    }

    public String getLatestSaleDate(int memberId) {

        String latestSaleDate = saleRepository.getLatestSaleDate(memberId);
        if (latestSaleDate != null) {
            return latestSaleDate;
        } else {
            return null;
        }
    }

    public boolean isNumberic(String str) {

        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    salesHistory.SalesHistoryDTO getSalesHistory(String searchSource) {

        if (isNumberic(searchSource)) {
            return saleRepository.getSalesHistoryByID(Integer.parseInt(searchSource));
        } else {
            return saleRepository.getSalesHistoryByMemberName(searchSource);
        }
    }

    ArrayList<SalesHistoryDTO> getSalesHistories() {

        return saleRepository.getSalesHistories();
    }

    boolean refundSalesHistory(int transactionID) {

        return saleRepository.refundSalesHistory(transactionID);
    }


}
