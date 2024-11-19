/* Italy Company - Fast Team(C) 2024 */
package it.nowprj.service;

import it.nowprj.dto.domain.Order;
import it.nowprj.mappers.OrderMapper;
import it.nowprj.model.Item;
import it.nowprj.model.OrderRequest;
import it.nowprj.repository.ItemRepository;
import it.nowprj.repository.OrderDataRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  private final ItemRepository itemRepository;
  private final OrderDataRepository orderDataRepository;
  private final OrderManagerService orderManagerService;

  public OrderService(
      ItemRepository itemRepository,
      OrderDataRepository orderDataRepository,
      OrderManagerService orderManagerService) {
    this.itemRepository = itemRepository;
    this.orderDataRepository = orderDataRepository;
    this.orderManagerService = orderManagerService;
  }

  @Transactional
  public Order createOrder(OrderRequest request) {
    var itemIds = request.getOrder().getItems().stream().map(Item::getProductId).toList();
    var itemEntityList = itemRepository.findAllById(itemIds);

    if (itemEntityList.size() != itemIds.size()) {
      throw new IllegalArgumentException("Some items are not found");
    }

    var order =
        orderManagerService.calculateOrderFromItems(itemEntityList, request.getOrder().getItems());

    var orderDataEntity = OrderMapper.INSTANCE.mapToEntity(order);
    orderDataRepository.save(orderDataEntity);

    return order.withOrderId(orderDataEntity.getOrderId());
  }
}
