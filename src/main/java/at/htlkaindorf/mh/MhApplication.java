package at.htlkaindorf.mh;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * <b>Movieholic</b><br><br>
 * API Application class for the REST API
 * @author Simon, David
 * @version 1.0
 */

@WebListener
@ApplicationPath("/api")
public class MhApplication extends Application implements ServletContextListener {

    /**
     * Start method for the application
     * @param sce {@link ServletContextListener}
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }
}