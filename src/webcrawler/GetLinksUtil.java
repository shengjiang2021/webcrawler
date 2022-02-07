package webcrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class GetLinksUtil {
    private static final int LEVEL_LIMIT = 1;

    public static String getContentFromHtmlPage(String url) {
        StringBuilder sb = new StringBuilder();

        try {
            URLConnection connection = new URL(url).openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            in.close();
        } catch (IOException e) {
            System.out.println("IOException found! Ignore current page with link: " + url);
        }

        return sb.toString();
    }

    private static String getNextLink(String page, int[] stx, int lv) {

        int linkStx = page.indexOf("<a href=", stx[0]);
        if (linkStx == -1) return null;
        int startQuote = page.indexOf('"', linkStx);

        StringBuilder sb = new StringBuilder();
        int i = startQuote + 1;
        for (; i < page.length() && page.charAt(i) != '"'; i++) {
            sb.append(page.charAt(i));
        }
        stx[0] = i + 1;
        return sb.toString();
    }

    public static void printAllLinks(String url, int lv, int limit, String parentUrl) {
        if (lv > limit) return;

        List<String> allLinks = new ArrayList<>();
        String page = getContentFromHtmlPage(url);

        int[] curIndex = new int[1];
        String nextLink;
        do {
            nextLink = getNextLink(page, curIndex, lv);
            if (parentUrl != null && parentUrl.eqals(nextLink)) continue;

            if (nextLink != null && nextLink.startsWith("http")) {
                allLinks.add(nextLink);

                String finalNextLink = nextLink;
                new Thread(() -> GetLinksUtil.printAllLinks(finalNextLink, lv + 1, limit, url)).start();
            }
        } while (nextLink != null);

        print(url, allLinks, lv);
    }

    private synchronized static void print(String url, List<String> childrenPages, int lv) {
        System.out.println("lv " + lv + " pages found on link: " + url);
        childrenPages.forEach(child -> System.out.println("|-- level " + lv + " " + child));
    }
}
