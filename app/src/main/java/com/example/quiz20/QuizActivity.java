package com.example.quiz20;
/*
 * @author
 * @theme
 * @date
 */



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    int currentQuestionIndex = 1;
    int score;
    boolean isQuizFinished;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TextView tq;
    ArrayList<Question> questions = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        questions.add(new Question("Какой из этих альбомов не принадлежит \"Королю и шуту\"", new String[]{"Камнем по голове", "Жертвы", "Непокорные", "Легенды"}, 3));
        questions.add(new Question("В каком году была основана группа \"Король и шут\"", new String[]{"1987", "1989", "2017", "1917"}, 1));
        questions.add(new Question("О чем поется в песне \"Кукла колдуна\"", new String[]{"О любви куклы к колдуну", "О ревности колдуна к кукле", "О мести куклы", "О создании куклы"}, 2));
        questions.add(new Question("Какое их этих животных было символом группы \"Король и шут\"", new String[]{"Крыса", "Волк", "Единорог", "Кот"}, 0));
        questions.add(new Question("Кто из этих людей гн является участником группы \"Король и шут\"", new String[]{" Михаил Горшнев", "Андрей Князев", "Александр Балунов", "Игорь Жевтун"}, 3));
        Collections.shuffle(questions);
        button1 = findViewById(R.id.butto1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        tq = findViewById(R.id.Qtext);
        loadNextQuestion();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(0);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(1);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(2);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(3);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /***
     *
     * @param selectedAnswerIndex
     * @return
     */
    public boolean checkAnswer(int selectedAnswerIndex) {
        Question currentQuestion = questions.get(currentQuestionIndex - 1);
        int correctAnswerIndex = currentQuestion.getCorrectAnswerIndex();
        if (selectedAnswerIndex == correctAnswerIndex) {
            score++;
            
            Toast.makeText(this, "Правильно!", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Неправильно!", Toast.LENGTH_SHORT).show();
        }
        currentQuestionIndex++;
        loadNextQuestion();
        return selectedAnswerIndex == correctAnswerIndex;

    }

    public void loadNextQuestion() {
        if (currentQuestionIndex <= questions.size()) {

            Question currentQuestion = questions.get(currentQuestionIndex - 1);
            tq.setText(currentQuestion.getQuestionText());
            button1.setText(currentQuestion.getOptions()[0]);
            button2.setText(currentQuestion.getOptions()[1]);
            button3.setText(currentQuestion.getOptions()[2]);
            button4.setText(currentQuestion.getOptions()[3]);
        } else {
            isQuizFinished = true;
            Toast.makeText(this, "Викторина завершена! Ваш счет: " + score, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(QuizActivity.this, EndActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);

        }
    }
}