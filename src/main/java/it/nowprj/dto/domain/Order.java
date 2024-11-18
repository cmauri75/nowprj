package it.nowprj.dto.domain;

import java.util.List;

public record Order(Integer orderId, Double orderPrice, Double orderVat, List<Item> items) {
}
