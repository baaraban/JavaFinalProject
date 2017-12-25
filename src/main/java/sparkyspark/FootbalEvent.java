package sparkyspark;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.net.ntp.TimeStamp;

@Data
@Builder
@ToString
public class FootbalEvent {
    private int code;
    private String from;
    private String to;
    private TimeStamp eventTime;
    private String stadion;
    private TimeStamp startTime;
}
