package com.example.myproject.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.R;
import com.example.myproject.dao.FlagDao;
import com.example.myproject.database.FlagDatabase;
import com.example.myproject.entity.Flag;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FlagAdapter extends RecyclerView.Adapter<FlagAdapter.FlagHolder> {
    List<Flag> flags;

    public FlagAdapter(List<Flag> flags) {
        this.flags = flags;
    }

    @NonNull
    @Override
    public FlagHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);

        return new FlagHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlagHolder holder, int position) {
        Flag flag = flags.get(position);
        holder.textView.setText(flag.getCountryName());
        holder.imageView.setImageBitmap(BitmapFactory.decodeByteArray(flag.getImage(), 0, flag.getImage().length));
    }

    @Override
    public int getItemCount() {
        return flags.size();
    }

    public class FlagHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public FlagHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());

                    builder.setTitle("Xoá câu hỏi");
                    builder.setMessage("Bạn đồng ý xoá?");

                    builder.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            FlagDao flagDao = FlagDatabase.getInstance(itemView.getContext()).flagDao();
                            flagDao.deleteByName(textView.getText().toString());
                            flags = flagDao.getAll();
                            Toast.makeText(itemView.getContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                    });

                    builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }
    }
}
