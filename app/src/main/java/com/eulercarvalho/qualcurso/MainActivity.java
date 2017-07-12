package com.eulercarvalho.qualcurso;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.content.Context;
import android.util.Log;
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
    String[] UNB;
    String[] UCB;
    String[] JK;
    String[] IESB;
    String[] CEUB;

    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mListView = (ListView) findViewById(R.id.cursos);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (verifyNetwork()) {
                    Bundle b = new Bundle();
                    b.putStringArray("URLs", createURLS(position));

                    Intent intent = new Intent(context, CourseActivity.class);

                    intent.putExtras(b);
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

    private void populateData() {
        this.COURSES = new String[]{
                "Administração",
                "Enfermagem",
                "Letras",
                "Sistemas de Informação",
                "Rede de Computadores",
                "Fisioterapia"
        };

        this.UNB = new String[] {
                "http://www.adm.unb.br/",
                "https://fs.unb.br/enfermagem/",
                "http://www.il.unb.br/",
                "http://www.unb.br/",
                "http://www.unb.br/",
                "http://fce.unb.br/sobre-o-curso-fis",
        };

        this.UCB = new String[] {
                "http://www.adm.unb.br/",
                "http://www.ucb.br/Cursos/38Enfermagem/",
                "http://www.ucb.br/Cursos/23Letras/",
                "http://www.ucb.br/Cursos/19SistemasDeInformacao/",
                "http://www.ucb.br/Cursos/Graduacao/",
                "http://www.ucb.br/Cursos/22Fisioterapia/",
        };

        this.JK = new String[] {
                "http://www.rededeensinojk.com.br/administracao",
                "http://www.rededeensinojk.com.br/enfermagem",
                "http://www.rededeensinojk.com.br/letras",
                "http://www.rededeensinojk.com.br/sistema-de-informacao",
                "http://www.rededeensinojk.com.br/rede-de-computadores",
                "http://www.rededeensinojk.com.br/",
        };

        this.IESB = new String[] {
                "http://www.iesb.br/graduacao/curso/administracao",
                "http://www.iesb.br/graduacao/curso/enfermagem",
                "http://www.iesb.br",
                "http://www.iesb.br/graduacao/curso/ciencia-da-computacao",
                "http://www.iesb.br/graduacao/curso/redes-de-computadores",
                "http://www.iesb.br",
        };


        this.CEUB = new String[] {
                "https://www.uniceub.br/processo-seletivo/vestibular/administracao.aspx#c",
                "https://www.uniceub.br/processo-seletivo/vestibular/enfermagem.aspx#c",
                "https://www.uniceub.br",
                "https://www.uniceub.br/processo-seletivo/vestibular/ciencia-da-computacao.aspx#c",
                "https://www.uniceub.br",
                "https://www.uniceub.br/processo-seletivo/vestibular/fisioterapia.aspx#c",
        };
    }

    public String[] createURLS(int index) {
        this.URLS = new String[] {
                this.CEUB[index],
                this.IESB[index],
                this.JK[index],
                this.UCB[index],
                this.UNB[index],
        };

        return this.URLS;
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

//    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()){
//            case R.id.cienciasbiomedicas: {
//                this.COURSES = getResources().getStringArray(R.array.biomedicas);
//                this.URLS = getResources().getStringArray(R.array.urlbiomedicas);
//                break;
//            }
//            case R.id.cienciasexatas: {
//                this.COURSES = getResources().getStringArray(R.array.exatas);
//                this.URLS = getResources().getStringArray(R.array.urlexatas);
//                break;
//            }
//            case R.id.cienciashumanas: {
//                this.COURSES = getResources().getStringArray(R.array.humanas);
//                this.URLS = getResources().getStringArray(R.array.urlhumanas);
//                break;
//            }
        }

//        reloadListData();
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
