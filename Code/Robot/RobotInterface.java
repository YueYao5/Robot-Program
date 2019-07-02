/**
 * Title      : RobotGUI.java
 * Description: This class is the main class of the program, which contains the dish robot and related method.
 * Copyright  : Copyright (c) 2006-2016
 * @author      Yao Yue
 * @version     1.0
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;



public class RobotInterface extends JFrame{
	Font f60=new Font("serif",Font.BOLD,60);//create new different font object.
	Font f35=new Font("serif",Font.BOLD,35);
	Font f25=new Font("serif",Font.BOLD,25);
	Font f30=new Font("serif",Font.BOLD,30);
	Font f40=new Font("serif",Font.BOLD,40);
	Font f20=new Font("serif",Font.BOLD,20);
	Font f10=new Font("serif",Font.BOLD,10);
	
    Color backGroundColor=new Color(176,180,240);// create new different color object.
    Color color1=new Color(153,236,236);
    Color color2=new Color(241,149,247);
    Color color3=new Color(243,88,185);
    Color color4=new Color(245,83,107);
    Color color5=new Color(245,146,84);
    Color color6=new Color(238,245,86);
    Color color7=new Color(128,245,88);
    Color color8=new Color(89,244,183);
    
    JFrame mainFrame=new JFrame();//create a new frame.
    
    JPanel greetingPanel=new JPanel();//create new different panel. this is for the first panel of welcome.
	JPanel northPanel=new JPanel();//the second panel of a label "please select one".
	JPanel menuPanel=new JPanel();//the main menu panel.
	JPanel dishPanel=new JPanel();//the sub menu panel.
	JPanel confirmPanel=new JPanel();// the confirm button panel. 
	JPanel showPanel=new JPanel();//the show the ordered dish panel.
	JPanel payPanel=new JPanel();//panel contains pay button and back to menu button.
	JPanel nextPanel=new JPanel();//panel contains go next button to enter next page.
	JPanel servingPanel=new JPanel();//panel which robot offer dishes.
	JPanel optionPanel=new JPanel();
	JPanel byePanel=new JPanel();//panel shows bye-bye message.
	JPanel finishPanel=new JPanel();//the last panel which contains finish button to go the first page.
	JPanel sleepPanel=new JPanel();//panel for sleeping mode.
	
	JButton[] button=new JButton[6];//create new button array object.
	JButton ok=new JButton("OK");
	
/**some primitive variables 
 * 	
 */
    int storeRest;//a variable for the store rest.
    int Price;// a variable for a dish price
	int total=0;//the total price the customer should pay.
	String S="";//the initial string.
    
	
/**a inner class of dish information.
 * 	it contains name, rest number and price. 
 */
	class DishInfro{
		String name;
	    int rest;//库存剩余量
	    int price;//一盘菜的价格
	}
	
/** a inner class of user dish ordering information
 * which contains dish name and price.
 */
	class userDish{
		String name;
		int price;
	}
	
/** create a new arrayList of DishInfro class.
 * create a new arrayList of userDish class.
 */
	ArrayList<DishInfro>myList=new ArrayList<DishInfro>();
	ArrayList<userDish>userList=new ArrayList<userDish>();
	
/**create a timer contractor to count time
 * if the time stay to 30s , it will create an action
 * to perform sleeping method
 */
	Timer timer=new Timer(30000,new ActionListener()
	  {
		public void actionPerformed(ActionEvent e){
			
			goSleep();
		  }
        }
	  );
	
/** open a new file which name is userFile
 * it record users' ordering behaviors.
 * what dishes he orders and the price.
 */
	File userFile=new File("Files/userFile");

