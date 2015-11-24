package com.br.lp3.business;

import com.br.lp3.utilities.FileTest;
import com.br.lp3.utilities.URLGetter;
import javax.json.JsonObject;

/**
 *
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public class LoginJSONParser {

    private static final String apikey = "295B7F5CAD1BE1AB2249CE5878E7C4B2";
    private static final String url = "http://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/";

    public static String getSteamID(String username) {
        JsonObject mainObj = URLGetter.getContent(url + "?key=" + apikey + "&vanityurl=" + username);
//        JsonObject mainObj = FileTest.getContent("C:\\Temp\\SteamFM\\SteamFM\\user.html");
        JsonObject response = mainObj.getJsonObject("response");

        return response.getInt("success") != 1 || (response.containsKey("message") && "No match".equals(response.getString("message"))) ? null : response.getString("steamid");
    }

}
