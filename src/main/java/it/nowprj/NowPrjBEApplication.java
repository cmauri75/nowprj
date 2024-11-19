/* Italy Company - Fast Team(C) 2023 */
package it.nowprj;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
@EnableTransactionManagement
public class NowPrjBEApplication {
  public static void main(String[] args) {
    SpringApplication.run(NowPrjBEApplication.class, args);
  }

  static class DecimalJsonSerializer extends JsonSerializer<Double> {
    @Override
    public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider)
        throws IOException {
      jgen.writeNumber(String.format("%.2f", value));
    }
  }

  @Bean
  public ObjectMapper registerObjectMapper(){
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule("DecimalJsonSerializer");
    module.addSerializer(Double.class, new DecimalJsonSerializer());
    module.addSerializer(double.class, new DecimalJsonSerializer());
    mapper.registerModule(module);
    return mapper;
  }
}
