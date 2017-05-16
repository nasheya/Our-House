package hu.ait.ourhouseroommateapp.main_functions;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import hu.ait.ourhouseroommateapp.adapter.BoardNotesAdapter;
import hu.ait.ourhouseroommateapp.adapter.ChoresAdapter;
import hu.ait.ourhouseroommateapp.adapter.ExpenseAdapter;
import hu.ait.ourhouseroommateapp.adapter.HouseRulesAdapter;
import hu.ait.ourhouseroommateapp.adapter.ShoppingListAdapter;
import hu.ait.ourhouseroommateapp.groups.GroupData;
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

    DatabaseReference dtb;
    private String userId;
    private String groupId;

    public MainNetworkFunctionality(){
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dtb = FirebaseDatabase.getInstance().getReference();
        getGroupId();
        dtb = dtb.child(groupId);
    }

    private void getGroupId() {
        Query query = dtb.child(GroupData.USERS_NODE).child(userId).child(GroupData.USERS_CURRENT_GROUP_NODE);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String name = dataSnapshot.getValue(String.class);
                    setGroupId(name);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    private void setGroupId(String name) {
        groupId = name;
    }

    /*
    All the adding functionality
     */
    public void addChore(Chore chore){
        String key = dtb.child(GeneralItem.CHORES_NODE).child(chore.getCategory()).push().getKey();
        chore.setKey(key);
        dtb.child(GeneralItem.CHORES_NODE).child(chore.getCategory()).child(key).setValue(chore);
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

    public void getNotesList(BoardNotesAdapter adapter){

    }

    public void getShoppingList(ShoppingListAdapter adapter, boolean sharedList){

    }

    public void getHouseRules(HouseRulesAdapter adapter){

    }

    /*
    All the deleting functionality
     */
    public void deleteChore(Chore item){

    }

    public void deleteExpense(Expense item){

    }

    public void deleteNote(BoardNote item){

    }

    public void deleteShoppingListItem(ShoppingListItem item){

    }

    public void deleteHouseRule(HouseRule item){

    }

}
