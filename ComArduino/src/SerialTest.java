import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

import BBDD.OracleBD;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.time.LocalTime;
import Utilidades.parsearFecha;


public class SerialTest extends parsearFecha implements SerialPortEventListener  {
    SerialPort serialPort;
    int aula =1;
    static OracleBD bd;
    /** Puerto COM a utilizar */
    private static final String PORT_NAMES[] = {"COM4"};




    private BufferedReader input;

    private OutputStream output;

    private static final int TIME_OUT = 2000;

    private static final int DATA_RATE = 9600;

    public void initialize() {



        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();


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

            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    TIME_OUT);


            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);


            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();


            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }


    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }


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
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String hora = dtf.format(now);
                if (aula >= 6){
                    aula =1;
                }
                String query = "INSERT INTO registro (aula, temperatura, ruido, humedad,hora) VALUES ("+aula+", " + temperatura + ", " + ruido + ", " + humedad + ", '"+ hora +"')";
                System.out.println(query);
                bd.makeInsert(query);
                aula++;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args) throws Exception {
        bd= new OracleBD();
        bd.setConnection();
        SerialTest main = new SerialTest();
        main.initialize();
        Thread t=new Thread() {
            public void run() {
                
                try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
            }
        };
        t.start();
        System.out.println("Comienza la insercción de datos en la BBDD");
    }
}