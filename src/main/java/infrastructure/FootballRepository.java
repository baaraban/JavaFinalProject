package infrastructure;

import enrichments.EventsDecryptor;
import enrichments.FootballTimeDeterminator;
import enrichments.TeamDeterminator;
import helpers.FootbalFrameBuilder;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validators.CodeActionValidation;
import validators.TimeValidator;

import java.io.Serializable;

import static org.apache.spark.sql.functions.col;

@Service
public class FootballRepository implements Serializable {
    @Autowired
    private FootbalFrameBuilder frameBuilder;

    public void doWork(){
        DataFrame df = frameBuilder.load("data/rawData.txt");


        df = df.withColumn("isTimeValid",
                functions.callUDF(TimeValidator.class.getName(), col("eventTime")));

        df = df.withColumn("isActionCodesValid",
                functions.callUDF(CodeActionValidation.class.getName(),
                        col("code"),
                        col("from"),
                        col("to")));

        df = df.withColumn("event name",
                functions.callUDF(EventsDecryptor.class.getName(),col("code")));
        df = df.withColumn("team",
                functions.callUDF(TeamDeterminator.class.getName(), col("from")));
        df = df.withColumn("time",
                functions.callUDF(FootballTimeDeterminator.class.getName(), col("eventTime")));


        df.show();
    }
}