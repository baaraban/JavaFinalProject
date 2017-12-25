package sparkyspark;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class FootballEnrichment implements Serializable {
    //@Value("${football_stuff.columnNames}")
    private String[] columnsInRows = {"code", "from", "to", "eventTime", "stadion"};
    private DataFrame frame;
    
    public FootballEnrichment(JavaSparkContext context, SQLContext sqlContext) {
        JavaRDD<String> rdd = context.textFile("data/rawData.txt", 1);

        List<StructField> fields = new ArrayList<>();
        for(String fieldName: columnsInRows){
            StructField field = DataTypes.createStructField(fieldName, DataTypes.StringType, true);
            fields.add(field);
        }

        StructType structType = DataTypes.createStructType(fields);

        JavaRDD<Row> rowRdd = rdd.map(record -> {
            ArrayList<String> attributes = new ArrayList<String>();
            for(String fieldName: columnsInRows){
                if(record.contains(fieldName)){
                    int start = record.indexOf(fieldName) + fieldName.length();
                    String withoutBeginning = record.substring(start);
                    attributes.add(withoutBeginning.substring(withoutBeginning.indexOf('=') + 1, withoutBeginning.indexOf(';')));
                }
            }
            return RowFactory.create(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3), attributes.get(4));
        });

        DataFrame df = sqlContext.createDataFrame(rowRdd, structType);
        df.show();
    }
}