package com.dummy.csv.service;

import java.io.InputStream;

import org.springframework.stereotype.Service;

@Service
public interface CsvJsonService {

		
	public StringBuilder convertToCsv(InputStream inputStream) throws Exception;
	
	public String convertToJson(InputStream inputStream) throws Exception;
	
}
