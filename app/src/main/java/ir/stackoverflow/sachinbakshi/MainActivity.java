package ir.stackoverflow.sachinbakshi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.facebook.stetho.Stetho;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
    @Bind(R.id.hiveActivityBTN)
    Button hiveActivityBTN;
    @Bind(R.id.stackoverflowActivityBTN)
    Button stackoverflowActivityBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
    @OnClick(R.id.hiveActivityBTN) void showHiveActivity() {
        startActivity(new Intent(this, HiveActivity.class));

    }
    @OnClick(R.id.stackoverflowActivityBTN) void showStackoverflowActivity() {
        startActivity(new Intent(this, StackoverflowActivity.class));
    }
}
