package com.modis.acciaback;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.modis.acciaback.service.Calculator;

class CalculatorTest {

	   @Test
	    public void testAdd() {
	        Calculator calculator = new Calculator();
	        int result = calculator.add(2, 3);
	        Assertions.assertEquals(5, result);
	    }

	    @Test
	    public void testSubtract() {
	        Calculator calculator = new Calculator();
	        int result = calculator.subtract(5, 3);
	        Assertions.assertEquals(2, result);
	    }
}
