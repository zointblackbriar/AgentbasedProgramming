package de.tudresden.agent.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RuntimeSampleAnn {
	//Test param
	String name();
	String desc();
}
