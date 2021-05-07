package com.example.agenziaviaggiapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> implements Filterable {

    List<String> stringa = new ArrayList<>();
    List<String> fullString;
    Context context;

    public RecyclerAdapter(Context ct, List<String> s1){
        context = ct;
        stringa = s1;
        fullString = new ArrayList<>(s1);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.listitem, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.stato.setText(stringa.get(position));
    }

    @Override
    public int getItemCount() {
        return stringa.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView stato;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            stato = itemView.findViewById(R.id.textView);
        }
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<String> paesifiltrati = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                paesifiltrati.addAll(stringa);
            } else {
                String filepattern = constraint.toString().toLowerCase().trim();

                for (int i = 0; i < stringa.size(); i++){
                    if (stringa.get(i).toLowerCase().contains(filepattern)){
                        paesifiltrati.add(stringa.get(i));
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = paesifiltrati;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            stringa.clear();
            stringa.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
