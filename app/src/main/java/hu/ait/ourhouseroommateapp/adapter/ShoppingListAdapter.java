package hu.ait.ourhouseroommateapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.ait.ourhouseroommateapp.R;
import hu.ait.ourhouseroommateapp.main_data.ShoppingListItem;
import hu.ait.ourhouseroommateapp.touch.TouchHelperInterface;

/**
 * Created by nasheyarahman on 5/16/17.
 */

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> implements TouchHelperInterface {
    private List<ShoppingListItem> shoppingList;

    public ShoppingListAdapter(){
        shoppingList = new ArrayList<ShoppingListItem>();
        shoppingList.add(new ShoppingListItem("Spinach", "", false, 1));
        shoppingList.add(new ShoppingListItem("Potatoes", "", false, 2));
        shoppingList.add(new ShoppingListItem("Rice", "", false, 3));
        shoppingList.add(new ShoppingListItem("Trash Bags", "", false, 2));
        shoppingList.add(new ShoppingListItem("Water Bottles", "", false, 1));
        shoppingList.add(new ShoppingListItem("Laundry Detergent", "", false, 1));
        shoppingList.add(new ShoppingListItem("Juice", "", false, 2));
        shoppingList.add(new ShoppingListItem("Cups", "", false, 3));
        shoppingList.add(new ShoppingListItem("Cleaning Spray", "", false, 2));
        shoppingList.add(new ShoppingListItem("Chocolate", "", false, 1));
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView title;
        public final CheckBox cbBought;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tvListItem);
            cbBought = (CheckBox) itemView.findViewById(R.id.cbBought);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ShoppingListAdapter.ViewHolder holder, int position) {
        holder.title.setText(shoppingList.get(position).getName());
        holder.cbBought.setChecked(shoppingList.get(position).isBought());

        holder.cbBought.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingList.get(holder.getAdapterPosition()).setBought(holder.cbBought.isChecked());
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //supposed to open dialog box with info
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
    }

    public void onItemDismiss(int position){
        shoppingList.remove(position);

        notifyItemRemoved(position);
    }

    public void addItem(ShoppingListItem item){
        shoppingList.add(0, item);
        notifyItemInserted(0);
    }
}
