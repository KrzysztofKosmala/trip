package pl.kosmala.shop.common.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


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


    @OneToMany
    @JoinColumn(name = "productId")
    private List<Review> reviews;


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
