package com.novoda.merlin.rxjava;

import android.content.Context;

import com.novoda.merlin.Merlin;
import com.novoda.merlin.MerlinBuilder;
import com.novoda.merlin.NetworkStatus;

import rx.Emitter;
import rx.Observable;

public class MerlinObservable {

    public static Observable<NetworkStatus.State> from(Context context) {
        return from(context, new Merlin.Builder());
    }

    public static Observable<NetworkStatus.State> from(Context context, MerlinBuilder merlinBuilder) {
        return from(merlinBuilder.withAllCallbacks()
                                 .build(context));
    }

    public static Observable<NetworkStatus.State> from(Merlin merlin) {
        return createObservable(merlin);
    }

    private static Observable<NetworkStatus.State> createObservable(Merlin merlin) {
        return Observable.create(new MerlinAction(merlin), Emitter.BackpressureMode.BUFFER)
                         .distinctUntilChanged();
    }
}
