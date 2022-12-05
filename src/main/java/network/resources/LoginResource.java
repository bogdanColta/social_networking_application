package network.resources;

import java.sql.SQLException;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import network.dao.UserDao;
import network.model.ServerError;
import network.model.User;
import network.model.UserToken;
import org.glassfish.jersey.server.ContainerRequest;
import utils.TokenList;

@Path("/account")
public class LoginResource {

    @Context
    private ContainerRequest request;

    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(User input) {
        try {
            boolean authorised = UserDao.checkUser(input.getUsername(), input.getPassword());
            if (!authorised) {
                return Response.status(Response.Status.FORBIDDEN).entity(new ServerError("Wrong user credentials")).build();
            }
            UserToken ut = new UserToken(input.getUsername());
            TokenList.addToken(ut);
            return Response.status(Response.Status.OK).header("SET-COOKIE", "SESSION_ID=" + ut.getToken() + "; HttpOnly; Path=/;").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ServerError("SQL error")).build();
        }
    }

    @Path("/register")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(User input) {
        try {
            boolean registered = UserDao.registerUser(input.getUsername(), input.getPassword());
            if (registered) {
                return Response.status(Response.Status.OK).entity(input).build();
            } else {
                return Response.status(Response.Status.CONFLICT)
                        .entity(new ServerError("The user is already registered")).build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ServerError("SQL error")).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCredentials(User input) {
        try {
            UserDao.setUserInfo(input, getUsername());
            return Response.status(Response.Status.OK).build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ServerError("SQL error")).build();
        }
    }

    @DELETE
    public Response delete(){
        try{
            UserDao.deleteUser(getUsername());
            return Response.status(Response.Status.OK).build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ServerError("SQL error")).build();
        }
    }

    @Path("/logout")
    @GET
    public Response logout(){
        UserToken token = new UserToken(getUsername(), request.getRequestCookies().get("SESSION_ID").getValue());
        TokenList.removeToken(token);
        return Response.status(Response.Status.OK).build();
    }

    private String getUsername(){
        return TokenList.getUser(request.getRequestCookies().get("SESSION_ID").getValue());
    }
}

