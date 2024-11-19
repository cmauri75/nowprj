/* Italy Company - Fast Team(C) 2024 */
package it.nowprj.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.nowprj.util.JsonUtil;
import java.util.List;
import org.instancio.Instancio;
import org.springframework.data.repository.CrudRepository;

public class EnhancedInstancioBuilder<V> {
  protected V dto;

  public void persist(CrudRepository<V, ?> repository) {
    dto = repository.save(dto);
  }

  public void delete(CrudRepository<V, ?> repository) {
    repository.delete(dto);
  }

  public String json() throws JsonProcessingException {
    return JsonUtil.toJson(dto);
  }

  public String jsonInList() throws JsonProcessingException {
    return JsonUtil.toJson(List.of(dto));
  }

  public V get() {
    return dto;
  }

  public EnhancedInstancioBuilder(Class<V> clazz) {
    dto = Instancio.of(clazz).create();
  }
}
