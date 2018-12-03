package com.mycompany.mywebapp.client;

import com.mycompany.mywebapp.shared.FieldVerifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.microsoft.test.datacontroll;
import org.microsoft.test.test;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MyWebApp2 implements EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
	private String textfield=null;
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  private static class Contact {
      private final String address;
      private final String birthday;
      private final String name;
      private final String little;

      public Contact(String name, String birthday, String address,String little) {
   
         this.name = name;
         this.birthday = birthday;
         this.address = address;
         this.little=little;
      }
      public List<Contact> readdb(){
    	  String bugs="";
    	  String messagetails="";
    	  String solutions="";
    	  List<Contact> rowlist=new ArrayList<Contact>();
    	  datacontroll demo=new test();
    	    try {
    		bugs=	demo.readProperty("data.properties", "bug");
    		messagetails=	demo.readProperty("data.properties", "messagetail");
    		solutions=demo.readProperty("data.properties", "solution");
    		for(int i=0;i<bugs.split(",").length;i++){
    			rowlist.add( new Contact(bugs.split(",")[i],messagetails.split(",")[i],solutions.split(",")[i],"text"));
    		}
    		} catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
			return rowlist;
      }
   }
  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
  
  /**
   * This is the entry point method.
   */
  private VerticalPanel panel2 = new VerticalPanel();
  public void onModuleLoad() {
//	File file=new File("ok.properties");
	  
	  
    final Button sendButton = new Button("Send");
    final TextBox nameField = new TextBox();
    nameField.setText("GWT User");
    final Label errorLabel = new Label();
    HTML htm=new HTML();
   htm.setHTML("<div>111</div>");
    // We can add style names to widgets
    sendButton.addStyleName("sendButton");
    CellTable<Contact> table = new CellTable<Contact>();
    table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

    // Add a text column to show the name.
    TextColumn<Contact> nameColumn = 
    new TextColumn<Contact>() {
       @Override
       public String getValue(Contact object) {
          return object.name;
       }
    };
    table.addColumn(nameColumn, "bug");

    // Add a date column to show the birthday.
   /* Cell<String> dateCell = new Cell<String>();
    Column<Contact, String> dateColumn 
    = new Column<Contact, String>(dateCell) {
       @Override
       public String getValue(Contact object) {
          return object.birthday;
       }
    };*/
    TextColumn<Contact> nameColumn2 = 
    	    new TextColumn<Contact>() {
    	       @Override
    	       public String getValue(Contact object) {
    	          return object.birthday;
    	       }
    	    };
    table.addColumn(nameColumn2, "详细信息+-+"
    		+ "");

    // Add a text column to show the address.
    TextColumn<Contact> addressColumn 
    = new TextColumn<Contact>() {
       @Override
       public String getValue(Contact object) {
          return object.address;
       }
    };
    table.addColumn(addressColumn, "解决方案");
    TextColumn<Contact> littleColumn 
    = new TextColumn<Contact>() {
       @Override
       public String getValue(Contact object) {
          return object.little;
       }
    };
    Contact con=new Contact("","","","");
    // Set the total row count. This isn't strictly necessary,
    // but it affects paging calculations, so its good habit to
    // keep the row count up to date.
    table.setRowCount(con.readdb().size(), true);

    // Push the data into the widget.
    table.setRowData(0, con.readdb());

   // VerticalPanel panel = new VerticalPanel();
    panel2.setBorderWidth(1);	    
    panel2.setWidth("400");
    panel2.add(table);

    // Add the widgets to the root panel.
    RootPanel.get("tabledata").add(panel2);

    // Add the nameField and sendButton to the RootPanel
    // Use RootPanel.get() to get the entire body element
    RootPanel.get("nameFieldContainer").add(nameField);
    RootPanel.get("sendButtonContainer").add(sendButton);
    RootPanel.get("errorLabelContainer").add(errorLabel);
    RootPanel.get("htmltext").add(htm);

    // Focus the cursor on the name field when the app loads
    nameField.setFocus(true);
    nameField.selectAll();

    // Create the popup dialog box
    final DialogBox dialogBox = new DialogBox();
    dialogBox.setText("Remote Procedure Call");
    dialogBox.setAnimationEnabled(true);
    final Button closeButton = new Button("Close");
    // We can set the id of a widget by accessing its Element
    closeButton.getElement().setId("closeButton");
    final Label textToServerLabel = new Label();
    final HTML serverResponseLabel = new HTML();
    VerticalPanel dialogVPanel = new VerticalPanel();
    dialogVPanel.addStyleName("dialogVPanel");
    dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
    dialogVPanel.add(textToServerLabel);
    textfield=textToServerLabel.getText();
    dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
    dialogVPanel.add(serverResponseLabel);
    dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
    dialogVPanel.add(closeButton);
    dialogBox.setWidget(dialogVPanel);

    // Add a handler to close the DialogBox
    closeButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        dialogBox.hide();
        sendButton.setEnabled(true);
        sendButton.setFocus(true);
      }
    });

    // Create a handler for the sendButton and nameField
    class MyHandler implements ClickHandler, KeyUpHandler {
      /**
       * Fired when the user clicks on the sendButton.
       */
      public void onClick(ClickEvent event) {
        sendNameToServer();
      }

      /**
       * Fired when the user types in the nameField.
       */
      public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
          sendNameToServer();
        }
      }

      /**
       * Send the name from the nameField to the server and wait for a response.
       */
      private void sendNameToServer() {
        // First, we validate the input.
        errorLabel.setText("");
        String textToServer = nameField.getText();
        if (!FieldVerifier.isValidName(textToServer)) {
          errorLabel.setText("Please enter at least four characters");
          return;
        }
        
        // Then, we send the input to the server.
        sendButton.setEnabled(false);
        textToServerLabel.setText(textToServer);
        serverResponseLabel.setText("");
        greetingService.greetServer(textToServer, new AsyncCallback<String>() {
          public void onFailure(Throwable caught) {
            // Show the RPC error message to the user
            dialogBox.setText("Remote Procedure Call - Failure");
            serverResponseLabel.addStyleName("serverResponseLabelError");
            serverResponseLabel.setHTML(SERVER_ERROR);
            dialogBox.center();
            closeButton.setFocus(true);
          }

          public void onSuccess(String result) {
        	//  result.
        	  HTML htm=new HTML();
        	   htm.setHTML("<div>"+nameField.getText()+"</div>");
        	   RootPanel.get("htmltext").add(htm);
            dialogBox.setText("Remote Procedure Call");
            serverResponseLabel.removeStyleName("serverResponseLabelError");
            serverResponseLabel.setHTML(result);
            dialogBox.center();
            closeButton.setFocus(true);
          }
        });
      }
    }

    // Add a handler to send the name to the server
    MyHandler handler = new MyHandler();
    sendButton.addClickHandler(handler);
    nameField.addKeyUpHandler(handler);
  }
}
