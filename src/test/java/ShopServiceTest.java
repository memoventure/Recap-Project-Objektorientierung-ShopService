import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() throws Exception{
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")), EnumOrderStatus.PROCESSING);
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    public void addOrderTest_whenExpectionThrown()
    {
        //GIVEN
        ShopService shopService = new ShopService();
        assertThrows(NoSuchElementException.class, () -> shopService.addOrder(List.of("2")));
    }

    @Test
    void orderStatusTest_whenOrder_expectListOfOrderStatus() throws Exception {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        List<Order> listOrders = shopService.getOrdersByStatus(EnumOrderStatus.PROCESSING);
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")), EnumOrderStatus.COMPLETED);
        assertNotEquals(listOrders.get(0).orderStatus(), expected.orderStatus());
        assertNotNull(expected.id());
    }

    @Test
    void updateOrderTest_whenOrderStatusCompletetd() throws NoSuchElementException {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);
        shopService.updateOrder(actual.id(), EnumOrderStatus.COMPLETED);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")), EnumOrderStatus.COMPLETED);
        List<Order> currentOrderList = shopService.getOrdersByStatus(EnumOrderStatus.COMPLETED);
        assertEquals(currentOrderList.get(0).orderStatus(), expected.orderStatus());
    }




}
