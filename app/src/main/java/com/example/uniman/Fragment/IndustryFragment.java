package com.example.uniman.Fragment;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uniman.Adapter.Adapter_Industry;
import com.example.uniman.Listener.Listener_Majors;
import com.example.uniman.Model.Majors;
import com.example.uniman.R;
import com.example.uniman.ViewModel.Industry_ViewModel;
import com.example.uniman.ViewModel.Majors_ViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class IndustryFragment extends Fragment implements Listener_Majors {

    private RecyclerView rcv_industry;
    private Adapter_Industry adapter_industry;
    private Industry_ViewModel industry_viewModel;
    private Fragment fragment = null;
    private FloatingActionButton float_add_nganh;
    private AlertDialog alertDialog;
    boolean tem;
    String manganh, tennganh,chuyennganh;
    int a;
    // dialog
    private TextInputEditText ed_manganh, ed_tenganh, ed_chuyennganh;
    private Button btn_add, btn_cancel;
    private ImageView img_add;
    private LinearLayout layout_add;
    //// thêm chuyên ngành
    private TextInputLayout textInputLayout;
    private TextInputEditText editText;
    // Khai báo một danh sách để lưu trữ ID của các EditText
    private List<TextInputEditText> editTextIds = new ArrayList<>();
    private  List<String> values;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_industry, container, false);
        addcontroll(view);
        addevenst();
        return view;
    }


    // ánh xạ view
    private void addcontroll(View view) {
        float_add_nganh = view.findViewById(R.id.float_add_nganh);
        rcv_industry = view.findViewById(R.id.rcv_industry);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_industry.setLayoutManager(linearLayoutManager);
        LoadList();
        //
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("pc", getActivity().MODE_PRIVATE);
        a = sharedPreferences.getInt("p", -1);
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void LoadList() {
        industry_viewModel = new ViewModelProvider(IndustryFragment.this).get(Industry_ViewModel.class);
        industry_viewModel.industry().observe(IndustryFragment.this, majors_model -> {
            if (majors_model.isSuccess()) {
                adapter_industry = new Adapter_Industry(getContext(), majors_model.getResult(), this);
                rcv_industry.setAdapter(adapter_industry);

                // dòng kẻ của rcv
                RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
                rcv_industry.addItemDecoration(itemDecoration);
            } else {
                Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addevenst() {
        float_add_nganh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tem = true;
                OpenDialog();
            }
        });
    }

    public void OpenDialog() {
        // set dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_add_nganh, null);
        builder.setView(v);
        // hiển thị dialog
        alertDialog = builder.create();
        alertDialog.setCancelable(false);// ko cho user thoát
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// bo góc
        alertDialog.show();
        //

        //

        addcontrolldialog(v);
        addevenstdialog();
    }

    private void addcontrolldialog(View v) {
        ed_manganh = v.findViewById(R.id.ed_manganh);
        ed_tenganh = v.findViewById(R.id.ed_tennganh);
        ed_chuyennganh = v.findViewById(R.id.ed_chuyennganh);
        btn_add = v.findViewById(R.id.btn_add_nganh);
        btn_cancel = v.findViewById(R.id.btn_huy_nganh);
        img_add = v.findViewById(R.id.img_add_cn);
        layout_add = v.findViewById(R.id.layout_add_cn);

    }

    private void addevenstdialog() {
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                editTextIds.clear();
            }
        });
        //

        // img_add chuyên ngành
        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEditText();

            }
        });

        if (tem) {
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manganh = ed_manganh.getText().toString().trim();
                    tennganh = ed_tenganh.getText().toString().trim();
                    //chuyennganh = ed_chuyennganh.getText().toString();
                    displayAllEditTextValues();
                    editTextIds.add(ed_chuyennganh);
                    //Log.e("chuyenganh",chuyennganh);
                    if (manganh.equals("") || tennganh.equals("")){
                        Toast.makeText(getContext(), "Yêu cầu nhập đủ", Toast.LENGTH_SHORT).show();
                    }else {
                        Majors_ViewModel majors_viewModel = new ViewModelProvider(getActivity()).get(Majors_ViewModel.class);
                        majors_viewModel.themnganh(manganh,tennganh,chuyennganh).observe(getActivity(),majors_model -> {
                            if (majors_model.isSuccess()){

                                LoadList();
                                Toast.makeText(getContext(), "Thành Công", Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();
                            }else {
                                Toast.makeText(getContext(), "thât bại", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
            });
        }

    }



    private void addEditText() {
        // Create a new TextInputLayout and EditText
        textInputLayout = new TextInputLayout(getContext());
        LinearLayout.LayoutParams textInputLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.VERTICAL
        );
        textInputLayoutParams.setMargins(0, 10, 0, 0);
        textInputLayout.setLayoutParams(textInputLayoutParams);

        // Adjust the width of the TextInputLayout to match parent
        textInputLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // Create and customize the EditText
        editText = new TextInputEditText(getContext());
        int id = View.generateViewId();
        editText.setId(id);
        editText.setHint("Chuyên Ngành");
        editText.setTextSize(20);

        // Create custom LayoutParams for the EditText with width 65dp
        LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dpToPx(65)
        );

        // Set the LayoutParams to EditText
        editText.setLayoutParams(editTextParams);

        // Add EditText to TextInputLayout
        textInputLayout.addView(editText);

        // Add TextInputLayout to the layout_add_cn LinearLayout
        layout_add.addView(textInputLayout);
        editTextIds.add(editText);
    }

    // Convert dp to pixels
    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
    // Phương thức để lấy giá trị của tất cả các EditText đã được thêm vào
    private List<String> getAllEditTextValues() {
        List<String> values = new ArrayList<>();
        for (TextInputEditText editText : editTextIds) {
            String text = editText.getText().toString();
            values.add(text);
        }
        return values;
    }

    // Một ví dụ về cách sử dụng phương thức để lấy giá trị của tất cả các EditText
    private void displayAllEditTextValues() {
        values = getAllEditTextValues();
        for (String value : values) {
            //Log.e("te", value); // Hiển thị mỗi giá trị riêng lẻ
        }
        chuyennganh = values.toString();
        Log.e("te", chuyennganh);
    }

    @Override
    public void OnClickMajors(Majors majors) {
        Bundle bundle = new Bundle();
        String manganh = majors.getManganh();
        if (a == 1) {
            bundle.putString("manganh", manganh);
            bundle.putString("tennganh", majors.getTennganh());
            bundle.putString("manganh", majors.getManganh());
            fragment = new YearcourseFragment();
            fragment.setArguments(bundle);
            getFragment();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Khóa Học");
        } else if (a == 2) {
            String tennganh = majors.getTennganh();
            bundle.putString("manganh", manganh);
            bundle.putString("tennganh", tennganh);
            fragment = new ClassListFragment();
            fragment.setArguments(bundle);
            getFragment();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Danh sách lớp");
        }

    }

    // update
    @Override
    public void OnClickUpdate(Majors majors) {
        tem = false;
        if (tem == false) {
            OpenDialog();
            btn_add.setText("Sửa");
            ed_manganh.setText(majors.getManganh());
            ed_tenganh.setText(majors.getTennganh());
            //
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manganh = ed_manganh.getText().toString();
                    tennganh = ed_tenganh.getText().toString();
                    Majors_ViewModel majors_viewModel = new ViewModelProvider(getActivity())
                            .get(Majors_ViewModel.class);
                    majors_viewModel.updatenganh(majors.getId(), manganh, tennganh).observe(getActivity()
                            , majors_model -> {
                        if (majors_model.isSuccess()) {
                            LoadList();
                            alertDialog.dismiss();
                            Toast.makeText(getContext(), "Thành Công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Thât bại", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }

    }


    // method khởi tạo fragment
    public void getFragment() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flcontent, fragment)
                .addToBackStack(null)
                .commit();
    }
}