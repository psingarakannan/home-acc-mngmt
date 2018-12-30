package org.pradeep.platform.beans;

import lombok.Getter;
import lombok.Setter;

/**
 * @author psingarakannan on 30/12/18
 **/
@Getter @Setter
public class ExceptionOutput {


    private boolean success;

    private String exception;

    private String errorMessage;

}
