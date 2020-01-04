package fall2018.csc207project.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fall2018.csc207project.gamecenter.GlobalCenter;
import fall2018.csc207project.gamecenter.GlobalManager;
import fall2018.csc207project.slidingtilegame.R;

public class SignUp extends AppCompatActivity {

    /**
     * The entered username to be updated from the editText for username
     */
    private String userName = "";

    /**
     * The entered password to be updated from the editText for password
     */
    private String password = "";

    private GlobalCenter globalCenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        globalCenter = GlobalManager.getGlobalCenter();
        addSignUpButtonListener();
        addCancelButtonListener();
    }

    /**
     * Activate the cancel button.
     */
    private void addCancelButtonListener() {
        Button CancelButton = findViewById(R.id.cancel_signup);
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }

    /**
     * Activate the signUp button.
     */
    private void addSignUpButtonListener() {
        Button loginButton = findViewById(R.id.signup_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText User = findViewById(R.id.signup_username);
                EditText Password = findViewById(R.id.signup_password);
                userName = User.getText().toString();
                password = Password.getText().toString();
                if (globalCenter.signUp(userName, password)) {
                    GlobalManager.saveAll(getApplicationContext());
                    localCenter();
                } else {
                    Toast.makeText(getApplicationContext(), "SignUp Failed",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Switch back to the Global view.
     */
    private void cancel() {
        Intent tmp = new Intent(this, Global.class);
        startActivity(tmp);
    }

    /**
     * Switch to the LocalGameCenterActivity view for the current user that just signs up.
     */
    private void localCenter() {
        Intent tmp = new Intent(this, LocalGameCenterActivity.class);
        startActivity(tmp);
    }
}