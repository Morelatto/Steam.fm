package br.com.lp3.business;

import br.com.lp3.entities.Game;
import br.com.lp3.utilities.URLGetter;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public class JogoJSONParser {

    private static final String apikey = "295B7F5CAD1BE1AB2249CE5878E7C4B2";
    private static final String url = "http://api.steampowered.com/";

    public static List<Game> getJogosByUser(String username) {
        List<Game> listaGames = new ArrayList<>();
        JsonObject mainObj = URLGetter.getContent(url
                + "IPlayerService/GetOwnedGames/v0001/?include_appinfo=1&include_played_free_games=1&key=" + apikey
                + "&steamid=" + username);
        // JsonObject mainObj = FileTest.getContent("C:\\Temp\\SteamFM\\SteamFM\\jogos.html");

        JsonObject response = mainObj.getJsonObject("response");
        JsonArray games = response.getJsonArray("games");

        for (JsonValue game : games) {
            JsonObject gameAux = (JsonObject) game;
            Game jogo = new Game(gameAux.getInt("appid"),
                gameAux.getString("name"),
                GeneroJogoJSONParser.getListaGenerosByJogo(gameAux.getInt("appid")),
                null);
            listaGames.add(jogo);
        }

        return listaGames;
    }

}
