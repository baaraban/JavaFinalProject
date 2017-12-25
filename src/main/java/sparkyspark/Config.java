package sparkyspark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan
@PropertySource("classpath:football_columns.properties")
public class Config {


    @Bean
    public JavaSparkContext sparkContext() {
        return new JavaSparkContext(new SparkConf()
                .setMaster("local")
                .setAppName("football_stuff"));
    }

    @Bean
    public SQLContext sqlContext(){ return new SQLContext(sparkContext()); }

    @Bean
    public FootballEnrichment footballEnrichment() { return new FootballEnrichment(sparkContext(), sqlContext()); }
}
