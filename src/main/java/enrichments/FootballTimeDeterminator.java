package enrichments;

import org.apache.spark.sql.api.java.UDF1;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sparkyspark.RegisterUDF;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
@RegisterUDF
public class FootballTimeDeterminator implements UDF1<String, String>, Serializable {
    @Override
    public String call(String time) throws Exception {
       return Integer.parseInt(time.split(":")[0]) >= 45 ? "Second" : "First";
    }
}
