/*-
 * =====LICENSE-START=====
 * Java 11 Application
 * ------
 * Copyright (C) 2020 - 2021 Organization Name
 * ------
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =====LICENSE-END=====
 */
package mercadolibre.mutant.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Builder;
import lombok.NoArgsConstructor;
import mercadolibre.mutant.verify.MutantVerify;

@Builder
@NoArgsConstructor
public class UtilReader {
	private static final Logger logger = Logger.getLogger(MutantVerify.class.getName());

	public static List<String> readDnaInfo(String filePath) throws IOException, NumberFormatException {
		List<String> lines = new ArrayList<String>();
		URL uri = ClassLoader.getSystemClassLoader().getResource(filePath);
		if (uri.getProtocol().equals("jar")) {
		  
		        InputStream input = ClassLoader.getSystemClassLoader().getResourceAsStream(filePath);
		        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
		            while (reader.ready()) {
		                String line = reader.readLine();
		                lines.add(line);
		            }
		        }catch (FileNotFoundException e) {
		        	logger.log(Level.SEVERE,e.getMessage());
		        } catch (IOException e) {
		            logger.log(Level.SEVERE,e.getMessage());
		        }
		    
		} else {
		    //this will probably work in your IDE, but not from a JAR
			try (Stream<String> stream = Files.lines(Paths.get(Paths.get(uri.toURI()).toString()))) {
				lines = stream.collect(Collectors.toList());
			} catch (URISyntaxException e) {
				logger.log(Level.SEVERE,e.getMessage());
			}
		}
		
		return lines;
	}

	

}
