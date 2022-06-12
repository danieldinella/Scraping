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
 *
 * @author Enrico
 */
public class Secondary extends javax.swing.JFrame {

    /**
     * Creates new form Secondary
     */
    public Secondary() {
        initComponents();
        getContentPane().setBackground(new java.awt.Color (190, 50, 52)); // color of frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // When you close the secondary window, the home window remains visible
        setLocationRelativeTo(null);  // centering frame
    }
    
    public int c = 1;
    public ImageIcon i = new ImageIcon("history\\tree0.png"); // initial image in the history
	public String currentPath = "history\\tree0.png";  // path of the initial image in the history

	/**
     * This method is called from within the constructor to initialize the form.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                         
    private void initComponents() {

    	jFileChooser = new javax.swing.JFileChooser(); // chooses the path for saving
        lbImage = new javax.swing.JLabel(); // processed image shown on screen
        lbTitle1 = new javax.swing.JLabel(); // the central writing
        lbTitle2 = new javax.swing.JLabel(); // written at the bottom right
        lbTitle3 = new javax.swing.JLabel(); // written at the bottom left
        btnSerch = new javax.swing.JButton(); // key to search by writing the name
        txtName = new javax.swing.JTextField(); // the text area that acquires the name of the emperor
        jComboBox1 = new javax.swing.JComboBox<>(); // dynasties menu 
        btnSearch2 = new javax.swing.JButton(); // key to search by menu
        jMenuBar = new javax.swing.JMenuBar(); // the menu at the top of the window
        jMenu = new javax.swing.JMenu(); // bar containing two items
        open = new javax.swing.JMenuItem(); // "open" item of menu
        save = new javax.swing.JMenuItem(); // "save" item of menu
        jScrollPane = new javax.swing.JScrollPane(); // scroll panel that contains the image
        lbLegend = new javax.swing.JLabel(); // legend image

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
        btnSerch.setText("Search");
        btnSerch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSerchActionPerformed(evt);
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
        btnSearch2.setText("Search");
        btnSearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch2ActionPerformed(evt);
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
                .addComponent(btnSearch2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSerch)
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
                        .addComponent(btnSearch2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbTitle3)
                        .addGap(18, 18, 18)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSerch)))
                .addGap(29, 29, 29))
        );
        // end various settings

        pack();
    }// </editor-fold>                        

    private void txtNameMouseClicked(java.awt.event.MouseEvent evt) {                                     
        
        txtName.setText("");
        txtName.setForeground(new Color(0,0,0));
        /* When the user clicks on the text area,
        the writing is removed and the color is set to black
        */
    }                                    
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {     //  is called when you try to close the frame                              

        int Answer = JOptionPane.showConfirmDialog(null, "Are you sure want to exit?", "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (Answer == JOptionPane.NO_OPTION) {
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // if the user clicks on "No" nothing happens
            }
            if (Answer == JOptionPane.YES_OPTION) {
                System.exit(0); // if the user clicks on "Yes" the system is shut down
            }
        /*
            When you click on the "x" it generates a confirmation message
            */
    }

    private void btnSerchActionPerformed(java.awt.event.ActionEvent evt) {     // it is called up when you press the search by name button                                    

        String input = txtName.getText();  // takes what is written in txtName
        
        if (input.equals("Write a name...")){ // if you search without writing anything it launches an error panel
        	JOptionPane.showMessageDialog(this, "Error! Enter valid input...", "Error", 0);
        }
        else {
	        int i = 0;
	        String link = "https://it.wikipedia.org/wiki/" + input; // prepare the name with what it finds written in the txtName area
	        
	        try {
	        	// composition of the data of the person or emperor sought
	        	StringProcessor.resetCodifica();
	        	Person per = new Person(input, link);
	        	if(per.getCheckImp()) {  // if the name sought is the name of an emperor
	        		per.closeDriver(per.getDriver());
	        		// get the emperor's information
					Imperatore imp = new Imperatore(input, link, true);
					imp.setDinastia();
					imp.setPadre();
					imp.setMadre();
					imp.setConiuge();
					imp.setCheckImperatore();
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
    
    private void btnSearch2ActionPerformed(java.awt.event.ActionEvent evt) {    // it is called up when you press the search by dinasty button                                       
        
    	String input = (String) jComboBox1.getSelectedItem(); // takes what is selected on the drop-down menu
    	input = input.substring(0, input.indexOf(' ')); // processes the obtained string
        int i = 0;
        String link = "https://it.wikipedia.org/wiki/" + input; // prepare the link with the selected name
        
        try {
        	// composition of the data of the person or emperor sought
        	StringProcessor.resetCodifica();
        	Person per = new Person(input, link);
        	if(per.getCheckImp()) { // if the name sought is the name of an emperor
        		per.closeDriver(per.getDriver());
        		// get the emperor's information
				Imperatore imp = new Imperatore(input, link, true);
				imp.setDinastia();
				imp.setPadre();
				imp.setMadre();
				imp.setConiuge();
				imp.setCheckImperatore();
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
    
    private void saveActionPerformed(java.awt.event.ActionEvent evt) {    // it is called when you click on the "save" menu item                                 
        
    	jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  // the fileChooser selects only folders and not files
       	int response = jFileChooser.showOpenDialog(null);  // to read the path
        if (response == jFileChooser.APPROVE_OPTION){  // after selecting the path on which to save the image
            File f = new File(jFileChooser.getSelectedFile().getAbsolutePath() + "\\download" + c + ".png"); // create the copy file
            File curr = new File(currentPath); // image file you want to download
            try {
            	Files.copy(curr, f);    // make the copy
            	JOptionPane.showMessageDialog(this, "Rescue successful!", "Success", 1);  // success message
            }
            catch (Exception e){
            	JOptionPane.showMessageDialog(this, "Attention! File not exist ...", "Attention", 2); // operation failed message
            }
        }
    	
    }
    
    private void openActionPerformed(java.awt.event.ActionEvent evt) {   // it is called when you click on the "open" menu item                            
        
    	try {
    		Desktop.getDesktop().open(new File("history"));  // shows the history folder on the screen
        } catch (IOException ex) {
        	JOptionPane.showMessageDialog(this, "Attention! Directory not exist ...", "Attention", 2);  // error message
        }
    } 

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Secondary().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnSearch2;
    private javax.swing.JButton btnSerch;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JFileChooser jFileChooser;
    private javax.swing.JMenu jMenu;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JLabel lbImage;
    private javax.swing.JLabel lbTitle1;
    private javax.swing.JLabel lbTitle2;
    private javax.swing.JLabel lbTitle3;
    private javax.swing.JMenuItem open;
    private javax.swing.JMenuItem save;
    private javax.swing.JTextField txtName;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel lbLegend;
    // End of variables declaration                   
}
