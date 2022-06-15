package webscraperwikipedia;
import java.awt.Color;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
*
* If you run the Home class, a frame will appear on the screen.
* Home, therefore, is the window from which the user will have to start before 
* viewing any image and then before moving to the Secondary class. 
* It is an introduction class where you can understand the purpose and 
* functionality of the project.
* 
* @author Enrico Chiarello
* @version 1.6.6
*/
public class Home extends javax.swing.JFrame {
	
	// Variables declaration - do not modify    
	/**
     * the central writing
     */
    private javax.swing.JLabel lbTitle;
    /**
     * key to search by writing the name
     */
    private javax.swing.JButton btnSearchName;
    /**
     * key to search by menu
     */
    private javax.swing.JButton btnSearchComb;
    /**
     * dynasties menu
     */
    private javax.swing.JComboBox<String> jCombBox;
    /**
     * the menu at the top of the window
     */
    private javax.swing.JMenuBar menu;
    /**
     * the text area that acquires the name of the emperor
     */
    private javax.swing.JTextField txtName;
    /**
     * "info" menu item
     */
    private javax.swing.JMenu voice1;
    /**
     * first panel for graphics
     */
    private javax.swing.JPanel jPanel1;
    /**
     * second panel for graphics
     */
    private javax.swing.JPanel jPanel2;
    // End of variables declaration   
	
	/**
     * Constructor that calls the initComponents() function 
     * and defines the color and position of the window on the screen
     * 
     */
    public Home() {
        initComponents();
        getContentPane().setBackground(new java.awt.Color (190, 50, 52)); // color of frame
        setLocationRelativeTo(null);  // centering frame
    } 

