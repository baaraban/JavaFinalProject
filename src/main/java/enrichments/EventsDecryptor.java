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
public class EventsDecryptor implements UDF1<String, String>, Serializable {
    @Autowired
    private UserConfig userConfig;

    @Override
    public String call(String val) throws Exception {
        return userConfig.codesMap.get(val);
    }
}
