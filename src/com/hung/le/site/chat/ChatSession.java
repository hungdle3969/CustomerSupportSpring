/**
 * 
 */
package com.hung.le.site.chat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


/**
 * @author admin
 * Server endpoint uses this ChatSession to associate a user requesting a chat with 
 * the support representative who responds. The chat session includes the opening message
 * and a log of all messages sent during the chat.
 */
public class ChatSession {
	
	private static final Logger log = LogManager.getLogger();

	private long sessionId;
    private String customerUsername;
    private Session customer;
    private String representativeUsername;
    private Session representative;
    private ChatMessage creationMessage;
    private final List<ChatMessage> chatLog = new ArrayList<>();

    public long getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(long sessionId)
    {
        this.sessionId = sessionId;
    }

    public String getCustomerUsername()
    {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername)
    {
        this.customerUsername = customerUsername;
    }

    public Session getCustomer()
    {
        return customer;
    }

    public void setCustomer(Session customer)
    {
        this.customer = customer;
    }

    public String getRepresentativeUsername()
    {
        return representativeUsername;
    }

    public void setRepresentativeUsername(String representativeUsername)
    {
        this.representativeUsername = representativeUsername;
    }

    public Session getRepresentative()
    {
        return representative;
    }

    public void setRepresentative(Session representative)
    {
        this.representative = representative;
    }

    public ChatMessage getCreationMessage()
    {
        return creationMessage;
    }

    public void setCreationMessage(ChatMessage creationMessage)
    {
        this.creationMessage = creationMessage;
    }

    @JsonIgnore
    public void log(ChatMessage message)
    {
        log.trace("Chat message logged for session {}.", this.sessionId);
        this.chatLog.add(message);
    }
	
	@JsonIgnore
	public void writeChatLog(File file) throws IOException{
		
		log.debug("Writing chat log to file {}.", file);
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		
		//use try with resource statement to ensure that the resource is closed at the end of the statement
		try(FileOutputStream stream = new FileOutputStream(file)){
			mapper.writeValue(stream, this.chatLog);
		}
		log.exit();
	}
}
