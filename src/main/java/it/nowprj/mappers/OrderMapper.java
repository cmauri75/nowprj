/* Italy Company - Fast Team(C) 2024 */
package it.nowprj.mappers;

import it.nowprj.dto.domain.Item;
import it.nowprj.dto.domain.Order;
import it.nowprj.entity.OrderDataEntity;
import it.nowprj.entity.OrderDataItemEntity;
import it.nowprj.model.OrderResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
  OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

  OrderResponse mapToModel(it.nowprj.dto.domain.Order order);

  OrderDataEntity mapToEntity(Order order);

  OrderDataItemEntity mapToEntity(Item item);

  List<OrderDataItemEntity> mapToEntity(List<Item> item);
}
