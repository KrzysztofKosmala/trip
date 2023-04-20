package pl.kosmala.shop.common.log;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LogService
{
    AdminOrderLogRepository adminOrderLogRepository;

    public void saveAdminOrderLog(AdminOrderLog adminOrderLog)
    {
        adminOrderLogRepository.save(adminOrderLog);
    }
}
