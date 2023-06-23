package com.example.myproject.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.myproject.R;
import com.example.myproject.dao.HistoryDao;
import com.example.myproject.database.HistoryDatabase;
import com.example.myproject.entity.History;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Menu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Menu extends Fragment {

    TableLayout tableLayout;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Menu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Menu.
     */
    // TODO: Rename and change types and number of parameters
    public static Menu newInstance(String param1, String param2) {
        Menu fragment = new Menu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lich_su, container, false);
        tableLayout = view.findViewById(R.id.tableLayout);

        HistoryDao historyDao = HistoryDatabase.getInstance(view.getContext()).historyDao();
        int id = retrieData(view);
        List<History> historyList = historyDao.getHistories(id);
        System.out.println(historyList.size());
        for (History h : historyList) {
            TableRow tbrow = new TableRow(this.getContext());
            TextView t1v = new TextView(this.getContext());
            t1v.setText("" + h.getId());
            t1v.setGravity(Gravity.CENTER_HORIZONTAL);
            ViewGroup.LayoutParams params = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 2f);
            t1v.setLayoutParams(params);
            TextView t2v = new TextView(this.getContext());
            t2v.setText("" + h.getCorrect_number() + "/" + 10);
            t2v.setGravity(Gravity.CENTER_HORIZONTAL);
            ViewGroup.LayoutParams params2 = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 4f);
            t2v.setLayoutParams(params2);
            TextView t3v = new TextView(this.getContext());
            t3v.setText(h.getTime_finished());
            ViewGroup.LayoutParams params3 = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 6f);
            t3v.setLayoutParams(params3);
            t3v.setGravity(Gravity.CENTER_HORIZONTAL);
            tbrow.addView(t1v);
            tbrow.addView(t2v);
            tbrow.addView(t3v);
            tableLayout.addView(tbrow);
        }
        return view;
    }

    public int retrieData(View view) {
        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("saveData", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id", 1);
    }
}