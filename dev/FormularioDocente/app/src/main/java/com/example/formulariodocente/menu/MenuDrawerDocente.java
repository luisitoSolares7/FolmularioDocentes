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
import android.widget.Toast;

import com.example.formulariodocente.Cambiar_Contra;
import com.example.formulariodocente.R;
import com.example.formulariodocente.fragment.Fragment_Historial;
import com.example.formulariodocente.fragment.Fragment_Inicio;
import com.example.formulariodocente.fragment.Fragment_form_fuera_clases;

import java.security.acl.Group;
import java.util.List;
import java.util.Map;

public class MenuDrawerDocente extends AppCompatActivity {

    private ActionBar actionBar;
    private Toolbar toolbar;
    private int idCuenta;
    //
    NavigationView nav_view;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_docente);
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
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Inicio");
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
            Toast.makeText(MenuDrawerDocente.this, "home", Toast.LENGTH_SHORT).show();
            cambio();
        }
        if (valor == R.id.Redes) {
            MenuItem d = nav_view.getMenu().findItem(R.id.caja);
            item.setVisible(false);
            d.setVisible(true);
            return;
        }
        if (valor == R.id.caja) {
            MenuItem d = nav_view.getMenu().findItem(R.id.Redes);
            item.setVisible(false);
            d.setVisible(true);
        }
        if(valor==R.id.nav_historial){
            fragmentManager.beginTransaction().replace(R.id.main_content, new Fragment_Historial(idCuenta)).commit();
            cambio();
        }
        if (valor == R.id.doc1) {
            cambio();
        }
        if (valor == R.id.doc2) {
            fragmentManager.beginTransaction().replace(R.id.main_content, new Fragment_form_fuera_clases(idCuenta)).commit();
            cambio();
        }
        if (valor == R.id.doc3) {
            cambio();
        }
        if (valor == R.id.doc4) {
            cambio();
        }
        if (valor == R.id.nav_salir) {
            finish();
        }
        if (valor == R.id.nav_cambio) {
            cambio();
            new Cambiar_Contra(MenuDrawerDocente.this, idCuenta);
            return;
        }
        actionBar.setTitle(item.getTitle());
        drawer.closeDrawers();
    }
    private void cambio(){
        MenuItem master = nav_view.getMenu().findItem(R.id.Redes);
        MenuItem caja = nav_view.getMenu().findItem(R.id.caja);
        caja.setVisible(false);
        master.setVisible(true);
    }
}
