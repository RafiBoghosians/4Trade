package am.fourTrade.onlineShopping.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

//To handle the exception globally we annotate the class with @ControllerAdvice
//Every controller will take an advice of this particular class for any exception
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	//Handling 404 error
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "The page is not constructed!");

		mv.addObject("errorDescription", "The page you are looking for is not available now!");

		mv.addObject("title", "404 Error Page");

		return mv;
	}
	
	//Custom Exception(HTTP Status 500) which came from ProductNotFoundException for integer value
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductNotFoundException() {
		ModelAndView mv = new ModelAndView("error");

		mv.addObject("errorTitle", "Product is not available!");

		mv.addObject("errorDescription", "The page you are looking for is not available right now!");

		mv.addObject("title", "Product Unavailable");

		return mv;
	}
	
	//Custom Exception(HTTP Status 400) which came from ProductNotFoundException XARE value
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception ex) {
		ModelAndView mv = new ModelAndView("error");

		mv.addObject("errorTitle", "Contact Your Addministrator!");
		
		//For printing entire stack trace
		//This is only for debugging 
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		ex.printStackTrace(pw);

		mv.addObject("errorDescription", sw.toString());
		//mv.addObject("errorDescription", ex.toString());

		mv.addObject("title", "Error");

		return mv;
	}
}
