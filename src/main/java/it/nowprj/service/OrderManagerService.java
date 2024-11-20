/* Italy Company - Fast Team(C) 2024 */
package it.nowprj.service;

import it.nowprj.dto.domain.Item;
import it.nowprj.dto.domain.Order;
import it.nowprj.entity.ItemEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderManagerService {

  /**
   * Merge items and itemEntityList to calculate the order price and vat
   * @param itemEntityList list of items data from the database
   * @param items list of requested items to be used to create order
   * @return the order with the calculated price and vat. OrderId will be calculated outside (a smaller dto should be used for perfect separation of concerns)
   */
  public Order calculateOrderFromItems(
      List<ItemEntity> itemEntityList, List<it.nowprj.model.Item> items) {

    List<Item> itemList = new ArrayList<>(itemEntityList.size());
    double orderPrice = 0;
    double orderVat = 0;
    for (var item : items) {
      var itemEntity =
          itemEntityList.stream()
              .filter(i -> i.getProductId().equals(item.getProductId()))
              .findFirst()
              .orElseThrow();

      var price = itemEntity.getPrice() * item.getQuantity();
      var vat = itemEntity.getVatPercent() * price;

      itemList.add(new Item(item.getProductId(), item.getQuantity(), price, vat));
      orderPrice += price;
      orderVat += vat;
    }

    return new Order(null, orderPrice, orderVat, itemList);
  }
}
