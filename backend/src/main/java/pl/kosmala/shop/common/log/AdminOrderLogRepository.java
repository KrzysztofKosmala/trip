package pl.kosmala.shop.common.log;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kosmala.shop.common.log.AdminOrderLog;

public interface AdminOrderLogRepository extends JpaRepository<AdminOrderLog, Long>
{

}
