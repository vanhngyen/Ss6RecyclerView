package com.example.ss6recyclerview.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ss6recyclerview.R;
import com.example.ss6recyclerview.model.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter {


    //1.data sourse
    //2. layout
    Activity activity;
    List<Contact> listData;
    //step2:
    IOnClickAdapter iOnClickAdapter;

    public ContactAdapter(Activity activity, List<Contact> listData) {
        this.activity = activity;
        this.listData = listData;
    }

    //step3:
    public void registerClick(IOnClickAdapter iClick){
        this.iOnClickAdapter = iClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //b1
        View itemView = activity.getLayoutInflater().inflate(R.layout.item_contact,parent,false);
        ContactHolder holder = new ContactHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
        //b2:setdata
        ContactHolder vh = (ContactHolder)holder;
        Contact model = listData.get(position);
        vh.ivAvatar.setImageResource(model.getAvatar());
        vh.tvUser.setText(model.getName());
        vh.tvPhone.setText(model.getPhone());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ContactHolder extends RecyclerView.ViewHolder{

        ImageView ivAvatar;
        TextView tvUser;
        TextView tvPhone;
        Button btEdit;

        public ContactHolder(View itemView) {
            super(itemView);

            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            tvUser = itemView.findViewById(R.id.tvUser);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            btEdit = itemView.findViewById(R.id.btEdit);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("ContactAdapter","onclick:"+getAdapterPosition());
                    Contact model = listData.get(getAdapterPosition());
                    Log.d("ContactAdapter","Name:"+model.getName());
                    //step4:

                    iOnClickAdapter.onClickItem(getAdapterPosition());
                }
            });
            btEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iOnClickAdapter.onClickEdit(getAdapterPosition());
                }
            });
        }
    }

    //step1: create interface contains actions
    public interface  IOnClickAdapter{
        void onClickItem(int position);
        void onClickEdit(int position);

    }
}
