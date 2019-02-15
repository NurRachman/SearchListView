package com.example.searchlistview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsHolder> implements Filterable {
    Context context;
    List<String> datafilter, data;

    public ItemsAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
        datafilter = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemsAdapter.ItemsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.items, viewGroup, false);
        return new ItemsHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ItemsHolder itemsHolder, int i) {
        String dataStings = datafilter.get(i);
        itemsHolder.textItems.setText(dataStings);
    }

    @Override
    public int getItemCount() {
        return datafilter.size();
    }

    class ItemsHolder extends RecyclerView.ViewHolder{
        TextView textItems;
        ItemsHolder(@NonNull View itemView) {
            super(itemView);
            textItems = itemView.findViewById(R.id.textItems);
        }
    }
    private void add(String string){
        data.add(string);
        datafilter.add(string);
        notifyItemInserted(datafilter.size() - 1);
    }
    public void addListItems(List<String> listData){
        for (String result : listData){
            add(result);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    datafilter = data;
                } else {
                    List<String> filteredList = new ArrayList<>();
                    for (String row : data) {
                        // Check Query Dari input
                        if (row.toLowerCase().contains(charString.toLowerCase()) || row.contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    datafilter = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = datafilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                datafilter = (ArrayList<String>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }

}
