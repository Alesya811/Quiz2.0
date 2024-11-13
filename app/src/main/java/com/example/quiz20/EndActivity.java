package com.example.quiz20;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EndActivity extends AppCompatActivity {
    TextView view;
    String rezuilt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_end);
        Button b = findViewById (R.id.buttonAgain);
         view = findViewById(R.id.textRez);
        int score = getIntent().getIntExtra("score",0);
        String textrez = Integer.toString(score);
        checkRez(score);
        String answer ="\n1. \"Легенды\" (Это сборник лучших песен, а не полноценный альбом) \n" +
                "2. 1989\n" +
                "3. О страшной мести куклы колдуну (Кукла мстит колдуну за то, что он её предал)\n" +
                "4. Крыса (Это животное ассоциируется с негативом, выживанием и свободой)\n" +
                "5. Игорь Жевтун "
                ;
        view.setText("Ваш результат:"+ textrez +"\n"+rezuilt+ "\nОтветы:"+answer);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EndActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void checkRez(int r)
    {
        if (r == 5)
        {
            rezuilt = "ты истинный фанат \"Короля и Шута\"!";
            return;
        }
        if(r < 3)
        {
            rezuilt = "тебе есть над чем подумать";
        }
        else {
            rezuilt = "ты неплохо знаешь творчество группы";
        }
        }
    }
