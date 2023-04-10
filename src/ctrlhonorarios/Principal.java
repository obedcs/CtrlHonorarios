/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrlhonorarios;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Obed
 */
public class Principal extends javax.swing.JFrame {
    Statement stat;
    Connection con,conMySQL;
    conecxionbd Conexion = new conecxionbd();
    Queriis ConsultasSistema = new Queriis();
    Sistema Sistema = new Sistema();
    
    String consulta="",clvmed="",Usuario="",UsuarioNmb="",curfolio="",curnmb="",
           mail="",pwdmail="",smtphost="",port="",clvpac="", nmbpac="",espmed="",
           exppac="",auth="",reportfolder="",logohste = "",logoctrl = "",logoback = "";
    String ingreso = "INGRESO DE HONORAROS";
    String devolucion = "DEVOLUCION DE EFECTIVO";
    
    float debe=0,pagado=0,montotal=0;
    
    int TablaValida = 0,tablacontable = 0;
    
    ResultSet rs=null;
    
    DefaultTableModel modeloT1;
    DefaultTableModel modeloT2;
    DefaultTableModel modeloT3;
    DefaultTableModel modeloT4;
    DefaultTableModel modeloT5;
    DefaultTableModel modeloT6;
    DefaultTableModel modeloT7;
    DefaultTableModel modeloT8;
    DefaultTableModel modeloT9;
    DefaultTableModel modeloT10;
    DefaultTableModel modeloT11;
    DefaultTableModel modeloT12;
    DefaultTableModel modeloT13;
    
    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        
        modeloT1 = (DefaultTableModel)jTable1.getModel();
        modeloT2 = (DefaultTableModel)jTable2.getModel();
        modeloT3 = (DefaultTableModel)jTable3.getModel();
        modeloT4 = (DefaultTableModel)jTable4.getModel();
        modeloT5 = (DefaultTableModel)jTable5.getModel();
        modeloT6 = (DefaultTableModel)jTable6.getModel();
        modeloT7 = (DefaultTableModel)jTable7.getModel();
        modeloT8 = (DefaultTableModel)jTable8.getModel();
        modeloT9 = (DefaultTableModel)jTable9.getModel();
        //modeloT10 = (DefaultTableModel)jTable10.getModel();*/
        modeloT11 = (DefaultTableModel)jTable11.getModel();
        
        jButton7.setEnabled(false);
        
        con=Conexion.Conecta();
        conMySQL=Conexion.ConectaMySQL();
        TextAutoCompleter completa = new TextAutoCompleter(jTextField16);
        getDeptosTCA(completa,0,"");
        LeeConfig();
        Licencia(auth);
        
        jTextField5.setEnabled(false);
        
        timer.start();
        
