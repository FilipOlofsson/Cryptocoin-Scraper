public class ScrapeResult {
    private String coin;
    private double price;
    ScrapeResult(String coin, double price) {
        this.coin = coin;
        this.price = price;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getCoin() {
        return coin;
    }
}
