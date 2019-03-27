package android.example.com.humyum;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnSignUp,btnSignIn;
    TextView txtSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignIn = findViewById(R.id.btnsignin);
        btnSignUp = findViewById(R.id.btnsignup);

        txtSlogan = findViewById(R.id.txtvSlogan);
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/Simplehandwritting-Regular.ttf");
        txtSlogan.setTypeface(face);
    }
}
