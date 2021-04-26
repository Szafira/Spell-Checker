package com.dnd.spellchecker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dnd.spellchecker.databinding.AnswersBottomDialogFragmentBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class SelectOptionBottomDialogFragment extends BottomSheetDialogFragment {
    private AnswerType answerType;
    private DialogAnswerResult listener;
    private AnswersBottomDialogFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AnswersBottomDialogFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.typeTv.setText(getQuestionByType(answerType));
        binding.answersTv.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false));
        binding.answersTv.setAdapter(new AdapterOfAnswers(answerType, listener));
    }

    public String getQuestionByType(AnswerType type){
        String textToReturn = "";
        switch (type){
            case OPTION_ONE:
                textToReturn = getString(R.string.question_label_one);
                break;
            case OPTION_TWO:
                textToReturn = getString(R.string.question_label_two);
                break;
            case OPTION_THREE:
                textToReturn = getString(R.string.question_label_three);
                break;
        }
        return textToReturn;
    }

    public void setType(AnswerType answerType) {
        this.answerType = answerType;
    }

    public void setListener(DialogAnswerResult listener) {
        this.listener = listener;
    }
}
