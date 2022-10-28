#include <HCSR04.h>
#include <LiquidCrystal_I2C.h>
//—– Adressage matériel —–i
LiquidCrystal_I2C lcd(0x27, 16, 2);
//LiquidCrystal_I2C lcd(0x3F,16,2);
 
// definition des broches du capteur
const int trigPin = 2;
const int echoPin = 3;
 
// initialisation du capteur avec les broches utilisees.
UltraSonicDistanceSensor distanceSensor(trigPin, echoPin);
void setup() {
lcd.init(); // initialisation de l’afficheur
// initialisation du port serie a 9600 band pour afficher les valeurs mesurees par le capteur.
Serial.begin(9600);
}
void loop() {
lcd.backlight();
lcd.clear(); // effacer le contenu de l’Afficheur LCD
// / toutes les 500 millisecondes nous faisons une mesure et nous affichons la distance en centimetre sur le port serie.
Serial.println(distanceSensor.measureDistanceCm());
lcd.print("distance = ");
lcd.setCursor(0,1); // se positionner à la deuxième ligne
lcd.print(distanceSensor.measureDistanceCm()+1);
lcd.print(" cm");
delay(500);
}
