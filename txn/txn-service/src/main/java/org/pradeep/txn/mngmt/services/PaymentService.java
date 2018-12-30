package org.pradeep.txn.mngmt.services;

import org.pradeep.platform.beans.TxnInput;
import org.pradeep.platform.beans.TxnOutput;
import org.springframework.stereotype.Service;

/**
 * @author psingarakannan on 28/12/18
 **/

@Service
public class PaymentService {

    public TxnOutput pay(TxnInput txnInput){


        return new TxnOutput ();
    }

}
