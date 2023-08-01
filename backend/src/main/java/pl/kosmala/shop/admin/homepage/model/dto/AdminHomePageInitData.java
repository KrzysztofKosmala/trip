package pl.kosmala.shop.admin.homepage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class AdminHomePageInitData
{
    private Map<String, String> productStrategies;
}
