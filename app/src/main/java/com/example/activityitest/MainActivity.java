package com.example.activityitest;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button showGuess;
    private EditText enterGuess;
    //hiển thị text từ Activity thứ 2 là ShowGuess
    //đổi sang phương thức mới vì ở Section 18 course 121 method startActivityForResult là deprecated
    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == RESULT_OK){

                        Intent intent = result.getData();
                        assert intent != null;
                        String message = intent.getStringExtra("message_back");
                        enterGuess.setText(message);
                        Toast.makeText(MainActivity.this,message
                                ,Toast.LENGTH_SHORT)
                                .show();

                    }
                }
            });

    //không cần sử dụng
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE){
//
//            assert data != null;
//            String message = data.getStringExtra("message_back");
//                Toast.makeText(MainActivity.this,message
//                        ,Toast.LENGTH_SHORT)
//                        .show();
//
//        }
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showGuess = findViewById(R.id.guess_button);
        enterGuess = findViewById(R.id.guess_field);

        showGuess.setOnClickListener(v -> {
            //Retrieve guessfield data
            String guess = enterGuess.getText().toString().trim();
            if(!guess.isEmpty()){
            Intent intent = new Intent(MainActivity.this, ShowGuess.class);
            intent.putExtra("guess",guess);
            intent.putExtra("name","bond");
            intent.putExtra("age",34);


            //cách khác
            //startActivity(new Intent(MainActivity.this, ShowGuess.class));

                //startActivityForResult(intent,2);
                // deprecated use this
                activityResultLauncher.launch(intent);
            }else {
                Toast.makeText(this,"Please Type in Something!",
                        Toast.LENGTH_LONG)
                        .show();
            }
        });


    }

    //kiểm tra từng activity cycle với Logcat
//    @Override
//    protected void onStart() {
//        super.onStart();
//        Toast.makeText(MainActivity.this, "OnStart() Called",
//                Toast.LENGTH_LONG).show();
//        Log.d("ACT", "onStart: show");
//    }
//
//    @Override
//    protected void onPostResume() {
//        super.onPostResume();
//        Toast.makeText(MainActivity.this, "onPostResume() Called",
//                Toast.LENGTH_SHORT).show();
//        Log.d("ACT", "onPostResume: show");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Toast.makeText(MainActivity.this, "onPostResume() Called",
//                Toast.LENGTH_SHORT).show();
//        Log.d("ACT", "onPause: show");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Toast.makeText(MainActivity.this, "onStop() Called",
//                Toast.LENGTH_SHORT).show();
//        Log.d("ACT", "onStop: show");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Toast.makeText(MainActivity.this, "onDestroy() Called",
//                Toast.LENGTH_SHORT).show();
//        Log.d("ACT", "onDestroy: show");
//    }

}