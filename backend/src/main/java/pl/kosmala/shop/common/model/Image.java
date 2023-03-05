package pl.kosmala.shop.common.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

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

    @Column(name = "destination")
    private TripDestination destination;

    @Column(name = "desctiption")
    private String desc;

    @Lob
    @Column(name = "data")
    private String data;

}
