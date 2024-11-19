/* Italy Company - Fast Team(C) 2023 */
package it.nowprj.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import it.nowprj.entity.ItemEntity;
import it.nowprj.entity.OrderDataEntity;
import it.nowprj.model.Item;
import it.nowprj.model.Order;
import it.nowprj.model.OrderRequest;
import it.nowprj.repository.ItemRepository;
import it.nowprj.repository.OrderDataRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class OrderServiceTest {

  @Autowired OrderService orderService;

  @MockBean ItemRepository itemRepository;

  @Autowired OrderDataRepository orderDataRepository;

  @Test
  @Transactional
  void when_items_not_found_exception_raises() {
    when(itemRepository.findAllById(any())).thenReturn(List.of());

    OrderRequest req = new OrderRequest(new Order(List.of(new Item(1, 1))));
    assertThrows(IllegalArgumentException.class, () -> orderService.createOrder(req));
  }

  @Transactional
  @ParameterizedTest
  @CsvSource(
      value = {"0,0", "0,10", "10,0", "10,10"},
      delimiter = ',')
  void when_request_is_ok_order_calculus_are_correct_and_order_is_stored(int qty1, int qty2) {
    double price1 = 1.0;
    double vatPercent1 = 0.1;
    double price2 = 10.0;
    double vatPercent2 = 0.22;

    ItemEntity itemEntity1 = new ItemEntity(1, price1, vatPercent1);
    ItemEntity itemEntity2 = new ItemEntity(2, price2, vatPercent2);
    when(itemRepository.findAllById(any())).thenReturn(List.of(itemEntity1, itemEntity2));

    Item item1 = new Item(1, qty1);
    Item item2 = new Item(2, qty2);
    OrderRequest req = new OrderRequest(new Order(List.of(item1, item2)));

    var createdOrder = orderService.createOrder(req);
    assertThat(createdOrder.orderPrice()).isEqualTo(price1 * qty1 + price2 * qty2);
    assertThat(createdOrder.orderVat())
        .isEqualTo(price1 * vatPercent1 * qty1 + price2 * vatPercent2 * qty2);

    List<OrderDataEntity> allOrders = orderDataRepository.findAll();
    assertThat(allOrders).hasSize(1);
    assertThat(allOrders.getFirst().getOrderPrice()).isEqualTo(createdOrder.orderPrice());
    assertThat(allOrders.getFirst().getOrderVat()).isEqualTo(createdOrder.orderVat());
    assertThat(allOrders.getFirst().getItems()).hasSize(2);
  }
}