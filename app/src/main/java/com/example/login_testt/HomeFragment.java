

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
// make sure to import this when trying to get data for other fragments.
// Even if you are not, make sure to import the firebase



//HOMEFRAGMENT IS KNOWN IN APP AS ADD Book

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    FragmentHomeBinding binding1;
    String editBookName;
    String editAuthorName;
    FirebaseDatabase db1;
    DatabaseReference reference1;

// make sure to have this
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding1 = FragmentHomeBinding.inflate(inflater, container, false);
   // make sure to have this as well 
        binding1.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editBookName = binding1.editBookName.getText().toString();
                editAuthorName = binding1.editAuthorName.getText().toString();

                if(!editBookName.isEmpty() && !editAuthorName.isEmpty()){
                    Library library = new Library(editBookName, editAuthorName);
                    db1 = FirebaseDatabase.getInstance();
                    reference1 = db1.getReference("Book Name");
                    reference1.child(editBookName).setValue(library).addOnCompleteListener(new OnCompleteListener<Void>() {
                        //create the child node name as the Book Name of the User
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding1.editBookName.setText("");
                            binding1.editAuthorName.setText("");
                            Toast.makeText(getContext(),"Succesfuuly Added Book",Toast.LENGTH_SHORT).show();
                            // How to call toast in Fragments
                        }
                    });

                } // end of if statement

            }
        });


        return binding1.getRoot();


    }
    // Need to write in every java class



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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


}