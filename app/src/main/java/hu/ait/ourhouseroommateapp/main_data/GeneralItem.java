package hu.ait.ourhouseroommateapp.main_data;

/**
 * Created by nasheyarahman on 5/16/17.
 */

public abstract class GeneralItem {
    public static final String CHORES_NODE = "chores";
    public static final String EXPENSES_NODE = "expenses";
    public static final String HOUSE_RULES_NODE = "house_rules";
    public static final String SHOPPING_LIST_NODE = "shopping_list";
    public static final String SHOPPING_LIST_GROUP = "group";
    public static final String BOARD_NODE = "board";

    protected String key;

    public String getKey(){
        return key;
    }

    public void setKey(String key){
        this.key = key;
    }
}
