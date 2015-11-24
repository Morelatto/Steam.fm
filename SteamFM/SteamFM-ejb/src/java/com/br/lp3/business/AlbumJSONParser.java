package com.br.lp3.business;

import com.br.lp3.entities.Album;
import com.br.lp3.utilities.URLGetter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 *
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public class AlbumJSONParser {

    private static final String url = "http://ws.audioscrobbler.com/2.0/";
    private static final String apikey = "57ee3318536b23ee81d6b27e36997cde";

    public static List<Album> getAlbumRecomendacao(List<Album> albuns) {
        List<Album> listaAlbunsRecomendacao = new ArrayList<>();
        for (Album album : albuns) {
            JsonObject mainObj = URLGetter.getContent(url + "?method=album.getInfo&mbid=" + album.getIdAlbumLastfm().trim() + "&api_key=" + apikey + "&format=json");
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

        JsonObject mainObj = URLGetter.getContent(url + "?method=tag.getTopAlbums&tag=" + tag + "&api_key=" + apikey + "&format=json");

        JsonObject albums = mainObj.getJsonObject("albums");
        JsonArray album = albums.getJsonArray("album");
        for (int i = 0; i < 10; i++) {
            JsonObject albumObj = album.getJsonObject(ThreadLocalRandom.current().nextInt(0, album.size()));
            if (!"".equals(albumObj.getString("mbid", ""))) {
                Album albumRec = new Album();
                albumRec.setIdAlbumLastfm(albumObj.getString("mbid"));
                albumRec.setTituloAlbum(albumObj.getString("name"));
                albumRec.setImagem(albumObj.containsKey("image") ? (albumObj.getJsonArray("image").size() == 6 ? albumObj.getJsonArray("image").getJsonObject(4).getString("#text", "") : albumObj.getJsonArray("image").getJsonObject(3).getString("#text", "")) : "");
                albumRec.setDescricao(albumObj.containsValue("wiki") ? albumObj.getJsonObject("wiki").getString("summary", "") : "");
                listaAlbunsRecomendados.add(albumRec);
            } else {
                i--;
            }
        }
        return listaAlbunsRecomendados;
    }

}
