package org.pradeep.txn.mngmt.api;

import org.pradeep.platform.constants.C;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author psingarakannan on 29/12/18
 **/
@SpringBootApplication(scanBasePackages = "org.pradeep")
@EntityScan(basePackages = "org.pradeep")
@EnableJpaRepositories("org.pradeep")

public class TxnMngmtApp {
    public static void main(String[] args) {
        System.setProperty(C.SPRING_APPLICATION_NAME, "txn-mngmt");

        SpringApplication.run ( TxnMngmtApp.class, args );
    }
}
