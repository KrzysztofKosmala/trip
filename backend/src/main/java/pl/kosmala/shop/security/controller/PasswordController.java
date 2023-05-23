package pl.kosmala.shop.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kosmala.shop.admin.dto.AdminTripDto;
import pl.kosmala.shop.security.payload.ChangePassword;
import pl.kosmala.shop.security.payload.ResetPasswordRequest;
import pl.kosmala.shop.security.service.ResetPasswordService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/password")
@RequiredArgsConstructor
public class PasswordController
{

    private final ResetPasswordService resetPasswordService;

    @PostMapping("/reset")
    public void resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest)
    {
        resetPasswordService.sendLostPasswordLink(resetPasswordRequest);
    }

    @PostMapping("/change")
    public void changePassword(@RequestBody ChangePassword changePassword)
    {
        resetPasswordService.changePassword(changePassword);
    }


}
