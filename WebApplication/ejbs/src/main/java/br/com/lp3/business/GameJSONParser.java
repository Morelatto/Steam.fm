package br.com.lp3.business;

import br.com.lp3.entities.Game;
import br.com.lp3.utilities.URLGetter;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.ArrayList;
import java.util.List;

import static br.com.lp3.utilities.SteamFMConstants.STEAM_API_KEY;
import static br.com.lp3.utilities.SteamFMConstants.STEAM_API_URL;

public class GameJSONParser {

    public static List<Game> getJogosByUser(String username) {
        List<Game> listaGames = new ArrayList<>();
        JsonObject mainObj = URLGetter.getContent(STEAM_API_URL
                + "IPlayerService/GetOwnedGames/v0001/?include_appinfo=1&include_played_free_games=1&key=" + STEAM_API_KEY
                + "&steamid=" + username);

        JsonObject response = mainObj.getJsonObject("response");
        JsonArray games = response.getJsonArray("games");

        for (JsonValue game : games) {
            JsonObject gameAux = (JsonObject) game;
            Game jogo = new Game((long) gameAux.getInt("appid"),
                    gameAux.getString("name"),
                    GameGenreJSONParser.getListaGenerosByJogo((long) gameAux.getInt("appid")),
                    null);
            listaGames.add(jogo);
        }

        return listaGames;
    }

}
