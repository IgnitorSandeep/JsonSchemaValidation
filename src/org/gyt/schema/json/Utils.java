package org.gyt.schema.json;
/*
 * Copyright (c) 2014, Francis Galiegue (fgaliegue@gmail.com)
 *
 * This software is dual-licensed under:
 *
 * - the Lesser General Public License (LGPL) version 3.0 or, at your option, any
 *   later version;
 * - the Apache Software License (ASL) version 2.0.
 *
 * The text of this file and of both licenses is available at the root of this
 * project or, if you have the jar distribution, in directory META-INF/, under
 * the names LGPL-3.0.txt and ASL-2.0.txt respectively.
 *
 * Direct link to the sources:
 *
 * - LGPL 3.0: https://www.gnu.org/licenses/lgpl-3.0.txt
 * - ASL 2.0: http://www.apache.org/licenses/LICENSE-2.0.txt
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;

/**
 * Utility class for examples
 */
public final class Utils
{
	private static final String PKGBASE;

	static {
		final String pkgName = Utils.class.getPackage().getName();
		PKGBASE = '/' + pkgName.replace(".", "/");
	}

	private Utils()
	{
	}

	/**
	 * Load one resource from the current package as a {@link JsonNode}
	 *
	 * @param name name of the resource (<b>MUST</b> start with {@code /}
	 * @return a JSON document
	 * @throws IOException resource not found
	 */
	public static JsonNode loadResource(final String name)
			throws IOException
	{
		System.out.println("[RESOURCE] "+PKGBASE + name);
		return JsonLoader.fromResource(PKGBASE + name);
	}
	
	public static JsonNode loadResource(final File file)
		throws IOException
	{
		System.out.println("[FILE] "+file.getName());
		return JsonLoader.fromFile(file);
	}
	
	public static JsonNode loadResourceAsString(final String jsonContentAsString)
			throws IOException
	{
			System.out.println("[JSON] "+jsonContentAsString);
			return JsonLoader.fromString(jsonContentAsString);
	}

	public static String readFile(String path, Charset encoding) 
			throws IOException 
	{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	public static String readFile(String path) throws IOException
	{
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader( new FileReader (path));
		String         line = null;
		StringBuilder  stringBuilder = new StringBuilder();
		String         ls = System.getProperty("line.separator");

		try {
			while( ( line = reader.readLine() ) != null ) {
				stringBuilder.append( line );
				stringBuilder.append( ls );
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stringBuilder.toString();
	}

	public static JsonNode getJsonNode(String json) throws JsonProcessingException, IOException{


		ObjectMapper mapper = new ObjectMapper();

		JsonNode actualJsonNode = mapper.readTree(json);

		return actualJsonNode;

	}
	
	public static String getWorkingDir()
	{
		String workingDir = System.getProperty("user.dir");
		return workingDir;
	}
	public static String getCurrentDir()
	{
		final String pkgName = Utils.class.getPackage().getName();
		return Utils.getWorkingDir()+File.separator+"src"+File.separator+pkgName.replace(".", File.separator);
	}

}
