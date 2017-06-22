package com.eulercarvalho.qualcurso;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //cursos
    private ListView mListView;
    private AdapterView listView;
    String[] COURSES;
    String[] URLS;

    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.COURSES = getResources().getStringArray(R.array.biomedicas);
        this.URLS = getResources().getStringArray(R.array.urlbiomedicas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mListView = (ListView) findViewById(R.id.cursos);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (verifyNetwork()) {
                    Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra("URL", URLS[position]);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this,
                            "É necessário possuir uma conexão ativa com a Internet: " + "\n"
                            + "Verifique e tente novamente..",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        reloadListData();
    }

    private void reloadListData() {
        String[] listItems = new String[COURSES.length];

        for(int i = 0; i < COURSES.length; i++){
            String title = COURSES[i];
            listItems[i] = title;
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    final public Boolean verifyNetwork() {
        if (this.context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (networkInfo == null) {
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()){
            case R.id.cienciasbiomedicas: {
                this.COURSES = getResources().getStringArray(R.array.biomedicas);
                this.URLS = getResources().getStringArray(R.array.urlbiomedicas);
                break;
            }
            case R.id.cienciasexatas: {
                this.COURSES = getResources().getStringArray(R.array.exatas);
                this.URLS = getResources().getStringArray(R.array.urlexatas);
                break;
            }
            case R.id.cienciashumanas: {
                this.COURSES = getResources().getStringArray(R.array.humanas);
                this.URLS = getResources().getStringArray(R.array.urlhumanas);
                break;
            }
        }

        reloadListData();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
