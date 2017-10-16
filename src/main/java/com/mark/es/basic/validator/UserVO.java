package com.mark.es.basic.validator;

import java.util.Date;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

public class UserVO {

	public UserVO() {}
	@NotNull
	private String name;
	//@Size(min = 0, max = 100)
	private int age ;
	@AssertTrue
	private boolean flag;
	@AssertFalse
	private boolean noflag;
	@NotNull
	@Future
	private Date futureDate;
	@Past
	@NotNull
	private Date pastDate;
}
