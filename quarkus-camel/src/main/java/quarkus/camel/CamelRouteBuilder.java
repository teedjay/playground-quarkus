package quarkus.camel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.csv.CsvDataFormat;

public class CamelRouteBuilder extends RouteBuilder {

    public void configure() {
        from("timer://testTimer?repeatCount=1")
            .to("https://www.vinmonopolet.no/medias/sys_master/locations/locations/h3c/h4a/8834253946910/8834253946910.csv")
            .setHeader(Exchange.CHARSET_NAME, constant("iso-8859-1"))
            .unmarshal(new CsvDataFormat().setDelimiter(';'))
            .bean("bean:dataholder")
            .log("all done");
    }

}
