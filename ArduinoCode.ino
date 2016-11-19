// these constants describe the pins. They won't change:
const int groundpin = 18;             // analog input pin 4 -- ground
const int powerpin = 19;              // analog input pin 5 -- voltage
const int xpin = A0;                  // x-axis of the accelerometer
const int ypin = A1;                  // y-axis
const int zpin = A2;                  // z-axis (only on 3-axis models)


#include <SoftwareSerial.h>

SoftwareSerial mySerial(10, 11); // RX, TX

void setup() {
  // Open serial communications and wait for port to open:
  Serial.begin(9600);

  
  // Provide ground and power by using the analog inputs as normal
  // digital pins.  This makes it possible to directly connect the
  // breakout board to the Arduino.  If you use the normal 5V and
  // GND pins on the Arduino, you can remove these lines.
  pinMode(groundpin, OUTPUT);
  pinMode(powerpin, OUTPUT);
  digitalWrite(groundpin, LOW);
  digitalWrite(powerpin, HIGH);
  while (!Serial) {
    ; // wait for serial port to connect. Needed for native USB port only
  }


  Serial.println("Goodnight moon!");

  // set the data rate for the SoftwareSerial port
  mySerial.begin(9600);
  
  
}
int xc=0;
int device_id = 01;
double initaccel,max,min;
bool concf;
int conc;

void loop() { // run over and over



int ts,ps,as,fs;
bool asb;
if(xc==0){max = min = initaccel=conc=0;}

int tanalog = (rand()%24)+160;
double v;
v = (tanalog*5.0)/1023;
//int t =(int)( (v-0.5)*100);
int t = (rand()%6 +29);

int pulse = (rand()%80)+100;


xc= xc+5;
xc=(xc%5000);
  int x = analogRead(xpin);
  double gx = (2/(621.0 - 398.0)) *(x - 398.0) - 1;
  int y = analogRead(ypin);
  double gy = (2/(608.0 - 387.0)) *(y - 387.0) - 1;
  int z = analogRead(zpin);
  double gz = (2/(606.0 - 407.0)) *(z - 407) - 1;
  double acc = sqrt((gx*gx) + (gy*gy) + (gz*gz));
 





if(xc==5)
  initaccel=acc;
if(initaccel>=acc){max = initaccel;min = acc;}
else {max = acc; min = initaccel;}

if(xc>5&& (acc-min<0.1))
  conc = 1;
else
{conc=0;xc=0;}
if(xc >4990&& conc==1)concf=false;
else 
{concf = true;}


 

if(pulse >=105 && pulse <= 180)
  ps =2;
else if(pulse >=100 && pulse <=190)
  ps = 1;
else
   ps =0;


if(t>= 32 && t<= 35)
  ts =2;
else if(t >=29 && t<=38)
  ts =1;
else 
  ts =0;
if(acc >=10)
  {asb = true;as = 1;}
else 
  {asb=false;as =2;}

if(ps==2 && as ==2 &&ts==2 && concf)
  fs =2;
else if(ps==0|| ts==0)
  fs =0;
else if(ps==1||ts==1||as==1||!concf)
{
  fs =1;
  if(ps==1&&ts==1)
    fs=0;
  else if(ps==1 && as ==1)
    fs =0;
  else if(ps ==1 && !concf)
    fs =0;
  else if(ts==1&& as==1)
    fs =0;
  else if(ts ==1&& !concf)
    fs=0;
  else if(as==1&& !concf)
    fs=0;
}


  char buff[18];
//  String id = String(device_id);
//  String temp = String(t);
//  String pul = String(pulse);
//  String ac = String(acc);
//  String fstate = String(fs);
//  String s;
//
//  char buff[18] = {id, ' ', temp, ' ', pul, ' ', ac, ' ', '1', fstate, '/0'}
if(concf && asb)
   sprintf(buff,"%d %d %d t t %d\n", device_id,t,pulse,fs);
else if(concf && !asb)

  //mySerial.print("hi");
   sprintf(buff,"%d %d %d t f %d\n", device_id,t,pulse,fs);
else if(!concf && asb)

  //mySerial.print("hi");
   sprintf(buff,"%d %d %d f t %d\n", device_id,t,pulse,fs);
else if(!concf && !asb)

  //mySerial.print("hi");
   sprintf(buff,"%d %d %d f f %d\n", device_id,t,pulse,fs);
      

//mySerial.write("Connected\n");


 
  mySerial.write(buff);
  
 // mySerial.write(pulse);
  //mySerial.write(acc);
 // if(concf)
   // mySerial.write('1');
 // else
  //  mySerial.write('0');
 // mySerial.write(fs);
  

  
  delay(5);
 // mySerial.write(buff);
 
}

