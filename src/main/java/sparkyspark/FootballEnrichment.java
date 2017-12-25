package sparkyspark;

import org.apache.spark.sql.DataFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class FootballEnrichment {
    @Autowired
    private FootbalFrameBuilder frameBuilder;

    public void doWork(){
        DataFrame df = frameBuilder.load("data/rawData.txt");

        df.show();
    }
}