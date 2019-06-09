package quarkus.camel;

import java.util.List;

public class Store {

    public String name;
    public String category;
    public String number;

    public Store(List<String> line) {
        this.name = line.get(1);
        this.category = line.get(9);
        this.number = line.get(26);
    }

    public String getCategory() {
        return category;
    }

}
