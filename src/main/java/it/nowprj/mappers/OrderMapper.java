/* Decathlon Italy - Tacos Team(C) 2024 */
package it.nowprj.mappers;

import it.nowprj.entity.ItemEntity;
import it.nowprj.dto.domain.Item;
import it.nowprj.model.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);


    OrderResponse map(it.nowprj.dto.domain.Order order);

    Item map(ItemEntity item);

    List<Item> map(List<ItemEntity> items);
}
