package org.samples.rest.server;


import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.security.ProtectionDomain;

public class RestServer {
    private static Logger logger = LoggerFactory.getLogger(RestServer.class);
    public static void main(String[] args) {
        
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(2222);
        
        server.setConnectors(new Connector[]{connector});
        
        WebAppContext context = new WebAppContext();
        context.setServer(server);
        
        String webXmlPath = "web.xml";
        
        //String urlWebCtx = Thread.currentThread().getContextClassLoader().getResource(webXmlPath).toString();

        ProtectionDomain protectionDomain = RestServer.class
                .getProtectionDomain();
        URL location = protectionDomain.getCodeSource().getLocation();
        context.setWar(location.toExternalForm());
        
        //context.setDescriptor(urlWebCtx);
        //context.setContextPath("/");

        //context.setParentLoaderPriority(true);
        
        server.setHandler(context);
        
        while(true) {
            try {
                server.start();
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        try {
            System.in.read();
            server.stop();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(100);
        }
    }
}
