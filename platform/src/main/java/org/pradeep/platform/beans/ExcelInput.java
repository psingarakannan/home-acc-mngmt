package org.pradeep.platform.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

/**
 * @author psingarakannan on 19/12/18
 **/
@Setter @Getter
public class ExcelInput {

    private String excelPath;

    private Integer lastAccessedRow;

    private String monthShortText;

    private String year;

    private String cellIndex;


}
