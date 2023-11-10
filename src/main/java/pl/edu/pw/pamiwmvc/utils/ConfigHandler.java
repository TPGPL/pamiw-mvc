package pl.edu.pw.pamiwmvc.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.edu.pw.pamiwmvc.dtos.Endpoints;

public class ConfigHandler {
    public static Endpoints getConfig() {
        try {
            var mapper = new ObjectMapper();
            var url = ConfigHandler.class.getResource("AppConfig.json");

            return mapper.readValue(url, Endpoints.class);
        } catch (Exception e) {
            return null;
        }
    }
}