/** It is a method which can read Welcome message 
 * from WelcomeMessage.txt
 *@return WelcomeMessage The random selected welcome message.
 */
	public String readWelcomeMessage(){
		
		int i;
		String line=null;//set line as null
        int max=(int)(Math.random()*6);//create a random number to match.
        String welcomeMessage=null;
		try
		{
			
			
			File Welcomefile=new File("Files/WelcomeMessage");//open a file
			FileReader fr = new FileReader(Welcomefile);
			BufferedReader br = new BufferedReader(fr);
			
			for(i=0;(line=br.readLine())!=null;i++)//read the file line by line.
			{
				if(i==max){//if the line number match the random number 
				welcomeMessage = br.readLine();//put the line to welcome message
				}
			}
			
			br.close();//close the buffer reader.
			fr.close();//close the file reader.
			
		}catch(Exception e){//catch the error.
			e.printStackTrace();
		}
		return welcomeMessage;//return the welcome message.
	}//welcome message
	 
	/** a method to show the first user interface.
	 * which contains random selected welcome message and 
	 * a ordering button for customers to order dish.
	 */
	public void goWelcome(){	
    
	timer.start();//start timer to count the time.
	JPanel greetingPanel=new JPanel();//create a new panel and set layout.
	greetingPanel.setBackground(backGroundColor);
	
	JLabel greetingLabel=new JLabel(readWelcomeMessage(),JLabel.CENTER);//create a label which can show the welcome message.
    greetingLabel.setFont(f60);
    

	JButton Order=new JButton("Order!");//create a new button.
	Order.setFont(f30);
	
	greetingPanel.setLayout(new BorderLayout());//set panel layout and background color.
	greetingPanel.add(BorderLayout.CENTER,greetingLabel);
	greetingPanel.add(BorderLayout.SOUTH,Order);
	
	Order.addActionListener(new ActionListener()//an inner class when button is clicked the code below will work.
	  {
        public void actionPerformed(ActionEvent e){
          timer.restart();// timer restart to count time.
          greetingPanel.setVisible(false);//set greeting panel to be invisible.
          goMainMenu();//back to main menu method.
         }
	  }
	);
    mainFrame.setTitle("WelCome");//set frame title.
	mainFrame.getContentPane().add(greetingPanel);
	mainFrame.setSize(700,450);
	mainFrame.setVisible(true);
	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}//welcome method.
 


 /** a method to generate the main menu user interface 
  * which contains six buttons for costumers to order dishes   
  */
