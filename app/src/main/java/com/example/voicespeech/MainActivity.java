package com.example.voicespeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.util.ResourceUtil;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button mBtnSpeech;
    private TextToSpeech tts;
    private EditText mEtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVoiceSpeech();
    }

    private void initVoiceSpeech() {
        mEtText = findViewById(R.id.et_text);

        mBtnSpeech = findViewById(R.id.btn_speech);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
            // 判断是否转化成功
                if (status == TextToSpeech.SUCCESS){
                    //默认设定语言为中文，原生的android貌似不支持中文。
                    int result = tts.setLanguage(Locale.CHINESE);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Toast.makeText(MainActivity.this, R.string.notAvailable, Toast.LENGTH_SHORT).show();
                    }
//                    else{
//                        //不支持中文就将语言设置为英文
//                        tts.setLanguage(Locale.US);
//                    }
                }
            }
        });
        tts.setPitch(1.0f);
        mBtnSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToSpeak = mEtText.getText().toString().trim();
                tts.speak(textToSpeak,TextToSpeech.QUEUE_FLUSH,null,null);
            }
        });


    }
}
