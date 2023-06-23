package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myproject.dao.FlagDao;
import com.example.myproject.database.FlagDatabase;
import com.example.myproject.entity.Flag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Playing extends AppCompatActivity {
    TextView question, correctAnswer;
    ImageView imageCountry;
    Button btnA, btnB, btnC, btnD;

    ImageView btnNext;

    List<Flag> flags;
    HashSet<String> mixOptions = new HashSet<>();
    List<String> options = new ArrayList<>();

    Flag flag;

    FlagDao flagDao;

    int number_question = 1;
    int correct_question = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        correctAnswer = findViewById(R.id.correctAnswer);
        question = findViewById(R.id.question);
        imageCountry = findViewById(R.id.imageCountry);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
        btnNext = findViewById(R.id.btnNext);

        //Khởi tạo 10 câu hỏi
        flagDao = FlagDatabase.getInstance(getApplicationContext()).flagDao();
        flags = flagDao.getTenQuestion();
        loadQuestion();

        //Xử lý next
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number_question++;
                loadQuestion();
            }
        });

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unclickable();
                if (checkCorrectAnswer(btnA.getText().toString())) {
                    correct_question++;
                    updateScore();
                    btnA.setBackgroundColor(Color.GREEN);
                } else {
                    btnA.setBackgroundColor(Color.RED);
                    if (checkCorrectAnswer(btnB.getText().toString())) {
                        btnB.setBackgroundColor(Color.GREEN);
                    } else if (checkCorrectAnswer(btnC.getText().toString())) {
                        btnC.setBackgroundColor(Color.GREEN);
                    } else {
                        btnD.setBackgroundColor(Color.GREEN);
                    }
                }
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unclickable();
                if (checkCorrectAnswer(btnB.getText().toString())) {
                    correct_question++;
                    updateScore();
                    btnB.setBackgroundColor(Color.GREEN);
                } else {
                    btnB.setBackgroundColor(Color.RED);
                    if (checkCorrectAnswer(btnA.getText().toString())) {
                        btnA.setBackgroundColor(Color.GREEN);
                    } else if (checkCorrectAnswer(btnC.getText().toString())) {
                        btnC.setBackgroundColor(Color.GREEN);
                    } else {
                        btnD.setBackgroundColor(Color.GREEN);
                    }
                }
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unclickable();
                if (checkCorrectAnswer(btnC.getText().toString())) {
                    correct_question++;
                    updateScore();
                    btnC.setBackgroundColor(Color.GREEN);
                } else {
                    btnC.setBackgroundColor(Color.RED);
                    if (checkCorrectAnswer(btnB.getText().toString())) {
                        btnB.setBackgroundColor(Color.GREEN);
                    } else if (checkCorrectAnswer(btnA.getText().toString())) {
                        btnA.setBackgroundColor(Color.GREEN);
                    } else {
                        btnD.setBackgroundColor(Color.GREEN);
                    }
                }
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unclickable();
                if (checkCorrectAnswer(btnD.getText().toString())) {
                    correct_question++;
                    updateScore();
                    btnD.setBackgroundColor(Color.GREEN);
                } else {
                    btnD.setBackgroundColor(Color.RED);
                    if (checkCorrectAnswer(btnB.getText().toString())) {
                        btnB.setBackgroundColor(Color.GREEN);
                    } else if (checkCorrectAnswer(btnC.getText().toString())) {
                        btnC.setBackgroundColor(Color.GREEN);
                    } else {
                        btnA.setBackgroundColor(Color.GREEN);
                    }
                }
            }
        });
    }

    private void updateScore() {
        correctAnswer.setText("Đáp án đúng: " + correct_question);
    }

    private void loadQuestion() {
        if (number_question == 11) {
            Intent intent = new Intent(this, KetQua.class);
            intent.putExtra("correct", correct_question);
            startActivity(intent);
            return ;
        }
        clickable();

        //Reset button color
        btnA.setBackgroundColor(Color.BLUE);
        btnB.setBackgroundColor(Color.BLUE);
        btnC.setBackgroundColor(Color.BLUE);
        btnD.setBackgroundColor(Color.BLUE);

        //Put data into components
        correctAnswer.setText("Đáp án đúng: " + correct_question);
        question.setText("Câu hỏi " + number_question);
        flag = flags.get(number_question-1);
        imageCountry.setImageBitmap(BitmapFactory.decodeByteArray(flag.getImage(), 0, flag.getImage().length));

        //Lấy 3 đáp án radom
        List<Flag> wrongName = flagDao.getThreeWrongAnswer(flag.getId());

        //Trộn 3 đáp án random + đáp án đúng để làm câu trả lời
        mixOptions.clear();
        mixOptions.add(flag.getCountryName());
        mixOptions.add(wrongName.get(0).getCountryName());
        mixOptions.add(wrongName.get(1).getCountryName());
        mixOptions.add(wrongName.get(2).getCountryName());

        options.clear();
        for (String name : mixOptions) {
            options.add(name);
        }
        btnA.setText(options.get(0));
        btnB.setText(options.get(1));
        btnC.setText(options.get(2));
        btnD.setText(options.get(3));
    }

    private boolean checkCorrectAnswer(String answer) {
        if (flag.getCountryName().equals(answer)) return true;
        return false;
    }

    private void unclickable() {
        btnA.setClickable(false);
        btnB.setClickable(false);
        btnC.setClickable(false);
        btnD.setClickable(false);
    }

    private void clickable() {
        btnA.setClickable(true);
        btnB.setClickable(true);
        btnC.setClickable(true);
        btnD.setClickable(true);
    }



}