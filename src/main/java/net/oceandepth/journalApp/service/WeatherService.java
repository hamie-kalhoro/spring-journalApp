package net.oceandepth.journalApp.service;

import net.oceandepth.journalApp.api.response.WeatherResponse;
import net.oceandepth.journalApp.cache.AppCache;
import net.oceandepth.journalApp.constants.PlaceHolders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city) {
        String finalApi = appCache.appCache.get(AppCache.keys.WEATHER_API.toString())
                            .replace(PlaceHolders.CITY, city).replace(PlaceHolders.API_KEY, apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        return response.getBody();
    }

}
