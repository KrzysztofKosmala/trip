package pl.kosmala.shop.common.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @JsonBackReference
    @ManyToMany(mappedBy = "images", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Product> trip;

}
