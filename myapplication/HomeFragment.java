package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.myapplication.HelperClasses.HomeAdapter.CategoriesAdapter;
import com.example.myapplication.HelperClasses.HomeAdapter.CategoriesHelperClass;
import com.example.myapplication.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.myapplication.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.myapplication.HelperClasses.HomeAdapter.MostViewedAdapter;
import com.example.myapplication.HelperClasses.HomeAdapter.MostViewedHelperClass;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    RecyclerView featuredRecycler,mostViewedRecycler,categoriesRecycler;
    RecyclerView.Adapter adapter;
    TextView text1,text2;

    SearchView searchView;
    ListView myListView;

    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter1;
    private GradientDrawable gradient1,gradient2,gradient3,gradient4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //text1 = (TextView) fi
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        //hooks
        featuredRecycler = rootView.findViewById(R.id.featured_recycler);
        mostViewedRecycler = rootView.findViewById(R.id.mostViewed_recycler);
        categoriesRecycler = rootView.findViewById(R.id.categories_recycler);

        text1 = rootView.findViewById(R.id.view_allbtn);
        text2 = rootView.findViewById(R.id.view_allbtn1);

        searchView = rootView.findViewById(R.id.searchview);
        myListView = rootView.findViewById(R.id.listView);

        myListView.setVisibility(View.GONE);

        arrayList=new ArrayList<>();
        arrayList.add("Dog");
        arrayList.add("Cat");
        arrayList.add("Fish");

        adapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList);

        myListView.setAdapter(adapter1);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                myListView.setVisibility(View.VISIBLE);
                adapter1.getFilter().filter(s);
                return false;
            }
        });
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(getActivity(), Adore_Me_activity.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0,0);
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(getActivity(), CarePlansActivity.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0,0);
            }
        });
        //functions will be executed automatically when this activity will be created
        featuredRecycler();
        mostViewedRecycler();
        categoriesRecycler();
        return rootView;
    }

    private void featuredRecycler(){

        featuredRecycler.setHasFixedSize(true);// will load only those images that will fit inside the screen
        featuredRecycler.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.cat_food1,"Cat Food","Whiskas : Nourishing cat food for a thriving pet."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.dog_food1,"Dog Food","Nutrient-rich dog food for a healthy, happy pet."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.fish_food1,"Fish Food","Fish food : boosts vitality for your aquatic friends."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.cata1,"Cat Belt","Cat belt : Keep tabs on your feline friend in style"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.doga1,"Dog Ball","Dog Ball : Let them play enjoy!"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.fisha1,"Fish accessories","Quality accessories for a thriving underwater world."));

        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);
    }

    private void categoriesRecycler(){ // our care card
        //all gradients
        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,new int[]{0xffd4cbe5, 0xffd4cbe5});
        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,new int[]{0xff7adccf, 0xff7adccf});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,new int[]{0xfff7c59f, 0xFFf7c59f});
        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,new int[]{0xffb8d7f5, 0xffb8d7f5});


        ArrayList<CategoriesHelperClass> categoriesHelperClasses = new ArrayList<CategoriesHelperClass>();
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient1,R.drawable.plans_bg1,"Trial Plan"));
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient2,R.drawable.plans_bg1,"Basic Daily Care"));
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient3,R.drawable.plans_bg1,"Weekend Gateway"));

        categoriesRecycler.setHasFixedSize(true);
        adapter = new CategoriesAdapter(categoriesHelperClasses);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false));
        categoriesRecycler.setAdapter(adapter);
    }
    private void mostViewedRecycler(){  //adore a pet card
        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false));

        ArrayList<MostViewedHelperClass> mostViewedLocations = new ArrayList<>();
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.cavapoo,"Cavapoo","Affectionate,intelligent crossbreed of Cavalier King Charles Spaniel and Poodle"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.german_shepard2,"German Shepard","Intelligent,loyal,versatile;excellent working dogs and devoted to family pets."));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.british1,"British Shorthair","Robust,affectionate,with a dense coat and round face. Calm and great for families."));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.japanese2,"Japanese Bobtail","Distinctive,lively breed with a kinked tail."));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.f1,"Blue discus","Brightly colored freshwater fish,popular as ornamented pets in aquariums."));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.f4,"Siamese fighting fish","Small,vibrant freshwater fish known for striking colors and elaborate finnage."));

        adapter = new MostViewedAdapter(mostViewedLocations);
        mostViewedRecycler.setAdapter(adapter);

    }

}