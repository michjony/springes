package com.mark.es.model;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName="user",indexStoreType="fs",type="info",shards=5,replicas=1,refreshInterval="-1")
public class User {
	private Long id; 
	private String name;
	private int age ;
	private String school;
	private String occupation;
	
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", school=" + school + ", occupation="
				+ occupation + "]";
	}

}
