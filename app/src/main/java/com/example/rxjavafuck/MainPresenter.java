package com.example.rxjavafuck;

import android.annotation.SuppressLint;
import android.util.Log;
import com.example.rxjavafuck.core.App;
import com.example.rxjavafuck.model.Example;
import com.example.rxjavafuck.model.Hit;
import com.example.rxjavafuck.model.modelUsers.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {

    public void example() {
        String tag = "story";
        Disposable title = Single.zip(App.getInstanse().getApi().example(1, tag),
                App.getInstanse().getApi().example(2, tag),
                (example, example2) -> {
                    List<Hit> hits = new ArrayList<>();
                    hits.addAll(example.getHits());
                    hits.addAll(example2.getHits());
                    return hits;
                })
                .toObservable()
                .flatMapIterable(hits -> hits)
                .subscribeOn(Schedulers.io())
                .subscribe(hit -> Log.d("Title", "" + hit.getTitle())
                        , Throwable::printStackTrace);
    }

    @SuppressLint("CheckResult")
    public void user() {
        String tag = "story";
        App.getInstanse().getApi().example(0, tag)
                .toObservable()
                .flatMapIterable(Example::getHits)
                .flatMap((Function<Hit, ObservableSource<User>>) hit -> App.getInstanse().getApi().user(hit.getAuthor()).toObservable())
                .filter(user -> user.getKarma() > 3000)
                .distinct()
                .subscribeOn(Schedulers.io())
                .subscribe((io.reactivex.functions.Consumer<? super User>) user -> Log.d(
                        "User", "User " + user.getUsername() + "Karma " + user.getKarma()), Throwable::printStackTrace);
    }

    @SuppressLint("CheckResult")
    void bang() {
        Maybe.create(new MaybeOnSubscribe<Boolean>() {
            @Override
            public void subscribe(MaybeEmitter<Boolean> emitter) throws Exception {
                if (new Random().nextBoolean()) {
                    emitter.onSuccess(true);
                    emitter.onComplete();
                } else {
                    emitter.onError(new IllegalArgumentException("false"));
                    emitter.onComplete();
                }
            }
        })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean s) throws Exception {
                        Log.d("TAG", s + " Bang");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });

    }


    static class TaskR {
    Maybe<String> maybeReturn() {

            return Maybe.create(new MaybeOnSubscribe<String>() {
                @Override
                public void subscribe(MaybeEmitter<String> emitter) throws Exception {
                    if(new Random().nextBoolean()){
                        emitter.onSuccess("Bang");
                        emitter.onComplete();
                    }else {
                        emitter.onComplete();
                    }
                }
            }).subscribeOn(Schedulers.io());
    }

    public static class Task5 {
        private TaskR aTaskR = new TaskR();

        @SuppressLint("CheckResult")
        public void maybeReturn1() {

            aTaskR.maybeReturn()
                    .toSingle("You're live")
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                            Log.d("TAG", "" + s);
                        }
                    });
        }

    }
    }

}

