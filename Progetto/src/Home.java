/* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
 
import java.awt.Color;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class Home extends javax.swing.JFrame {
	
    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        getContentPane().setBackground(new java.awt.Color (190, 50, 52)); // color of frame
        setLocationRelativeTo(null);  // centering frame
    } 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

    	lbTitle = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jCombBox = new javax.swing.JComboBox<>();
        btnSearch2 = new javax.swing.JButton();
        menu = new javax.swing.JMenuBar();
        voice1 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        

        lbTitle.setFont(new java.awt.Font("Castellar", 3, 14)); // NOI18N
        lbTitle.setText("Hi, this is a web scraper, start looking!");
        lbTitle.setForeground(new Color(255, 255, 255));
        
        jPanel1.setBackground(new java.awt.Color(190, 50, 52));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "By name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setToolTipText("");

        txtName.setForeground(new java.awt.Color(153, 153, 153));
        txtName.setText("Write a name...");
        txtName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNameMouseClicked(evt);
            }
        });
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNameKeyPressed(evt);
            }
        });
        

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        
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
                .addComponent(btnSearch)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(190, 50, 52));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "By dinastry", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setToolTipText("");
        
        jCombBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Augusto (Giulio Claudii,  27 a.C.-14 d.C.)", "Vespasiano (Flavii, 68-79)", "Adriano (Antonini, 117-138)", "Settimio_Severo (Severi, 193-211)", "Valeriano (Valeriani, 253-260)", "Costantino (Costantiniana, 306-337)" }));

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
                .addComponent(btnSearch2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jCombBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch2)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        
        btnSearch2.setText("Search");
        btnSearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch2ActionPerformed(evt);
            }
        });
        

        voice1.setIcon(new javax.swing.ImageIcon("info.png")); // NOI18N
        voice1.setText("Info");
        voice1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                voice1MouseClicked(evt);
            }
        });
        menu.add(voice1);

        setJMenuBar(menu);

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
        
        lbTitle.setHorizontalAlignment(JLabel.CENTER);
        
        pack();
               
    }// </editor-fold>                        

    private void txtNameKeyPressed(java.awt.event.KeyEvent evt) {                                   
        // TODO add your handling code here:

    }                                  

    private void txtNameMouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        txtName.setText(""); 
        txtName.setForeground(new Color(0,0,0));
        /* When the user clicks on the text area, 
            the writing is removed and the color is set to black
        */
    }                                    

    private void voice1MouseClicked(java.awt.event.MouseEvent evt) {                                    
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Here you can search for the family \n"
                    + "tree of any Roman emperor. \n This is a web scaper!", "Info", 1);
        /* When you click on the "info" icon a window 
            will open with a short description. 
            1 as a parameter because it is a message panel.
        */
    }                                   

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:

        String s = txtName.getText();
        
        if (s.equals("Write a name...")){
        	JOptionPane.showMessageDialog(this, "Error! Enter valid input...", "Error", 0);
        }
        else {
        	//s = s.replaceAll(" ", "");
        	//s = s.toLowerCase();
        	int i = 0;
        
        	File f = new File("history");
        	if (!f.exists()) {
        		f.mkdir();
        	}
        	else {
	        	String[] v = f.list();
	        	for (String s1 : v) {
	        		File f1 = new File("history\\" + s1);
	        		f1.delete();
	        	}
	        }
	        
	        String link = "https://it.wikipedia.org/wiki/" + s;
	        
	        try {
	        	StringProcessor.resetCodifica();
	        	Person per = new Person(s, link);
	        	per.setCheckImperatore();
	        	if (per.checkImp()) {
	        			per.closeDriver(per.getDriver());
	                	Imperatore imp = new Imperatore(s, link, true);
	                	imp.setDinastia();
	                	imp.setPadre();
	                	imp.setMadre();
	                	imp.setConiuge();
	                	imp.setCheckImperatore();
	                	imp.setMandato();
	                	imp.setFigli();

	    	        	StringProcessor.processString(imp, "");
	    	        	System.out.println(StringProcessor.getCodifica());
	    	        	TreeImage.setCodifica(StringProcessor.getCodifica());
	    	        	TreeImage.createImage(0);
	    	        	
	    	        	System.out.println("Nome: " + imp.getNome());
						System.out.println("Dinastia: " + imp.getDinastia());
						System.out.println("Madre: " + imp.getMadre());
						System.out.println("Padre: " + imp.getPadre());
						System.out.println("Coniugi: ");
						imp.printConiuge(imp.getConiuge());
						System.out.println("Figli: ");
						System.out.println(imp.getFigli());
						System.out.println("\n");
						System.out.println("Mandato: " + imp.getMandato());
						System.out.println("\n");
	               	}
	                else {
	                	per.setDinastia();
	                    per.setPadre();
	                   	per.setMadre();
	                   	per.setConiuge();
	                   	per.setFigli();
	                   	//TreeImage.processString(per, "");
	    	        	StringProcessor.processString(per, "");
	    	        	System.out.println(StringProcessor.getCodifica());
	    	        	TreeImage.setCodifica(StringProcessor.getCodifica());
	    	        	TreeImage.createImage(0);
	                	}
			
	        }
	        catch (Exception e) {
	        	i = -1;
	        }		
			
	        if (i == -1){
	            JOptionPane.showMessageDialog(this, "Attention! The page wikipedia"
	            		+ " not exist ...", "Attention", 2);
	        }
	        else{
	            Secondary window = new Secondary();
	            window.show();
	            dispose();
	        }
        }
    }                                         

    private void formWindowClosing(java.awt.event.WindowEvent evt) {                                   
        // TODO add your handling code here:
        int Answer = JOptionPane.showConfirmDialog(null, "Are you sure want to exit?", "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (Answer == JOptionPane.NO_OPTION) {
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
            if (Answer == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        /*
            When you click on the "x" it generates a confirmation message
            */
    }  
    
    private void btnSearch2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    	String input = (String) jCombBox.getSelectedItem();
    	input = input.substring(0, input.indexOf(' '));
        int i = 0;
        String link = "https://it.wikipedia.org/wiki/" + input;
        
        File f = new File("history");
    	if (!f.exists()) {
    		f.mkdir();
    	}
    	else {
        	String[] v = f.list();
        	for (String s1 : v) {
        		File f1 = new File("history\\" + s1);
        		f1.delete();
        	}
        }
        
        try {
        	StringProcessor.resetCodifica();
        	Person per = new Person(input, link);
        	per.setCheckImperatore();
        	if(per.checkImp()) {
        		per.closeDriver(per.getDriver());
				Imperatore imp = new Imperatore(input, link, true);
				imp.setDinastia();
				imp.setPadre();
				imp.setMadre();
				imp.setConiuge();
				imp.setCheckImperatore();
				imp.setMandato();
				imp.setFigli();
				imp.closeDriver(imp.getDriver());
				System.out.println(imp.getNome());
				System.out.println("mandato:" + imp.getMandato());
				
				StringProcessor.processString(imp, "");
	        	TreeImage.setCodifica(StringProcessor.getCodifica());
	        	TreeImage.createImage(0);
			}
			else {
				per.setDinastia();
				per.setPadre();
				per.setMadre();
				per.setConiuge();
				per.setFigli();
				per.closeDriver(per.getDriver());
				
				StringProcessor.processString(per, "");
	        	TreeImage.setCodifica(StringProcessor.getCodifica());
	        	TreeImage.createImage(0);
			}
		
        }
        catch (Exception e) {
        	i = -1;
        }
		
		
        if (i == -1){
            JOptionPane.showMessageDialog(this, "Attention! The page wikipedia"
            		+ " not exist ...", "Attention", 2);
        }
        else{
        	Secondary window = new Secondary();
            window.show();
            dispose();
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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch2;
    private javax.swing.JComboBox<String> jCombBox;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JMenuBar menu;
    private javax.swing.JTextField txtName;
    private javax.swing.JMenu voice1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    
    // End of variables declaration                   
}
