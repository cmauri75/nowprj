/* Decathlon Italy - Tacos Team(C) 2024 */
package it.nowprj;

import it.nowprj.entity.ItemEntity;
import it.nowprj.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevInitializer {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(DevInitializer.class);


    private final ItemRepository itemRepository;

    public DevInitializer(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @PostConstruct
    public void createDummyData() {
        itemRepository.save(new ItemEntity(1, 2d,0.2d));
        itemRepository.save(new ItemEntity(2, 7.5d,0.75d));
        itemRepository.save(new ItemEntity(3, 3d,0.3d));
        log.debug("Created 3 items");
    }
}