    /**
     * This method is called from within the constructor to initialize the form. 
     * In particular it deals with the positioning, size, background, color and 
     * leyout in general of each graphic attribute. It also declares the 
     * listeners who have the task of triggering events and then recall methods
     * 
     */                       
    private void initComponents() {

    	lbTitle = new javax.swing.JLabel(); 
        txtName = new javax.swing.JTextField(); 
        btnSearchName = new javax.swing.JButton(); 
        jCombBox = new javax.swing.JComboBox<>(); 
        btnSearchComb = new javax.swing.JButton(); 
        menu = new javax.swing.JMenuBar(); 
        voice1 = new javax.swing.JMenu(); 
        jPanel1 = new javax.swing.JPanel(); 
        jPanel2 = new javax.swing.JPanel(); 

        // adds a listener to the window that allows you to stop the program when you close the window
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        
        // settings for the title writing
        lbTitle.setFont(new java.awt.Font("Castellar", 3, 14)); 
        lbTitle.setText("Hi, this is a web scraper, start looking!");
        lbTitle.setForeground(new Color(255, 255, 255));
        
        // settings for the first panel
        jPanel1.setBackground(new java.awt.Color(190, 50, 52));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "By name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setToolTipText("");

        // settings for the text area that acquires the name of the emperor
        txtName.setForeground(new java.awt.Color(153, 153, 153));
        txtName.setText("Write a name...");
        txtName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNameMouseClicked(evt);
            }
        });        

        // settings for the key to search by writing the name
        btnSearchName.setText("Search");
        btnSearchName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchNameActionPerformed(evt);
            }
        });
        
        // settings for the positioning and layout of the first panel
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(btnSearchName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearchName)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        
        // settings for the second panel
        jPanel2.setBackground(new java.awt.Color(190, 50, 52));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "By dinastry", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setToolTipText("");
        
        // setting the name of the emperors in the drop-down menu
        jCombBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Augusto (Giulio Claudii,  27 a.C.-14 d.C.)", "Vespasiano (Flavii, 68-79)", "Adriano (Antonini, 117-138)", "Settimio_Severo (Severi, 193-211)", "Valeriano (Valeriani, 253-260)", "Costantino (Costantiniana, 306-337)" }));

        // settings for the positioning and layout of the second panel
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jCombBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(btnSearchComb)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jCombBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearchComb)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        
        // settings for the key to search by writing the drop-down menu
        btnSearchComb.setText("Search");
        btnSearchComb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchCombActionPerformed(evt);
            }
        });
        
        // settings the menu item
        voice1.setIcon(new javax.swing.ImageIcon("info.png")); // adds the icon to the menu item
        voice1.setText("Info");
        voice1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                voice1MouseClicked(evt);
            }
        });
        
        menu.add(voice1);// adds the item to the menu 

        setJMenuBar(menu);

        // various settings for the layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        // end various settings
        
        lbTitle.setHorizontalAlignment(JLabel.CENTER); // centering the title
        
        pack();
               
    }                                                      

    /**
     * When the user clicks on the text area of the search by name, 
     * the gray "Write a name..." is removed and the color is set to black.
     * 
     * @param evt   listener of the mouse click
     */
    private void txtNameMouseClicked(java.awt.event.MouseEvent evt) {              
        txtName.setText(""); 
        txtName.setForeground(new Color(0,0,0));
    }                                    

    /**
     * When the user clicks on the "info" icon, 
     * a window opens with a short description.
     * 
     * @param evt  listener of the mouse click
     */
    private void voice1MouseClicked(java.awt.event.MouseEvent evt) {                                    
        JOptionPane.showMessageDialog(this, "Here you can search for the family \n"
                    + "tree of any Roman emperor. \n This is a web scaper!", "Info", 1); //1 as a parameter because it is a message panel.
        
    }                                   

    /**
     * In this method you can make the first search (by writing the name) that follows 
     * the creation of an image. To display the history correctly, this method deletes 
     * the history of previous searches. If the input is correct, from here you go to 
     * the second frame.
     * 
     * @param evt    listener of the button click
     */
    private void btnSearchNameActionPerformed(java.awt.event.ActionEvent evt) {                                         

        String s = txtName.getText(); // takes what is written in txtName
        
        if (s.equals("Write a name...") || s.equals("")){  // if you search without writing anything it launches an error panel
        	JOptionPane.showMessageDialog(this, "Error! Enter valid input...", "Error", 0);
        }
        else {
        	//s = s.replaceAll(" ", "");
        	//s = s.toLowerCase();
        	int i = 0;
        
        	File f = new File("history");
        	// if the "history" folder does not exist it creates it
        	if (!f.exists()) {
        		f.mkdir();
        	}
        	// otherwise it empties it
        	else {
	        	String[] v = f.list();
	        	for (String s1 : v) {
	        		File f1 = new File("history\\" + s1);
	        		f1.delete();
	        	}
	        }
	        
	        String link = "https://it.wikipedia.org/wiki/" + s; // prepare the name with what it finds written in the txtName area
	        
	        try {
	        	// composition of the data of the person or emperor sought
	        	StringProcessor.resetCodifica();
	        	Person per = new Person(s, link);
	        	per.setCheckImp();
	        	if (per.getCheckImp()) {  // if the name sought is the name of an emperor
	        			per.closeDriver(per.getDriver());
	        			// get the emperor's information
	                	Imperatore imp = new Imperatore(s, link);
	                	imp.setDinastia();
	                	imp.setPadre();
	                	imp.setMadre();
	                	imp.setConiuge();
	                	imp.setCheckImp();
	                	imp.setMandato();
	                	imp.setFigli();

	                	// image creation
	    	        	StringProcessor.processString(imp, "");
	    	        	System.out.println(StringProcessor.getCodifica());
	    	        	TreeImage.setCodifica(StringProcessor.getCodifica());
	    	        	TreeImage.createImage(0); // image with starting name zero
	    	        	
	               	}
	                else {
	                	// get the person's information
	                	per.setDinastia();
	                    per.setPadre();
	                   	per.setMadre();
	                   	per.setConiuge();
	                   	per.setFigli();
	                   	//TreeImage.processString(per, "");
	                   	
	                   	// image creation
	    	        	StringProcessor.processString(per, "");
	    	        	TreeImage.setCodifica(StringProcessor.getCodifica());
	    	        	TreeImage.createImage(0); // image with starting name zero
	                	}
			
	        }
	        catch (Exception e) {
	        	i = -1;  // if it throws an exception, it allows us to manage it and show the error panel on the screen
	        }		
			
	        if (i == -1){
	        	// search panel failed
	            JOptionPane.showMessageDialog(this, "Attention! The page wikipedia"
	            		+ " not exist ...", "Attention", 2);
	        }
	        else{
	        	// if the search is successful you go to the second window where you can view the image
	            Secondary window = new Secondary();
	            window.show();
	            dispose(); // closes this frame
	        }
        }
    }                                         

    /**
     * In this method you can make the first search (selecting the dynasty 
     * from the drop-down menu) that follows the creation of an image. 
     * To display the history correctly, this method deletes the history of previous searches. 
     * If the input is correct, from here you go to the second frame.    
     * 
     * @param evt  listener of the button click
     */
    private void btnSearchCombActionPerformed(java.awt.event.ActionEvent evt) {                                          
   
    	String input = (String) jCombBox.getSelectedItem(); // takes what is selected on the drop-down menu
    	input = input.substring(0, input.indexOf(' '));  // processes the obtained string
        int i = 0;
        String link = "https://it.wikipedia.org/wiki/" + input;  // prepare the link with the selected name
        
        File f = new File("history");
        // if the "history" folder does not exist it creates it
    	if (!f.exists()) {
    		f.mkdir();
    	}
    	// otherwise it empties it
    	else {
        	String[] v = f.list();
        	for (String s1 : v) {
        		File f1 = new File("history\\" + s1);
        		f1.delete();
        	}
        }
        
        try {
        	// composition of the data of the person or emperor sought
        	StringProcessor.resetCodifica();
        	Person per = new Person(input, link);
        	per.setCheckImp();
        	if(per.getCheckImp()) { // if the name sought is the name of an emperor
        		per.closeDriver(per.getDriver());
        		// get the emperor's information
				Imperatore imp = new Imperatore(input, link);
				imp.setDinastia();
				imp.setPadre();
				imp.setMadre();
				imp.setConiuge();
				imp.setCheckImp();
				imp.setMandato();
				imp.setFigli();
				imp.closeDriver(imp.getDriver());
				
				// image creation
				StringProcessor.processString(imp, "");
	        	TreeImage.setCodifica(StringProcessor.getCodifica());
	        	TreeImage.createImage(0);  // image with starting name zero
			}
			else {
				// get the person's information
				per.setDinastia();
				per.setPadre();
				per.setMadre();
				per.setConiuge();
				per.setFigli();
				per.closeDriver(per.getDriver());
				
				// image creation
				StringProcessor.processString(per, "");
	        	TreeImage.setCodifica(StringProcessor.getCodifica());
	        	TreeImage.createImage(0);  // image with starting name zero
			}
		
        }
        catch (Exception e) {
        	i = -1;  // if it throws an exception, it allows us to manage it and show the error panel on the screen
        }		
		
        if (i == -1){
        	// search panel failed
            JOptionPane.showMessageDialog(this, "Attention! The page wikipedia"
            		+ " not exist ...", "Attention", 2);
        }
        else{
        	// if the search is successful you go to the second window where you can view the image
            Secondary window = new Secondary();
            window.show();
            dispose(); // closes this frame
        }
    } 
    
    /**
     * It is called when you try to close the frame. 
     * In fact, when you click on the "x" a confirmation message is generated.
     * 
     * @param evt  listener of window click
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {                               

        int Answer = JOptionPane.showConfirmDialog(null, "Are you sure want to exit?", "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (Answer == JOptionPane.NO_OPTION) {
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // if the user clicks on "No" nothing happens
            }
            if (Answer == JOptionPane.YES_OPTION) {
                System.exit(0);  // if the user clicks on "Yes" the system is shut down
            }
    } 

    // to start on the screen 
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }                
}