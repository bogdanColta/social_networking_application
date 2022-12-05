package network.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import network.dao.MessageDao;
import network.model.Message;
import network.model.ServerError;
import org.glassfish.jersey.server.ContainerRequest;
import utils.Check;
import utils.TokenList;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Path("/chat")
public class MessageResource {

    @Context
    private ContainerRequest request;

    @Path("/messages")

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response messages(){
        try{
            List<Message> messages = MessageDao.getMessages();
            return Response.status(200).entity(messages).build();
        } catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ServerError("SQL error")).build();
        }
    }

    @Path("message")
    @Check
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response message(Message message){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        String strDate = dateFormat.format(date);
        try{
            MessageDao.addMessage(getUsername(), strDate, message.getMessage());
            return Response.status(200).build();
        }catch (SQLException e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ServerError("SQL error")).build();
        }
    }

    private String getUsername(){
        return TokenList.getUser(request.getRequestCookies().get("SESSION_ID").getValue());
    }

}
