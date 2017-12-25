package configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component
public class UserConfig implements Serializable {
    @Value("#{'${football_stuff.columnNames}'.split(',')}")
    public String[] columnsInRows;

    @Value("${codes.values}")
    public String[] codesValues;

    @Value("${football.teams}")
    public String teamSquads;

}