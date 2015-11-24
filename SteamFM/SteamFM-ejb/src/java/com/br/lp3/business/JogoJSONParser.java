package com.br.lp3.business;

import com.br.lp3.entities.Jogo;
import com.br.lp3.utilities.FileTest;
import com.br.lp3.utilities.URLGetter;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

/**
 *
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public class JogoJSONParser {

    private static final String apikey = "295B7F5CAD1BE1AB2249CE5878E7C4B2";
    private static final String url = "http://api.steampowered.com/";

    public static List<Jogo> getJogosByUser(String username) {
        List<Jogo> listaJogos = new ArrayList<>();
//        JsonObject mainObj = URLGetter.getContent(url + "IPlayerService/GetOwnedGames/v0001/?include_appinfo=1&include_played_free_games=1&key=" + apikey + "&steamid=" + username);
        JsonObject mainObj = FileTest.getContent("C:\\Temp\\SteamFM\\SteamFM-master\\SteamFM-master\\SteamFM\\jogos.html");
        
        JsonObject response = mainObj.getJsonObject("response");
        JsonArray games = response.getJsonArray("games");

        for (JsonValue game : games) {
            JsonObject gameAux = (JsonObject) game;
            Jogo jogo = new Jogo(gameAux.getInt("appid"), gameAux.getString("name"), GeneroJogoJSONParser.getListaGenerosByJogo(gameAux.getInt("appid")));
            listaJogos.add(jogo);
        }

        return listaJogos;
    }

}
