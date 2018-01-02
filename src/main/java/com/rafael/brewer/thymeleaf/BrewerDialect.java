package com.rafael.brewer.thymeleaf;

import java.util.HashSet;
import java.util.Set;
import com.rafael.brewer.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.rafael.brewer.thymeleaf.processor.MessageElementTagProcessor;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

public class BrewerDialect extends AbstractProcessorDialect{

	public BrewerDialect() {
		super("Rafael Brewer", "brewer", StandardDialect.PROCESSOR_PRECEDENCE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		// TODO Auto-generated method stub
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		System.out.println("Prefixo do Dialeto "+dialectPrefix);
		return processadores;
	}

}
