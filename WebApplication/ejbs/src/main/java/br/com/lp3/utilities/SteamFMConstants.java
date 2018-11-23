package br.com.lp3.utilities;

public class SteamFMConstants {

    private SteamFMConstants() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class");
    }

    public static final String LAST_FM_API_URL = "http://ws.audioscrobbler.com/2.0/";
    public static final String LAST_FM_API_KEY = "57ee3318536b23ee81d6b27e36997cde";

    public static final String STEAM_API_URL = "http://api.steampowered.com/";
    public static final String STEAM_API_KEY = "295B7F5CAD1BE1AB2249CE5878E7C4B2";

    public static final String RMI_SERVER_HOST = "localhost";
    public static final Integer RMI_SERVER_PORT = 1099;

}
