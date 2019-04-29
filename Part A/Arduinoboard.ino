void setup()
{
  pinMode(2, INPUT);
  pinMode(3, INPUT);
  pinMode(9, INPUT);
  pinMode(11, OUTPUT);
  pinMode(12, OUTPUT);
  pinMode(13, OUTPUT);
}
const int LOCKED = 1;
const int WAITING = 2;
const int UNLOCKED = 3;
int currentState = LOCKED;
int motionSensed = LOW;
int buttonLeft = 0;
int buttonRight = 0;
int counter = 0;
int incorrectAndLocking = 0;

void loop()
{
  while(currentState == LOCKED){
    incorrectAndLocking = 1;
    buttonLeft = 0;
    buttonRight = 0;
    counter = 0;
    digitalWrite(13, HIGH);
    motionSensed = digitalRead(9);
    if(motionSensed == HIGH){
      	digitalWrite(13, LOW);
    	currentState = WAITING;
    }
  }
  while(currentState == WAITING){
    digitalWrite(12, HIGH);
    while((buttonLeft==0 || buttonRight==0)&& counter<2){
    int buttonLeftReading = digitalRead(3);
      if(buttonLeftReading==HIGH){
        digitalWrite(12, LOW);
        delay(50);
        digitalWrite(12, HIGH);
        buttonLeft = 1;
        if(counter==1 && buttonRight==1){
          incorrectAndLocking = 0;
        }
        counter++;

      }
  	int buttonRightReading = digitalRead(2);
      if(buttonRightReading==HIGH){
        digitalWrite(12, LOW);
        delay(50);
        digitalWrite(12, HIGH);
        buttonRight =1;
        if(counter==1 && buttonLeft==1){
          incorrectAndLocking = 1;
        }
        counter++;
      }
    }
    if(incorrectAndLocking == 0){
    currentState = UNLOCKED;
    }else{
      digitalWrite(12, LOW);
      currentState = LOCKED;
    }
  }
  while(currentState == UNLOCKED){
    digitalWrite(12, LOW);
    digitalWrite(11, HIGH);
    delay(1000);
    digitalWrite(11, LOW);
    currentState = LOCKED;
  }
}
