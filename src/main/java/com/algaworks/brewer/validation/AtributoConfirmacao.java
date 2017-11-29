package com.algaworks.brewer.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

import com.algaworks.brewer.validation.validator.AtributoConfirmacaoValidator;

@Target({ ElementType.TYPE }) //onde pode ser utilizado a anotação criada
@Retention(RetentionPolicy.RUNTIME) // vai rodar em tempo de execução
@Constraint(validatedBy = { AtributoConfirmacaoValidator.class }) // estou falando que uma classe pode ser passada para ser validada
public @interface AtributoConfirmacao {
	
	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "Atributos não conferem"; // subscrevendo a mensagem padrão
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	String atributo();
	String atributoConfirmacao();
}
