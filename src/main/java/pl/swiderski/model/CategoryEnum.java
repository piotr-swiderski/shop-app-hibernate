package pl.swiderski.model;

public enum CategoryEnum {

    CATEGORY1("Category1"),
    CATEGORY2("Category2"),
    CATEGORY3("Category3"),
    CATEGORY4("Category4"),
    CATEGORY5("Category5");

    private final String category;

    CategoryEnum(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
