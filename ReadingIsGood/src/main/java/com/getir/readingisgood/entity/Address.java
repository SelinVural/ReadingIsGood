package com.getir.readingisgood.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Address {

	@Id
    @GeneratedValue
    private Long id;
	
	private AddressType type;
	
    private String postcode;
    
    private String title;
    
    private String body;
          
    @CreationTimestamp
    private LocalDateTime createDateTime;

	public Address() {
		super();
	}

	public Address(Long id, AddressType type, String postcode, String title, String body,
			LocalDateTime createDateTime) {
		super();
		this.id = id;
		this.type = type;
		this.postcode = postcode;
		this.title = title;
		this.body = body;
		this.createDateTime = createDateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AddressType getType() {
		return type;
	}

	public void setType(AddressType type) {
		this.type = type;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", type=" + type + ", postcode=" + postcode + ", title=" + title + ", body=" + body
				+ ", createDateTime=" + createDateTime + "]";
	}
    
    
    

}
