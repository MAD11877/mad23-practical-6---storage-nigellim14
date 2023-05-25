package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        /*ImageView androidimgbg2 = findViewById(R.id.androidimg2);
        ImageView androidimg2 = findViewById(R.id.androidimg2);
         */

        Random rand = new Random();
        /*
        AlertDialog.Builder profile  = new AlertDialog.Builder(this);
        profile.setTitle("Profile")
                .setMessage("MADness")
                .setCancelable(false)
                .setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent NewPage = new Intent(ListActivity.this, MainActivity.class);
                        NewPage.putExtra("RanInt", rand.nextInt());
                        startActivity(NewPage);
                    }
                })
                .setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

         */

        /*A list of 20 user object randomize name, description, value of followed*/
        ArrayList<User> userList = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            User user1 = new User();
            user1.setName("Name-" + rand.nextInt());
            user1.setDescription("Description-" + rand.nextInt());
            user1.setFollowed(rand.nextBoolean());
            userList.add(user1);
        }

        /*Replace imageview with recyclerview*/
        RecyclerView recyclerView = findViewById(R.id.recycleView);
        UsersAdapter adapter = new UsersAdapter(this, userList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        /*androidimgbg2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                profile.show();
            }
        });
        androidimgbg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile.show();
            }
        }); */
    }
}