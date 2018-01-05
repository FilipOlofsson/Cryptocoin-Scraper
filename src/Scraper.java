import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Scraper {
    
    ScrapeResult Scrape(String coin) {
        try {
            Document doc = Jsoup.connect("https://coinmarketcap.com/currencies/" + coin).get();
            double rawPrice = Double.parseDouble(doc.select("#quote_price").attr("data-usd"));
            String coinAbbrev = doc.select("small.hidden-xs").html().replaceAll("[()]", "").toLowerCase();
            return new ScrapeResult(coinAbbrev, rawPrice);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
