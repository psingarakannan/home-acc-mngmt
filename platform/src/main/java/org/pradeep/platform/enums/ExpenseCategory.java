package org.pradeep.platform.enums;

import lombok.Getter;

/**
 * @author psingarakannan on 9/12/18
 **/
public enum ExpenseCategory {
    GROCERY("Grocery"),
    FUEL("Fuel"),
    DAILY_EXPENSE("Daily Expense"),
    TRANSFERRED_TO_OTHER_ACCOUNT("Transferred to other account"),
    RENT("Rent"),
    CHIT_FUND("Chit Fund"),
    INVESTMENT("Investment"),
    OTHERS("Others"),
    TRAVEL("Travel");
    public enum SubType{
        MALIGAI("Maligai"),
        OTHERS("Others"),
        VEG("Veg"),
        MEAT("Meat"),
        MILK("Milk"),
        WATER("Water"),
        LIFE_INSURANCE("Life Insurance"),
        MUTUAL_FUND("Mutual Fund"),
        RESTUARANT("Restuarant"),
        FUEL("Fuel");
        @Getter
        private String name;
        SubType(String name){
            this.name = name;
        }
    }
    @Getter
    private String name;
    ExpenseCategory(String name){
        this.name = name;
    }

}
