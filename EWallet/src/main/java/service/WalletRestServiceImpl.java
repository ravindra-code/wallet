package service;

import business.AccountManager;
import business.TransactionManager;
import dto.Account;
import dto.Transaction;
import dto.User;

import java.math.BigDecimal;

public class WalletRestServiceImpl implements WalletRestService{

    public TransactionManager transactionManager;
    public AccountManager accountManager;

    public WalletRestServiceImpl(){
        transactionManager = new TransactionManager();
        accountManager = new AccountManager();
    }

    @Override
    public Account getAccountBalance(String accountId) throws Exception{
        return accountManager.getAccountBalance(accountId);
    }

    @Override
    public String getTransactionStatus(String transactionId) throws Exception{
        return transactionManager.getTransactionStatus(transactionId);
    }

    @Override
    public void createUserProfile(User user) throws Exception{
        accountManager.createUser(user.getEmail(), user.getName(), user.getPassword());
    }

    @Override
    public void addMoneyToAccount(String email, BigDecimal amount) throws Exception{
        User user = accountManager.getUserProfile(email);
        transactionManager.addMoneyToAccount(user.getAccount_id(), amount);

    }

    @Override
    public void transferFunds(String from, String to, BigDecimal amount) throws Exception{
        transactionManager.transferMoney(from, to, amount);

    }
}
