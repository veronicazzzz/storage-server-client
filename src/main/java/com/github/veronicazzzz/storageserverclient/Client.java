package com.github.veronicazzzz.storageserverclient;


import com.github.veronicazzzz.storageserverclient.Curl.RequestBuilder;
import com.github.veronicazzzz.storageserverclient.Enum.Routes;
import com.github.veronicazzzz.storageserverclient.Exception.NonSuccessResponseException;
import okhttp3.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Client {
    private final OkHttpClient client;
    private final String url;

    public Client(String url) {
        this.client = new OkHttpClient()
                .newBuilder()
                .build()
        ;
        this.url = url;
    }

    public String getFilesInfo() throws IOException, NonSuccessResponseException {
        Request request = RequestBuilder.build(
                url + Routes.FILES_INFO.getRoute(),
                "GET",
                null
        );

        Response response = this.client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new NonSuccessResponseException(response.toString());
        }

        return response.body().string();
    }

    public String postFile(String filePath) throws IOException {
        File file = new File(filePath);

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(),
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                file))
                .build();

        Request request = RequestBuilder.build(
                url + Routes.FILE.getRoute(),
                "POST",
                body
        );

        Response response = this.client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Failed to post file: " + response);
        }

        return response.body().string();
    }

    public void downloadFile(String fileName, String savePath) throws IOException {
        Request request = RequestBuilder.build(
                url + Routes.FILE.getRoute() + "/" + fileName,
                "GET",
                null
        );

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Failed to get file: " + response);
        }

        File file = new File(savePath);
        file.getParentFile().mkdirs();
        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file, false);
        fos.write(response.body().bytes());
        fos.close();
    }

    public String deleteFile(String fileName) throws IOException, NonSuccessResponseException {
        MediaType mediaType = MediaType.parse("text/plain");

        RequestBody body = RequestBody.create(mediaType, "");

        Request request = RequestBuilder.build(
                url + Routes.FILE.getRoute() + "/" + fileName,
                "DELETE",
                body
        );

        Response response = this.client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new NonSuccessResponseException(response.toString());
        }

        return response.body().string();
    }
}
