package com.calculator.calculator_dev;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.controlsfx.control.action.Action;

import java.text.NumberFormat;

public class Calculator_Controller {
    @FXML
    private Button zero,one,two,three,four,five,six,seven,eight,nine,add,subtract,multiply,divide,delete,equals;
    @FXML
    private TextArea field;
    @FXML
    private VBox parentPane;
    private String exp,num_temp="",sign_temp="",result_temp="";
    private String numbersInExpression[];
    private String signsInExpression[];
    private int count1=0,count2=0;
    private double result=0.0;

    @FXML
    public void writeInField(ActionEvent ae)
    {
        try {
            exp = field.getText();
            String str = ((Button) ae.getSource()).getText();
            if (str.equals("+") || str.equals("-") || str.equals("/") || str.equals("*")) {
                if (exp.length() != 0) {
                    if (Character.isDigit(exp.charAt(exp.length() - 1))) {
                        if (field.getText().equals(result_temp)) {
                            result = 0.0;
                            field.setText("");
                            field.setPromptText("0");
                            return;
                        }
                        field.setText(field.getText() + str);
                    }
                }
            }
            if (Character.isDigit(str.charAt(0))) {
                if (field.getText().equals(result_temp)) {
                    result = 0.0;
                    field.setText("");
                    field.setPromptText("0");
                }
                field.setText(field.getText() + str);
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception!");
        }
    }
    @FXML
    public void splitExpression(ActionEvent ae)
    {
        try {
            exp = field.getText();
            if (exp.length() != 0) {
                for (int i = 0; i < exp.length() - 1; i++) {
                    char ch = exp.charAt(i);
                    if (Character.isDigit(ch)) {
                        if (Character.isDigit(exp.charAt(i + 1))) {
                            num_temp += Character.toString(ch);
                        }
                        if (exp.charAt(i + 1) == '+' || exp.charAt(i + 1) == '-' || exp.charAt(i + 1) == '*' || exp.charAt(i + 1) == '/') {
                            num_temp += Character.toString(ch) + " ";
                            count1++;
                        }
                    }
                    if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                        sign_temp += Character.toString(ch) + " ";
                        count2++;
                    }
                    else {
                        continue;
                    }
                }
                if (Character.isDigit(exp.charAt(exp.length() - 1))) {
                    num_temp += Character.toString(exp.charAt(exp.length() - 1));
                    count1++;
                }
                numbersInExpression = num_temp.split(" ");
                signsInExpression = sign_temp.split(" ");
                num_temp = "";
                sign_temp = "";
                count2 = 0;
                count1 = 0;
                calculate();
            }
            else {
                field.setText("");
                result = 0.0;
                result_temp = "";
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception!");
        }
    }
    private void calculate()
    {
        try {
            result=Double.valueOf(numbersInExpression[0]);
            int i=0;
            while (!signsInExpression[i].isEmpty()) {
                if (signsInExpression[i].equals("+")) {
                    result = Double.valueOf(numbersInExpression[i]) + Double.valueOf(numbersInExpression[i + 1]);
                    deleteElement(numbersInExpression, 1);
                    deleteElement(numbersInExpression, 1);
                    deleteElement(signsInExpression, 1);
                    insertElement(numbersInExpression, 1, String.valueOf(result));
                }
                if (signsInExpression[i].equals("-")) {
                    result = Double.valueOf(numbersInExpression[i]) - Double.valueOf(numbersInExpression[i + 1]);
                    deleteElement(numbersInExpression, 1);
                    deleteElement(numbersInExpression, 1);
                    deleteElement(signsInExpression, 1);
                    insertElement(numbersInExpression, 1, String.valueOf(result));
                }
                if (signsInExpression[i].equals("/")) {
                    result = Double.valueOf(numbersInExpression[i]) / Double.valueOf(numbersInExpression[i + 1]);
                    deleteElement(numbersInExpression, 1);
                    deleteElement(numbersInExpression, 1);
                    deleteElement(signsInExpression, 1);
                    insertElement(numbersInExpression, 1, String.valueOf(result));
                }
                if (signsInExpression[i].equals("*")) {
                    result = Double.valueOf(numbersInExpression[i]) * Double.valueOf(numbersInExpression[i + 1]);
                    deleteElement(numbersInExpression, 1);
                    deleteElement(numbersInExpression, 1);
                    deleteElement(signsInExpression, 1);
                    insertElement(numbersInExpression, 1, String.valueOf(result));
                }
            }
            field.setText(String.valueOf(result));
            System.out.println(result);
            result_temp=String.valueOf(result);
            numbersInExpression=null;
            signsInExpression=null;
        }
        catch(Exception e)
        {
            field.setText("Wrong Format");
            result_temp=field.getText();
        }
    }
    private void insertElement(String[] arr, int pos,String ele) {
        int index=pos-1;
        if(pos>arr.length||pos==0)
        {
            return;
        }
        for(int i=0;i<arr.length;i++)
        {
            if(i==index)
            {
                String temp=arr[i];
                arr[i]=ele;
                for(int j=i+1;j<arr.length;j++)
                {
                    String temp2=arr[j];
                    arr[j]=temp;
                    temp=temp2;
                }
                break;
            }
        }
    }
    private void deleteElement(String[] arr,int pos)
    {
        int index=pos-1;
        if(pos>arr.length||pos==0)
        {
            return;
        }
        for(int i=index;i<arr.length-1;i++)
        {
            arr[i]=arr[i+1];
        }
        arr[arr.length-1]="";
    }
    @FXML
    public void clearAll(ActionEvent ae)
    {
        result=0.0;
        field.setText("");
        field.setPromptText("0");
    }
}
