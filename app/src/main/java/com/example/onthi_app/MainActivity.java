package com.example.onthi_app;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onthi_app.Adapter.AdapterXemay;
import com.example.onthi_app.Model.Response;
import com.example.onthi_app.Model.Xemay;
import com.example.onthi_app.Service.ApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements AdapterXemay.XeClick{

    RecyclerView rcvSV;
    FloatingActionButton fltadd;

    AdapterXemay adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fltadd = findViewById(R.id.fltadd);
        rcvSV = findViewById(R.id.rcvSV);

        getData();

        fltadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog();
            }
        });
    }

    private void getData() {

        Call<Response<List<Xemay>>> call = ApiService.apiservice.getdanhsac();
        call.enqueue(new Callback<Response<List<Xemay>>>() {
            @Override
            public void onResponse(Call<Response<List<Xemay>>> call, retrofit2.Response<Response<List<Xemay>>> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus() == 200){
                        List<Xemay> ds = response.body().getData();
                        loadData(ds);
                        Toast.makeText(MainActivity.this, "Get list ok", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<List<Xemay>>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Get list fail", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void loadData (List<Xemay> list){
        adapter = new AdapterXemay(list,this,this);
        rcvSV.setLayoutManager(new LinearLayoutManager(this));
        rcvSV.setAdapter(adapter);
    }

    @Override
    public void edit(Xemay xemay) {
        Updatedialog(xemay);
        Log.d("id","id : "+ xemay.get_id());
    }

    @Override
    public void delete(Xemay xemay) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Canh bao");
        builder.setMessage("Ban co muon xoa hay k");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Call<Response<Xemay>> call = ApiService.apiservice.deleteXe(xemay.get_id());
                call.enqueue(new Callback<Response<Xemay>>() {
                    @Override
                    public void onResponse(Call<Response<Xemay>> call, retrofit2.Response<Response<Xemay>> response) {
                        if (response.isSuccessful()){
                            if (response.body().getStatus() == 200){
                                Toast.makeText(MainActivity.this, "Xoa ok", Toast.LENGTH_SHORT).show();
                                getData();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<Xemay>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Xoa fail", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        builder.setNegativeButton("No",null);
        builder.show();

    }

    @Override
    public void chitiet(Xemay xemay) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_chitiet,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextView edtTenXe = view.findViewById(R.id.edtTenXe);
        TextView edtMauSac = view.findViewById(R.id.edtMauSac);
        TextView edtGiaBan = view.findViewById(R.id.edtGiaBan);
        TextView edtMoTa = view.findViewById(R.id.edtMoTa);
        ImageView imghinhAnh = view.findViewById(R.id.imgHinhAnh);
        Button btnSave = view.findViewById(R.id.btnSave);

        edtTenXe.setText("Ten xe : "+xemay.getTen_xe_ph1234());
        edtMauSac.setText("Mau sac : "+xemay.getMau_sac_ph1234());
        edtGiaBan.setText("Gia ban : "+xemay.getGia_ban_ph1234());
        edtMoTa.setText("Mo ta : "+xemay.getMo_ta_ph1234());

        Glide.with(imghinhAnh)
                .load(xemay.getHinh_anh_ph1234())
                .into(imghinhAnh);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void opendialog (){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edtTenXe = view.findViewById(R.id.edtTenXe);
        EditText edtMauSac = view.findViewById(R.id.edtMauSac);
        EditText edtGiaBan = view.findViewById(R.id.edtGiaBan);
        EditText edtMoTa = view.findViewById(R.id.edtMoTa);
        EditText edtHinhAnh = view.findViewById(R.id.edtHinhAnh);
        Button btnSave = view.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edtTenXe.getText().toString().trim();
                String mau = edtMauSac.getText().toString().trim();
                String gia = edtGiaBan.getText().toString().trim();
                String mota = edtMoTa.getText().toString().trim();
                String hinh = edtHinhAnh.getText().toString().trim();

                if (ten.isEmpty()){
                    Toast.makeText(MainActivity.this, "Ten khong dc de trong", Toast.LENGTH_SHORT).show();
                } else if (mau.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Mau khong dc de trong", Toast.LENGTH_SHORT).show();
                } else if (gia.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Gia khong dc de trong", Toast.LENGTH_SHORT).show();
                } else if (!gia.matches("[0-9]++")) {
                    Toast.makeText(MainActivity.this, "Gia phai la so", Toast.LENGTH_SHORT).show();
                }else{

                    Xemay xemay = new Xemay(ten,mau,mota,hinh,Integer.parseInt(gia));
                    Call<Response<Xemay>> call = ApiService.apiservice.addXe(xemay);
                    call.enqueue(new Callback<Response<Xemay>>() {
                        @Override
                        public void onResponse(Call<Response<Xemay>> call, retrofit2.Response<Response<Xemay>> response) {
                            if (response.isSuccessful()){
                                if (response.body().getStatus() == 200){
                                    getData();
                                    dialog.dismiss();
                                    Toast.makeText(MainActivity.this, "Add success", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Response<Xemay>> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Add fail", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });

    }

    public void Updatedialog (Xemay xemay){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edtTenXe = view.findViewById(R.id.edtTenXe);
        EditText edtMauSac = view.findViewById(R.id.edtMauSac);
        EditText edtGiaBan = view.findViewById(R.id.edtGiaBan);
        EditText edtMoTa = view.findViewById(R.id.edtMoTa);
        EditText edtHinhAnh = view.findViewById(R.id.edtHinhAnh);
        Button btnSave = view.findViewById(R.id.btnSave);

        edtTenXe.setText(xemay.getTen_xe_ph1234());
        edtMauSac.setText(xemay.getMau_sac_ph1234());
        edtGiaBan.setText(xemay.getGia_ban_ph1234()+"");
        edtMoTa.setText(xemay.getMo_ta_ph1234());
        edtHinhAnh.setText(xemay.getHinh_anh_ph1234());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edtTenXe.getText().toString().trim();
                String mau = edtMauSac.getText().toString().trim();
                String gia = edtGiaBan.getText().toString().trim();
                String mota = edtMoTa.getText().toString().trim();
                String hinh = edtHinhAnh.getText().toString().trim();

                if (ten.isEmpty()){
                    Toast.makeText(MainActivity.this, "Ten khong dc de trong", Toast.LENGTH_SHORT).show();
                } else if (mau.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Mau khong dc de trong", Toast.LENGTH_SHORT).show();
                } else if (gia.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Gia khong dc de trong", Toast.LENGTH_SHORT).show();
                } else if (!gia.matches("[0-9]++")) {
                    Toast.makeText(MainActivity.this, "Gia phai la so", Toast.LENGTH_SHORT).show();
                }else{
                    Xemay xe = new Xemay(ten,mau,mota,hinh,Integer.parseInt(gia));

                    Call<Response<Xemay>> call = ApiService.apiservice.updateXe(xemay.get_id(),xe);
                    call.enqueue(new Callback<Response<Xemay>>() {
                        @Override
                        public void onResponse(Call<Response<Xemay>> call, retrofit2.Response<Response<Xemay>> response) {
                            if (response.isSuccessful()){
                                if (response.body().getStatus() == 200){
                                    getData();
                                    dialog.dismiss();
                                    Toast.makeText(MainActivity.this, "update success", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Response<Xemay>> call, Throwable t) {
                            Log.d("Update","Errr : "+ t);
                            Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
}