package pl.kosmala.shop.fakeData;

import com.github.javafaker.Faker;
import pl.kosmala.shop.common.user.entity.User;
import pl.kosmala.shop.common.user.entity.types.Role;

import java.util.ArrayList;
import java.util.List;

public class UserGenerator
{
    Faker faker = new Faker();




    public User generateUser()
    {
        User user = new User();
        user.setEmail(faker.internet().emailAddress());
        user.setRole(Role.ROLE_CUSTOMER);
        user.setPassword(faker.internet().password());
        user.setLastname(faker.address().lastName());
        user.setFirstname(faker.address().firstName());
        return user;
    }

    public List<User> generateUsers(int howMany)
    {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < howMany; i++)
        {
            users.add(generateUser());
        }
        return users;
    }
}
