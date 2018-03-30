package com.example.annaimira.text_to_speech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.content.Intent;
import java.util.Locale;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener, OnInitListener {



    private TextToSpeech myTTS;
    //status check code
    private int MY_DATA_CHECK_CODE = 0;

    //create the Activity
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get a reference to the button element listed in the XML layout
        Button speakButton = (Button)findViewById(R.id.button5);
        //listen for clicks
        speakButton.setOnClickListener(this);
        //Button alpha = (Button)findViewById(R.id.button);
        //listen for clicks
        //alpha.setOnClickListener(this);
        //Button rhymes = (Button)findViewById(R.id.button3);
        //listen for clicks
        //rhymes.setOnClickListener(this);
        //Button num = (Button)findViewById(R.id.button2);
        //listen for clicks
        //num.setOnClickListener(this);
        //Button week = (Button)findViewById(R.id.button4);
        //listen for clicks
        //week.setOnClickListener(this);

        //check for TTS data
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
    }

    //respond to button clicks
    public void onClick(View v) {

        switch(v.getId()){
            //get the text entered
            case R.id.button5:
            { EditText enteredText = (EditText)findViewById(R.id.editText);
                String words = enteredText.getText().toString();
                speakWords(words);
            }
            break;
            //case R.id.button: {

                //String q = "a b c d e f g h i j k l m n o p q r s t u v w x y z";
                //speakWords(q);

            //}
            //break;
            //case R.id.button3:
            //{
                //String s="";
                //for (int i = 0; i <10; i++) {

                    //s=s+Integer.toString(i);
                //}
                //speakWords(s);
            //}

            //break;
            //case R.id.button2:
            //{
                //String a = "Twinkle twinkle little star, How I wonder what you are. Up above the world so high, like a diamond in the sky.";




                //String b ="Johnny Johnny yes papa, eating sugar no papa,telling lies no papa,open your mouth, ha ha haa!";
                //speakWords(a+b);
            //}
            //break;
            //case R.id.button4:
            //{
                //speakWords("Sunday Monday Tuesday Wednesday Thursday Friday Saturday");
            //}
            //break;
            default:
                break;
        }


    }

    //speak the user text
    private void speakWords(String speech) {

        //speak straight away
        myTTS.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
    }

    //act on result of TTS data check
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                //the user has the necessary data - create the TTS
                myTTS = new TextToSpeech(this, this);
            }
            else {
                //no data - install it now
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }

    //setup TTS
    public void onInit(int initStatus) {

        //check for successful instantiation
        if (initStatus == TextToSpeech.SUCCESS) {
            if(myTTS.isLanguageAvailable(Locale.US)==TextToSpeech.LANG_AVAILABLE)
                myTTS.setLanguage(Locale.US);
            myTTS.isLanguageAvailable(Locale.UK);
            myTTS.isLanguageAvailable(Locale.FRENCH);
            myTTS.isLanguageAvailable(Locale.GERMAN);
            myTTS.isLanguageAvailable(Locale.CHINESE);
            myTTS.isLanguageAvailable(Locale.ITALIAN);

        }
        else if (initStatus == TextToSpeech.ERROR) {
            Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
        }
    }
}

