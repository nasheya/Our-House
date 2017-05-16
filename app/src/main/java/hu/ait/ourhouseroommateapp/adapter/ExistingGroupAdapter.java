package hu.ait.ourhouseroommateapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

import hu.ait.ourhouseroommateapp.R;
import hu.ait.ourhouseroommateapp.groups.GroupPresenter;
import hu.ait.ourhouseroommateapp.login.LoginPresenter;

/**
 * Created by nasheyarahman on 5/16/17.
 */

public class ExistingGroupAdapter extends RecyclerView.Adapter<ExistingGroupAdapter.ViewHolder> {
    private List<String> existingGroups;

    private Context context;

    private GroupPresenter groupPresenter;

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvExistingGroup;

        public ViewHolder(View itemView) {
            super(itemView);

            tvExistingGroup = (TextView) itemView.findViewById(R.id.tvExistingGroup);
        }
    }

    public ExistingGroupAdapter(Context context, GroupPresenter groupPresenter){
        this.context = context;

        this.groupPresenter = groupPresenter;

        existingGroups = new ArrayList<String>();

        groupPresenter.getUserGroups(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.existing_groups_item, parent, false);

        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvExistingGroup.setText(existingGroups.get(holder.getAdapterPosition()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupPresenter.setAsCurrentUserGroup(existingGroups.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return existingGroups.size();
    }

    public void setExistingGroups(List<String> existingGroups) {
        this.existingGroups = existingGroups;
        notifyDataSetChanged();
    }
}
