package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.service;

import com.sun.org.apache.xerces.internal.impl.xs.opti.SchemaParsingConfig;
import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.model.Task;
import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.proxy.ProxyFactory;
import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.worker.RoomClimateRegulator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@WebListener()
public class ContextListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    private static final String TASK_POOL = "TaskPool";


    // Public constructor is required by servlet spec
    public ContextListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */

        ServletContext sc = sce.getServletContext();
        Set<Task> tasks = Collections.synchronizedSet(new HashSet<Task>());
        sc.setAttribute(TASK_POOL, tasks);

        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        sc.setAttribute(ScheduledThreadPoolExecutor.class.getSimpleName(), executor);

        ScheduledFuture<?> future = executor.scheduleAtFixedRate(
                new RoomClimateRegulator(tasks, new ProxyFactory()), 0, 1, TimeUnit.MINUTES);
        sc.setAttribute(ScheduledFuture.class.getSimpleName(), future);
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */

        ServletContext sc = sce.getServletContext();

        ScheduledFuture<?> future = (ScheduledFuture<?>) sc.getAttribute(ScheduledFuture.class.getSimpleName());
        sc.removeAttribute(ScheduledFuture.class.getSimpleName());
        while (true) {
            if (future.cancel(false))
                break;
        }

        ScheduledThreadPoolExecutor executor =
                (ScheduledThreadPoolExecutor) sc.getAttribute(ScheduledThreadPoolExecutor.class.getSimpleName());
        sc.removeAttribute(ScheduledThreadPoolExecutor.class.getSimpleName());
        executor.shutdown();

        sc.removeAttribute(TASK_POOL);
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
      /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
      /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
