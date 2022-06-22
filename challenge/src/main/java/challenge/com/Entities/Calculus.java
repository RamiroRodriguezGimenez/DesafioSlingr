/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenge.com.Entities;


public class Calculus {
   
    
   
    private String expression;
    private double result;
    private int nDecimal;
    

    public Calculus(String expression, double result, int nDecimal) {
        this.expression = expression;
        this.result = result;
        this.nDecimal = nDecimal;
    }
    
    public int getnDecimal() {
        return nDecimal;
    }

    public void setnDecimal(int nDecimal) {
        this.nDecimal = nDecimal;
    }
   

    public Calculus() {
    }

  

   
    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Calc{" +  ", expression=" + expression + ", result=" + result + ", nDecimal=" + nDecimal + '}';
    }

   
}

