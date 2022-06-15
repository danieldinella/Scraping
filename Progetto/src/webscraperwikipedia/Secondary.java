package webscraperwikipedia;
import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.google.common.io.Files;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.image.BufferedImage;

/**
 * From the Home class, by performing the first search, you will automatically 
 * switch to the Secondary class, which has the function of showing on screen 
 * the image of the family tree of the emperor just searched and to continue with 
 * the search by updating the image in the center of the window. 
 * Also you can make image saves and view the history.
 *
 * @author Enrico Chiarello
 * @version 1.6.6
 */
public class Secondary extends javax.swing.JFrame {
	
	// Variables declaration - do not modify 
	/**
	 * dynasties menu 
	 */
	private javax.swing.JComboBox<String> jComboBox1;
	/**
	 * key to search by menu
	 */
    private javax.swing.JButton btnSearchComb;
    /**
     *  the text area that acquires the name of the emperor
     */
    private javax.swing.JTextField txtName;
    /**
     * key to search by writing the name
     */
    private javax.swing.JButton btnSerchName;
    /**
     * the central writing
     */
    private javax.swing.JLabel lbTitle1; 
    /**
     * written at the bottom right
     */
    private javax.swing.JLabel lbTitle2;
    /**
     * written at the bottom left
     */
    private javax.swing.JLabel lbTitle3;
    /**
     * the menu bar at the top of the window
     */
    private javax.swing.JMenuBar jMenuBar;
    /**
     * menu containing two items
     */
    private javax.swing.JMenu jMenu;
    /**
     * "open" item of menu
     */
    private javax.swing.JMenuItem open;
    /**
     * "save" item of menu
     */
    private javax.swing.JMenuItem save;
    /**
     * processed image shown on screen
     */
    private javax.swing.JLabel lbImage;
    /**
     * chooses the path for saving
     */
    private javax.swing.JFileChooser jFileChooser;
    /**
     * scroll panel that contains the image
     */
    private javax.swing.JScrollPane jScrollPane;
    /**
     * legend image
     */
    private javax.swing.JLabel lbLegend;
    /**
     * counter for history and downloads
     */
    private int c = 1;
    /**
     * initial image in the history
     */
    private ImageIcon i = new ImageIcon("history\\tree0.png"); 
    /**
     * path of the initial image in the history
     */
	private String currentPath = "history\\tree0.png";  
    // End of variables declaration   

    /**
     * Constructor that calls the initComponents() function and 
     * defines the color and position of the window on the screen.
     */
    public Secondary() {
        initComponents();
        getContentPane().setBackground(new java.awt.Color (190, 50, 52)); // color of frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // When you close the secondary window, the home window remains visible
        setLocationRelativeTo(null);  // centering frame
    }
    
	/**
     * This method is called from within the constructor to initialize the form. 
     * In particular it deals with the positioning, size, background, color and leyout
     *  in general of each graphic attribute. It also declares the listeners who have 
     *  the task of triggering events and then recall methods.
     */                        
    private void initComponents() {

    	jFileChooser = new javax.swing.JFileChooser(); 
        lbImage = new javax.swing.JLabel(); 
        lbTitle1 = new javax.swing.JLabel(); 
        lbTitle2 = new javax.swing.JLabel(); 
        lbTitle3 = new javax.swing.JLabel(); 
        btnSerchName = new javax.swing.JButton(); 
        txtName = new javax.swing.JTextField(); 
        jComboBox1 = new javax.swing.JComboBox<>(); 
        btnSearchComb = new javax.swing.JButton(); 
        jMenuBar = new javax.swing.JMenuBar(); 
        jMenu = new javax.swing.JMenu(); 
        open = new javax.swing.JMenuItem(); 
        save = new javax.swing.JMenuItem(); 
        jScrollPane = new javax.swing.JScrollPane(); 
        lbLegend = new javax.swing.JLabel(); 

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); // ti permette di fermare il programma quando chiudi la finestra

        lbImage.setIcon(i); // sets the opening image
        lbImage.setHorizontalAlignment(JLabel.CENTER); // centering image
        
        // settings for the scroll panel that contains the image
        jScrollPane.setViewportView(lbImage);
        jScrollPane.getViewport().setBackground(new java.awt.Color (190, 50, 52));
        jScrollPane.setBorder(null);
        
        lbLegend.setIcon(new javax.swing.ImageIcon("legenda.png")); // load the legend image

        // settings for the central writing
        lbTitle1.setFont(new java.awt.Font("Castellar", 3, 14));
        lbTitle1.setText("Here is his family tree! ");
        lbTitle1.setForeground(new Color(255, 255, 255));

        // settings for the written at the bottom right
        lbTitle2.setFont(new java.awt.Font("Castellar", 3, 14));
        lbTitle2.setText("Choose the dynasty:");
        lbTitle2.setForeground(new Color(255, 255, 255));
        
        // settings for the written at the bottom left
        lbTitle3.setFont(new java.awt.Font("Castellar", 3, 14));
        lbTitle3.setText("Search by name:");
        lbTitle3.setForeground(new Color(255, 255, 255));
        
