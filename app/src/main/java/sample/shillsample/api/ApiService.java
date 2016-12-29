package sample.shillsample.api;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Observable;
import sample.shillsample.bean.Girl;

/**
 * Created by we on 2016/12/9.
 */

public interface ApiService {
    @GET("data/福利/{size}/{page}")
    Observable<Girl> getPhotoList(
            @Header("Cache-Control") String cacheControl,
            @Path("size") int size,
            @Path("page") int page);
}
