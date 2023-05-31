package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent recieve = getIntent();
        int id = recieve.getIntExtra("id", 0);
        user = ListActivity.userList.get(id);

        /*
        Intent receive = getIntent();
        Bundle data = receive.getBundleExtra("UserData");
        */


         /*
        //Set user information from data
        User u1 = new User();
        u1.setName(data.getString("Name"));
        u1.setId(data.getInt("Id"));
        u1.setDescription(data.getString("Desc"));
        u1.setFollowed(data.getBoolean("followStatus"));
        */


        TextView headText = findViewById(R.id.headtext);
        headText.setText(user.getName());
        TextView desc = findViewById(R.id.longmessage);
        desc.setText(user.getDescription());
        setFollowBtnText();


        /* onClickListener such that it will show an Alert Dialog*/
        /*
        followbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = followbtn.getText().toString();
                    if (text.equals("FOLLOW")) {
                    followbtn.setText("UNFOLLOW");
                    Toast.makeText(getApplicationContext(), "FOLLOWED", Toast.LENGTH_SHORT).show();
                } else if (text.equals("UNFOLLOW")) {
                    followbtn.setText("FOLLOW");
                    Toast.makeText(getApplicationContext(), "UNFOLLOWED", Toast.LENGTH_SHORT).show();
                }
            }
        });
        */
    }

    public void setFollowBtnText(){
        Button followBtn = findViewById(R.id.follow);
        if(user.isFollowed()){
            followBtn.setText("UNFOLLOW");
        }
        else{
            followBtn.setText("FOLLOW");
        }
    }

    public void onClickFollow (View v){
        user.setFollowed(!user.isFollowed());
        if(user.isFollowed()){
            Toast.makeText(this,"FOLLOWED", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"UNFOLLOWED",Toast.LENGTH_SHORT).show();
        }
        setFollowBtnText();

        DBHandler db = new DBHandler(this);
        db.updateUser(user);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("debug", "start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("debug", "stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("debug", "destroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("debug", "paused");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("debug", "resume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("debug", "restart");
    }
}