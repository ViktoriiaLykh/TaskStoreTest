package org.example;

public enum SortOrderOption {
    PRICE_LOW_TO_HIGH("Price (low to high)");

    public String getOrderOption() {
        return orderOption;
    }

    private final String orderOption;

    SortOrderOption(String orderOption) {
        this.orderOption = orderOption;
    }
}
