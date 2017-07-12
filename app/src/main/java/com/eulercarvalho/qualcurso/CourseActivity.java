package com.eulercarvalho.qualcurso;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by TiagoCarvalho on 11/07/17.
 */

public class CourseActivity extends Activity {
    String[] URLS;
    String[] itemname;

    private ListView mListView;

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        String[] UNB;
        String[] UCB;
        String[] JK;
        String[] IESB;
        String[] CEUB;

        this.itemname = new String[] {
                "CEUB",
                "IESB",
                "JK",
                "UCB",
                "UNB",
        };
        Bundle b = this.getIntent().getExtras();

        if (b != null) {

            this.URLS = b.getStringArray("URLs");
        }
        mListView = (ListView) findViewById(R.id.listView1);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.itemname);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedURL = URLS[position];

                if (clickedURL == "") {
                    Toast.makeText(CourseActivity.this,
                            "Esta IES não oferece este curso..",
                            Toast.LENGTH_LONG).show();
                }


                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("URL", URLS[position]);
                startActivity(intent);
            }

        });
    }


}
