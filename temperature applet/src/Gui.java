import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.lang.NumberFormatException;

public class Gui extends JApplet implements ActionListener{
	private Temp convert;
	
	private JTextField txtcelcius;
	private JTextField txtfahrenheit;
	
	private JButton btncelcius;
	private JButton btnfahrenheit;
	
	private JLabel lblcelcius;
	private JLabel lblfahrenheit;
	
	public void init(){
		setSize(300,200);
		setLayout(null);
		
		convert = new Temp();
		
		txtcelcius = new JTextField();
		txtfahrenheit = new JTextField();
		btncelcius = new JButton("Convert>>>");
		btnfahrenheit = new JButton("<<<Convert");
		lblcelcius = new JLabel("Celcius to fahrenheit");
		lblfahrenheit = new JLabel("Fahrenheit to celcius");
		
		txtcelcius.setBounds(20,50,120,20);
		txtfahrenheit.setBounds(150,50,120,20);
		lblcelcius.setBounds(20,20,120,20);
		lblfahrenheit.setBounds(150,20,120,20);
		btncelcius.setBounds(20,75,100,20);
		btnfahrenheit.setBounds(150,75,100,20);
		
		add(btnfahrenheit);
		add(btncelcius);
		add(lblfahrenheit);
		add(lblcelcius);
		add(txtcelcius);
		add(txtfahrenheit);
		
		btncelcius.addActionListener(this);
		btnfahrenheit.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btncelcius){
			try{
			convert.setCelcius(Double.parseDouble(txtcelcius.getText()));
			txtfahrenheit.setText(Double.toString(convert.getFahrenheit()));
			}catch(NumberFormatException error){
				txtcelcius.setText("Invalid entry!");
			}
		}
		
		if(e.getSource() == btnfahrenheit){
			try{
				convert.setFahrenheit(Double.parseDouble(txtfahrenheit.getText()));
				txtcelcius.setText(Double.toString(convert.getCelcius()));
				}catch(NumberFormatException error){
					txtfahrenheit.setText("Invalid entry!");
				}
		}
	}
}