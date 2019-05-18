package com.example.formulariodocente.menu;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CursorTreeAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.formulariodocente.R;
import com.example.formulariodocente.fragment.Fragment_Inicio;

import java.util.List;
import java.util.Map;

public class MenuDrawerDocente extends AppCompatActivity {

    private ActionBar actionBar;
    private Toolbar toolbar;
    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_docente);
        initToolbar();
        initNavigationMenu();
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
        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
                FragmentManager fragmentManager = getSupportFragmentManager();
                if (item.getItemId() == R.id.nav_home) {
                    //  fragmentManager.beginTransaction().replace(R.id.main_content, new FragmentCatalogo()).commit();
                    Toast.makeText(MenuDrawerDocente.this, "home", Toast.LENGTH_SHORT).show();
                }
                if (item.getItemId() == R.id.nav_trending) {
                    //fragmentManager.beginTransaction().replace(R.id.main_content, new FragmentPedido()).commit();
                    Toast.makeText(MenuDrawerDocente.this, "treeding", Toast.LENGTH_SHORT).show();
                }
                actionBar.setTitle(item.getTitle());
                drawer.closeDrawers();

                return true;
            }
        });

        drawer.openDrawer(GravityCompat.START);
    }
}
