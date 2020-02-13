package io.codementor.gtommee.rest_tutorial.models;

import org.springframework.data.annotation.Id;

public class Domain {
	@Id
	public Integer _id;

	public String domainName;
	public String hosting;
	public Integer total;
	
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public Integer getTotal() {
		return total;
	}	

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getHosting() {
		return hosting;
	}

	public void setHosting(String hosting) {
		this.hosting = hosting;
	}

}
