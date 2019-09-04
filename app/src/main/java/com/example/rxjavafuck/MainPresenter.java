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
import java.util.concurrent.TimeUnit;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {

    String tag = "story";
    public void example() {
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
            public void subscribe(MaybeEmitter<Boolean> emitter) {
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
                    public void accept(Boolean s) {
                        Log.d("TAG", s + " Bang");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

    }

    static class Task4 {
    Maybe<String> maybeReturn() {

            return Maybe.create(new MaybeOnSubscribe<String>() {
                @Override
                public void subscribe(MaybeEmitter<String> emitter) {
                    if(new Random().nextBoolean()){
                        emitter.onSuccess("Bang");
                        emitter.onComplete();
                    }else {
                        emitter.onComplete();
                    }
                }
            }).subscribeOn(Schedulers.io());
    }

    static class Task5 {

        private Task4 aTask4 = new Task4();

        @SuppressLint("CheckResult")
        void maybeReturn1() {

            aTask4.maybeReturn()
                    .toSingle("You're live")
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) {
                            Log.d("TAG", "" + s);
                        }
                    });
        }

    }
    }
    @SuppressLint("CheckResult")
    void task7(){

        Observable.zip(Observable.range(0, 10),
                Observable.interval(1, TimeUnit.SECONDS),
                (integer, aLong) -> integer)
                .buffer(2)
                .flatMapIterable(new Function<List<Integer>, Iterable<Integer>>(){
                    @Override
                    public Iterable<Integer> apply(List<Integer> integers) {
                        return integers;
                    }
                }).flatMap(new Function<Integer, ObservableSource<Example>>() {
            @Override
            public ObservableSource<Example> apply(Integer integer) {
                return App.getInstanse().getApi().example(integer, tag).toObservable();
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Example>() {
                    @Override
                    public void accept(Example story) {
                        Log.d("Page", "" + story.getPage());
                    }
                });
                    }
     @SuppressLint("CheckResult")
     void task8 (){
         Observable.zip(App.getInstanse().getApi().example(0, tag)
                         .subscribeOn(Schedulers.io())
                         .doOnSuccess(story -> Log.d("TAG", "1:" + Thread.currentThread().getName()))
                         .toObservable(),

                 App.getInstanse().getApi().example(1, tag)
                         .subscribeOn(Schedulers.io())
                         .doOnSuccess(story -> Log.d("TAG", "2:" + Thread.currentThread().getName()))
                         .toObservable(),

                 (example, example2) -> {
                     List<Hit> hits = new ArrayList<>();
                     hits.addAll(example.getHits());
                     hits.addAll(example2.getHits());
                     return hits;
                 })
                 .flatMapIterable(hits -> hits)
                 .subscribeOn(Schedulers.io())
                 .subscribe(hit -> {
                     Log.d("TAG", "Title: " + hit.getTitle()
                     + "3" + Thread.currentThread().getName());
                 });

     }

     void task9(){
         Observable.zip(Observable.range(0, 10),
                 Observable.interval(1, TimeUnit.SECONDS),
                 (integer, aLong) -> integer)
                 .doOnNext(new Consumer<Integer>() {
                     @Override
                     public void accept(Integer integer) throws Exception {
                         if(integer == 7){ throw new Exception();
                         }
                     }
                 })
                 .subscribeOn(Schedulers.io())
                 .subscribe(new Observer<Integer>() {
                     @Override
                     public void onSubscribe(Disposable d) {
                         Log.d("TAG", "Subscribed");
                     }
                     @Override
                     public void onNext(Integer integer) {
                         Log.d("TAG", "" + integer);
                     }
                     @Override
                     public void onError(Throwable e) {
                         Log.d("TAG", "error!!");
                     }
                     @Override
                     public void onComplete() {
                     }
                 });
     }
     

}


