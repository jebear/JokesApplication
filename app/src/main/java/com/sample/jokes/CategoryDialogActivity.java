package com.sample.jokes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class CategoryDialogActivity extends Activity {

    /**
     * Views
     * */
    private RadioGroup rgCategory;
    private Button btnGo;

    private String limitTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_category);

         WindowManager.LayoutParams params = getWindow().getAttributes();
         params.x = 0;
         params.height = 330;
         params.width = 450;
         params.y = 0;

         this.getWindow().setAttributes(params);

         initViews();


    }

    private void initViews(){
        rgCategory = (RadioGroup) findViewById(R.id.radioGroupCategory);
        btnGo = (Button) findViewById(R.id.btnGoCategory);

        btnGo.setOnClickListener(onclick);
    }

    private View.OnClickListener onclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();

            switch (rgCategory.getCheckedRadioButtonId()){
                case R.id.rbtnExplicit:
                    limitTo = "explicit";
                    break;
                case R.id.rbtnNerdy:
                    limitTo = "nerdy";
                    break;
            }

            Intent intent = new Intent(getApplicationContext(), JokeActivity.class);
            intent.putExtra("url",String.format("http://api.icndb.com/jokes/random?limitTo=[%s]",limitTo));
            startActivity(intent);
        }
    };


}
