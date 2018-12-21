package org.pradeep.exp.mngmt.utils;

import flexjson.transformer.AbstractTransformer;

/**
 * @author psingarakannan on 20/12/18
 **/
public class ExcludeNullTransformer extends AbstractTransformer {

    @Override
    public Boolean isInline() {
        return true;
    }

    @Override
    public void transform(Object object) {
        // Do nothing, null objects are not serialized.
        return;
    }
}