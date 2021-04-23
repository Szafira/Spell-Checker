package com.dnd.spellchecker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class AdapterOfAnswers extends RecyclerView.Adapter {

    private final DialogAnswerResult listener;
    private final AnswerType answerType;
    private List<String> list;
    private Context context;

    public AdapterOfAnswers(AnswerType answerType, DialogAnswerResult listener) {
        this.listener = listener;
        this.answerType = answerType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AnswerItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_answer, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AnswerItemViewHolder) {
            ((AnswerItemViewHolder) holder).binding.answersTv.setText(list.get(position));
            ((AnswerItemViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.setResult(answerType, list.get(position));
                    }
                }
            );
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
        switch (answerType) {
            case OPTION_ONE:
                list = Arrays.asList(context.getResources().getStringArray(R.array.answer_list_for_question_one));
                break;
            case OPTION_TWO:
                list = Arrays.asList(context.getResources().getStringArray(R.array.answer_list_for_question_two));
                break;
            case OPTION_THREE:
                list = Arrays.asList(context.getResources().getStringArray(R.array.answer_list_for_question_three));
                break;
        }
    }
}
