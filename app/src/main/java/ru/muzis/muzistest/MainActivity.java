package ru.muzis.muzistest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = ButterKnife.findById(this, R.id.main_text);
        App.getInstance()
                .getApiManager()
                .getTopArtists()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        model -> {
                            textView.setText(model.toString());
                        },
                        throwable -> {
                            textView.setText(throwable.getMessage());
                        }
                );
    }
}
