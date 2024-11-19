/* Italy Company - Fast Team(C) 2023 */
package it.nowprj;

import static org.assertj.core.api.Assertions.assertThat;

import it.nowprj.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NowPrjApplicationTest {

  @Autowired OrderService orderService;

  @Test
  void contextLoads() {
    assertThat(orderService).isNotNull();
  }
}
