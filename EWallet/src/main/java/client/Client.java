package client;

import business.AccountManager;
import dto.User;

public class Client {


    public static void main(String[] args) throws Exception{

        AccountManager accountManager = new AccountManager();

       // accountManager.createUser("testuser4@gmail.com", "Test", "abcd");
        User user = accountManager.getUserProfile("testuser4@gmail.com");
        accountManager.getAccountBalance(user.getAccount_id());
        //accountManager.getAccountBalance("accntb1fd988d-de8d-4a9b-9b09-db5256445e0e");


    }
}
