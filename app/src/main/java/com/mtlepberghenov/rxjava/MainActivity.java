package com.mtlepberghenov.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        Model model = new Model();

        model.getData()
                .subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Timber.d("onSubscribe");
            }

            @Override
            public void onNext(Long aLong) {
                Timber.d("onNext: %s", aLong);
            }

            @Override
            public void onError(Throwable e) {
                Timber.d("onError");
            }

            @Override
            public void onComplete() {
                Timber.d("onComplete");
            }
        });
    }
}

class Model {

    public Observable<Long> getData() {
        return Observable.interval(500, TimeUnit.MILLISECONDS);
    }
}
