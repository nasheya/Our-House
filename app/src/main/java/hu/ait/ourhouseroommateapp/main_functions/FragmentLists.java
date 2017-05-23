package hu.ait.ourhouseroommateapp.main_functions;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import hu.ait.ourhouseroommateapp.R;
import hu.ait.ourhouseroommateapp.adapter.ShoppingListAdapter;
import hu.ait.ourhouseroommateapp.main_data.BoardNote;
import hu.ait.ourhouseroommateapp.main_data.ShoppingListItem;
import hu.ait.ourhouseroommateapp.touch.TouchHelperCallback;

import static hu.ait.ourhouseroommateapp.R.id.recyclerNotes;

/**
 * Created by nasheyarahman on 5/14/17.
 */

public class FragmentLists extends Fragment {
    public static final String TAG = "FragmentShoppingList";

    private ShoppingListAdapter shoppingListAdapter;
    private RecyclerView recyclerShoppingList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fabShoppingList);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showAddTodoDialog();
            }
        });

        recyclerShoppingList = (RecyclerView) rootView.findViewById(R.id.recyclerShoppingList);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerShoppingList.setLayoutManager(layoutManager);

        shoppingListAdapter = new ShoppingListAdapter();
        recyclerShoppingList.setAdapter(shoppingListAdapter);

        //adding touch support
        ItemTouchHelper.Callback callback = new TouchHelperCallback(shoppingListAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerShoppingList);

        return rootView;
    }

//    private void showAddNoteDialog() {
//        final AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
//
//        builder.setTitle(R.string.add_note_title);
//
//        View view = LayoutInflater.from(this.getActivity()).inflate(R.layout.board_note_add, null);
//        builder.setView(view);
//
//        final EditText etNoteTitle = (EditText) view.findViewById(R.id.etNoteTitle);
//        final EditText etNoteBody = (EditText) view.findViewById(R.id.etNoteBody);
//
//        builder.setPositiveButton(R.string.add_item_okay, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if(isFormEmpty(etNoteTitle, etNoteBody) == false){
//                    ShoppingListItem shoppingListItem = new ShoppingListItem(etNoteTitle.getText().toString(), etNoteBody.getText().toString());
//                    boardNotesAdapter.addItem(boardNote);
//                    recyclerNotes.scrollToPosition(boardNotesAdapter.getItemCount()-1);
//                }
//            }
//        });
//
//        builder.setNegativeButton(R.string.cancel_dialog, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//
//        builder.show();
//    }
}
