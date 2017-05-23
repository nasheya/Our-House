package hu.ait.ourhouseroommateapp.main_functions;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import hu.ait.ourhouseroommateapp.adapter.BoardNotesAdapter;
import hu.ait.ourhouseroommateapp.adapter.ChoresAdapter;
import hu.ait.ourhouseroommateapp.adapter.ExpenseAdapter;
import hu.ait.ourhouseroommateapp.adapter.HouseRulesAdapter;
import hu.ait.ourhouseroommateapp.adapter.ShoppingListAdapter;
import hu.ait.ourhouseroommateapp.CurrentGroup;
import hu.ait.ourhouseroommateapp.main_data.BoardNote;
import hu.ait.ourhouseroommateapp.main_data.Chore;
import hu.ait.ourhouseroommateapp.main_data.Expense;
import hu.ait.ourhouseroommateapp.main_data.GeneralItem;
import hu.ait.ourhouseroommateapp.main_data.HouseRule;
import hu.ait.ourhouseroommateapp.main_data.ShoppingListItem;

/**
 * Created by nasheyarahman on 5/16/17.
 */

public class MainNetworkFunctionality {

    private DatabaseReference dtb;
    private String userId;
    private String groupId;

    private MainFunctionScreen screen;

    public MainNetworkFunctionality(){
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        groupId = CurrentGroup.getCurrentGroup();
        dtb = FirebaseDatabase.getInstance().getReference().child(groupId);
    }

    public void attachScreen(MainFunctionScreen screen){
        this.screen = screen;
    }

    public void detachScreen(){
        screen = null;
    }

    public DatabaseReference getDatabase(){
        return dtb;
    }

    /*
    All the adding functionality
     */
    public void addChore(Chore chore){
        String key = dtb.child(GeneralItem.CHORES_NODE).push().getKey();
        chore.setKey(key);
        dtb.child(GeneralItem.CHORES_NODE).child(key).setValue(chore);
    }

    public void addExpense(Expense expense){
        String key = dtb.child(GeneralItem.EXPENSES_NODE).push().getKey();
        expense.setKey(key);
        dtb.child(GeneralItem.EXPENSES_NODE).child(key).setValue(expense);
    }

    public void addHouseRule(HouseRule rule){
        String key = dtb.child(GeneralItem.HOUSE_RULES_NODE).push().getKey();
        rule.setKey(key);
        dtb.child(GeneralItem.HOUSE_RULES_NODE).child(key).setValue(rule);
    }

    public void addNote(BoardNote note){
        String key = dtb.child(GeneralItem.BOARD_NODE).push().getKey();
        note.setKey(key);
        dtb.child(GeneralItem.BOARD_NODE).child(key).setValue(note);
    }

    public void addListItem(ShoppingListItem item, boolean shared){
        if(shared){
            String key = dtb.child(GeneralItem.SHOPPING_LIST_NODE).child(GeneralItem.SHOPPING_LIST_GROUP).push().getKey();
            item.setKey(key);
            dtb.child(GeneralItem.SHOPPING_LIST_NODE).child(GeneralItem.SHOPPING_LIST_GROUP).child(key).setValue(item);
        } else {
            String key = dtb.child(GeneralItem.SHOPPING_LIST_NODE).child(userId).push().getKey();
            item.setKey(key);
            dtb.child(GeneralItem.SHOPPING_LIST_NODE).child(userId).child(key).setValue(item);
        }
    }

    /*
    All the retreiving data functionality
     */
    public void getChoresList(ChoresAdapter adapter){

    }

    public void getExpenseList(ExpenseAdapter adapter){

    }

    public void getNotesList(final BoardNotesAdapter adapter){
        dtb.child(GeneralItem.BOARD_NODE).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                BoardNote note = dataSnapshot.getValue(BoardNote.class);
                adapter.addToList(note);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                BoardNote note = dataSnapshot.getValue(BoardNote.class);
                deleteNote(note);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    public void getShoppingList(ShoppingListAdapter adapter, boolean sharedList){

    }

    public void getHouseRules(final HouseRulesAdapter adapter){
        dtb.child(GeneralItem.HOUSE_RULES_NODE).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                HouseRule rule = dataSnapshot.getValue(HouseRule.class);
                adapter.addToList(rule);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                HouseRule rule = dataSnapshot.getValue(HouseRule.class);
                deleteHouseRule(rule);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    /*
    All the deleting functionality
     */
    public void deleteChore(Chore item){

    }

    public void deleteExpense(Expense item){

    }

    public void deleteNote(BoardNote item){
        dtb.child(GeneralItem.BOARD_NODE).child(item.getKey()).removeValue();
    }

    public void deleteShoppingListItem(ShoppingListItem item){

    }

    public void deleteHouseRule(HouseRule item){
        dtb.child(GeneralItem.HOUSE_RULES_NODE).child(item.getKey()).removeValue();
    }


    /*
    Editing functionality
     */
    public void editNote(BoardNote item){
        dtb.child(GeneralItem.BOARD_NODE).child(item.getKey()).setValue(item);
    }

    public void editExpense(Expense item){

    }

    public void editChore(Chore item){

    }

    public void editShoppingListItem(ShoppingListItem item){

    }

    public void editHouseRule(HouseRule item){

    }

}
