package com.example.formulariodocente.menu;

import android.content.ClipData;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
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
import com.example.formulariodocente.fragment.Fragment_form_Fotocopia;
import com.example.formulariodocente.fragment.Fragment_form_Incidentes;
import com.example.formulariodocente.fragment.Fragment_form_fuera_clases;
import com.example.formulariodocente.fragment.Fragment_form_reprogramacion;
import com.example.formulariodocente.fragment.Fragment_historial_Fotocopia;

import java.security.acl.Group;
import java.util.List;
import java.util.Map;

public class MenuDrawerDocente extends AppCompatActivity {

    private ActionBar actionBar;
    private Toolbar toolbar;
    private int idCuenta;
    boolean bandera = false;
    NavigationView nav_view;
    DrawerLayout drawer;
    TextView title;

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
        fragmentManager.beginTransaction().replace(R.id.main_content, new Fragment_Inicio(idCuenta)).commit();

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
            fragmentManager.beginTransaction().replace(R.id.main_content, new Fragment_Inicio(idCuenta)).commit();
            bandera = false;
            cambio();
        }
        if (valor == R.id.Redes) {
            MenuItem d = nav_view.getMenu().findItem(R.id.caja);
            item.setVisible(false);
            d.setVisible(true);
            bandera = true;
            invalidateOptionsMenu();
            return;
        }
        if (valor == R.id.caja) {
            MenuItem d = nav_view.getMenu().findItem(R.id.Redes);
            item.setVisible(false);
            d.setVisible(true);
            bandera = true;
        }
        if (valor == R.id.nav_historial) {
            fragmentManager.beginTransaction().replace(R.id.main_content, new Fragment_Historial(idCuenta)).commit();
            bandera = false;
            cambio();
        }
        if(valor==R.id.nav_fotocopia){
            fragmentManager.beginTransaction().replace(R.id.main_content, new Fragment_historial_Fotocopia(idCuenta)).commit();
            bandera = false;
            cambio();
        }
        if (valor == R.id.doc1) {
            fragmentManager.beginTransaction().replace(R.id.main_content, new Fragment_form_Incidentes(idCuenta)).commit();
            bandera = true;
            cambio();
        }
        if (valor == R.id.doc2) {
            fragmentManager.beginTransaction().replace(R.id.main_content, new Fragment_form_fuera_clases(idCuenta)).commit();
            bandera = true;
            cambio();
        }
        if (valor == R.id.doc3) {
            fragmentManager.beginTransaction().replace(R.id.main_content, new Fragment_form_reprogramacion(idCuenta)).commit();
            bandera = true;
            cambio();
        }
        if (valor == R.id.doc4) {
            fragmentManager.beginTransaction().replace(R.id.main_content, new Fragment_form_Fotocopia(idCuenta)).commit();
            bandera = true;
            cambio();
        }
        if (valor == R.id.nav_salir) {
            bandera = true;
            finish();
        }
        if (valor == R.id.nav_cambio) {
            cambio();
            new Cambiar_Contra(MenuDrawerDocente.this, idCuenta);
            return;
        }
        //actionBar.setTitle(item.getTitle());
        invalidateOptionsMenu();
        title.setText(item.getTitle());
        drawer.closeDrawers();
    }

    private void cambio() {
        MenuItem master = nav_view.getMenu().findItem(R.id.Redes);
        MenuItem caja = nav_view.getMenu().findItem(R.id.caja);
        caja.setVisible(false);
        master.setVisible(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        if (bandera) {
            getMenuInflater().inflate(R.menu.opcion_menu1, menu);
        } else {
            getMenuInflater().inflate(R.menu.opcione_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragmento = fragmentManager.findFragmentById(R.id.main_content);
        if (id == R.id.action_settings) {

            if (fragmento instanceof Fragment_Historial) {
                fragmentManager.beginTransaction().replace(R.id.main_content, new Fragment_Historial(idCuenta)).commit();
            } else {
                fragmentManager.beginTransaction().replace(R.id.main_content, new Fragment_Inicio(idCuenta)).commit();
            }
            cambio();
            return true;
        }
        if (id == R.id.action_clear) {
            if (fragmento instanceof Fragment_form_Fotocopia) {
                fragmentManager.beginTransaction().replace(R.id.main_content, new Fragment_form_Fotocopia(idCuenta)).commit();
            }
            if (fragmento instanceof Fragment_form_fuera_clases) {
                fragmentManager.beginTransaction().replace(R.id.main_content, new Fragment_form_fuera_clases(idCuenta)).commit();
            }
            if (fragmento instanceof Fragment_form_reprogramacion) {
                fragmentManager.beginTransaction().replace(R.id.main_content, new Fragment_form_reprogramacion(idCuenta)).commit();
            }
            if (fragmento instanceof Fragment_form_Incidentes) {
                fragmentManager.beginTransaction().replace(R.id.main_content, new Fragment_form_Incidentes(idCuenta)).commit();
            }
            cambio();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
