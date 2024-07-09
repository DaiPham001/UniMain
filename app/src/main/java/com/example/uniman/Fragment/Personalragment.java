package com.example.uniman.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.uniman.R;
import com.example.uniman.Utils.Utils;
import com.example.uniman.ViewModel.Student_ViewModel;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class Personalragment extends Fragment {

    private TextView tv_mssv, tv_ten, tv_sex, tv_lop, tv_nganh, tv_cnganh;
    private Student_ViewModel student_viewModel;
    private ImageView img_avata, img_cam;

    //
    String mediaPath;
    String urlavata;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_personalragment, container, false);
        addcontroll(view);
        addevenst();
        return view;
    }


    @SuppressLint("FragmentLiveDataObserve")
    private void addcontroll(View view) {
        tv_cnganh = view.findViewById(R.id.tv_cnganh);
        tv_ten = view.findViewById(R.id.tv_ten);
        tv_sex = view.findViewById(R.id.tv_sex);
        tv_lop = view.findViewById(R.id.tv_lop);
        tv_nganh = view.findViewById(R.id.tv_nganh);
        tv_mssv = view.findViewById(R.id.tv_mssv);
        img_avata = view.findViewById(R.id.img_avata);
        img_cam = view.findViewById(R.id.img_cam);
        //

        student_viewModel = new ViewModelProvider(Personalragment.this).get(Student_ViewModel.class);
        student_viewModel.Student(Utils.user.getMa()).observe(Personalragment.this, student_model -> {
            if (student_model.isSuccess()) {
                for (int i = 0; i < student_model.getResult().size(); i++) {
                    tv_cnganh.setText("Chuyên Ngành: " + student_model.getResult().get(i).getChuyennganh());
                    tv_ten.setText("Tên: " + student_model.getResult().get(i).getName());
                    if (student_model.getResult().get(i).getGioitinh() == 1) {
                        tv_sex.setText("Giới Tính: Nam");
                    } else if (student_model.getResult().get(i).getGioitinh() == 0) {
                        tv_sex.setText("Giới Tính: Nữ");
                    }
                    tv_lop.setText("Lớp: " + student_model.getResult().get(i).getLop());
                    tv_nganh.setText("Ngành: " + student_model.getResult().get(i).getNganh());
                    if (student_model.getResult().get(i).getHinhanh() != null) {
                        if (student_model.getResult().get(i).getHinhanh().contains("http")) {
                            Glide.with(getContext()).load(student_model.getResult().get(i).getHinhanh()).into(img_avata);
                        } else {
                            String hinhanh = Utils.BASE + "images/" + (student_model.getResult().get(i).getHinhanh());
                            Glide.with(getContext()).load(hinhanh).into(img_avata);
                        }

                    } else {
                        Glide.with(getContext()).load(R.drawable.th).into(img_avata);
                    }

                }
                tv_mssv.setText("Mã Sin Viên: " + Utils.user.getMa());
            } else {
                Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addevenst() {
        onClickImgCam();

    }

    private void onClickImgCam() {
        img_cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenFile();

            }
        });
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void Updateavata() {
        student_viewModel = new ViewModelProvider(Personalragment.this).get(Student_ViewModel.class);
        student_viewModel.Updateavata(Utils.user.getMa(), urlavata).observe(Personalragment.this, uploadingavata_model -> {
           if (uploadingavata_model.isSuccess()){
               if (urlavata == null) {
                   return;
               } else {
                   Log.e("url", urlavata);
               }
           }else {
               Toast.makeText(getContext(), "thất bại", Toast.LENGTH_SHORT).show();
           }

        });
    }


    // mở file
    private void OpenFile() {
        ImagePicker.with(Personalragment.this)
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mediaPath = data.getDataString();
        File();
        Log.e("onActivityResult", mediaPath);

    }

    // file
    public void File() {
        Uri uri = Uri.parse(mediaPath);
        File file = new File(GetPath(uri));
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

        student_viewModel = new ViewModelProvider(Personalragment.this).get(Student_ViewModel.class);
        student_viewModel.Uploadimg(filePart).observe(Personalragment.this, uploadingavata_model -> {
            if (uploadingavata_model.isSuccess()) {
                urlavata = uploadingavata_model.getName();
                Log.e("img", uploadingavata_model.getName());
                Updateavata();
            }
        });
    }
}