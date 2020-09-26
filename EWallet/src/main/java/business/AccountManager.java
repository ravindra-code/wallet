package business;

import db.QueryProcessor;
import dto.Account;
import dto.User;
import exception.UserAlreadyExistsException;
import exception.UserNotFoundException;
import request.WalletRequests;

public class AccountManager {

    QueryProcessor queryProcessor;
    WalletRequests walletRequests;

    public AccountManager(){
        queryProcessor = new QueryProcessor();
        walletRequests = new WalletRequests();
    }

    public User getUserProfile(String email) throws Exception{

        User user = queryProcessor.getUserDetails(email);
        if (null!=user.getAccount_id()){
            return user;
        }else{
            throw new UserNotFoundException("User does not exist.", null);
        }
    }

    public boolean userExists(String email) throws Exception{
        User user = queryProcessor.getUserDetails(email);
        if (null!=user.getAccount_id()){
            return true;
        }else{
            return false;
        }
    }

    public Account getAccountBalance(String accountNo) throws Exception{
        Account account = queryProcessor.fetchAccountBalance(accountNo);
        if (null!=account.getAccountId()) {
            return account;
        }else{
            throw new UserNotFoundException("User does not exists. ", null);
        }
    }

    public void createUser(String email, String name, String password) throws Exception{

        boolean exists = userExists(email);
        if (!exists) {
            User userRequest = walletRequests.createUserRequest(email, name, password);
            queryProcessor.createUserAccount(userRequest);
        }else{
            throw new UserAlreadyExistsException("User already exists. ", null);
        }


    }
}
