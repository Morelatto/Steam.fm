package br.com.lp3.business;

import br.com.lp3.entities.GameGenre;
import br.com.lp3.utilities.UrlBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

class GameGenreJSONParser {

    private static final String GAME_TAGS_SELECTOR = ".app_tag";

    private GameGenreJSONParser() {
    }

    static List<GameGenre> getGameGenresByGameId(Long gameId) {
        List<GameGenre> gameGenres = new ArrayList<>();

        try {
            Document steamPage = Jsoup.connect(UrlBuilder.steamGamePage(gameId)).get();
            Elements gameGenreElements = steamPage.select(GAME_TAGS_SELECTOR);
            gameGenreElements.forEach(gg -> GameGenre.builder().name(gg.text()).build());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gameGenres;
    }

}
