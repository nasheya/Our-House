package hu.ait.ourhouseroommateapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hu.ait.ourhouseroommateapp.main_data.BoardNote;

/**
 * Created by nasheyarahman on 5/16/17.
 */

public class BoardNotesAdapter extends RecyclerView.Adapter<BoardNotesAdapter.ViewHolder> {
    private List<BoardNote> boardNoteList;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public BoardNotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BoardNotesAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return boardNoteList.size();
    }
}
