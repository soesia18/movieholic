package at.htlkaindorf.mh.resource;

import at.htlkaindorf.mh.beans.User;
import at.htlkaindorf.mh.database.UserDB;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 */

@Path("/login")
public class MhLoginResource {
    public static final String KEY = "my-jwt-secret-is-not-long-enough";

    public String createJWT(String payload) throws JOSEException {
        JWSObject object = new JWSObject(new JWSHeader(JWSAlgorithm.HS256),
                new Payload(payload));
        object.sign(new MACSigner(KEY.getBytes()));

        return object.serialize();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response login(User user) {
        try {
            User u = UserDB.getInstance().login(user);
            ObjectMapper om = new ObjectMapper();
            String uStr = om.writeValueAsString(u.getRights());
            String jwt = createJWT(uStr);
            return Response.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response register(User user) {
        try {
            User u = UserDB.getInstance().register(user);

            ObjectMapper om = new ObjectMapper();
            String uStr = om.writeValueAsString(u.getRights());
            String jwt = createJWT(uStr);

            return Response.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}