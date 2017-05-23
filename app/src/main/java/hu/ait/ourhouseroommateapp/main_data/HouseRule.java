package hu.ait.ourhouseroommateapp.main_data;

/**
 * Created by nasheyarahman on 5/16/17.
 */

public class HouseRule extends GeneralItem{
    String rule;

    public HouseRule(){}

    public HouseRule(String rule) {
        this.rule = rule;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
