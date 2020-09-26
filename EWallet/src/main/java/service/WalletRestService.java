package service;

import dto.Account;
import dto.User;

import javax.ws.rs.*;
import java.math.BigDecimal;

@Path("/wallet")
public interface WalletRestService {

    @GET
    @Path("/accountBalance/{accountId}")
    @Produces("application/json")
    public Account getAccountBalance(@PathParam("accountId") String accountId) throws Exception;

    @GET
    @Path("/transactionStatus/{transactionId}")
    @Produces("application/json")
    public String getTransactionStatus(@PathParam("transactionId") String transactionId) throws Exception;

    @POST
    @Path("/createUser")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public void createUserProfile(User user) throws Exception;

    @POST
    @Path("/addMoney/{email}/{amount}")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public void addMoneyToAccount(@PathParam("email") String email, @PathParam("amount") BigDecimal amount) throws Exception;


    @POST
    @Path("/transferMoney/{from}/{to}/{amount}")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public void transferFunds(@PathParam("from") String from,
                              @PathParam("to") String to,
            @PathParam("amount") BigDecimal amount) throws Exception;



}
