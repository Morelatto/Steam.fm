package br.com.lp3.utilities;

import static br.com.lp3.utilities.SteamFMConstants.LAST_FM_API_KEY;
import static br.com.lp3.utilities.SteamFMConstants.LAST_FM_API_URL;
import static br.com.lp3.utilities.SteamFMConstants.STEAM_API_KEY;
import static br.com.lp3.utilities.SteamFMConstants.STEAM_API_URL;

public class UrlBuilder {

    private static final String STEAM_GAME_PAGE_URL = "http://store.steampowered.com/app/";
    private static final String STEAM_API_USER_VALIDATION_PATH = "ISteamUser/ResolveVanityURL/v0001/";
    private static final String STEAM_API_GET_OWNED_GAMES_PATH = "IPlayerService/GetOwnedGames/v0001/";
    private static final String STEAM_GAME_PAGE_LANGUAGE_PARAM = "l=brazilian";
    private static final String STEAM_INCLUDE_APP_INFO_PARAM = "include_appinfo=1";
    private static final String STEAM_INCLUDE_PLAYED_FREE_GAMES_PARAM = "include_played_free_games=1";
    private static final String STEAM_API_KEY_PARAM = "key=" + STEAM_API_KEY;
    private static final String STEAM_ID_PARAM = "steamid=";
    private static final String STEAM_VANITY_URL_PARAM = "vanityurl=";
    private static final String LAST_FM_SIMILAR_ARTISTS = "method=artist.getSimilar";
    private static final String LAST_FM_SIMILAR_ALBUMS = "method=album.getInfo";
    private static final String LAST_FM_SIMILAR_SONGS = "method=track.getSimilar";
    private static final String LAST_FM_MBID_PARAM = "mbid=";
    private static final String LAST_FM_API_KEY_PARAM = "api_key=" + LAST_FM_API_KEY;
    private static final String LAST_FM_JSON_FORMAT = "format=json";

    private UrlBuilder() {
    }

    public static String steamGamePage(Long gameId) {
        return STEAM_GAME_PAGE_URL + gameId + "?" + STEAM_GAME_PAGE_LANGUAGE_PARAM;
    }

    public static String steamUserValidation(String steamUsername) {
        return STEAM_API_URL + STEAM_API_USER_VALIDATION_PATH + "?" + STEAM_API_KEY_PARAM + "&"
                + STEAM_VANITY_URL_PARAM + steamUsername;
    }

    public static String steamOwnedGames(String steamId) {
        return STEAM_API_URL + STEAM_API_GET_OWNED_GAMES_PATH + "?" + STEAM_INCLUDE_APP_INFO_PARAM + "&"
                + STEAM_INCLUDE_PLAYED_FREE_GAMES_PARAM + "&" + STEAM_API_KEY_PARAM + "&" + STEAM_ID_PARAM + steamId;
    }

    public static String lastFmSimilarArtists(String lastFmId) {
        return LAST_FM_API_URL + "?" + LAST_FM_SIMILAR_ARTISTS + "&" + LAST_FM_MBID_PARAM + lastFmId + "&"
                + LAST_FM_API_KEY_PARAM + "&" + LAST_FM_JSON_FORMAT;
    }

    public static String lastFmSimilarAlbums(String lastFmId) {
        return LAST_FM_API_URL + "?" + LAST_FM_SIMILAR_ALBUMS + "&" + LAST_FM_MBID_PARAM + lastFmId + "&"
                + LAST_FM_API_KEY_PARAM + "&" + LAST_FM_JSON_FORMAT;
    }

    public static String lastFmSimilarSongs(String lastFmId) {
        return LAST_FM_API_URL + "?" + LAST_FM_SIMILAR_SONGS + "&" + LAST_FM_MBID_PARAM + lastFmId + "&"
                + LAST_FM_API_KEY_PARAM + "&" + LAST_FM_JSON_FORMAT;
    }

}
