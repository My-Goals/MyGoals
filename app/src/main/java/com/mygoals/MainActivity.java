package com.mygoals;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.mygoals.databinding.ActivityMainBinding;
import com.mygoals.ui.main.PageViewModel;
import com.mygoals.ui.main.SectionsPagerAdapter;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ImageView frame;
    private BottomNavigationView bottom_navigation;
private NavigationView navview;
    private NavigationView navView;
    private MenuItem prevMenuItem;
    private PageViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        bottom_navigation = findViewById(R.id.bottom_navigation);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
       viewPager.setOffscreenPageLimit(3); // Set the offscreen page limit to 3
        viewPager.setAdapter(sectionsPagerAdapter);


        // Glide.with(this).load(R.drawable.geniosinfondo).circleCrop().into(frame);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {


                    case R.id.lamp:
                        item.setChecked(true);
                        //Toast.makeText(MainActivity.this, "Add clicked.", Toast.LENGTH_SHORT).show();
                        // removeBadge(mybottomNavView,item.getItemId());
                        viewPager.setCurrentItem(0);
                        break;

                    case R.id.user:
                        item.setChecked(true);
                        //Toast.makeText(MainActivity.this, "Browse clicked.", Toast.LENGTH_SHORT).show();
                        //  removeBadge(mybottomNavView,item.getItemId());
                        viewPager.setCurrentItem(1);
                        break;

                    case R.id.data:
                        item.setChecked(true);
                        //Toast.makeText(MainActivity.this, "Personal clicked.", Toast.LENGTH_SHORT).show();
                        // removeBadge(mybottomNavView,item.getItemId());
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.form:
                        item.setChecked(true);
                        //Toast.makeText(MainActivity.this, "Likes clicked.", Toast.LENGTH_SHORT).show();
                        //removeBadge(mybottomNavView,item.getItemId());
                        viewPager.setCurrentItem(4);
                        break;
                }
                return false;
            }
        });


//        here we listen to viewpager moves and set navigations items checked or not

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottom_navigation.getMenu().getItem(0).setChecked(false);
                    bottom_navigation.getMenu().getItem(position).setChecked(true);
                    // removeBadge(bottom_navigation, bottom_navigation.getMenu().getItem(position).getItemId());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        hideBottomNavigationOnKeyboard();

    }

    public void hideBottomNavigationOnKeyboard() {
        View rootView = findViewById(android.R.id.content);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                rootView.getWindowVisibleDisplayFrame(r);
                int screenHeight = rootView.getRootView().getHeight();
                int keypadHeight = screenHeight - r.bottom;

                boolean isKeyboardActive = keypadHeight > screenHeight * 0.25;

                if (isKeyboardActive) {
                    bottom_navigation.setVisibility(View.INVISIBLE);
                } else {
                    bottom_navigation.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nav,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_profile:
                Intent intentCarrito = new Intent(this,ProfileActivity.class);
                startActivity(intentCarrito);
                intentCarrito.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                break;
            case R.id.nav_exit:
logOut();
            default:
                return  super.onOptionsItemSelected(item);
        }

        return false;
    }



    public void logOut() {

        FirebaseAuth.getInstance().signOut();
        Intent login_intent = new Intent(this, LoginPage.class);
        login_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); // clear previous task (optional)
        startActivity(login_intent);


    }


}
