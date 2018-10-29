package br.com.lp3.business;

import br.com.lp3.utilities.URLGetter;

import javax.json.JsonObject;

import static br.com.lp3.utilities.SteamFMConstants.STEAM_API_KEY;
import static br.com.lp3.utilities.SteamFMConstants.STEAM_API_URL;

public class LoginJSONParser {

    public static String getSteamID(String username) {
        JsonObject mainObj = URLGetter.getContent(STEAM_API_URL + "?key=" + STEAM_API_KEY + "&vanityurl=" + username);
        JsonObject response = mainObj.getJsonObject("response");

        return response.getInt("success") != 1
                || (response.containsKey("message") && "No match".equals(response.getString("message"))) ? null
                : response.getString("steamid");
    }

}
