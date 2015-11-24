package com.br.lp3.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public class FileTest {

    public static BufferedReader getPageBR(String file) {

        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)), "UTF-8"));
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(FileTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return in;
    }

    public static String getPageString(String files) {
        File file = new File(files);

        FileInputStream fis = null;
        String str = "";

        try {
            fis = new FileInputStream(file);
            int content;
            while ((content = fis.read()) != -1) {
                str += (char) content;
            }
        } catch (IOException e) {
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
            }
        }
        return str;
    }

    public static JsonObject getContent(String file) {
        try {
            StringBuilder sb;
            try (BufferedReader br = getPageBR(file)) {
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