public void goMainMenu(){
    
    JPanel northPanel=new JPanel();//create new panels
    JPanel menuPanel=new JPanel();
   
	northPanel.setLayout(new GridLayout(1,0));//set panels layout and background color.
    northPanel.setBackground(backGroundColor);
	menuPanel.setBackground(backGroundColor);
	
	JLabel northLabel=new JLabel("Please select an option: ",JLabel.LEFT);//create new labels.
	northLabel.setFont(f30);
	northLabel.setBackground(backGroundColor);
	northLabel.setOpaque(true);
	
	JButton button1=new JButton("<html><body>1<br>Fish</body></html>");//create a new buttons and set it forms.
	button1.setFont(f30);
	button1.setBackground(color1);
	button1.addActionListener(new ActionListener(){//an inner class which will work only when user click it.
        public void actionPerformed(ActionEvent e){
         timer.restart();//timer restart.
         menuPanel.setVisible(false);//set current panel to be false.
         northPanel.setVisible(false);
         myList.clear();//clear the arrayList myList or in the file it will write several times.
         File fishFile=new File("Files/FishDishes");// open a matched file.
         goDishPage(fishFile);// call a method to go next page.
         }
	  }
	);
	
	
	JButton button2=new JButton("<html><body>2<br>Meat</body></html>");//just as same as above
	button2.setFont(f30);
	button2.setBackground(color2);
	button2.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
    	  timer.restart();
       menuPanel.setVisible(false);
       northPanel.setVisible(false);
       myList.clear();
       File meatFile=new File("Files/MeatDishes");
       goDishPage(meatFile);
       }
	  }
	);
	
	
	JButton button3=new JButton("<html><body>3<br>Rice</body></html>");//just as same as above.
	button3.setFont(f30);
	button3.setBackground(color3);
	button3.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
    	   timer.restart();
    	menuPanel.setVisible(false);
    	northPanel.setVisible(false);
    	myList.clear();
        File riceFile=new File("Files/RiceDishes");
        goDishPage(riceFile);
       }
	  }
	);
	
	
	JButton button4=new JButton("<html><body>4<br>Noodle</body></html>");//just as same as above.
	button4.setFont(f30);
	button4.setBackground(color4);
	button4.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
    	   timer.restart();
    	menuPanel.setVisible(false);
    	northPanel.setVisible(false);
    	myList.clear();
        File noodleFile=new File("Files/NoodleDishes");      
        goDishPage(noodleFile); 
        }
	  }
	);
	
	
	JButton button5=new JButton("<html><body>5<br>Drink</body></html>");//just as same as above.
	button5.setFont(f30);
	button5.setBackground(color5);
	button5.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
    	  timer.restart();
    	menuPanel.setVisible(false);
    	northPanel.setVisible(false);
        File drinkFile=new File("Files/DrinkDishes");
        myList.clear();
        goDishPage(drinkFile);
       }
	  }
	);
	
	northPanel.add(northLabel);//add label onto panel.
	
	menuPanel.add(button1);//add button onto panel.
	menuPanel.add(button2);
	menuPanel.add(button3);		
	menuPanel.add(button4);
	menuPanel.add(button5);	
	menuPanel.setLayout(new GridLayout(0,3));
	menuPanel.setBackground(backGroundColor);

	mainFrame.setTitle("MainMenu");//set title for frame.
    mainFrame.setLayout(new BorderLayout(0,3));
	mainFrame.getContentPane().add(BorderLayout.NORTH,northPanel);
    mainFrame.getContentPane().add(BorderLayout.CENTER,menuPanel);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}//mainMenu method over.


