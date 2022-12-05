package network.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import network.dao.ImageDao;
import network.model.Image;
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

@Path("/gallery")
public class ImagesResource {

    @Context
    private ContainerRequest request;

    @Path("/images")
    @Check
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response images(){
        try {
            List<Image> images = ImageDao.getImages();
            return Response.status(200).entity(images).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ServerError("SQL error")).build();
        }
    }

    @Path("/image")
    @Check
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response image(Image image){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        String strDate = dateFormat.format(date);
        try{
            System.out.println(image.getEncoding());
            ImageDao.addImage(getUsername(), image.getEncoding(), strDate);
            return Response.status(200).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ServerError("SQL error")).build();
        }
    }

    private String getUsername(){
        return TokenList.getUser(request.getRequestCookies().get("SESSION_ID").getValue());
    }
}
