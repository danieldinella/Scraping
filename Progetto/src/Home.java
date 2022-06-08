/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */


import java.awt.Color;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
        menu = new javax.swing.JMenuBar();
        voice1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lbTitle.setFont(new java.awt.Font("Castellar", 3, 14)); // NOI18N
        lbTitle.setText("Type the name of the Roman emperor:");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addComponent(btnSearch)))
                .addContainerGap(74, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnSearch)
                .addContainerGap(63, Short.MAX_VALUE))
        );

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
        	Person per = new Person(s, link);
        	per.setDinastia();
        	per.setImperatore();
        	per.setPadre();
        	per.setMadre();
        	per.setConiuge();
        	per.setFigli();
		
        	TreeImage.processString(per, "");
        	TreeImage img = new TreeImage(0);
        	img.resetCodifica();
        }
        catch (Exception e) {
        	i = -1;
        }
        if (i == -1){
            JOptionPane.showMessageDialog(this, "Attention! The page wikipedia"
            		+ " not exist ...", "Attentio", 2);
        }
        
        
        else{
            Secondary window = new Secondary();
            window.show();
            dispose();
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
    private javax.swing.JLabel lbTitle;
    private javax.swing.JMenuBar menu;
    private javax.swing.JTextField txtName;
    private javax.swing.JMenu voice1;
    
    // End of variables declaration                   
}