/** a method show the matched sub menu when user select a kind of dishes. 
 * it shows more details of the main menu.
 * @param myfile A type of File which the GUI choose to read.
 */
	public void goDishPage(File myfile) {
        
		JPanel dishPanel=new JPanel();//create a new panel.
        JPanel northPanel=new JPanel();
        
        
        northPanel.setLayout(new GridLayout(1,2));//set Layout of panel.
        dishPanel.setLayout(new GridLayout(2,3));
        
        northPanel.setBackground(backGroundColor);//set background color of panels.
        dishPanel.setBackground(backGroundColor);
       
        
        JLabel northLabel=new JLabel("Please choose one you like: ",JLabel.LEFT);//create new labels.
    	northLabel.setFont(f25);
    	northLabel.setBackground(backGroundColor);
    	
    	JButton southButton=new JButton("back to main menu");//create a new button which allow user to back to main menu.
    	southButton.setFont(f20);
    	southButton.addActionListener(
    			new ActionListener(){
					  public void actionPerformed(ActionEvent e){// an inner class which work after clicked.
						      timer.restart();//timer restart.
					    	  dishPanel.setVisible(false);
					    	  northPanel.setVisible(false); 
					       	  goMainMenu();// call main menu method and back to main menu interface.
					    	 } 
					       } 
    			
    			);
    	
    	northPanel.add(northLabel);//add label into panel.
    	northPanel.add(southButton);
    	
		mainFrame.getContentPane().add(BorderLayout.CENTER,dishPanel);
		mainFrame.getContentPane().add(BorderLayout.NORTH,northPanel);
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try//try to read file.
		{
			int i,j;
		    String line=null;
		    JButton[] button=new JButton[6];//create new array buttons.
		    
			FileReader fr = new FileReader(myfile);//begin to read files.
			BufferedReader br = new BufferedReader(fr);	
		   	
			for(i=0;(line=br.readLine())!=null;i++)
			{
                j=i+1;

		        String[] result=line.split("/");// split lines by "\",take the former to result[0] and latter to result[1].
		        button[i]=new JButton("<html><body>"+j+"<br>"+result[0]+"<br>"+"RMB:"+result[1]+"</body></html>"); //put result array on the button.
				button[i].setFont(f30);
				
				dishPanel.add(button[i]);//add button on the panel.
				
				DishInfro dish=new DishInfro();//create new object which type is DishInfro.
				dish.name=result[0];//store result[0]into dish.name.
				dish.price=Integer.parseInt(result[1]);
				dish.rest=Integer.parseInt(result[2]);
				
				
			    if(dish.rest==0){//if stock list shows the rest of a dish is 0,enable the button. users cannot order this one.
			    	
			    	button[i].setEnabled(false);
			    	JLabel shortageLabel=new JLabel("Shortage!",JLabel.CENTER);
			    	shortageLabel.setFont(f30); 
			    	button[i].add(shortageLabel);//put a label on the button to tell user the dish is shortage.
			    }
				
				
				button[i].addActionListener(new ActionListener(){// an inner class button will work after clicked.
					public void actionPerformed(ActionEvent e){
						timer.restart();//timer restart.
				    	
				        dishPanel.setVisible(false);//set invisible of current panel.
				        northPanel.setVisible(false);
			      
				        JPanel confirmPanel=new JPanel();//create new panel.
				        JPanel showPanel=new JPanel();
				        
						confirmPanel.setLayout(new GridLayout(0,2));
						
						confirmPanel.setBackground(backGroundColor);
						showPanel.setBackground(backGroundColor);

						// show user what he/she ordered and the price and make he/she confirm.
						JLabel infroLabel=new JLabel("<html><body>"+"<br>"+"<br>"+"Dish Name:  "+dish.name+"<br>"+"Price:  "+dish.price+"  yuan"+"</body></html>");
						infroLabel.setFont(f40);
						infroLabel.setBackground(backGroundColor);
						
						JButton confirmButton=new JButton("OK, I want it");
						confirmButton.setFont(f20);
						
						JButton backButton=new JButton("back to menu");
						backButton.setFont(f20);
						
						showPanel.add(infroLabel);
						confirmPanel.add(backButton);
						confirmPanel.add(confirmButton);
						
						mainFrame.setTitle("SubMenu");
						mainFrame.getContentPane().add(BorderLayout.SOUTH,confirmPanel);
						mainFrame.getContentPane().add(BorderLayout.CENTER,showPanel);
						mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						
						confirmButton.addActionListener(new ActionListener(){// inner class if user clicked this which means he/she confirm to order these dishes.
						    public void actionPerformed(ActionEvent e){ 
						    	 timer.restart();
						    	 total=total+dish.price;
						    	 dish.rest=dish.rest-1;//the rest number of the dishes will be decreased by 1.
						    	 
									try{
										
										FileWriter bw=new FileWriter(userFile);//write what users order in the file.
										String s=null;
									
										s=dish.name+"/"+dish.price;//record dish name and price.
										bw.write(s);
										bw.write("\r\n");//turn to another line.
										bw.flush();
										bw.close();//close buffer writer.
							          }  catch(Exception e1){
										e1.printStackTrace();
									   }
									
						    	 
						    	 confirmPanel.setVisible(false);
						    	 showPanel.setVisible(false);
						    	 
						    	 writeDishInfro(myfile);//renew the rest number of a dish in the file.
						    	 
						    	 goNextConfirm(total,userFile);//call a method to go next interface.
						       }
						     }  
					     );//confirm button 
						
						backButton.addActionListener(new ActionListener(){// an inner class which allow user to go back to main menu.
						  public void actionPerformed(ActionEvent e){
							      timer.restart();
						    	  confirmPanel.setVisible(false);
						    	  showPanel.setVisible(false);
						       	  goMainMenu();//call a method to go back to main menu to order again.
						    	 } 
						       } 
					          );//back button 
					       
				    	
				       }
					  }
					);
				
				myList.add(dish);//add dish into ArrayList myList.
				if(j==1)//set back ground color of different buttons.
					button[i].setBackground(color1);
				if(j==2)
					button[i].setBackground(color2);
				if(j==3)
					button[i].setBackground(color3);
				if(j==4)
					button[i].setBackground(color4);
				if(j==5)
					button[i].setBackground(color5);
				if(j==6)
					button[i].setBackground(color6);
			
				
			}//over the for loop
			
			br.close();//close the buffer reader.
			fr.close();//close the file reader.
			
		}catch(Exception e11){//catch error when read file.
			e11.printStackTrace();
		}
		
}//finish the dishPage method.
	
	
	/**a method renew the dish information in the original files.
	 * @param myfile A type of File and clarify which file need to be renew.
	 */
