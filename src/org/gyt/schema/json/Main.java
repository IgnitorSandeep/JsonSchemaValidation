package org.gyt.schema.json;

import java.io.File;
import java.io.IOException;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
/**
 * this is a driver class to verify {@link Validator} class method.
 * @author sandeep
 */
public class Main {

	public static void main(String[] args) throws ProcessingException, IOException {
		
		System.out.println("Validating Using Resourse Path");
		System.out.println("++++++++++++++++++++++++++++++");
		Validator validator = new Validator();
		boolean isValid = validator.validateAsResource("/schema.json", "/actual.json");
		System.out.println("[RESULT] "+"isValid : "+isValid);
		System.out.println();
		
		System.out.println("Validating Using File");
		System.out.println("+++++++++++++++++++++");
		File jsonFile = new File(Utils.getCurrentDir()+File.separator+"actual.json");
		File schemaFile = new File(Utils.getCurrentDir()+File.separator+"schema.json");
		isValid = validator.validateAsFile(jsonFile, schemaFile);
		System.out.println("[RESULT] "+"isValid : "+isValid);
		System.out.println();
		
		System.out.println("Validating Using Json Content As String");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		String actualJsonContent = Utils.readFile("F:\\Workspace\\JsonShemaValidation\\src\\org\\gyt\\schema\\json\\actual.json");
		String jsonSchema =Utils.readFile("F:\\Workspace\\JsonShemaValidation\\src\\org\\gyt\\schema\\json\\schema.json") ;
		System.out.println("Actual Json Content :");
		System.out.println("*********************");
		System.out.println(actualJsonContent);
		System.out.println("Json Schema Content :");
		System.out.println("*********************");
		System.out.println(jsonSchema);
		isValid = validator.validateAsString(actualJsonContent, jsonSchema);
		System.out.println("[RESULT] "+"isValid : "+isValid);
		
		
	}

}
