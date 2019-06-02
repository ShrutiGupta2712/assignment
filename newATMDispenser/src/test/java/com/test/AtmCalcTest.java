package com.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.junit.Before;
import org.junit.Test;

import com.code.AtmCalc;


public class AtmCalcTest extends Mockito {
	
	String input ="amount";
	
	@Mock
	 HttpServletRequest request;
	 @Mock
	 HttpServletResponse response;
	 @Mock
	 HttpSession session;

	 @Mock
	 RequestDispatcher rd;
	 
	 @Before
	    public void setUp() {
		 MockitoAnnotations.initMocks(this);
	    }
		@Test
		public void test() throws IOException {


			when(request.getParameter(input)).thenReturn("320");
	 
	        StringWriter sw = new StringWriter();
	        PrintWriter pw = new PrintWriter(sw);
	         
	        when(response.getWriter()).thenReturn(pw);
	 
	        AtmCalc myServlet =new AtmCalc();
	        myServlet.service(request, response);
	        String result = sw.getBuffer().toString().trim();
	        String exp = "6 notes of $50 and 1 notes of $20";
	        assertEquals(result, exp);
		 }
		@Test
		public void test1() throws IOException {


			when(request.getParameter(input)).thenReturn("90");
	 
	        StringWriter sw = new StringWriter();
	        PrintWriter pw = new PrintWriter(sw);
	         
	        when(response.getWriter()).thenReturn(pw);
	 
	        AtmCalc myServlet =new AtmCalc();
	        myServlet.service(request, response);
	        String result = sw.getBuffer().toString().trim();
	        String exp = "1 notes of $50 and 2 notes of $20";
	        assertEquals(result, exp);
		 }
		
		@Test
		public void test2() throws IOException {


			when(request.getParameter(input)).thenReturn("130");
	 
	        StringWriter sw = new StringWriter();
	        PrintWriter pw = new PrintWriter(sw);
	         
	        when(response.getWriter()).thenReturn(pw);
	 
	        AtmCalc myServlet =new AtmCalc();
	        myServlet.service(request, response);
	        String result = sw.getBuffer().toString().trim();
	        String exp = "ATM cannot dispense this amount, please choose other amount.";
	        assertEquals(result, exp);
		 }
	    
}
