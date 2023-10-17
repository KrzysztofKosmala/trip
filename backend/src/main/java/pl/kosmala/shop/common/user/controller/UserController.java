package pl.kosmala.shop.common.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.kosmala.shop.common.user.dto.UserDto;
import pl.kosmala.shop.common.user.entity.User;
import pl.kosmala.shop.common.user.service.UserService;
import pl.kosmala.shop.order.model.dto.OrderListDto;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController
{

    private final UserService userService;

    @GetMapping()
    public UserDto getUserDetails(@AuthenticationPrincipal User user)
    {
        if(user == null){
            throw new IllegalArgumentException("Brak użytkownika!");
        }
        return userService.getDetails(user);
    }

    @PostMapping("/update")
    public UserDto updateUserDetails(@AuthenticationPrincipal User user, @RequestBody UserDto details)
    {
        if(user == null){
            throw new IllegalArgumentException("Brak użytkownika!");
        }
        return userService.updateDetails(user, details);
    }

    /*todo: przeniesc do order*/
    @GetMapping("/checkIfCanOrder")
    public boolean checkIfCanOrder(@AuthenticationPrincipal User user)
    {
        if(user == null){
            throw new IllegalArgumentException("Brak użytkownika!");
        }
        return userService.checkIfUserCanOrder(user);
    }

}
