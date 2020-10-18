/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatting.application;


import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
        
public class client extends JFrame implements ActionListener{
    
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;
    static  Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    Boolean typing;
    
    client(){
      p1=new JPanel();
      p1.setLayout(null);
      p1.setBackground(new Color(7,54,84));
      p1.setBounds(0,0,490,50);
      add(p1);
      
      
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l1=new JLabel(i3);
        l1.setBounds(5,15,25,25);
        p1.add(l1);
        
        l1.addMouseListener(new MouseAdapter(){
         
          public void mouseClicked(MouseEvent ae){
              System.exit(0);
          }
        
        
         });
        
         ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/2.png"));
        Image i5 = i4.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
        ImageIcon i6=new ImageIcon(i5);
        JLabel l2=new JLabel(i6);
        l2.setBounds(35,10,30,30);
        p1.add(l2);
        
         ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT);
        ImageIcon i9=new ImageIcon(i8);
        JLabel l5=new JLabel(i9);
        l5.setBounds(178,12,30,30);
        p1.add(l5);
        
         ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT);
        ImageIcon i12=new ImageIcon(i11);
        JLabel l6=new JLabel(i12);
        l6.setBounds(220,12,30,30);
        p1.add(l6);
        
         ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(7,17, Image.SCALE_DEFAULT);
        ImageIcon i15=new ImageIcon(i14);
        JLabel l7=new JLabel(i15);
        l7.setBounds(262,13,13,25);
        p1.add(l7);
        
        JLabel l3=new JLabel("Bunty");
        l3.setFont(new Font("SAN_SERIF",Font.BOLD,15));
        l3.setForeground(Color.WHITE);
        l3.setBounds(76,10,130,20);
        p1.add(l3);
        
         JLabel l4=new JLabel("Active Now");
        l4.setFont(new Font("SAN_SERIF",Font.PLAIN,10));
        l4.setForeground(Color.WHITE);
        l4.setBounds(76,31,80,10);
        p1.add(l4);
        
         Timer t=new Timer(1,new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                if(!typing){
                    l4.setText("Active Now");
                }
            }
        });
        
        t.setInitialDelay(2000);
        
        a1=new JTextArea();
        a1.setBounds(3,53,295,410);
        
        a1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        a1.setEditable(false);
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);
        add(a1);
        
        
        t1=new JTextField();
        t1.setBounds(5,470,220,40);
        t1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        add(t1);
        
         t1.addKeyListener(new KeyAdapter(){
           public void keyPressed(KeyEvent ke){
             l4.setText("typing...");
             t.stop();
             typing=true;
           }
           public void keyReleased(KeyEvent ke){
               typing=false;
               if(!t.isRunning()){
                   t.start();
               }
           }
       }); 
        
        b1=new JButton("Send");
        b1.setBounds(223,470,80,40);
        b1.setBackground(new Color(7,94,84));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        b1.addActionListener(this);
        add(b1);
        
       // getContentPane().setBackground(Color.MAGENTA);
        setLayout(null);
      setSize(300,500);
      
      setLocation(800,200);
      setUndecorated(true);
      setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        try{
        String out=t1.getText();
        a1.setText(a1.getText()+"\n\t\t"+out);
        
        t1.setText("");
        dout.writeUTF(out);
        
        }catch(Exception e){}
    }
    public static void main(String[] args){
        
        new client().setVisible(true);
        
        try{
           s=new Socket("127.0.0.1", 6001);
           din = new DataInputStream(s.getInputStream());
           dout = new DataOutputStream(s.getOutputStream());
           
           String msginput= "";
           msginput=din.readUTF();
           a1.setText(a1.getText()+"\n"+msginput);
        
       }catch (Exception e){}
    
    }

   
}
