package com.dependencyInjection.framework.example;

import com.dependencyInjection.framework.annotation.Inject;
import com.dependencyInjection.framework.example.service.CalculatorService;
import com.dependencyInjection.framework.example.service.TextFormatterService;

public class ConstructorInjectionExample2 {
        private CalculatorService calculatorService;
        private TextFormatterService textFormatterService;


        public String processNumbers(int firstNumber, int secondNumber) {
            int number = calculatorService.calculate(firstNumber, secondNumber);
            return textFormatterService.format(String.valueOf(number));
        }
}
