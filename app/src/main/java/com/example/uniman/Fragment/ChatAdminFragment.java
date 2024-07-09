package com.example.uniman.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.uniman.Adapter.Adapter_Adminchat;
import com.example.uniman.Model.User;
import com.example.uniman.R;
import com.example.uniman.Utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class ChatAdminFragment extends Fragment implements Adapter_Adminchat.Onclick{

    private RecyclerView rcv_chat;
    private RadioGroup radio_chat;
    private Adapter_Adminchat adapter_adminchat;
    private ArrayList<User> list = new ArrayList<>();
    private Fragment fragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_chat_admin,container,false);
      addcontroll(view);
      addevenst();
      return view;
    }

    private void addcontroll(View view) {
        radio_chat = view.findViewById(R.id.radio_chat);
        rcv_chat = view.findViewById(R.id.rcv_adchat);
        //
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_chat.setLayoutManager(linearLayoutManager);
    }

    private void loadList(final int role) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("user").whereEqualTo("role",role).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                User user = new User();
                                user.setId(documentSnapshot.getLong("id").intValue());
                                user.setName(documentSnapshot.getString("name"));
                                user.setMa(documentSnapshot.getString("ma"));
                                user.setHinhanh(documentSnapshot.getString("hinhanh"));
                                user.setChat(documentSnapshot.getString("chat"));
                                list.add(user);
                                if (Utils.user.getRole() == 3){
                                    handleAdminChat(user.getId(),user.getChat());
                                }
                            }
                            check();
                        }
                    }
                });

    }
    // A method to process the chat data for admin users
    private void handleAdminChat(int userId, String chat) {
        // Logic to process chat data for admin users
        // For example, you might want to log it or perform some operations
        Log.e("AdminChat", "User ID: " + userId + ", Chat: " + chat);
    }
//    private void loadList() {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("user").get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()){
//                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
//                                User user = new User();
//                                user.setId(documentSnapshot.getLong("id").intValue());
//                                user.setName(documentSnapshot.getString("name"));
//                                user.setMa(documentSnapshot.getString("ma"));
//                                user.setHinhanh(documentSnapshot.getString("hinhanh"));
//                                user.setChat(documentSnapshot.getString("chat"));
//                                list.add(user);
//                            }
//                            check();
//                        }
//                    }
//                });
//
//    }
    public void check(){
        if (list.size() > 0){
            adapter_adminchat = new Adapter_Adminchat(getContext(),list,this);
            rcv_chat.setAdapter(adapter_adminchat);
            // dòng kẻ của rcv
            RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),
                    DividerItemDecoration.VERTICAL);
            rcv_chat.addItemDecoration(itemDecoration);
        }
    }

    private void addevenst() {
        radio_chat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_gv){
                    loadList(2);
                    list.clear();
                }else {
                    loadList(1);
                    list.clear();
                }
            }
        });

    }


    @Override
    public void onclick(User user) {
        Bundle bundle = new Bundle();
        String id = String.valueOf(user.getId());
        bundle.putString("id",id);
        fragment = new ChatFragment();
        fragment.setArguments(bundle);
        getFragment();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Chat");
        list.clear();
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