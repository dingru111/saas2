package com.gta.train.platform.config.validator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.hibernate.validator.HibernateValidator;
 

public class ValidationUtils {
    
    /**
     * 使用hibernate的注解来进行验证
     * 
     */
    private static Validator validator = Validation
            .byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();

  
    /**
     * @description 
     * @author wbh
     * @date 2018年11月19日 下午1:48:34
     * @param obj 验证对象
     * @return 返回的验证信息。当返回list为空代表验证通过
     * List<String>
     */
    public static <T> List<String> validate(T obj) {
    	Set<String> result = new HashSet<>();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
        	   result.add(constraintViolation.getMessage());
		}
       return new ArrayList<String>(result);
    }
}