package com.cntt.ui.thongke;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.cntt.R;
import com.cntt.adapter.ThongKeLoaiThuRecyclerViewAdapter;
import com.cntt.entity.ThongKeLoaiThu;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThongKeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThongKeFragment extends Fragment {

    private ThongKeViewModel mThongKeViewModel;
    private EditText etTongThu;
    private RecyclerView rvThongKeLoaiThu;
    private ThongKeLoaiThuRecyclerViewAdapter adapter;

    public ThongKeFragment() {
    }


    public static ThongKeFragment newInstance() {
        ThongKeFragment fragment = new ThongKeFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);

//        trả về sự thể hiện trên giao diện
        etTongThu = view.findViewById(R.id.etTongThu);
        rvThongKeLoaiThu = view.findViewById(R.id.rvThongKeThu);

        mThongKeViewModel = new ViewModelProvider(this).get(ThongKeViewModel.class);
        adapter = new ThongKeLoaiThuRecyclerViewAdapter(getActivity());
        rvThongKeLoaiThu.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvThongKeLoaiThu.setAdapter(adapter);

//        Lắng nghe sự thay đổi trong thu để cập nhật trên biểu đồ
        mThongKeViewModel.getTongThu().observe(getActivity(), new Observer<Float>() {
            @Override
            public void onChanged(Float aFloat) {
                etTongThu.setText("" + aFloat);
            }
        });
        //        Lắng nghe sự thay đổi trong thống kê loại thu để cập nhật trên biểu đồ
        mThongKeViewModel.getThongKeLoaiThu().observe(getActivity(), new Observer<List<ThongKeLoaiThu>>() {
            @Override
            public void onChanged(List<ThongKeLoaiThu> thongKeLoaiThus) {
                adapter.setList(thongKeLoaiThus);
            }
        });
        return view;
    }
}