package com.mark.es.basic.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * 自定义参数校验注解
 * 校验List中是否有空值
 */
@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD,ElementType.FIELD}) //约束注解应用的目标元素类型
@Retention(RetentionPolicy.RUNTIME) //约束注解应用的时机
@Documented
@Constraint(validatedBy = ListNotHasNullValidatorImpl.class) //此处指定注解的实现类。与约束注解关联的验证器
public @interface ListNotHasNull {
	int value() default 0;
	
	String message() default "List not null exist";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payLoad() default {};
	
	@Target({ElementType.METHOD,ElementType.CONSTRUCTOR,ElementType.ANNOTATION_TYPE,ElementType.FIELD})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List{
		ListNotHasNull[] value();
	}
}
