package hu.ait.ourhouseroommateapp.groups;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

import hu.ait.ourhouseroommateapp.R;
import hu.ait.ourhouseroommateapp.adapter.ExistingGroupAdapter;
import hu.ait.ourhouseroommateapp.ui.Presenter;

/**
 * Created by nasheyarahman on 5/13/17.
 */

public class GroupPresenter extends Presenter<GroupScreen>{
    private DatabaseReference dtb;

    private String userId;

    public GroupPresenter(){
        dtb = FirebaseDatabase.getInstance().getReference();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public void addGroupToFirebase(String groupID, String groupName, String groupCode){
        transactionGroupCreate(groupID, groupName, groupCode);
    }

    public void joinGroup(String groupID, String groupCode){
        transactionGroupJoin(groupID, groupCode);
    }

    private void joinFirebaseGroup(String groupID){
        dtb.child(GroupData.USERS_NODE).child(userId).child(GroupData.USERS_CURRENT_GROUP_NODE).setValue(groupID);
        dtb.child(GroupData.USERS_NODE).child(userId).child(GroupData.USER_GROUPS_NODE).child(groupID).setValue(true);

        dtb.child(GroupData.GROUPS_NODE).child(groupID).child(GroupData.GROUP_USERS_NODE).child(userId).setValue(true);
    }

    private void addNodeToFirebase(String groupID, String groupName, String groupCode){
        dtb.child(GroupData.USERS_NODE).child(userId).child(GroupData.USERS_CURRENT_GROUP_NODE).setValue(groupID);
        dtb.child(GroupData.USERS_NODE).child(userId).child(GroupData.USER_GROUPS_NODE).child(groupID).setValue(true);

        dtb.child(GroupData.GROUPS_NODE).child(groupID).child(GroupData.GROUP_NAME_NODE).setValue(groupName);
        dtb.child(GroupData.GROUPS_NODE).child(groupID).child(GroupData.GROUP_CODE_NODE).setValue(groupCode);
        dtb.child(GroupData.GROUPS_NODE).child(groupID).child(GroupData.GROUP_USERS_NODE).child(userId).setValue(true);
    }

    private void transactionGroupJoin(final String groupID, final String groupCode){
        dtb.child(GroupData.GROUPS_NODE).child(groupID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String code = (String) snapshot.child(GroupData.GROUP_CODE_NODE).getValue();

                    if(code.equals(groupCode)){
                        joinFirebaseGroup(groupID);
                        screen.navigateToMainScreen();
                    } else {
                        screen.highlightUniquenessProblem();
                    }
                } else {
                    screen.highlightUniquenessProblem();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                screen.postFailure();
            }
        });
    }

    private void transactionGroupCreate(final String groupID, final String groupName, final String groupCode) {
        dtb.child(GroupData.GROUPS_NODE).child(groupID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    screen.highlightUniquenessProblem();
                } else {
                    addNodeToFirebase(groupID, groupName, groupCode);
                    screen.navigateToMainScreen();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                screen.postFailure();
            }
        });
    }

    public void getUserGroups(final ExistingGroupAdapter adapter){


        dtb.child(GroupData.USERS_NODE).child(userId).child(GroupData.USER_GROUPS_NODE).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    List<String> groupsList = new ArrayList<String>();

                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        String tempItem = postSnapshot.getKey();
                        groupsList.add(tempItem);
                    }

                    adapter.setExistingGroups(groupsList);
                } else {
                    screen.postFailure();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                screen.postFailure();
            }
        });
    }

    public void setAsCurrentUserGroup(String group){
        dtb.child(GroupData.USERS_NODE).child(userId).child(GroupData.USERS_CURRENT_GROUP_NODE).setValue(group);

        screen.navigateToMainScreen();
    }
}
