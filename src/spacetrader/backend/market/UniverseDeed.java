package spacetrader.backend.market;

public class UniverseDeed extends MarketItem {
    public UniverseDeed(String playerName) {
        super(MarketGoods.UNIVERSE);
        officialItemName = playerName + "'s Universe";
    }
}
