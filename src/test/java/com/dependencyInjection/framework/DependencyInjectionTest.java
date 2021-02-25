package com.dependencyInjection.framework;

import com.dependencyInjection.framework.example.ConstructorInjectionExample;
import com.dependencyInjection.framework.example.ConstructorInjectionExample1;
import com.dependencyInjection.framework.example.ConstructorInjectionExample2;
import com.dependencyInjection.framework.example.service.CalculatorService;
import com.dependencyInjection.framework.example.service.TextFormatterService;
import com.dependencyInjection.framework.example.service.impl.SimpleTextFormatterService;
import com.dependencyInjection.framework.example.service.impl.SubtractionCalculatorService;
import com.dependencyInjection.framework.exeptions.ConstructorNotFoundException;
import com.dependencyInjection.framework.exeptions.TooManyConstructorsException;
import com.dependencyInjection.framework.module.impl.InjectorImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;


public class DependencyInjectionTest {

	@Rule
	public ExpectedException thrown= ExpectedException.none();

	@Test
	public void test1() throws Exception {
		BeanFactory ownDi = DependencyInjection.getBeanFactory(new DependencyInjectionConfigExample1());
		ConstructorInjectionExample example = (ConstructorInjectionExample) ownDi.inject(ConstructorInjectionExample.class);
		
		String processNumbers = example.processNumbers(3, 2);
		
		assertEquals("Simple text: 1", processNumbers);
	}

	@Test
	public void testTooManyConstructorsException() throws Exception {

		BeanFactory ownDi = DependencyInjection.getBeanFactory(new DependencyInjectionConfigExample1());
		Object example =  ownDi.inject(ConstructorInjectionExample1.class);
		thrown.expect(TooManyConstructorsException.class);
		throw new TooManyConstructorsException();
	}

	@Test
	public void testConstructorNotFoundException() throws Exception {
		BeanFactory ownDi = DependencyInjection.getBeanFactory(new DependencyInjectionConfigExample1());
		Object example = ownDi.inject(ConstructorInjectionExample2.class);
		thrown.expect(ConstructorNotFoundException.class);
		throw new ConstructorNotFoundException();
	}

	private class DependencyInjectionConfigExample1 extends InjectorImpl {
		
		@Override
		public void configure() {
			bind(CalculatorService.class, SubtractionCalculatorService.class);
			bind(TextFormatterService.class, SimpleTextFormatterService.class);
		}
	}

}
