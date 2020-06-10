package cc.serfer.ws.user.shared;
/*
import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

public class FeignErrorDecoder implements ErrorDecoder {

    @Autowired
    Environment environment;

    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()) {
            case 400:
                return new HystrixBadRequestException(response.reason());
            case 404:
                if(methodKey.contains("getAlbums")) {

                   return new ResponseStatusException(HttpStatus.valueOf(response.status()), environment.getProperty("albums.exception.albums-not-found"));
                }
                break;
            default:
                return new Exception(response.reason());
        }
        return null;
    }
}
*/