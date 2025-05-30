/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUD_Form;

import Admin.user_form;
import Config.Session;
import Config.config;
import Config.passwordHasher;
import com.mysql.jdbc.Statement;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Raven
 */
public class crud_form extends javax.swing.JFrame {

    /**
     * Creates new form crud_form_citizen
     */
    public crud_form() {
        initComponents();
    }

    public String destination = "";
    File selectedFile;
    public String oldpath;
    public String path;
    
    public int FileExistenceChecker(String path){
        File file = new File(path);
        String fileName = file.getName();
        
        Path filePath = Paths.get("src/userimages", fileName);
        boolean fileExists = Files.exists(filePath);
        
        if (fileExists) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public static int getHeightFromWidth(String imagePath, int desiredWidth) {
        try {
            // Read the image file
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);
            
            // Get the original width and height of the image
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();
            
            // Calculate the new height based on the desired width and the aspect ratio
            int newHeight = (int) ((double) desiredWidth / originalWidth * originalHeight);
            
            return newHeight;
        } catch (IOException ex) {
            System.out.println("No image found!");
        }
        
        return -1;
    }
    
    public  ImageIcon ResizeImage(String ImagePath, byte[] pic, JLabel label) {
        ImageIcon MyImage = null;
            if(ImagePath !=null){
                MyImage = new ImageIcon(ImagePath);
            }else{
                MyImage = new ImageIcon(pic);
            }

        int newHeight = getHeightFromWidth(ImagePath, label.getWidth());

        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), newHeight, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
    
