/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interaction;

import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bima
 */
public class ListPerjalanan extends javax.swing.JFrame {

    /**
     * Creates new form ListPerjalanan
     */
    Connection con;
    ResultSet RsBrg;
    Statement stm;
    PreparedStatement pst;
    
    Boolean ada = false;
    Boolean edit = false;
    String sRegion;
    String sIDbus;
    
    //private Object[][] Tablebarang =  null;
    private Object[][] dataTable = null;
    private String[] header = {"Kode","ID Bus","Region","Waktu","Tujuan","Harga"};

    public ListPerjalanan() {
        initComponents();
        setLocationRelativeTo(null);
        open_db();
        baca_data();
        aktif(false);
        setTombol(true);
        Fillcombo();
    }
    private void Fillcombo(){
	try {
		String sql = "select * from listBus";
		pst=con.prepareStatement(sql);
		RsBrg=pst.executeQuery();
		
		while(RsBrg.next()){
			String kode = RsBrg.getString("kode");
			ComboIDBus.addItem(kode);
		}
	}catch(Exception e){
	JOptionPane.showMessageDialog(null, e); 
                }
}
    private void setField()
    {
        int row=Tableperjalanan.getSelectedRow();
        Textkodejln.setText((String)Tableperjalanan.getValueAt(row,0));
        ComboIDBus.setSelectedItem((String)Tableperjalanan.getValueAt(row,1));
        Combojenis.setSelectedItem((String)Tableperjalanan.getValueAt(row,2));  
        TextWaktu.setText((String)Tableperjalanan.getValueAt(row,3));
        TextTujuan.setText((String)Tableperjalanan.getValueAt(row,4)); 
        String harga=Double.toString((Double)Tableperjalanan.getValueAt(row,5));
        Textharga.setText(harga);
    }
    
