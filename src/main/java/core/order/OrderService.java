package core.order;

public interface OrderService {

    /**
     * 주문 생성
     * @param memberId 회원 아이디
     * @param itemName 상품명
     * @param itemPrice 상품 가격
     * @return 생성된 주문 객체
     */
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
