package br.com.lp3.business;

import br.com.lp3.entities.GameGenre;
import br.com.lp3.entities.dto.Game;
import br.com.lp3.utilities.JsonUtils;
import br.com.lp3.utilities.UrlBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.java.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import static br.com.lp3.utilities.JsonUtils.STEAM_GAME_ID_KEY;
import static br.com.lp3.utilities.JsonUtils.STEAM_GAME_NAME_KEY;
import static br.com.lp3.utilities.JsonUtils.STEAM_RESPONSE_KEY;
import static br.com.lp3.utilities.JsonUtils.STEAM_GAMES_KEY;

public class GameJSONParser {

    private GameJSONParser() {
    }

    public static List<Game> getGamesBySteamId(String steamId) {
        List<Game> gamesList = new ArrayList<>();
        JSONArray gamesJsonArray = getGamesJsonArray(steamId);
        for (int i = 0; i < gamesJsonArray.length(); i++) {
            JSONObject game = gamesJsonArray.getJSONObject(i);
            long gameId = game.getLong(STEAM_GAME_ID_KEY);
            List<GameGenre> gameGenreList = GameGenreJSONParser.getGameGenresByGameId(gameId);
            if (!gameGenreList.isEmpty()) {
                gamesList.add(Game
                        .builder()
                        .id(gameId)
                        .name(game.getString(STEAM_GAME_NAME_KEY))
                        .gameGenreList(gameGenreList)
                        .build());
            }
        }
        return gamesList;
    }

    private static JSONArray getGamesJsonArray(String steamId) {
        try {
            JSONObject mainObject = JsonUtils.readJsonFromUrl(UrlBuilder.steamOwnedGames(steamId));
            JSONObject response = mainObject.getJSONObject(STEAM_RESPONSE_KEY);
            if (response.has(STEAM_GAMES_KEY)) {
                return response.getJSONArray(STEAM_GAMES_KEY);
            }
        } catch (IOException e) {
            // TODO log
            e.printStackTrace();
        }
        return new JSONArray();
    }

}
