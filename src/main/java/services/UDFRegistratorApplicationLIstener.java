package services;

import annotations.RegisterUDF1Boolean;
import annotations.RegisterUDF3Boolean;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.api.java.UDF3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.apache.spark.sql.types.DataTypes;
import annotations.RegisterUDF1String;

import java.util.Collection;

@Component
public class UDFRegistratorApplicationLIstener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private SQLContext sqlContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Collection<Object> udf1StringObjects = context.getBeansWithAnnotation(RegisterUDF1String.class).values();
        Collection<Object> udf1BooleanObjects = context.getBeansWithAnnotation(RegisterUDF1Boolean.class).values();
        Collection<Object> udf3BooleanObjects = context.getBeansWithAnnotation(RegisterUDF3Boolean.class).values();

        for(Object udfObject: udf1StringObjects){
            sqlContext.udf().register(udfObject.getClass().getName(), (UDF1<?, ?>) udfObject, DataTypes.StringType);
        }
        for(Object udfObject: udf1BooleanObjects){
            sqlContext.udf().register(udfObject.getClass().getName(), (UDF1<?, ?>) udfObject, DataTypes.BooleanType);
        }
        for(Object udfObject: udf3BooleanObjects){
            sqlContext.udf().register(udfObject.getClass().getName(), (UDF3<?, ?, ?, ?>) udfObject, DataTypes.BooleanType);
        }
    }
}
