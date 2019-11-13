package spacetrader.backend.player;

import spacetrader.backend.market.MarketItem;

import java.util.HashMap;

/** Inventory of every item in Ship. Fuel is not counted in Inventory. */
public class Inventory {

    private int size;
    private int usedSpace;
    private HashMap<String, Integer> items;

    public Inventory(int size) {
        this.size = size;
        this.usedSpace = 0;
        items = new HashMap<>();
    }

    public int getSize() {
        return size;
    }

    public int getUsedSpace() {
        return usedSpace;
    }

    public int getCurrentCount(MarketItem item) {
        if (items.containsKey(item.getOfficialItemName())) {
            return items.get(item.getOfficialItemName());
        }
        return 0;
    }

    public HashMap<String, Integer> getItems() {
        return items;
    }

    public boolean addItem(MarketItem item, int quantity) {
        if (item.getSize() * quantity + usedSpace <= size) {
            if (items.containsKey(item.getOfficialItemName())) {
                items.put(item.getOfficialItemName(),
                        items.get(item.getOfficialItemName()) + quantity);
            } else {
                items.put(item.getOfficialItemName(), quantity);
            }
            usedSpace += item.getSize() * quantity;
            return true;
        }
        return false;
    }

    public boolean removeItem(MarketItem item, int quantity) {
        if (items.containsKey(item.getOfficialItemName())
                && items.get(item.getOfficialItemName()) >= quantity) {
            if (items.get(item.getOfficialItemName()) - quantity == 0) {
                items.remove(item.getOfficialItemName());
            } else {
                items.put(item.getOfficialItemName(),
                        items.get(item.getOfficialItemName()) - quantity);
            }
            usedSpace -= item.getSize() * quantity;
            return true;
        }
        return false;
    }

    public boolean removeItem(String item) {
        if (items.containsKey(item)) {
            items.remove(item);
            return true;
        }
        return false;
    }

    public boolean removeAllItems() {
        items.clear();
        return true;
    }
}
