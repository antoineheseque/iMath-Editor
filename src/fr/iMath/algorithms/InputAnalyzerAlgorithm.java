package fr.iMath.algorithms;

import fr.iMath.objects.EquationObjectData;
import fr.iMath.objects.EquationObjectType;
import fr.iMath.objects.Operator;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Input Analyser Algorithm
 * @author JEANNIN Louis
 *
 */
public class InputAnalyzerAlgorithm {
	// Convert StringInput to a list of Numbers and Operations here ..
    private SortedMap<Integer, EquationObjectData> list = new TreeMap<>();
    //private List<Integer> indexArray = new ArrayList<>();

    /**
     * Analyse.
     * @param function String
     * @return List of EquationObjectData
     */
    public List<EquationObjectData> analyse(String function){
		System.out.println("Starting the analysis...");
        matchesConstant(function,"[1-9]*");
        matchesOperator(function,"[()+\\/*\\-^~]");
        matchesChar(function,"[a-z]*");
		System.out.println("Analysis finished.");
        return getList(list);
    }

    /**
     * Update EquationObjectData list with functions and variables
     * @param text String
     * @param regex String
     */
    public void matchesChar(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            if(matcher.group().length() != 0) {
                Pattern patternFunction = Pattern.compile("(ln|exp|sqrt|sinc|sin|cos|tan|pi)");
                Matcher matcherFunction = patternFunction.matcher(matcher.group());
                if(matcherFunction.find()){
                    System.out.println("Function : " + matcherFunction.group() + " -> Not implemented yet.");
                }
                else{
                    Pattern patternVariable = Pattern.compile("[a-z]");
                    Matcher matcherVariable = patternVariable.matcher(matcher.group());
                    while (matcherVariable.find()) {
                        list.put(matcher.start()+matcherVariable.start(), new EquationObjectData(matcherVariable.group()));
                    }
                }
            }
        }
    }

    /**
     * Update EquationObjectData list with constants
     * @param text String
     * @param regex String
     */
    public void matchesConstant(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            if(matcher.group().length() != 0){
                list.put(matcher.start(), new EquationObjectData(Float.parseFloat(matcher.group())));
            }
        }
    }

    /**
     * Update  EquationObjectData list with operators
     * @param text String
     * @param regex String
     */
    public void matchesOperator(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        Operator operator;
        while (matcher.find()) {
            if(matcher.group().length() != 0){

                switch(matcher.group()){
                    case "+":
                        operator = Operator.PLUS;
                        break;
                    case "-":
                        operator = Operator.MINUS;
                        break;
                    case "*":
                        operator = Operator.MULTIPLY;
                        break;
                    case "/":
                        operator = Operator.DIVIDE;
                        break;
                    case "^":
                        operator = Operator.POWER;
                        break;
                    case "~":
                        operator = Operator.NEGATE;
                        break;
                    case "(":
                        operator = Operator.LEFTPARENTHESIS;
                        break;
                    case ")":
                        operator = Operator.RIGHTPARENTHESIS;
                        break;
                    default:
                        operator = Operator.RIGHTPARENTHESIS;
                }
                list.put(matcher.start(), new EquationObjectData(operator));
            }
        }
    }

    /**
     * EquationObjectData list analysis.
     * @param list {@code Map<Integer,EquationObjectData>}
     * @return List of EquationObjectData
     */
    public List<EquationObjectData> getList(SortedMap<Integer,EquationObjectData> list){
        Iterator<Entry<Integer, EquationObjectData>> i = list.entrySet().iterator();
        List<EquationObjectData> objectList = new ArrayList<>();
        
        EquationObjectData previous = new EquationObjectData(Operator.DIVIDE);
        while (i.hasNext())
        {
            Map.Entry<Integer,EquationObjectData> m = i.next();
            EquationObjectData value = m.getValue();
            if((previous.getType() != EquationObjectType.OPERATOR || (Operator)previous.getObject() == Operator.RIGHTPARENTHESIS) && (value.getType() != EquationObjectType.OPERATOR || (Operator)value.getObject() == Operator.LEFTPARENTHESIS)){
                objectList.add(new EquationObjectData(Operator.MULTIPLY));
            }
            objectList.add(value);
            previous = value;
        }
        return objectList;
    }

    /**
     * Show EquationObjectData list
     * @param list Liste non ordonnée
     */
    public void showList(List<EquationObjectData> list) {
        for (EquationObjectData e : list) {
            System.out.println(e.getObject().toString());
        }
    }
}
