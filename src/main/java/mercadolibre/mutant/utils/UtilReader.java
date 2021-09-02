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
