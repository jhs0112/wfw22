package kr.ac.hansung.cse.hellospringdatajpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//@NoArgsConstructor
@ToString
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @NotBlank(message = "브랜드는 필수입니다.")
    private String brand;

    @NotBlank(message = "제조국은 필수입니다.")
    private String madeIn;

    @Min(value = 0, message = "가격은 0원 초과여야합니다.")
    private double price;

    public Product() {}

    public Product(String name, String brand, String madeIn, double price) {
        this.name = name;
        this.brand = brand;
        this.madeIn = madeIn;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}