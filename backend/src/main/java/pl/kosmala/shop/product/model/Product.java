package pl.kosmala.shop.product.model;

import jakarta.persistence.*;
import lombok.*;


@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
            (
                    name = "name",
                    nullable = false,
                    columnDefinition = "TEXT"
            )
    private String name;
    @Column
            (
                    name = "description",
                    nullable = false,
                    columnDefinition = "TEXT"

            )
    private String desc;

    @Column
            (
                    name = "currency",
                    nullable = false,
                    columnDefinition = "TEXT"

            )
    private String currency;
    @Column
            (
                    name = "category"
                    //           nullable = false
            )
    private String category;

    public Product(String name, String desc, String currency, String category)
    {
        this.name = name;
        this.desc = desc;
        this.currency = currency;
        this.category = category;
    }
    public Product(Long id, String name, String desc, String currency, String category)
    {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.currency = currency;
        this.category = category;
    }

}
