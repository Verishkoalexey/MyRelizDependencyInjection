package com.dependencyInjection.framework.example;

import com.dependencyInjection.framework.annotation.Inject;
import com.dependencyInjection.framework.example.service.CalculatorService;
import com.dependencyInjection.framework.example.service.TextFormatterService;

public class ConstructorInjectionExample1 {
    private CalculatorService calculatorService;
    private TextFormatterService textFormatterService;

    @Inject
    public ConstructorInjectionExample1(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @Inject
    public ConstructorInjectionExample1(CalculatorService calculatorService, TextFormatterService textFormatterService) {
        this.calculatorService = calculatorService;
        this.textFormatterService = textFormatterService;
    }

    public String processNumbers(int firstNumber, int secondNumber) {
        int number = calculatorService.calculate(firstNumber, secondNumber);
        return textFormatterService.format(String.valueOf(number));
    }
}
