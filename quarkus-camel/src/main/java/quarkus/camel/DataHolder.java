package quarkus.camel;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Named("dataholder")
public class DataHolder {

    List<Store> stores = Arrays.asList();

    public void processLines(List<List<String>> csvLines) {
        stores = csvLines.stream().map(line -> new Store(line)).collect(Collectors.toList());
    }

}
