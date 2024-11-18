/* Italy Company - Fast Team(C) 2024 */
package it.nowprj.dto.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public interface Jsonizable {
  ObjectMapper mapper =
      new ObjectMapper()
          .registerModule(new Jdk8Module())
          .registerModule(new JavaTimeModule())
          .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

  default String toJSON() {
    try {
      return mapper.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      return "EXC: " + this;
    }
  }
}
