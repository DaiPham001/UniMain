package com.example.uniman.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.uniman.Model.NotisenData;
import com.example.uniman.R;
import com.example.uniman.Retrofit.APINotifcation;
import com.example.uniman.Retrofit.RetrofitNotification;
import com.example.uniman.Utils.Utils;
import com.example.uniman.ViewModel.Noti_ViewModel;
import com.example.uniman.ViewModel.Teacher_ViewModel;
import com.example.uniman.ViewModel.User_ViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText ed_ma, ed_pass;
    private Button btn_login;
    private CheckBox checkBox;
    boolean check;
    private User_ViewModel user_viewModel;
    //
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addcontroll();
        adevenst();
        //CheckInstructor();
    }
    // ánh xạ view
    private void addcontroll() {
        ed_ma = findViewById(R.id.ed_ma);
        ed_pass = findViewById(R.id.ed_pass);
        btn_login = findViewById(R.id.btn_login);
        checkBox =findViewById(R.id.checkbox);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        // Khởi tạo ProgressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang đăng nhập...");
        progressDialog.setCancelable(false);
        //
       // sharedPreferences = LoginActivity.this.getSharedPreferences("login",LoginActivity.this.MODE_PRIVATE);
        // checkbox login

    }

    // xử lý hoạt động
    private void adevenst() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma = ed_ma.getText().toString().trim();
                String pass = ed_pass.getText().toString().trim();
                if (pass.equals("") || ma.equals("")) {
                    Toast.makeText(LoginActivity.this, "Nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    login(ma,pass);
                }
            }
        });

    }



    private void gettoken(){
        FirebaseMessaging.getInstance().getToken()
                .addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String a) {
                        if (!TextUtils.isEmpty(a)){
                            User_ViewModel user_viewModel =new ViewModelProvider(LoginActivity.this).get(User_ViewModel.class);
                            user_viewModel.themtoken(Utils.user.getId(),a).observe(LoginActivity.this,user_model ->{
                                if (user_model.isSuccess()){
//                                    Log.e("token",a);
//                                    Log.e("id",String.valueOf(Utils.user.getId()));
                                }else {
                                    Toast.makeText(LoginActivity.this, "Them token that bai", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
    }

    private void login(String ma, String pass){
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(ma, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    user_viewModel = new ViewModelProvider(LoginActivity.this).get(User_ViewModel.class);
                    user_viewModel.Login(ma, pass).observe(LoginActivity.this, user_model -> {
                        if (user_model.isSuccess()) {
                            Utils.user = user_model.getResult().get(0);
                           // Log.e("token",Utils.user.getToken());
                            //Log.e("role", String.valueOf(Utils.user.getRole()));
                            Toast.makeText(LoginActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            gettoken();
                            CheckInstructor();
                            finishAffinity();
                        } else {
                            Toast.makeText(LoginActivity.this, "Kiểm tra lại tài khoản mật khẩu", Toast.LENGTH_SHORT).show();
                        }
                    });
                    savepass();
                } else {
                    Exception exception = task.getException();
                    if (exception != null) {
                        Log.e("LoginError", "Error during login", exception);
                    }
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void savepass() {
        SharedPreferences sharedPreferences = getSharedPreferences("save", LoginActivity.this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String ma = String.valueOf(editor.putString("ma", ed_ma.getText().toString().trim()));
        String pass = String.valueOf(editor.putString("pass", ed_pass.getText().toString().trim()));
        editor.putBoolean("save1", checkBox.isChecked());// nếu checkbox đc chọn
        editor.commit();
       // login(ma, pass);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // hiển thị mật khẩu đã đc lưu
        SharedPreferences sharedPreferences = getSharedPreferences("save",LoginActivity.this.MODE_PRIVATE);
        String ma = sharedPreferences.getString("ma", "");
        String pass = sharedPreferences.getString("pass", "");
         check = sharedPreferences.getBoolean("save1", false);
        if (check == true) {
            ed_ma.setText(ma);
            ed_pass.setText(pass);
            login(ma,pass);
        }else {
                Log.e("checkbox","yeu cau dang nhap");
        }
       // registerReceiver(broadcastReceiver, intentFilter);
    }

    //
    private void CheckInstructor(){
        Teacher_ViewModel teacher_viewModel = new ViewModelProvider(this).get(Teacher_ViewModel.class);
        teacher_viewModel.getteacher(Utils.user.getMa()).observe(this, teacher_model -> {
            if (teacher_model.isSuccess()){
                for (int i=0;i<teacher_model.getResult().size();i++){
                    Utils.teacher = teacher_model.getResult().get(i);
                    Log.e("mamh",Utils.teacher.getMauser());
                }
                Log.e("mauser", String.valueOf(Utils.teacher.getMgv()));
            }
        });
    }
}