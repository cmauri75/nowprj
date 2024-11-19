/* Italy Company - Fast Team(C) 2024 */
package it.nowprj.controller;

import it.nowprj.api.OrderApi;
import it.nowprj.dto.domain.Order;
import it.nowprj.mappers.OrderMapper;
import it.nowprj.model.OrderRequest;
import it.nowprj.model.OrderResponse;
import it.nowprj.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController implements OrderApi {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @Override
  public ResponseEntity<OrderResponse> apiV1OrderPost(OrderRequest order) {
    Order res = orderService.createOrder(order);
    return new ResponseEntity<>(OrderMapper.INSTANCE.mapToModel(res), HttpStatus.OK);
  }
}
