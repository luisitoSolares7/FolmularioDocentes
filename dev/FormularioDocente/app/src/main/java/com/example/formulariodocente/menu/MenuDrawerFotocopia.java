package com.example.formulariodocente.menu;

import android.content.ClipData;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.CursorTreeAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.formulariodocente.Cambiar_Contra;
import com.example.formulariodocente.R;
import com.example.formulariodocente.fragment.Fragment_Historial;
import com.example.formulariodocente.fragment.Fragment_Inicio;
import com.example.formulariodocente.fragment.Fragment_form_Incidentes;
import com.example.formulariodocente.fragment.Fragment_form_fuera_clases;

import java.security.acl.Group;
import java.util.List;
import java.util.Map;

public class MenuDrawerFotocopia extends AppCompatActivity {

    private ActionBar actionBar;
    private Toolbar toolbar;
    private int idCuenta;
    boolean bandera = true;

    //
    NavigationView nav_view;
    DrawerLayout drawer;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_fotocopia);
        this.init();
        this.initToolbar();
        this.initNavigationMenu();
    }

    public void init() {
        Bundle parametros = this.getIntent().getExtras();
        if (parametros != null) {
            idCuenta = parametros.getInt("idCuenta");
        }
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = toolbar.findViewById(R.id.title);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        title.setText("INICIO");
        actionBar.setTitle("");
    }

    private void initNavigationMenu() {
        nav_view = (NavigationView) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_content, new Fragment_Inicio()).commit();

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {
                acciones(item);
                return true;
            }
        });

        //drawer.openDrawer(GravityCompat.START);
    }

    public void acciones(MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        int valor = item.getItemId();
        if (valor == R.id.nav_home) {
            fragmentManager.beginTransaction().replace(R.id.main_content, new Fragment_Inicio()).commit();
            bandera = true;
            Toast.makeText(MenuDrawerFotocopia.this, "home", Toast.LENGTH_SHORT).show();
        }

        if (valor == R.id.nav_historial) {
            fragmentManager.beginTransaction().replace(R.id.main_content, new Fragment_Historial(idCuenta)).commit();
            bandera=false;
        }
        if(valor== R.id.QR){
            bandera=false;
        }
        if (valor == R.id.nav_salir) {
            finish();
        }
        //actionBar.setTitle(item.getTitle());
        invalidateOptionsMenu();
        title.setText(item.getTitle());
        drawer.closeDrawers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (bandera) {
            menu.clear();
        } else {
            getMenuInflater().inflate(R.menu.opcione_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
