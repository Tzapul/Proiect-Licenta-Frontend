package com.example.tzapt.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tzapt.fragments.AboutUsFragment;
import com.example.tzapt.fragments.BookingsFragment;
import com.example.tzapt.fragments.MyAccountFragment;
import com.example.tzapt.models.Client;
import com.example.tzapt.models.Guest;

public class UserMainView extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Client client;
    private MenuItem myAccountItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().performIdentifierAction(R.id.my_reservations, 0);
        navigationView.setCheckedItem(R.id.my_reservations);

        Menu menu = navigationView.getMenu();
        myAccountItem = menu.findItem(R.id.my_account);

        client = (Client) getIntent().getSerializableExtra("client");

        if (client instanceof Guest) {
            myAccountItem.setVisible(false);
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Bundle bundle = new Bundle();
        bundle.putSerializable("client", client);
        Fragment fragment = null;
        if (id == R.id.my_account) {
            fragment = new MyAccountFragment();
            fragment.setArguments(bundle);
        } else if (id == R.id.my_reservations) {
            fragment = new BookingsFragment();
            fragment.setArguments(bundle);
        } else if (id == R.id.about_us){
            fragment = new AboutUsFragment();

        }

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(
                R.id.main_content,
                fragment,
                fragment.getTag()).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
