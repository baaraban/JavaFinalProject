package sparkyspark;

import org.apache.spark.sql.DataFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import static org.apache.spark.sql.functions.*;
import static org.apache.spark.sql.functions.col;

@Service
public class FootballEnrichment implements Serializable {
    @Autowired
    private FootbalFrameBuilder frameBuilder;

    public void doWork(){
        DataFrame df = frameBuilder.load("data/rawData.txt");
        df = df.withColumn("event name",
                callUDF(EventsDecryptor.class.getName(),col("code")));
        df.show();
    }
}