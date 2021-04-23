package com.dnd.spellchecker;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dnd.spellchecker.databinding.ItemAnswerBinding;

public class AnswerItemViewHolder extends RecyclerView.ViewHolder {
    public ItemAnswerBinding binding;

    public AnswerItemViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = ItemAnswerBinding.bind(itemView);
    }


}
