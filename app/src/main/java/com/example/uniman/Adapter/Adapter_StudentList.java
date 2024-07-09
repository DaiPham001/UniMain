package com.example.uniman.Adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uniman.Model.Student;
import com.example.uniman.R;
import com.example.uniman.Utils.Utils;

import java.util.ArrayList;

public class Adapter_StudentList extends RecyclerView.Adapter<Adapter_StudentList.Studenlist_ViewHolder> {


    // Khai báo interface
    public interface OnItemClickListener {
        void onEditClicked(Student student);
        void onDeleteClicked(Student student);
    }

    private Context context;
    private ArrayList<Student> list;
    private OnItemClickListener listener;

    public Adapter_StudentList(Context context, ArrayList<Student> list ) {
        this.context = context;
        this.list = list;
    }

    public Adapter_StudentList(Context context, ArrayList<Student> list,OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Studenlist_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_studentlist,parent,false);
        return new Studenlist_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Studenlist_ViewHolder holder, int position) {
        Student student = list.get(position);
        holder.tv_student.setText(student.getName());
        holder.layout_studenl.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                if (Utils.user.getRole() == 3){
                    menu.add(0, 0, getItemCount(), "Sửa").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            // Xử lý sự kiện khi người dùng chọn "Sửa"
                            // Ví dụ: mở màn hình chỉnh sửa thông tin sinh viên
                            listener.onEditClicked(student);
                            return true;
                        }
                    });
                    menu.add(0, 1, getItemCount(), "Xóa").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            // Xử lý sự kiện khi người dùng chọn "Xóa"
                            // Ví dụ: gọi phương thức để xóa sinh viên
                            listener.onDeleteClicked(student);
                            return true;
                        }
                    });
                }else {
                   return;
                }

            }
        });

    }



    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class Studenlist_ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_student;
        private LinearLayout layout_studenl;
        public Studenlist_ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_student = itemView.findViewById(R.id.tv_studenl);
            layout_studenl = itemView.findViewById(R.id.layout_studenl);
        }
    }
}
