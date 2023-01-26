package com.project.laundryappui.menu.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.project.laundryappui.Layout_Produk;
import com.project.laundryappui.LogoutActivity;
import com.project.laundryappui.R;
import com.project.laundryappui.SessionManager;
import com.project.laundryappui.menu.home.adapter.HomeAdapter;
import com.project.laundryappui.menu.home.model.HomeModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private Context mContext;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private HomeAdapter homeAdapter;
    private ImageView imageSetrika, imageCuci, imageDry, imagePremium, logout;
    private List<HomeModel> homeModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setLayout();

        setAdapterType(view);
        setAdapter();


    }



    private void setLayout() {
        imageSetrika = getView().findViewById(R.id.image_1);
        logout = getView().findViewById(R.id.logout);

        imageSetrika.setOnClickListener(v -> {
            Intent intent = new Intent(HomeFragment.this.getActivity(), Layout_Produk.class);
            startActivity(intent);
        });

        imageCuci = getView().findViewById(R.id.image_2);

        imageCuci.setOnClickListener(v -> {
            Intent intent = new Intent(HomeFragment.this.getActivity(), Layout_Produk.class);
            startActivity(intent);
        });

        imageDry = getView().findViewById(R.id.image_3);

        imageDry.setOnClickListener(v -> {
            Intent intent = new Intent(HomeFragment.this.getActivity(), Layout_Produk.class);
            startActivity(intent);
        });

        imagePremium = getView().findViewById(R.id.image_4);

        imagePremium.setOnClickListener(v -> {
            Intent intent = new Intent(HomeFragment.this.getActivity(), Layout_Produk.class);
            startActivity(intent);
        });

        logout = getView().findViewById(R.id.logout);

        logout.setOnClickListener(v -> {
            Intent intent = new Intent(HomeFragment.this.getActivity(), LogoutActivity.class);
            startActivity(intent);
        });


        }



    private void initData() {
        homeModelList = new ArrayList<>();

        homeModelList.add(new HomeModel(R.drawable.bg_post1, "Amanda Laundry", "Rp. 100.000", "Distance 1.2 km" ));
        homeModelList.add(new HomeModel(R.drawable.bg_post2, "Papa Laundry", "Rp.75.000", "Distance 1.3 km" ));
        homeModelList.add(new HomeModel(R.drawable.bg_post3, "Mama Laundry", "Rp. 50.000", "Distance 1.4 km" ));
    }

    private void setAdapterType(View view) {
        recyclerView    = view.findViewById(R.id.recyclerview_recommended);
        layoutManager   = new LinearLayoutManager(mContext);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setNestedScrollingEnabled(true);
    }

    private void setAdapter() {
        initData();

        homeAdapter = new HomeAdapter(homeModelList);
        recyclerView.setAdapter(homeAdapter);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }
}