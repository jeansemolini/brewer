package com.algaworks.brewer.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE }) //onde pode ser utilizado a anotação criada
@Retention(RetentionPolicy.RUNTIME) // vai rodar em tempo de execução
@Constraint(validatedBy = {}) // estou falando que uma classe pode ser passada para ser validada
@Pattern(regexp = "([a-zA-Z]{2}\\d{4})?") // validação a ser executada
public @interface SKU {
	
	@OverridesAttribute(constraint = Pattern.class, name = "message")
	//String message() default "SKU deve seguir o padrão XX9999"; // subscrevendo a mensagem padrão
	String message() default "{com.algaworks.constraints.SKU.message}"; // subscrevendo a mensagem padrão
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}
