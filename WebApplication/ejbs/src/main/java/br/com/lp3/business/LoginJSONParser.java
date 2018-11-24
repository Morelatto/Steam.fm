package br.com.lp3.business;

import br.com.lp3.utilities.JsonUtils;
import br.com.lp3.utilities.UrlBuilder;

import java.io.IOException;

import org.json.JSONObject;

import static br.com.lp3.utilities.JsonUtils.STEAM_ID_KEY;
import static br.com.lp3.utilities.JsonUtils.STEAM_RESPONSE_KEY;

public class LoginJSONParser {

    private LoginJSONParser() {
    }

    public static String getSteamIdFromUsername(String steamUsername) {
        try {
            JSONObject mainObject = JsonUtils.readJsonFromUrl(UrlBuilder.steamUserValidation(steamUsername));
            JSONObject response = mainObject.getJSONObject(STEAM_RESPONSE_KEY);
            String steamId = response.optString(STEAM_ID_KEY);
            if (steamId != null) {
                return steamId;
            }
        } catch (IOException e) {
            // TODO log
            e.printStackTrace();
        }
        return null;
    }

}
