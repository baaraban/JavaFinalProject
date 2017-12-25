package configuration;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"enrichments", "sparkyspark", "validators", "configuration",
        "infrastructure", "helpers", "services", "annotations", "consts", "aspects"})
@PropertySource({"classpath:football_columns.properties", "classpath:codes.properties", "classpath:teams.properties"})
@EnableAspectJAutoProxy
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
    public UserConfig userConfig(){ return new UserConfig();}
}
