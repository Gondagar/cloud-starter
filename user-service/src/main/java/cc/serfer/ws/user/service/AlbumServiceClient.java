package cc.serfer.ws.user.service;
/*
import cc.serfer.ws.user.transfer.AlbumResponseModel;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "albums-ws", *//*fallback = AlbumsFallback.class*//* fallbackFactory = AlbumsFallbackFactory.class)
public interface AlbumServiceClient {


    @GetMapping("/users/{id}/albums")
    List<AlbumResponseModel> getAlbums(@PathVariable String id);

}


@Component
class AlbumsFallbackFactory implements FallbackFactory<AlbumServiceClient> {

    @Override
    public AlbumServiceClient create(Throwable throwable) {
        return null;
    }
}



@Slf4j
class AlbumsServiceClientFallback implements AlbumServiceClient {


    private final Throwable cause;

    public AlbumsServiceClientFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public List<AlbumResponseModel> getAlbums(String id) {

        if(cause instanceof FeignException && ((FeignException) cause).status() == 404){
            log.error("404 error took place when getAlbums was called with userId " + id + ". Error massage: "+ cause.getLocalizedMessage());
        } else {
            log.error("Other error took place: " + cause.getLocalizedMessage());
        }

        return new ArrayList<>();
    }
}

///////////////////////////////////////////////////////////

*//*
@Component
class AlbumsFallback implements AlbumServiceClient {

    @Override
    public List<AlbumResponseModel> getAlbums(String id) {
        return new ArrayList<>();
    }
}
*/
