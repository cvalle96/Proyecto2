import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

import BBDD.OracleBD;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.time.LocalTime;
import Utilidades.parsearFecha;


public class SerialTest extends parsearFecha implements SerialPortEventListener  {
    SerialPort serialPort;
    int idAula=1;
    static OracleBD bd;
    /** The port we're normally going to use. */
    private static final String PORT_NAMES[] = {"COM4"};



    /**
     * A BufferedReader which will be fed by a InputStreamReader
     * converting the bytes into characters
     * making the displayed results codepage independent
     */
    private BufferedReader input;
    /** The output stream to the port */
    private OutputStream output;
    /** Milliseconds to block while waiting for port open */
    private static final int TIME_OUT = 2000;
    /** Default bits per second for COM port. */
    private static final int DATA_RATE = 9600;

    public void initialize() {
        // the next line is for Raspberry Pi and
        // gets us into the while loop and was suggested here was suggested https://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186


        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        //First, Find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;

                    break;
                }
            }
        }
        if (portId == null) {
            System.out.println("Could not find COM port.");
            return;
        }

        try {
            // open serial port, and use class name for the appName.
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    TIME_OUT);

            // set port parameters
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            // open the streams
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();

            // add event listeners
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    /**
     * This should be called when you stop using the port.
     * This will prevent port locking on platforms like Linux.
     */
    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    /**
     * Handle an event on the serial port. Read the data and print it.
     */
    public synchronized void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                String inputLine = input.readLine();
                //System.out.println(inputLine);
                String[] parts = inputLine.split("-");
                String part1 = parts[0]; //humedad
                String part2 = parts[1]; //temperatura
                String part3 = parts[2]; //Ruido
                Integer humedad = Integer.valueOf(part1);
                Integer temperatura = Integer.valueOf(part2);
                Float ruido = Float.valueOf(part3);
                LocalTime time = LocalTime.now();
                // String hora = parsearFechaSQL(time);
                //Formatear hora para que se lo coma el sql

                //System.out.println("Humedad = " + humedad);
                //System.out.println("Temperatura = " + temperatura);
                //System.out.println("Ruido = "+ ruido);
                //System.out.println("Hora = "+ hora);

                if (idAula >= 6){
                    idAula=1;
                }

                String query = "INSERT INTO registro (id_aula, temperatura, ruido, humedad,hora) VALUES ("+idAula+", " + temperatura + ", " + ruido + ", " + humedad + ", "+ "null" +")";
                System.out.println(query);
                bd.makeInsert(query);
                idAula++;
            } catch (Exception e) {
                //System.err.println(e.toString());
                e.printStackTrace();
            }

        }
        // Ignore all the other eventTypes, but you should consider the other ones.
    }

    public static void main(String[] args) throws Exception {
        bd= new OracleBD();
        bd.setConnection();
        SerialTest main = new SerialTest();
        main.initialize();
        Thread t=new Thread() {
            public void run() {
                //the following line will keep this app alive for 1000 seconds,
                //waiting for events to occur and responding to them (printing incoming messages to console).
                try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
            }
        };
        t.start();
        System.out.println("Comienza la insercción de datos en la BBDD");
    }
}