package com.dnd.spellchecker;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.dnd.spellchecker.databinding.ActivityMainBinding;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements DialogAnswerResult {

    private ActivityMainBinding binding;
    private DialogFragment currentDialog;
    JSONObject jsonRead = new JSONObject();
    String jsonToString = new String();
    HttpURLConnection urlConnection = null;



    class Async extends AsyncTask<Void, Void, String> {
        public String jsonToString;

        @Override
        protected String doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;

            try {
                URL url = new URL("https://www.dnd5eapi.co/api/spells");
                urlConnection = (HttpURLConnection) url.openConnection();

                int code = urlConnection.getResponseCode();
                if (code != 200) {
                    throw new IOException("Invalid response from server: " + code);
                }

                BufferedReader rd = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream()));
                String line;

                while ((line = rd.readLine()) != null) {
                    jsonRead.put("Spell", line);

                }
                jsonToString = jsonRead.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }

                return jsonToString;
            }

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setLabels();
        setStartUI();
        setContentView(binding.getRoot());
        Button buttonSearch = findViewById(R.id.buttonSearch);


        String BaseUrl = "https://www.dnd5eapi.co/api/";
        String CategoryUrl = getString(R.string.Spells);
        String fullUrl = BaseUrl + CategoryUrl + "/paladin";



    }


    private void setStartUI() {
        setListenerForOptionOne();
        binding.optionTwo.backgroundCl.setBackgroundColor(Color.GRAY);
        binding.optionThree.backgroundCl.setBackgroundColor(Color.GRAY);
    }

    private void setLabels() {
        binding.optionOne.questionTv.setText(getString(R.string.question_label_one));
        binding.optionTwo.questionTv.setText(getString(R.string.question_label_two));
        binding.optionThree.questionTv.setText(getString(R.string.question_label_three));
    }

    private void openDialogWithAnswers(AnswerType answerType) {
        SelectOptionBottomDialogFragment dialog = new SelectOptionBottomDialogFragment();
        dialog.setType(answerType);
        dialog.setListener(this);
        dialog.show(getSupportFragmentManager(), "Dialog");
        currentDialog = dialog;
    }

    private void setListenerForOptionOne() {
        binding.optionOne.backgroundCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogWithAnswers(AnswerType.OPTION_ONE);
            }
        });
    }

    private void setListenersForOptionTwoAndThree() {
        binding.optionTwo.backgroundCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogWithAnswers(AnswerType.OPTION_TWO);
            }
        });
        binding.optionThree.backgroundCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogWithAnswers(AnswerType.OPTION_THREE);

            }
        });
    }

    @Override
    public void setResult(AnswerType type, String answer) {
        switch (type) {
            case OPTION_ONE:
                binding.optionOne.answersTv.setText(answer);
                binding.optionTwo.backgroundCl.setBackgroundColor(Color.WHITE);
                binding.optionThree.backgroundCl.setBackgroundColor(Color.WHITE);
                setListenersForOptionTwoAndThree();
                break;
            case OPTION_TWO:
                binding.optionTwo.answersTv.setText(answer);
                break;
            case OPTION_THREE:
                binding.optionThree.answersTv.setText(answer);
                break;


        }
        currentDialog.dismiss();
    }

    public void search(View view) {
        TextView textView = findViewById(R.id.result_rv);

        Async async = new Async();
        async.execute();

        String results = "Test :v";
        textView.setText(jsonToString);
        System.out.println(jsonToString);
        System.out.println(results);


    }

}


