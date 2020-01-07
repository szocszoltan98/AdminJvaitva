package com.example.admin.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.admin.Database.FirebaseDataHelper;
import com.example.admin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class signFragment extends Fragment {
   private EditText editText_groupId;
   private Button btn_openId;
   private Button btn_newGroup;
   private boolean conSession;
   private FirebaseDatabase database = FirebaseDatabase.getInstance();
   private DatabaseReference adminReference = database.getReference().child("Groups");
   private String id;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View v;
       v =inflater.inflate(R.layout.fragment_sign, container, false);
        editText_groupId = v.findViewById(R.id.groupId);
        btn_openId = v.findViewById(R.id.open);
        btn_newGroup = v.findViewById(R.id.newfragment);
        //uj csoport letrehozasa ,mar letezo ellenorzese
        btn_newGroup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                id = editText_groupId.getText().toString();
                if(id.isEmpty()){
                    editText_groupId.setError("Please enter a group id");
                }
                else {
                    editText_groupId.setError(null);
                adminReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot item: dataSnapshot.getChildren()){
                            if(item.child("groupId").getValue().equals(id)) {
                                editText_groupId.setError("Already exists");

                            }
                            else {
                                String key2 = FirebaseDataHelper.Instance.CreateNewGroup(id);
                                if (key2.equals("Invalid")) {
                                    Toast.makeText(getActivity(),"Failed!",Toast.LENGTH_SHORT).show();

                                }
                                else {
                                    Toast.makeText(getActivity(),id + " Successfully created",Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            } }
        });

        // csoporthoz valo belepes
       btn_openId.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String id = editText_groupId.getText().toString();
               connectSession(id);
           }
       });

        return v;
    }
    private void connectSession(final String id) {

        conSession = false;
        //itt keresi meg az adatbazisban ha letezik a csoport ha igen belep azaz uj oldalra visz
        //ha nem errort kiir
        adminReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                  conSession = false;
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    if (item.child("groupId").getValue().equals(id)) {
                        conSession = true;
                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                        Fragment f = new FragmentShow();
                        fr.addToBackStack(null);
                        fr.replace(R.id.fragment_container,f);
                        Bundle args = new Bundle();
                        args.putString("groupId", editText_groupId.getText().toString());
                        f.setArguments(args);
                        fr.commit();
                        break;
                    }
                }
                if (!conSession )
                {
                    editText_groupId.setError("Does not exist.");

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        }); }





}
