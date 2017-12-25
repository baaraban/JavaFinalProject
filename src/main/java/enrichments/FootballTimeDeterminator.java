package enrichments;

import org.apache.spark.sql.api.java.UDF1;
import org.springframework.stereotype.Component;
import annotations.RegisterUDF1String;

import java.io.Serializable;

@Component
@RegisterUDF1String
public class FootballTimeDeterminator implements UDF1<String, String>, Serializable {
    @Override
    public String call(String time) throws Exception {
       return Integer.parseInt(time.split(":")[0]) >= 45 ? "Second" : "First";
    }
}
