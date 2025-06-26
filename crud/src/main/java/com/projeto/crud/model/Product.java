package com.projeto.crud.model;
import jakarta.persistence.*;


@Entity//expicificnto q esse classe sera uma intidade é ela tera uma tabela no banco de dados e a tabela tera o nome product
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// aqui ele estar espacicando q a primeira linha de codigo sera um id é ele sera gerado altomaticamente
    private Integer id;
    private String name;
    private Long price;

    public Product(){

    }

    public Product(Integer id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
