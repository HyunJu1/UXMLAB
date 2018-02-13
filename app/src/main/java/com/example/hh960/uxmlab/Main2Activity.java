package com.example.hh960.uxmlab;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements LoadJSONTask.Listener, AdapterView.OnItemClickListener {

    private ListView mListView;

    public static final String URL = "http://192.168.56.1/uxmlab_read_board.php";

    private List<HashMap<String, String>> mAndroidMapList = new ArrayList<>();

    private static final String KEY_BOARD_TITLE = "board_title";
    private static final String KEY_BOARD_CONTENT = "board_content";
    private static final String KEY_AUTHOR = "author";
//    @Override
//    protected void
//    onResume() {
//        super.onResume();
//        setContentView(R.layout.activity_main2);
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(getApplicationContext(),BoardWriteActivity.class);
                Intent intent2= getIntent();
                String id = intent2.getStringExtra("id");
                intent.putExtra("id",id);
                startActivity(intent);
//                Intent intent = new Intent(Main2Activity.this, BoardWriteActivity.class);
//                startActivity(intent);
            }
        });
        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);
        new LoadJSONTask(this).execute(URL);
    }

    @Override
    public void onLoaded(List<Board_Read> androidList) {

        for (Board_Read result : androidList) {

            HashMap<String, String> map = new HashMap<>();

            map.put(KEY_BOARD_TITLE, result.getBoard_title());
            map.put(KEY_BOARD_CONTENT, result.getBoard_content());
            map.put(KEY_AUTHOR, result.getAuthor());

            mAndroidMapList.add(map);
        }

        loadListView();
    }

    @Override
    public void onError() {

        Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(this, mAndroidMapList.get(i).get(KEY_BOARD_CONTENT),Toast.LENGTH_LONG).show();
    }

    private void loadListView() {

        ListAdapter adapter = new SimpleAdapter(Main2Activity.this, mAndroidMapList, R.layout.list_item,
                new String[] {KEY_BOARD_TITLE, KEY_BOARD_CONTENT, KEY_AUTHOR},
                new int[] { R.id.version,R.id.name, R.id.api });

        mListView.setAdapter(adapter);

    }
}