package ir.stackoverflow.sachinbakshi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

   /* @Bind(R.id.stackoverflowActivityBTN)
    Button stackoverflowActivityBTN;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    /*@OnClick(R.id.stackoverflowActivityBTN) void showStackoverflowActivity() {
        startActivity(new Intent(this, StackoverflowActivity.class));
    }*/
}
