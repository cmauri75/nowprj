/* Italy Company - Fast Team(C) 2023 */
package it.nowprj.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.nowprj.builder.EnhancedInstancioBuilder;
import it.nowprj.dto.domain.Order;
import it.nowprj.mappers.OrderMapper;
import it.nowprj.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired ObjectMapper objectMapper;

  @MockBean OrderService orderService;

  @Test
  void whenOrderIsCreatedDataIsConsistent() throws Exception {
    var res = new EnhancedInstancioBuilder<>(Order.class);
    when(orderService.createOrder(any())).thenReturn(res.get());

    var excepted = objectMapper.writeValueAsString(OrderMapper.INSTANCE.mapToModel(res.get()));

    this.mockMvc
        .perform(post("/api/v1/order").contentType(MediaType.APPLICATION_JSON).content("{}"))
        .andDo(print())
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(content().json(excepted));
  }
}
