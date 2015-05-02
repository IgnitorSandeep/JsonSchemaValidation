package org.gyt.schema.json;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
/**
 * This class provides utility to validate the json against given schema.
 * 
 * @author sandeep
 *
 */
public class Validator {

	public boolean validateAsResource(String actualResourcePath,String schemaResourcePath) throws ProcessingException, IOException
	{
		final JsonNode schemaNode = Utils.loadResource(actualResourcePath);
		final JsonNode actualNode = Utils.loadResource(schemaResourcePath);

		final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
		final JsonSchema schema = factory.getJsonSchema(schemaNode);

		ProcessingReport report;
		report = schema.validate(actualNode);

		return report.isSuccess();
	}

	public boolean validateAsFile(File resourceFile,File schemaFile) throws ProcessingException, IOException
	{
		final JsonNode jsonNode = Utils.loadResource(resourceFile);
		final JsonNode schemaNode = Utils.loadResource(schemaFile);

		final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
		final JsonSchema schema = factory.getJsonSchema(schemaNode);

		ProcessingReport report;
		report = schema.validate(jsonNode);

		return report.isSuccess();
	}

	public boolean validateAsString(String actualJsonContent,String jsonSchema) throws JsonProcessingException, IOException, ProcessingException{

		JsonNode actualJsonNode = Utils.loadResourceAsString(actualJsonContent);
		JsonNode jsonScemaNode =  Utils.loadResourceAsString(jsonSchema);

		final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
		final JsonSchema schema = factory.getJsonSchema(jsonScemaNode);

		ProcessingReport report;
		report = schema.validate(actualJsonNode);

		return report.isSuccess();
	}
}
