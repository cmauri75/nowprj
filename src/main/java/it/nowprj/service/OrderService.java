package it.nowprj.service;

import it.nowprj.dto.domain.Item;
import it.nowprj.dto.domain.Order;
import it.nowprj.mappers.OrderMapper;
import it.nowprj.model.OrderRequest;
import it.nowprj.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final ItemRepository itemRepository;

    public OrderService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Order createOrder(OrderRequest request) {
        var items = itemRepository.findAll();


        var res = new Order(1,1d,0.1d, OrderMapper.INSTANCE.map(items));

        return res;
    }
}
