package hu.ait.ourhouseroommateapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import hu.ait.ourhouseroommateapp.MainApplication;
import hu.ait.ourhouseroommateapp.groups.GroupData;

/**
 * Created by nasheyarahman on 5/22/17.
 */

public class CurrentGroup {
    public static final String OUR_HOUSE = "OUR_HOUSE";
    public static final String GROUP_NAME = "GROUP_NAME";

    private static SharedPreferences sp = MainApplication.getAppContext().getSharedPreferences(OUR_HOUSE, Context.MODE_PRIVATE);

    public static void addGroupToSP(String groupID) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(GROUP_NAME, groupID);
        editor.apply();
    }

    public static String getCurrentGroup(){
        String group = sp.getString(GROUP_NAME, null);
        return group;
    }

    public static void clearCurrentGroup(){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(GROUP_NAME, null);
        editor.apply();
    }

    public static void retrieveGroupId() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference dtb = FirebaseDatabase.getInstance().getReference();

        Query query = dtb.child(GroupData.USERS_NODE).child(userId).child(GroupData.USERS_CURRENT_GROUP_NODE);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String name = dataSnapshot.getValue(String.class);
                    addGroupToSP(name);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    public static void retrieveGroupId(final Intent after){
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference dtb = FirebaseDatabase.getInstance().getReference();

        Query query = dtb.child(GroupData.USERS_NODE).child(userId).child(GroupData.USERS_CURRENT_GROUP_NODE);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String name = dataSnapshot.getValue(String.class);
                    addGroupToSP(name);
                    MainApplication.getAppContext().startActivity(after);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }
}
