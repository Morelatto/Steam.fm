package br.com.lp3.business;

import br.com.lp3.entities.Album;
import br.com.lp3.utilities.URLGetter;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static br.com.lp3.utilities.SteamFMConstants.LAST_FM_API_KEY;
import static br.com.lp3.utilities.SteamFMConstants.LAST_FM_API_URL;

public class AlbumJSONParser {

    public static List<Album> getAlbumRecomendacao(List<Album> albuns) {
        List<Album> listaAlbunsRecomendacao = new ArrayList<>();
        for (Album album : albuns) {
            JsonObject mainObj = URLGetter.getContent(LAST_FM_API_URL + "?method=album.getInfo&mbid="
                    + album.getLastFmId().trim() + "&api_key=" + LAST_FM_API_KEY + "&format=json");
            JsonObject albumObj = mainObj.getJsonObject("album");
            JsonObject tags = albumObj.getJsonObject("tags");
            JsonArray tag = tags.getJsonArray("tag");
            JsonObject tagObj = tag.getJsonObject(ThreadLocalRandom.current().nextInt(0, tag.size()));
            listaAlbunsRecomendacao.addAll(getRandomAlbunsByTag(tagObj.getString("name")));
        }
        return listaAlbunsRecomendacao;
    }

    private static List<Album> getRandomAlbunsByTag(String tag) {
        List<Album> listaAlbunsRecomendados = new ArrayList<>();

        JsonObject mainObj = URLGetter.getContent(LAST_FM_API_URL + "?method=tag.getTopAlbums&tag=" + tag + "&api_key="
                + LAST_FM_API_KEY + "&format=json");

        JsonObject albums = mainObj.getJsonObject("albums");
        JsonArray album = albums.getJsonArray("album");
        for (int i = 0; i < 5; i++) {
            JsonObject albumObj = album.getJsonObject(ThreadLocalRandom.current().nextInt(0, album.size()));
            if (!"".equals(albumObj.getString("mbid", ""))) {
                Album albumRec = new Album();
                albumRec.setLastFmId(albumObj.getString("mbid"));
                albumRec.setUrl(albumObj.getString("url"));
                albumRec.setName(albumObj.getString("name"));
                albumRec
                        .setImage(albumObj.containsKey("image") ? (albumObj.getJsonArray("image").size() == 6 ? albumObj
                                .getJsonArray("image")
                                .getJsonObject(4)
                                .getString("#text", "")
                                : albumObj.getJsonArray("image").getJsonObject(3).getString("#text", ""))
                                : "");
                albumRec.setDescription(albumObj.containsValue("wiki") ? albumObj
                        .getJsonObject("wiki")
                        .getString("summary", "") : "");
                listaAlbunsRecomendados.add(albumRec);
            } else {
                i--;
            }
        }
        return listaAlbunsRecomendados;
    }

}
