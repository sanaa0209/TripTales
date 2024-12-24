package com.unimib.triptales.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.unimib.triptales.R;
import com.unimib.triptales.ui.diario.fragment.Diary;
import java.util.List;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder> {

    private List<Diary> diaryList;

    public DiaryAdapter(List<Diary> diaryList) {
        this.diaryList = diaryList;
    }

    @NonNull
    @Override
    public DiaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diary_card, parent, false);
        return new DiaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryViewHolder holder, int position) {
        Diary diary = diaryList.get(position);
        holder.textViewDiaryName.setText(diary.getName());
        holder.textViewDates.setText(diary.getStartDate() + " - " + diary.getEndDate());
        if (diary.getImage() != null) {
            holder.imageViewDiary.setImageBitmap(diary.getImage());
        }
    }

    @Override
    public int getItemCount() {
        return diaryList.size();
    }

    public static class DiaryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewDiary;
        TextView textViewDiaryName, textViewDates;

        public DiaryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewDiary = itemView.findViewById(R.id.imageViewDiary);
            textViewDiaryName = itemView.findViewById(R.id.textViewDiaryName);
            textViewDates = itemView.findViewById(R.id.textViewDates);
        }
    }

    // Metodo per aggiornare la lista di diari
    public void updateDiaryList(List<Diary> newDiaryList) {
        this.diaryList = newDiaryList;
        notifyDataSetChanged();
    }
}
