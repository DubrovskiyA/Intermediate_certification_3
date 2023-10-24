package Ext;

import Model.User;
import Model.UserId_token;
import Props.PropertyProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

public class APIcleaner {
    private static final String BASE_URL= PropertyProvider.getInstance().getProps().getProperty("test.baseUrl");
    private static final String USER_NAME=PropertyProvider.getInstance().getProps().getProperty("test.userName");
    private static final String PASS=PropertyProvider.getInstance().getProps().getProperty("test.pass");
    private static final String URL_LOGIN_Path1 ="Account";
    private static final String URL_LOGIN_Path2 ="v1";
    private static final String URL_LOGIN_Path3 ="Login";
    private static final String URL_DELETE_ALL_BOOKS_Path1 ="BookStore";
    private static final String URL_DELETE_ALL_BOOKS_Path2="v1";
    private static final String URL_DELETE_ALL_BOOKS_Path3="Books";
    private OkHttpClient client;
    private ObjectMapper mapper;
    MediaType APPLICATION_JSON=MediaType.parse("application/json; charset=utf-8");

    public APIcleaner() {
        client=new OkHttpClient.Builder().build();
        mapper=new ObjectMapper();
    }
    public void deleteBooks()  {
        try {
//        get user id and token
            HttpUrl url_user=HttpUrl.parse(BASE_URL).newBuilder()
                    .addPathSegment(URL_LOGIN_Path1)
                    .addPathSegments(URL_LOGIN_Path2)
                    .addPathSegments(URL_LOGIN_Path3).build();
            RequestBody body=RequestBody.create(mapper.writeValueAsString(new User(USER_NAME,PASS)),APPLICATION_JSON);
            Request request_getUseId=new Request.Builder().post(body).url(url_user).build();
            Response response1=client.newCall(request_getUseId).execute();
            UserId_token userId_token=mapper.readValue(response1.body().string(), UserId_token.class);
//        delete all books
            HttpUrl url_delete=HttpUrl
                    .parse(BASE_URL).newBuilder()
                    .addPathSegment(URL_DELETE_ALL_BOOKS_Path1)
                    .addPathSegments(URL_DELETE_ALL_BOOKS_Path2)
                    .addPathSegments(URL_DELETE_ALL_BOOKS_Path3)
                    .addQueryParameter("UserId",userId_token.getUserId()).build();
            Request request_delete=new Request.Builder()
                    .delete()
                    .url(url_delete).addHeader("Authorization", "Bearer "+userId_token.getToken()).build();
            client.newCall(request_delete).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
