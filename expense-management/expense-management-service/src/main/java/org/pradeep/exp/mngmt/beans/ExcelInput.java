package org.pradeep.exp.mngmt.beans;

import lombok.Getter;
import lombok.Setter;

/**
 * @author psingarakannan on 19/12/18
 **/
@Setter @Getter
public class ExcelInput {

    private String excelPath;

    private Integer lastAccessedRow;

    private String monthShortText;

    private String year;
}
