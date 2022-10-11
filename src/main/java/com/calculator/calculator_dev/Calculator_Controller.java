package com.calculator.calculator_dev;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.controlsfx.control.action.Action;

public class Calculator_Controller {
    @FXML
    private Button zero,one,two,three,four,five,six,seven,eight,nine,add,subtract,multiply,divide,delete,equals;
    @FXML
    private TextArea field;
    @FXML
    private VBox parentPane;
    String exp="";
    @FXML
    public void writeInField(ActionEvent ae)
    {
        exp=field.getText();
        String str=((Button) ae.getSource()).getText();
        if(str.equals("+")||str.equals("-")||str.equals("/")||str.equals("*"))
        {
            if(Character.isDigit(exp.charAt(exp.length()-1)))
            {
                field.setText(field.getText()+str);
            }
        }
        if(Character.isDigit(str.charAt(0)))
        {
            field.setText(field.getText()+str);
        }
    }
    @FXML
    public void splitExpression(ActionEvent ae)
    {

    }
    @FXML
    public void clearAll(ActionEvent ae)
    {
        field.setText("");
        field.setPromptText("0");
    }
}
