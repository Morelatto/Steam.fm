package br.com.lp3.utilities;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class URLGetter {

    public static JsonObject getContent(String uri) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(uri).openConnection();

            StringBuilder sb;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            }
            return Json.createReader(new StringReader(new Scanner(sb.toString()).useDelimiter("\\A").next())).readObject();
        } catch (IOException ex) {
            Logger.getLogger(URLGetter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
