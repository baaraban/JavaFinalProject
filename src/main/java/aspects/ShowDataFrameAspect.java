package aspects;

import consts.EnvironmentConsts;
import org.apache.spark.sql.DataFrame;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Profile(EnvironmentConsts.DEV)
public class ShowDataFrameAspect {
    @Before("@annotation(ShowDataFrameInTheBeginning)")
    public void showDataFrameInTheBeginningOfMethod(JoinPoint jp){
        DataFrame df = (DataFrame) jp.getArgs()[0];
        System.out.println("Show DataFrameInTheBeginning aspect is working ...");
        df.show();
        System.out.println("Show DataFrameInTheBeginning aspect end of the print...");
    }
}
