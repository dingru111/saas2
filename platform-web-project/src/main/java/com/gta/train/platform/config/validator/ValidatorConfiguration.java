package com.gta.train.platform.config.validator;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;

 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
 

//@Configuration
public class ValidatorConfiguration {
    //@Bean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .addProperty( "hibernate.validator.fail_fast", "true" )
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return validator;
    } 
  /*  @Bean
    public Validator getValidatorFactory(){
     
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class).configure().failFast(true).buildValidatorFactory();
        return validatorFactory.getValidator();
    }
 */
}