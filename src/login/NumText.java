/*
 * Copyright (c) 2012, 2014 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package login;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static javafx.geometry.HPos.RIGHT;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NumText extends Application {

    final static String [] uni  = {"cero","uno","dos","tres","cuatro","cinco","seis","siete","ocho","nueve","diez","once","doce","trece","catorce","quince","dieciseis","diecisiete","dieciocho","diecinueve","veinte"};
    static final String [] dec  = {" "," ","veinti","treinta","cuarenta","cincuenta","sesenta","setenta","ochenta","noventa","cien"};
    final static String [] cen  = {" ","ciento ","doscientos ","trescientos ","cuatrocientos ","quinientos ","seiscientos ","setecientos ","ochocientos ","novecientos ","mil "};
    public static String cadena;
    TextField userTextField;
    TextField pwBox;

    static String categoria(int n) {
        if (n <= 20) {cadena = uni[n];
        } else if (n > 20 && n <= 100) {if (n % 10 != 0) {
            if (n > 30) {cadena = dec[(n - n % 10) / 10] + " y " + categoria(n % 10);
            } else {cadena = dec[(n - n % 10) / 10] + categoria(n % 10);}
        } else {cadena = dec[(n - n % 10) / 10];}
        } else if (n > 100 && n <= 1000) {
            if(n%100!=0) {cadena = cen [(n-n%100)/100] + categoria(n % 100);
            }else{cadena = cen [(n-n%100)/100];}
        }else if (n > 1000 && n <= 100000) {
            if(n%1000==0){
                if(n>=2000){cadena = categoria((n-n%1000)/1000)+cen[10];}else {
                    cadena = cen[10];}
            }else{
                if(n<2000) {cadena = cen[10]+categoria((n%1000));}else if(n>2000){
                    cadena = categoria((n-n%1000)/1000)+cen[10]+categoria(n%1000);
                }
            }
        }if(n > 100000){cadena = "Elija un numero menor que 100,000";}
        return cadena;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Convertidor número-Texto");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Bienvenidos; Welcome...");
        scenetitle.setId("welcome-text");
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Número:");
        grid.add(userName, 0, 1);

        userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Texto:");
        grid.add(pw, 0, 2);

         pwBox = new TextField();
        pwBox.setEditable(false);
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Convertir");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 0, 6);
        grid.setColumnSpan(actiontarget, 2);
        grid.setHalignment(actiontarget, RIGHT);
        actiontarget.setId("actiontarget");

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                int temp = new Integer( userTextField.getText());
                if(temp >= 0){
                    String s = categoria(temp);
                    pwBox.setText(s);
                }else{
                    actiontarget.setText("Sólo números naturales menores a 100,000");
                }
            }
        });
        Scene scene = new Scene(grid, 600, 275);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(NumText.class.getResource("NumText.css").toExternalForm());
        primaryStage.show();
    }
    
       public static void main(String[] args) {
        launch(args);
    }

}