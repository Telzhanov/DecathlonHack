package com.example.askhat.decathlon.core;

import android.support.annotation.NonNull;
import io.reactivex.Observable;
import retrofit2.*;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
    private final RxJava2CallAdapterFactory original;

    private RxErrorHandlingCallAdapterFactory() {
        original = RxJava2CallAdapterFactory.create();
    }

    public static CallAdapter.Factory create() {
        return new RxErrorHandlingCallAdapterFactory();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxCallAdapterWrapper(retrofit, original.get(returnType, annotations, retrofit));
    }

    private static class RxCallAdapterWrapper implements CallAdapter<Observable<?>, Object> {
        private final Retrofit retrofit;
        private final CallAdapter<?, ?> wrapped;

        public RxCallAdapterWrapper(Retrofit retrofit, CallAdapter<?, ?> wrapped) {
            this.retrofit = retrofit;
            this.wrapped = wrapped;
        }

        @Override
        public Type responseType() {
            return wrapped.responseType();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Observable<?> adapt(@NonNull Call call) {
            return ((Observable) wrapped.adapt(call)).onErrorResumeNext(throwable -> {
                return Observable.error(asRetrofitException((Throwable) throwable));
            });
        }

        private RetrofitException asRetrofitException(Throwable throwable) {
            // We had non-200 http error
            if (throwable instanceof HttpException) {
                HttpException httpException = (HttpException) throwable;
                Response response = httpException.response();
                return RetrofitException.Companion.httpError(response.raw().request().url().toString(), response, retrofit);
            }
            // A network error happened
            if (throwable instanceof IOException) {
                return RetrofitException.Companion.networkError((IOException) throwable);
            }

            // We don't know what happened. We need to simply convert to an unknown error

            return RetrofitException.Companion.unexpectedError(throwable);
        }
    }
}
