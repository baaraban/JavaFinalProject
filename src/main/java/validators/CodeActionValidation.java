package validators;

import annotations.RegisterUDF3Boolean;
import com.google.common.base.Strings;
import configuration.UserConfig;
import org.apache.spark.sql.api.java.UDF3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@RegisterUDF3Boolean
public class CodeActionValidation implements UDF3<String, String, String, Boolean>, Serializable {
    @Autowired
    private UserConfig userConfig;


    @Override
    public Boolean call(String eventCode, String from, String to) throws Exception {
        if(userConfig.bothActions.contains(eventCode) && (Strings.isNullOrEmpty(from) || Strings.isNullOrEmpty(to))){
            return false;
        }

        if(userConfig.fromActions.contains(eventCode) && (Strings.isNullOrEmpty(from) || !Strings.isNullOrEmpty(to))){
            return false;
        }

        if(userConfig.toActions.contains(eventCode) && (Strings.isNullOrEmpty(to) || !Strings.isNullOrEmpty(from))){
            return false;
        }

        return true;
    }
}
