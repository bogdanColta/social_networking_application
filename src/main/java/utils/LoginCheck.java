package utils;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Check
@Provider
@Priority(Priorities.AUTHENTICATION)
public class LoginCheck implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String authorizationCookie;
        try {
            authorizationCookie = requestContext.getCookies().get("SESSION_ID").getValue();
        } catch (NullPointerException e) {
            abortWithUnauthorized(requestContext);
            return;
        }
        if (!validToken(authorizationCookie))
            abortWithUnauthorized(requestContext);
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
    }

    private boolean validToken(String token) {
        TokenList.print();
        if (!TokenList.isValidToken(token)) {
            return false;
        } else {
            System.out.println("User: " + TokenList.getUser(token) + " successfully validated their token");
            return true;
        }
    }
}
