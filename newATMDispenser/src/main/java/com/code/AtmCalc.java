package com.code;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AtmCalc extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int[] denomination = { 500, 50 , 20 };
	static int[] denPresent = {10,10,10};
	static int[] count = { 0, 0, 0};
	static int totalNotes = 0;
	static int amount=0;
	

	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		int input = Integer.parseInt(req.getParameter("amount"));
		
		PrintWriter out = res.getWriter();
		if(input>500){
			out.println("Withdraw limit is 500");
			out.close();
		}else{
		
			String output = withdrawCash(input);
			out.println(output);
			out.close();
		}
		
	}
	
	public static String withdrawCash(int amount){
		
    	StringBuilder result = new StringBuilder();
	    for (int i = 0; i < denomination.length; i++) {
	                int noteCount = amount / denomination[i];
	                    count[i] = noteCount>=denPresent[i]?denPresent[i]:noteCount;
	                    denPresent[i] =  noteCount>=denPresent[i]?0:denPresent[i]- noteCount;
	                    amount = amount -(count[i]*denomination[i]);
	                    if(denomination[2]>amount && amount !=0){
	                    	return "ATM cannot dispense this amount, please choose other amount.";
	                    }
	    }
	    for (int i = 0; i < count.length; i++) {
	        if (count[i] != 0) {
	        	result.append(count[i] +" notes of $" +denomination[i] +" and ");
	        }
	    }
	   return result.substring(0, result.length() - 4);

	}

}
