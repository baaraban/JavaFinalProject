package sparkyspark;

import configuration.Config;
import infrastructure.FootballEnrichment;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

       FootballEnrichment footballEnrichment = context.getBean(FootballEnrichment.class);
       footballEnrichment.doWork();
    }
}
