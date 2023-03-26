package pl.kosmala.shop.common.image.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import pl.kosmala.shop.common.model.Product;
import pl.kosmala.shop.common.model.TripDestination;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Image implements Comparable<Image>
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id) &&
                Objects.equals(name, image.name) &&
                Objects.equals(type, image.type) &&
                Objects.equals(location, image.location) &&
                Objects.equals(desc, image.desc) &&
                Objects.equals(thumbImage, image.thumbImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, location, desc, thumbImage);
    }

    @Override
    public int compareTo(Image o)
    {
        return this.id.compareTo(o.getId());
    }
}
