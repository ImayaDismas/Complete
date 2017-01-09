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
public class AddUserActivity extends Activity implements View.OnClickListener {
    private Button addTodoBtn;
    private SQLController dbController;
    private EditText nameEditText;
    private EditText designationEditText;
    private EditText departmentEditText;
    private EditText image_urlEditText;
    private EditText dobEditText;
    private EditText heightEditText;
    private EditText weightEditText;
    private EditText allowed_areasEditText;
    private EditText card_serialEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add User");

        setContentView(R.layout.activity_add_user);
        nameEditText = (EditText) findViewById(R.id.name_edittext);
        designationEditText = (EditText) findViewById(R.id.designation_edittext);
        departmentEditText = (EditText) findViewById(R.id.department_edittext);
        image_urlEditText = (EditText) findViewById(R.id.img_url_edittext);
        dobEditText = (EditText) findViewById(R.id.dob_edittext);
        heightEditText = (EditText) findViewById(R.id.height_edittext);
        weightEditText = (EditText) findViewById(R.id.weight_edittext);
        allowed_areasEditText = (EditText) findViewById(R.id.allowed_areas_edittext);
        card_serialEditText = (EditText) findViewById(R.id.card_serial_edittext);
        addTodoBtn = (Button) findViewById(R.id.add_user);
        dbController = new SQLController(this);
        dbController.open();
        addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_user:
                final String name = nameEditText.getText().toString();
                final String designation = designationEditText.getText().toString();
                final String department = departmentEditText.getText().toString();
                final String img_url = image_urlEditText.getText().toString();
                final String dob = dobEditText.getText().toString();
                final String height = heightEditText.getText().toString();
                final String weight = weightEditText.getText().toString();
                final String allowed_areas = allowed_areasEditText.getText().toString();
                final String card_serial = card_serialEditText.getText().toString();

                dbController.insert(name, designation, department, img_url, dob, height, weight, allowed_areas, card_serial);


                Intent main = new Intent(AddUserActivity.this,
                        UserListActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;
            default:
                break;
        }
    }
}