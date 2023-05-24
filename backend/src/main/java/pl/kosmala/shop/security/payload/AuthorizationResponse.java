package pl.kosmala.shop.security.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationResponse
{
    @JsonProperty
    private String message;
}
