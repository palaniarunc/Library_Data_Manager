package com.example.login_testt;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.login_testt.databinding.ActivityMainBinding;
import com.example.login_testt.databinding.FragmentHomeBinding;
import com.example.login_testt.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

// PROFITE FRAGMENT IS KNOWN AS GET DATA IN APP
public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding1;
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

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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

        binding1 = FragmentProfileBinding.inflate(inflater, container, false);
        // make sure to have this as well

        binding1.button.setOnClickListener(new View.OnClickListener() {
            // the button on binding1. button - the "button" is the ID of the button
            @Override
            public void onClick(View v) {
                String book_name = binding1.etusername.getText().toString();
                if(!book_name.isEmpty()){

                    readData(book_name);


                }
                else{
                    Toast.makeText(getContext(),"Please Enter Valid Book_Name",Toast.LENGTH_SHORT).show();
                }




            } // end of OnClick


        });


        return binding1.getRoot();
        // make sure to have the above
    } // end of oncreate view

    private void readData(String bookName) {

        reference1 = FirebaseDatabase.getInstance().getReference("Book Name");
        reference1.child(bookName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                // need to see if null, print out N/A if child is null and book is Check Out, Yes(True) or No(False)

                binding1.tvBookAuthor.setText("");
                binding1.tvStudentName.setText("");
                binding1.tvCheckInOut.setText("");

                if(task.isSuccessful()){

                        if(task.getResult().exists()){

                            Toast.makeText(getContext(), "Successfully Read", Toast.LENGTH_SHORT).show();

                            DataSnapshot dataSnapshot = task.getResult();
                            String AuthorName = String.valueOf(dataSnapshot.child("author_Name").getValue());
                            String ChildName = String.valueOf(dataSnapshot.child("child_Name").getValue());
                            boolean Check_In_Out = Boolean.valueOf(String.valueOf(dataSnapshot.child("check_In_Out").getValue()));

                            // delete above code after
                            binding1.tvBookAuthor.setText(AuthorName);
                            if(ChildName.isEmpty()){
                                binding1.tvStudentName.setText("N/A");

                            }
                            else{
                                binding1.tvStudentName.setText(ChildName);
                            }



                            if(Check_In_Out == false){
                                binding1.tvCheckInOut.setText("No");


                            }
                            else{
                                binding1.tvCheckInOut.setText("Yes");

                            }





                        }
                        else{
                            Toast.makeText(getContext(), "Book Name does not exist", Toast.LENGTH_SHORT).show();
                        }

                }
                else{
                    Toast.makeText(getContext(), "Toast failed to retrive data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}