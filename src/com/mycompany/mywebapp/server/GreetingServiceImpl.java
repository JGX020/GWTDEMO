package com.mycompany.mywebapp.server;

import com.mycompany.mywebapp.client.GreetingService;
import com.mycompany.mywebapp.shared.FieldVerifier;

import java.io.IOException;

import org.microsoft.test.datacontroll;
import org.microsoft.test.test;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
    GreetingService {

  public String greetServer(String input) throws IllegalArgumentException, IOException {
    // Verify that the input is valid. 
    if (!FieldVerifier.isValidName(input)) {
      // If the input is not valid, throw an IllegalArgumentException back to
      // the client.
      throw new IllegalArgumentException(
          "Name must be at least 4 characters long");
    }

    String serverInfo = getServletContext().getServerInfo();
    String userAgent = getThreadLocalRequest().getHeader("User-Agent");

    // Escape data from the client to avoid cross-site script vulnerabilities.
    input = escapeHtml(input);
    userAgent = escapeHtml(userAgent);
    datacontroll text=new test();
    if(text.readProperty("topic.properties","content")==null){
    text.createFile("topic.properties");
    text.writeProperty("topic.properties", "content", input);}
    else{
    	text.writeProperty("topic.properties", "content",text.readProperty("topic.properties","content")+","+ input);
    }
    return "Hello, " + input + "!<br><br>I am running " + serverInfo
        + ".<br><br>It looks like you are using:<br>" + userAgent;
  }

  /**
   * Escape an html string. Escaping data received from the client helps to
   * prevent cross-site script vulnerabilities.
   * 
   * @param html the html string to escape
   * @return the escaped string
   */
  private String escapeHtml(String html) {
    if (html == null) {
      return null;
    }
    return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
        ">", "&gt;");
  }
}
