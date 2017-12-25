package sparkyspark;

import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.api.java.UDF1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.apache.spark.sql.types.DataTypes;

import java.util.Collection;

@Component
public class UDFRegistratorApplicationLIstener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private SQLContext sqlContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Collection<Object> udfObjects = context.getBeansWithAnnotation(RegisterUDF.class).values();
        for(Object udfObject: udfObjects){
            sqlContext.udf().register(udfObject.getClass().getName(), (UDF1<?,?>)udfObject, DataTypes.StringType);
        }
    }
}
