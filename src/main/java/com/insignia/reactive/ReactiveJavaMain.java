package com.insignia.reactive;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class ReactiveJavaMain {
	public static void main(String[] args) {
		Observable<String> myObservable = Observable.create(
			    new Observable.OnSubscribe<String>() {
			        @Override
			        public void call(Subscriber<? super String> sub) {
			            sub.onNext("Hello, world!");
			            sub.onCompleted();
			        }
			    }
			);

		Subscriber<String> mySubscriber = new Subscriber<String>() {
		    @Override
		    public void onNext(String s) { System.out.println(s); }

		    @Override
		    public void onCompleted() { }

		    @Override
		    public void onError(Throwable e) { }
		};

		myObservable.subscribe(mySubscriber);

		Observable<Float> myObservable1 = Observable.create(
				new Observable.OnSubscribe<Float>() {
					@Override
					public void call(Subscriber<? super Float> sub) {
						Float myFloat = new Float(8.00);
						sub.onNext(myFloat);
					}
				}
			);


		Action1<Float> onNextAction = new Action1<Float>() {
			@Override
			public void call(Float f){
				System.out.println("this is: "+f);
			}
		};




		myObservable.subscribe();
	}
}
