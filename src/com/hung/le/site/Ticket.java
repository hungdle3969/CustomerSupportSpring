package com.hung.le.site;

import java.time.Instant;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Ticket {
	
	private long id;
	private String customerName;
	private String subject;
	private String body;
	private Map<String, Attachment> attachments = new LinkedHashMap<>();
	private Instant dateCreated;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public Instant getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Instant dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Collection<Attachment> getAttachments() {
		return this.attachments.values();
	}
	
	public Attachment getAttachment(String name){
		return this.attachments.get(name);
	}
	
	public void setAttachments(Map<String, Attachment> attachments) {
		this.attachments = attachments;
	}
	
	public void addAttachment(Attachment attachment){
		this.attachments.put(attachment.getName(), attachment);
	}
	
	public int getNumberOfAttachments(){
		return this.attachments.size();
	}

}
