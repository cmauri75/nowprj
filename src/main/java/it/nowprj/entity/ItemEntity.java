/* Decathlon Italy - Tacos Team(C) 2024 */
package it.nowprj.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Item")
public class ItemEntity {
    @Id
    private Integer productId;
    private Double price;
    private Double vat;

    public ItemEntity(Integer productId, Double price, Double vat) {
        this.productId = productId;
        this.price = price;
        this.vat = vat;
    }

    public ItemEntity() {
    }

    public Integer getProductId() {
        return productId;
    }

    public Double getPrice() {
        return price;
    }

    public Double getVat() {
        return vat;
    }
}
