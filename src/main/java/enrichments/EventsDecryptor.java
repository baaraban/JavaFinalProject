package enrichments;

import org.apache.spark.sql.api.java.UDF1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sparkyspark.RegisterUDF;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
@RegisterUDF
public class EventsDecryptor implements UDF1<String, String>, Serializable {
    @Value("${codes.values}")
    private String[] codesValues;

    private Map<String, String> codesMap = new HashMap<>();

    @PostConstruct
    public void initMap(){
        for(String value: codesValues) {
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
