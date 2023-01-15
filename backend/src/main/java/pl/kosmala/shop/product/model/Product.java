package pl.kosmala.shop.product.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;


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
    @Enumerated(EnumType.STRING)
    private ProductCurrency currency;
    @Column
            (
                    name = "category"
                    //           nullable = false
            )
    private String category;

    @Column
            (
                    name = "slug",
                    //           nullable = false
                    unique = true
            )
    private String slug;

    public Product(String name, String desc, ProductCurrency currency, String category, String slug)
    {
        this.name = name;
        this.desc = desc;
        this.currency = currency;
        this.category = category;
        this.slug = slug;
    }
    public Product(Long id, String name, String desc, ProductCurrency currency, String category, String slug)
    {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.currency = currency;
        this.category = category;
        this.slug = slug;
    }

}
