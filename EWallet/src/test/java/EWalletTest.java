import business.AccountManager;
import business.TransactionManager;

import dto.Account;
import dto.User;
import org.junit.*;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.AssertTrue;
import java.math.BigDecimal;


public class EWalletTest {

    @Test
    public void testCreateUserFlow() throws Exception{
        AccountManager manager = new AccountManager();

        boolean userExists = manager.userExists("testuser7@gmail.com");
        if (!userExists) {
            manager.createUser("testuser7@gmail.com", "Name", "password");

        }
        User user = manager.getUserProfile("testuser7@gmail.com");
        Assert.assertEquals("testuser7@gmail.com", user.getEmail());
        Assert.assertEquals("Name", user.getName());


    }

    @Test
    public void testAddMoney() throws Exception{
        AccountManager manager = new AccountManager();
        TransactionManager transactionManager = new TransactionManager();
        User user = manager.getUserProfile("testuser7@gmail.com");
        Account account = manager.getAccountBalance(user.getAccount_id());
        transactionManager.addMoneyToAccount(user.getAccount_id(), new BigDecimal(100));
        Account account1 = manager.getAccountBalance(user.getAccount_id());
        Assert.assertEquals(account.getBalance().add(new BigDecimal(100)), account1.getBalance());

    }

}
