package hu.ait.ourhouseroommateapp.groups;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import hu.ait.ourhouseroommateapp.R;
import hu.ait.ourhouseroommateapp.ui.Presenter;

/**
 * Created by nasheyarahman on 5/13/17.
 */

public class GroupPresenter extends Presenter<GroupScreen>{
    DatabaseReference dtb;

    public GroupPresenter(){
        dtb = FirebaseDatabase.getInstance().getReference();
    }

    public void addGroupToFirebase(String groupID, String groupName, String groupCode){
        transactionGroupCreate(groupID, groupName, groupCode);
    }

    public void joinGroup(String groupID){

    }

    private void joinFirebaseGroup(String groupID){
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dtb.child(GroupData.USERS_NODE).child(userId).child(GroupData.USERS_CURRENT_GROUP_NODE).setValue(groupID);
        dtb.child(GroupData.USERS_NODE).child(userId).child(GroupData.USER_GROUPS_NODE).child(groupID).setValue(true);

        dtb.child(GroupData.GROUPS_NODE).child(groupID).child(GroupData.GROUP_USERS_NODE).child(userId).setValue(true);
    }

    private void addNodeToFirebase(String groupID, String groupName, String groupCode){
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dtb.child(GroupData.USERS_NODE).child(userId).child(GroupData.USERS_CURRENT_GROUP_NODE).setValue(groupID);
        dtb.child(GroupData.USERS_NODE).child(userId).child(GroupData.USER_GROUPS_NODE).child(groupID).setValue(true);

        dtb.child(GroupData.GROUPS_NODE).child(groupID).child(GroupData.GROUP_NAME_NODE).setValue(groupName);
        dtb.child(GroupData.GROUPS_NODE).child(groupID).child(GroupData.GROUP_CODE_NODE).setValue(groupCode);
        dtb.child(GroupData.GROUPS_NODE).child(groupID).child(GroupData.GROUP_USERS_NODE).child(userId).setValue(true);
    }

    private void transactionGroupJoin(){
        //        //Ugly ass nesting, but this is to check the code and group ID match up correctly
        if(cancel == false){
            Log.d("TAG", dtb.child(GroupData.GROUPS_NODE).toString());
            dtb.child(GroupData.GROUPS_NODE).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {

                    Log.d("TAG", snapshot.toString() +" FIRSTTTTT");
                    //Log.d("TAG", snapshot.hasChild(groupID)+"");
                    if (snapshot.hasChild(groupID)) {
                        Log.d("TAG", snapshot.toString());
                        Log.d("TAG", "I am here!!");
                        //group name exists, let's check the group code now
                        dtb.child(GroupData.GROUPS_NODE).child(groupID).child(GroupData.GROUP_CODE_NODE)
                                .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                String code = snapshot.getValue().toString();

                                if (!groupCode.equals(code)) {

                                } else {
                                    etID.setError(null);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                System.out.println(getString(R.string.error_heading) + databaseError.toString());
                            }
                        });

                    } else {

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
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
            public void onCancelled(DatabaseError databaseError) {}
        });

//        dtb.child(GroupData.GROUPS_NODE).child(groupID).runTransaction(new Transaction.Handler() {
//            @Override
//            public Transaction.Result doTransaction(MutableData mutableData) {
//                if (mutableData.getValue() == null) {
//                    addNodeToFirebase(groupID, groupName, groupCode);
//                    return Transaction.success(mutableData);
//                }
//
//                return Transaction.abort();
//            }
//
//            @Override
//            public void onComplete(DatabaseError firebaseError, boolean commited, DataSnapshot dataSnapshot) {
//                if (commited) {
//                    screen.navigateToMainScreen();
//                } else {
//                    screen.highlightUniquenessProblem();
//                }
//            }
//        });
    }
}
