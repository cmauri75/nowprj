/* Italy Company - Fast Team(C) 2024 */
package it.nowprj.dto.domain;

import java.util.List;

public record Order(Integer orderId, Double orderPrice, Double orderVat, List<Item> items) {
  public Order withOrderId(Integer newOrderId) {
    return new Order(newOrderId, orderPrice, orderVat, items);
  }
}
