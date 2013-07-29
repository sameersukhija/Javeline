/**
 * Created with IntelliJ IDEA.
 * User: adipasquale
 * Date: 7/24/13
 * Time: 11:14 AM
 * To change this template use File | Settings | File Templates.
 */

import com.lumata.common.testing.system.Security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSecurity {

    private static final Logger logger = LoggerFactory.getLogger(TestSecurity.class);

    @Test()
    public void encrypt_decrypt_password() {

        String password = "password";

        String encrypted_password = Security.encrypt( password );

        String decrypted_password = Security.decrypt( encrypted_password );

        Assert.assertEquals( password, decrypted_password );

    }


}
