package sparkyspark;

import configuration.Config;
import infrastructure.FootballRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

       FootballRepository footballRepository = context.getBean(FootballRepository.class);
       footballRepository.doWork();
    }
}
