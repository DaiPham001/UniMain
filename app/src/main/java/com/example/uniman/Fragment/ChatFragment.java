package com.example.uniman.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.uniman.Adapter.Adapter_Chat;
import com.example.uniman.Model.Chat;
import com.example.uniman.R;
import com.example.uniman.Utils.Utils;
import com.example.uniman.ViewModel.Chat_ViewModel;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


public class ChatFragment extends Fragment {

    private RecyclerView rcv_chat;
    private EditText ed_chat;
    private ImageView img_send;
    private FirebaseFirestore db;
    private Adapter_Chat adapter_chat;
    private ArrayList<Chat> list;
    private String hinhanh,chat,id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_chat, container, false);
        addcontroll(view);
        addevenst();
        return view;
    }

    private void addcontroll(View v) {
        db = FirebaseFirestore.getInstance();
        rcv_chat = v.findViewById(R.id.rcv_chat);

        img_send = v.findViewById(R.id.img_send);
        ed_chat = v.findViewById(R.id.ed_chat);
        list = new ArrayList<>();
        //
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_chat.setLayoutManager(linearLayoutManager);
        adapter_chat = new Adapter_Chat(getContext(), list, String.valueOf(Utils.user.getId()));
        rcv_chat.setAdapter(adapter_chat);

        // user
        if (Utils.user.getRole() == 1 || Utils.user.getRole() == 2){
            Chat_ViewModel chat_viewModel = new ViewModelProvider(getActivity()).get(Chat_ViewModel.class);
            chat_viewModel.getavatachat(Utils.user.getMa()).observe(getActivity(), student_model -> {
                if (student_model.isSuccess()) {
                    for (int i = 0; i < student_model.getResult().size(); i++) {
                        hinhanh = student_model.getResult().get(i).getHinhanh();
                        //Log.e("hinhanh", hinhanh);
                    }

                }
            });
            loadList_user();
        }else {// admin
            // nhan id tu ChatAdminfragment
             id = getArguments().getString("id");
             Log.e("id",String.valueOf(id));
            loadList_user();
        }
    }

    private void insertUser() {
        HashMap<String, Object> user = new HashMap<>();
        user.put("id", Utils.user.getId());
        if (Utils.user.getRole() == 2) {
            user.put("name", Utils.teacher.getTengv());
        } else if (Utils.user.getRole() == 1){
            user.put("name", Utils.user.getName());
        }else {
            user.put("name", "Admin");
        }
        user.put("role", Utils.user.getRole());
        user.put("ma", Utils.user.getMa());
        user.put("hinhanh", hinhanh);
        user.put("chat", chat);
        db.collection("user").document(String.valueOf(Utils.user.getId())).set(user);
    }

    //
    private void addevenst() {
        img_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chat = ed_chat.getText().toString().trim();
                if (!chat.equals("")) {
                    HashMap<String, Object> message = new HashMap<>();
                    if (Utils.user.getRole() == 1 || Utils.user.getRole() == 2){
                        //Log.e("id", String.valueOf(Utils.user.getId()));
                        message.put(Utils.key_send, String.valueOf(Utils.user.getId()));
                        message.put(Utils.key_received, Utils.RECEIVEDI);
                        message.put(Utils.key_message, chat);
                        message.put(Utils.key_date, new Date());
                        db.collection(Utils.key_path).add(message);
                        insertUser();
                    }else {
                        message.put(Utils.key_send, String.valueOf(Utils.user.getId()));
                        message.put(Utils.key_received, id);
                        message.put(Utils.key_message, chat);
                        message.put(Utils.key_date, new Date());
                        db.collection(Utils.key_path).add(message);
                        insertUser();
                    }
                    ed_chat.setText("");
                } else {
                    //Toast.makeText(getContext(), "ban chua nhap noi dung", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    private void loadList_user() {
        // nguoi dung
        if (Utils.user.getRole() == 1 || Utils.user.getRole() == 2){
            db.collection(Utils.key_path)
                    .whereEqualTo(Utils.key_send, String.valueOf(Utils.user.getId()))
                    .whereEqualTo(Utils.key_received, Utils.RECEIVEDI)
                    .addSnapshotListener(eventListener);

            db.collection(Utils.key_path)
                    .whereEqualTo(Utils.key_send, Utils.RECEIVEDI)
                    .whereEqualTo(Utils.key_received, String.valueOf(Utils.user.getId()))
                    .addSnapshotListener(eventListener);
        }else {// admin
            db.collection(Utils.key_path)
                    .whereEqualTo(Utils.key_send, String.valueOf(Utils.user.getId()))
                    .whereEqualTo(Utils.key_received, id)
                    .addSnapshotListener(eventListener);

            db.collection(Utils.key_path)
                    .whereEqualTo(Utils.key_send, id)
                    .whereEqualTo(Utils.key_received, String.valueOf(Utils.user.getId()))
                    .addSnapshotListener(eventListener);
        }

    }

    private final EventListener<QuerySnapshot> eventListener = (value, error) -> {
        if (error != null) {
            Log.e("loi", "");
        }
        if (value != null) {
            int count = list.size();
            for (DocumentChange documentChange : value.getDocumentChanges()) {
                if (documentChange.getType() == DocumentChange.Type.ADDED) ;
                Chat chat = new Chat();
                chat.send = documentChange.getDocument().getString(Utils.key_send);
                chat.received = documentChange.getDocument().getString(Utils.key_received);
                chat.mess = documentChange.getDocument().getString(Utils.key_message);
                chat.dateObj = documentChange.getDocument().getDate(Utils.key_date);
                chat.datetime = fomat_data(documentChange.getDocument().getDate(Utils.key_date));
                list.add(chat);
            }
            Collections.sort(list, (ojb1, ojb2) -> ojb1.dateObj.compareTo(ojb2.dateObj));
            if (count == 0) {
                adapter_chat.notifyDataSetChanged();
            } else {
                adapter_chat.notifyItemChanged(list.size(), list.size());
                rcv_chat.smoothScrollToPosition(list.size() - 1);
            }
        }
    };

    public String fomat_data(Date date) {
        return new SimpleDateFormat("MMMM dd-hh:mm a", Locale.getDefault()).format(date);
    }
}