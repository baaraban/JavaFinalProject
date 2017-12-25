package helpers;

import configuration.UserConfig;
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
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class FootbalFrameBuilder implements Serializable {

    @Autowired
    private JavaSparkContext javaSparkContext;

    @Autowired
    private SQLContext sqlContext;

    @Autowired
    private UserConfig userConfig;

    private StructType getStructType(){
        List<StructField> fields = new ArrayList<>();
        for(String fieldName: userConfig.columnsInRows){
            StructField field = DataTypes.createStructField(fieldName, DataTypes.StringType, true);
            fields.add(field);
        }

        return DataTypes.createStructType(fields);
    }

    public JavaRDD<Row> getRowRdd(String filePath){
        //smells like kostyl', but it's not working any other way(serializable exception)
        String[] columnsInRows = userConfig.columnsInRows;
        JavaRDD<String> rdd = javaSparkContext.textFile(filePath, 1);
        return rdd.map(record -> {
            ArrayList<String> attributes = new ArrayList<String>();
            for(String fieldName: columnsInRows){
                if(record.contains(fieldName)){
                    int start = record.indexOf(fieldName) + fieldName.length();
                    String withoutBeginning = record.substring(start);
                    attributes.add(withoutBeginning.substring(withoutBeginning.indexOf('=') + 1, withoutBeginning.indexOf(';')));
                }
            }
            return RowFactory.create(attributes.toArray());
        });
    }

    public DataFrame load(String filePath){
        StructType structType = this.getStructType();

        JavaRDD<Row> rowRdd = getRowRdd(filePath);

        return sqlContext.createDataFrame(rowRdd, structType);
    }

}
