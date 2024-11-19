/* Italy Company - Fast Team(C) 2023 */
package it.nowprj.repository;

import it.nowprj.entity.OrderDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDataRepository extends JpaRepository<OrderDataEntity, Integer> {}
