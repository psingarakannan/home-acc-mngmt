package org.pradeep.exp.mngmt.utils;

import flexjson.JSONSerializer;

/**
 * @author psingarakannan on 20/12/18
 **/
public class CoreHelper {

    public static String deepSerialize(Object o) {
        JSONSerializer ser = (new JSONSerializer()).transform(new ExcludeNullTransformer(), new Class[]{Void.TYPE});
        ser.exclude(new String[]{"*.class"});
        ser.prettyPrint(false);
        return ser.deepSerialize(o);
    }

}
