package com.dnd.spellchecker;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.dnd.spellchecker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements DialogAnswerResult {

    private ActivityMainBinding binding;
    private DialogFragment currentDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setLabels();
        setStartUI();
        setContentView(binding.getRoot());
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
}
