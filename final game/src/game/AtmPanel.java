

package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;


public class AtmPanel extends JPanel implements ActionListener{
	Timer timer,wait,debttimer;
	FontMetrics promptsize;
	BufferedImage string;
	JButton one, two, three, four, five, six, seven, eight, nine, zero;
	JButton deposit, loan, withdraw, leave, confirm, cancel;
	JTextArea box;
	int money,debt,stored,temp, maxloan;
	int screennumber; //0 = start, 1 = deposit, 2 = withdraw, 3 = loan, 4 main. 
	double interest;
	DecimalFormat round;
	public AtmPanel(){
		setLayout(null);		
		money = 0;
		debt = 1000000;
		interest = 1.05;
		stored = 0;
		temp = 0;
		maxloan = 0;
		screennumber = 0;
		box = new JTextArea("");
		string = new BufferedImage(1,1,1);//Use bufferedimage to accuractlely store prompt size.
		one = new JButton("1");
		two = new JButton("2");
		three = new JButton("3");
		four = new JButton("4");
		five = new JButton("5");
		six = new JButton("6");
		seven = new JButton("7");
		eight = new JButton("8");
		nine = new JButton("9");
		zero = new JButton("0");
		deposit = new JButton("Deposit");
		withdraw = new JButton("Withdraw");
		loan = new JButton("Request loan");
		leave = new JButton("Leave");
		confirm = new JButton("Confirm");
		cancel = new JButton("Cancel");
		round = new DecimalFormat("#0.00");
		promptsize = string.getGraphics().getFontMetrics(getFont().deriveFont((float)40));//Get font details from bufferedimage and change to 40
		timer = new Timer(5,this);
		wait = new Timer(1000,this);
		debttimer = new Timer(3600000,this);
		debttimer.start();
		timer.start();
		box.setFont(getFont().deriveFont((float)30));
		box.setEditable(false);
		box.setForeground(Color.WHITE);
		box.setBackground(Color.BLACK);
		
		add(one);
		add(two);
		add(three);
		add(four);
		add(five);
		add(six);
		add(seven);
		add(eight);
		add(nine);
		add(zero);
		add(deposit);
		add(withdraw);
		add(leave);
		add(confirm);
		add(cancel);
		add(box);
		add(loan);
		
		loan.setVisible(false);
		deposit.setVisible(false);
		withdraw.setVisible(false);
		leave.setVisible(false);
		box.setVisible(false);
		
		one.setBounds(400,500,50,50);
		two.setBounds(460,500,50,50);
		three.setBounds(520,500,50,50);
		four.setBounds(400,560,50,50);
		five.setBounds(460,560,50,50);
		six.setBounds(520,560,50,50);
		seven.setBounds(400,620,50,50);
		eight.setBounds(460,620,50,50);
		nine.setBounds(520,620,50,50);
		zero.setBounds(460,680,50,50);
		deposit.setBounds(50,250,150,50);
		loan.setBounds(50,325,150,50);
		withdraw.setBounds(800,250,150,50);
		leave.setBounds(800,325,150,50);
		confirm.setBounds(350,680,100,50);
		cancel.setBounds(520,680,100,50);
		
		one.addActionListener(this);
		two.addActionListener(this);
		three.addActionListener(this);
		four.addActionListener(this);
		five.addActionListener(this);
		six.addActionListener(this);
		seven.addActionListener(this);
		eight.addActionListener(this);
		nine.addActionListener(this);
		zero.addActionListener(this);
		deposit.addActionListener(this);
		withdraw.addActionListener(this);
		loan.addActionListener(this);
		confirm.addActionListener(this);
		cancel.addActionListener(this);
		
		
	}
	
