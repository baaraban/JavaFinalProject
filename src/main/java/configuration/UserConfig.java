package configuration;

import consts.EnvironmentConsts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class UserConfig implements Serializable {
    @Autowired
    private EnvironmentConsts consts;

    public String filePath = "data/rawData.txt";

    public List<String> bothActions = new ArrayList<String>();
    public List<String> fromActions = new ArrayList<String>();
    public List<String> toActions = new ArrayList<String>();


    @Value("#{'${football_stuff.columnNames}'.split(',')}")
    public String[] columnsInRows;

    @Value("${codes.values}")
    public String[] codesValues;

    @Value("${football.teams}")
    public String teamSquads;

    public Map<String, String> codesMap = new HashMap<>();

    public Map<String, String> teamMap = new HashMap<>();

    @PostConstruct
    public void initMap(){
        for(String value: codesValues) {
            String key = value.substring(0, value.indexOf('=')).trim();
            String val = value.substring(value.indexOf('=')+1, value.length()).trim();

            //I know, it's very bad, but I'm practicing with extremal coding, sorry about that
            if(val.contains(consts.TO_ACTION_MARKING)){
                val = val.replace(consts.TO_ACTION_MARKING, "");
                toActions.add(key);
            } else if(val.contains(consts.FROM_ACTION_MARKING)){
                val = val.replace(consts.FROM_ACTION_MARKING, "");
                fromActions.add(key);
            } else if(val.contains(consts.BOTH_ACTION_MARKING)) {
                val = val.replace(consts.BOTH_ACTION_MARKING, "");
                bothActions.add(key);
            }

            this.codesMap.put(key, val);
        }

        String[] teams = teamSquads.split(";");
        for(String team: teams){
            String key = team.substring(0, team.indexOf('='));
            String value = team.substring(team.indexOf('=') + 1, team.length());
            this.teamMap.put(key, value);
        }
    }
}
