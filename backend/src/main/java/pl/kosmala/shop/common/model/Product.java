package pl.kosmala.shop.common.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import pl.kosmala.shop.common.image.model.Image;
import pl.kosmala.shop.order.model.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
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
                        columnDefinition = "TEXT",
                        unique = true
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
        @Column
                (
                        name = "base_price"
                        //           nullable = false
                )
        private BigDecimal basePrice;

        @Column
                (
                        name = "sale_price"
                        //           nullable = false
                )
        private BigDecimal salePrice;

        @OneToMany
        @JoinColumn(name = "productId")
        private List<Review> reviews;
        @EqualsAndHashCode.Exclude
        @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
        @JoinTable(
                name = "product_image",
                joinColumns = @JoinColumn(name = "trip_id"),
                inverseJoinColumns = @JoinColumn(name = "image_id"))
        private Set<Image> images;

        @OneToMany(mappedBy = "product")
        @JsonBackReference
        private List<Order> orders;
        @Column
                (
                        name = "show_on_homepage"
                )
        private Boolean showOnHomePage;

        public Product(String name, String desc, ProductCurrency currency, String slug, String fullDesc, Set<Image> images, BigDecimal basePrice, BigDecimal salePrice)
        {
            this.name = name;
            this.desc = desc;
            this.currency = currency;
            this.slug = slug;
            this.fullDesc = fullDesc;
            this.images = images;
            this.basePrice = basePrice;
            this.salePrice = salePrice;
        }
        public Product(Long id, String name, String desc, ProductCurrency currency, String slug, String fullDesc,  Set<Image> images, BigDecimal basePrice, BigDecimal salePrice)
        {
            this.id = id;
            this.name = name;
            this.desc = desc;
            this.currency = currency;
            this.slug = slug;
            this.fullDesc = fullDesc;
            this.images = images;
            this.basePrice = basePrice;
            this.salePrice = salePrice;
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
        public void addOrder(Order order) {
            if (orders == null) {
                orders = new ArrayList<>();
            }
            if (!orders.contains(order)) {
                orders.add(order);

            }
        }
        public void removeOrder(Order order) {
            orders.removeIf(o -> o.getId().equals(order.getId()));
            order.setProduct(null);
        }

        public void detouchAllOrders()
        {
            if(orders != null)
            {
                orders.forEach(o -> o.setProduct(null));
                orders.clear();
            }
        }
    }
