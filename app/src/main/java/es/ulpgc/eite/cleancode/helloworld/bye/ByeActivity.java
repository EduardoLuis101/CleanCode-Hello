package es.ulpgc.eite.cleancode.helloworld.bye;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.cleancode.helloworld.R;
import es.ulpgc.eite.cleancode.helloworld.hello.HelloActivity;
import es.ulpgc.eite.cleancode.helloworld.hello.HelloContract;
import es.ulpgc.eite.cleancode.helloworld.hello.HelloViewModel;

public class ByeActivity
        extends AppCompatActivity implements ByeContract.View {

    public static String TAG = ByeActivity.class.getSimpleName();

    private ByeContract.Presenter presenter;

    Button sayByeButton, goHelloButton;
    TextView byeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bye);
        getSupportActionBar().setTitle(R.string.bye_screen_title);

        sayByeButton = findViewById(R.id.sayByeButton);
        goHelloButton = findViewById(R.id.goHelloButton);
        byeMessage = findViewById(R.id.byeMessage);

        sayByeButton.setOnClickListener(v -> presenter.sayByeButtonClicked());

        goHelloButton.setOnClickListener(v -> presenter.goHelloButtonClicked());

        sayByeButton.setText(getSayByeButtonLabel());
        goHelloButton.setText(getGoHelloButtonLabel());

        // do the setup
        ByeScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }
    }

    @Override
    public void displayByeData(ByeViewModel viewModel) {
        Log.e(TAG, "displayByeData()");

        // deal with the data
        byeMessage.setText(viewModel.byeMessage);
    }

    private String getGoHelloButtonLabel() {
        return getResources().getString(R.string.go_hello_button_label);

    }

    private String getSayByeButtonLabel() {
        return getResources().getString(R.string.say_bye_button_label);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // load the data
        presenter.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        presenter.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.onDestroy();
    }

    @Override
    public void onDataUpdated(ByeViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");

        // deal with the data
        ((TextView) findViewById(R.id.byeMessage)).setText(viewModel.byeMessage);
    }


    @Override
    public void navigateToNextScreen() {
        Intent intent = new Intent(this, ByeActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToHelloScreen() {
        Intent intent = new Intent(this, HelloActivity.class);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(ByeContract.Presenter presenter) {
        this.presenter = presenter;
    }
}