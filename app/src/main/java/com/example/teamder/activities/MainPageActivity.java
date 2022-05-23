package com.example.teamder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.teamder.databinding.ActivityMainPageBinding;
import com.example.teamder.logic.DataManager;
import com.example.teamder.models.RoleType;
import com.example.teamder.models.InstanceOfTypeUser;
import com.example.teamder.retrofit.RetrofitService;
import com.example.teamder.service.JsonApiUsers;
import com.example.teamder.R;
import com.example.teamder.models.UserBoundary;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPageActivity extends AppCompatActivity {
    private Bundle bundle;

    //for exit with back
    private int counterBack;

    private final int ROWS = 3;
    private final int COLS = 3;

    BottomNavigationView navView;

    private MaterialTextView textView;
    private ImageView[][] groupsPictures;
    private MaterialTextView[][] groupsNames;
    private LinearLayout[][] groupsSections;

    private ActivityMainPageBinding binding;

    private TextView textViewResult;
    private MaterialButton button;

    private String username;

    private DataManager dataManager;


    ///////////////////////here is group fragment view values..
    private MaterialButton groups_addGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        binding = ActivityMainPageBinding.inflate (getLayoutInflater ());
        setContentView (binding.getRoot ());

        dataManager = new DataManager ();
        bundle =new Bundle ();

        findViews ();
    //    getUserBoundary ();

        Log.d ("pttt", "onCreate: ");
        textView = findViewById (R.id.fragmentHome_TXT_continue);
        textView.setText ("Hey " + dataManager.getUserBoundary ().getUsername () + "\nLets find you a team!");

        navView = findViewById (R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder (
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build ();
        NavController navController = Navigation.findNavController (this, R.id.nav_host_fragment_activity_main);
        //NavigationUI.setupActionBarWithNavController (this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController (binding.navView, navController);


        /*
        TODO:
        Code for POST instance type group and after nastia finish button "Add Group",
        move this to listener of that button.
         */
        //updateUserRoleType ();
    }

// TODO: delete... it is bad here .. only stay for case i need this again.
//    private void setListeners() {
//        //add new group
//        groups_addGroup.setOnClickListener(v -> {
//            Intent intent = new Intent (this, CreateGroupActivity.class);
//            startActivity (intent);
//        });
//    }

    //Add "Tool bar" - settings in the top of current Screen
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Activate the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        putInBundle();
        switch (item.getItemId()){
            case R.id.homeMenu_startProj: {
                Intent intent = new Intent(MainPageActivity.this, CreateGroupActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            }

            case R.id.homeMenu_myProfile: {
                Intent intent = new Intent(MainPageActivity.this, ViewProfileActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            }

            case R.id.homeMenu_logout: {
                Intent intent = new Intent(MainPageActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void putInBundle() {
        String userBoundaryJson = new Gson ().toJson(dataManager.getUserBoundary ());
        String instanceBoundaryJson = new Gson ().toJson(dataManager.getInstanceOfTypeUser ());
        bundle.putString(getString(R.string.BUNDLE_USER_BOUNDARY_KEY),userBoundaryJson);
        bundle.putString(getString(R.string.BUNDLE_USER_INSTANCE_BOUNDARY_KEY),instanceBoundaryJson);
    }


    private void updateUserRoleType() {
        String userDomain = dataManager.getUserDomain ();
        String userEmail = dataManager.getUserEmail ();

        dataManager.updateUserRoleTypeData ();

        RetrofitService retrofitService = new RetrofitService ();

        JsonApiUsers jsonApiUsers = retrofitService.getRetrofit ().create (JsonApiUsers.class);

        Call<Void> call = jsonApiUsers.updateUser (userDomain, userEmail, dataManager.getUserBoundary ());
        call.enqueue (new Callback<Void> () {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful ()) {
                    Log.d ("pttt", "" + response.code ());
                }
                if(dataManager.getUserBoundaryRoleType ().equals (RoleType.MANAGER.toString ())) {
                    Log.d ("pttt", "Success!!! user role updated to manager");
                    //createInstanceOfTypeGroup ();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d ("pttt", "Failure!!!, Message: " + t.getMessage ());
            }
        });

    }


    private void getUserBoundary() {
        bundle = getIntent ().getExtras ();
        String userBoundaryJson = bundle.getString (getString (R.string.BUNDLE_USER_BOUNDARY_KEY));
        String instanceBoundaryJson = bundle.getString (getString (R.string.BUNDLE_USER_INSTANCE_BOUNDARY_KEY));

        dataManager.setUserBoundary ((new Gson ().fromJson (userBoundaryJson, UserBoundary.class)));
        dataManager.setInstanceOfTypeUser (new Gson ().fromJson (instanceBoundaryJson, InstanceOfTypeUser.class));
        Log.d ("pttt", "description in main page :"+dataManager.getUserDescription ());

    }

    private void findViews() {
        ////////////////////the groups page
        groups_addGroup = findViewById(R.id.groups_addGroup);

        ////////////////////the home page
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

    // To go back "OUT" from app with phone
    @Override
    public void onBackPressed() {
        counterBack++;
        if(counterBack == 2){
            //super.onBackPressed();
            goToHomeScreen();
        }
    }

    // "BACK" main page activity
    public void goToHomeScreen() {
        counterBack=0;
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}