        private void open_db()
    {       
        try{
            koneksiMysql kon = new koneksiMysql("localhost","root","","masterDB");
            con = kon.getConnection();
            //System.out.println("Berhasil ");
            }catch (Exception e) {
            System.out.println("Error : "+e);
    }
    }
    
        
            private void baca_data()
    {
        try{
            stm = con.createStatement();
            RsBrg = stm.executeQuery("select * from listPerjalanan");
                
            ResultSetMetaData meta = RsBrg.getMetaData();
            int col = meta.getColumnCount();            
            int baris = 0;
            while(RsBrg.next()) {
                baris = RsBrg.getRow();
            }
            
            dataTable = new Object[baris][col];
            int x = 0;
            RsBrg.beforeFirst();
            while(RsBrg.next()) {
                dataTable[x][0] = RsBrg.getString("kd_prjlnan");
                dataTable[x][1] = RsBrg.getString("ID_bus");
                dataTable[x][2] = RsBrg.getString("region");
                dataTable[x][3] = RsBrg.getString("waktu");
                dataTable[x][4] = RsBrg.getString("tujuan");
                dataTable[x][5] = RsBrg.getDouble("harga");
                x++;
            }
            Tableperjalanan.setModel(new DefaultTableModel(dataTable,header));        
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
            
    private void kosong()
    {
        Textkodejln.setText("");
        TextWaktu.setText(""); 
        TextTujuan.setText("");
        Textharga.setText("");
    }
    
    private void aktif(boolean x)
    {
        Textkodejln.setEditable(x);
        ComboIDBus.setEnabled(x);
        Combojenis.setEnabled(x);
        TextWaktu.setEditable(x);
        TextTujuan.setEditable(x);
        Textharga.setEditable(x);
    }
    
        private void setTombol(boolean t)
    {
        Buttontambah.setEnabled(t);
        Buttonkoreksi.setEnabled(t);
        Buttonhapus.setEnabled(t);
        Buttonsimpan.setEnabled(!t);
        Buttonbatal.setEnabled(!t);
        Buttonkeluar.setEnabled(t);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Textkodejln = new javax.swing.JTextField();
        TextWaktu = new javax.swing.JTextField();
        TextTujuan = new javax.swing.JTextField();
        Textharga = new javax.swing.JTextField();
        Combojenis = new javax.swing.JComboBox();
        ComboIDBus = new javax.swing.JComboBox();
        Buttontambah = new javax.swing.JButton();
        Buttonsimpan = new javax.swing.JButton();
        Buttonkoreksi = new javax.swing.JButton();
        Buttonhapus = new javax.swing.JButton();
        Buttonbatal = new javax.swing.JButton();
        Buttonkeluar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tableperjalanan = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(158, 227, 228));

        jLabel1.setText("Kode Perjalanan");

        jLabel2.setText("ID Bus");

        jLabel3.setText("Region");

        jLabel4.setText("Waktu");

        jLabel5.setText("Tujuan");

        jLabel6.setText("Harga");

        Combojenis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jawa", "Sumatra", "Kalimantan", "Sulawesi", "Bali & Nt" }));
        Combojenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CombojenisActionPerformed(evt);
            }
        });

        ComboIDBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboIDBusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Combojenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Textkodejln, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Textharga, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(ComboIDBus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TextWaktu, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TextTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Textkodejln, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ComboIDBus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Combojenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TextWaktu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TextTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Textharga, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Buttontambah.setText("Tambah");
        Buttontambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtontambahActionPerformed(evt);
            }
        });

        Buttonsimpan.setText("Simpan");
        Buttonsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonsimpanActionPerformed(evt);
            }
        });

        Buttonkoreksi.setText("Koreksi");
        Buttonkoreksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonkoreksiActionPerformed(evt);
            }
        });

        Buttonhapus.setText("Hapus");
        Buttonhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonhapusActionPerformed(evt);
            }
        });

        Buttonbatal.setText("Batal");
        Buttonbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonbatalActionPerformed(evt);
            }
        });

        Buttonkeluar.setText("Keluar");
        Buttonkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonkeluarActionPerformed(evt);
            }
        });

        Tableperjalanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "", "", "", "", "", ""
            }
        ));
        Tableperjalanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableperjalananMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tableperjalanan);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Buttontambah)
                        .addGap(18, 18, 18)
                        .addComponent(Buttonsimpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Buttonhapus)
                        .addGap(18, 18, 18)
                        .addComponent(Buttonkoreksi)
                        .addGap(18, 18, 18)
                        .addComponent(Buttonbatal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 429, Short.MAX_VALUE)
                        .addComponent(Buttonkeluar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Buttontambah)
                    .addComponent(Buttonsimpan)
                    .addComponent(Buttonkoreksi)
                    .addComponent(Buttonhapus)
                    .addComponent(Buttonbatal)
                    .addComponent(Buttonkeluar))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtontambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtontambahActionPerformed
        aktif(true);
        setTombol(false);
        kosong();
        Textkodejln.requestFocusInWindow();
        edit = false;
    }//GEN-LAST:event_ButtontambahActionPerformed

    private void ButtonsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonsimpanActionPerformed
        String tKode=Textkodejln.getText();
        String tWaktu=TextWaktu.getText();
        String tTujuan=TextTujuan.getText();
        
        double hrg=Double.parseDouble(Textharga.getText());
        try{
        if (edit==true)
                {
            stm.executeUpdate("update listPerjalanan set ID_bus='"+sIDbus+"',region='"+sRegion+"', waktu='"+tWaktu+"',tujuan='"+tTujuan+"', harga="+hrg+" where kd_prjlnan='" + tKode + "'");
                }else
        {
            stm.executeUpdate("INSERT into listPerjalanan VALUES('"+tKode+"','"+sIDbus+
                    "','"+sRegion+"','"+tWaktu+"','"+tTujuan+"',"+hrg+")");
        }
         Tableperjalanan.setModel(new DefaultTableModel(dataTable,header));
        baca_data();
        aktif(false);
        setTombol(true); 
        kosong();
    }catch(SQLException e) {
       JOptionPane.showMessageDialog(null, e);
 }
    }//GEN-LAST:event_ButtonsimpanActionPerformed

    private void ButtonhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonhapusActionPerformed

        try{
            String sql="delete from listPerjalanan where kd_prjlnan='" + Textkodejln.getText()+ "'";
            stm.executeUpdate(sql);
            baca_data();
            kosong();
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null, e);
            }
    }//GEN-LAST:event_ButtonhapusActionPerformed

    private void ButtonkoreksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonkoreksiActionPerformed
        edit=true;                                          
        aktif(true);
        setTombol(false);
        Textkodejln.setEditable(false);
    }//GEN-LAST:event_ButtonkoreksiActionPerformed

    private void ButtonbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonbatalActionPerformed
        aktif(false);
        setTombol(true);
    }//GEN-LAST:event_ButtonbatalActionPerformed

    private void ButtonkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonkeluarActionPerformed
        dispose();
        new fromMenu().setVisible(true);
    }//GEN-LAST:event_ButtonkeluarActionPerformed

    private void TableperjalananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableperjalananMouseClicked
        setField();
    }//GEN-LAST:event_TableperjalananMouseClicked

    private void CombojenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CombojenisActionPerformed
        JComboBox cRegion= (javax.swing.JComboBox)evt.getSource();
        //Membaca Item Yang Terpilih — > String
        sRegion = (String)cRegion.getSelectedItem();
    }//GEN-LAST:event_CombojenisActionPerformed

    private void ComboIDBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboIDBusActionPerformed
        // TODO add your handling code here:
        JComboBox cIDbus= (javax.swing.JComboBox)evt.getSource();
        //Membaca Item Yang Terpilih — > String
        sIDbus = (String)cIDbus.getSelectedItem();
    }//GEN-LAST:event_ComboIDBusActionPerformed

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
            java.util.logging.Logger.getLogger(ListPerjalanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListPerjalanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListPerjalanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListPerjalanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListPerjalanan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buttonbatal;
    private javax.swing.JButton Buttonhapus;
    private javax.swing.JButton Buttonkeluar;
    private javax.swing.JButton Buttonkoreksi;
    private javax.swing.JButton Buttonsimpan;
    private javax.swing.JButton Buttontambah;
    private javax.swing.JComboBox ComboIDBus;
    private javax.swing.JComboBox Combojenis;
    private javax.swing.JTable Tableperjalanan;
    private javax.swing.JTextField TextTujuan;
    private javax.swing.JTextField TextWaktu;
    private javax.swing.JTextField Textharga;
    private javax.swing.JTextField Textkodejln;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
