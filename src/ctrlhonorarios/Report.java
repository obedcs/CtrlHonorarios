/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrlhonorarios;

import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Obed
 */
public class Report {
    static Connection conn = null;
    static Statement st = null;
    static ResultSet rs = null;
    private static JasperPrint report;
    static String carpeta="";
    Principal Mainn = new Principal();
    
    public static Date ConvertirFecha(String Fecha){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {date = formatter.parse(Fecha);} catch (ParseException e) {e.printStackTrace();}
        return date;
    }
    
    public static void mostrarReporteEntHon(Connection con,String folio,String medico, String paciente) throws SQLException, JRException {
        Map parametro = new HashMap();
        parametro.put("Folio", folio);
        parametro.put("nmed", medico);
        parametro.put("npac", paciente);
        java.lang.String url = carpeta+"ReciboPago.jrxml";
        conn = con;

        JasperReport reportes = JasperCompileManager.compileReport(url);
        JasperPrint print = JasperFillManager.fillReport(reportes, parametro, conn);
        
        //para imprimir directamente
        JasperPrintManager.printReport(print, false);
    }
    
    public static void mostrarReporteHonAgrupado(Connection con,String Usuario,String Med,int stats,String consec) throws SQLException, JRException {
        Map parametro = new HashMap();
        parametro.put("Cajero", Usuario);
        parametro.put("Med", Med);
        parametro.put("StatCnt", stats);
        parametro.put("Consec", consec);
        java.lang.String url = carpeta+"ReciboPagoAgrupado.jrxml";
        conn = con;

        JasperReport reportes = JasperCompileManager.compileReport(url);
        JasperPrint print = JasperFillManager.fillReport(reportes, parametro, conn);
        
        //para imprimir directamente
        JasperPrintManager.printReport(print, false);
    }
    
    public static void mostrarReporteCorteGral(Connection con,String cajero) throws SQLException, JRException {
        Map parametro = new HashMap();
        parametro.put("caja", Integer.parseInt(cajero));
        java.lang.String url = carpeta+"CorteCaja.jrxml";
        conn = con;

        JasperReport reportes = JasperCompileManager.compileReport(url);
        JasperPrint print = JasperFillManager.fillReport(reportes, parametro, conn);
        
        //para imprimir directamente
        JasperPrintManager.printReport(print, false);
    }
    
    public static void mostrarReporteCorteContable(Connection con,String fecha,String cajero, String corte) throws SQLException, JRException {
        Map parametro = new HashMap();
        parametro.put("Fecha", ConvertirFecha(fecha));
        parametro.put("Usuario", cajero);
        parametro.put("Corte", corte);
        java.lang.String url = carpeta+"CorteCajaContable.jrxml";
        conn = con;

        JasperReport reportes = JasperCompileManager.compileReport(url);
        JasperPrint print = JasperFillManager.fillReport(reportes, parametro, conn);
        
        //para imprimir directamente
        JasperPrintManager.printReport(print, false);
    }
    
    public static void EdoCtaPaciente(Connection con,String folio) throws SQLException, JRException {
        Map parametro = new HashMap();
        parametro.put("Pacte", folio);
        java.lang.String url = carpeta+"EdoCtaPaciente.jrxml";
        conn = con;

        JasperReport reportes = JasperCompileManager.compileReport(url);
        JasperPrint print = JasperFillManager.fillReport(reportes, parametro, conn);
        
        //para imprimir directamente
        JasperPrintManager.printReport(print, false);
    }
    
    public static void mostrarReporteCorteGralEstatus(Connection con,String fecha,String cajero,int Estatus) throws SQLException, JRException {
        Map parametro = new HashMap();
        parametro.put("Fecha", ConvertirFecha(fecha));
        parametro.put("Usuario", cajero);
        parametro.put("Estatus", Estatus);
        java.lang.String url = carpeta+"CorteCajaStatus.jrxml";
        conn = con;

        JasperReport reportes = JasperCompileManager.compileReport(url);
        JasperPrint print = JasperFillManager.fillReport(reportes, parametro, conn);
        
        //para imprimir directamente
        JasperPrintManager.printReport(print, false);
    }
    
    public static void mostrarReporteDevIngEfe(Connection con,int Statuss,String Titulo) throws SQLException, JRException {
        Map parametro = new HashMap();
        parametro.put("Stat", Statuss);
        parametro.put("Titulo", Titulo);
        java.lang.String url = carpeta+"DevolucionYCargo.jrxml";
        conn = con;

        JasperReport reportes = JasperCompileManager.compileReport(url);
        JasperPrint print = JasperFillManager.fillReport(reportes, parametro, conn);
        
        //para imprimir directamente
        JasperPrintManager.printReport(print, false);
    }
    
    public static void mostrarReporteConsTrans(Connection con,int ID,int ST) throws SQLException, JRException {
        Map parametro = new HashMap();
        parametro.put("ID", ID);
        parametro.put("Estatus", ST);
        java.lang.String url = carpeta+"DevolucionYCargoConsulta.jrxml";
        conn = con;

        JasperReport reportes = JasperCompileManager.compileReport(url);
        JasperPrint print = JasperFillManager.fillReport(reportes, parametro, conn);
        
        //para imprimir directamente
        JasperPrintManager.printReport(print, false);
    }
    
    public static void CajaChica(Connection con,String fechai,String fechaf,float saldo,String logohste,String logoctrl,String logoback) throws SQLException, JRException {
        Map parametro = new HashMap();
        
        JOptionPane.showMessageDialog(null,"Carpeta Report Class: "+carpeta);
        parametro.put("fechainicial", ConvertirFecha(fechai));
        parametro.put("fechafinal", ConvertirFecha(fechaf));
        parametro.put("Saldo", saldo);
        parametro.put("LogoP", logohste);
        parametro.put("LogoCtrl", logoctrl);
        parametro.put("LogoBack", logoback);
        java.lang.String url = carpeta+"SaldoCajaChica.jrxml";
        conn = con;

        JasperReport reportes = JasperCompileManager.compileReport(url);
        JasperPrint print = JasperFillManager.fillReport(reportes, parametro, conn);
        
        //para mostrar el reporte
        JDialog viewer = new JDialog(new JFrame(),"Vista Previa del Reporte", true);
        viewer.setSize(950,700);
        viewer.setLocationRelativeTo(null);
        JRViewer jrv = new JRViewer(print);
        viewer.getContentPane().add(jrv);
        viewer.show();
    }
}
