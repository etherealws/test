package com.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "weather-client",url = "http://aliv18.data.moji.com")
public interface WeatherFeignClient {

    @PostMapping("/whapi/json/alicity/weather/condition")
    String getWeather(@RequestHeader("Authorization") String autho,
                    @RequestParam("token") String token,
                    @RequestParam("cityId") String cityId);
}
