package api.poke.apipoke.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface ImageService {
    ByteArrayOutputStream kd(String qq) throws Exception;

    ByteArrayOutputStream zyy(String qq) throws IOException;

    ByteArrayOutputStream psj(String qq) throws IOException;

    ByteArrayOutputStream mb(String qq) throws IOException;

    ByteArrayOutputStream iKun(String qq) throws IOException;

    ByteArrayOutputStream diu(String qq) throws IOException;

    ByteArrayOutputStream gs(String qq) throws IOException, ExecutionException, InterruptedException, TimeoutException, Exception;
}
