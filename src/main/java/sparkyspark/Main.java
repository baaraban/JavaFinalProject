package sparkyspark;

import configuration.Config;
import configuration.*;
import consts.EnvironmentConsts;
import infrastructure.FootballRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        //System.setProperty("spring.profiles.active", EnvironmentConsts.DEV);

        UserConfig userConfig = context.getBean(UserConfig.class);
        FootballRepository footballRepository = context.getBean(FootballRepository.class);
        footballRepository.doWork(userConfig.filePath);
    }
}
