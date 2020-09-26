package request;

import business.TransactionManager;
import dto.Transaction;
import dto.User;
import util.WalletUtil;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class WalletRequests {

    TransactionManager transactionManager;

    public WalletRequests(){
        transactionManager = new TransactionManager();
    }

    public Transaction createTransactionRequest(String fromAccount, String toAccount, BigDecimal amount, String type) throws Exception{
        Transaction transaction = new Transaction();
        transaction.setToAccount(toAccount);
        transaction.setFromAccount(fromAccount);
        transaction.setTransactionType(type);
        transaction.setTransactionDate(Calendar.getInstance().getTime());
        transaction.setTransactionAmount(amount);
        return transaction;
    }

    public User createUserRequest(String email, String name, String password) throws Exception{

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setAccount_id("accnt" + WalletUtil.generateID());
        user.setPassword(transactionManager.encryptPassword(password));
        user.setUsername(email);
        return user;
    }
}
