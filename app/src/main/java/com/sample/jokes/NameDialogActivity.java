package com.sample.jokes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

public class NameDialogActivity extends Activity {

    /**
     * Views
     * */
    private EditText etFirstName;
    private EditText etLastName;
    private Button btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_name);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.x = 0;
        params.height = 330;
        params.width = 450;
        params.y = 0;

        this.getWindow().setAttributes(params);

        initViews();
    }

    private void initViews(){
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        btnGo = (Button) findViewById(R.id.btnGo);

        btnGo.setOnClickListener(onclick);
    }

    private View.OnClickListener onclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            attemptLogin();
        }
    };

    private void attemptLogin() {

        // Reset errors.
        etFirstName.setError(null);
        etLastName.setError(null);

        // Store values at the time of the login attempt.
        String fname = etFirstName.getText().toString();
        String lname = etLastName.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(fname)) {
            etFirstName.setError(getString(R.string.empty_first_name));
            focusView = etFirstName;
            cancel = true;
        }else if (TextUtils.isEmpty(lname)) {
            etLastName.setError(getString(R.string.empty_last_name));
            focusView = etLastName;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {

            finish();

            Intent intent = new Intent(getApplicationContext(), JokeActivity.class);
            intent.putExtra("url",String.format("http://api.icndb.com/jokes/random?firstName=%s&amp;lastName=%s",etFirstName.getText(), etLastName.getText()));
            startActivity(intent);
        }
    }
}
