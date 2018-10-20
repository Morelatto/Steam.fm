package br.com.lp3.utilities;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public class URLGetter {

    public static JsonObject getContent(String uri) {
        try {

//            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.10", 3128));
//            HttpURLConnection httpcon = (HttpURLConnection) new URL(uri).openConnection(proxy);
            HttpURLConnection httpcon = (HttpURLConnection) new URL(uri).openConnection();

            StringBuilder sb;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(httpcon.getInputStream(), StandardCharsets.UTF_8))) {
                sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            }

            return Json.createReader(new StringReader(new Scanner(sb.toString()).useDelimiter("\\A").next())).readObject();
        } catch (MalformedURLException ex) {
            Logger.getLogger(URLGetter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(URLGetter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
