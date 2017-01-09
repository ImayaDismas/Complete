package com.dismas.imaya.gatekeeper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dismas.imaya.gatekeeper.Database.SQLController;

/**
 * Created by imaya on 10/4/16.
 */
public class ModifyUserActivity extends Activity implements View.OnClickListener {
    private EditText nameText;
    private Button updateBtn, deleteBtn;
    private long user_id;
    private SQLController dbController;
    private EditText designationText;
    private EditText departmentText;
    private EditText imgurlText;
    private EditText dobText;
    private EditText heightText;
    private EditText weightText;
    private EditText allowed_areasText;
    private EditText card_serialText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modify Record");
        setContentView(R.layout.activity_modify_user);

        dbController = new SQLController(this);
        dbController.open();
        nameText = (EditText) findViewById(R.id.name_edittext);
        designationText = (EditText) findViewById(R.id.designation_edittext);
        departmentText = (EditText) findViewById(R.id.department_edittext);
        imgurlText = (EditText) findViewById(R.id.img_url_edittext);
        dobText = (EditText) findViewById(R.id.dob_edittext);
        heightText = (EditText) findViewById(R.id.height_edittext);
        weightText = (EditText) findViewById(R.id.weight_edittext);
        allowed_areasText = (EditText) findViewById(R.id.allowed_areas_edittext);
        card_serialText = (EditText) findViewById(R.id.card_serial_edittext);
        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("user_id");
        String name = intent.getStringExtra("name");
        String designation = intent.getStringExtra("designation");
        String department = intent.getStringExtra("department");
        String img_url = intent.getStringExtra("imgurl");
        String dob = intent.getStringExtra("dob");
        String height = intent.getStringExtra("height");
        String weight = intent.getStringExtra("weight");
        String allowed_areas = intent.getStringExtra("allowed_areas");
        String card_serial = intent.getStringExtra("card_serial");

        user_id = Long.parseLong(id);
        nameText.setText(name);
        designationText.setText(designation);
        departmentText.setText(department);
        imgurlText.setText(img_url);
        dobText.setText(dob);
        heightText.setText(height);
        weightText.setText(weight);
        allowed_areasText.setText(allowed_areas);
        card_serialText.setText(card_serial);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String name = nameText.getText().toString();
                String designation = designationText.getText().toString();
                String department = departmentText.getText().toString();
                String img_url = imgurlText.getText().toString();
                String dob = dobText.getText().toString();
                String height = heightText.getText().toString();
                String weight = weightText.getText().toString();
                String allowed_areas = allowed_areasText.getText().toString();
                String card_serial = card_serialText.getText().toString();
                dbController.update(user_id, name, designation, department, img_url, dob, height, weight, allowed_areas, card_serial);
                this.returnHome();
                break;
            case R.id.btn_delete:
                dbController.delete(user_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(),
                UserListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}