package com.example.adrian.aplikacjewielowarstwowe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class ActivityMain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private Toolbar tToolbar;
    private DrawerLayout dlNavigation;
    private NavigationView nvNavigation;
    private TextView tvNameUser;
    private TextView tvLoginUser;

    private String lastFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tToolbar = (Toolbar) findViewById(R.id.toolbar);
        dlNavigation = (DrawerLayout) findViewById(R.id.id_main_drawer_layout);
        nvNavigation = (NavigationView) findViewById(R.id.nav_view);

        setSupportActionBar(tToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dlNavigation, tToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        dlNavigation.setDrawerListener(toggle);

        toggle.syncState();

        nvNavigation.setNavigationItemSelectedListener(this);

        setMainFragment();
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.id_main_drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    private void setMainFragment()
    {
        Fragment nextFragment = new FragmentContact();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.id_main_content, nextFragment);
        fragmentTransaction.commit();
    }

    /*
    Obsługa poszczególnych pozycji w menu głównym aplikacji
     */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        Fragment nextFragment = null;

        int id = item.getItemId();

        if (id == R.id.id_menu_main_add_incident)
        {
            nextFragment = new FragmentAddIncident();
        }
        else if (id == R.id.id_menu_main_resolve_incident)
        {
            nextFragment = new FragmentResolveIncident();
        }
        else if (id == R.id.id_menu_main_active_incident)
        {
            nextFragment = new FragmentActiveIncident();
        }
        else if (id == R.id.id_menu_main_contact)
        {
            nextFragment = new FragmentContact();
        }
        else if (id == R.id.id_menu_main_log_out)
        {
            Intent intent = new Intent(this, ActivityLogin.class);
            startActivity(intent);
            finish();
        }

        if(nextFragment != null)
        {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.id_main_content, nextFragment);
            fragmentTransaction.commit();
        }

        DrawerLayout dlNavigation = (DrawerLayout) findViewById(R.id.id_main_drawer_layout);

        dlNavigation.closeDrawer(GravityCompat.START);

        return true;
    }

    public void setLastFragment(String lastFragment)
    {
        this.lastFragment = lastFragment;
    }

    public String getLastFragment()
    {
        return lastFragment;
    }
}
