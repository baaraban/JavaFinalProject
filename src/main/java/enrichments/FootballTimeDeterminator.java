package enrichments;

import configuration.UserConfig;
import org.apache.spark.sql.api.java.UDF1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import annotations.RegisterUDF1String;

import java.io.Serializable;

@Component
@RegisterUDF1String
public class FootballTimeDeterminator implements UDF1<String, String>, Serializable {
    @Autowired
    private UserConfig userConfig;

    @Override
    public String call(String time) throws Exception {
       return Integer.parseInt(time.split(":")[0]) >= userConfig.ONE_TIME_DURATION ? "Second" : "First";
    }
}
