package com.example.uniman.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.uniman.Fragment.ChangeFragment;
import com.example.uniman.Fragment.ChatAdminFragment;
import com.example.uniman.Fragment.ChatFragment;
import com.example.uniman.Fragment.HomeFragment;
import com.example.uniman.Fragment.IndustryFragment;
import com.example.uniman.Fragment.NotificationFragment;
import com.example.uniman.Fragment.Personalragment;
import com.example.uniman.Fragment.ScheduleFragment;
import com.example.uniman.R;
import com.example.uniman.Utils.Utils;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private View headerView;
    private NavigationView naview;
    private Fragment fragment = null;
    //
    int role;
    // notification
    private ImageView img_notifi,img_chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addcontroll();
        Hierarchy();
        addevenst();
    }
    // ánh xạ view
    @SuppressLint("WrongViewCast")
    private void addcontroll() {
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        naview = findViewById(R.id.naview);
        img_notifi = findViewById(R.id.img_notifi);
        img_chat = findViewById(R.id.img_chat);

        // hiển thị navigation
        headerView = naview.getHeaderView(0);


        //hiển thị toobar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menutobar);
    }

    private void addevenst() {
        Navigation();

        /// add hoạt động cho img về trang chủ
        View view = naview.getHeaderView(0);
        ImageView img_user = view.findViewById(R.id.img_user);
        TextView tv_name = view.findViewById(R.id.tv_nameuser);
        //tv_name.setText(Utils.user.);
        img_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new HomeFragment();
                getFragment();

                // trọn item navigation xong ẩn navigation
                drawerLayout.closeDrawer(GravityCompat.START);// thì đóng
            }
        });
    }

    private void Navigation() {
        // set fragment mặc định
        getSupportActionBar().setTitle("Trang chủ");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flcontent, new HomeFragment())
                .commit();

        naview.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case  R.id.homepage:
                    fragment = new HomeFragment();
                    getFragment();
                    break;
                case R.id.sub_pass:
                    fragment = new ChangeFragment();
                    getFragment();
                    break;
                case R.id.person:
                    fragment = new Personalragment();
                    getFragment();
                    break;
                case R.id.lh:
                    fragment = new ScheduleFragment();
                    getFragment();
                    break;
                case R.id.qln:
                    fragment = new IndustryFragment();
                    getFragment();
                    break;

                case R.id.logout:
                    logout();
                    break;
                default:

                    break;
            }

            // đổi title fragment
            getSupportActionBar().setTitle(item.getTitle());

            drawerLayout.closeDrawers();
            return false;
        });

        img_notifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new NotificationFragment();
                getFragment();
                MainActivity.this.getSupportActionBar().setTitle("Thông Báo");
            }
        });

        img_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (Utils.user.getRole() == 2 || Utils.user.getRole() == 1){
                   fragment = new ChatFragment();
                   getFragment();
                   // an toolbar
                   //replaceFragment(fragment);
                   MainActivity.this.getSupportActionBar().setTitle("Chat");
               }else {
                   fragment = new ChatAdminFragment();
                   getFragment();
                   MainActivity.this.getSupportActionBar().setTitle("Chat");
               }
            }
        });
    }

    // phân cấp phân quyền
    private void Hierarchy() {
        role = Utils.user.getRole();
        Menu menu = naview.getMenu();
        if (role == 3){
            menu.findItem(R.id.person).setVisible(false);
            menu.findItem(R.id.result).setVisible(false);
            menu.findItem(R.id.qln).setVisible(true);
        }else if (role == 2){
            menu.findItem(R.id.ql).setVisible(false);
        }else if (role == 1){
            menu.findItem(R.id.ql).setVisible(false);
        }
    }
    // hàm sử lý hoạt động toolbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);// mở navigation bên trái
        }
        return super.onOptionsItemSelected(item);
    }
    // method khởi tạo fragment
    public void getFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flcontent, fragment)
                .addToBackStack(null)
                .commit();
    }

    // đăng xuất
    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Đăng xuất khỏi tài khoản của bạn ?");
        builder.setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                FirebaseAuth.getInstance().signOut();
                finishAffinity();
            }
        });

        builder.setNegativeButton("Hủy", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flcontent, fragment);
        ((FragmentTransaction) transaction).addToBackStack(null);  // Optional: add to back stack so the user can navigate back
        transaction.commit();

        // Ẩn toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

}