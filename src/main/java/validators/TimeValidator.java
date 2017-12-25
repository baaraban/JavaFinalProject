package validators;

import annotations.RegisterUDF1Boolean;
import configuration.UserConfig;
import consts.EnvironmentConsts;
import org.apache.spark.sql.api.java.UDF1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@RegisterUDF1Boolean
public class TimeValidator implements UDF1<String, Boolean>, Serializable{
    @Autowired
    private EnvironmentConsts consts;

    @Autowired
    private UserConfig userConfig;

    @Override
    public Boolean call(String time) throws Exception {
        String[] timeArr = time.split(":");
        Integer minutes = Integer.parseInt(timeArr[0]);
        Integer seconds = Integer.parseInt(timeArr[1]);
        if(minutes > consts.MAX_MINUTES_VALUE || minutes < consts.MIN_MINUTES_VALUE){
            return false;
        }

        if(seconds > consts.MAX_SECONDS_VALUE || seconds < consts.MIN_SECONDS_VALUE){
            return false;
        }

        return true;
    }
}
