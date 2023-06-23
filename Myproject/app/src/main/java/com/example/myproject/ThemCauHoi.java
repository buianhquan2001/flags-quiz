package com.example.myproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myproject.adapter.FlagAdapter;
import com.example.myproject.dao.FlagDao;
import com.example.myproject.database.FlagDatabase;
import com.example.myproject.entity.Flag;
import com.example.myproject.fragment.CauHoi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.jar.Pack200;

public class ThemCauHoi extends AppCompatActivity {
    ImageView addImage;
    ActivityResultLauncher<Intent>activityResultLauncher;

    private Bitmap selectedImage;
    private EditText countryName;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_cau_hoi);

        registerActivityForSelectImage();

        addImage = findViewById(R.id.addImage);
        btnSave = findViewById(R.id.btnSave);
        countryName = findViewById(R.id.countryName);


        //Mở tệp ảnh
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(ThemCauHoi.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ThemCauHoi.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activityResultLauncher.launch(intent);
                }
            }
        });

        //Lưu ảnh
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedImage == null) {
                    Toast.makeText(getApplicationContext(), "Chưa thêm ảnh!", Toast.LENGTH_LONG).show();
                } else {
                    String country_name = countryName.getText().toString();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    //Bitmap scaledImage = makeSmall(selectedImage, 300);
                    //Chuyển ảnh thành format png
                    selectedImage.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
                    //Ảnh được chuyển thành định dạng nhị phân
                    byte[] image = outputStream.toByteArray();
/*
                    Intent intent = new Intent();
                    intent.putExtra("country_name", country_name);
                    intent.putExtra("image", image);*/
                    Flag flag = new Flag(country_name, image);

                    FlagDao flagDao = FlagDatabase.getInstance(getApplicationContext()).flagDao();
                    flagDao.insert(flag);
                    Toast.makeText(getApplicationContext(), "Them Thanh cong" + flagDao.getAll().size(), Toast.LENGTH_LONG).show();

                   Intent intent = new Intent(ThemCauHoi.this, Game.class);
                   startActivity(intent);

                }
            }
        });
    }

    //Hiển thị hình ảnh
    public void registerActivityForSelectImage() {
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                int resultCode = result.getResultCode();
                Intent data = result.getData();
                if (resultCode == RESULT_OK && data != null) {
                    try {
                        //Get ảnh lấy được thuộc lớp bitmap
                        selectedImage = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                        //Set ảnh hiển thị lên màn hình
                        addImage.setImageBitmap(selectedImage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    //Bật kho ảnh
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activityResultLauncher.launch(intent);
        }
    }

/*    public Bitmap makeSmall(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float ratio = (float) width / (float) height;

        if (ratio > 1) {
            width = maxSize;
            height = (int) (width / ratio);
        } else {
            height = maxSize;
            width = (int) (height * ratio);
        }

        return Bitmap.createScaledBitmap(image, width, height, true);
    }*/
}