	public void reset(){
		loan.setVisible(false);
		deposit.setVisible(false);
		withdraw.setVisible(false);
		leave.setVisible(false);
		box.setVisible(false);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		Graphics2D text = (Graphics2D) g.create();
		text.setFont(getFont().deriveFont((float)40));
		text.setColor(Color.WHITE);
		g2d.setColor(Color.BLACK);
		g2d.fillRect(16,16,992,736);
		g2d.setColor(new Color(188,198,204));
		g2d.fillRect(0, 0, 1024, 16);
		g2d.fillRect(0, 0, 16, 768);
		g2d.fillRect(0,752,1024,16);
		g2d.fillRect(1008,0,16,768);
		
		text.drawString("Your money: $"+money, 25,50);
		
		if(screennumber == 0){
			text.drawString("Press any button to begin",512-promptsize.stringWidth("Press any button to begin")/2, 200);
			reset();
		}else if(screennumber == 4){
			text.drawString("What would you like to do?", 512-promptsize.stringWidth("What would you like to do?")/2, 200);
			loan.setVisible(true);
			deposit.setVisible(true);
			withdraw.setVisible(true);
			leave.setVisible(true);
		}else if(screennumber == 1){
			text.drawString("Deposit amount. Your balence is:",512-promptsize.stringWidth("Deposit amount. Your balence is:")/2, 200);
			text.drawString("Creditors will automatically withdraw from your account.",512-promptsize.stringWidth("Creditors will automatically withdraw from your account.")/2, 300);
			text.drawString("$" + stored,512-promptsize.stringWidth("$" + stored)/2, 250);
			text.drawString("$"+box.getText(),512-promptsize.stringWidth(box.getText())/2,400);
			reset();
		}else if(screennumber == 2){
			text.drawString("Withdraw amount. Your balence is:",512-promptsize.stringWidth("Withdraw amount. Your balence is:")/2, 200);
			text.drawString("$" + stored,512-promptsize.stringWidth("$" + stored)/2, 250);
			text.drawString("$"+box.getText(),512-promptsize.stringWidth(box.getText())/2,400);
			reset();
		}else if(screennumber == 3){
			text.drawString("Apply for a loan. Your max credit is: ",512-promptsize.stringWidth("Apply for a loan. Your max credit is: ")/2, 200);
			text.drawString("$" + maxloan,512-promptsize.stringWidth("$" + maxloan)/2, 250);
			text.drawString("Your debt (compounds hourly) is: $"+debt,512-promptsize.stringWidth("Your debt (compounds hourly) is: $"+debt)/2, 300);
			text.drawString("Interest on your debt is: 5%",512-promptsize.stringWidth("Interest on your debt is: 5%")/2, 350);
			text.drawString("$"+box.getText(),512-promptsize.stringWidth(box.getText())/2,400);
			reset();
			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == timer){
			repaint();
			maxloan = (1000000 - debt)/2;
			if(maxloan < 0){
				maxloan = 0;
			}
		}
		if(e.getSource() == wait){
			box.setText("");
			wait.stop();
		}
		if(e.getSource() == debttimer){
			debt = (int) (debt * interest);
		}
		if((screennumber == 0)&&(e.getSource()!= timer)&&(e.getSource() != debttimer)){
			screennumber = 4;
			
		}else{
			if(e.getSource() == one){
				if((box.getText().length() < 10)&&(screennumber!=4)){
					box.setText(box.getText() + "1");
				}
				
			}else if(e.getSource() == two){
				if((box.getText().length() < 10)&&(screennumber!=4)){
					box.setText(box.getText() + "2");
				}
				
				
			}else if(e.getSource() == three){
				if((box.getText().length() < 10)&&(screennumber!=4)){
					box.setText(box.getText() + "3");
				}
				
			}else if(e.getSource() == four){
				if((box.getText().length() < 10)&&(screennumber!=4)){
					box.setText(box.getText() + "4");
				}	
				
			}else if(e.getSource() == five){
				if((box.getText().length() < 10)&&(screennumber!=4)){
					box.setText(box.getText() + "5");
				}
				
			}else if(e.getSource() == six){
				if((box.getText().length() < 10)&&(screennumber!=4)){
					box.setText(box.getText() + "6");
				}
				
			}else if(e.getSource() == seven){
				if((box.getText().length() < 10)&&(screennumber!=4)){
					box.setText(box.getText() + "7");
				}
				
			}else if(e.getSource() == eight){
				if((box.getText().length() < 10)&&(screennumber!=4)){
					box.setText(box.getText() + "8");
				}
				
			}else if(e.getSource() == nine){
				if((box.getText().length() < 10)&&(screennumber!=4)){
					box.setText(box.getText() + "9");
				}
				
			}else if(e.getSource() == zero){
				if((box.getText().length() < 10)&&(screennumber!=4)){
					box.setText(box.getText() + "0");
				}
				
			}else if(e.getSource() == deposit){
				screennumber = 1;
			}else if(e.getSource() == withdraw){
				screennumber = 2;
			}else if(e.getSource() == loan){
				screennumber = 3;
			}else if(e.getSource() == cancel){
				if((screennumber == 1)||(screennumber == 2)||(screennumber == 3)){
					screennumber = 4;
					box.setText("");
				}
			}else if(e.getSource() == confirm){
				if(screennumber == 1){
					try{
						temp = -1;
						temp = Integer.parseInt(box.getText());
					}catch(Exception error){}
					
					if((temp > money)||(temp <= 0)){
						box.setText("Invalid");
						wait.start();
					}else{
						if(debt > 0){
							money -= temp;
							debt -= temp;
							box.setText("");
						}else{
							stored += temp;
							money -= temp;
							box.setText("");
						}
					}
					
				}else if(screennumber == 2){
					try{
						temp = -1;;
						temp = Integer.parseInt(box.getText());
					}catch(Exception error){}
					
					if((temp > stored)||(temp <= 0)){
						box.setText("Invalid");
						wait.start();
					}else{
						stored -= temp;
						money += temp;
						box.setText("");
					}
				}else if(screennumber == 3){
					try{
						temp = -1;
						temp = Integer.parseInt(box.getText());
					}catch(Exception error){}
					
					if((temp > maxloan)||(temp <= 0)){
						box.setText("Invalid");
						wait.start();
					}else{
						debt += temp;
						money += temp;
						box.setText("");
					}
				}
			}
		}
		
		
	}

}
