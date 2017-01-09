package com.dismas.imaya.gatekeeper;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.dismas.imaya.gatekeeper.Database.DBhelper;
import com.dismas.imaya.gatekeeper.Database.SQLController;

/**
 * Created by imaya on 10/4/16.
 */

public class UserListActivity extends AppCompatActivity {
    private SQLController dbcon;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_users_list);

        dbcon = new SQLController(this);
        dbcon.open();
        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));
        // Attach The Data From DataBase Into ListView Using Crusor Adapter
        Cursor cursor = dbcon.fetch();
        String[] from = new String[] { DBhelper._ID, DBhelper.NAME,
                DBhelper.DESIGNATION, DBhelper.DEPARTMENT, DBhelper.IMG_URL, DBhelper.DOB,
                DBhelper.HEIGHT, DBhelper.WEIGHT, DBhelper.ALLOWED_AREAS, DBhelper.CARD_SERIAL };
        int[] to = new int[] { R.id.id, R.id.name, R.id.designation, R.id.department, R.id.imageurl, R.id.dob,
        R.id.height, R.id.weight, R.id.allowed_areas, R.id.card_serial};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.activity_view_record, cursor, from, to);


        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long viewId) {
                TextView user_id_tv = (TextView) view.findViewById(R.id.id);
                TextView name_tv = (TextView) view.findViewById(R.id.name);
                TextView designation_tv = (TextView) view.findViewById(R.id.designation);
                TextView department_tv = (TextView) view.findViewById(R.id.department);
                TextView imgurl_tv = (TextView) view.findViewById(R.id.imageurl);
                TextView dob_tv = (TextView) view.findViewById(R.id.dob);
                TextView height_tv = (TextView) view.findViewById(R.id.height);
                TextView weight_tv = (TextView) view.findViewById(R.id.weight);
                TextView allowed_areas_tv = (TextView) view.findViewById(R.id.allowed_areas);
                TextView card_serial_tv = (TextView) view.findViewById(R.id.card_serial);

                String user_id = user_id_tv.getText().toString();
                String name = name_tv.getText().toString();
                String designation = designation_tv.getText().toString();
                String department = department_tv.getText().toString();
                String imageurl = imgurl_tv.getText().toString();
                String dob = dob_tv.getText().toString();
                String height = height_tv.getText().toString();
                String weight = weight_tv.getText().toString();
                String allowed_areas = allowed_areas_tv.getText().toString();
                String card_serial = card_serial_tv.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(),
                        ModifyUserActivity.class);
                modify_intent.putExtra("name", name);
                modify_intent.putExtra("designation", designation);
                modify_intent.putExtra("user_id", user_id);
                modify_intent.putExtra("department", department);
                modify_intent.putExtra("imageurl", imageurl);
                modify_intent.putExtra("dob", dob);
                modify_intent.putExtra("height", height);
                modify_intent.putExtra("weight", weight);
                modify_intent.putExtra("allowed_areas", allowed_areas);
                modify_intent.putExtra("card_serial", card_serial);
                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_record) {
            Intent add_mem = new Intent(this, AddUserActivity.class);
            startActivity(add_mem);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