        this.setJMenuBar(jMenuBar1);
        editables();
        cent_this();
        cent_reg();
    }
    
    Timer timer = new Timer (1000, new ActionListener () 
    {
        public void actionPerformed(ActionEvent e){
            int hora0=0,minutos0=0,segundos0=0;
            String hora="",minutos="",segundos="";
            Calendar calendario = Calendar.getInstance();
            
            hora0 = calendario.get(Calendar.HOUR_OF_DAY);
            minutos0 = calendario.get(Calendar.MINUTE);
            segundos0 = calendario.get(Calendar.SECOND);
            
            if(hora0 < 10)
                hora = "0"+Integer.toString(hora0);
            else
                hora = Integer.toString(hora0);
            if(minutos0 < 10)
                minutos = "0"+Integer.toString(minutos0);
            else
                minutos = Integer.toString(minutos0);
            if(segundos0 < 10)
                segundos = "0"+Integer.toString(segundos0);
            else
                segundos = Integer.toString(segundos0);
            
            jTextField19.setText(Sistema.FechaSystem());
            jTextField20.setText(hora + ":" + minutos + ":" + segundos);
        }
    });
    
    public void cent_this(){
        this.pack();
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = this.getSize();
        this.setLocation(((pantalla.width - ventana.width)/2), ((pantalla.height - ventana.height)/2));
        this.setVisible(true);
    }
    
    public void cent_adm(){
        Admision.pack();
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = Admision.getSize();
        Admision.setLocation(((pantalla.width - ventana.width)/2), ((pantalla.height - ventana.height)/2));
        Admision.setVisible(true);
    }
    
    public void cent_reg(){
        Registro.pack();
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = Registro.getSize();
        Registro.setLocation(((pantalla.width - ventana.width)/2), ((pantalla.height - ventana.height)/2));
        Registro.setVisible(true);
    }
    
    public void cent_medicos(){
        Medicos.pack();
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = Medicos.getSize();
        Medicos.setLocation(((pantalla.width - ventana.width)/2), ((pantalla.height - ventana.height)/2));
        Medicos.setVisible(true);
    }
    
    public void cent_cajac(){
        CajaChica.pack();
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = CajaChica.getSize();
        CajaChica.setLocation(((pantalla.width - ventana.width)/2), ((pantalla.height - ventana.height)/2));
        CajaChica.setVisible(true);
    }
    
    public void cent_caja(){
        Caja.pack();
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = Caja.getSize();
        Caja.setLocation(((pantalla.width - ventana.width)/2), ((pantalla.height - ventana.height)/2));
        Caja.setVisible(true);
    }
    
    public void cent_pacpen(){
        PacientesPend.pack();
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = PacientesPend.getSize();
        PacientesPend.setLocation(((pantalla.width - ventana.width)/2), ((pantalla.height - ventana.height)/2));
        PacientesPend.setVisible(true);
    }
    
    public void cent_fiscales(){
        Fiscales.pack();
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = Fiscales.getSize();
        Fiscales.setLocation(((pantalla.width - ventana.width)/2), ((pantalla.height - ventana.height)/2));
        Fiscales.setVisible(true);
    }
    
    public void cent_pago(){
        DetallePago.pack();
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = DetallePago.getSize();
        DetallePago.setLocation(((pantalla.width - ventana.width)/2), ((pantalla.height - ventana.height)/2));
        DetallePago.setVisible(true);
    }
    
    public void editables(){
        jMenuBar1.setVisible(false);
    }
    
    public String FechaServer(){
        String cons = "SELECT curdate() AS fecha;";
        
        try{
            stat = conMySQL.createStatement();
            rs = ConsultasSistema.Estructura(conMySQL,cons);
            
            if(rs.next()) cons = rs.getString("fecha");
        }catch (Exception e){ JOptionPane.showMessageDialog(null,e); }
        
        return cons;
    }
    
    public int checkValidez(String fecha) throws ParseException{
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date factual = formato.parse(FechaServer());
        Date fobtiene = formato.parse(fecha);
        int resultado = 0;
        
        if(fobtiene.before(factual) == true) resultado = 1; // licencia caducada
        
        return resultado;
    }
    
    public int checkFormatoValidez(String validez){
        int pasa = 0; // 0=licencia caducada, 1=licencia correcta
        String mes="", dia="", anio="";
        char Array[] = validez.toCharArray();
        
        dia += String.valueOf(Array[8]);
        dia += String.valueOf(Array[9]);
        
        mes += String.valueOf(Array[5]);
        mes += String.valueOf(Array[6]);
        
        anio += String.valueOf(Array[0]);
        anio += String.valueOf(Array[1]);
        anio += String.valueOf(Array[2]);
        anio += String.valueOf(Array[3]);
        
        if((Integer.parseInt(mes) >= 1)&&(Integer.parseInt(mes) <= 12)){
            if((Integer.parseInt(dia) >= 1)&&(Integer.parseInt(dia) <= 31)){
                if(Integer.parseInt(anio) >= 2020){
                    pasa = 1;
                }
            }
        }
        
        return pasa;
    }
    
    public void Licencia(String auth){
        String Obtiene[] = new String[2];
        String cadena="",validez="";
        
        if(!auth.equals("1"))
            ConsultaUsuarioTCA();
        else{
            try{
                stat = conMySQL.createStatement();
                rs = ConsultasSistema.Estructura(conMySQL,"SELECT * FROM servlicencias WHERE status = 1");
                if(rs.next())
                    cadena=Sistema.DesencriptarMD5(rs.getString("clave"))+rs.getString("digito");
                else{
                    JOptionPane.showMessageDialog(null, "no se encontró licencia activa","error",JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            }catch (Exception e){ JOptionPane.showMessageDialog(null,e); }
        }
        
        Obtiene = Sistema.VerificaLicencia(cadena.toUpperCase());
        if(Obtiene[1].equals("1"))
            JOptionPane.showMessageDialog(null, "formato incorrecto de licencia","error",JOptionPane.ERROR_MESSAGE);
        else{
            validez = Obtiene[0].toString();
            try {
                if(checkValidez(validez) == 0){
                    if(checkFormatoValidez(validez) == 0){
                        JOptionPane.showMessageDialog(null, "licencia caducada ...","error",JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                    else{
                        jTextField21.setText(validez);
                        jTextField1.setEnabled(true); jPasswordField1.setEnabled(true);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "licencia caducada","error",JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            } catch (ParseException ex) { Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex); }
        }
    }
    
    public String getDeptosTCA(TextAutoCompleter completa, int tipo,String depto){
        if(tipo == 0){
            consulta = "SELECT CASE WHEN (LEN(a.Nombre) > 28)\n" +
                       "	    THEN RTRIM(SUBSTRING(a.Nombre,1,28))\n" +
                       "	    ELSE RTRIM(a.Nombre) END AS nm\n" +
                       "FROM Departamentos a\n" +
                       "ORDER BY a.Nombre ASC";
            try{
                stat = con.createStatement();
                rs=ConsultasSistema.Estructura(con,consulta);
                while(rs.next())
                    completa.addItem(rs.getString("nm"));
            }catch (Exception e){ JOptionPane.showMessageDialog(null, e); }
        }
        else{
            consulta = "SELECT a.Departamento\n" +
                       "FROM Departamentos a\n" +
                       "WHERE a.Nombre LIKE '"+depto+"%'";
            try{
                stat = con.createStatement();
                rs=ConsultasSistema.Estructura(con,consulta);
                if(rs.next())
                    depto = rs.getString("Departamento");
            }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
        }
        return depto;
    }
    
    public void ConsultaUsuarioTCA(){
        String user = jTextField18.getText();
        String pass = jPasswordField1.getText();
        String nombrecomp="",privilegios="";
        
        try{
           stat = conMySQL.createStatement();
           rs=ConsultasSistema.Estructura(conMySQL,"SELECT * FROM usuarios WHERE us_usuario = '"+user+"';");
           
           if(rs.next()){
               String uss=rs.getString("us_usuario");
               String pwd=rs.getString("us_pwd");
               if((uss.equals(user))&&(pass.equals(pwd))){
                   nombrecomp=rs.getString("us_nombre");
                   privilegios=rs.getString("us_priv");
                   VerificaPermisos(privilegios);
                   UsuarioNmb = nombrecomp;
                   Usuario = uss;
                   jTextField17.setText(uss+" [ "+nombrecomp+" ]");
                   jTextField19.setText(Sistema.FechaSystem());
                   jTextField20.setText(Sistema.HoraSystem());
                   jMenuBar1.setVisible(true);
                   cent_this();
                   Registro.dispose();
               }
               else{
                   JOptionPane.showMessageDialog(null, "contraseña incorrecta","error",JOptionPane.ERROR_MESSAGE);
               }
           }
           else{
               JOptionPane.showMessageDialog(null, "usuario no existe","error",JOptionPane.ERROR_MESSAGE);
           }
       }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
    }
    
    public void VerificaPermisos(String permiso){
        if(permiso.equals("SIS")){
            adm.setEnabled(true);
            sistemas.setEnabled(true);
        }
        else{
            if(permiso.equals("ADM")){
                adm.setEnabled(true);
                sistemas.setEnabled(false);
            }
            else{
                if(permiso.equals("CAJ")){
                    adm.setEnabled(true);
                    sistemas.setEnabled(false);
                    
                }
            }
        }
    }
    
    public void getMedicos(){
        consulta = "SELECT RTRIM(b.ApellidoPaterno)+' '+RTRIM(b.ApellidoMaterno)+' '+RTRIM(b.Nombre) AS nmed,\n" +
                   "       RTRIM(c.desc_esp) AS espe,RTRIM(b.CorreoElectronico) AS mail,b.Medico\n" +
                   "FROM hosimd b\n" +
                   "INNER JOIN hosesp c ON c.esp=b.Especialidad1\n" +
                   "ORDER BY nmed ASC";
        
        for(int i=modeloT3.getRowCount()-1;i>=0;i--) modeloT3.removeRow(i);
        
        try{
            stat = con.createStatement();
            rs=ConsultasSistema.Estructura(con,consulta);
           
            while(rs.next()){
                Object []Fila = new Object [4];
                Fila[0] = rs.getString("Medico");
                Fila[1] = rs.getString("nmed");
                Fila[2] = rs.getString("espe");
                Fila[3] = rs.getString("mail");
                modeloT3.addRow(Fila);
            }
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
    }
    
    public void TabbedPane(int tipo){
        if (tipo == 0){
            jTabbedPane5.setEnabledAt(1,false);
            jTabbedPane5.setEnabledAt(2,false);
        }
        else{
            if (tipo == 1){
                jTabbedPane5.setEnabledAt(0,false);
                jTabbedPane5.setEnabledAt(2,false);
            }
            else{
                if (tipo == 2){
                    jTabbedPane5.setEnabledAt(0,false);
                    jTabbedPane5.setEnabledAt(1,false);
                }
            }
        }
        
        jTabbedPane5.setEnabledAt(tipo,true);
        jTabbedPane5.setSelectedIndex(tipo);
    }
    
    public void SetHonorarios(String folcaja){
        int a = 0,b = 0;
        String fisc = "";
        for(int i=0;i<modeloT4.getRowCount();i++){
            if(modeloT4.getValueAt(i,4).toString().equals("NO")) fisc = "0";
            else{ fisc = "1"; b++; }
            consulta = "INSERT INTO honorarios(hon_med,hon_fol,hon_fecha,hon_hora,hon_usr,total,solfiscales,status_contable,expediente)\n" +
                       "VALUES ('"+modeloT4.getValueAt(i,0)+"','"+clvpac+"',curdate(),curtime(),'"+Usuario+"'," +
                       modeloT4.getValueAt(i,3)+","+fisc+","+folcaja+",'"+exppac+"')";
            a += ConsultasSistema.InsertaActualiza(conMySQL,consulta);
        }
        if(a == modeloT4.getRowCount()){
            JOptionPane.showMessageDialog(null, "Asignación correcta de Honorarios");
            if(b > 0){
                jTextField12.setText(exppac);
                getFiscales(exppac);
                cent_fiscales();
            }
            LimpiaHonorarios();
            Admision.dispose();
        }
    }
    
    public int getFiscales(String expediente){
        int a = 0;
        consulta = "SELECT * FROM fiscales WHERE exp = '"+expediente+"'";
        
        try{
            stat = conMySQL.createStatement();
            rs=ConsultasSistema.Estructura(conMySQL,consulta);
            if(rs.next()){
                a = 1;
                jTextField6.setText(rs.getString("nombre"));
                jTextField7.setText(rs.getString("direccion"));
                jTextField8.setText(rs.getString("cp"));
                jTextField9.setText(rs.getString("ciudad"));
                jTextField10.setText(rs.getString("estado"));
                jTextField11.setText(rs.getString("correo"));
                jTextField13.setText(rs.getString("rfc"));
            }
        }catch (Exception e){ JOptionPane.showMessageDialog(null, e); }
        return a;
    }
    
    public void ConsultaEdoCta(String Folio,String Medico){
        consulta = "SELECT a.reg_desc,a.reg_fecha,a.reg_usr,a.reg_monto\n" +
                   "FROM registro a\n" +
                   "WHERE a.reg_med='"+Medico+"' AND a.reg_pac='"+Folio+"'";
        
        for(int i=modeloT6.getRowCount()-1;i>=0;i--) modeloT6.removeRow(i);
        
        try{
            stat = conMySQL.createStatement();
            rs=ConsultasSistema.Estructura(conMySQL,consulta);
            
            while(rs.next()){
                Object []Fila = new Object [4];
                    Fila[0] = rs.getString("reg_desc");
                    Fila[1] = rs.getString("reg_fecha");
                    Fila[2] = Sistema.ConvierteMoneda(rs.getString("reg_monto"));
                    Fila[3] = rs.getString("reg_usr");
                modeloT6.addRow(Fila);
            }
            try{
                consulta = "SELECT SUM(a.reg_monto) AS abonado,(SELECT total\n" +
                           "  				        FROM honorarios\n" +
                           "					WHERE hon_med=a.reg_med AND hon_fol=a.reg_pac) AS cuenta\n" +
                           "FROM registro a\n" +
                           "WHERE a.reg_med='"+Medico+"' AND a.reg_pac='"+Folio+"'";
                stat = conMySQL.createStatement();
                rs=ConsultasSistema.Estructura(conMySQL,consulta);
                if(rs.next()){
                    jTextField2.setText(rs.getString("abonado"));
                    jTextField4.setText(rs.getString("cuenta"));
                    jTextField3.setText(Float.toString(Float.parseFloat(jTextField4.getText()) - Float.parseFloat(jTextField2.getText())));
                }
            }catch (Exception e){
                consulta = "SELECT total\n" +
                           "FROM honorarios\n" +
                           "WHERE hon_med='"+Medico+"' AND hon_fol='"+Folio+"'\n";
                stat = conMySQL.createStatement();
                rs=ConsultasSistema.Estructura(conMySQL,consulta);
                if(rs.next()){
                    jTextField2.setText("0");
                    jTextField4.setText(rs.getString("total"));
                    jTextField3.setText(Float.toString(Float.parseFloat(jTextField4.getText()) - Float.parseFloat(jTextField2.getText())));
                }
            }
            
            debe = Float.parseFloat(jTextField3.getText());
            pagado = Float.parseFloat(jTextField2.getText());
            montotal = Float.parseFloat(jTextField4.getText());
            
            jTextField2.setText(Sistema.ConvierteMoneda(Float.toString(pagado)));
            jTextField3.setText(Sistema.ConvierteMoneda(Float.toString(debe)));
            jTextField4.setText(Sistema.ConvierteMoneda(Float.toString(montotal)));
            
            jTextField5.setEnabled(true);
        }catch (Exception e){ JOptionPane.showMessageDialog(null, e); }
    }
    
    public int EntregaPagoMedico(int numfila,String folio){
        String ID = modeloT5.getValueAt(numfila,0).toString();
        String upd = "UPDATE honorarios SET hon_status=1,status_contable=2,"+
                     "hon_foliocnt="+folio+" WHERE ID="+ID+";";
        int cnt2=0;
        
        try{
            stat = conMySQL.createStatement();
            if(ConsultasSistema.InsertaActualiza(conMySQL,upd) > 0) cnt2 ++;
            else
                JOptionPane.showMessageDialog(null, "ocurrió un error mientras se actualizaban los datos","error",JOptionPane.ERROR_MESSAGE);
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
        
        return cnt2;
    }
    
    public void ConsPacFolioAb(){
        consulta = "SELECT DISTINCT LTRIM(a.p_fol_cto) AS fol,\n" +
                   "RTRIM(a.p_apellm)+' '+RTRIM(a.p_apellp)+' '+RTRIM(a.p_nom) AS nom\n" +
                   "FROM hospac a\n" +
                   "INNER JOIN OPENQUERY(MYSQL,'SELECT *\n" +
                   "				FROM honorarios\n" +
                   "				WHERE hon_status = 0') b ON b.hon_fol=LTRIM(a.p_fol_cto)\n" +
                   "ORDER BY nom ASC";
        
        for(int i=modeloT5.getRowCount()-1;i>=0;i--)
            modeloT5.removeRow(i);
        
        try{
            stat = con.createStatement();
            rs=ConsultasSistema.Estructura(con,consulta);
            
            while(rs.next()){
                Object []Fila = new Object [2];
                    Fila[0] = rs.getString("fol");
                    Fila[1] = rs.getString("nom");
                modeloT5.addRow(Fila);
            }
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
    }
    
    public String GeneraRandom(){
        Random randomGenerator = new Random();
        String clave = "";
        
        for(int i=0;i<=4;i++)
            clave += Integer.toString(randomGenerator.nextInt(10));
        
        return clave;
    }
    
    public void consMedAsignados(String folio){
        consulta = "SELECT hon_med\n" +
                   "FROM honorarios\n" +
                   "WHERE hon_fol = '"+folio+"'";
        
        try{
            jComboBox1.removeAll();
            stat = conMySQL.createStatement();
            rs=ConsultasSistema.Estructura(conMySQL,consulta);
            
            while(rs.next())
                jComboBox1.addItem(rs.getString("hon_med"));
            
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
    }
    
    public void ConsMedNmb(String clave){
        consulta = "SELECT RTRIM(ApellidoPaterno)+' '+RTRIM(ApellidoMaterno)+' '+RTRIM(Nombre) AS nmed\n" +
                   "FROM hosimd\n" +
                   "WHERE Medico ='"+clave+"'";
        
        try{
            stat = con.createStatement();
            rs=ConsultasSistema.Estructura(con,consulta);
            
            if(rs.next()) jLabel6.setText("MEDICO: "+rs.getString("nmed"));
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
    }
    
    public void setAbono(String med,String pac,float abono,int tipo){
        String label = "",foliocaja=GeneraRandom();
        
        if(tipo == 0) label = "ABONO A CUENTA / FOL:"+pac+" / MED:"+med;
        else label = "CUENTA SALDADA / FOL:"+pac+" / MED:"+med;
        
        consulta = "INSERT INTO registro(reg_med,reg_pac,reg_monto,reg_desc,reg_fecha,reg_usr,reg_foliocaja) VALUES('" +
                   jComboBox1.getSelectedItem()+"','"+jTextField1.getText()+"',"+abono+",'"+label+"',curdate(),'"+Usuario+"','"+foliocaja+"')";
        
        if(ConsultasSistema.InsertaActualiza(conMySQL,consulta) > 0){
            if(tipo == 0)
                JOptionPane.showMessageDialog(null,"abono registrado");
            else{
                consulta = "UPDATE honorarios SET hon_status=1 WHERE hon_med='"+med+"' AND hon_fol='"+pac+"'";
                if(ConsultasSistema.InsertaActualiza(conMySQL,consulta) > 0){
                    JOptionPane.showMessageDialog(null,"cuenta saldada, se procederá al envío de correo electrónico");
                    LlenaCamposEmail(pac,med);
                }
            }
            Impresion_EntregaHon(foliocaja,med,pac);
            LimpiaAbonos();
        }
    }
    
    public void LlenaCamposEmail(String folio,String med){
        consulta="SELECT b.rfc,b.nombre,b.direccion,b.cp,b.ciudad,b.estado,b.correo\n" +
                 "FROM fiscales b\n" +
                 "INNER JOIN honorarios a ON a.expediente=b.exp\n" +
                 "WHERE a.hon_fol = '"+folio+"'\n" +
                 "AND a.hon_med = '"+med+"'\n" +
                 "AND a.solfiscales = 1";
        String asunto="HOSPITAL DEL SURESTE: PAGO DE HONORARIOS",mailmed="",cadena,pacnmb;
        
        try{
            stat = conMySQL.createStatement();
            rs=ConsultasSistema.Estructura(conMySQL,consulta);
            if(rs.next()){
                cadena="QUE LIQUIDA LA CUENTA PENDIENTE DE HONORARIOS. EL PACIENTE \n"+
                       "SOLICITA FACTURA. SE ENVÍAN SUS DATOS FISCALES: \n"+
                       "RFC: "+rs.getString("rfc")+"\n"+
                       "RAZON SOCIAL: "+rs.getString("nombre")+"\n"+
                       "DIRECCION: "+rs.getString("direccion")+"\n"+
                       "CODIGO POSTAL: "+rs.getString("cp")+"\n"+
                       "CIUDAD: "+rs.getString("ciudad")+"\n"+
                       "ESTADO: "+rs.getString("estado")+"\n"+
                       "CORREO: "+rs.getString("correo");
                try{
                    consulta = "SELECT DISTINCT RTRIM(CorreoElectronico) AS mail,RTRIM(f_folio_nom) AS nmpac\n" +
                               "FROM hosimd,hosfol\n" +
                               "WHERE Medico = '"+med+"' AND LTRIM(f_folio) = '"+folio+"'";
                    stat = con.createStatement();
                    rs=ConsultasSistema.Estructura(con,consulta);
                    if(rs.next()){
                        mailmed = rs.getString("mail");
                        pacnmb = rs.getString("nmpac");
                        cadena = "HOSPITAL DEL SURESTE A.C. LE INFORMA:\n\nSE RECIBIÓ UN PAGO DEL PACIENTE "+pacnmb+",\n"+cadena;
                        
                        if(!mailmed.equals("")) SendeMail(mailmed,asunto,cadena);
                        else JOptionPane.showMessageDialog(null, "no se encontro correo del médico","error",JOptionPane.ERROR_MESSAGE);
                    }
                }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
            }
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
    }
    
    public void SendeMail(String destina,String subject,String mensaje) throws MessagingException{
        final String username = mail;
        final String password = pwdmail;
        Properties props = new Properties();
        
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.host", smtphost);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(destina));
            message.setSubject(subject);
            message.setText(mensaje);

            Transport.send(message);
            JOptionPane.showMessageDialog(null,"correo enviado");

        } catch (MessagingException e) { JOptionPane.showMessageDialog(null,e); }
    }
    
    public void LeeConfig(){
        try{
            Properties p = new Properties();
            p.load(new FileInputStream("Config.ini"));
            mail=p.getProperty("MailFrom");
            pwdmail=p.getProperty("PasswordMail");
            smtphost=p.getProperty("SMTPHost");
            port=p.getProperty("Port");
            auth = p.getProperty("AuthLimit");
            reportfolder = p.getProperty("ReportFolder");
            logohste = p.getProperty("LogoP");
            logoctrl = p.getProperty("LogoCtrl");
            logoback = p.getProperty("LogoBack");
            
            Report.carpeta = reportfolder;
        }catch (Exception e) {System.out.println("error al leer el archivo .ini:"+ e.getMessage());}
    }
    
    public int VerificaSiHayFiscales(String folio){
        String cons = "SELECT * FROM fiscales WHERE folio='"+folio+"'";
        int verificador = 0;
        
        try{
            stat = conMySQL.createStatement();
            rs=ConsultasSistema.Estructura(conMySQL,cons);
            if(rs.next()) verificador = 1;
            else verificador = 0;
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
        return verificador;
    }
    
    public void InsertaCaja(){
        rs = null;
        String folio=ConsecutivoFolioCajaChica();
        String cant=jTextField56.getText();
        String depto = getDeptosTCA(null,1,jTextField16.getText());
        
        if(jComboBox11.getSelectedIndex() == 1) cant="-"+cant;
        
        jTextField55.setText(folio);
        String cons = "INSERT INTO cajachica(folio,fecha,hora,descripcion,nota,cantidad,depto) VALUES("+
                      folio+",curdate(),curtime(),'"+jTextField57.getText().toUpperCase()+"','"+
                      jTextField58.getText().toUpperCase()+"',"+cant+",'"+depto+"')";
        if(ConsultasSistema.InsertaActualiza(conMySQL,cons) > 0){
            JOptionPane.showMessageDialog(null,"transacción generada correctamente");
            LimpiaIngresoCaja();
        }
    }
    
    public String ConsecutivoFolioCajaChica(){
        rs=null;
        String folio = "";
        String cons = "SELECT MAX(folio) AS mx\n" +
                      "FROM cajachica";
        
        try{
            stat = conMySQL.createStatement();
            rs=ConsultasSistema.Estructura(conMySQL,cons);
            if(rs.next())
                folio = Integer.toString(Integer.parseInt(rs.getString("mx"))+1);
        }catch (Exception e){ folio = "1"; }
        
        return folio;
    }
    
    public void consTransaccionFecha(String fecha1,String fecha2){
        rs=null;
        String cons="SELECT * FROM cajachica WHERE fecha BETWEEN '"+fecha1+"' AND '"+fecha2+"'";
        System.out.println(cons);
        
        for(int i=modeloT11.getRowCount()-1;i>=0;i--) modeloT11.removeRow(i);
        
        try{
            stat = conMySQL.createStatement();
            rs=ConsultasSistema.Estructura(conMySQL,cons);
            while(rs.next()){
                Object []Fila = new Object [4];
                Fila[0] = rs.getString("folio");
                Fila[1] = rs.getString("hora")+"  "+rs.getString("descripcion");
                Fila[2] = rs.getString("depto");
                Fila[3] = rs.getString("cantidad");
                modeloT11.addRow(Fila);
            }
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
    }
    
    public void SumaTabla(){
        float total=0;
        for(int i=0;i<modeloT11.getRowCount();i++)
            total += Float.parseFloat(modeloT11.getValueAt(i,3).toString());
        jTextField59.setText(Sistema.ConvierteMoneda(Float.toString(total)));
    }
    
    public void getDatosFolio(String folio){
        rs=null;
        String cons = "SELECT fecha,descripcion,cantidad\n"+
                      "FROM cajachica\n"+
                      "WHERE folio='"+folio+"'";
        
        try{
            stat = conMySQL.createStatement();
            rs=ConsultasSistema.Estructura(conMySQL,cons);
            if(rs.next()){
                jTextField60.setEnabled(false);
                float cant = Float.parseFloat(rs.getString("cantidad"));
                if(cant < 0) { cant = cant * (-1); jComboBox12.setSelectedIndex(1);}
                else jComboBox12.setSelectedIndex(0);
                jTextField62.setText(Float.toString(cant));
                jTextField61.setText(rs.getString("descripcion"));
                jDateChooser3.setCalendar(ConvertDateToCalendar(rs.getString("fecha")));
            }
            else JOptionPane.showMessageDialog(null, "revise el folio","error",JOptionPane.ERROR_MESSAGE);
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
    }
    
    public Calendar ConvertDateToCalendar(String fecha) throws ParseException{
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(fecha);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    
    public void UpdFolio(String folio){
        String cant = jTextField62.getText();
        int opc = 0;
        if(jComboBox12.getSelectedIndex() == 1) cant="-"+cant;
        String ins="UPDATE cajachica SET fecha='"+Sistema.FechaPeriodo(jDateChooser3.getCalendar())+"',descripcion='"+
                   jTextField61.getText().toUpperCase()+"',cantidad="+cant+" WHERE folio="+folio+";";
        
        opc=JOptionPane.showConfirmDialog(null,"¿Actualizar datos?","verifique",JOptionPane.YES_NO_OPTION);
        if(opc == 0){
            if(ConsultasSistema.InsertaActualiza(conMySQL,ins) > 0){
                JOptionPane.showMessageDialog(null,"datos actualizados");
                LimpiaEdicion();
            }
        }
    }
    
    //-----------------LIMPIEZA
    public void LimpiaEdicion(){
        jTextField60.setText("");
        jTextField61.setText("");
        jTextField62.setText("");
        jComboBox12.setSelectedIndex(0);
        jDateChooser3.setCalendar(Calendar.getInstance());
        jTextField60.setEnabled(true);
    }
    
    public void LimpiaFiscales(){
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
        jTextField11.setText("");
        jTextField12.setText("");
        jTextField13.setText("");
    }
    
    public void LimpiaIngresoCaja(){
        jTextField55.setText("");
        jTextField56.setText("");
        jTextField57.setText("");
        jTextField58.setText("");
    }
    
    public void LimpiaTablaHonorarios(){
        for(int i=modeloT4.getRowCount()-1;i>=0;i--)
            modeloT4.removeRow(i);
    }
    
    public void LimpiaHonorarios(){
        for(int i=modeloT4.getRowCount()-1;i>=0;i--)
            modeloT4.removeRow(i);
    }
    
    public void LimpiaCorte(){
        for(int i=modeloT7.getRowCount()-1;i>=0;i--)
            modeloT7.removeRow(i);
        
        jTextField14.setText("");
        jTextField15.setText("");
        jLabel27.setText("TOTAL:");
        
        jButton7.setEnabled(false);
    }
    
    public void LimpiaAbonos(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField1.setEnabled(true);
        jComboBox1.removeAllItems();
        
        for(int i=modeloT6.getRowCount()-1;i>=0;i--)
            modeloT6.removeRow(i);
    }
    
    public float SaldoAnterior(String Fecha){
        String cons = "SELECT SUM(cantidad) AS total\n" +
                      "FROM cajachica\n" +
                      "WHERE fecha < '"+Fecha+"'";
        float Saldo=0;
        try{
            stat = conMySQL.createStatement();
            rs=ConsultasSistema.Estructura(conMySQL,cons);
            if(rs.next()) Saldo = Float.parseFloat(rs.getString("total"));
        }catch (Exception e){ Saldo = 0; }
        
        System.out.println(Saldo);
        return Saldo;
    }
    
    public void getPacientesHosp(){
        String medicosMySQL = getFoliosMySQL();
        consulta = "SELECT a.p_num_exp,LTRIM(a.p_fol_cto) AS fol,RTRIM(a.p_num_cto) AS cto,RTRIM(c.desc_esp) AS esp,\n" +
                   "       RTRIM(a.p_apellp)+' '+RTRIM(a.p_apellm)+' '+RTRIM(a.p_nom) AS nm,a.p_cod_cia,\n" +
                   "       (SELECT Nombre FROM cxccli WHERE ClaveCliente=a.p_cod_cia) AS cmp,a.p_med0,\n" +
                   "       RTRIM(b.ApellidoPaterno)+' '+RTRIM(b.ApellidoMaterno)+' '+RTRIM(b.Nombre) AS nmed,p_num_exp\n" +
                   "FROM hospac a\n" +
                   "INNER JOIN hosimd b ON b.Medico=a.p_med0\n" +
                   "INNER JOIN hosesp c ON c.esp=b.Especialidad1\n" +
                   "WHERE a.p_status = '10'\n" +
                   "AND a.p_tpo_cto IN ('NORMAL','URGURG','CEXTER') \n" +
                   "AND LTRIM(a.p_fol_cto) NOT IN(" + medicosMySQL + ")\n" +
                   "ORDER BY nm ASC";
        
        for(int i=modeloT1.getRowCount()-1;i>=0;i--) modeloT1.removeRow(i);
        
        try{
            stat = con.createStatement();
            rs=ConsultasSistema.Estructura(con,consulta);
            while(rs.next()){
                Object []Fila = new Object [8];
                Fila[0] = rs.getString("fol");
                Fila[1] = rs.getString("cto");
                Fila[2] = rs.getString("nm");
                Fila[3] = rs.getString("cmp");
                Fila[4] = rs.getString("nmed");
                Fila[5] = rs.getString("p_med0");
                Fila[6] = rs.getString("esp");
                Fila[7] = rs.getString("p_num_exp");
                modeloT1.addRow(Fila);
            }
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
    }
    
    public String getFoliosMySQL(){
        String cadena = "";
        consulta = "SELECT hon_fol FROM honorarios";
        try{
            stat = conMySQL.createStatement();
            rs=ConsultasSistema.Estructura(conMySQL,consulta);
            
            while(rs.next()) cadena += "'" + (rs.getString("hon_fol") + "',");
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
        return cadena.substring(0, cadena.length()-1);
    }
    
    public String getFolioCaja(){
        String folcaja = "";
        consulta = "SELECT numero AS folcont\n" +
                   "FROM consecutivos\n" +
                   "WHERE statuss = '0' AND usuario = '"+Usuario+"'\n" +
                   "LIMIT 1";
        try{
            stat = conMySQL.createStatement();
            rs=ConsultasSistema.Estructura(conMySQL,consulta);
            if(rs.next())
                folcaja = rs.getString("folcont");
            else{
                consulta = "SELECT MIN(numero) AS folcont\n" +
                           "FROM consecutivos\n" +
                           "WHERE statuss = '0'\n" +
                           "LIMIT 1";
                try{
                    stat = conMySQL.createStatement();
                    rs=ConsultasSistema.Estructura(conMySQL,consulta);
                    if(rs.next()){
                        folcaja = rs.getString("folcont");
                        consulta = "UPDATE consecutivos SET usuario='"+Usuario+"' WHERE numero="+folcaja+" AND statuss='0'";
                        ConsultasSistema.InsertaActualiza(conMySQL,consulta);
                    }
                }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
            }
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
        return folcaja;
    }
    
    public void getCaja(){
        float total = 0;
        String tot = "";
        consulta = "SELECT DISTINCT e.hon_med+' '+RTRIM(f.ApellidoPaterno)+' '+RTRIM(f.ApellidoMaterno)+' '+RTRIM(f.Nombre) AS nmed,\n" +
                   "                e.hon_fol+' '+RTRIM(d.p_apellm)+' '+RTRIM(d.p_apellp)+' '+RTRIM(d.p_nom) AS npac,\n" +
                   "                CONVERT(DATE,e.hon_fecha) AS fec,e.reg_monto,e.us_nombre,e.numero,e.reg_foliocaja\n" +
                   "FROM hospac d\n" +
                   "INNER JOIN OPENQUERY(MYSQL,'SELECT *\n" +
                   "			 FROM honorarios a\n" +
                   "			 INNER JOIN consecutivos b ON b.numero=a.status_contable\n" +
                   "			 INNER JOIN registro c ON c.reg_med=a.hon_med AND c.reg_pac=a.hon_fol\n" +
                   "                     INNER JOIN usuarios z ON z.us_usuario=a.hon_usr\n" +
                   "			 WHERE b.statuss=0 AND b.usuario=''"+Usuario+"''') e ON e.hon_fol=LTRIM(d.p_fol_cto)\n" +
                   "INNER JOIN hosimd f ON f.Medico=d.p_med0";
        try{
            stat = con.createStatement();
            rs=ConsultasSistema.Estructura(con,consulta);
            
            for(int i=modeloT7.getRowCount()-1;i>=0;i--) modeloT7.removeRow(i);
            
            while(rs.next()){
                jTextField14.setText("[ "+Usuario+" ]  "+rs.getString("us_nombre"));
                jTextField15.setText(rs.getString("numero"));
                tot = rs.getString("reg_monto");
                total += Float.parseFloat(tot);
                Object []Fila = new Object [5];
                Fila[0] = rs.getString("nmed");
                Fila[1] = rs.getString("npac");
                Fila[2] = rs.getString("fec");
                Fila[3] = Sistema.ConvierteMoneda(tot);
                Fila[4] = rs.getString("reg_foliocaja");
                modeloT7.addRow(Fila);
            }
            jLabel27.setText("TOTAL: "+Sistema.ConvierteMoneda(Float.toString(total)));
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
    }
    
    public void getDetallePagosMedico(String medico){
        consulta = "SELECT DISTINCT b.reg_pac,RTRIM(a.p_apellm)+' '+RTRIM(a.p_apellp)+' '+RTRIM(a.p_nom) AS npac,\n" +
                   "                b.reg_desc,CONVERT(DATE,b.reg_fecha) AS fec,b.reg_monto,b.reg_foliocaja\n" +
                   "FROM hospac a\n" +
                   "INNER JOIN OPENQUERY(MYSQL,'SELECT *\n" +
                   "				FROM registro\n" +
                   "				WHERE reg_entrega=1') b ON b.reg_pac=LTRIM(a.p_fol_cto)\n" +
                   "WHERE b.reg_med = '"+medico+"'";
        try{
            for(int i=modeloT9.getRowCount()-1;i>=0;i--) modeloT9.removeRow(i);
            stat = con.createStatement();
            rs=ConsultasSistema.Estructura(con,consulta);
            while(rs.next()){
                Object []Fila = new Object [6];
                Fila[0] = rs.getString("reg_pac");
                Fila[1] = rs.getString("npac");
                Fila[2] = rs.getString("reg_desc");
                Fila[3] = rs.getString("fec");
                Fila[4] = Sistema.ConvierteMoneda(rs.getString("reg_monto"));
                Fila[5] = rs.getString("reg_foliocaja");
                modeloT9.addRow(Fila);
            }
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
    }
    
    public int setPagoMedico(String folcaja,String folpac){
        int a = 0;
        consulta = "UPDATE registro\n" +
                   "SET reg_entrega=2\n" +
                   "WHERE reg_foliocaja='"+folcaja+"' AND reg_pac='"+folpac+"' AND reg_entrega=1";
        if(ConsultasSistema.InsertaActualiza(conMySQL,consulta) > 0) a=1;
        return a;
    }
    
    public String[] getNamesPacMed(String med, String pac){
        String datos[] = new String[2];
        consulta = "SELECT RTRIM(a.ApellidoPaterno)+' '+RTRIM(a.ApellidoMaterno)+' '+RTRIM(a.Nombre) AS nmed,\n" +
                   "	   RTRIM(b.p_apellm)+' '+RTRIM(b.p_apellp)+' '+RTRIM(b.p_nom) AS npac\n" +
                   "FROM hosimd a,hospac b\n" +
                   "WHERE a.Medico='"+med+"' AND LTRIM(b.p_fol_cto)='"+pac+"'";
        try{
            stat = con.createStatement();
            rs=ConsultasSistema.Estructura(con,consulta);
            if(rs.next()){
                datos[0] = rs.getString("nmed");
                datos[1] = rs.getString("npac");
            }
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
        return datos;
    }
    
    public String ConvertirFecha(String Fecha){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {date = formatter.parse(Fecha);} catch (ParseException e) {e.printStackTrace();}
        return formatter.format(date);
    }
    
    //-------------------- REPORTES
    public void Impresion_EntregaHon(String folio,String medico, String paciente){
        String datos[] = new String[2];
        datos = getNamesPacMed(medico,paciente);
        try { 
            Report.carpeta = reportfolder;
            Report.mostrarReporteEntHon(conMySQL, folio, datos[0], datos[1]); }
        catch (SQLException ex) { Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex); }
        catch (JRException ex) { Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    public void Impresion_EntregaHonAgrupado(String Usuario,String Med,int stats,String consec){
        try { 
            Report.carpeta = reportfolder;
            Report.mostrarReporteHonAgrupado(conMySQL,Usuario,Med,stats,consec); }
        catch (SQLException ex) { Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex); }
        catch (JRException ex) { Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    public void Impresion_CorteGral(String caja){
        try { 
            Report.carpeta = reportfolder;
            Report.mostrarReporteCorteGral(conMySQL, caja); }
        catch (SQLException ex) { Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex); }
        catch (JRException ex) { Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    public void Impresion_CorteContable(String fecha,String cajero,String corte){
        try { 
            Report.mostrarReporteCorteContable(conMySQL, fecha, cajero, corte); }
        catch (SQLException ex) { Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex); }
        catch (JRException ex) { Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    public void Impresion_EdoCtaPaciente(String folio){
        try { 
            Report.EdoCtaPaciente(conMySQL, folio); }
        catch (SQLException ex) { Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex); }
        catch (JRException ex) { Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    public void Impresion_CorteGralEstatus(String fecha,String cajero,int Estatus){
        try { 
            Report.mostrarReporteCorteGralEstatus(conMySQL, fecha, cajero,Estatus); }
        catch (SQLException ex) { Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex); }
        catch (JRException ex) { Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    public void Impresion_DevEfe(int Estatus,String Titulo){
        try { 
            Report.mostrarReporteDevIngEfe(conMySQL, Estatus,Titulo); }
        catch (SQLException ex) { Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex); }
        catch (JRException ex) { Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    public void Impresion_ConsTrans(int ID,int ST){
        try { 
            Report.mostrarReporteConsTrans(conMySQL,ID,ST); }
        catch (SQLException ex) { Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex); }
        catch (JRException ex) { Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex); }
    }

    public void Reporte_CajaChica(String fechai,String fechaf){
        try { 
            Report.CajaChica(conMySQL,fechai,fechaf,SaldoAnterior(fechai),logohste,logoctrl,logoback); }
        catch (SQLException ex) { Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex); }
        catch (JRException ex) { Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Admision = new javax.swing.JDialog();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        Registro = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        CajaChica = new javax.swing.JDialog();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel14 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jTextField55 = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        jTextField56 = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jTextField57 = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jTextField58 = new javax.swing.JTextField();
        jButton21 = new javax.swing.JButton();
        jLabel71 = new javax.swing.JLabel();
        jComboBox11 = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jLabel72 = new javax.swing.JLabel();
        jTextField59 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel73 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jPanel16 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jTextField60 = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jTextField61 = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel77 = new javax.swing.JLabel();
        jComboBox12 = new javax.swing.JComboBox<>();
        jButton22 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jLabel78 = new javax.swing.JLabel();
        jTextField62 = new javax.swing.JTextField();
        Medicos = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        Caja = new javax.swing.JDialog();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jTextField5 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        PacientesPend = new javax.swing.JDialog();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        Fiscales = new javax.swing.JDialog();
        jLabel12 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        DetallePago = new javax.swing.JDialog();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        adm = new javax.swing.JMenu();
        men = new javax.swing.JMenuItem();
        desasigna = new javax.swing.JMenuItem();
        caja = new javax.swing.JMenu();
        corte = new javax.swing.JMenuItem();
        cajac = new javax.swing.JMenuItem();
        sistemas = new javax.swing.JMenu();

        Admision.setTitle(".:. Admision .:.");
        Admision.setModal(true);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FOLIO", "CUARTO", "NOMBRE", "COMPAÑIA", "MEDICO", "CLAVE", "ESPEC", "EXP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(45);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(45);
            jTable1.getColumnModel().getColumn(1).setMinWidth(53);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(53);
            jTable1.getColumnModel().getColumn(2).setMinWidth(200);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(200);
            jTable1.getColumnModel().getColumn(3).setMinWidth(250);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(250);
            jTable1.getColumnModel().getColumn(4).setMinWidth(200);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(200);
            jTable1.getColumnModel().getColumn(5).setMinWidth(0);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(6).setMinWidth(0);
            jTable1.getColumnModel().getColumn(6).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(7).setMinWidth(0);
            jTable1.getColumnModel().getColumn(7).setMaxWidth(0);
        }

        jLabel1.setText("Paso 1: SELECCIONE EL PACIENTE");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Selección", jPanel17);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CLAVE", "NOMBRE", "ESPECIALIDAD", "CORREO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable3KeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setMinWidth(50);
            jTable3.getColumnModel().getColumn(0).setMaxWidth(50);
            jTable3.getColumnModel().getColumn(1).setMinWidth(230);
            jTable3.getColumnModel().getColumn(1).setMaxWidth(230);
            jTable3.getColumnModel().getColumn(3).setMinWidth(150);
            jTable3.getColumnModel().getColumn(3).setMaxWidth(150);
        }

        jLabel2.setText("Paso 2: SELECCIONE EL MÉDICO");

        jButton4.setText("Siguiente");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane5.addTab("Asignación", jPanel1);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CLAVE", "NOMBRE", "ESPECIALIDAD", "HONORARIOS", "FACT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable4);
        if (jTable4.getColumnModel().getColumnCount() > 0) {
            jTable4.getColumnModel().getColumn(0).setMinWidth(50);
            jTable4.getColumnModel().getColumn(0).setMaxWidth(50);
            jTable4.getColumnModel().getColumn(3).setMinWidth(80);
            jTable4.getColumnModel().getColumn(3).setMaxWidth(80);
            jTable4.getColumnModel().getColumn(4).setMinWidth(50);
            jTable4.getColumnModel().getColumn(4).setMaxWidth(50);
        }

        jLabel3.setText("PACIENTE:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update.png"))); // NOI18N
        jButton1.setText("Agregar Más");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/guardar.png"))); // NOI18N
        jButton2.setText("Grabar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/guardar.png"))); // NOI18N
        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane5.addTab("Confirmación", jPanel2);

        javax.swing.GroupLayout AdmisionLayout = new javax.swing.GroupLayout(Admision.getContentPane());
        Admision.getContentPane().setLayout(AdmisionLayout);
        AdmisionLayout.setHorizontalGroup(
            AdmisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdmisionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane5)
                .addContainerGap())
        );
        AdmisionLayout.setVerticalGroup(
            AdmisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdmisionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Registro.setModal(true);
        Registro.setUndecorated(true);

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Usuario:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Passwd:");

        jTextField18.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jTextField18.setForeground(new java.awt.Color(153, 153, 153));
        jTextField18.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField18ActionPerformed(evt);
            }
        });

        jPasswordField1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jPasswordField1.setForeground(new java.awt.Color(153, 153, 153));
        jPasswordField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                            .addGap(67, 67, 67)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout RegistroLayout = new javax.swing.GroupLayout(Registro.getContentPane());
        Registro.getContentPane().setLayout(RegistroLayout);
        RegistroLayout.setHorizontalGroup(
            RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        RegistroLayout.setVerticalGroup(
            RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        CajaChica.setTitle(".:. Caja Chica .:.");

        jLabel67.setText("Folio:");

        jTextField55.setEditable(false);
        jTextField55.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel68.setText("Cantidad:");

        jTextField56.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel69.setText("Concepto:");

        jLabel70.setText("Nota:");

        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/guardar.png"))); // NOI18N
        jButton21.setText("Guardar");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jLabel71.setText("Tipo:");

        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Entrada", "Salida" }));

        jLabel28.setText("Departamento:");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(0, 570, Short.MAX_VALUE)
                        .addComponent(jButton21))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel70)
                            .addComponent(jLabel69)
                            .addComponent(jLabel71)
                            .addComponent(jLabel67))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField57)
                            .addComponent(jTextField58)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel14Layout.createSequentialGroup()
                                        .addComponent(jTextField55, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel68)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField56, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel14Layout.createSequentialGroup()
                                        .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel28)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(jTextField55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel68)
                    .addComponent(jTextField56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(jTextField57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(jTextField58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel71)
                    .addComponent(jLabel28)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
                .addComponent(jButton21)
                .addContainerGap())
        );

        jTabbedPane4.addTab("Entrada / Salida de Efectivo", jPanel14);

        jTable11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FOLIO", "DESCRIPCION", "DEPTO", "CANTIDAD"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane12.setViewportView(jTable11);
        if (jTable11.getColumnModel().getColumnCount() > 0) {
            jTable11.getColumnModel().getColumn(0).setMinWidth(50);
            jTable11.getColumnModel().getColumn(0).setMaxWidth(50);
            jTable11.getColumnModel().getColumn(2).setMinWidth(50);
            jTable11.getColumnModel().getColumn(2).setMaxWidth(50);
            jTable11.getColumnModel().getColumn(3).setMinWidth(70);
            jTable11.getColumnModel().getColumn(3).setMaxWidth(70);
        }

        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reporte.png"))); // NOI18N
        jButton23.setText("Reporte");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/buscar.png"))); // NOI18N
        jButton24.setText("Actualiza");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel72.setText("Total:");

        jTextField59.setEditable(false);
        jTextField59.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jDateChooser1.setDateFormatString("dd-MM-yyyy");

        jLabel73.setText("Fecha:");

        jDateChooser2.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel72)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField59, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel73)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jButton24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton23)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(jTextField59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Estatus de Caja", jPanel15);

        jLabel74.setText("Folio:");

        jTextField60.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField60ActionPerformed(evt);
            }
        });

        jLabel75.setText("Concepto:");

        jLabel76.setText("Fecha:");

        jDateChooser3.setDateFormatString("dd-MM-yyyy");

        jLabel77.setText("Tipo:");

        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Entrada", "Salida" }));

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update.png"))); // NOI18N
        jButton22.setText("Actualizar");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nuevo.png"))); // NOI18N
        jButton25.setText("Nuevo");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jLabel78.setText("Cantidad:");

        jTextField62.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel74)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField60, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel78)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField62, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel76)
                            .addComponent(jLabel75))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField61)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel77)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 373, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(jTextField60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78)
                    .addComponent(jTextField62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75)
                    .addComponent(jTextField61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                .addComponent(jButton25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane4.addTab("Edición de Folios", jPanel16);

        javax.swing.GroupLayout CajaChicaLayout = new javax.swing.GroupLayout(CajaChica.getContentPane());
        CajaChica.getContentPane().setLayout(CajaChicaLayout);
        CajaChicaLayout.setHorizontalGroup(
            CajaChicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CajaChicaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );
        CajaChicaLayout.setVerticalGroup(
            CajaChicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CajaChicaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );

        Medicos.setTitle(".:. Catálogo de Médicos .:.");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CLAVE", "NOMBRE", "ESPECIALIDAD", "CORREO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(1).setMinWidth(250);
            jTable2.getColumnModel().getColumn(1).setMaxWidth(250);
        }

        javax.swing.GroupLayout MedicosLayout = new javax.swing.GroupLayout(Medicos.getContentPane());
        Medicos.getContentPane().setLayout(MedicosLayout);
        MedicosLayout.setHorizontalGroup(
            MedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MedicosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addContainerGap())
        );
        MedicosLayout.setVerticalGroup(
            MedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MedicosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap())
        );

        Caja.setTitle(".:. Caja Honorarios .:.");
        Caja.setModal(true);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Paciente:");

        jTextField1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CONCEPTO", "FECHA", "MONTO", "USUARIO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTable6);
        if (jTable6.getColumnModel().getColumnCount() > 0) {
            jTable6.getColumnModel().getColumn(1).setMinWidth(70);
            jTable6.getColumnModel().getColumn(1).setMaxWidth(70);
            jTable6.getColumnModel().getColumn(2).setMinWidth(70);
            jTable6.getColumnModel().getColumn(2).setMaxWidth(70);
            jTable6.getColumnModel().getColumn(3).setMinWidth(60);
            jTable6.getColumnModel().getColumn(3).setMaxWidth(60);
        }

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Médico:");

        jComboBox1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBox1PropertyChange(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("SIN DATOS");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Abonado:");

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Adeudo:");

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Monto:");

        jTextField4.setEditable(false);
        jTextField4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("SIN DATOS");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Abono:");

        jTextField5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)))
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jSeparator2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1)
                                    .addComponent(jComboBox1, 0, 96, Short.MAX_VALUE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(3, 3, 3)
                                .addComponent(jTextField2))
                            .addComponent(jSeparator1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Abonos", jPanel3);

        jLabel25.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel25.setText("Cajero:");

        jTextField14.setEditable(false);
        jTextField14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField14.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MEDICO", "PACIENTE", "FECHA", "TOTAL", "FOLIO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTable7);
        if (jTable7.getColumnModel().getColumnCount() > 0) {
            jTable7.getColumnModel().getColumn(2).setMinWidth(65);
            jTable7.getColumnModel().getColumn(2).setMaxWidth(65);
            jTable7.getColumnModel().getColumn(3).setMinWidth(70);
            jTable7.getColumnModel().getColumn(3).setMaxWidth(70);
            jTable7.getColumnModel().getColumn(4).setMinWidth(0);
            jTable7.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        jLabel26.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel26.setText("Caja:");

        jTextField15.setEditable(false);
        jTextField15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField15.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton6.setText("Extraer");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel27.setText("TOTAL:");

        jButton7.setText("Generar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7)
                                .addGap(0, 42, Short.MAX_VALUE))
                            .addComponent(jScrollPane7)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Corte de Caja", jPanel4);

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CLAVE", "NOMBRE", "MONTO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable8KeyPressed(evt);
            }
        });
        jScrollPane8.setViewportView(jTable8);
        if (jTable8.getColumnModel().getColumnCount() > 0) {
            jTable8.getColumnModel().getColumn(0).setMinWidth(60);
            jTable8.getColumnModel().getColumn(0).setMaxWidth(60);
            jTable8.getColumnModel().getColumn(1).setMinWidth(250);
            jTable8.getColumnModel().getColumn(1).setMaxWidth(250);
        }

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update.png"))); // NOI18N
        jButton8.setText("Generar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton8))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Entrega", jPanel6);

        javax.swing.GroupLayout CajaLayout = new javax.swing.GroupLayout(Caja.getContentPane());
        Caja.getContentPane().setLayout(CajaLayout);
        CajaLayout.setHorizontalGroup(
            CajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CajaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        CajaLayout.setVerticalGroup(
            CajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CajaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        PacientesPend.setTitle(".:. Pacientes con Folio Abierto .:.");
        PacientesPend.setModal(true);

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FOLIO", "NOMBRE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable5KeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);
        if (jTable5.getColumnModel().getColumnCount() > 0) {
            jTable5.getColumnModel().getColumn(0).setMinWidth(70);
            jTable5.getColumnModel().getColumn(0).setMaxWidth(70);
        }

        javax.swing.GroupLayout PacientesPendLayout = new javax.swing.GroupLayout(PacientesPend.getContentPane());
        PacientesPend.getContentPane().setLayout(PacientesPendLayout);
        PacientesPendLayout.setHorizontalGroup(
            PacientesPendLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PacientesPendLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PacientesPendLayout.setVerticalGroup(
            PacientesPendLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PacientesPendLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Fiscales.setTitle(".:. Datos Fiscales del Paciente .:.");
        Fiscales.setModal(true);

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Nombre / Razón Social:");

        jTextField6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Domicilio:");

        jTextField7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Código Postal:");

        jTextField8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("Ciudad:");

        jTextField9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Estado:");

        jTextField10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel17.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel17.setText("e-mail:");

        jTextField11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/guardar.png"))); // NOI18N
        jButton5.setText("Guardar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel23.setText("Expediente:");

        jTextField12.setEditable(false);
        jTextField12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField12.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel24.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel24.setText("RFC:");

        jTextField13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField13.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout FiscalesLayout = new javax.swing.GroupLayout(Fiscales.getContentPane());
        Fiscales.getContentPane().setLayout(FiscalesLayout);
        FiscalesLayout.setHorizontalGroup(
            FiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FiscalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FiscalesLayout.createSequentialGroup()
                        .addGroup(FiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(FiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField6)
                            .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                            .addGroup(FiscalesLayout.createSequentialGroup()
                                .addGroup(FiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, FiscalesLayout.createSequentialGroup()
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel15))
                                    .addComponent(jTextField12, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(FiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(FiscalesLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField11))
                                    .addComponent(jTextField9)))
                            .addGroup(FiscalesLayout.createSequentialGroup()
                                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FiscalesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5)))
                .addContainerGap())
        );
        FiscalesLayout.setVerticalGroup(
            FiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FiscalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        DetallePago.setTitle(".:. Detalle del Pago .:.");
        DetallePago.setModal(true);

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FOLIO", "NOMBRE", "DESCRIPCION", "FECHA", "MONTO", "FOL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(jTable9);
        if (jTable9.getColumnModel().getColumnCount() > 0) {
            jTable9.getColumnModel().getColumn(0).setMinWidth(50);
            jTable9.getColumnModel().getColumn(0).setMaxWidth(50);
            jTable9.getColumnModel().getColumn(1).setMinWidth(195);
            jTable9.getColumnModel().getColumn(1).setMaxWidth(195);
            jTable9.getColumnModel().getColumn(2).setMinWidth(250);
            jTable9.getColumnModel().getColumn(2).setMaxWidth(250);
            jTable9.getColumnModel().getColumn(3).setMinWidth(65);
            jTable9.getColumnModel().getColumn(3).setMaxWidth(65);
            jTable9.getColumnModel().getColumn(4).setMinWidth(75);
            jTable9.getColumnModel().getColumn(4).setMaxWidth(75);
            jTable9.getColumnModel().getColumn(5).setMinWidth(0);
            jTable9.getColumnModel().getColumn(5).setMaxWidth(0);
        }

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cajachica.png"))); // NOI18N
        jButton9.setText("Pagar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DetallePagoLayout = new javax.swing.GroupLayout(DetallePago.getContentPane());
        DetallePago.getContentPane().setLayout(DetallePagoLayout);
        DetallePagoLayout.setHorizontalGroup(
            DetallePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DetallePagoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DetallePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton9)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DetallePagoLayout.setVerticalGroup(
            DetallePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DetallePagoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jButton9)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(".:. Hospital del Sureste A.C. :   Sistema de Honorarios Médicos .:.");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo Honorarios 2.jpg"))); // NOI18N

        jLabel18.setText("Usuario:");

        jTextField17.setEditable(false);
        jTextField17.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel22.setText("Fecha:");

        jTextField19.setEditable(false);
        jTextField19.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField20.setEditable(false);
        jTextField20.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel29.setText("Vigencia:");

        jTextField21.setEditable(false);
        jTextField21.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        adm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/admision.png"))); // NOI18N
        adm.setText("Admisión");

        men.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/honmeedi.png"))); // NOI18N
        men.setText("Honorarios Médicos");
        men.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menActionPerformed(evt);
            }
        });
        adm.add(men);

        desasigna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/desasignar.png"))); // NOI18N
        desasigna.setText("Desasignar Médico");
        desasigna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desasignaActionPerformed(evt);
            }
        });
        adm.add(desasigna);

        jMenuBar1.add(adm);

        caja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/caja.png"))); // NOI18N
        caja.setText("Caja");

        corte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/movimientos.png"))); // NOI18N
        corte.setText("Movimientos de Caja");
        corte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                corteActionPerformed(evt);
            }
        });
        caja.add(corte);

        cajac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cajachica.png"))); // NOI18N
        cajac.setText("Caja Alterna");
        cajac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajacActionPerformed(evt);
            }
        });
        caja.add(cajac);

        jMenuBar1.add(caja);

        sistemas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/admin.png"))); // NOI18N
        sistemas.setText("Administracion");
        jMenuBar1.add(sistemas);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField21)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menActionPerformed
        // TODO add your handling code here:
        jLabel2.setText("Paso 2: SELECCIONE EL MÉDICO");
        TabbedPane(0);
        getPacientesHosp();
        cent_adm();
    }//GEN-LAST:event_menActionPerformed

    private void jTextField18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField18ActionPerformed
        // TODO add your handling code here:
        if(!jTextField18.equals(""))
            jPasswordField1.grabFocus();
    }//GEN-LAST:event_jTextField18ActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
        if(!jPasswordField1.equals(""))
            ConsultaUsuarioTCA();
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void corteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_corteActionPerformed
        // TODO add your handling code here:
        cent_caja();
    }//GEN-LAST:event_corteActionPerformed

    private void desasignaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desasignaActionPerformed
        // TODO add your handling code here:
        //ConsPacientes((DefaultTableModel) jTable9.getModel(),jTextField14.getText(),1); 
        
    }//GEN-LAST:event_desasignaActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        InsertaCaja();
    }//GEN-LAST:event_jButton21ActionPerformed

    private void cajacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajacActionPerformed
        // TODO add your handling code here:
        cent_cajac();
    }//GEN-LAST:event_cajacActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        UpdFolio(jTextField60.getText());
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        consTransaccionFecha(ConvertirFecha(Sistema.FechaPeriodo(jDateChooser1.getCalendar())),ConvertirFecha(Sistema.FechaPeriodo(jDateChooser2.getCalendar())));
        SumaTabla();
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
        Reporte_CajaChica(Sistema.FechaPeriodo(jDateChooser1.getCalendar()),Sistema.FechaPeriodo(jDateChooser2.getCalendar()));
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jTextField60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField60ActionPerformed
        // TODO add your handling code here:
        String folio = jTextField60.getText();
        char folioo[] = new char[10];
        folioo = folio.toCharArray();
        int opc = 0;
        
        if(folioo[0] == '@'){
            opc = JOptionPane.showConfirmDialog(null,"¿eliminar folio?","opcion",JOptionPane.YES_NO_OPTION);
            if(opc == 0){
                if(ConsultasSistema.InsertaActualiza(conMySQL,"DELETE FROM cajachica WHERE folio = "+folio) > 0)
                    JOptionPane.showMessageDialog(null,"folio eliminado");
            }
        }
        else getDatosFolio(folio);
    }//GEN-LAST:event_jTextField60ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
        LimpiaEdicion();
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // TODO add your handling code here:
        String medi = "";
        if ( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            getMedicos();
            clvmed = modeloT1.getValueAt(jTable1.getSelectedRow(),5).toString();
            clvpac = modeloT1.getValueAt(jTable1.getSelectedRow(),0).toString();
            nmbpac = modeloT1.getValueAt(jTable1.getSelectedRow(),2).toString();
            espmed = modeloT1.getValueAt(jTable1.getSelectedRow(),6).toString();
            exppac = modeloT1.getValueAt(jTable1.getSelectedRow(),7).toString();
            jLabel3.setText("PACIENTE:  [ " + clvpac + " ] " + nmbpac);
            if(!clvmed.equals("M00263")){
                medi = "[ " + clvmed + " ] " + modeloT1.getValueAt(jTable1.getSelectedRow(),4).toString();
                jLabel2.setText(jLabel2.getText()+"   NOTA: EL MEDICO " + medi + "   YA SE ENCUENTRA ASIGNADO");
                Object []Fila = new Object [5];
                Fila[0] = clvmed;
                Fila[1] = modeloT1.getValueAt(jTable1.getSelectedRow(),4);
                Fila[2] = espmed;
                Fila[3] = "0";
                Fila[4] = "NO";
                modeloT4.addRow(Fila);
            }
            TabbedPane(1);
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable3KeyPressed
        // TODO add your handling code here:
        String clv = "";
        int a = 0;
        if ( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            clv = modeloT3.getValueAt(jTable3.getSelectedRow(),0).toString();
            for(int i=0;i<modeloT4.getRowCount();i++)
                if(clv.equals(modeloT4.getValueAt(i,0).toString())) a++;
            if(a == 0){
                Object []Fila = new Object [5];
                Fila[0] = clv;
                Fila[1] = modeloT3.getValueAt(jTable3.getSelectedRow(),1);
                Fila[2] = modeloT3.getValueAt(jTable3.getSelectedRow(),2);
                Fila[3] = "0";
                Fila[4] = "NO";
                modeloT4.addRow(Fila);
            }
            TabbedPane(2);
        }
    }//GEN-LAST:event_jTable3KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        TabbedPane(1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        SetHonorarios(getFolioCaja());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        TabbedPane(2);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        if(jTextField1.getText().equals("?")){
            ConsPacFolioAb();
            cent_pacpen();
        }
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTable5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable5KeyPressed
        // TODO add your handling code here:
        if ( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            String fol = modeloT5.getValueAt(jTable5.getSelectedRow(),0).toString();
            consMedAsignados(fol);
            jTextField1.setText(fol);
            jTextField1.setEnabled(false);
            jLabel10.setText("PACIENTE: "+modeloT5.getValueAt(jTable5.getSelectedRow(),1).toString());
            PacientesPend.dispose();
        }
    }//GEN-LAST:event_jTable5KeyPressed

    private void jComboBox1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBox1PropertyChange
        // TODO add your handling code here:
        try{
            ConsultaEdoCta(jTextField1.getText(),jComboBox1.getSelectedItem().toString());
            ConsMedNmb(jComboBox1.getSelectedItem().toString());
        }catch(Exception e) {}
    }//GEN-LAST:event_jComboBox1PropertyChange

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
        float abono = Float.parseFloat(jTextField5.getText());
        int tipo = 0;
        
        System.out.println(abono);
        System.out.println(debe);
        
        if(abono > debe)
            JOptionPane.showMessageDialog(null, "abono mayor al audeudo","error",JOptionPane.ERROR_MESSAGE);
        else{
            if(abono <= 0)
                JOptionPane.showMessageDialog(null, "el abono debe ser mayor a cero","error",JOptionPane.ERROR_MESSAGE);
            else{
                if(abono == debe) tipo = 1;
                setAbono(jComboBox1.getSelectedItem().toString(),jTextField1.getText(),abono,tipo);
            }
        }
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String nm = jTextField6.getText().toUpperCase();
        String dom = jTextField7.getText().toUpperCase();
        String cp = jTextField8.getText();
        String cd = jTextField9.getText().toUpperCase();
        String st = jTextField10.getText().toUpperCase();
        String email = jTextField11.getText();
        String exp = jTextField12.getText();
        String rfc = jTextField13.getText().toUpperCase();
        
        if((nm.equals(""))||(dom.equals(""))||(cp.equals(""))||(cd.equals(""))||(st.equals(""))||(email.equals(""))||(rfc.equals("")))
            JOptionPane.showMessageDialog(null, "llene todos los campos","error",JOptionPane.ERROR_MESSAGE);
        else{
            consulta = "INSERT INTO fiscales(exp,rfc,nombre,direccion,cp,ciudad,estado,correo) VALUES('" +
                       exp+"','"+rfc+"','"+nm+"','"+dom+"','"+cp+"','"+cd+"','"+st+"','"+email+"')";
            if(ConsultasSistema.InsertaActualiza(conMySQL,consulta) > 0){
                JOptionPane.showMessageDialog(null, "datos fiscales guardados");
                LimpiaFiscales();
                Fiscales.dispose();
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        getCaja();
        if(modeloT7.getRowCount() > 0) jButton7.setEnabled(true);
        else jButton7.setEnabled(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int opc = 0,a=0;
        opc = JOptionPane.showConfirmDialog(null,"¿generar corte? esta opción no se puede deshacer","seleccione",JOptionPane.YES_NO_OPTION);
        if(opc == 0){
            consulta = "UPDATE consecutivos SET statuss=1 WHERE numero="+jTextField15.getText();
            if(ConsultasSistema.InsertaActualiza(conMySQL,consulta) > 0){
                for(int i=0;i<modeloT7.getRowCount();i++){
                    consulta = "UPDATE registro SET reg_entrega=1 WHERE reg_foliocaja='"+modeloT7.getValueAt(i,4)+"' AND reg_entrega=0";
                    if(ConsultasSistema.InsertaActualiza(conMySQL,consulta) > 0) a++;
                }
                if(modeloT7.getRowCount() == a){
                    JOptionPane.showMessageDialog(null, "se ha generado el corte");
                    Impresion_CorteGral(jTextField15.getText());
                    LimpiaCorte();
                }
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        consulta = "SELECT DISTINCT b.hon_med,RTRIM(c.ApellidoPaterno)+' '+RTRIM(c.ApellidoMaterno)+' '+RTRIM(c.Nombre) AS nmed,\n" +
                    "               SUM(b.tot) AS total\n" +
                    "FROM hospac a\n" +
                    "INNER JOIN OPENQUERY(MYSQL,'SELECT *,SUM(b.reg_monto) AS tot\n" +
                    "				 FROM honorarios a\n" +
                    "				 INNER JOIN registro b ON b.reg_med=a.hon_med AND b.reg_pac=a.hon_fol\n" +
                    "				 WHERE b.reg_entrega=1\n" +
                    "				 GROUP BY a.hon_med') b ON b.hon_fol=LTRIM(a.p_fol_cto)\n" +
                    "INNER JOIN hosimd c ON c.Medico=a.p_med0\n" +
                    "GROUP BY b.hon_med,c.ApellidoPaterno,c.ApellidoMaterno,c.Nombre";
        try{
            for(int i=modeloT8.getRowCount()-1;i>=0;i--) modeloT8.removeRow(i);
            stat = con.createStatement();
            rs=ConsultasSistema.Estructura(con,consulta);
            while(rs.next()){
                Object []Fila = new Object [3];
                Fila[0] = rs.getString("hon_med");
                Fila[1] = rs.getString("nmed");
                Fila[2] = Sistema.ConvierteMoneda(rs.getString("total"));
                modeloT8.addRow(Fila);
            }
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTable8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable8KeyPressed
        // TODO add your handling code here:
        if ( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            getDetallePagosMedico(modeloT8.getValueAt(jTable8.getSelectedRow(),0).toString());
            cent_pago();
        }
    }//GEN-LAST:event_jTable8KeyPressed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        String folcaja,folpac;
        int opc = 0;
        opc = JOptionPane.showConfirmDialog(null,"¿entregar pago a médico? esta opción no se puede deshacer",
                                                 "seleccione",JOptionPane.YES_NO_OPTION);
        if(opc == 0){
            for(int i=0;i<modeloT9.getRowCount();i++){
                folcaja = modeloT9.getValueAt(i,5).toString();
                folpac = modeloT9.getValueAt(i,0).toString();
                opc += setPagoMedico(folcaja,folpac);
            }
            if(opc == modeloT9.getRowCount()){
                JOptionPane.showMessageDialog(null, "registro actualizado");
                DetallePago.dispose();
                for(int i=modeloT8.getRowCount()-1;i>=0;i--) modeloT8.removeRow(i);
            }
        }
    }//GEN-LAST:event_jButton9ActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Admision;
    private javax.swing.JDialog Caja;
    private javax.swing.JDialog CajaChica;
    private javax.swing.JDialog DetallePago;
    private javax.swing.JDialog Fiscales;
    private javax.swing.JDialog Medicos;
    private javax.swing.JDialog PacientesPend;
    private javax.swing.JDialog Registro;
    private javax.swing.JMenu adm;
    private javax.swing.JMenu caja;
    private javax.swing.JMenuItem cajac;
    private javax.swing.JMenuItem corte;
    private javax.swing.JMenuItem desasigna;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox12;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField55;
    private javax.swing.JTextField jTextField56;
    private javax.swing.JTextField jTextField57;
    private javax.swing.JTextField jTextField58;
    private javax.swing.JTextField jTextField59;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField60;
    private javax.swing.JTextField jTextField61;
    private javax.swing.JTextField jTextField62;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JMenuItem men;
    private javax.swing.JMenu sistemas;
    // End of variables declaration//GEN-END:variables
}
