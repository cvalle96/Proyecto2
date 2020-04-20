package Utilidades;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class parsearFecha {

    public static Timestamp parsearFechaSQL(String fechaString) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date utilDate = sdf.parse(fechaString);
        java.sql.Timestamp timedate=new java.sql.Timestamp(utilDate.getTime());
        return timedate;
    }

    public static Timestamp parsearFechaSQL(Date fecha){//DE java a SQL
        java.sql.Timestamp timedate=new java.sql.Timestamp(fecha.getTime());
        return timedate;

    }

    public static Date parsearFechaUtil(String fechaString) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date utilDate = sdf.parse(fechaString);
        return utilDate;
    }

    public static String fechaEnString(Date fechaDate){
        SimpleDateFormat fechaHora = new SimpleDateFormat("dd/MM/yyyy");
        String convertido = fechaHora.format(fechaDate);
        return convertido;
    }
    public static String fechaEnStringSinHoras(Date fechaDate){
        SimpleDateFormat fechaHora = new SimpleDateFormat("dd/MM/yyyy");
        String convertido = fechaHora.format(fechaDate);
        return convertido;
    }

}