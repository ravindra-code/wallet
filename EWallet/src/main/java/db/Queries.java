package db;

public class Queries {

    public static final String GET_ACCOUNT_BALANCE = "Select account_id, account_balance from ACCOUNT where account_id = ?";
    public static final String GET_PASSBOOK = "select from_account, to_account, transact_amount, transact_type" +
            "balance_remaining, transact_date from transaction where" +
            "from_account=? OR to_account=?" +
            " order by date desc";
    public static final String GET_USER_PROFILE = "select name, email, account_id, created_on from users where email = ?";
    public static final String GET_TRANSACTION_STATUS = "select transaction_id, status from status" +
            "where transaction_id = ?";
    public static final String GET_MINI_STATEMENT = "select TOP 5 from transaction t" +
            "where t.from_account = ? OR to_account = ? order by date desc";
    public static final String GET_TRANSACTION_DETAILS = "select t.from_account, t.to_account, t.transact_amount, " +
            "t.transact_type, t.transact_date, s.status " +
            "from transaction t where id = ?"+
            "inner join status s on s.transaction_id = t.id";

    public static final String ADD_MONEY = "UPDATE ACCOUNT SET account_balance = account_balance + ? where account_id=?";
    public static final String CREATE_TRANSACTION = "Insert into transaction (ID, TRANSACT_AMOUNT, TRANSACT_TYPE, " +
            "BALANCE_REMAINING, TRANSACT_DATE, FROM_ACCOUNT, TO_ACCOUNT, DETAILS) values (?,?,?,?,?,?,?,?)";
    public static final String CREATE_USER = "Insert into users (ID, NAME, EMAIL, USERNAME, CREATED_ON, PASSWORD, ACCOUNT_ID)values (?,?,?,?,?,?,?)";
    public static final String INITIATE_ACCOUNT = "Insert into ACCOUNT (ID, ACCOUNT_ID, ACCOUNT_BALANCE) " +
            "values (?,?,?)";


}
