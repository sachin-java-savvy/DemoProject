package com.dummy.herlper;

import java.util.List;
import java.util.Stack;

public class ConverterUtil {
	public static StringBuilder convertToCsvFromJson(List<Character> charArrList) throws Exception {
		StringBuilder strBuilder = new StringBuilder();
		StringBuilder strBuilderHeader = new StringBuilder();
		StringBuilder str = new StringBuilder();
		boolean startOfJson = true;
		boolean headerStr = true;
		Stack<Character> operatorStack = new Stack<>();
		boolean fq = true;
		for (Character temp : charArrList) {

			if (startOfJson && !temp.equals(Character.valueOf('{'))) {
				throw new Exception("Error : Invalid Json");
			} else {
				startOfJson = false;
			}

			switch (temp) {
			case '{':
				operatorStack.push(temp);
				break;
			case '}':
				Character c = operatorStack.pop();
				if (!c.equals(Character.valueOf('{'))) {
					throw new Exception("Error : Invalid Json");
				}
				break;
			case '\"':
				if (fq) {
					operatorStack.push(temp);
					fq = false;
				} else {
					Character tempChar = operatorStack.pop();
					if (!tempChar.equals(Character.valueOf('\"'))) {
						throw new Exception("Error : Invalid Json");
					}
					fq = true;
				}
				break;
			case ':':
				strBuilderHeader.append(str);
				strBuilderHeader.append(",");
				str = new StringBuilder();
				break;
			case ',':
				strBuilder.append(str);
				strBuilder.append(",");
				str = new StringBuilder();
				break;
			default:
				str.append(temp);
			}
		}
		StringBuilder strTemp = new StringBuilder();
		strTemp.append(strBuilderHeader.substring(0, strBuilderHeader.length() - 1)).append("\n");
		strTemp.append(strBuilder).append(str);
		return strTemp;
	}

	public static String convertToJsonFromCsv(List<String[]> strArrList) throws Exception {
		StringBuilder strBuilder = new StringBuilder();
		boolean startOfCsv = true;
		for (String[] temp : strArrList) {

			if (startOfCsv) {
				strBuilder.append("{");
				startOfCsv = false;

			}
			strBuilder.append("\"").append(temp[0]).append("\"").append(":").append("\"").append(temp[1]).append("\"");
			strBuilder.append(",");

		}

		String finalString = null;
		if (!startOfCsv) {
			finalString = strBuilder.substring(0, strBuilder.length() - 1);
			finalString = finalString + "}";
		}
		return finalString;
	}
}
