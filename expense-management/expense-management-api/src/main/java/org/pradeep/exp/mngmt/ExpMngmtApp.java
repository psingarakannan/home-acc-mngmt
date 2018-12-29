package org.pradeep.exp.mngmt;

import org.pradeep.platform.constants.C;
import org.springframework.boot .SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author psingarakannan on 9/12/18
 **/

//TODO Include logger, Redis or Hz in future
/*This Application is created to manage the household expense management
*
*/
@SpringBootApplication()
public class ExpMngmtApp {
    public static void main(String[] args) {
        System.setProperty(C.SPRING_APPLICATION_NAME, "exp-mngmt");

        SpringApplication.run ( ExpMngmtApp.class, args );
        System.out.println ("Application ExpMngmtApp started");
    }
}
