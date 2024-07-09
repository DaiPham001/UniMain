package com.example.uniman.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.uniman.Adapter.AdapterSpinner_Class;
import com.example.uniman.Adapter.AdapterSpinner_cnganh;
import com.example.uniman.Adapter.Adapter_Fullnganh;
import com.example.uniman.Adapter.Adapter_StudentList;
import com.example.uniman.Model.Class;
import com.example.uniman.Model.Majors;
import com.example.uniman.Model.Student;
import com.example.uniman.R;
import com.example.uniman.Utils.Utils;
import com.example.uniman.ViewModel.ClassList_ViewModel;
import com.example.uniman.ViewModel.Industry_ViewModel;
import com.example.uniman.ViewModel.Majors_ViewModel;
import com.example.uniman.ViewModel.Schedule_ViewModel;
import com.example.uniman.ViewModel.Student_ViewModel;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;

import kotlin.text.UStringsKt;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class StudentListFragment extends Fragment implements Adapter_StudentList.OnItemClickListener {

    private RecyclerView rcv_studentlist;
    private String tenlop, tennganh, ten;
    private Adapter_StudentList adapter_studentList;
    private Student_ViewModel student_viewModel;
    private FloatingActionButton float_add_pm;

    // view dialog
    private EditText ed_ma, ed_name, ed_lick;
    private TextView tv_nganh, tv_class;
    private Spinner spinner_gt, spinner_cnganh, nganh, lop,spinnercn;
    private Button btn_add, btn_huy;
    private ImageView img_cam;
    AlertDialog alertDialog;
    //
    int loaigt = 0;
    //
    String manganh, mediaPath, chuyennganh,nganhpinner,loppiner;
    private ArrayAdapter adapter;
    private AdapterSpinner_cnganh adapterSpinner_cnganh;
    //
    private TextView ed_search;
    // nhận du liệu tìm kiem
    String manganhk, tenganhk;

    // du liệu role 2 giang viên;
    String tenlopg,tenk;

    //
    Fragment fragment = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_student_list, container, false);
        addcontroll(view);
        addevenst();
        return view;
    }

    private void addcontroll(View view) {
        ed_search = view.findViewById(R.id.ed_search);
        float_add_pm = view.findViewById(R.id.float_add_pm);
        rcv_studentlist = view.findViewById(R.id.rcv_studentlist);
        //
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_studentlist.setLayoutManager(linearLayoutManager);
        if (Utils.user.getRole() == 3){
            // nhận dữ liệu từ ClasslistFragment
            tenlop = getArguments().getString("tenlop");
            Loadlist(tenlop);
            tennganh = getArguments().getString("tennganh");
            manganh = getArguments().getString("manganh");

            // nhận du liệu back tù tìm kiêm role 3
            manganhk = getArguments().getString("manganhs");
            tenganhk = getArguments().getString("tennganhs");
        }else if (Utils.user.getRole() == 2){
            float_add_pm.setVisibility(View.GONE);
            // nhận du liệu back tù tìm kiêm role 2
            tenlopg = getArguments().getString("tenlopg");
            if (tenlopg != null){
                Loadlist(tenlopg);
            }else {
                tenk = getArguments().getString("tenk");
                Log.e("giang viên",tenk);
                Loadlist(tenk);
            }

        }

    }

    private void Loadlist(String a) {
        student_viewModel = new ViewModelProvider(getActivity()).get(Student_ViewModel.class);
        if (a != null) {
            student_viewModel.Studentlist(a).observe(getActivity(), student_model -> {
                if (student_model.isSuccess()) {
                    adapter_studentList = new Adapter_StudentList(getContext(), student_model.getResult(), this);
                    rcv_studentlist.setAdapter(adapter_studentList);
                    // dòng kẻ của rcv
                    RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
                    rcv_studentlist.addItemDecoration(itemDecoration);
                }
            });
        } else {
            // nhân du liệu tù searchfragmnet
            ten = getArguments().getString("ten");
            student_viewModel.Studentlist(ten).observe(getActivity(), student_model -> {
                if (student_model.isSuccess()) {
                    adapter_studentList = new Adapter_StudentList(getContext(), student_model.getResult(), this);
                    rcv_studentlist.setAdapter(adapter_studentList);
                    // dòng kẻ của rcv
                    RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
                    rcv_studentlist.addItemDecoration(itemDecoration);
                }
            });
        }

    }

    private void addevenst() {
        float_add_pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                v = inflater.inflate(R.layout.cumtom_dialog, null);
                builder.setView(v);
                // hiển thị dialog
                alertDialog = builder.create();
                alertDialog.setCancelable(false);// ko cho user thoát
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// bo góc
                alertDialog.show();
                // ánh xạ view dialog
                spinner_gt = v.findViewById(R.id.spinner_gt);
                spinner_cnganh = v.findViewById(R.id.spinner_cnganh);
                ed_name = v.findViewById(R.id.ed_name);
                ed_ma = v.findViewById(R.id.ed_mac);
                ed_lick = v.findViewById(R.id.ed_link);
                btn_add = v.findViewById(R.id.btn_addc);
                btn_huy = v.findViewById(R.id.btn_huyc);
                img_cam = v.findViewById(R.id.img_cams);
                tv_class = v.findViewById(R.id.tv_classadd);
                // lam lại đat điêu kiệnt
                if (Utils.user.getRole() == 3){
                    if (tenlop != null){
                        tv_class.setText("Lớp: " + tenlop);
                    }else {
                        tv_class.setText("Lớp: " + ten);
                    }

                    tv_nganh = v.findViewById(R.id.tv_nganhadd);
                    if (tennganh != null){
                        tv_nganh.setText("Ngành: " + tennganh);
                    }else {
                        tv_nganh.setText("Ngành: " + tenganhk);
                    }
                }else {
                    return;
                }


                // đổ dữ liệu cho Spinner
                LoadSpinnergt();
                spinner_gt.setAdapter(adapter);
                cnganh();
                // xử lý sự kiện Spinner
                addevenstspiner();
            }
        });

        // seachiview
        ed_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SearchFragment();
                Bundle bundle = new Bundle();
                if (Utils.user.getRole() == 3){
                    if (tenlop != null) {
                        bundle.putString("tenlop", tenlop);
                    } else {
                        bundle.putString("tenlop", ten);
                    }
                    if (manganh != null){
                        bundle.putString("manganh",manganh);
                    }else {
                        bundle.putString("manganh",manganhk);
                    }
                    if (tennganh != null){
                        bundle.putString("tennganh",tennganh);
                    }else {
                        bundle.putString("tennganh",tenganhk);
                    }
                }else {
                    if (tenlopg != null){
                        bundle.putString("tenlop",tenlopg);
                    }else {
                        bundle.putString("tenlop",tenk);
                    }

                }

                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flcontent, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void addevenstspiner() {
        spinner_cnganh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Majors selectedMajor = (Majors) parent.getItemAtPosition(position);
                chuyennganh = selectedMajor.getChuyennganh();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_gt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loaigt = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // thêm
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Onclickadd();
            }
        });
        // btn hủy
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        img_cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenFile();
            }
        });
    }

    // mở file
    private void OpenFile() {
        ImagePicker.with(StudentListFragment.this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start();
    }

    public String GetPath(Uri uri) {
        String result;
        Cursor cursor = getContext().getContentResolver().query(uri, null, null, null);
        if (cursor == null) {
            result = uri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(index);
            cursor.close();
        }
        return result;
    }

    // file
    public void File() {
        Uri uri = Uri.parse(mediaPath);
        File file = new File(GetPath(uri));
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

        Student_ViewModel student_ViewModel = new ViewModelProvider(StudentListFragment.this).get(Student_ViewModel.class);
        student_ViewModel.Uploadimg(filePart).observe(StudentListFragment.this, addProductNew_viewModel -> {
            if (addProductNew_viewModel.isSuccess()) {
                Log.e("name", addProductNew_viewModel.getName());
                ed_lick.setText(addProductNew_viewModel.getName());
            } else {
                Toast.makeText(getContext(), addProductNew_viewModel.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mediaPath = data.getDataString();
        File();
        Log.e("onActivityResult", mediaPath);
    }

    /// thêm
    private void Onclickadd() {
        String ma = ed_ma.getText().toString().trim();
        String name = ed_name.getText().toString().trim();
        String link = ed_lick.getText().toString().trim();
        if (ma.equals("") || name.equals("") || link.equals("") || loaigt == 0) {
            Toast.makeText(getContext(), "Yêu câu nhập đủ", Toast.LENGTH_SHORT).show();
        } else {
            int gt = 0;
            if (loaigt == 1) {
                gt = 1;
            } else if (loaigt == 2) {
                gt = 0;
            }
            //
            Schedule_ViewModel schedule_viewModel = new ViewModelProvider(getActivity()).get(Schedule_ViewModel.class);
            if (tenlop != null && tennganh != null){
                schedule_viewModel.insertsv(ma, name, gt, tenlop, tennganh, chuyennganh, link).observe(getActivity(), student_model -> {
                    if (student_model.isSuccess()) {
                        Loadlist(tenlop);
                        alertDialog.dismiss();
                        Toast.makeText(getContext(), "Thêm thanh công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
            }else {
                schedule_viewModel.insertsv(ma, name, gt, ten, tenganhk, chuyennganh, link).observe(getActivity(),
                        student_model -> {
                    if (student_model.isSuccess()) {
                        Loadlist(tenlop);
                        alertDialog.dismiss();
                        Toast.makeText(getContext(), "Thêm thanh công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
            }


        }
    }

    // giới tính
    private void LoadSpinnergt() {
        ArrayList<String> listgt = new ArrayList<>();
        listgt.add("Chọn giới tính");
        listgt.add("Nam");
        listgt.add("Nữ");
        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, listgt);

    }

    // chuyên ngành
    private void cnganh() {
        if (manganh != null ){
            Industry_ViewModel industry_viewModel = new ViewModelProvider(getActivity()).get(Industry_ViewModel.class);
            industry_viewModel.chnganh(manganh).observe(getActivity(), majors_model -> {
                adapterSpinner_cnganh = new AdapterSpinner_cnganh(getContext(), majors_model.getResult());
                if (majors_model.isSuccess()) {
                    spinner_cnganh.setAdapter(adapterSpinner_cnganh);
                } else {
                    Log.e("spinner_cnganh", "null");
                }
            });
        }else {
            Industry_ViewModel industry_viewModel = new ViewModelProvider(getActivity()).get(Industry_ViewModel.class);
            industry_viewModel.chnganh(manganhk).observe(getActivity(), majors_model -> {
                adapterSpinner_cnganh = new AdapterSpinner_cnganh(getContext(), majors_model.getResult());
                if (majors_model.isSuccess()) {
                    spinner_cnganh.setAdapter(adapterSpinner_cnganh);
                } else {
                    Log.e("spinner_cnganh", "null");
                }
            });
        }

    }

    // chuyên lớp spiner
    private void lop() {
        ClassList_ViewModel classList_viewModel = new ViewModelProvider(getActivity()).get(ClassList_ViewModel.class);
        classList_viewModel.getclass(manganh).observe(getActivity(), class_model -> {
            AdapterSpinner_Class adapterSpinner_class = new AdapterSpinner_Class(getContext(), class_model.getResult());
            if (class_model.isSuccess()) {
                lop.setAdapter(adapterSpinner_class);
            } else {
                Log.e("spinner_cnganh", "null");
            }
        });
    }

    private void getfullnganh(){
        Majors_ViewModel majors_viewModel = new ViewModelProvider(getActivity()).get(Majors_ViewModel.class);
        majors_viewModel.gettennganh().observe(getActivity(), majors_model -> {
            Adapter_Fullnganh adapter_fullnganh = new Adapter_Fullnganh(getContext(),majors_model.getResult());
            if (majors_model.isSuccess()){
                nganh.setAdapter(adapter_fullnganh);
            }else {
                Log.e("getfullnganh", "null");
            }
        });
    }

    // sửa
    @Override
    public void onEditClicked(Student student) {
        // set dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_update, null);
        builder.setView(v);
        // hiển thị dialog
        alertDialog = builder.create();
        alertDialog.setCancelable(false);// ko cho user thoát
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// bo góc
        alertDialog.show();

        // anh xạ vie
        Button btn_huy = v.findViewById(R.id.btn_huyu);
        Button btn_update = v.findViewById(R.id.btn_update);
        EditText ed_mssvu = v.findViewById(R.id.ed_mssv);
        EditText ed_tenu = v.findViewById(R.id.ed_ten);
        ImageView img_avata = v.findViewById(R.id.img_avatau);
        Spinner gt = v.findViewById(R.id.gt);
        lop = v.findViewById(R.id.lop);
        nganh = v.findViewById(R.id.nganh);
        spinnercn = v.findViewById(R.id.chuyennganh);
        // loadlist psinner
        LoadSpinnergt();
        gt.setAdapter(adapter);
        // fullngành
        getfullnganh();


        // xử ly hoạt động
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        spinnercn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Majors selectedMajor = (Majors) parent.getItemAtPosition(position);
                chuyennganh = selectedMajor.getChuyennganh();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        gt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loaigt = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // lây tên ngành
        //String =null ;
        nganh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Majors selectedMajor = (Majors) parent.getItemAtPosition(position);
                manganh = selectedMajor.getManganh();
                nganhpinner = selectedMajor.getTennganh();
                // khi chọn ngành sẽ load lại chuyên ngành
                // chuyên ngành
                Industry_ViewModel industry_viewModel = new ViewModelProvider(getActivity()).get(Industry_ViewModel.class);
                industry_viewModel.chnganh(manganh).observe(getActivity(), majors_model -> {
                    adapterSpinner_cnganh = new AdapterSpinner_cnganh(getContext(), majors_model.getResult());
                    if (majors_model.isSuccess()) {
                        spinnercn.setAdapter(adapterSpinner_cnganh);
                    } else {
                        Log.e("spinner_cnganh", "null");
                    }
                });                     
                lop();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Class aClass = (Class) parent.getItemAtPosition(position);
                loppiner = aClass.getTenlop();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        ed_mssvu.setText(""+student.getMa());
        ed_tenu.setText(student.getName());
        if (student.getHinhanh().contains("http")){
            Glide.with(getContext()).load(student.getHinhanh()).into(img_avata);
        }else {
            String hinhanh = Utils.BASE+"images/"+student.getHinhanh();
            Glide.with(getContext()).load(hinhanh).into(img_avata);
        }

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int gt = 0;
                if (loaigt == 1) {
                    gt = 1;
                } else if (loaigt == 2) {
                    gt = 0;
                }
                String ma = ed_mssvu.getText().toString().trim();
                String ten = ed_tenu.getText().toString().trim();
                Student_ViewModel student_viewModel = new ViewModelProvider(getActivity()).get(Student_ViewModel.class);
                student_viewModel.update(student.getId(),ma,ten,gt,loppiner,nganhpinner,chuyennganh,student.getHinhanh()).observe(
                        getActivity(), student_model -> {
                            if (student_model.isSuccess()){
                                Toast.makeText(getContext(),"thanh công",Toast.LENGTH_SHORT);
                                alertDialog.dismiss();
                                Loadlist(tenlop);
                            }else {
                                Toast.makeText(getContext(),"thât bại",Toast.LENGTH_SHORT);
                            }
                        }
                );
            }
        });
    }

    // xóa
    @Override
    public void onDeleteClicked(Student student) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setTitle("Bạn có muốn xóa ?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Log.e("id",String.valueOf(student.getId()));

                Student_ViewModel student_viewModel = new ViewModelProvider(getActivity()).get(Student_ViewModel.class);
                student_viewModel.delete(student.getId()).observe(getActivity(), student_model -> {
                    if (student_model.isSuccess()) {
                        Loadlist(tenlop);
                        Toast.makeText(getContext(), "Xoa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "thât bại", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        builder.setNegativeButton("Hủy", null);
        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



}