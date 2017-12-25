package infrastructure;

import enrichments.EventsDecryptor;
import enrichments.FootballTimeDeterminator;
import enrichments.TeamDeterminator;
import helpers.FootbalFrameBuilder;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

import static org.apache.spark.sql.functions.col;

@Service
public class FootballEnrichment implements Serializable {
    @Autowired
    private FootbalFrameBuilder frameBuilder;

    public void doWork(){
        DataFrame df = frameBuilder.load("data/rawData.txt");
        df = df.withColumn("event name",
                functions.callUDF(EventsDecryptor.class.getName(),col("code")));
        df = df.withColumn("team",
                functions.callUDF(TeamDeterminator.class.getName(), col("from")));
        df = df.withColumn("time",
                functions.callUDF(FootballTimeDeterminator.class.getName(), col("eventTime")));
        df.show();
    }
}