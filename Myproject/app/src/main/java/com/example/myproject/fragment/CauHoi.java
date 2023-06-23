package com.example.myproject.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myproject.R;
import com.example.myproject.ThemCauHoi;
import com.example.myproject.adapter.FlagAdapter;
import com.example.myproject.dao.FlagDao;
import com.example.myproject.database.FlagDatabase;
import com.example.myproject.entity.Flag;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CauHoi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CauHoi extends Fragment {
    private FloatingActionButton btnFloating;
    private RecyclerView recyclerView;
    ActivityResultLauncher<Intent> activityResultLauncher;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CauHoi() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CauHoi.
     */
    // TODO: Rename and change types and number of parameters
    public static CauHoi newInstance(String param1, String param2) {
        CauHoi fragment = new CauHoi();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //registerActivityForSelectImage();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    //Hiện thỉ câu hỏi dạng reclycle view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cau_hoi, container, false);
        btnFloating = view.findViewById(R.id.btnFloating);
        recyclerView = view.findViewById(R.id.recyclerView);

        FlagDao flagDao = FlagDatabase.getInstance(getContext()).flagDao();
        FlagAdapter flagAdapter = new FlagAdapter(flagDao.getAll());
        recyclerView.setAdapter(flagAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        btnFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), ThemCauHoi.class));
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}