package android.example.com.humyum;

import android.example.com.humyum.Model.User;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {
    EditText  etxtNumber,etxtPassword;
    Button btnSignIN;
    ProgressBar progressbar;
    TextView txtvProgressbarMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etxtNumber = (MaterialEditText) findViewById(R.id.etxt_phone);
        etxtPassword = (MaterialEditText) findViewById(R.id.etxt_password);
        Button btnSignIn = findViewById(R.id.btnsignin_signin_activity);
        progressbar = findViewById(R.id.progressBar);
        txtvProgressbarMessage = findViewById(R.id.txtv_progressbar);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference tableUser = firebaseDatabase.getReference("user");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressbar.setVisibility(View.VISIBLE);
                txtvProgressbarMessage.setVisibility(View.VISIBLE);
                tableUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(etxtNumber.getText().toString()).exists()) {
                            User user = dataSnapshot.child(etxtNumber.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(etxtPassword.getText().toString())) {
                                progressbar.setVisibility(View.INVISIBLE);
                                txtvProgressbarMessage.setVisibility(View.INVISIBLE);
                                Toast.makeText(SignIn.this, "Login Successful", Toast.LENGTH_LONG).show();
                            } else {
                                progressbar.setVisibility(View.INVISIBLE);
                                txtvProgressbarMessage.setVisibility(View.INVISIBLE);
                                Toast.makeText(SignIn.this, "Login failed", Toast.LENGTH_LONG).show();
                            }
                        }else {
                            progressbar.setVisibility(View.INVISIBLE);
                            txtvProgressbarMessage.setVisibility(View.INVISIBLE);
                            Toast.makeText(SignIn.this, "User doesn't exist", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
