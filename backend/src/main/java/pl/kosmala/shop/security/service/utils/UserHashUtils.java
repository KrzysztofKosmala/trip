package pl.kosmala.shop.security.service.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import pl.kosmala.shop.security.entity.User;

import java.time.LocalDateTime;

public class UserHashUtils
{



    public static String generateHashBasedOnUser(User user)
    {
        String toHash = user.getId() + user.getUsername() + user.getPassword() +
                LocalDateTime.now();
        return DigestUtils.sha256Hex(toHash);
    }




}
