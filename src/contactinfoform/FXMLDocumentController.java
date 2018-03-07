/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactinfoform;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.Year;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;

/**
 *
 * @author nlayfield
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Label test;
    @FXML
    private TextField firstName, lastName, companyName, phoneNumber, email,conference;
    @FXML
    private TextArea noteTextBox;
    

    @FXML
    private void handleButtonAction(ActionEvent event) throws FileNotFoundException, IOException {
        String fname = "", lname = "", cname = "", pnum = "", ename = "", note = "", csv = "",con="";
        String header = "First Name,Last Name,Company Name,Phone Number,Email,Notes\n";
        label.setVisible(true);
        FadeTransition fadeTransition 
                        = new FadeTransition(Duration.millis(1000), label);
                fadeTransition.setFromValue(1.0);
                fadeTransition.setToValue(0.0);
                fadeTransition.play();
        con=conference.getText();
        fname = firstName.getText();
        lname = lastName.getText();
        cname = companyName.getText();
        pnum = phoneNumber.getText();
        ename = email.getText();
        note=noteTextBox.getText();
        csv = fname + "," + lname + "," + cname + "," + pnum + "," + ename + "," + note + ",\n";
        //System.out.println("Working Directory = "+ System.getProperty("user.home"));
        //System.out.println(Year.now().getValue());
        File check = new File(System.getProperty("user.home") + "/desktop/"+con+Year.now().getValue()+".csv");
        if (!check.exists() && !(check.isDirectory())) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(check));
            writer.write(header);
            writer.close();
        }
        FileWriter f = new FileWriter(System.getProperty("user.home") + "/desktop/"+con+Year.now().getValue()+".csv", true);
        StringBuilder sb = new StringBuilder();
        sb.append(csv);
        PrintWriter pw = new PrintWriter(f);
        pw.append(sb.toString());
        pw.close();
        firstName.setText("");
        lastName.setText("");
        companyName.setText("");
        phoneNumber.setText("");
        email.setText("");
        noteTextBox.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        label.setVisible(false);
    }

}