        // listener on the button to search by writing the name
        btnSerchName.setText("Search");
        btnSerchName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSerchNameActionPerformed(evt);
            }
        });
        
        // listener on closing the window
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        // settings for the text area that acquires the name of the emperor
        txtName.setForeground(new java.awt.Color(153, 153, 153));
        txtName.setText("Write a name...");
        txtName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNameMouseClicked(evt);
            }
        });

        // setting the name of the emperors in the drop-down menu
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Augusto (Giulio Claudii,  27 a.C.-14 d.C.)", "Vespasiano (Flavii, 68-79)", "Adriano (Antonini, 117-138)", "Settimio_Severo (Severi, 193-211)", "Valeriano (Valeriani, 253-260)", "Costantino (Costantiniani, 306-337)" }));

        // listener on the button to search by drop-down menu
        btnSearchComb.setText("Search");
        btnSearchComb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchCombActionPerformed(evt);
            }
        });
        
        jMenu.setText("File"); // set menu name

        open.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        open.setText("Open history");
        // listener on the "open" menu item
        open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openActionPerformed(evt);
            }
        });
        
        jMenu.add(open); // add menu item

        save.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        save.setText("Save");
        // listener on the "open" menu item
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        
        jMenu.add(save); // add menu item

        jMenuBar.add(jMenu); // add menu

        setJMenuBar(jMenuBar); 

        // various settings for the layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(354, 354, 354)
                .addComponent(lbTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 483, Short.MAX_VALUE)
                .addComponent(lbLegend)
                .addGap(43, 43, 43))
            .addGroup(layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(btnSearchComb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSerchName)
                .addGap(170, 170, 170))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(lbTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitle3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(121, 121, 121))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(lbTitle1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbLegend, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTitle2)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearchComb))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbTitle3)
                        .addGap(18, 18, 18)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSerchName)))
                .addGap(29, 29, 29))
        );
        // end various settings

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
                System.exit(0); // if the user clicks on "Yes" the system is shut down
            }
    }

    /**
     * In this method, search by name. If the input is correct, the new image 
     * created is displayed on the screen. The images that are created from time 
     * to time are kept in the history of the history and are numbered according to the counter.
     * 
     * @param evt   listener of the button click
     */
    private void btnSerchNameActionPerformed(java.awt.event.ActionEvent evt) {                                         

        String input = txtName.getText();  // takes what is written in txtName
        
        if (input.equals("Write a name...") || input.equals("")){ // if you search without writing anything it launches an error panel
        	JOptionPane.showMessageDialog(this, "Error! Enter valid input...", "Error", 0);
        }
        else {
	        int i = 0;
	        String link = "https://it.wikipedia.org/wiki/" + input; // prepare the name with what it finds written in the txtName area
	        
	        try {
	        	// composition of the data of the person or emperor sought
	        	StringProcessor.resetCodifica();
	        	Person per = new Person(input, link);
	        	per.setCheckImp();
	        	if(per.getCheckImp()) {  // if the name sought is the name of an emperor
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
		        	TreeImage.createImage(c); // numbered image
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
		        	TreeImage.createImage(c); // numbered image
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
	        	// if the search is successful the new image just created is shown on the screen
	        	currentPath = "history\\tree" + c + ".png";
	        	lbImage.setIcon(new javax.swing.ImageIcon(currentPath));       	
	        	c++; // increment the image number
	        }
        }
    }       
    
    /**
     * In this method, you search by dynasty from the drop-down menu. 
     * The newly created image is displayed on the screen. The images that are 
     * created from time to time are kept in the history of the history and are 
     * numbered according to the counter.
     * 
     * @param evt   listener of the button click
     */
    private void btnSearchCombActionPerformed(java.awt.event.ActionEvent evt) {                                          
        
    	String input = (String) jComboBox1.getSelectedItem(); // takes what is selected on the drop-down menu
    	input = input.substring(0, input.indexOf(' ')); // processes the obtained string
        int i = 0;
        String link = "https://it.wikipedia.org/wiki/" + input; // prepare the link with the selected name
        
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
	        	TreeImage.createImage(c); // numbered image
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
	        	TreeImage.createImage(c); // numbered image
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
        	// if the search is successful the new image just created is shown on the screen
        	currentPath = "history\\tree" + c + ".png";
        	lbImage.setIcon(new javax.swing.ImageIcon(currentPath));       	
        	c++; // increment the image number
        }
    }     
    
    /**
     * Selects the location of a folder where the current image will then be saved.
     * 
     * @param evt   listener of the button click
     */
    private void saveActionPerformed(java.awt.event.ActionEvent evt) {                                     
        
    	jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  // the fileChooser selects only folders and not files
       	int response = jFileChooser.showOpenDialog(null);  // to read the path
        if (response == jFileChooser.APPROVE_OPTION){  // after selecting the path on which to save the image
            File f = new File(jFileChooser.getSelectedFile().getAbsolutePath() + "\\download" + c + ".png"); // create the copy file
            File curr = new File(currentPath); // image file you want to download
            try {
            	Files.copy(curr, f);    // make the copy
            	JOptionPane.showMessageDialog(this, "Image saved successfully!", "Success", 1);  // success message
            }
            catch (Exception e){
            	JOptionPane.showMessageDialog(this, "Attention! File not exist ...", "Attention", 2); // operation failed message
            }
        }
    	
    }
    
    /**
     * Open the history folder to view the entire history from the start of the program.
     * 
     * @param evt   listener of the button click
     */
    private void openActionPerformed(java.awt.event.ActionEvent evt) {                               
        
    	try {
    		Desktop.getDesktop().open(new File("history"));  // shows the history folder on the screen
        } catch (IOException ex) {
        	JOptionPane.showMessageDialog(this, "Attention! Directory doesn't exist ...", "Attention", 2);  // error message
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
            java.util.logging.Logger.getLogger(Secondary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Secondary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Secondary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Secondary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Secondary().setVisible(true);
            }
        });
    }                
}