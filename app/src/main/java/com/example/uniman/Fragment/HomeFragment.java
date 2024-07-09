package com.example.uniman.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.uniman.Adapter.Adapter_pager;
import com.example.uniman.R;
import com.example.uniman.Utils.Utils;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment {
    private LinearLayout layout_tt,layout_kq,layout_dkhp,layout_lh,layout_qll,layout_dn,layout_hl,layout_dktk;
    private Fragment fragment = null;
    private ViewPager viewPager;
    // arrlist
    private Timer timer;
    private ArrayList<String> listpager;
    private Adapter_pager adapter_pager;
    private CircleIndicator circleIndicator;

    //
    private TextView tv_kq,tv_lh;

    int role = Utils.user.getRole();
    int b,c;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home,container,false);
        addcontroll(view);
        Loaditem();
        addevenst();
        sharedPreferences = getActivity().getSharedPreferences("pc",getActivity().MODE_PRIVATE);

        return view;
    }

    // ánh xạ view
    private void addcontroll(View view) {
        //int spacing = 8;
        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.circle);
        layout_tt = view.findViewById(R.id.layout_tt);
        layout_kq = view.findViewById(R.id.layout_kq);
        layout_dkhp = view.findViewById(R.id.layout_dk);
        layout_lh = view.findViewById(R.id.layout_lh);
        layout_qll = view.findViewById(R.id.layout_qll);
        layout_dn = view.findViewById(R.id.layout_dn);
        layout_hl = view.findViewById(R.id.layout_hl);
        layout_dktk = view.findViewById(R.id.layout_dktk);
        tv_kq = view.findViewById(R.id.tv_kq);
        tv_lh = view.findViewById(R.id.tv_lh);

        // loadlist viewpager
        listpager = getListpager();
        adapter_pager = new Adapter_pager(getContext(), listpager);
        viewPager.setAdapter(adapter_pager);
        circleIndicator.setViewPager(viewPager);
        adapter_pager.registerDataSetObserver(circleIndicator.getDataSetObserver());
        AutoSildeImgaes();
    }


    // phân quyên User
    private void Loaditem() {
       if (role == 3){
           layout_tt.setVisibility(View.GONE);
           layout_kq.setVisibility(View.GONE);
           layout_lh.setVisibility(View.GONE);
           layout_qll.setVisibility(View.GONE);
           layout_hl.setVisibility(View.GONE);
       }else if (role == 2){
           layout_tt.setVisibility(View.GONE);
           layout_dkhp.setVisibility(View.GONE);
           layout_dn.setVisibility(View.GONE);
           layout_hl.setVisibility(View.GONE);
           layout_dktk.setVisibility(View.GONE);
           tv_kq.setText("Nhập Điểm");
           tv_lh.setText("Lịch Giảng");
       }else if (role ==1){
           layout_qll.setVisibility(View.GONE);
           layout_dn.setVisibility(View.GONE);
           layout_dktk.setVisibility(View.GONE);
       }
    }

    private void addevenst() {
        layout_tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new Personalragment();
                getFragment();
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Thông Tin Cá Nhân");
            }
        });
        //
        layout_kq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role == 2){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    b = 2;
                    editor.putInt("b",b);
                    editor.apply();
                    fragment = new ClassListFragment();
                    getFragment();
                    ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Quản lý lớp");
                }else if (role == 1){
                    fragment = new Results_Fragment();
                    getFragment();
                    ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Kết quả học tập");
                }

            }
        });
        //

        layout_qll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.e("day","hd");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                b = 1;
                editor.putInt("b",b);
                editor.apply();
                fragment = new ClassListFragment();
                getFragment();
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Quản lý lớp");
            }
        });
        //

        layout_dkhp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role == 1){
                    fragment = new UserRegisterCourse_Fragment();
                    getFragment();
                    ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Đăng ký học phần");
                }else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    int a = 1;
                    editor.putInt("p",a);
                    editor.apply();
                   // Log.e("a","dd");
                    //
                    fragment = new IndustryFragment();
                    getFragment();
                    ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Đăng ký học phần Admin");
                }

            }
        });
        //
        layout_lh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new ScheduleFragment();
                getFragment();
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Lịch học");
            }
        });
        //

        layout_dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.e("a","dd");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                int a = 2;
                //c = 2;
                editor.putInt("p",a);
                //editor.putInt("c",c);
                editor.apply();
                //Log.e("a",String.valueOf(a));
                fragment = new IndustryFragment();
                getFragment();
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Danh sách ngành");
            }
        });
        //
        layout_hl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new Retest_Fragment();
                getFragment();
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Đăng ký thi lại");
            }
        });
        //
        layout_dktk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new RegisterFragment();
                getFragment();
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Đăng ký tài");
            }
        });
        //

    }


    // method khởi tạo fragment
    public void getFragment() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flcontent, fragment)
                .addToBackStack(null)
                .commit();
    }

    // list viewpager
    private ArrayList<String> getListpager() {
        ArrayList<String> list = new ArrayList<>();
        list.add(new String("https://watermark.lovepik.com/photo/40112/9310.jpg_wh1200.jpg"));
        list.add(new String("https://kidsuni.edu.vn/wp-content/uploads/2020/09/cam-nang-giao-duc-gia-dinh-1.jpg"));
        list.add(new String("https://tse3.mm.bing.net/th?id=OIP.d84HUThJyK4EDapKqMD5zgHaE8&pid=Api&P=0&w=300&h=300"));
        return list;
    }

    // hàm sử lý auto time Viewpager
    private void AutoSildeImgaes() {
        if (listpager == null || listpager.isEmpty() || viewPager == null) {
            return;
        }
        // khởi tạo timer
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();//
                        int totalItem = listpager.size() - 1;// tổng số ảnh
                        if (currentItem < totalItem) {// nếu ảnh chưa chạy đến ảnh cuối
                            currentItem++;// thì vẫn chạy tiếp
                            viewPager.setCurrentItem(currentItem);
                        } else {// nếu ảnh chạy đến cuối
                            viewPager.setCurrentItem(0);// thì quay lại ảnh đầu
                        }
                    }
                });
            }
        }, 5000, 3000);// thời gian sử lý tác vụ
    }
}