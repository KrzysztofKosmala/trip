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
                    name = "slug",
                    //           nullable = false
                    unique = true
            )
    private String slug;

    @Column
            (
                    name = "full_dessc"
            )
    private String fullDesc;

    public Product(String name, String desc, ProductCurrency currency, String slug, String fullDesc)
    {
        this.name = name;
        this.desc = desc;
        this.currency = currency;
        this.slug = slug;
        this.fullDesc = fullDesc;
    }
    public Product(Long id, String name, String desc, ProductCurrency currency, String slug, String fullDesc)
    {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.currency = currency;
        this.slug = slug;
        this.fullDesc = fullDesc;
    }

}
