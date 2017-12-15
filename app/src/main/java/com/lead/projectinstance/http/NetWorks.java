package com.lead.projectinstance.http;


import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 类名称：NetWorks
 * 创建人：wangliteng
 * 创建时间：2016-5-18 14:57:11
 * 类描述：网络请求的操作类
 */
public class NetWorks extends RetrofitUtils {
    protected static final NetService service = getRetrofit().create(NetService.class);

    //设缓存有效期为1天
    protected static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，使用缓存
    protected static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置。不使用缓存
    protected static final String CACHE_CONTROL_NETWORK = "max-age=0";

    private interface NetService {

//        //POST请求
//        @FormUrlEncoded
//        @POST("bjws/app.user/login")
//        Observable<Verification> getVerfcationCodePost(@Field("tel") String tel, @Field("password") String pass);
//
//        //POST请求
//        @FormUrlEncoded
//        @POST("bjws/app.user/login")
//        Observable<Verification> getVerfcationCodePostMap(@FieldMap Map<String, String> map);
//
        @GET("ses/ew")
        Call<Response> getshow();

        @GET("")
        Call<String> getCall();
        //GET请求
        @GET("bjws/app.user/login")
        Observable<String> getVerfcationGet(@Query("tel") String tel, @Query("password") String pass);
//
//
//        //GET请求，设置缓存
//        @Headers("Cache-Control: public," + CACHE_CONTROL_CACHE)
//        @GET("bjws/app.user/login")
//        Observable<Verification> getVerfcationGetCache(@Query("tel") String tel, @Query("password") String pass);
//
//
//        @Headers("Cache-Control: public," + CACHE_CONTROL_NETWORK)
//        @GET("bjws/app.menu/getMenu")
//        Observable<MenuBean> getMainMenu();
//
//        @FormUrlEncoded
//        @POST
//        Observable<RefundsList> getBackOrderList(@Url String url, @FieldMap Map<String, String> map);
//
//
//        //POST请求
//        @POST("/data/upload/APP/59e98a23f1629.apk")
//        Observable<File> getUploadApk();
    }
//
//    //POST请求
//    public static void verfacationCodePost(String tel, String pass, Observer<Verification> observer){
//        setSubscribe(service.getVerfcationCodePost(tel, pass),observer);
//    }
//
//
//    //POST请求参数以map传入
//    public static void verfacationCodePostMap(Map<String, String> map, Observer<Verification> observer) {
//       setSubscribe(service.getVerfcationCodePostMap(map),observer);
//    }
//
//    //Get请求设置缓存
//    public static void verfacationCodeGetCache(String tel, String pass, Observer<Verification> observer) {
//        setSubscribe(service.getVerfcationGetCache(tel, pass),observer);
//    }
//
//    //Get请求
    public static void verfacationCodeGet(String tel, String pass, Observer<String> observer) {
        setSubscribe(service.getVerfcationGet(tel, pass),observer);
    }

    public static void setHttp(String tel){
        Call<Response> call = service.getshow();

//        Call<String> call = service.getCall();
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });
//
//        try {
//            Response<String> execute = call.execute();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public void setSubsriber(){
        Observable observable = Observable.create(new Observable.OnSubscribe<String>(){

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("");
            }
        });


    }
//
//    //Get请求
//    public static void verfacationCodeGetsub(String tel, String pass, Observer<Verification> observer) {
//        setSubscribe(service.getVerfcationGet(tel, pass),observer);
//    }
//
//    //Get请求
//    public static void Getcache( Observer<MenuBean> observer) {
//        setSubscribe(service.getMainMenu(),observer);
//    }
//
//    public static void backOrderList(Map<String, String> map, Observer<RefundsList> observer){
//        setSubscribe(service.getBackOrderList("getBackOrderList" + getTokenStr("getBackOrderList")+"/cache_token/b65c4b9d30125c0fd229bb5c822ed2d6",map),observer);
//    }


    /**
     * 插入观察者
     *
     * @param observable
     * @param observer
     * @param <T>
     */
    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
//                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }
}
