package com.lead.projectinstance.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.lead.projectinstance.config.Contracts.API_SERVER;

/**
 * 类名称：RetrofitUtils
 * 创建人：huyufeng
 * 类描述：封装一个retrofit集成0kHttp3的抽象基类
 */
public abstract class RetrofitUtils {
    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;

    /**
     * 获取Retrofit对象
     *
     * @return
     */
    protected static Retrofit getRetrofit() {
        if (null == mRetrofit) {
            if (null == mOkHttpClient) {
                mOkHttpClient = OkHttp3Utils.getOkHttpClient();
            }
            //Retrofit2后使用build设计模式
            mRetrofit = new Retrofit.Builder()
                    //设置服务器路径
                    .baseUrl(API_SERVER + "/")
                    //添加转化库，默认是Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //添加回调库，采用RxJava
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //设置使用okhttp网络请求
                    .client(mOkHttpClient)
                    .build();

        }
        return mRetrofit;
    }

//    /**
//     * 拦截器
//     */
//    private static class MyIntercepter implements Interceptor {
//        DownloadProgressListener listener;
//        public MyIntercepter(DownloadProgressListener listener){
//            this.listener = listener;
//        }
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Response response = chain.proceed(chain.request());
//            return response.newBuilder().body(new DownloadResponseBody(response.body(), listener)).build();
//        }
//    }

}
