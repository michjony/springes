package com.mark.es.basic.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 
 * @author mqzhao
 * 约束验证器的实现需要扩展 JSR303 规范提供的接口 javax.validation.ConstraintValidator
 */
public class ListNotHasNullValidatorImpl implements ConstraintValidator<ListNotHasNull, List>{

	private int value;
	
	public ListNotHasNullValidatorImpl() {}

	/**
	 * 对验证器进行实例化 
	 */
	@Override
	public void initialize(ListNotHasNull constraintAnnotation) {
		this.value = constraintAnnotation.value();
	}

	/**
	 * 约束校验方法
	 */
	@Override
	public boolean isValid(List value, ConstraintValidatorContext context) {
        for (Object object : value) {
            if (object == null) {
                //如果List集合中含有Null元素，校验失败
                return false;
            }
        }
        return true;
	}

}
