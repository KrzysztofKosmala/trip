package pl.kosmala.shop.common.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
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
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinTable(
            name = "product_image",
            joinColumns = @JoinColumn(name = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images;

    public Product(String name, String desc, ProductCurrency currency, String slug, String fullDesc, Set<Image> images)
    {
        this.name = name;
        this.desc = desc;
        this.currency = currency;
        this.slug = slug;
        this.fullDesc = fullDesc;
        this.images = images;
    }
    public Product(Long id, String name, String desc, ProductCurrency currency, String slug, String fullDesc,  Set<Image> images)
    {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.currency = currency;
        this.slug = slug;
        this.fullDesc = fullDesc;
        this.images = images;
    }
    public void addImage(Image image) {
        if (images == null) {
            images = new HashSet<>();
        }
        images.add(image);
        if (image.getProducts() == null) {
            image.setProducts(new HashSet<>());
        }
        image.getProducts().add(this);
    }

    public void removeImage(Image image) {
        if (images != null) {
            images.remove(image);
            if (image.getProducts() != null) {
                image.getProducts().remove(this);
            }
        }
    }
}
