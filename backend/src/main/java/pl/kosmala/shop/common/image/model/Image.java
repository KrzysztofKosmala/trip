package pl.kosmala.shop.common.image.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import pl.kosmala.shop.common.model.Product;
import pl.kosmala.shop.common.model.TripDestination;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Image
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "location")
    private TripDestination location;

    @Column(name = "desctiption")
    private String desc;

    @Column(name = "thumbImage")
    private String thumbImage;
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "images", fetch = FetchType.LAZY)
    private Set<Product> products;

    public void addProduct(Product product) {
        if (products == null) {
            products = new HashSet<>();
        }
        products.add(product);
        if (product.getImages() == null) {
            product.setImages(new HashSet<>());
        }

        product.getImages().add(this);
    }
    public void removeProduct(Product product) {
        if (products != null) {
            products.remove(product);
        }
    }
}
