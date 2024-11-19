/* Italy Company - Fast Team(C) 2023 */
package it.nowprj.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerITest {

  private static final String ORDER_JSON_REQ =
      """
            { "order": { "items": [ { "product_id": 1, "quantity": 1 }, { "product_id": 2, "quantity": 5 }, { "product_id": 3, "quantity": 1 } ] } }""";
  private static final String ORDER_JSON_EXCEPTED_RES =
      """
              {"orderId":1,"orderPrice":140.0,"orderVat":30.0,"items":[{"product_id":1,"quantity":1,"price":10.0,"vat":1.0},{"product_id":2,"quantity":5,"price":100.0,"vat":20.0},{"product_id":3,"quantity":1,"price":30.0,"vat":9.0}]}
              """;
  private static final String ORDER_JSON_ERROR_EXCEPTED_RES =
      """
          {
              "status": 500,
              "error": "Internal Server Error",
              "path": "/api/v1/order"
          }""";

  @Autowired TestRestTemplate testRestTemplate;

  @Test
  @Sql({"/fixture/items.sql"})
  void whenOrderIsCreatedDataIsCorrect() throws JSONException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> request = new HttpEntity<>(ORDER_JSON_REQ, headers);

    ResponseEntity<String> response =
        testRestTemplate.postForEntity("/api/v1/order", request, String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    JSONAssert.assertEquals(response.getBody(), ORDER_JSON_EXCEPTED_RES, true);
  }

  @Test
  void whenNoDataIsFoundErrorResultGot() throws JSONException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> request = new HttpEntity<>(ORDER_JSON_REQ, headers);

    ResponseEntity<String> response =
        testRestTemplate.postForEntity("/api/v1/order", request, String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    JSONAssert.assertEquals(ORDER_JSON_ERROR_EXCEPTED_RES, response.getBody(), false);
  }
}
