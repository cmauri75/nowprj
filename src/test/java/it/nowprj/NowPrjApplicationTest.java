/* Italy Company - Fast Team(C) 2023 */
package it.nowprj;

import it.nowprj.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NowPrjApplicationTest {

    @Autowired
    OrderService orderService;

    @Test
    void contextLoads() {
        assertThat(orderService).isNotNull();
    }
}
