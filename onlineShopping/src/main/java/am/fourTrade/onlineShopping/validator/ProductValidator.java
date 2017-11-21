package am.fourTrade.onlineShopping.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import am.fourTrade.shoppingBackend.dto.Product;
// In order to check whether an image has been selected for upload
public class ProductValidator implements Validator {
	
	//This will take care of checking whether we have Product instance only
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object targer, Errors errors) {
		
		//casting the type of the target to product
		Product product = (Product)targer;
		/*
		 * First Check
		 */
		//whether file has been selected or not
		if(product.getFile() == null || 
				product.getFile().getOriginalFilename().equals("")) {
			//since there is an error and there is no file available in selected section
			
			//we are writing it for "file" field in the Product.java
			//don't want to provide any error code so second argument will be null
			//The third argument is default message
			errors.rejectValue("file", null, "Please select an image file to upload");
			return;
		}
		
		/*
		 * Second Check
		 */
		// we need to make sure that we are uploading image only and not uploading any other file type
		if (!(product.getFile().getContentType().equals("image/jpeg")
				|| product.getFile().getContentType().equals("image/png")
				|| product.getFile().getContentType().equals("image/gif"))) {
						errors.rejectValue("file", null, "Please use only image file for upload!");
						return;
		}
	}
}
