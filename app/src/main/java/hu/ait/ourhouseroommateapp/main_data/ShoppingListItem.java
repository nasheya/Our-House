package hu.ait.ourhouseroommateapp.main_data;

/**
 * Created by nasheyarahman on 5/16/17.
 */

public class ShoppingListItem extends GeneralItem {
    public enum ShoppingCategory {
        GROCERY(0, "Groceries"),
        ESSENTIALS(1, "Home Essentials"),
        APPLICANCES(2, "Home Appliances"),
        EVENT(3, "Special Event Items"),
        MISC(4, "Misc");

        private int value;
        private String name;

        private static final int size = ShoppingCategory.values().length;

        private ShoppingCategory(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public static int getSize() {
            return size;
        }

        public static ShoppingCategory fromInt(int value) {
            for (ShoppingCategory p : ShoppingCategory.values()) {
                if (p.value == value) {
                    return p;
                }
            }

            return MISC;
        }
    }

    private String name;
    private String description;
    private boolean bought;
    private int category;

    public ShoppingListItem(String itemName, String description, boolean bought, int category){
        this.name = itemName;
        this.description = description;
        this.bought = bought;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
