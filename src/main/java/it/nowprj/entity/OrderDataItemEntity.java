/* Italy Company - Fast Team(C) 2024 */
package it.nowprj.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "OrderDataItem")
public class OrderDataItemEntity {
  @Id @GeneratedValue private UUID id;

  private Integer productId;
  private Integer quantity;
  private Double price;
  private Double vat;

  public OrderDataItemEntity(Integer productId, Integer quantity, Double price, Double vat) {
    this.productId = productId;
    this.quantity = quantity;
    this.price = price;
    this.vat = vat;
  }

  public OrderDataItemEntity() {}

  public Integer getProductId() {
    return productId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public Double getPrice() {
    return price;
  }

  public Double getVat() {
    return vat;
  }
}
