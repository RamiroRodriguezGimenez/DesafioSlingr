/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenge.com.Services;

import challenge.com.Entities.Calculus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    @Autowired(required = false)
    public Calculus calc;

    public static Calculus calculate(Calculus c, boolean decimal, int n) throws Exception {
        if (c.getExpression().equals(" ")) {
            c.setResult(Math.round((c.getResult()) * Math.pow(10, c.getnDecimal())) / Math.pow(10, c.getnDecimal()));
            return (c);
        }

        int nDecimal = c.getnDecimal();
        String expression = c.getExpression();
        double number = c.getResult();

        char firstChar = (expression.charAt(0));

        if (Character.isDigit(firstChar)) {
            if (!decimal) {
                number = number * 10 + Character.getNumericValue(firstChar);
            } else {

                number = Math.round((number + (Character.getNumericValue(firstChar)) * (Math.pow(10, (n * (-1))))) * Math.pow(10, nDecimal)) / Math.pow(10, nDecimal);
                n = n + 1;

            }

        }

        switch (firstChar) {
            case ('.'):
                decimal = true;
                n = 1;

                break;
            case ('('):
                c.setExpression(expression.substring(1));
                c.setResult(number);
                Calculus cAux = new Calculus(c.getExpression(), c.getResult(), c.getnDecimal());

                cAux = parenthesis(cAux, "", 0);
                expression = " " + cAux.getExpression();
                c.setExpression(expression);
                decimal = false;
                break;
            case ('+'):
                decimal = false;

                Calculus cSum = new Calculus("", 0.0, c.getnDecimal());
                cSum.setExpression(c.getExpression().substring(1));
                cSum = sumarRestar(cSum);

                c.setResult(c.getResult() + cSum.getResult());
                c.setExpression(" " + cSum.getExpression());

                expression = c.getExpression();
                number = c.getResult();
                break;
            case ('-'):
                decimal = false;

                Calculus cRes = new Calculus("", 0.0, c.getnDecimal());
                cRes.setExpression(c.getExpression().substring(1));
                cRes = sumarRestar(cRes);
                c.setResult(c.getResult() - cRes.getResult());
                c.setExpression(" " + cRes.getExpression());
                expression = c.getExpression();
                number = c.getResult();
                break;
            case ('*'):
                decimal = false;

                Calculus cMul = new Calculus("", 0.0, c.getnDecimal());
                cMul.setExpression(c.getExpression().substring(1));
                cMul = multDiv(cMul);
                c.setResult(c.getResult() * cMul.getResult());
                c.setExpression(" " + cMul.getExpression());
                expression = c.getExpression();
                number = c.getResult();

                break;
            case ('/'):
                decimal = false;

                Calculus cDiv = new Calculus("", 0.0, c.getnDecimal());
                cDiv.setExpression(c.getExpression().substring(1));
                cDiv = multDiv(cDiv);
                c.setResult(c.getResult() / cDiv.getResult());
                c.setExpression(" " + cDiv.getExpression());
                expression = c.getExpression();
                number = c.getResult();

                break;
            case ('s'):
                if (expression.substring(0, 5).equals("sqrt(")) {

                    c.setExpression(expression.substring(1));
                    c.setResult(number);
                    Calculus cSqr = new Calculus();
                    cSqr = sqrt(c, "", 4);
                    expression = " " + cSqr.getExpression();
                    c.setExpression(expression);
                   
                    decimal = false;
                } else {
                    throw new Exception();
                }
                break;
            case ('^'):
                decimal = false;

                Calculus cPow = new Calculus("", 0.0, c.getnDecimal());
                cPow.setExpression(c.getExpression().substring(1));
                cPow = multDiv(cPow);
                c.setResult(Math.pow(c.getResult(), cPow.getResult()));
                c.setExpression(" " + cPow.getExpression());
                expression = c.getExpression();
                number = c.getResult();
                break;

            default:
                if (!Character.isDigit(firstChar)) {
                    throw new Exception();
                }
        }

        if (c.getExpression().length() < 1) {
            c.setResult(Math.round((c.getResult()) * Math.pow(10, c.getnDecimal())) / Math.pow(10, c.getnDecimal()));
            return (c);
        }
        c.setExpression(expression.substring(1));
        c.setResult(number);
        if (expression.length() > 1) {

            c = calculate(c, decimal, n);
        }
        c.setResult(Math.round((c.getResult()) * Math.pow(10, c.getnDecimal())) / Math.pow(10, c.getnDecimal()));
        return (c);
    }

    public static Calculus parenthesis(Calculus cAux, String term, int index) throws Exception {

        String expression = cAux.getExpression();
        double number = cAux.getResult();
        char firstChar = (expression.charAt(0));
        int finBucle = 1;
        do {
            index = index + 1;
            term = term + firstChar;

            firstChar = (expression.charAt(index));

            if (firstChar == '(') {
                finBucle = finBucle + 1;
            } else if (firstChar == ')') {
                finBucle = finBucle - 1;
            }
        } while (finBucle > 0);
        cAux.setExpression(term);
        Calculus cAuxx = new Calculus(cAux.getExpression(), cAux.getResult(), cAux.getnDecimal());
        cAuxx = calculate(cAux, false, 0);
        cAux.setExpression(Double.toString(cAuxx.getResult()) + expression.substring(index + 1));

        return cAux;

    }

    public static Calculus sumarRestar(Calculus cSum) throws Exception {
        String expression = cSum.getExpression();
        double number = cSum.getResult();
        int index = 0;
        char firstChar;
        String term = "";
        firstChar = (expression.charAt(index));
        do {
            if (firstChar == '(') {
                cSum.setExpression(expression.substring(index + 1));
                Calculus cAux = new Calculus(cSum.getExpression(), cSum.getResult(), cSum.getnDecimal());

                cAux = parenthesis(cAux, "", 0);

                index = index - 1;
                expression = (cAux.getExpression());
            } else if (firstChar == 's') {

                if (expression.substring(0 + index, 5 + index).equals("sqrt(")) {
                    cSum.setExpression(expression.substring(index + 1));
                    Calculus cAux = new Calculus(cSum.getExpression(), cSum.getResult(), cSum.getnDecimal());
                    cAux = sqrt(cAux, "", 4);
                    index = - 1;
                    expression = (cAux.getExpression());
                }
            } else {
                term = term + firstChar;

            }

            index = index + 1;
            if (index < expression.length()) {
                firstChar = (expression.charAt(index));
            }
        } while ((firstChar != '+' && firstChar != '-') && (index < expression.length()));
        cSum.setExpression(term);
        cSum.setResult(0.0);
        cSum = calculate(cSum, false, 0);
        cSum.setExpression(expression.substring(index));
        if (index < expression.length()) {
            cSum.setExpression(expression.substring(index));
        } else {
            cSum.setExpression("");
        }
        return cSum;
    }

    public static Calculus multDiv(Calculus cMul) throws Exception {
        String expression = cMul.getExpression();
        double number = cMul.getResult();
        int index = 0;
        char firstChar;
        String term = "";
        firstChar = (expression.charAt(index));
        do {

            if (firstChar == '(') {
                cMul.setExpression(expression.substring(index + 1));
                Calculus cAux = new Calculus(cMul.getExpression(), cMul.getResult(), cMul.getnDecimal());
                cAux = parenthesis(cAux, "", 0);
                index = index - 1;
                expression = (cAux.getExpression());

            } else if (firstChar == 's') {

                if (expression.substring(index, index + 5).equals("sqrt(")) {

                    cMul.setExpression(expression.substring(index));

                    Calculus cAux = new Calculus(cMul.getExpression(), cMul.getResult(), cMul.getnDecimal());
                    cAux = sqrt(cAux, "", 5);
                    index = index - 1;
                    expression = (Double.toString(cAux.getResult()));
                }
            } else {
                term = term + firstChar;
            }
            index = index + 1;
            if (index < expression.length()) {
                firstChar = (expression.charAt(index));
            }
            ;
        } while ((Character.isDigit(firstChar) || firstChar == '.') && (index < expression.length()));
        cMul.setExpression(term);
        cMul.setResult(0.0);
        cMul = calculate(cMul, false, 0);
        cMul.setExpression(expression.substring(index));
        if (index < expression.length()) {
            cMul.setExpression(expression.substring(index));
        } else {
            cMul.setExpression("");
        }
        return cMul;
    }

    public static Calculus sqrt(Calculus cSqr, String term, int index) throws Exception {

        String expression = cSqr.getExpression();
        double number = cSqr.getResult();
        char firstChar = (expression.charAt(index));

        do {
            index = index + 1;
            term = term + firstChar;
            firstChar = (expression.charAt(index));
        } while (firstChar != ')');
        cSqr.setExpression(term);
        Calculus aux = new Calculus(cSqr.getExpression(), cSqr.getResult(), cSqr.getnDecimal());

        aux = calculate(aux, false, 0);
        cSqr.setResult(Math.pow(aux.getResult(), 0.5));

        cSqr.setExpression(Double.toString(cSqr.getResult()) + expression.substring(index + 1));
        return cSqr;
    }

    public Calculus create(String expression, int nDecimal) throws Exception {
        Calculus c = new Calculus();
        c.setExpression(expression);
        c.setnDecimal(nDecimal);
        c = calculate(c, false, 0);

        return (c);
    }
}
