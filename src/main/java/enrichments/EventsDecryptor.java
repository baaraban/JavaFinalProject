package enrichments;

import configuration.UserConfig;
import org.apache.spark.sql.api.java.UDF1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import annotations.RegisterUDF;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
@RegisterUDF
public class EventsDecryptor implements UDF1<String, String>, Serializable {
    @Autowired
    private UserConfig userConfig;

    private Map<String, String> codesMap = new HashMap<>();

    @PostConstruct
    public void initMap(){
        for(String value: userConfig.codesValues) {
            String key = value.substring(0, value.indexOf('=')).trim();
            String val = value.substring(value.indexOf('=')+1, value.length()).trim();
            this.codesMap.put(key, val);
        }

    }

    @Override
    public String call(String val) throws Exception {
        return this.codesMap.get(val);
    }
}
