package business;

import constants.WalletConstants;
import db.QueryProcessor;
import dto.Account;
import dto.Transaction;
import exception.InsufficientBalanceException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import request.WalletRequests;

import java.math.BigDecimal;
import java.util.List;

public class TransactionManager {

    QueryProcessor queryProcessor;

    public TransactionManager(){
        queryProcessor = new QueryProcessor();

    }

    public Transaction getTransactionDetails(String transactionId) throws Exception{

        return queryProcessor.fetchTransactionDetails(transactionId);
    }

    public List<Transaction> fetchPassbookDetails(String accountId) throws Exception{

        return queryProcessor.fetchPassbook(accountId);
    }

    public String getTransactionStatus(String transactionId) throws Exception{

        return queryProcessor.fetchTransactionStatus(transactionId);
    }


    // todo: make it thread safe.
    public void addMoneyToAccount(String accountId, BigDecimal amount) throws Exception{
        WalletRequests requests = new WalletRequests();
        queryProcessor.addMoney(accountId, amount);
        Transaction transaction = requests.createTransactionRequest(WalletConstants.SELF, accountId, amount, WalletConstants.CREDIT);
        queryProcessor.createTransaction(transaction);
    }

    public void transferMoney(String fromAccount, String toAccount, BigDecimal amount) throws Exception{

        Account account = queryProcessor.fetchAccountBalance(fromAccount);
        BigDecimal amountRequired = amount.add(computeCharges(amount));
        if (amountRequired.compareTo(account.getBalance())==1){
            // TODO: throw exception saying insufficient funds.
            throw new InsufficientBalanceException("Insufficient Balance", null);
        }else{

            sendMoney(fromAccount, fromAccount, account.getBalance().subtract(amountRequired));
            sendMoney(fromAccount, toAccount, amount );
            createTransaction(fromAccount, toAccount, amount, WalletConstants.DEBIT);

            // Deduct commissions and charges and send
            sendMoney(fromAccount, WalletConstants.EWALLET_ACCOUNT, amountRequired.subtract(amount));
            createTransaction(fromAccount, WalletConstants.EWALLET_ACCOUNT, amountRequired.subtract(amount), WalletConstants.DEBIT);
        }

    }

    public void sendMoney(String fromAccount, String toAccount, BigDecimal amount) throws Exception{

        queryProcessor.addMoney(toAccount, amount);

    }

    public void createTransaction(String fromAccount, String toAccount, BigDecimal amount, String type) throws Exception{
        WalletRequests requests = new WalletRequests();
        Transaction transaction = requests.createTransactionRequest(fromAccount, toAccount, amount, type);
        queryProcessor.createTransaction(transaction);

    }

    // Calculate commission and charges
    public BigDecimal computeCharges(BigDecimal amount) {

        BigDecimal charges = amount.multiply(new BigDecimal(0.002));
        BigDecimal commission = amount.multiply(new BigDecimal(0.0005));
        BigDecimal totalCharges = charges.add(commission);
        return totalCharges;

    }

    public String encryptPassword(String password){

        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String unencryptedPassword, String hashPassword){

        return BCrypt.checkpw(unencryptedPassword, hashPassword);
    }

}
