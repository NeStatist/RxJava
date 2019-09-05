package com.example.rxjavafuck

import android.annotation.SuppressLint
import android.util.Log
import com.example.rxjavafuck.core.App
import com.example.rxjavafuck.model.Example
import com.example.rxjavafuck.model.Hit
import com.example.rxjavafuck.model.ModelTask
import com.example.rxjavafuck.model.modelUsers.User
import java.util.ArrayList
import java.util.Random
import java.util.concurrent.TimeUnit
import io.reactivex.Maybe
import io.reactivex.MaybeOnSubscribe
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import android.content.ContentValues.TAG
import io.reactivex.functions.BiFunction

class MainPresenter {

    private val tag = "story"


    @SuppressLint("CheckResult")
    fun example() {
        Single.zip(App.instanse?.api!!.example(1, tag),
                App.instanse?.api!!.example(2, tag),
                BiFunction<Example, Example, List<Hit>> { example, example2 ->
                    val hits = ArrayList<Hit>()
                    hits.addAll(example.hits)
                    hits.addAll(example2.hits)
                    hits
                })
                .toObservable()
                .flatMapIterable { hits -> hits }
                .subscribeOn(Schedulers.io())
                .subscribe { hit -> Log.d("Title", "" + hit.title) }
    }

    @SuppressLint("CheckResult")
    fun user() {
        val tag = "story"
        App.instanse?.api!!.example(0, tag)
                .toObservable()
                .flatMapIterable { example2 -> example2.hits }
                .flatMap { hit ->
                    App.instanse?.api!!.user(hit.author).toObservable()
                }
                .filter { user -> user.karma!! > 3000 }
                .distinct()
                .subscribeOn(Schedulers.io())
                .subscribe({ getUser -> Log.d("User", "User " + getUser.username + "Karma " + getUser.karma) }, { it.printStackTrace() })

    }

    @SuppressLint("CheckResult")
    internal fun bang() {
        Maybe.create(MaybeOnSubscribe<Boolean> { emitter ->
            if (Random().nextBoolean()) {
                emitter.onSuccess(true)
                emitter.onComplete()
            } else {
                emitter.onError(IllegalArgumentException("false"))
                emitter.onComplete()
            }
        })
                .subscribe({ s -> Log.d("TAG", s!!.toString() + " Bang") }, { throwable -> throwable.printStackTrace() })

    }

    internal class Task4 {
        fun maybeReturn(): Maybe<String> {
            return Maybe.create(MaybeOnSubscribe<String> { emitter ->
                if (Random().nextBoolean()) {
                    emitter.onSuccess("Bang")
                    emitter.onComplete()
                } else {
                    emitter.onComplete()
                }
            }).subscribeOn(Schedulers.io())
        }

        internal class Task5 {

            private val aTask4 = Task4()
            @SuppressLint("CheckResult")
            fun maybeReturn1() {

                aTask4.maybeReturn()
                        .toSingle("You're live")
                        .subscribe { s -> Log.d("TAG", "" + s) }
            }

        }
    }

    @SuppressLint("CheckResult")
    internal fun task7() {

        Observable.zip<Int, Long, Int>(Observable.range(0, 10),
                Observable.interval(1, TimeUnit.SECONDS),
                BiFunction { integer, _ -> integer })
                .buffer(2)
                .flatMapIterable { integers -> integers }
                .flatMap { t -> App.instanse?.api?.example(t, tag)?.toObservable() }.subscribeOn(Schedulers.io())
                .subscribe { story -> Log.d("Page", "" + story.page) }
    }

    @SuppressLint("CheckResult")
    internal fun task8() {
        Observable.zip(
                App.instanse?.api!!.example(0, tag)
                        .subscribeOn(Schedulers.io())
                        .doOnSuccess { Log.d("TAG", "1:" + Thread.currentThread().name) }
                        .toObservable(),

                App.instanse?.api!!.example(1, tag)
                        .subscribeOn(Schedulers.io())
                        .doOnSuccess { Log.d("Threads", "2:" + Thread.currentThread().name) }
                        .toObservable(),

                BiFunction<Example, Example, List<Hit>> { example, example2 ->
                    val hits = ArrayList<Hit>()
                    hits.addAll(example2.hits)
                    hits.addAll(example2.hits)
                    hits
                })
        .flatMapIterable { hits -> hits }
                .subscribeOn(Schedulers.io())
                .subscribe { hit ->
                    Log.d("TAG", "Title: " + hit.title
                            + "3" + Thread.currentThread().name)
                }

    }

    internal fun task9() {
        Observable.zip<Int, Long, Int>(Observable.range(0, 10),
                Observable.interval(1, TimeUnit.SECONDS),
                BiFunction(){ integer, aLong -> integer })
                .doOnNext { integer ->
                    if (integer == 7) {
                        throw Exception()
                    }
                }
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<Int> {
                    override fun onNext(t: Int) {
                        Log.d("TAG", "" + t)
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.d("TAG", "Subscribed")
                    }


                    override fun onError(e: Throwable) {
                        Log.d("TAG", "error!!")
                    }

                    override fun onComplete() {}
                })
    }

    internal fun task10() {
        val integerBehaviorSubject = BehaviorSubject.create<Int>()
        integerBehaviorSubject.subscribe(getFirstObserver())
        integerBehaviorSubject.onNext(1)
        integerBehaviorSubject.onNext(2)
        integerBehaviorSubject.onNext(3)
        integerBehaviorSubject.subscribe(getSecondObserver())
        integerBehaviorSubject.onNext(4)
        integerBehaviorSubject.onNext(5)
        integerBehaviorSubject.onNext(6)
        integerBehaviorSubject.onComplete()
    }

    private fun getFirstObserver(): Observer<Int> {
        return object : Observer<Int> {
            override fun onComplete() {
                Log.d(TAG, " First onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, " First onSubscribe : " + d.isDisposed)
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, " First onError : " + e.message)
            }

            override fun onNext(t: Int) {
                Log.d(TAG, " First onNext value : $t")

            }
        }
    }
            fun getSecondObserver(): Observer<Int> {
                return object : Observer<Int> {
                    override fun onSubscribe(d: Disposable) {
                        Log.d(TAG, " Second onSubscribe : " + d.isDisposed)
                    }

                    override fun onNext(t: Int) {
                        Log.d(TAG, "" + t)
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, " Second onError : " + e.message)
                    }

                    override fun onComplete() {
                        Log.d(TAG, "Second onComplete")
                    }
                }
            }

            @SuppressLint("CheckResult")
            internal fun task11() {
                App.instanse?.api?.example(0, tag)?.toObservable()
                        ?.flatMap(Function<Example, ObservableSource<ModelTask>> { story ->
                            story.hits[2].author.let {
                                App.instanse?.api?.user(it)
                                        ?.toObservable()
                                        ?.map(Function<User, ModelTask> { user ->
                                            user.username?.let { it1 -> ModelTask(it1, user.karma!!, story.hits[2].title) } })
                            }
                        })?.subscribeOn(Schedulers.io())
                        ?.subscribe(Consumer<ModelTask> { modelTask -> Log.d("LOG", "Username "
                                + modelTask.username +
                                " Karma " + modelTask.karma + " Title " + modelTask.title) })
            }

        }




