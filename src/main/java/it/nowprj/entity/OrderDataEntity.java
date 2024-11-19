/* Italy Company - Fast Team(C) 2024 */
package it.nowprj.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "OrderData")
public class OrderDataEntity {
  @Id @GeneratedValue private Integer orderId;
  private Double orderPrice;
  private Double orderVat;

  @OneToMany(cascade = CascadeType.ALL)
  private List<OrderDataItemEntity> items;

  public Integer getOrderId() {
    return orderId;
  }

  public Double getOrderPrice() {
    return orderPrice;
  }

  public Double getOrderVat() {
    return orderVat;
  }

  public List<OrderDataItemEntity> getItems() {
    return items;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public void setOrderPrice(Double orderPrice) {
    this.orderPrice = orderPrice;
  }

  public void setOrderVat(Double orderVat) {
    this.orderVat = orderVat;
  }

  public void setItems(List<OrderDataItemEntity> items) {
    this.items = items;
  }
}
