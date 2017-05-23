package hu.ait.ourhouseroommateapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.ait.ourhouseroommateapp.R;
import hu.ait.ourhouseroommateapp.main_data.BoardNote;
import hu.ait.ourhouseroommateapp.main_functions.FragmentWhiteboard;
import hu.ait.ourhouseroommateapp.main_functions.MainNetworkFunctionality;
import hu.ait.ourhouseroommateapp.touch.TouchHelperInterface;
import hu.ait.ourhouseroommateapp.ui.MainActivity;

/**
 * Created by nasheyarahman on 5/16/17.
 */

public class BoardNotesAdapter extends RecyclerView.Adapter<BoardNotesAdapter.ViewHolder> implements TouchHelperInterface {
    private List<BoardNote> boardNoteList;
    private MainNetworkFunctionality network;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView body;
        public ImageView edit;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tvNoteTitle);
            body = (TextView) itemView.findViewById(R.id.tvNoteBody);
            edit = (ImageView) itemView.findViewById(R.id.editBoardNote);
        }

        public void setTitle(String title) {
            this.title.setText(title);
        }

        public void setBody(String body) {
            this.body.setText(body);
        }
    }

    public BoardNotesAdapter(MainNetworkFunctionality network, Context context){
        this.network = network;
        boardNoteList = new ArrayList<>();
        network.getNotesList(this);
        this.context = context;
    }

    @Override
    public BoardNotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BoardNotesAdapter.ViewHolder holder, int position) {
        holder.title.setText(boardNoteList.get(position).getTitle());
        holder.body.setText(boardNoteList.get(position).getBody());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showEditDialog();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //supposed to open dialog box with info and maybe delete button
            }
        });
    }

    @Override
    public int getItemCount() {
        return boardNoteList.size();
    }

    public void onItemDismiss(int position){
        BoardNote temp = boardNoteList.remove(position);
        network.deleteNote(temp);
        notifyItemRemoved(position);
    }

    public void addItem(BoardNote item){
        network.addNote(item);
    }

    public void addToList(BoardNote item){
        boardNoteList.add(0, item);
        notifyItemInserted(0);
    }

    public void editItem(int position, BoardNote note){
        network.editNote(note);
        boardNoteList.set(position, note);
        notifyDataSetChanged();
    }
}
