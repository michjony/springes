package com.mark.es.basic.validator;

import java.io.ByteArrayInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidatorUtil {
	// java 注解校验
	public static <T> String validatorByAnnotation(T t) {
		return validate(t);
	}

	/**
	 * 对对象的属性进行校验
	 * 
	 * @param object
	 * @param propertyName
	 * @param groups
	 * @return
	 */
	public static <T> String validateProperty(T object, String propertyName, Class<?>... groups) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<T>> constraintViolations = validator.validateProperty(object, propertyName, groups);
		StringBuilder sb = new StringBuilder();
		if (constraintViolations != null && !constraintViolations.isEmpty()) {
			Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
			while (iterator.hasNext()) {
				ConstraintViolation<T> next = iterator.next();
				sb.append("字段").append(next.getPropertyPath()).append(":");
				sb.append(next.getMessage()).append("\n");
			}
		}
		return sb.toString();
	}

	/**
	 * 验证对象
	 * 
	 * @param object
	 * @param groups
	 * @return
	 */
	public static <T> String validate(T object, Class<?>... groups) {
		StringBuilder sb = new StringBuilder();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(object, groups);
		if (constraintViolations != null && !constraintViolations.isEmpty()) {
			for (Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator(); iterator
					.hasNext();) {
				ConstraintViolation<T> next = iterator.next();
				sb.append("字段").append(next.getPropertyPath()).append(":");
				sb.append(next.getMessage()).append("\n");
			}
		}
		return sb.toString();
	}

	/**
	 * 使用xml文件的字节流校验文件
	 * @param bytes
	 * @param list
	 * @return
	 */
	public static <T> String validateByXml(byte[] bytes, List<T> list) {
		StringBuilder sb = new StringBuilder();
		if (list == null || bytes == null) {
			return sb.toString();
		}
		
		Configuration<?> configuration = Validation.byDefaultProvider().configure();
		configuration.addMapping(new ByteArrayInputStream(bytes));
		ValidatorFactory factory = configuration.buildValidatorFactory();
	    Validator validator = factory.getValidator();
		
		for (T t : list) {
			Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
			if(null!=constraintViolations && !constraintViolations.isEmpty()){
				Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
				while(iterator.hasNext()){
					ConstraintViolation<T> next = iterator.next();
					sb.append("class:[").append(t.getClass()).append("]").append("---")
					.append("字段").append(next.getPropertyPath()).append(":").append(next.getMessage()).append("\n");
				}
			}
		}
		factory.close();
		return sb.toString();
		
		
	}

	public static void main(String[] args) {
		UserVO vo = new UserVO();
		System.out.println(validatorByAnnotation(vo));
		System.out.println("===========");
		System.out.println(validateProperty(vo, "pastDate"));
	}
}
