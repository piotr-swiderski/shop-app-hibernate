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

    CategoryEnum(String category) {
        this.category = category;
    }

    public String getString() {
        return category;
    }

    public static List<String> CategoryList(){
        return Stream.of(CategoryEnum.values())
                .map(s -> s.getString())
                .collect(Collectors.toList());
    }
}
