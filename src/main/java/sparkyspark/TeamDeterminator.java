package sparkyspark;

import org.apache.spark.sql.api.java.UDF1;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
@RegisterUDF
public class TeamDeterminator implements UDF1<String, String>, Serializable {

    @Value("${football.teams}")
    private String teamSquads;

    private Map<String, String> teamMap = new HashMap<>();

    @PostConstruct
    public void initMap(){
            String[] teams = teamSquads.split(";");
            for(String team: teams){
                String key = team.substring(0, team.indexOf('='));
                String value = team.substring(team.indexOf('=') + 1, team.length());
                this.teamMap.put(key, value);
            }

    }

    @Override
    public String call(String s) throws Exception {
        for(Map.Entry<String, String> pair: teamMap.entrySet()){
            if(pair.getValue().contains(s)){
                return pair.getKey();
            }
        }
        return null;
    }
}
