package com.example.simpletodo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{
    public interface OnClickListener {
        void onItemClicked(int position);
    }

    public interface OnLongClickListener {
        //pass position of where the long press is
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;
    OnClickListener clickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener clickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create new view. use layout inflator
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        //wrap in a view holder and return it
        return new ViewHolder(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //bind data to particular viewholder

        //grab item at the position
        String item = items.get(position);

        //bind the item to the specified view holder
        holder.bind(item);
    }

    //tells RV how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    //container to provide easy access that represents each row of list
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        public void bind(String item) {
            //update the view inside the view holder with this data
            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                  @Override
                  public boolean onLongClick(View v) {
                      //notify listener which position was long pressed
                      longClickListener.onItemLongClicked(getAdapterPosition());

                      return true; //consuming long click
                  }
              }
            );
        }
    }

}
