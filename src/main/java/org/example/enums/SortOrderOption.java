package org.example.enums;

public enum SortOrderOption {
    PRICE_LOW_TO_HIGH("Price (low to high)");

    private final String orderOption;

    SortOrderOption(String orderOption) {
        this.orderOption = orderOption;
    }

    public String getOrderOption() {
        return orderOption;
    }
}
