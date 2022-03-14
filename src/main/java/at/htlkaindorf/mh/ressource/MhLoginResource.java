package at.htlkaindorf.mh.ressource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import jakarta.ws.rs.Path;

/**
 *
 */

@Path("/login")
public class MhLoginResource {
    public static final String KEY = "my-jwt-secret-is-not-long-enough";




}
