#include "DHT.h" //incluimos la libreria del sensor de temperatura/humedad
#define DHTTYPE DHT11 
const int DHTPin = 2;     // Conectamos el sensor al pin 2
const int sensorPIN = A0;
const int sampleWindow = 50; // Ancho ventana en mS (50 mS = 20Hz) 
DHT dht(DHTPin, DHTTYPE);
 
void setup() {
   Serial.begin(9600);
   
   dht.begin();
}
 
void loop() {
   //Establecemos que lea datos cada MINUTO
   delay(12000); //un minuto son 60000 segundos
   unsigned long startMillis= millis();
 
   unsigned int signalMax = 0;
   unsigned int signalMin = 1024;
 
   // Recopilar durante la ventana
   unsigned int sample;
   while (millis() - startMillis < sampleWindow)
   {
      sample = analogRead(sensorPIN);
      if (sample < 1024)
      {
         if (sample > signalMax)
         {
            signalMax = sample;  // Actualizar máximo
         }
         else if (sample < signalMin)
         {
            signalMin = sample;  // Actualizar mínimo
         }
      }
   }
   unsigned int peakToPeak = signalMax - signalMin;  // Amplitud del sonido
   double volts = (peakToPeak * 5.0) / 1024;  // Convertir a tensión
 
   
   int h = dht.readHumidity();
   int t = dht.readTemperature();
 
   if (isnan(h) || isnan(t)) {
      Serial.println("Error en el sensor");
      return;
   } 
   //Serial.print("Humedad: ");
   Serial.print(h);
   Serial.print("-");     
   //Serial.print("% ");
   //Serial.print("Temperatura: ");
   Serial.print(t);
   Serial.print("-");
   //Serial.print("ºC ");   
   //Serial.print("Ruido: ");
   Serial.print(volts);     
   Serial.print("\n");


   
}
