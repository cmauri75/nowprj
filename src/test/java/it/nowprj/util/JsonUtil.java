/* Italy Company - Fast Team(C) 2024 */
package it.nowprj.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class JsonUtil {

  public static String getJsonPayload(String resource) {
    ClassLoader classLoader = JsonUtil.class.getClassLoader();
    File file =
        new File(classLoader.getResource("static/payloads/" + resource + ".json").getFile());
    try {
      return new String(Files.readAllBytes(file.toPath()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static String toJson(Object object) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper.writeValueAsString(object);
  }
}
