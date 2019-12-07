package pl.swiderski.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CategoryEnum {

    CATEGORY1("Owoce"),
    CATEGORY2("Warzywa"),
    CATEGORY3("Slodycze"),
    CATEGORY4("Napoje"),
    CATEGORY5("Produkty szkolne"),
    CATEGORY6("Czesci samochodowe"),
    CATEGORY7("Akcesoria komputerowe");

    private final String category;
    private static final List<String> categoryList = Stream.of(CategoryEnum.values())
            .map(CategoryEnum::getString)
            .collect(Collectors.toList());


    CategoryEnum(String category) {
        this.category = category;
    }

    public String getString() {
        return category;
    }

    public static List<String> getCategoryList() {
        return categoryList;
    }

    public static int getIndexOfCategory(String category){
        return categoryList.indexOf(category);
    }
}
