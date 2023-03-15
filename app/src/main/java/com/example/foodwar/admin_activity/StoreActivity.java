package com.example.foodwar.admin_activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodwar.adapter.ListStoreAdapter;
import com.example.foodwar.databinding.ActivityStoreBinding;
import com.example.foodwar.databinding.StoreDialogBinding;
import com.example.foodwar.databinding.StoreInsertBinding;
import com.example.foodwar.models.Restaurant;
import com.example.foodwar.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class StoreActivity extends AppCompatActivity {
    private ActivityStoreBinding binding;
    private Dialog dialog;
    private DatabaseReference dbRet= FirebaseDatabase.getInstance().getReference("restaurants");
    private ArrayList<Restaurant> listRestaurant = new ArrayList<>();
    private Restaurant restaurantMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityStoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        restaurantMain=new Restaurant();
        init();
        initBtnInsert();
        initRCV();

    }

    private void initRCV(){
        dbRet.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    listRestaurant.clear();
                    for (DataSnapshot res: snapshot.getChildren()
                    ) {
                        String resID=res.child("id").getValue().toString();
                        String resName= Objects.requireNonNull(res.child("name").getValue()).toString();
                        Integer resRate=Integer.parseInt(res.child("rate").getValue().toString());
                        String resType=res.child("des").getValue().toString();
                        String resAddress=res.child("address").getValue().toString();
                        String resAvata=res.child("image").getValue().toString();
                        Restaurant mRestaurant=new Restaurant(resID,resName,resRate,resAvata,resType,resAddress);
                        listRestaurant.add(mRestaurant);

                    }
                    for (Restaurant u:listRestaurant
                         ) {
                        Log.d("Rsava",u.getImage());
                    }
                    setAdapter(listRestaurant);
                    initSearch(listRestaurant);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void setAdapter(ArrayList<Restaurant> listRestaurant){
        LinearLayoutManager storeVertical = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.storeRecyclerView.setLayoutManager(storeVertical);
        ListStoreAdapter adapter = new ListStoreAdapter(this, listRestaurant, new ListStoreAdapter.IRestaurant() {
            @Override
            public void onDetailCLick(int position) {
                restaurantMain=listRestaurant.get(position);
                onSetupDetailDiaog(restaurantMain);
            }
        });
        binding.storeRecyclerView.setAdapter(adapter);
    }
    private void initSearch(ArrayList<Restaurant> restaurants){
        binding.btnSearchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Restaurant> listSearch=new ArrayList<>();
                String search=binding.searchView.getQuery().toString();
                if(search.equals("")){
                    for (Restaurant u:restaurants
                         ) {
                        listSearch.add(u);
                    }
                }else {
                    for (Restaurant u: restaurants
                         ) {
                        if(u.getName().contains(search)){
                            listSearch.add(u);
                        }
                    }

                }
                setAdapter(listSearch);
            }
        });
    }
    private void initBtnInsert(){
        binding.btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSetupInsertDialog();
            }
        });
    }
    private void init(){

    }
    private void onSetupInsertDialog(){
        dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        StoreInsertBinding viewDataBinding=StoreInsertBinding.inflate(LayoutInflater.from(StoreActivity.this));
        dialog.setContentView(viewDataBinding.getRoot());
        Window window = dialog.getWindow();


        window.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAtributes = window.getAttributes();
        windowAtributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAtributes);
        if (Gravity.CENTER == Gravity.CENTER) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }
        InsertRestaurant(viewDataBinding);
        dialog.show();
    }
    private void InsertRestaurant(StoreInsertBinding viewDataBinding){
        viewDataBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        viewDataBinding.btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String autoID=dbRet.push().getKey();
                restaurantMain.setId(autoID);
                restaurantMain.setName(viewDataBinding.txtFullName.getText().toString());
                int c=(int)Double.parseDouble(viewDataBinding.storeRating.getRating()+"");
                restaurantMain.setRate(c);
                restaurantMain.setDes(viewDataBinding.txtKindofF.getText().toString());
                restaurantMain.setAddress(viewDataBinding.txtAddress.getText().toString());
                restaurantMain.setImage(viewDataBinding.txtAvata.getText().toString());
                dbRet.child(autoID).setValue(restaurantMain).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(StoreActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(StoreActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("FirebaseErro",e.toString());
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void onSetupDetailDiaog(Restaurant restaurantMain){
        dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        StoreDialogBinding viewDataBinding=StoreDialogBinding.inflate(LayoutInflater.from(StoreActivity.this));
        dialog.setContentView(viewDataBinding.getRoot());
        Window window = dialog.getWindow();


        window.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAtributes = window.getAttributes();
        windowAtributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAtributes);
        if (Gravity.CENTER == Gravity.CENTER) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }
        viewDataBinding.txtFullName.setText(restaurantMain.getName());
        viewDataBinding.storeRating.setRating(restaurantMain.getRate());
        viewDataBinding.txtKindofF.setText(restaurantMain.getDes());
        viewDataBinding.txtAddress.setText(restaurantMain.getAddress());
        viewDataBinding.txtAvata.setText(restaurantMain.getImage());
        if (!TextUtils.isEmpty(restaurantMain.getImage())) {
            viewDataBinding.txtAvata.setText(restaurantMain.getImage());
            Picasso.get().load(restaurantMain.getImage())
                    .placeholder(R.drawable.icon_loading)
                    .error(R.drawable.ic_error)
                    .into(viewDataBinding.imgAvata);
        } else {
            // Load default image
            viewDataBinding.txtAvata.setText("");
            Picasso.get().load(R.drawable.default_image_res)
                    .placeholder(R.drawable.icon_loading)
                    .error(R.drawable.ic_error)
                    .into(viewDataBinding.imgAvata);
        }
        viewDataBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        viewDataBinding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                restaurantMain.setName(viewDataBinding.txtFullName.getText().toString());
                int c=(int)Double.parseDouble(viewDataBinding.storeRating.getRating()+"");
                restaurantMain.setRate(c);
                restaurantMain.setDes(viewDataBinding.txtKindofF.getText().toString());
                restaurantMain.setAddress(viewDataBinding.txtAddress.getText().toString());
                restaurantMain.setImage(viewDataBinding.txtAvata.getText().toString());
                dbRet.child(restaurantMain.getId()).setValue(restaurantMain).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(StoreActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(StoreActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("FirebaseErro",e.toString());
                        dialog.dismiss();
                    }
                });
            }
        });
        viewDataBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRet.child(restaurantMain.getId()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(StoreActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(StoreActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("FirebaseErro",e.toString());
                        dialog.dismiss();
                    }
                });
            }
        });
        dialog.show();
    }
}