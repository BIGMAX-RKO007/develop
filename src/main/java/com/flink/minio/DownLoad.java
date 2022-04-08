package com.flink.minio;

import io.minio.DownloadObjectArgs;
import io.minio.MinioClient;

public class DownLoad {
    public static void main(String[] args) {
// Create a minioClient with the MinIO server playground, its access keyand secret key.
                MinioClient minioClient =
                MinioClient.builder()
                        .endpoint("http://10.10.41.242:9090")
                        .credentials("minioadmin", "minioadmin")
                        .build();
// Download object given the bucket, object name and output file name
        try {
            minioClient.downloadObject(
                    DownloadObjectArgs.builder()
                            .bucket("jpg")
                            .object("微信图.png")
                            .filename("微信图.png")
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
