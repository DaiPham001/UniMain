package com.example.uniman.Retrofit;

import com.example.uniman.Model.Class_Model;
import com.example.uniman.Model.Majors;
import com.example.uniman.Model.Majors_Model;
import com.example.uniman.Model.Notifi_Model;
import com.example.uniman.Model.Results_Model;
import com.example.uniman.Model.Retest_Model;
import com.example.uniman.Model.Schedule_Model;
import com.example.uniman.Model.Semester_Model;
import com.example.uniman.Model.Student_Model;
import com.example.uniman.Model.Teacher_Model;
import com.example.uniman.Model.Uploadingavata_Model;
import com.example.uniman.Model.User_Model;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface API {

    // get data
    // get data
    @GET("industrylist.php")
    Call<Majors_Model> getindustry();

    //
    @POST("login.php")
    @FormUrlEncoded
    Call<User_Model> login(
            @Field("ma") String ma,
            @Field("password") String password
    );

    @POST("register.php")
    @FormUrlEncoded
    Call<User_Model> registter(
            @Field("ma") String ma,
            @Field("password") String password,
            @Field("role") int role,
            @Field("uid") String uid

    );

    // them token
    @POST("themtoken.php")
    @FormUrlEncoded
    Call<User_Model> themtoken(
            @Field("id") int id,
            @Field("token") String token
    );

    @POST("change.php")
    @FormUrlEncoded
    Call<User_Model> change(
            @Field("ma") String ma,
            @Field("password") String password
    );

    @POST("student.php")
    @FormUrlEncoded
    Call<Student_Model> student(
            @Field("ma") String ma
    );

    // lịch học
    @POST("schedule.php")
    @FormUrlEncoded
    Call<Schedule_Model> schedule(
            @Field("ma") String ma,
            @Field("thu") String thu
    );
    // lịch giảng gv
    @POST("lectureschedule.php")
    @FormUrlEncoded
    Call<Schedule_Model> lectureschedule(
            @Field("mgv") String mgv,
            @Field("thu") String thu
    );

    // update trangthai lịch
    @POST("updateschedule_tt.php")
    @FormUrlEncoded
    Call<Schedule_Model> updateschedule(
            @Field("mgv") String mgv,
            @Field("id") int id,
            @Field("trangthai") int trangthai
    );
    @POST("classlist.php")
    @FormUrlEncoded
    Call<Class_Model> classlist(
            @Field("manganh") String manganh
    );

    @POST("studentlist.php")
    @FormUrlEncoded
    Call<Student_Model> studentlist(
            @Field("lop") String lop
    );
    @Multipart
    @POST("uploadimg.php")
    Call <Uploadingavata_Model> uploadimg(@Part MultipartBody.Part file);

    @POST("updateavata.php")
    @FormUrlEncoded
    Call<Uploadingavata_Model> updateavata(
            @Field("ma") String ma,
            @Field("hinhanh") String hinhanh
    );

    // thêm sinhvien
    @POST("insertstudent.php")
    @FormUrlEncoded
    Call<Uploadingavata_Model> insertsv(
            @Field("ma") String ma,
            @Field("name") String name,
            @Field("gioitinh") int gioitinh,
            @Field("lop") String lop,
            @Field("nganh") String nganh,
            @Field("chuyennganh") String chuyennganh,
            @Field("hinhanh") String hinhanh
    );

    // update sinhvien
    @POST("updatestudent.php")
    @FormUrlEncoded
    Call<Uploadingavata_Model> updatesv(
            @Field("id") int id,
            @Field("ma") String ma,
            @Field("name") String name,
            @Field("gioitinh") int gioitinh,
            @Field("lop") String lop,
            @Field("nganh") String nganh,
            @Field("chuyennganh") String chuyennganh,
            @Field("hinhanh") String hinhanh
    );
    // xoa sinh viên

    @POST("deletestudent.php")
    @FormUrlEncoded
    Call<Uploadingavata_Model> delete(
            @Field("id") int id
    );

    // spinner
    @POST("getchuyennganh.php")
    @FormUrlEncoded
    Call<Majors_Model> getchnganh(
            @Field("manganh") String manganh
    );

    // get lop
    @POST("getclass.php")
    @FormUrlEncoded
    Call<Class_Model> getclass(
            @Field("manganh") String manganh
    );

    // get tên nganh
    @GET("gettennganh.php")
    Call<Majors_Model> gettennganh();

    //tìm kiêm
    @POST("search.php")
    @FormUrlEncoded
    Call<Student_Model> search(
            @Field("search") String search,
            @Field("lop") String lop
    );

    // get list class giang viên dạy
    @POST("getlistclassgv.php")
    @FormUrlEncoded
    Call<Schedule_Model> getclassgv(
            @Field("mgv") String mgv
    );

    // lây thông tin cua giang viên
    @POST("getteacher.php")
    @FormUrlEncoded
    Call<Teacher_Model> getteacher(
            @Field("ma") String ma
    );

    // lây thông Results
    @POST("getresults.php")
    @FormUrlEncoded
    Call<Results_Model> getresults(
            @Field("msv") String msv
    );

    //hiển thị Results
    @POST("hienthikq.php")
    @FormUrlEncoded
    Call<Results_Model> hienthikq(
            @Field("mgv") String mgv,
            @Field("lop") String lop
    );
    // cập nhật Results
    @POST("nhapkq.php")
    @FormUrlEncoded
    Call<Results_Model> nhapkq(
            @Field("msv") String msv,
            @Field("mamh") String mamh,
            @Field("diemcc") float diemcc,
            @Field("diemhs1") float diemhs1,
            @Field("diemhs2") float diemhs2,
            @Field("diemhs3") float diemhs3,
            @Field("img") int img,
            @Field("diemck1") float diemck1,
            @Field("diemthi") float diemthi,
            @Field("tongdiem") float tongdiem,
            @Field("td4") float td4,
            @Field("diemchu") String diemchu,
            @Field("xeploai") String xeploai,
            @Field("ghichu") String ghichu
    );

    // hiển thị cac môn thi lại
    @POST("getthilai.php")
    @FormUrlEncoded
    Call<Retest_Model> getthilai(
            @Field("msv") String msv
    );

    // hiển thị cac môn thi cải thiện
    @POST("getthicaithien.php")
    @FormUrlEncoded
    Call<Retest_Model> getthicaithien(
            @Field("msv") String msv
    );

    // admin
    // thêm cac môn đa đang ky thi lại và cải thiện khi giao viên nhập điểm
    @POST("themthilaigv.php")
    @FormUrlEncoded
    Call<Retest_Model> themthilai(
            @Field("msv") String msv,
            @Field("mamh") String mamh,
            @Field("lophp") String lophp,
            @Field("tenmh") String tenmh,
            @Field("tc") int tc,
            @Field("diemck1") String diemck1,
            @Field("ghichu") String ghichu
    );

    // thêm cac môn đa đang ky thi lại và cải thiện khi sinh viên đang ky
    @POST("themthilaisv.php")
    @FormUrlEncoded
    Call<Retest_Model> themthilaisv(
            @Field("msv") String msv,
            @Field("lophp") String lophp,
            @Field("tenmh") String tenmh,
            @Field("tc") int tc,
            @Field("tienthi") float diemck1,
            @Field("ngaydangky") String ngaydangky
    );
    // hiển thị cac môn đa đang ky thi lại và cải thiện
    @POST("getcacmonthilai.php")
    @FormUrlEncoded
    Call<Retest_Model> getmonthilai(
            @Field("msv") String msv
    );

    // hiển thị cac môn admin theo ngành
    @POST("getmonadmin.php")
    @FormUrlEncoded
    Call<Semester_Model> getmonadmin(
            @Field("khoa") int khoa,
            @Field("namhoc") String namhoc,
            @Field("hocki") String hocki
    );

    // thêm cac môn admin theo ngành
    @POST("themmonadmin.php")
    @FormUrlEncoded
    Call<Semester_Model> themmonadmin(
            @Field("khoa") int khoa,
            @Field("manganh") String manganh,
            @Field("namhoc") String namhoc,
            @Field("hocki") String hocki,
            @Field("mamh") String mamh,
            @Field("tenmh") String tenmh,
            @Field("tengv") String tengv,
            @Field("tinchi") int tinchi,
            @Field("hocphi") double hocphi

    );

    // update môn admin
    @POST("updatemonadmin.php")
    @FormUrlEncoded
    Call<Semester_Model> updatemonadmin(
            @Field("id") int id,
            @Field("khoa") int khoa,
            @Field("manganh") String manganh,
            @Field("namhoc") String namhoc,
            @Field("hocki") String hocki,
            @Field("mamh") String mamh,
            @Field("tenmh") String tenmh,
            @Field("tengv") String tengv,
            @Field("tinchi") int tinchi,
            @Field("hocphi") double hocphi
    );
    // hiển thị nam học kì theo khoa nam hoc
    @POST("getkhoahoc.php")
    @FormUrlEncoded
    Call<Semester_Model> getkhoahoc(
            @Field("manganh") String manganh
    );

    // hiển thị spinner năm học
    @POST("getnamhoc.php")
    @FormUrlEncoded
    Call<Semester_Model> getnamhoc(
            @Field("khoa") int khoa
    );
    ////////////////////

    //user
    // hiển thị cac môn chua đang ky
    @POST("getmonchuadangky.php")
    @FormUrlEncoded
    Call<Semester_Model> getmonchuadangky(
            @Field("khoa") int khoa,
            @Field("namhoc") String namhoc,
            @Field("hocki") String hocki
    );


    // them mon user  cac môn đang ky
    @POST("themmonuser.php")
    @FormUrlEncoded
    Call<Semester_Model>themmonuser(
            @Field("masv") String masv,
            @Field("khoa") int khoa,
            @Field("manganh") String manganh,
            @Field("namhoc") String namhoc,
            @Field("hocki") String hocki,
            @Field("mamh") String mamh,
            @Field("tenmh") String tenmh,
            @Field("lop") String lop,
            @Field("tinchi") int tinchi,
            @Field("tongtc") int tongtc,
            @Field("hocphi") double hocphi,
            @Field("trangthai") int trangthai,
            @Field("ngaydk") String ngaydk
    );

    // hiển thị cac môn da đang ky
    @POST("getmonhocdadangky.php")
    @FormUrlEncoded
    Call<Semester_Model> getmondadangky(
            @Field("masv") String masv,
            @Field("khoa") int khoa,
            @Field("namhoc") String namhoc,
            @Field("hocki") String hocki
    );

    // hiển ten giang vien spinner
    @POST("gettengvtheomkhoa.php")
    @FormUrlEncoded
    Call<Teacher_Model> gettengvtheomkhoa(
            @Field("makhoa") String makhoa
    );

    // thêm ngành
    @POST("themnganh.php")
    @FormUrlEncoded
    Call<Majors_Model> themnganh(
            @Field("manganh") String manganh,
            @Field("tennganh") String tennganh,
            @Field("chuyennganh") String chuyennganh
    );

    // update ngành
    @POST("updatenganh.php")
    @FormUrlEncoded
    Call<Majors_Model> updatenganh(
            @Field("id") int id,
            @Field("manganh") String manganh,
            @Field("tennganh") String tennganh
    );

    // notification
    @GET("getnotifi.php")
    Call<Notifi_Model> getnotifi();

    // themnotifi
    @POST("themnotifi.php")
    @FormUrlEncoded
    Call<Notifi_Model> themnotifi(
            @Field("title") String title,
            @Field("body") String body);

    // getavachat
    @POST("getavatachat.php")
    @FormUrlEncoded
    Call<Student_Model> getavachat(
            @Field("ma") String ma);
}
