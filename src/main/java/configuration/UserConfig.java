package configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class UserConfig implements Serializable {
    public final int MAX_SECONDS_VALUE = 60;
    public final int MIN_SECONDS_VALUE = 0;
    public final int MAX_MINUTES_VALUE = 120;
    public final int MIN_MINUTES_VALUE = 0;
    public final int ONE_TIME_DURATION = 45;

    private final String BOTH_ACTION_MARKING = "(both)";
    private final String FROM_ACTION_MARKING = "(from)";
    private final String TO_ACTION_MARKING = "(to)";

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

            //I know, it's very bad, but I'm practicing with extremal coding, sorry about that
            if(key.contains(TO_ACTION_MARKING)){
                key = key.replace(TO_ACTION_MARKING, "");
                toActions.add(key);
            } else if(key.contains(FROM_ACTION_MARKING)){
                key = key.replace(FROM_ACTION_MARKING, "");
                fromActions.add(key);
            } else if(key.contains(BOTH_ACTION_MARKING)) {
                key = key.replace(BOTH_ACTION_MARKING, "");
                bothActions.add(key);
            }

            String val = value.substring(value.indexOf('=')+1, value.length()).trim();
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