/* Italy Company - Fast Team(C) 2023 */
package it.nowprj.repository;

import static org.assertj.core.api.Assertions.assertThat;

import it.nowprj.builder.EnhancedInstancioBuilder;
import it.nowprj.entity.OrderDataEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class OrderDataRepositoryTest {
  @Autowired OrderDataRepository orderDataRepository;

  @Test
  @Transactional
  void when_order_is_created_it_should_be_found() {
    var orderDataEntityIB = new EnhancedInstancioBuilder<>(OrderDataEntity.class);
    orderDataEntityIB.persist(orderDataRepository);

    var savedOrder = orderDataRepository.findById(orderDataEntityIB.get().getOrderId());

    assertThat(orderDataRepository.findById(savedOrder.get().getOrderId()))
        .isPresent()
        .contains(savedOrder.get());
  }
}