    public void imageUpdater(String existingFilePath, String newFilePath){
        File existingFile = new File(existingFilePath);
        if (existingFile.exists()) {
            String parentDirectory = existingFile.getParent();
            File newFile = new File(newFilePath);
            String newFileName = newFile.getName();
            File updatedFile = new File(parentDirectory, newFileName);
            existingFile.delete();
            try {
                Files.copy(newFile.toPath(), updatedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image updated successfully.");
            } catch (IOException e) {
                System.out.println("Error occurred while updating the image: "+e);
            }
        } else {
            try{
                Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }catch(IOException e){
                System.out.println("Error on update!");
            }
        }
    }
    
    public static String em, us;

    public boolean duplicateChecker() {
        config conf = new config();
        try {
            String query = "SELECT * FROM accounts WHERE uname = '" + username.getText() + "' OR email = '" + email.getText() + "'";
            ResultSet resultSet = conf.getData(query);

            if (resultSet.next()) {
                em = resultSet.getString("email");
                if (em.equals(email.getText())) {
                    JOptionPane.showMessageDialog(null, "Email is Already Used!");
                    email.setText(null);
                }
                
                us = resultSet.getString("uname");
                if (us.equals(username.getText())) {
                    JOptionPane.showMessageDialog(null, "Username is Already Used!");
                    username.setText(null);
                }

                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return false;
        }
    }
    public boolean updateChecker() {
        config conf = new config();
        try {
            String query = "SELECT * FROM accounts WHERE (uname = '" + username.getText() + "' OR email = '" + email.getText() + "') AND a_id != '" + uid.getText() + "'";
            ResultSet resultSet = conf.getData(query);

            if (resultSet.next()) {
                em = resultSet.getString("email");
                if (em.equals(email.getText())) {
                    JOptionPane.showMessageDialog(null, "Email is Already Used!");
                    email.setText(null);
                }
                
                us = resultSet.getString("uname");
                if (us.equals(username.getText())) {
                    JOptionPane.showMessageDialog(null, "Username is Already Used!");
                    username.setText(null);
                }
                
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return false;
        }
    }

    public boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }
    public boolean isValidContact(String contact) {
        return contact.matches("^9\\d{9}$");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        fname = new javax.swing.JTextField();
        lname = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        gender = new javax.swing.JComboBox<>();
        usertype = new javax.swing.JComboBox<>();
        contact = new javax.swing.JTextField();
        cpassword = new javax.swing.JPasswordField();
        password = new javax.swing.JPasswordField();
        enter = new javax.swing.JLabel();
        clear = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        status = new javax.swing.JComboBox<>();
        uid = new javax.swing.JTextField();
        back = new javax.swing.JLabel();
        fcolor = new javax.swing.JTextField();
        fanimal = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        Remove = new javax.swing.JLabel();
        Select = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title.setFont(new java.awt.Font("Playbill", 1, 36)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("User Form");
        title.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 760, 40));

        username.setBackground(new java.awt.Color(204, 255, 255));
        username.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        username.setToolTipText("");
        username.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "User Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 14))); // NOI18N
        username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                usernameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                usernameMouseExited(evt);
            }
        });
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        jPanel1.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, 310, 40));

        fname.setBackground(new java.awt.Color(204, 255, 255));
        fname.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        fname.setToolTipText("");
        fname.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "First Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 14))); // NOI18N
        fname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fnameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fnameMouseExited(evt);
            }
        });
        fname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnameActionPerformed(evt);
            }
        });
        jPanel1.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 210, 40));

        lname.setBackground(new java.awt.Color(204, 255, 255));
        lname.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lname.setToolTipText("");
        lname.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Last Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 14))); // NOI18N
        lname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lnameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lnameMouseExited(evt);
            }
        });
        lname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lnameActionPerformed(evt);
            }
        });
        jPanel1.add(lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 210, 40));

        email.setBackground(new java.awt.Color(204, 255, 255));
        email.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        email.setToolTipText("");
        email.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Email", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 14))); // NOI18N
        email.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                emailMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                emailMouseExited(evt);
            }
        });
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        jPanel1.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 300, 40));

        gender.setBackground(new java.awt.Color(204, 255, 255));
        gender.setEditable(true);
        gender.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        gender.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gender", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 14))); // NOI18N
        gender.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderActionPerformed(evt);
            }
        });
        jPanel1.add(gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 190, 80));

        usertype.setBackground(new java.awt.Color(204, 255, 255));
        usertype.setEditable(true);
        usertype.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        usertype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Staff" }));
        usertype.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User Type", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 14))); // NOI18N
        usertype.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        usertype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usertypeActionPerformed(evt);
            }
        });
        jPanel1.add(usertype, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, 200, 80));

        contact.setBackground(new java.awt.Color(204, 255, 255));
        contact.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        contact.setToolTipText("");
        contact.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Contact Number +63", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 14))); // NOI18N
        contact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                contactMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                contactMouseExited(evt);
            }
        });
        contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactActionPerformed(evt);
            }
        });
        jPanel1.add(contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 190, 40));

        cpassword.setBackground(new java.awt.Color(204, 255, 255));
        cpassword.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        cpassword.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Confirm Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 14))); // NOI18N
        jPanel1.add(cpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 210, 40));

        password.setBackground(new java.awt.Color(204, 255, 255));
        password.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        password.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 14))); // NOI18N
        jPanel1.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 190, 40));

        enter.setBackground(new java.awt.Color(204, 255, 255));
        enter.setFont(new java.awt.Font("Playbill", 0, 36)); // NOI18N
        enter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enter.setText("Enter");
        enter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        enter.setOpaque(true);
        enter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enterMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enterMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                enterMouseExited(evt);
            }
        });
        jPanel1.add(enter, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 350, 90, 40));

        clear.setBackground(new java.awt.Color(204, 255, 255));
        clear.setFont(new java.awt.Font("Playbill", 0, 36)); // NOI18N
        clear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clear.setText("Clear");
        clear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        clear.setOpaque(true);
        clear.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                clearMouseDragged(evt);
            }
        });
        clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                clearMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                clearMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                clearMousePressed(evt);
            }
        });
        jPanel1.add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 350, 90, 40));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("-");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 0, 30, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("x");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 0, 30, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 30));

        status.setBackground(new java.awt.Color(204, 255, 255));
        status.setEditable(true);
        status.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Pending" }));
        status.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 14))); // NOI18N
        status.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusActionPerformed(evt);
            }
        });
        jPanel1.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 260, 200, 80));

        uid.setBackground(new java.awt.Color(204, 255, 255));
        uid.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        uid.setToolTipText("");
        uid.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "User ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 14))); // NOI18N
        uid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                uidMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                uidMouseExited(evt);
            }
        });
        uid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uidActionPerformed(evt);
            }
        });
        jPanel1.add(uid, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 610, 40));

        back.setBackground(new java.awt.Color(204, 255, 255));
        back.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back.setText("Back");
        back.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        back.setOpaque(true);
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backMouseExited(evt);
            }
        });
        jPanel1.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 50, 40));

        fcolor.setBackground(new java.awt.Color(204, 255, 255));
        fcolor.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        fcolor.setToolTipText("");
        fcolor.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Favorite Color", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 14))); // NOI18N
        fcolor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fcolorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fcolorMouseExited(evt);
            }
        });
        fcolor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fcolorActionPerformed(evt);
            }
        });
        jPanel1.add(fcolor, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, 310, 40));

        fanimal.setBackground(new java.awt.Color(204, 255, 255));
        fanimal.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        fanimal.setToolTipText("");
        fanimal.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Favorite Animal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 14))); // NOI18N
        fanimal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fanimalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fanimalMouseExited(evt);
            }
        });
        fanimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fanimalActionPerformed(evt);
            }
        });
        jPanel1.add(fanimal, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 300, 40));

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        image.setFont(new java.awt.Font("Tahoma", 0, 100)); // NOI18N
        image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 170, 220));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 150, 190, 240));

        Remove.setBackground(new java.awt.Color(204, 255, 255));
        Remove.setFont(new java.awt.Font("Playbill", 0, 36)); // NOI18N
        Remove.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Remove.setText("Remove");
        Remove.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Remove.setOpaque(true);
        Remove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RemoveMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RemoveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RemoveMouseExited(evt);
            }
        });
        jPanel1.add(Remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 100, 90, 40));

        Select.setBackground(new java.awt.Color(204, 255, 255));
        Select.setFont(new java.awt.Font("Playbill", 0, 36)); // NOI18N
        Select.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Select.setText("Select");
        Select.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        Select.setOpaque(true);
        Select.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SelectMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SelectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SelectMouseExited(evt);
            }
        });
        jPanel1.add(Select, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 100, 90, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void usernameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameMouseEntered

    private void usernameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameMouseExited

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void fnameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fnameMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fnameMouseEntered

    private void fnameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fnameMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_fnameMouseExited

    private void fnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fnameActionPerformed

    private void lnameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lnameMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lnameMouseEntered

    private void lnameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lnameMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lnameMouseExited

    private void lnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lnameActionPerformed

    private void emailMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emailMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_emailMouseEntered

    private void emailMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emailMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_emailMouseExited

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void genderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genderActionPerformed

    private void usertypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usertypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usertypeActionPerformed

    private void contactMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contactMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_contactMouseEntered

    private void contactMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contactMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_contactMouseExited

    private void contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contactActionPerformed

    private void enterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterMouseClicked
        // TODO add your handling code here:
        if (enter.getText().equals("Add")) {
            config conf = new config();

            if (fname.getText().isEmpty() || lname.getText().isEmpty() || email.getText().isEmpty()
                || username.getText().isEmpty() || password.getText().isEmpty()
                || fanimal.getText().isEmpty() || fcolor.getText().isEmpty() || contact.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "All Fields are Required!");

            } else if (!isValidEmail(email.getText())) {
                JOptionPane.showMessageDialog(null, "Invalid Email! Must be @gmail.com");

            } else if (!isValidContact(contact.getText())) {
                JOptionPane.showMessageDialog(null, "Invalid Contact Number! Must be 10 digits and start with 9");

            } else if (password.getText().length() < 8) {
                JOptionPane.showMessageDialog(null, "Password Must be longer than 8!");

            } else if (!(password.getText().equals(cpassword.getText()))) {
                JOptionPane.showMessageDialog(null, "Password does not match!");

            } else if (duplicateChecker()) {
                System.out.println("Duplicate Exists!");

            } else {
                try {
                    String pass = passwordHasher.hashPassword(password.getText());

                    String sql = "INSERT INTO accounts (fname, lname, gender, user_type, email, uname, pname, contact, fanimal, fcolor, status, image) "
                               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement pst = conf.connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    pst.setString(1, fname.getText());
                    pst.setString(2, lname.getText());
                    pst.setString(3, gender.getSelectedItem().toString());
                    pst.setString(4, usertype.getSelectedItem().toString());
                    pst.setString(5, email.getText());
                    pst.setString(6, username.getText());
                    pst.setString(7, pass);
                    pst.setString(8, contact.getText());
                    pst.setString(9, fanimal.getText());
                    pst.setString(10, fcolor.getText());
                    pst.setString(11, status.getSelectedItem().toString());
                    pst.setString(12, destination);

                    pst.execute();

                    ResultSet generatedKey = pst.getGeneratedKeys();
                    int lastInsertedId = -1;
                    if (generatedKey.next()) {
                        lastInsertedId = generatedKey.getInt(1);
                    }

                    Session sess = Session.getInstance();
                    String action = "Added new user with ID No. " + lastInsertedId;
                    conf.insertData("INSERT INTO logs (u_id, action, date) VALUES ('" + sess.getUid() + "', '" + action + "', '" + LocalDateTime.now() + "')");

                    if (selectedFile != null && !destination.isEmpty()) {
                        Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }

                    JOptionPane.showMessageDialog(null, "Registered Successfully!");
                    user_form uf = new user_form();
                    uf.setVisible(true);
                    this.dispose();

                } catch (NoSuchAlgorithmException | SQLException | IOException ex) {
                    System.out.println("Error: " + ex);
                }
            }

        } else {
            config conf = new config();

            if (fname.getText().isEmpty() || lname.getText().isEmpty() || email.getText().isEmpty()
                || username.getText().isEmpty() || password.getText().isEmpty()
                || fanimal.getText().isEmpty() || fcolor.getText().isEmpty() || contact.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "All Fields are Required!");

            } else if (!isValidEmail(email.getText())) {
                JOptionPane.showMessageDialog(null, "Invalid Email! Must be @gmail.com");

            } else if (!isValidContact(contact.getText())) {
                JOptionPane.showMessageDialog(null, "Invalid Contact Number! Must be 10 digits and start with 9");

            } else if (password.getText().length() < 8) {
                JOptionPane.showMessageDialog(null, "Password Must be longer than 8!");

            } else if (!(password.getText().equals(cpassword.getText()))) {
                JOptionPane.showMessageDialog(null, "Password does not match!");

            } else if (updateChecker()) {
                System.out.println("Duplicate Exists!");

            } else {
                try {
                    String pass = password.getText().equals("********") ? null : passwordHasher.hashPassword(password.getText());
                    String updateQuery;
                    
                    if (pass == null) {
                        updateQuery = "UPDATE accounts SET fname = '"+fname.getText()+"',"
                            + "lname = '"+lname.getText()+"',"
                            + "gender = '"+gender.getSelectedItem()+"',"
                            + "user_type = '"+usertype.getSelectedItem()+"',"
                            + "status = '"+status.getSelectedItem()+"',"
                            + "email = '"+email.getText()+"',"
                            + "contact = '"+contact.getText()+"',"
                            + "fanimal = '"+fanimal.getText()+"',"
                            + "fcolor = '"+fcolor.getText()+"',"
                            + "uname = '"+username.getText()+"',"
                            + "image = '"+destination+"' WHERE a_id = '"+uid.getText()+"'";
                    } else {
                        updateQuery = "UPDATE accounts SET fname = '"+fname.getText()+"',"
                            + "lname = '"+lname.getText()+"',"
                            + "gender = '"+gender.getSelectedItem()+"',"
                            + "user_type = '"+usertype.getSelectedItem()+"',"
                            + "status = '"+status.getSelectedItem()+"',"
                            + "email = '"+email.getText()+"',"
                            + "contact = '"+contact.getText()+"',"
                            + "uname = '"+username.getText()+"',"
                            + "fanimal = '"+fanimal.getText()+"',"
                            + "fcolor = '"+fcolor.getText()+"',"
                            + "pname = '"+pass+"',"
                            + "image = '"+destination+"' WHERE a_id = '"+uid.getText()+"'";
                    }

                    conf.updateData(updateQuery);
                    if (destination.isEmpty()) {
                        File existingFile = new File(oldpath);
                        if (existingFile.exists()) {
                            existingFile.delete();
                        }
                    } else {
                        if (!(oldpath.equals(path))) {
                            imageUpdater(oldpath, path);
                        }
                    }
                    Session sess = Session.getInstance();
                    String action = "Updated user with ID No. " + uid.getText();
                    conf.insertData("INSERT INTO logs (u_id, action, date) VALUES ('" + sess.getUid() + "', '" + action + "', '" + LocalDateTime.now() + "')");

                    user_form uf = new user_form();
                    uf.setVisible(true);
                    this.dispose();

                } catch (NoSuchAlgorithmException ex) {
                    System.out.println("Update Error: " + ex);
                }
            }
        }
    }//GEN-LAST:event_enterMouseClicked

    private void enterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterMouseEntered
        // TODO add your handling code here:
        enter.setForeground(Color.white);
        enter.setBackground(Color.gray);
    }//GEN-LAST:event_enterMouseEntered

    private void enterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterMouseExited
        // TODO add your handling code here:
        enter.setForeground(Color.black);
        enter.setBackground(null);
    }//GEN-LAST:event_enterMouseExited

    private void clearMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearMouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_clearMouseDragged

    private void clearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearMouseClicked
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(null, "Confirm Clear everything?", "clear", JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION){
            fname.setText(null);
            lname.setText(null);
            email.setText(null);
            contact.setText(null);
            username.setText(null);
            password.setText(null);
            cpassword.setText(null);
            fanimal.setText(null);
            fcolor.setText(null);
            JOptionPane.showMessageDialog(null, "Cleared Successfully!");
        }
    }//GEN-LAST:event_clearMouseClicked

    private void clearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearMouseEntered
        // TODO add your handling code here:
        clear.setForeground(Color.white);
        clear.setBackground(Color.gray);
    }//GEN-LAST:event_clearMouseEntered

    private void clearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearMouseExited
        // TODO add your handling code here:
        clear.setForeground(Color.black);
        clear.setBackground(null);
    }//GEN-LAST:event_clearMouseExited

    private void clearMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_clearMousePressed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        int exit = JOptionPane.showConfirmDialog(null, "Confirm Exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
        if (exit == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Exited Successfully!");
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Exit Canceled!");
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusActionPerformed

    private void uidMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uidMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_uidMouseEntered

    private void uidMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uidMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_uidMouseExited

    private void uidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uidActionPerformed

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        // TODO add your handling code here:
        user_form uf = new user_form();
        uf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backMouseClicked

    private void backMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseEntered
        // TODO add your handling code here:
        back.setForeground(Color.white);
        back.setBackground(Color.gray);
    }//GEN-LAST:event_backMouseEntered

    private void backMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseExited
        // TODO add your handling code here:
        back.setForeground(Color.black);
        back.setBackground(null);
    }//GEN-LAST:event_backMouseExited

    private void fcolorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fcolorMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fcolorMouseEntered

    private void fcolorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fcolorMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_fcolorMouseExited

    private void fcolorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fcolorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fcolorActionPerformed

    private void fanimalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fanimalMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fanimalMouseEntered

    private void fanimalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fanimalMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_fanimalMouseExited

    private void fanimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fanimalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fanimalActionPerformed

    private void RemoveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RemoveMouseClicked
        // TODO add your handling code here:
        if(!Remove.isEnabled()){
            return;
        }else{
            Remove.setEnabled(true);
            Select.setEnabled(true);
            image.setIcon(null);
            destination = "";
            path = "";
        }
    }//GEN-LAST:event_RemoveMouseClicked

    private void RemoveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RemoveMouseEntered
        // TODO add your handling code here:
        Remove.setForeground(Color.white);
        Remove.setBackground(Color.gray);
    }//GEN-LAST:event_RemoveMouseEntered

    private void RemoveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RemoveMouseExited
        // TODO add your handling code here:
        Remove.setForeground(Color.black);
        Remove.setBackground(null);
    }//GEN-LAST:event_RemoveMouseExited

    private void SelectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectMouseClicked
        // TODO add your handling code here:
        if(!Select.isEnabled()){
            return;
        }else{
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                try {
                    selectedFile = fileChooser.getSelectedFile();
                    destination = "src/userimages/" + selectedFile.getName();
                    path  = selectedFile.getAbsolutePath();

                    if(FileExistenceChecker(path) == 1){
                        JOptionPane.showMessageDialog(null, "File Already Exist, Rename or Choose another!");
                        destination = "";
                        path="";
                    }else{
                        image.setIcon(ResizeImage(path, null, image));
                        Select.setEnabled(false);
                        Remove.setEnabled(false);
                    }
                } catch (Exception ex) {
                    System.out.println("File Error!");
                }
            }
        }
    }//GEN-LAST:event_SelectMouseClicked

    private void SelectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectMouseEntered
        // TODO add your handling code here:
        Select.setForeground(Color.white);
        Select.setBackground(Color.gray);
    }//GEN-LAST:event_SelectMouseEntered

    private void SelectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectMouseExited
        // TODO add your handling code here:
        Select.setForeground(Color.black);
        Select.setBackground(null);
    }//GEN-LAST:event_SelectMouseExited

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
            java.util.logging.Logger.getLogger(crud_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(crud_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(crud_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(crud_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new crud_form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel Remove;
    public javax.swing.JLabel Select;
    private javax.swing.JLabel back;
    private javax.swing.JLabel clear;
    public javax.swing.JTextField contact;
    public javax.swing.JPasswordField cpassword;
    public javax.swing.JTextField email;
    public javax.swing.JLabel enter;
    public javax.swing.JTextField fanimal;
    public javax.swing.JTextField fcolor;
    public javax.swing.JTextField fname;
    public javax.swing.JComboBox<String> gender;
    public javax.swing.JLabel image;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    public javax.swing.JTextField lname;
    public javax.swing.JPasswordField password;
    public javax.swing.JComboBox<String> status;
    public javax.swing.JLabel title;
    public javax.swing.JTextField uid;
    public javax.swing.JTextField username;
    public javax.swing.JComboBox<String> usertype;
    // End of variables declaration//GEN-END:variables
}
