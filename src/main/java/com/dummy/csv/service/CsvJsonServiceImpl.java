package com.dummy.csv.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dummy.shared.excetion.ConverterException;
import com.dummy.shared.excetion.ErrorMessages;

import static com.dummy.herlper.ConverterUtil.*;

@Service
public class CsvJsonServiceImpl implements CsvJsonService {

	@Override
	public StringBuilder convertToCsv(InputStream inputStream) throws ConverterException {
		StringBuilder csvBuilder = new StringBuilder();
		List<Character> charArrList = new ArrayList<>();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;

			while ((line = in.readLine()) != null) {
				charArrList.addAll(line.chars().mapToObj((i) -> Character.valueOf((char) i))
						 .filter(c -> !c.isWhitespace(c))
						.collect(Collectors.toList()));
			}
			csvBuilder = convertToCsvFromJson(charArrList);
		} catch(ConverterException exception) {
			throw new ConverterException(ErrorMessages.INVALID_INPUT.getErrorMessage());
		}
		catch (Exception e) {
			throw new ConverterException(ErrorMessages.INTERNAL_SERVER_ERROR.getErrorMessage());
		}
		return csvBuilder;
	}

	@Override
	public String convertToJson(InputStream inputStream) throws Exception {

 		List<String[]> strList = new ArrayList<>();
		String result=null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;

			while ((line = in.readLine()) != null) {
				strList.add(line.split(","));
			}
			result = convertToJsonFromCsv(strList);
		} catch(ConverterException exception) {
			throw new ConverterException(ErrorMessages.INVALID_INPUT.getErrorMessage());
		}
		catch (Exception e) {
			throw new ConverterException(ErrorMessages.INTERNAL_SERVER_ERROR.getErrorMessage());
		}
		return result;
	}

}
