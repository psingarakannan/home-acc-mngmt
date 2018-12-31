package org.pradeep.platform.utils;

import flexjson.JSONSerializer;

/**
 * @author psingarakannan on 30/12/18
 **/
public class JsonUtils {

    public static String deepSerialize(Object o) {
        JSONSerializer ser = new JSONSerializer().transform(new ExcludeNullTransformer(), void.class);
        ser.exclude("*.class");
        ser.prettyPrint(false);
        return ser.deepSerialize(o);
    }
}
