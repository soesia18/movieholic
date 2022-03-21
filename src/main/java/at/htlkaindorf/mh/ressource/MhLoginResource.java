package at.htlkaindorf.mh.ressource;

import at.htlkaindorf.mh.beans.User;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    public Response login(User user) {
        try {
            if (user.getEmail().equals("admin") && user.getPassword().equals("admin")){
                ObjectMapper om = new ObjectMapper();
                String u = om.writeValueAsString(user);
                String jwt = createJWT(u);
                return Response.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
