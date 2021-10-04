package com.example.android.ecommerce.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.android.ecommerce.R;
import com.example.android.ecommerce.activites.RegistrationActivity;
import com.example.android.ecommerce.activites.ShowAllActivity;
import com.example.android.ecommerce.adapters.CategoryAdapter;
import com.example.android.ecommerce.adapters.NewProductsAdapter;
import com.example.android.ecommerce.adapters.PopularProductsAdapter;
import com.example.android.ecommerce.models.CategoryModel;
import com.example.android.ecommerce.models.NewProductsModel;
import com.example.android.ecommerce.models.PopularProductsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {


    LinearLayout linearLayout;

    RecyclerView catRecyclerView;
    RecyclerView newRecyclerView;
    RecyclerView popRecyclerView;

    ProgressDialog progressDialog;

    CategoryAdapter categoryAdapter;
    List<CategoryModel> categoryModelList;

    NewProductsAdapter newProductsAdapter;
    List<NewProductsModel> newProductsModelList;

    PopularProductsAdapter popProductsAdapter;
    List<PopularProductsModel> popProductsModelList;

    TextView catShowAll,popularShowAll,newProductShowAll;

    FirebaseFirestore db ;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_home, container, false);

        catRecyclerView = root.findViewById(R.id.rec_category);
        newRecyclerView = root.findViewById(R.id.new_product_rec);
        popRecyclerView = root.findViewById(R.id.popular_rec);
        linearLayout = root.findViewById(R.id.home_layout);
        progressDialog = new ProgressDialog(getActivity());

        catShowAll = root.findViewById(R.id.category_see_all);
        popularShowAll = root.findViewById(R.id.popular_see_all);
        newProductShowAll = root.findViewById(R.id.newProducts_see_all);


        db = FirebaseFirestore.getInstance();


        ImageSlider imageSlider = root.findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.banner1,"Discount On Shoes Items", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner2,"Discount On Perfume", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner3,"70% OFF", ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);

        progressDialog.setTitle("Welcome To My ECommerce App ");
        progressDialog.setMessage("Please Wait......");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        linearLayout.setVisibility(View.GONE);

        catShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

        popularShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

        newProductShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });


        catRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL,false));
        categoryModelList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getContext(),categoryModelList);
        catRecyclerView.setAdapter(categoryAdapter);

        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CategoryModel categoryModel = document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();
                                progressDialog.dismiss();
                                linearLayout.setVisibility(View.VISIBLE);

                            }
                        } else {
                            Toast.makeText(getActivity() , ""+task.getException() ,Toast.LENGTH_SHORT);
                        }
                    }
                });


        newRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL,false));
        newProductsModelList = new ArrayList<>();
        newProductsAdapter = new NewProductsAdapter(getContext(),newProductsModelList);
        newRecyclerView.setAdapter(newProductsAdapter);

        db.collection("NewProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                NewProductsModel newProductsModel = document.toObject(NewProductsModel.class);
                                newProductsModelList.add(newProductsModel);
                                newProductsAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(getActivity() , ""+task.getException() ,Toast.LENGTH_SHORT);
                        }
                    }
                });

        popRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        popProductsModelList = new ArrayList<>();
        popProductsAdapter = new PopularProductsAdapter(getContext(),popProductsModelList);
        popRecyclerView.setAdapter(popProductsAdapter);

        db.collection("AllProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularProductsModel popularProductsModel = document.toObject(PopularProductsModel.class);
                                popProductsModelList.add(popularProductsModel);
                                popProductsAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(getActivity() , ""+task.getException() ,Toast.LENGTH_SHORT);
                        }
                    }
                });

        return root;
    }
}