public void writeDishInfro (File myfile){
	
	try{
			FileWriter bw=new FileWriter(myfile);//open a file by file writer.
			
			String s=null;
		
			for(int i=0;i<myList.size();i++){
				DishInfro temDishInfro=(DishInfro)myList.get(i);//put myList information into temDishInfro.
				s=temDishInfro.name+"/"+temDishInfro.price+"/"+temDishInfro.rest;//take them into String s.
				bw.write(s);//write s into file.
				bw.write("\r\n");//turn another lien.
				bw.flush();
			 }
            bw.close();//close file writer.
          }  catch(Exception e){
			e.printStackTrace();
		   }  
}


/** a method which  show the confirm information of ordering 
 * which contains continue to order and waiting for food buttons
 * @param total, userFile Total: the total price of a user. userFile: show ordering detail on the interface
 * to tell costumer what he/she orders.
 * @param total Total: the total price of the bill.
 * @param userFile The dish information of the costumer ordered.
 */
	public void goNextConfirm(int total,File userFile){
		
		JPanel nextPanel=new JPanel();
		JPanel payPanel=new JPanel();
		
		payPanel.setLayout(new GridLayout(1,0));
		nextPanel.setLayout(new GridLayout(0,2));
		nextPanel.setBackground(backGroundColor);
		payPanel.setBackground(backGroundColor);
		
		JButton payButton=new JButton("Waiting for food");
		payButton.setFont(f20);
		payButton.addActionListener(//an inner class which can go next page and serving dishes.
				new ActionListener(){
				    public void actionPerformed(ActionEvent e){
				    	timer.restart();
				    	nextPanel.setVisible(false);
				    	payPanel.setVisible(false);
				    	tellJoke();//call the method which can tell joke.
				    }
				}
			);//pay button
		
		JButton continueButton=new JButton("Continue to order");
		continueButton.setFont(f20);
		continueButton.addActionListener(
				new ActionListener(){
				    public void actionPerformed(ActionEvent e){//an inner class which can back to main menu and continue to order.
				    	timer.restart();
				    	nextPanel.setVisible(false);
				    	payPanel.setVisible(false);
				    	goMainMenu();//back to main menu.
				    }
				}
			);
	
		
		int i;
		String line=null;
		
		
		try//try to read a file.
		{
			
			FileReader fr = new FileReader("Files/userFile");//read userFile
			BufferedReader br = new BufferedReader(fr);
			line=br.readLine();
			for(i=0;line!=null;i++)
			{
				String[] result=line.split("/");//split with"/" and store them into array result.
				S=S+"name: "+result[0]+"/ price: "+result[1]+"         yuan;   ";//put array result into a string.
				line=br.readLine();//read line of file.
				
		       
			}
			
			br.close();
			fr.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		  JLabel mylabel=new JLabel("<html><body>"+"Bill details: "+"<br>"+S+"</body></html>"); //create a new label and show string s on it.   
		  mylabel.setFont(f35);
		  
		  
		  
		
		payPanel.add(mylabel);
		nextPanel.add(continueButton);
		nextPanel.add(payButton);
		
		mainFrame.setTitle("Check Out");
		mainFrame.getContentPane().add(BorderLayout.CENTER,payPanel);
		mainFrame.getContentPane().add(BorderLayout.SOUTH,nextPanel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	/** a method shows telling joke interface after costumer finish order 
	 * and waiting for serving dishes.
	 */
	public void tellJoke(){
		JPanel servingPanel=new JPanel();
		JPanel optionPanel=new JPanel();
		
		servingPanel.setBackground(backGroundColor);
		optionPanel.setBackground(backGroundColor);
		
        int i,j;
		String line=null;
		JLabel jokeLabel=new JLabel();
		int max=(int)(Math.random()*4);
        String JokeMessage=null;
		
		try//read joke message file. just the same as above file reader code.
		{
			
			FileReader fr = new FileReader("Files/JokeMessage");
			BufferedReader br = new BufferedReader(fr);	
		   	line=br.readLine();
			for(i=0;line!=null;i++)
			{
				if(i==max){
					String[] result=line.split("/"); 
				    jokeLabel.setText("<html><body>"+"<br>"+"<br>"+result[0]+"<br>"+result[1]+"<br>"+result[2]+"</body></html>"); 
				    jokeLabel.setFont(f30);
		            jokeLabel.setBackground(backGroundColor);
		            }
				line=br.readLine();
			}
			br.close();
			fr.close();
			
		}catch(Exception e11){
			e11.printStackTrace();
		}
			    
		        	
		JButton stopButton=new JButton("stop");//stop the robot telling joke.
		stopButton.setFont(f20);
		stopButton.addActionListener(
				new ActionListener(){
				    public void actionPerformed(ActionEvent e){//an inner class the robot asked to not tell jokes.
				    	timer.restart();
				    	jokeLabel.setText("<html><body>"+"<br>"+"<br>"+"Ok!!!I'm QUIET!"+"</body></html>");
				    	jokeLabel.setFont(f40);
				    	stopButton.setEnabled(false);
				    }
				}
				);//STOPBUTTON
		
		JButton backOrderButton=new JButton("Order");
		backOrderButton.setFont(f20); 
		backOrderButton.addActionListener(
				new ActionListener(){// an inner class can back to main menu.
				    public void actionPerformed(ActionEvent e){
				    	timer.restart();
				    	servingPanel.setVisible(false);
				    	optionPanel.setVisible(false);
				    	goMainMenu();
				    }
				}
				);//back button
		
		
		JButton leaveButton=new JButton("leave");
		leaveButton.setFont(f20);
		leaveButton.addActionListener(
				new ActionListener(){// an inner class which costumer ask to leave.
				    public void actionPerformed(ActionEvent e){
				    	timer.restart();
				    	servingPanel.setVisible(false);
				    	optionPanel.setVisible(false);
				    	leavingPay();//call a method which can show bye- bye message.
				    	
				    }
				}
			);//leave button 
		
		servingPanel.add(jokeLabel);//add labels into panels
		optionPanel.add(backOrderButton);
		optionPanel.add(stopButton);
		optionPanel.add(leaveButton);
		
		mainFrame.setTitle("Serving Dishes");
		mainFrame.getContentPane().add(BorderLayout.CENTER,servingPanel);//add panel into frame.
		mainFrame.getContentPane().add(BorderLayout.SOUTH,optionPanel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}
	
	/** a method shows the bill details after the costumer choose to leave
	 */
	public void leavingPay(){
		
		JPanel leaving=new JPanel();
		leaving.setBackground(backGroundColor);
		leaving.setLayout(new GridLayout(1,0));
		
		JPanel pay=new JPanel();
		pay.setBackground(backGroundColor);
		
		JButton payButton=new JButton("pay the bill");//create a new button.
		payButton.setFont(f20);
		payButton.addActionListener(
				new ActionListener(){//an inner class which can call bye-bye method.
				    public void actionPerformed(ActionEvent e){
				    	timer.restart();
				    	leaving.setVisible(false);
				    	pay.setVisible(false);
				    	sayByebye();//call method to show random selected farewell message.
				    }
				}
			);
		
		// create a new label which shows the bill detail of the costumer ordering.
		JLabel payLabel=new JLabel("<html><body>"+"Bill details: "+"<br>"+S+"<br>"+"the total price is: "+total+" yuan"+"</body></html>");
	    payLabel.setFont(f35);
	    
	    leaving.add(payLabel);//add label on the panel.
	    pay.add(payButton);
	    
	    mainFrame.setTitle("Paying and leave");
		mainFrame.getContentPane().add(BorderLayout.CENTER,leaving);
		mainFrame.getContentPane().add(BorderLayout.SOUTH,pay);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    
	}



	/** a method can read file of farewell message.
	 * @return fareWellMessage. The random selected farewell message from farewell file.
	 */
	public String readFareWellMessage(){
		
			
			int i;
			String line=null;
	        int max=(int)(Math.random()*5);//create a new random number.
	        String FareWellMessage=null;
			try
			{
				
				
				File FareWellfile=new File("Files/FareWellMessage");//open the file.
				FileReader fr = new FileReader(FareWellfile);
				BufferedReader br = new BufferedReader(fr);
				
				for(i=0;(line=br.readLine())!=null;i++)
				{
					if(i==max){//if line number equals to random number.
					FareWellMessage = br.readLine();//read the line of the file and save it in the farewell message.
					}
				}
				
				br.close();
				fr.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return FareWellMessage;
		}//welcome message
	
	
	/** a method will show the last user interface.
	 * which contains a label shows bye-bye message and a finish button.
	 */
	public void sayByebye(){
		
		JPanel byePanel=new JPanel();
		JPanel finishPanel=new JPanel();
		
		byePanel.setBackground(backGroundColor);
		finishPanel.setBackground(backGroundColor);
		
		JLabel byeLabel=new JLabel(readFareWellMessage(),JLabel.CENTER);//return farewell message and show it on the label.
		byeLabel.setFont(f60);
		byeLabel.setBackground(backGroundColor);
		
		JLabel warnLabel=new JLabel("I'm looking forward to seeing you again",JLabel.CENTER);
		warnLabel.setBackground(backGroundColor);
		warnLabel.setFont(f30);
		
		JButton finishButton=new JButton ("FINISH");//create a finish button.
		finishButton.setFont(f30);
		finishButton.addActionListener(//an inner class which can clear all information of the former user record.
				new ActionListener(){
				    public void actionPerformed(ActionEvent e){
				    	timer.restart();
				    	byePanel.setVisible(false);
				    	finishPanel.setVisible(false);
				    	total=0;//reset total price.
				    	userFile.delete();//delete former user file.
				    	S=null;//reset S;
				    	goWelcome();
				 
				    					    	
				    }//action perform
				}//action Listener
			);//finishButton 
		
		byePanel.setLayout(new GridLayout(2,1));
	    byePanel.add(byeLabel);
	    byePanel.add(warnLabel);
	    finishPanel.add(finishButton);
	    
	    mainFrame.setTitle("FareWell");//set new title of a frame.
	    mainFrame.getContentPane().add(BorderLayout.CENTER,byePanel);
	    mainFrame.getContentPane().add(BorderLayout.SOUTH,finishPanel);
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		
	}
	
 /** a method when the robot is inactive for 30 seconds. it will go a sleeping mode.
  * and show a message dialog to warn user. 
  * it can be waked up for any time.
  */
	public void goSleep(){
		timer.stop();//stop the timer.
		//generate a message dialog to warn user.
		JOptionPane.showMessageDialog(this,"I'm sleeping...","SleepingMode",JOptionPane.WARNING_MESSAGE);
	
		
	}

}//main class


