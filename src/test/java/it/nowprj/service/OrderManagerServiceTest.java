/* Italy Company - Fast Team(C) 2023 */
package it.nowprj.service;

import static org.assertj.core.api.Assertions.assertThat;

import it.nowprj.entity.ItemEntity;
import it.nowprj.model.Item;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderManagerServiceTest {

  @Autowired OrderManagerService orderManagerService;

  @ParameterizedTest
  @CsvSource(
      value = {"0,0", "0,10", "10,0", "10,10"},
      delimiter = ',')
  void when_calculate_order_calculations_are_correct(int qty1, int qty2) {
    double price1 = 1.0;
    double vatPercent1 = 0.1;
    double price2 = 10.0;
    double vatPercent2 = 0.22;

    ItemEntity itemEntity1 = new ItemEntity(1, price1, vatPercent1);
    ItemEntity itemEntity2 = new ItemEntity(2, price2, vatPercent2);
    Item item1 = new Item(1, qty1);
    Item item2 = new Item(2, qty2);
    var createdOrder =
        orderManagerService.calculateOrderFromItems(
            List.of(itemEntity1, itemEntity2), List.of(item1, item2));

    assertThat(createdOrder.orderPrice()).isEqualTo(price1 * qty1 + price2 * qty2);
    assertThat(createdOrder.orderVat())
        .isEqualTo(price1 * vatPercent1 * qty1 + price2 * vatPercent2 * qty2);
  }
}
