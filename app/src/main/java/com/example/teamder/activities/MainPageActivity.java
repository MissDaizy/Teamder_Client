package com.example.teamder.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.teamder.server.JsonPlaceHolderApi;
import com.example.teamder.R;
import com.example.teamder.models.UserBoundary;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.teamder.databinding.ActivityMainPageBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPageActivity extends AppCompatActivity {

    private final int ROWS = 3;
    private final int COLS = 3;

    private TextView textView;
    private ImageView[][] groupsPictures;
    private MaterialTextView[][] groupsNames;
    private LinearLayout[][] groupsSections;

    private ActivityMainPageBinding binding;

    private TextView textViewResult;
    private MaterialButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);


        binding = ActivityMainPageBinding.inflate (getLayoutInflater ());
        setContentView (binding.getRoot ());
        findViews();
        textView=findViewById (R.id.textView);
        BottomNavigationView navView = findViewById (R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder (
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build ();
        NavController navController = Navigation.findNavController (this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController (this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController (binding.navView, navController);


        Retrofit retrofit = new Retrofit.Builder ()
                .baseUrl ("http://192.168.68.113:8084/iob/")
                .addConverterFactory (GsonConverterFactory.create ())
                .build ();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<UserBoundary> call = jsonPlaceHolderApi.getUserBoundary("2022b.diana.ukrainsky" ,"Vadim@gmail.com" );
        call.enqueue(new Callback<UserBoundary>() {
            @Override
            public void onResponse(Call<UserBoundary> call, Response<UserBoundary> response) {
                if(!response.isSuccessful()){
                    textView.setText(""+response.code());
                }
                textView.setText(response.body().getUserId ().getEmail ());
            }

            @Override
            public void onFailure(Call<UserBoundary> call, Throwable t) {
                textView.setText(""+t.getMessage());
            }
        });


    }

    private void findViews() {
        groupsNames = new MaterialTextView[][]{
                {findViewById(R.id.fragmentHome_groupName00),
                        findViewById(R.id.fragmentHome_groupName01),
                        findViewById(R.id.fragmentHome_groupName02)
                },
                {findViewById(R.id.fragmentHome_groupName10),
                        findViewById(R.id.fragmentHome_groupName11),
                        findViewById(R.id.fragmentHome_groupName12),
                },
                {findViewById(R.id.fragmentHome_groupName20),
                        findViewById(R.id.fragmentHome_groupName21),
                        findViewById(R.id.fragmentHome_groupName22),
                }
        };

        groupsPictures = new ImageView[][]{
                {findViewById(R.id.fragmentHome_picture00),
                        findViewById(R.id.fragmentHome_picture01),
                        findViewById(R.id.fragmentHome_picture02)
                },
                {findViewById(R.id.fragmentHome_picture10),
                        findViewById(R.id.fragmentHome_picture11),
                        findViewById(R.id.fragmentHome_picture12),
                },
                {findViewById(R.id.fragmentHome_picture20),
                        findViewById(R.id.fragmentHome_picture21),
                        findViewById(R.id.fragmentHome_picture22),
                }
        };

        groupsSections = new LinearLayout[][]{
                {findViewById(R.id.fragmentHome_matrix00),
                        findViewById(R.id.fragmentHome_matrix01),
                        findViewById(R.id.fragmentHome_matrix02)
                },
                {findViewById(R.id.fragmentHome_matrix10),
                        findViewById(R.id.fragmentHome_matrix11),
                        findViewById(R.id.fragmentHome_matrix12),
                },
                {findViewById(R.id.fragmentHome_matrix20),
                        findViewById(R.id.fragmentHome_matrix21),
                        findViewById(R.id.fragmentHome_matrix22),
                }
        };
    }


}