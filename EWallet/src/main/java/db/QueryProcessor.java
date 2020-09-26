package db;

import constants.WalletConstants;
import dto.Account;
import dto.Transaction;
import dto.User;
import util.WalletUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class QueryProcessor {

    public Connection connection;

    public QueryProcessor(){
        connection = H2Manager.getConnection();
    }

    public Account fetchAccountBalance(String accountNo) throws Exception{
        Account account = new Account();
        try  {
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.GET_ACCOUNT_BALANCE);
            preparedStatement.setString(1, accountNo);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                account.setAccountId(accountNo);
                account.setBalance(rs.getBigDecimal(WalletConstants.ACCOUNT_BALANCE));
            }

            return account;
        } catch (SQLException e) {
            H2Manager.printSQLException(e);
            throw new Exception(e);
        }

    }

    public String fetchTransactionStatus(String transactionId) throws Exception{

        String status = "";
        try  {
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.GET_TRANSACTION_STATUS);
            preparedStatement.setString(1, transactionId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                status = rs.getString(WalletConstants.TRANSACTION_STATUS);

            }
            return status;
        } catch (SQLException e) {
            H2Manager.printSQLException(e);
            throw new Exception(e);
        }
    }

    public Transaction fetchTransactionDetails(String transactionId) throws Exception{
        Transaction transaction = new Transaction();
        try  {
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.GET_TRANSACTION_DETAILS);
            preparedStatement.setString(1, transactionId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                transaction.setTransactionDate(rs.getDate(WalletConstants.TRANSACTION_DATE));
                transaction.setTransactionType(rs.getString(WalletConstants.TRANSACTION_TYPE));
                transaction.setStatus(rs.getString(WalletConstants.TRANSACTION_STATUS));
                transaction.setTransactionAmount(rs.getBigDecimal(WalletConstants.TRANSACTION_AMT));
                transaction.setFromAccount(rs.getString(WalletConstants.FROM_ACCOUNT));
                transaction.setToAccount(rs.getString(WalletConstants.TO_ACCOUNT));

            }
            return transaction;
        } catch (SQLException e) {
            H2Manager.printSQLException(e);
            throw new Exception(e);
        }

    }

    public List<Transaction>  fetchPassbook(String accountNo) throws Exception{
        List<Transaction> transactionList = new ArrayList<>();
        try  {
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.GET_MINI_STATEMENT);
            preparedStatement.setString(1, accountNo);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionDate(rs.getDate(WalletConstants.TRANSACTION_DATE));
                transaction.setTransactionType(rs.getString(WalletConstants.TRANSACTION_TYPE));
                transaction.setRemainingBalance(rs.getBigDecimal(WalletConstants.REMAINING_BALANCE));
                transaction.setTransactionAmount(rs.getBigDecimal(WalletConstants.TRANSACTION_AMT));
                transaction.setFromAccount(rs.getString(WalletConstants.FROM_ACCOUNT));
                transaction.setToAccount(rs.getString(WalletConstants.TO_ACCOUNT));

                transactionList.add(transaction);

            }
            return transactionList;
        } catch (SQLException e) {
            H2Manager.printSQLException(e);
            throw new Exception(e);
        }

    }

    public User  getUserDetails(String email) throws Exception{
        User user = new User();
        try  {
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.GET_USER_PROFILE);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                user.setAccount_id(rs.getString(WalletConstants.ACCOUNT_ID));
                user.setName(rs.getString(WalletConstants.NAME));
                user.setCreated_on(rs.getDate(WalletConstants.CREATED_ON));
                user.setEmail(rs.getString(WalletConstants.EMAIL));

            }
            return user;
        } catch (SQLException e) {
            H2Manager.printSQLException(e);
            throw new Exception(e);
        }

    }

    public void addMoney(String accountId, BigDecimal amount) throws Exception{

        try  {
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.ADD_MONEY);
            preparedStatement.setString(2, accountId);
            preparedStatement.setBigDecimal(1, amount);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            H2Manager.printSQLException(e);
            throw new Exception(e);
        }

    }

    public void createTransaction(Transaction transaction) throws Exception{

        try  {
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.CREATE_TRANSACTION);
            preparedStatement.setString(1, WalletUtil.generateID());
            preparedStatement.setBigDecimal(2, transaction.getTransactionAmount());
            preparedStatement.setString(3, transaction.getTransactionType());
            preparedStatement.setBigDecimal(4, transaction.getRemainingBalance());
            preparedStatement.setDate(5, new Date(Calendar.getInstance().getTimeInMillis()));
            preparedStatement.setString(6, transaction.getFromAccount());
            preparedStatement.setString(7, transaction.getToAccount());
            preparedStatement.setString(8, transaction.getDetails());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            H2Manager.printSQLException(e);
            throw new Exception(e);
        }

    }

    public void initiateAccountActivity(String account, BigDecimal amount) throws Exception{
        try  {
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.INITIATE_ACCOUNT);
            preparedStatement.setString(1, WalletUtil.generateID());
            preparedStatement.setString(2, account);
            preparedStatement.setBigDecimal(3, amount);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            H2Manager.printSQLException(e);
            throw new Exception(e);
        }

    }

    public void createUserAccount(User user) throws Exception{

        try  {
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.CREATE_USER);
            preparedStatement.setString(1, "user" + WalletUtil.generateID());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setDate(5, new Date(Calendar.getInstance().getTimeInMillis()));
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setString(7, user.getAccount_id());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            initiateAccountActivity(user.getAccount_id(), new BigDecimal(0));

        } catch (SQLException e) {
            H2Manager.printSQLException(e);
            throw new Exception(e);
        }

    }
}
