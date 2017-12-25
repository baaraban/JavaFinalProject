package validators;

import annotations.RegisterUDF1Boolean;
import org.apache.spark.sql.api.java.UDF1;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@RegisterUDF1Boolean
public class CodeActionValidation implements UDF1<String, Boolean>, Serializable {

    @Override
    public Boolean call(String s) throws Exception {
        return false;
    }
}
