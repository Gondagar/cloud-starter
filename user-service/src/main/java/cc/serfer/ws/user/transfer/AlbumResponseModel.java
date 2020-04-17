package cc.serfer.ws.user.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumResponseModel {

        private String albumId;
        private String userId;
        private String name;
        private String description;

}
