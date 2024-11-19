/* Italy Company - Fast Team(C) 2023 */
package it.nowprj.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
class ItemRepositoryTest {
  @Autowired ItemRepository itemRepository;

  @Test
  @Sql({"/fixture/items.sql"})
  void when_search_all_data_is_found() {
    Sort productIdSorted = Sort.by("productId");
    var savedItem = itemRepository.findAll(productIdSorted);
    assertThat(savedItem).isNotEmpty().hasSize(3);
    assertThat(savedItem.get(0).getProductId()).isEqualTo(1);
    assertThat(savedItem.get(0).getPrice()).isEqualTo(10.0);
    assertThat(savedItem.get(0).getVatPercent()).isEqualTo(0.1);
  }

  @Test
  @Sql({"/fixture/items.sql"})
  void when_searchBy_filter_correct_data_found() {
    var ids = List.of(1, 3);
    var savedItem = itemRepository.findAllById(ids);
    assertThat(savedItem)
        .isNotEmpty()
        .hasSize(2)
        .contains(itemRepository.findById(ids.get(0)).get())
        .contains(itemRepository.findById(ids.get(1)).get());
  }
}
