package pl.kosmala.shop.product.repository;

import org.springframework.data.repository.CrudRepository;
import pl.kosmala.shop.common.model.Product;
@Deprecated
public interface ProductRepository extends CrudRepository<Product, Long>
{

}
