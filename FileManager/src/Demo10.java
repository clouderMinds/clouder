import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Demo10 extends JFrame implements ActionListener {
 String s[] = { "吉林", "辽宁", "黑龙江" };
 String ss[][] = { { "吉林市", "长春市", "四平市" }, 
   { "沈阳市", "大连市", "锦州市" },
   { "佳木斯", "哈尔滨", "齐齐哈尔" } };
 JComboBox jb1 = new JComboBox(s);
 JComboBox jb2 = new JComboBox();
 JPanel panel = new JPanel();

 public Demo10() {
  panel.add(jb1);
  panel.add(jb2);
  jb1.addActionListener(this);
  this.getContentPane().add(panel);
  this.setBounds(100, 100, 300, 300);
  this.setDefaultCloseOperation(3);
  this.setVisible(true);

 }

 public static void main(String[] args) {
  new Demo10();
 }

 public void actionPerformed(ActionEvent e) {
  JComboBox temp = (JComboBox) e.getSource();
  String name = (String) temp.getSelectedItem();
  if(jb2.getItemCount()!=0){
   jb2.removeAllItems();
   jb2.updateUI();
   jb2.setSelectedItem("");
  }
  if(name.equals(s[0])){ 
   for(int i=0;i<3;i++){
    jb2.addItem(ss[0][i]);
   }
  }
  else if(name.equals(s[1])){
   for(int i=0;i<3;i++){
    jb2.addItem(ss[1][i]);
   }
  }
  else if(name.equals(s[2])){
   for(int i=0;i<3;i++){
    jb2.addItem(ss[2][i]);
   }
  }

 }

}