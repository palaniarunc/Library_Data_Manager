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
import com.example.login_testt.databinding.FragmentSettingBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        //Check In Code
        binding1.buttonIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String book_Name = binding1.tvBookName1.getText().toString();

                updateData_In(book_Name);
            }
        });




        return binding1.getRoot();
    }

    private void updateData_In(String bookName) {
        HashMap User = new HashMap();
        User.put("check_In_Out", false);
        User.put("child_Name", "");

        reference1 = FirebaseDatabase.getInstance().getReference("Book Name");
        reference1.child(bookName).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if(task.isSuccessful()){

                    binding1.tvBookName1.setText("");
                    // for check out - need to change tvBookName1 to tvBookName2
                    Toast.makeText(getContext(), "Book successfully Checked In", Toast.LENGTH_SHORT).show();
                    // need to change above for the Checked Out


                }
                else{
                    Toast.makeText(getContext(), "Failed to Update",Toast.LENGTH_SHORT ).show();
                }

            }
        });


    }
}