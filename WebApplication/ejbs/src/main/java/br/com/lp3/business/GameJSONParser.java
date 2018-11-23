package br.com.lp3.business;

import br.com.lp3.entities.dto.Game;
import br.com.lp3.utilities.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import static br.com.lp3.utilities.SteamFMConstants.STEAM_API_KEY;
import static br.com.lp3.utilities.SteamFMConstants.STEAM_API_URL;

public class GameJSONParser {

    private static final String STEAM_API_GET_OWNED_GAMES = "IPlayerService/GetOwnedGames/v0001/";
    private static final String INCLUDE_APP_INFO_PARAM = "include_appinfo=1";
    private static final String INCLUDE_PLAYED_FREE_GAMES_PARAM = "include_played_free_games=1";
    private static final String KEY_PARAM = "key";
    private static final String STEAM_ID_PARAM = "steamid";

    private GameJSONParser() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class");
    }

    public static List<Game> getGamesBySteamId(String steamId) {
        List<Game> gamesList = new ArrayList<>();
        JSONArray gamesJsonArray = getGamesJsonArray(steamId);
        for (int i = 0; i < gamesJsonArray.length(); i++) {
            JSONObject game = gamesJsonArray.getJSONObject(i);
            long gameId = game.getLong("appid");
            gamesList.add(new Game(gameId, game.getString("name"), null,
            // GameGenreJSONParser.getListaGenerosByJogo(gameId),
                null));
        }
        return gamesList;
    }

    private static JSONArray getGamesJsonArray(String steamId) {
        try {
            JSONObject mainObject = JsonReader.readJsonFromUrl(STEAM_API_URL + STEAM_API_GET_OWNED_GAMES + "?"
                    + INCLUDE_APP_INFO_PARAM + "&" + INCLUDE_PLAYED_FREE_GAMES_PARAM + "&" + KEY_PARAM + "="
                    + STEAM_API_KEY + "&" + STEAM_ID_PARAM + "=" + steamId);
            JSONObject response = mainObject.getJSONObject("response");
            return response.getJSONArray("games");
        } catch (IOException e) {
            // TODO log
            return new JSONArray();
        }
    }

}
