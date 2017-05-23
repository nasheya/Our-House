package hu.ait.ourhouseroommateapp.main_functions;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import hu.ait.ourhouseroommateapp.R;
import hu.ait.ourhouseroommateapp.adapter.BoardNotesAdapter;
import hu.ait.ourhouseroommateapp.main_data.BoardNote;
import hu.ait.ourhouseroommateapp.main_data.GeneralItem;
import hu.ait.ourhouseroommateapp.touch.TouchHelperCallback;

/**
 * Created by nasheyarahman on 5/14/17.
 */

public class FragmentWhiteboard extends Fragment implements MainFunctionScreen{
    public static final String TAG = "FragmentBoardNotes";

    private BoardNotesAdapter boardNotesAdapter;
    private RecyclerView recyclerNotes;
    private MainNetworkFunctionality network;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_board_notes, container, false);

        network = new MainNetworkFunctionality();
        network.attachScreen(this);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fabNotes);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddNoteDialog();
            }
        });

        recyclerNotes = (RecyclerView) rootView.findViewById(R.id.recyclerNotes);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerNotes.setLayoutManager(layoutManager);

        boardNotesAdapter = new BoardNotesAdapter(network, this.getActivity());
        recyclerNotes.setAdapter(boardNotesAdapter);

        //adding touch support
        ItemTouchHelper.Callback callback = new TouchHelperCallback(boardNotesAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerNotes);

        return rootView;
    }

    @Override
    public void onDestroy() {
        network.detachScreen();
        super.onDestroy();
    }

    private void showAddNoteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());

        builder.setTitle(R.string.add_note_title);

        View view = LayoutInflater.from(this.getActivity()).inflate(R.layout.board_note_add, null, false);
        builder.setView(view);

        final EditText etNoteTitle = (EditText) view.findViewById(R.id.etNoteTitle);
        final EditText etNoteBody = (EditText) view.findViewById(R.id.etNoteBody);

        builder.setPositiveButton(R.string.add_note_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(isFormEmpty(etNoteTitle, etNoteBody) == false){
                    BoardNote boardNote = new BoardNote(etNoteTitle.getText().toString(), etNoteBody.getText().toString());
                    boardNotesAdapter.addItem(boardNote);
                    recyclerNotes.smoothScrollToPosition(0);
                }
            }
        });

        builder.setNegativeButton(R.string.cancel_dialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }



    private boolean isFormEmpty(EditText etNoteTitle, EditText etNoteBody){
        if(TextUtils.isEmpty(etNoteTitle.getText()) && TextUtils.isEmpty(etNoteBody.getText())){
            etNoteTitle.requestFocus();
            etNoteTitle.setError(getString(R.string.error_field_required));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void postFailure() {
        Toast.makeText(this.getActivity(), R.string.error_general, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void postSuccess() {}
}
