package com.example.login_testt;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.login_testt.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    //-------

    ActivityMainBinding binding;
///    String editFirstname, editLastname, editAge, editUsername;
    FirebaseDatabase db;
    DatabaseReference reference;

    // For activity_main.xml, android:hint lets you have next
    // in input box on the app without having the actual text
    // it will be invisible when you start typing

    // Textview does not require input for activity_main.xml

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item-> {

            if(item.getItemId() == R.id.home){
                Toast.makeText(MainActivity.this, "Successfuly switched to Add Book", Toast.LENGTH_SHORT).show();
                replaceFragment(new HomeFragment());
            }
            else if(item.getItemId() == R.id.profile){
                Toast.makeText(MainActivity.this, "Successfuly switched to Get Data", Toast.LENGTH_SHORT).show();
                replaceFragment(new ProfileFragment());
            }
            else if(item.getItemId() == R.id.settings){
                Toast.makeText(MainActivity.this, "Successfuly switched to Check In/OUT", Toast.LENGTH_SHORT).show();
                replaceFragment(new SettingFragment());
            }


            return true;
        });


/*
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this is where you get the input
                editFirstname = binding.editFirstName.getText().toString();
                editLastname = binding.editLastName.getText().toString();
                editAge = binding.editAge.getText().toString();
                editUsername = binding.editUsername.getText().toString();

                if(!editFirstname.isEmpty() && !editLastname.isEmpty() && !editAge.isEmpty() && !editUsername.isEmpty() ){
                    // checking to see if the the inputs are not empty
                    Users users = new Users(editFirstname,editLastname, editAge, editUsername);
                    // creating an object of Users Class
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference(  "Users");
                    // Creates the Main Node Name
                    reference.child(editUsername).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        // creates the child node name as the Username of the user
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                                // After the input has gone in, it will set all the fields as empty once the data
                             // has been given to databse
                            binding.editFirstName.setText("");
                            binding.editLastName.setText("");
                            binding. editAge.setText("");
                            binding.editUsername.setText("");

                            Toast.makeText(MainActivity.this, "Successfuly Updated", Toast.LENGTH_SHORT).show();
                        }
                    });
                            // Name of the child node
                } // end of the if statement

            }
        });
*/

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_Layout,fragment);
        fragmentTransaction.commit();
        // added the prevois line

    }

    //-------


}