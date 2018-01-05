import java.io.*;
import java.util.ArrayList;

public class Controller {
    private Database db;
    private Scraper scraper;
    ArrayList<String> coins = new ArrayList<>();
    
    
    public Controller(String server, int port, String username, String password, String db) {
        this.db = new Database(server, port, username, password, db);
        this.scraper = new Scraper();
        coins = loadCoins();
    }
    
    void execute(int interval) {
        while(true) {
            coins = loadCoins();
            for(String coin : coins) {
                ScrapeResult result = scraper.Scrape(coin);
                db.Insert(result.getCoin(), result.getPrice());
            }
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private ArrayList<String> loadCoins() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("coins.txt")));
            ArrayList<String> loadedCoins = new ArrayList<>();
            String line;
            while((line = reader.readLine()) != null) {
                loadedCoins.add(line);
            }
            return loadedCoins;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
