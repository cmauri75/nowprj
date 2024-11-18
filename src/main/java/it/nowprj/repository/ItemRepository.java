/* Decathlon Italy - Tacos Team(C) 2023 */
package it.nowprj.repository;

import it.nowprj.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
}
