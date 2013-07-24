package com.lumata.common.testing.plan;

import com.lumata.common.testing.system.Security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: adipasquale
 * Date: 7/23/13
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestSecurity {

    /*private static final Logger logger = LoggerFactory.getLogger(TestSecurity.class);*/

    @Test()
    public void encrypt_decrypt_password() {

        String password = "password";

        String encrypted_password = Security.encrypt( password );

        String decrypted_password = Security.decrypt( encrypted_password );

        /*Assert.assertEquals( password, decrypted_password );*/

        Assert.assertEquals( "failed", decrypted_password );

    }


}
