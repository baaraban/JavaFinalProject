package enrichments;

import configuration.UserConfig;
import org.apache.spark.sql.api.java.UDF1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import annotations.RegisterUDF1String;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
@RegisterUDF1String
public class TeamDeterminator implements UDF1<String, String>, Serializable {
    @Autowired
    private UserConfig userConfig;

    @Override
    public String call(String s) throws Exception {
        for(Map.Entry<String, String> pair: userConfig.teamMap.entrySet()){
            if(pair.getValue().contains(s)){
                return pair.getKey();
            }
        }
        return null;
    }
}
