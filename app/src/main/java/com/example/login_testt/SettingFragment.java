package com.example.login_testt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.login_testt.databinding.ActivityMainBinding;
import com.example.login_testt.databinding.FragmentHomeBinding;
import com.example.login_testt.databinding.FragmentProfileBinding;
import com.example.login_testt.databinding.FragmentSettingBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


//SETTING FRAGMENT IS KNOWN AS CHECKIN/OUT IN APP
public class SettingFragment extends Fragment {



    FragmentSettingBinding binding1;
    // need to change every time
    // you import as well
    FirebaseDatabase db1;
    DatabaseReference reference1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String tree1 = "";
    String tree2 = "";

    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding1 = FragmentSettingBinding.inflate(inflater, container, false);

        binding1.RadioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == binding1.radioButler.getId()){
                    tree1 = "Mrs_Butler";
                }
                else if(checkedId == binding1.radioPriya.getId()){
                    tree1 = "Mrs_Priya";
                }
            }
        });
        binding1.RadioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == binding1.radioButler2.getId()){
                    tree2 = "Mrs_Butler";
                }
                if(checkedId == binding1.radioPriya2.getId()){
                    tree2 = "Mrs_Priya";
                }
            }
        });


        //Check In Code
        binding1.buttonIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String book_Name = binding1.tvBookName1.getText().toString();


                if(!book_Name.isEmpty()) {

                    updateData_In(book_Name,tree1);
                }
                else{
                    Toast.makeText(getContext(), "Please enter a book name" , Toast.LENGTH_SHORT).show();
                }
                if(binding1.RadioGroup3.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getContext(), "Please select a teacher", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        binding1.buttonOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String book_Name = binding1.tvBookName2.getText().toString();
                String child_Name = binding1.tvStudentName.getText().toString();



                if((!book_Name.isEmpty()) && (!child_Name.isEmpty())) {
                    updateData_Out(book_Name, child_Name,tree2);
                }
                else{
                    if((book_Name.isEmpty()) && (child_Name.isEmpty())){
                        Toast.makeText(getContext(), "Please enter a Book name and Student Name" , Toast.LENGTH_SHORT).show();
                    }
                    else if(book_Name.isEmpty()){
                        Toast.makeText(getContext(), "Please enter a Book name" , Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getContext(), "Please enter a Student name", Toast.LENGTH_SHORT).show();
                    }
                }
                if(binding1.RadioGroup4.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getContext(), "Please select a teacher", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });


        return binding1.getRoot();
    }

    private void updateData_Out(String bookName, String childName,String tree3) {



        reference1 = FirebaseDatabase.getInstance().getReference(tree3);

        reference1.child(bookName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    HashMap User = new HashMap();
                    User.put("check_In_Out", true);
                    User.put("child_Name", childName);

                    reference1.child(bookName).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {

                            if(task.isSuccessful()){

                                binding1.tvBookName2.setText("");
                                binding1.tvStudentName.setText("");
                                binding1.RadioGroup4.clearCheck();
                                tree2 = "";
                                // for check out - need to change tvBookName1 to tvBookName2
                                Toast.makeText(getContext(), "Book successfully Checked Out", Toast.LENGTH_SHORT).show();
                                // need to change above for the Checked Out


                            }
                            else{
                                Toast.makeText(getContext(), "Failed to Update",Toast.LENGTH_SHORT ).show();
                            }

                        } // end of onComplete Method
                    }); // end of reference 1 - addOncomplete Lister- Update CHilderen



                }// end of if statement
                else{
                    Toast.makeText(getContext(),"Book does not exist", Toast.LENGTH_SHORT).show();
                }
            } // end of onData Change

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Database Error",Toast.LENGTH_SHORT).show();
            }
        }); // end of addListerFor single Value event





    }


    private void updateData_In(String bookName,String tree4) {


        reference1 = FirebaseDatabase.getInstance().getReference(tree4);

        reference1.child(bookName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    HashMap User = new HashMap();
                    User.put("check_In_Out", false);
                    User.put("child_Name", "");

                    reference1.child(bookName).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {

                            if(task.isSuccessful()){

                                binding1.tvBookName1.setText("");
                                binding1.RadioGroup3.clearCheck();
                                tree1 = " ";
                                // for check out - need to change tvBookName1 to tvBookName2
                                Toast.makeText(getContext(), "Book successfully Checked In", Toast.LENGTH_SHORT).show();
                                // need to change above for the Checked Out


                            }
                            else{
                                Toast.makeText(getContext(), "Failed to Update",Toast.LENGTH_SHORT ).show();
                            }

                        } // end of onComplete Method
                    }); // end of reference 1 - addOncomplete Lister- Update CHilderen



                }// end of if statement
                else{
                    Toast.makeText(getContext(),"Book does not exist", Toast.LENGTH_SHORT).show();
                }
            } // end of onData Change

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Database Error",Toast.LENGTH_SHORT).show();
            }
        }); // end of addListerFor single Value event




    } // end of UpdateData_In Method









}